package com.example.umeed.data.network;

import com.example.umeed.data.model.request.AttendanceRequestModel;
import com.example.umeed.data.model.request.CreateMeetingRequestModel;
import com.example.umeed.data.model.request.LoginRequestModel;
import com.example.umeed.data.model.request.RegisterRequestModel;
import com.example.umeed.data.model.response.AttendanceResponseModel;
import com.example.umeed.data.model.response.CreateMeetingResponseModel;
import com.example.umeed.data.model.response.GetAllMeetingsResponseModel;
import com.example.umeed.data.model.response.LoginResponseModel;
import com.example.umeed.data.model.response.ManagerUserResponseModel;
import com.example.umeed.data.model.response.ProfileDetailsResponseModel;
import com.example.umeed.data.model.response.RegisterResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
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

    @POST(value = "auth/get_user_info")
    Call<ProfileDetailsResponseModel> getInfo(@Header("Authorization") String token);

    @POST(value = "auth/fetch_users")
    Call<ManagerUserResponseModel> getUsers(@Header("Authorization") String token);

    @POST(value = "ops/get_meetings")
    Call<GetAllMeetingsResponseModel> getMeetings(@Header("Authorization") String token);

    @POST(value="ops/create_meeting")
    Call<CreateMeetingResponseModel> create(@Header("Authorization") String token, @Body CreateMeetingRequestModel body);

    @POST(value = "ops/view_attendance")
    Call<AttendanceResponseModel> getAttendance(@Header("Authorization") String token, @Body AttendanceRequestModel body);

}
