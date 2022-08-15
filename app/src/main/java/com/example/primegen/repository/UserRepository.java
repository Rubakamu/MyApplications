package com.example.primegen.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.primegen.api.RestApiService;
import com.example.primegen.api.RetrofitInstance;
import com.example.primegen.base_properties.BaseResponse;
import com.example.primegen.base_properties.Branch;
import com.example.primegen.base_properties.BranchResponse;
import com.example.primegen.login.LoginResponse;
import com.example.primegen.profile.family.FamilyResponse;
import com.example.primegen.profile.my_address.AddressBookResponse;
import com.example.primegen.profile.my_test.BookingHistoryResponse;
import com.example.primegen.profile.my_test.booking_details.BookingDetailsResponse;
import com.example.primegen.signup.User;
import com.example.primegen.test.TestResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private ArrayList<User> users = new ArrayList<>();
    private MutableLiveData<TestResponse> testLiveData = new MutableLiveData<>();
    private MutableLiveData<BaseResponse> createUserLiveData = new MutableLiveData<>();
    private MutableLiveData<BaseResponse> updateUserLiveData = new MutableLiveData<>();
    private MutableLiveData<LoginResponse> loginLiveData = new MutableLiveData<>();
    private MutableLiveData<LoginResponse> otpLiveData = new MutableLiveData<>();
    private MutableLiveData<BaseResponse> forgotPasswordLiveData = new MutableLiveData<>();
    private MutableLiveData<LoginResponse> resetPasswordLiveData = new MutableLiveData<>();
    private MutableLiveData<AddressBookResponse> addressBookMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<AddressBookResponse> addAddressMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<FamilyResponse> familyListMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<FamilyResponse> addFamilyMemberMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<BookingHistoryResponse> bookingHistoryResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<BookingDetailsResponse> bookingDetailsMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<BaseResponse> checkoutMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<BranchResponse> branchLiveData = new MutableLiveData<>();


    private Application application;

    public UserRepository(Application application) {

        this.application = application;
    }

    public MutableLiveData<TestResponse> getTestLiveData() {
        RestApiService test = RetrofitInstance.getApiService();
        Call<TestResponse> call = test.getAllTestData();

        call.enqueue(new Callback<TestResponse>() {
            @Override
            public void onResponse(Call<TestResponse> call, Response<TestResponse> response) {
                if (response.body() != null) {
                    testLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<TestResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), "Please check your Internet connections"+t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        return testLiveData;
    }

    public MutableLiveData<BaseResponse> getCreateUserLiveData(String username, String customerName, String email, String password) {
        RestApiService user = RetrofitInstance.getApiService();
        Call<User> call = user.insertUser(username, customerName, email, password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                createUserLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        return createUserLiveData;
    }


    public MutableLiveData<LoginResponse> getLoginLiveData(String email, String password) {
        RestApiService user = RetrofitInstance.getApiService();
        Call<LoginResponse> call = user.loginUser(email, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loginLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
        return loginLiveData;
    }

    public MutableLiveData<BaseResponse> getUpdateUserLiveData(String customerID, String customerTitle, String customerName, String customerLastname,
                                                               String customerUsername, String customerPassword, String customerEmail, String customerDOB,
                                                               String customerAge, String customerGender, String customerBloodGroup) {
        RestApiService updateUser = RetrofitInstance.getApiService();
        Call<User> call = updateUser.updateUser(customerID, customerTitle, customerName, customerLastname, customerUsername,
                customerPassword, customerEmail, customerDOB, customerAge, customerGender, customerBloodGroup);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                updateUserLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return updateUserLiveData;
    }


    public MutableLiveData<LoginResponse> getOtpLiveData(String otp, String id) {
        RestApiService otpVerification = RetrofitInstance.getApiService();
        Call<LoginResponse> call = otpVerification.otpVerification(otp, id);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                otpLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        return otpLiveData;
    }

    public MutableLiveData<BaseResponse> getForgotPasswordLiveData(String phone) {
        RestApiService forgotPassword = RetrofitInstance.getApiService();
        Call<LoginResponse> call = forgotPassword.forgotPassword(phone);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                forgotPasswordLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
        return forgotPasswordLiveData;
    }

    public MutableLiveData<LoginResponse> getResetPasswordLiveData(String resetPassword, String id) {
        RestApiService changePassword = RetrofitInstance.getApiService();
        Call<LoginResponse> call = changePassword.setResetPassword(resetPassword, id);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                resetPasswordLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return resetPasswordLiveData;
    }

    public MutableLiveData<AddressBookResponse> getAddressBookMutableLiveData(String customerID) {
        RestApiService addressBook = RetrofitInstance.getApiService();
        Call<AddressBookResponse> call = addressBook.addressBook(customerID);
        call.enqueue(new Callback<AddressBookResponse>() {
            @Override
            public void onResponse(Call<AddressBookResponse> call, Response<AddressBookResponse> response) {
                addressBookMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<AddressBookResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return addressBookMutableLiveData;
    }

    public MutableLiveData<AddressBookResponse> getAddAddressMutableLiveData(String customerID, String customerName, String customerUsername, String customerEmail, String abAddressline1, String abAddressline2, String abCity, String abState,
                                                                             String abLandMark, String abPincode, String abAddressType, String abLabel, String abLocation, String abCurrent,
                                                                             String abGender, String abDOB, String abRelationship) {
        RestApiService addAddress = RetrofitInstance.getApiService();
        Call<AddressBookResponse> call = addAddress.addAddress(customerID, customerName, customerUsername, customerEmail, abAddressline1, abAddressline2, abCity, abState, abLandMark, abPincode, abAddressType, abLabel, abLocation, abCurrent, abGender, abDOB, abRelationship);
        call.enqueue(new Callback<AddressBookResponse>() {
            @Override
            public void onResponse(Call<AddressBookResponse> call, Response<AddressBookResponse> response) {
                addAddressMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<AddressBookResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();


            }
        });

        return addAddressMutableLiveData;
    }

    public MutableLiveData<FamilyResponse> getFamilyListMutableLiveData(String customerID) {
        RestApiService familyList = RetrofitInstance.getApiService();
        Call<FamilyResponse> call = familyList.familyList(customerID);
        call.enqueue(new Callback<FamilyResponse>() {
            @Override
            public void onResponse(Call<FamilyResponse> call, Response<FamilyResponse> response) {
                familyListMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<FamilyResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return familyListMutableLiveData;
    }

    public MutableLiveData<FamilyResponse> getAddFamilyMemberMutableLiveData(String customerID, String relativeName, String relativeAltMobile, String relativeDOB, String relativeGender, String customerRelationship) {
        RestApiService addFamily = RetrofitInstance.getApiService();
        Call<FamilyResponse> call = addFamily.addFamilyMember(customerID, relativeName, relativeAltMobile, relativeDOB, relativeGender, customerRelationship);
        call.enqueue(new Callback<FamilyResponse>() {
            @Override
            public void onResponse(Call<FamilyResponse> call, Response<FamilyResponse> response) {
                addFamilyMemberMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<FamilyResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return addFamilyMemberMutableLiveData;
    }

    public MutableLiveData<BookingHistoryResponse> getBookingHistoryResponseMutableLiveData(String customerID) {
        RestApiService bookingHistory = RetrofitInstance.getApiService();
        Call<BookingHistoryResponse> call = bookingHistory.bookingHistory(customerID);
        call.enqueue(new Callback<BookingHistoryResponse>() {
            @Override
            public void onResponse(Call<BookingHistoryResponse> call, Response<BookingHistoryResponse> response) {
                bookingHistoryResponseMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<BookingHistoryResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return bookingHistoryResponseMutableLiveData;
    }

    public MutableLiveData<BookingDetailsResponse> getDetailsMutableLiveData(String customerID, String bookingUniqueID) {
        RestApiService bookingDetails = RetrofitInstance.getApiService();
        Call<BookingDetailsResponse> call = bookingDetails.bookingDetails(customerID, bookingUniqueID);

        call.enqueue(new Callback<BookingDetailsResponse>() {
            @Override
            public void onResponse(Call<BookingDetailsResponse> call, Response<BookingDetailsResponse> response) {
                bookingDetailsMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<BookingDetailsResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        return bookingDetailsMutableLiveData;
    }

    public MutableLiveData<BaseResponse> getCheckoutMutableLiveData(String customerID, String bookingRequestedDate, String bookingNotes, String abID, String bookingPaymentMode, String SessbookingFullAddress, String bookingTests, String SessbookingTotalPackages, String SessbookingTotalQty, String SessbookingSubtotal, String SessbookingTotalAmount, String bookingCouponID, String bookingCouponAmount, String relativeID, String branchLocation, ArrayList<String> bookingtestprofileID, ArrayList<String> bookingTestPackageQty, ArrayList<String> bookingTestPackageAmount, ArrayList<String> bookingdetailSubtotal, ArrayList<String> bookingTestorProfile) {
        RestApiService checkout = RetrofitInstance.getApiService();
        Call<BaseResponse> call = checkout.checkout(customerID,bookingRequestedDate,bookingNotes,abID,bookingPaymentMode,SessbookingFullAddress,bookingTests,SessbookingTotalPackages,SessbookingTotalQty,SessbookingSubtotal,SessbookingTotalAmount,bookingCouponID,bookingCouponAmount,relativeID,branchLocation,bookingtestprofileID,bookingTestPackageQty,bookingTestPackageAmount,bookingdetailSubtotal,bookingTestorProfile);

        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                checkoutMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return checkoutMutableLiveData;
    }

    public MutableLiveData<BranchResponse> getBranchLiveData() {
        RestApiService branch = RetrofitInstance.getApiService();
        Call<BranchResponse> call = branch.getAllBranchData();

        call.enqueue(new Callback<BranchResponse>() {
            @Override
            public void onResponse(Call<BranchResponse> call, Response<BranchResponse> response) {
                if (response.body() != null) {
                    branchLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BranchResponse> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        return branchLiveData;
    }
}
