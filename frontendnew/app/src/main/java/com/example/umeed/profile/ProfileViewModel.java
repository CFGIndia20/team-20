package com.example.umeed.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.umeed.data.UmeedRepository;
import com.example.umeed.data.model.request.ProfileRequestModel;
import com.example.umeed.data.model.request.RegisterRequestModel;
import com.example.umeed.data.model.response.ProfileDetailsResponseModel;
import com.example.umeed.data.model.response.RegisterResponseModel;
import com.example.umeed.data.network.RetrofitService;

public class ProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileViewModel";
    private UmeedRepository umeedRepository;

    public ProfileViewModel() {
        umeedRepository = UmeedRepository.getInstance();
    }

    public LiveData<ProfileDetailsResponseModel> getInfo(String token) {

        ProfileRequestModel profileRequestModel=new ProfileRequestModel();
        profileRequestModel.setToken("Token "+token);

        return umeedRepository.getInfo("Token "+token);
    }
}
