package com.example.umeedcfg.ui.splash;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SplashViewModel extends ViewModel {

    boolean isTimerDone;

    public SplashViewModel() {
        isTimerDone = false;
    }

    public void startTimer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isTimerDone=false;
            }
        }, 500);
    }

}