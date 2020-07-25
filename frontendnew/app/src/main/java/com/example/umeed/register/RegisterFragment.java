package com.example.umeed.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.umeed.R;
import com.example.umeed.login.LoginFragmentDirections;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class RegisterFragment extends Fragment {
    private EditText etMobileNumberRegister;
    private EditText etPasswordRegister;
    private EditText etSkills;
    private CircularProgressButton btnRegister;
//    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);
        etMobileNumberRegister = root.findViewById(R.id.etMobileNumberRegister);
        etPasswordRegister=root.findViewById(R.id.etPasswordRegister);
        etSkills=root.findViewById(R.id.etSkills);
        btnRegister = root.findViewById(R.id.btnProceedSignup);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
