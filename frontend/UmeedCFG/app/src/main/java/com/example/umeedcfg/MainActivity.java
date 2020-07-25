package com.example.umeedcfg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Fragment navHostFragment = getSupportFragmentManager().getPrimaryNavigationFragment();
        Fragment fragment = navHostFragment.getChildFragmentManager().getFragments().get(0);
        fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

