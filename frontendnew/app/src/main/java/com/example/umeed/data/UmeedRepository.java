package com.example.umeed.data;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.umeed.MyApplication;
import com.example.umeed.data.model.request.LoginRequestModel;
import com.example.umeed.data.model.request.ProfileRequestModel;
import com.example.umeed.data.model.request.RegisterRequestModel;
import com.example.umeed.data.model.response.LoginResponseModel;
import com.example.umeed.data.model.response.ProfileDetailsResponseModel;
import com.example.umeed.data.model.response.RegisterResponseModel;
import com.example.umeed.data.network.APIInterface;
import com.example.umeed.data.network.RetrofitService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UmeedRepository {
    private static final String TAG = "UmeedRepository";
    private static UmeedRepository umeedRepository;
    private APIInterface apiInterface;

    public UmeedRepository() {
        apiInterface = RetrofitService.getRetrofitInstance().create(APIInterface.class);
    }

    public static UmeedRepository getInstance() {
        if (umeedRepository == null) {
            umeedRepository = new UmeedRepository();
        }
        return umeedRepository;
    }

    public LiveData<RegisterResponseModel> registerUser(RegisterRequestModel registerRequestModel){
        MutableLiveData<RegisterResponseModel> registerResponseModelMutableLiveData=new MutableLiveData<>();
        apiInterface.mobileRegister(registerRequestModel).enqueue(new Callback<RegisterResponseModel>() {
            @Override
            public void onResponse(Call<RegisterResponseModel> call, Response<RegisterResponseModel> response) {
                if (response.code() == 500) {
//                    Toast.makeText(requireA, "Server Error", Toast.LENGTH_SHORT).show();
                    registerResponseModelMutableLiveData.setValue(null);
                } else {
                    registerResponseModelMutableLiveData.setValue(response.body());
                    Log.d(TAG, "onResponse: " + new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<RegisterResponseModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
                registerResponseModelMutableLiveData.setValue(null);
            }
        });
        return registerResponseModelMutableLiveData;
    }

    public LiveData<LoginResponseModel> login(LoginRequestModel loginRequestModel){
        MutableLiveData<LoginResponseModel> loginResponseModelMutableLiveData=new MutableLiveData<>();
        apiInterface.mobileLogin(loginRequestModel).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.code() == 500) {
//                    Toast.makeText(requireA, "Server Error", Toast.LENGTH_SHORT).show();
                    loginResponseModelMutableLiveData.setValue(null);
                } else {
                    loginResponseModelMutableLiveData.setValue(response.body());
                    Log.d(TAG, "onResponse: " + new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
                loginResponseModelMutableLiveData.setValue(null);
            }
        });
        return loginResponseModelMutableLiveData;
    }

    public LiveData<ProfileDetailsResponseModel> getInfo(String token){
        MutableLiveData<ProfileDetailsResponseModel> profileDetailsResponseModelMutableLiveData=new MutableLiveData<>();
        apiInterface.getInfo(token).enqueue(new Callback<ProfileDetailsResponseModel>() {
            @Override
            public void onResponse(Call<ProfileDetailsResponseModel> call, Response<ProfileDetailsResponseModel> response) {
                if(response.code()==500){
                    profileDetailsResponseModelMutableLiveData.setValue(null);
                }else{
                    profileDetailsResponseModelMutableLiveData.setValue(response.body());
                    Log.d(TAG, "onResponse: " + new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<ProfileDetailsResponseModel> call, Throwable t) {
                Log.d(TAG,"onResponse: "+t);
                profileDetailsResponseModelMutableLiveData.setValue(null);

            }
        });
        return profileDetailsResponseModelMutableLiveData;
    }
}
