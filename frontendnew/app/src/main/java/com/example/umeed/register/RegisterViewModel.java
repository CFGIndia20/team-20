package com.example.umeed.register;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.umeed.data.UmeedRepository;
import com.example.umeed.data.model.request.RegisterRequestModel;
import com.example.umeed.data.model.response.RegisterResponseModel;
import com.example.umeed.data.network.RetrofitService;

import java.util.regex.Pattern;

public class RegisterViewModel extends ViewModel {
    private static final String TAG = "RegisterViewModel";
    String mobileNumber;
    private UmeedRepository umeedRepository;

    public RegisterViewModel() {
        umeedRepository = UmeedRepository.getInstance();
    }

    public LiveData<RegisterResponseModel> signUp(String firstName,String lastName,String mobileNumber1,String area,String skills, String password ) {
        Log.d(TAG, "signIn: " + RetrofitService.getTOKEN());
        Log.d(TAG, "signIn: mobile: " + mobileNumber);
        RegisterRequestModel requestModel = new RegisterRequestModel();
        requestModel.setFirst_name(firstName);
        requestModel.setLast_name(lastName);
        requestModel.setArea(area);
        requestModel.setMobile(mobileNumber1);
        requestModel.setSkills(skills);
        requestModel.setPassword(password);

        return umeedRepository.registerUser(requestModel);
    }

    boolean isValidMobile(String phone) {
        this.mobileNumber = phone;
        return !Pattern.matches("\"(0/91)?[7-9][0-9]{9}\"", phone) && phone.length() == 10;
    }

}
