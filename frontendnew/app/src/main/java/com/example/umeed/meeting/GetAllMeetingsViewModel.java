package com.example.umeed.meeting;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.umeed.data.UmeedRepository;
import com.example.umeed.data.model.response.GetAllMeetingsResponseModel;
import com.example.umeed.data.model.response.ManagerUserResponseModel;

public class GetAllMeetingsViewModel extends ViewModel {
    private LiveData<GetAllMeetingsResponseModel> subcategoryModelLiveData;
    private UmeedRepository umeedRepository;
    //      private ArrayList<ManagerUser> managerUsers;
    public GetAllMeetingsViewModel() {
        umeedRepository=new UmeedRepository();
    }

    public LiveData<GetAllMeetingsResponseModel> getSubcategoryModelLiveData(String token) {
        subcategoryModelLiveData = umeedRepository.getMeetingsUser("Token "+token);
        return subcategoryModelLiveData;
    }
}
