package com.example.primegen.prescription;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.primegen.R;
import com.example.primegen.databinding.FragmentPrescriptionBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PrescriptionFragment extends Fragment {

    private FragmentPrescriptionBinding mPrescriptionBinding;
    private static final int CAMERA_REQUEST_CODE = 101;
    private static final int GALLERY_REQ_CODE = 102;

    public static final int REQUEST_IMAGE = 100;
    public static final int REQUEST_PERMISSION = 200;
    private String imageFilePath = "";
    private String imageFileName = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mPrescriptionBinding = FragmentPrescriptionBinding.inflate(inflater);
        setBackPress();
        takePictureFromGallery();
        takePictureFromCamera();

        return mPrescriptionBinding.getRoot();
    }

    private void takePictureFromCamera() {
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION);
        }

        mPrescriptionBinding.takePicture.setOnClickListener(v -> {

            openCameraIntent();
        });

    }

    private void openCameraIntent() {
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            Uri photoUri = FileProvider.getUriForFile(requireActivity(), requireActivity().getPackageName() + ".provider", photoFile);
            pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(pictureIntent, REQUEST_IMAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireActivity(), "Thanks for granting Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE) {
                mPrescriptionBinding.prescriptionImage.setImageURI(Uri.parse(imageFilePath));
                mPrescriptionBinding.text.setText(imageFileName + ".jpg");

            } else if (requestCode == GALLERY_REQ_CODE) {
                imageFilePath = getImageFilePath(data.getData());
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                imageFileName = "IMG_" + timeStamp;
                mPrescriptionBinding.prescriptionImage.setImageURI(Uri.parse(imageFilePath));
                mPrescriptionBinding.text.setText(imageFileName);

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(requireActivity(), "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }


            Bundle bundle = new Bundle();
            bundle.putString("image", imageFilePath);
            bundle.putString("imageName", imageFileName);
            Navigation.findNavController(mPrescriptionBinding.getRoot()).navigate(R.id.action_uploaded_prescription,bundle);
        }
    }

    private File createImageFile() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        imageFileName = "IMG_" + timeStamp;
        File storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        imageFilePath = image.getAbsolutePath();

        return image;
    }

    private void takePictureFromGallery() {
        mPrescriptionBinding.gallery.setOnClickListener(v -> {
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(i, GALLERY_REQ_CODE);

        });
    }

    public String getImageFilePath(Uri uri) {
        String path = null, image_id = null;

        Cursor cursor = requireActivity().getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            image_id = cursor.getString(0);
            image_id = image_id.substring(image_id.lastIndexOf(":") + 1);
            cursor.close();
        }

        Cursor cursor1 = requireActivity().getContentResolver().query(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media._ID + " = ? ", new String[]{image_id}, null);
        if (cursor1 != null) {
            cursor1.moveToFirst();
            path = cursor1.getString(cursor1.getColumnIndex(MediaStore.Images.Media.DATA));
            cursor1.close();
        }
        return path;
    }

    private void setBackPress() {
        mPrescriptionBinding.back.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });
    }


}