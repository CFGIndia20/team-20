package com.example.umeed;

import android.app.Application;

import com.example.umeed.data.PrefManager;


/**
 * User: Aman
 * Date: 14-12-2019
 * Time: 04:06 PM
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        PrefManager.initInstance(this);
    }
}
