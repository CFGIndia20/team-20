package com.example.umeed.data.network;

import com.example.umeed.data.model.request.LoginRequestModel;
import com.example.umeed.data.model.request.RegisterRequestModel;
import com.example.umeed.data.model.response.LoginResponseModel;
import com.example.umeed.data.model.response.RegisterResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {
    @Headers({
            "Accept: application/json",
            "Content-type: application/json"
    })
    @POST(value = "auth/user_register")
    Call<RegisterResponseModel> mobileRegister(@Body RegisterRequestModel body);

    @POST(value = "auth/user_login")
    Call<LoginResponseModel> mobileLogin(@Body LoginRequestModel body);
}
