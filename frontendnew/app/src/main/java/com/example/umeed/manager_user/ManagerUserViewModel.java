package com.example.umeed.manager_user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.umeed.data.UmeedRepository;
import com.example.umeed.data.model.response.ManagerUserResponseModel;

import java.util.ArrayList;

public class ManagerUserViewModel extends ViewModel {
    private LiveData<ManagerUserResponseModel> subcategoryModelLiveData;
    private UmeedRepository umeedRepository;
//      private ArrayList<ManagerUser> managerUsers;
    public ManagerUserViewModel() {
       umeedRepository=new UmeedRepository();
    }

    public LiveData<ManagerUserResponseModel> getSubcategoryModelLiveData(String token) {
        subcategoryModelLiveData = umeedRepository.getUserData("Token "+token);
        return subcategoryModelLiveData;
    }

}

