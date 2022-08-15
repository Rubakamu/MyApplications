package com.example.primegen.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.primegen.base_properties.BaseResponse;
import com.example.primegen.base_properties.Branch;
import com.example.primegen.base_properties.BranchResponse;
import com.example.primegen.login.LoginResponse;
import com.example.primegen.profile.family.FamilyResponse;
import com.example.primegen.profile.my_address.AddressBookResponse;
import com.example.primegen.profile.my_test.BookingHistoryResponse;
import com.example.primegen.profile.my_test.booking_details.BookingDetailsResponse;
import com.example.primegen.repository.UserRepository;
import com.example.primegen.test.Test;
import com.example.primegen.test.TestResponse;

import java.util.ArrayList;
import java.util.List;

public class PrimeViewModel extends AndroidViewModel {
    private final UserRepository userRepository;

    public PrimeViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public LiveData<TestResponse> getAllTestData() {
        return userRepository.getTestLiveData();
    }
    public LiveData<BranchResponse> getAllBrachData() {
        return userRepository.getBranchLiveData();
    }

    public LiveData<BaseResponse> insertUser(String username, String customerName, String email, String password) {
        return userRepository.getCreateUserLiveData(username, customerName, email, password);
    }

    public LiveData<LoginResponse> loginUser(String email, String password) {
        return userRepository.getLoginLiveData(email, password);
    }


    public LiveData<BaseResponse> updateUser(String customerID, String customerTitle, String customerName, String customerLastname,
                                             String customerUsername, String customerPassword, String customerEmail, String customerDOB,
                                             String customerAge, String customerGender, String customerBloodGroup) {


        return userRepository.getUpdateUserLiveData(customerID, customerTitle, customerName, customerLastname, customerUsername,
                customerPassword, customerEmail, customerDOB, customerAge, customerGender, customerBloodGroup);


    }

    public LiveData<LoginResponse> otpVerification(String otp, String id) {
        return userRepository.getOtpLiveData(otp, id);
    }

    public LiveData<BaseResponse> forgotPassword(String phone) {
        return userRepository.getForgotPasswordLiveData(phone);
    }

    public LiveData<LoginResponse> resetPassword(String resetPassword, String id) {
        return userRepository.getResetPasswordLiveData(resetPassword, id);
    }

    public LiveData<AddressBookResponse> addressBook(String customerID) {
        return userRepository.getAddressBookMutableLiveData(customerID);
    }

    public LiveData<AddressBookResponse> addAddress(String customerID, String customerName, String customerUsername, String customerEmail, String abAddressline1, String abAddressline2, String abCity, String abState,
                                                    String abLandMark, String abPincode, String abAddressType, String abLabel, String abLocation, String abCurrent,
                                                    String abGender, String abDOB, String abRelationship) {
        return userRepository.getAddAddressMutableLiveData(customerID, customerName, customerUsername, customerEmail, abAddressline1, abAddressline2, abCity, abState, abLandMark, abPincode, abAddressType, abLabel, abLocation, abCurrent, abGender, abDOB, abRelationship);
    }

    public LiveData<FamilyResponse> familyList(String customerID) {
        return userRepository.getFamilyListMutableLiveData(customerID);
    }

    public LiveData<FamilyResponse> addFamilyMember(String customerID, String relativeName, String relativeAltMobile, String relativeDOB, String relativeGender, String customerRelationship) {
        return userRepository.getAddFamilyMemberMutableLiveData(customerID, relativeName, relativeAltMobile, relativeDOB, relativeGender, customerRelationship);
    }

    public LiveData<BookingHistoryResponse> bookingHistory(String customerID) {
        return userRepository.getBookingHistoryResponseMutableLiveData(customerID);
    }

    public LiveData<BookingDetailsResponse> bookingDetails(String customerID,String bookingUniqueID) {
        return userRepository.getDetailsMutableLiveData(customerID,bookingUniqueID);
    }

    public LiveData<BaseResponse> checkout(String customerID, String bookingRequestedDate, String bookingNotes, String abID, String bookingPaymentMode, String SessbookingFullAddress, String bookingTests, String SessbookingTotalPackages, String SessbookingTotalQty, String SessbookingSubtotal, String SessbookingTotalAmount, String bookingCouponID, String bookingCouponAmount, String relativeID, String branchLocation, ArrayList<String> bookingtestprofileID, ArrayList<String> bookingTestPackageQty, ArrayList<String> bookingTestPackageAmount, ArrayList<String> bookingdetailSubtotal, ArrayList<String> bookingTestorProfile) {
        return userRepository.getCheckoutMutableLiveData(customerID,bookingRequestedDate,bookingNotes,abID,bookingPaymentMode,SessbookingFullAddress,bookingTests,SessbookingTotalPackages,SessbookingTotalQty,SessbookingSubtotal,SessbookingTotalAmount,bookingCouponID,bookingCouponAmount,relativeID,branchLocation,bookingtestprofileID,bookingTestPackageQty,bookingTestPackageAmount,bookingdetailSubtotal,bookingTestorProfile);
    }

    public boolean isMatchesFound(String searchText, Test test) {
//        return kural.getTranslation().toLowerCase().contains(searchText) ||
//                kural.getTamilTranslation().toLowerCase().contains(searchText) ||
//                kural.getMk().toLowerCase().contains(searchText) ||
//                kural.getMv().toLowerCase().contains(searchText) ||
//                kural.getSp().toLowerCase().contains(searchText) ||
//                kural.getExplanation().contains(searchText) ||
//                kural.getCouplet().contains(searchText);

        return test.getTestprofileName().toLowerCase().contains(searchText) ||
                test.getTestprofileCondition().toLowerCase().contains(searchText);
    }

}
