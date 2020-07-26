package com.example.umeed.data;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.umeed.MyApplication;
import com.example.umeed.data.model.request.AttendanceRequestModel;
import com.example.umeed.data.model.request.CreateMeetingRequestModel;
import com.example.umeed.data.model.request.LoginRequestModel;
import com.example.umeed.data.model.request.ProfileRequestModel;
import com.example.umeed.data.model.request.RegisterRequestModel;
import com.example.umeed.data.model.response.AttendanceResponseModel;
import com.example.umeed.data.model.response.CreateMeetingResponseModel;
import com.example.umeed.data.model.response.GetAllMeetingsResponseModel;
import com.example.umeed.data.model.response.LoginResponseModel;
import com.example.umeed.data.model.response.ManagerUserResponseModel;
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

    public LiveData<ManagerUserResponseModel> getUserData(String token){
        MutableLiveData<ManagerUserResponseModel> managerUserResponseModelMutableLiveData=new MutableLiveData<>();
        apiInterface.getUsers(token).enqueue(new Callback<ManagerUserResponseModel>() {
            @Override
            public void onResponse(Call<ManagerUserResponseModel> call, Response<ManagerUserResponseModel> response) {
                if(response.code()==500){
                    managerUserResponseModelMutableLiveData.setValue(null);
                    Log.d(TAG, "onResponse: "+response.body());
                }else{
                    managerUserResponseModelMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ManagerUserResponseModel> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t);
            }
        });
        return managerUserResponseModelMutableLiveData;
    }

    public LiveData<GetAllMeetingsResponseModel> getMeetingsUser(String token){
        MutableLiveData<GetAllMeetingsResponseModel> getAllMeetingsResponseModelMutableLiveData=new MutableLiveData<>();
        apiInterface.getMeetings(token).enqueue(new Callback<GetAllMeetingsResponseModel>() {
            @Override
            public void onResponse(Call<GetAllMeetingsResponseModel> call, Response<GetAllMeetingsResponseModel> response) {
                if(response.code()==500){
                    Log.d(TAG, "onResponse: "+response.body());
                    getAllMeetingsResponseModelMutableLiveData.setValue(null);
                }else {
                    getAllMeetingsResponseModelMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GetAllMeetingsResponseModel> call, Throwable t) {
                getAllMeetingsResponseModelMutableLiveData.setValue(null);
                Log.d(TAG, "onFailure: "+t);
            }
        });
        return getAllMeetingsResponseModelMutableLiveData;
    }

    public LiveData<CreateMeetingResponseModel> createMeeting(String token, CreateMeetingRequestModel createMeetingRequestModel){
        MutableLiveData<CreateMeetingResponseModel> createMeetingResponseModelMutableLiveData=new MutableLiveData<>();
        apiInterface.create(token,createMeetingRequestModel).enqueue(new Callback<CreateMeetingResponseModel>() {
            @Override
            public void onResponse(Call<CreateMeetingResponseModel> call, Response<CreateMeetingResponseModel> response) {
                if(response.code()==500){
                    Log.d(TAG, "onResponse: "+response.body());
                    createMeetingResponseModelMutableLiveData.setValue(null);
                }else{
                    createMeetingResponseModelMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CreateMeetingResponseModel> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t);
                createMeetingResponseModelMutableLiveData.setValue(null);
            }
        });
        return createMeetingResponseModelMutableLiveData;
    }

    public LiveData<AttendanceResponseModel> getAtt(String token, AttendanceRequestModel requestModel){
        MutableLiveData<AttendanceResponseModel> attendanceResponseModelMutableLiveData=new MutableLiveData<>();
        apiInterface.getAttendance(token,requestModel).enqueue(new Callback<AttendanceResponseModel>() {
            @Override
            public void onResponse(Call<AttendanceResponseModel> call, Response<AttendanceResponseModel> response) {
                if(response.code()==500){
                    Log.d(TAG, "onResponse: "+null);
                    attendanceResponseModelMutableLiveData.setValue(null);
                }else{
                    attendanceResponseModelMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AttendanceResponseModel> call, Throwable t) {
                attendanceResponseModelMutableLiveData.setValue(null);
            }
        });
        return attendanceResponseModelMutableLiveData;
    }
}
