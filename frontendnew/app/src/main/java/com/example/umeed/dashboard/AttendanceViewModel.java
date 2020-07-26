package com.example.umeed.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.umeed.data.UmeedRepository;
import com.example.umeed.data.model.request.AttendanceRequestModel;
import com.example.umeed.data.model.response.AttendanceResponseModel;
import com.example.umeed.data.model.response.ManagerUserResponseModel;

public class AttendanceViewModel extends ViewModel {
    private LiveData<AttendanceResponseModel> subcategoryModelLiveData;
    private UmeedRepository umeedRepository;
    AttendanceRequestModel attendanceRequestModel=new AttendanceRequestModel();
    //      private ArrayList<ManagerUser> managerUsers;
    public AttendanceViewModel() {
        umeedRepository=new UmeedRepository();
    }

    public LiveData<AttendanceResponseModel> getAttendance(String token,String mobile) {
        attendanceRequestModel.setMobileNumber(mobile);
        subcategoryModelLiveData = umeedRepository.getAtt(token,attendanceRequestModel);
        return subcategoryModelLiveData;
    }
}
