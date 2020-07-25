package com.example.umeed.splash;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.umeed.data.PrefManager;

/**
 * User: Aman
 * Date: 14-12-2019
 * Time: 04:45 PM
 */
public class SplashViewModel extends ViewModel {

    MutableLiveData<Boolean> isTimerDone;

    public SplashViewModel() {
        isTimerDone = new MutableLiveData<>();
        isTimerDone.setValue(false);
//        PrefManager.getInstance().setLoggedIn(false);
    }

    public void startTimer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isTimerDone.setValue(true);
            }
        }, 500);
    }

}
