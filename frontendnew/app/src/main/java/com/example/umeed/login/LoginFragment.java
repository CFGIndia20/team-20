package com.example.umeed.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.umeed.R;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class LoginFragment extends Fragment {
    private EditText etMobileNumber;
    private CircularProgressButton btnProceed;
    private CircularProgressButton btnProceedSignup;
//    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        etMobileNumber = root.findViewById(R.id.etMobileNumber);
        btnProceed = root.findViewById(R.id.btnProceed);
        btnProceedSignup=root.findViewById(R.id.btnSignUp);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnProceedSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment());
            }
        });
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(LoginFragmentDirections.actionLoginFragmentToDashBoardFragment());
            }
        });
    }
}

