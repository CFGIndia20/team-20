package com.example.umeed.meeting;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.umeed.data.UmeedRepository;
import com.example.umeed.data.model.request.CreateMeetingRequestModel;
import com.example.umeed.data.model.request.RegisterRequestModel;
import com.example.umeed.data.model.response.CreateMeetingResponseModel;
import com.example.umeed.data.model.response.RegisterResponseModel;
import com.example.umeed.data.network.RetrofitService;

public class CreateMeetingViewModel extends ViewModel {
    private UmeedRepository umeedRepository;

    public CreateMeetingViewModel() {
        umeedRepository = UmeedRepository.getInstance();
    }

    public LiveData<CreateMeetingResponseModel> signUp(String token,String date,String time) {
        CreateMeetingRequestModel createMeetingRequestModel=new CreateMeetingRequestModel();
        createMeetingRequestModel.setDate(date);
        createMeetingRequestModel.setTime(time);
        return umeedRepository.createMeeting(token,createMeetingRequestModel);
    }
}
