package com.example.umeed.splash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.umeed.MainActivity;
import com.example.umeed.R;
import com.example.umeed.data.PrefManager;
import com.example.umeed.splash.SplashViewModel;

public class SplashFragment extends Fragment {

    private SplashViewModel splashViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        splashViewModel = ViewModelProviders.of(requireActivity()).get(SplashViewModel.class);
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PrefManager.initInstance(getContext());
        splashViewModel.startTimer();
        splashViewModel.isTimerDone.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    if (PrefManager.getInstance().isLoggedIn()) {
//                        if (PrefManager.getInstance().isLocationScreenDone()) {
//                            Navigation.findNavController(view).navigate(SplashFragmentDirections.actionSplashFragmentToBottomNavigationFragment(null));
//                        } else {
                        Navigation.findNavController(view).navigate(SplashFragmentDirections.actionSplashFragmentToDashBoardFragment());

//                        }
                    } else {
                        Navigation.findNavController(view).navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment());
                    }
                }
            }
        });
    }
}