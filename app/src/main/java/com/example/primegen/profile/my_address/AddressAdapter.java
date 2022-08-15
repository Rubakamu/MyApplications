package com.example.primegen.profile.my_address;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primegen.databinding.ListItemMyAddressBinding;
import com.example.primegen.test.TestClickListener;

import java.util.ArrayList;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {

    private Context mContext;
    private ArrayList<Addressbook> mAddressList;

    public AddressAdapter(Context context, ArrayList<Addressbook> addressList){
        mContext = context;
        mAddressList = addressList;

    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemMyAddressBinding myAddressBinding = ListItemMyAddressBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AddressViewHolder(myAddressBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        AddressViewHolder viewHolder = (AddressAdapter.AddressViewHolder) holder;
        Addressbook addressbook = mAddressList.get(position);

        viewHolder.mMyAddressBinding.tvType.setText(addressbook.getAbAddressType());
        viewHolder.mMyAddressBinding.edtFullName.setText(addressbook.getAbCustomerName());
        viewHolder.mMyAddressBinding.tvPhone.setText(addressbook.getAbCustomerPhone());
        viewHolder.mMyAddressBinding.tvEmail.setText(addressbook.getAbCustomerEmail());
        viewHolder.mMyAddressBinding.tvAddress.setText(addressbook.getAbAddressline1());
        viewHolder.mMyAddressBinding.tvAddressLine1.setText(addressbook.getAbAddressline2());
        viewHolder.mMyAddressBinding.tvCity.setText(String.format("%s-%s", addressbook.getAbCity(), addressbook.getAbPincode()));
        viewHolder.mMyAddressBinding.tvLandmark.setText(addressbook.getAbLandMark());
        // myAddressBinding.tvState.setText(addressBookResponse.getAddressbook().getAbState());

    }

    @Override
    public int getItemCount() {
        return mAddressList.size();
    }

    public class AddressViewHolder extends RecyclerView.ViewHolder {
        ListItemMyAddressBinding mMyAddressBinding;

        public AddressViewHolder(@NonNull ListItemMyAddressBinding itemView) {
            super(itemView.getRoot());

            mMyAddressBinding = itemView;
        }
    }
}
