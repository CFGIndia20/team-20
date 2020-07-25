package com.example.umeed.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.umeed.R;
import com.example.umeed.data.PrefManager;
import com.example.umeed.data.model.response.LoginResponseModel;
import com.example.umeed.data.model.response.RegisterResponseModel;
import com.example.umeed.register.RegisterFragment;
import com.example.umeed.register.RegisterFragmentDirections;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class LoginFragment extends Fragment {
    private EditText etMobileNumber;
    private EditText etPassword;
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
        etPassword = root.findViewById(R.id.etPassword);
        btnProceed = root.findViewById(R.id.btnProceed);
        btnProceedSignup = root.findViewById(R.id.btnSignUp);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginViewModel loginViewModel = new LoginViewModel();
        btnProceedSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment());
            }
        });
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mobile = etMobileNumber.getText().toString();
                final String password = etPassword.getText().toString();
                if (mobile.length() < 10) {
                    Toast.makeText(LoginFragment.this.getActivity(), "Enter a valid mobile number", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(LoginFragment.this.getActivity(), "Password cannot be less than 6 characters", Toast.LENGTH_SHORT).show();
                } else {
                    loginViewModel.login(mobile, password).observe(getViewLifecycleOwner(), new Observer<LoginResponseModel>() {
                        @Override
                        public void onChanged(LoginResponseModel loginResponseModel) {
                            if (loginResponseModel != null) {
                                if (loginResponseModel.getStatus().equals("failure")) {
                                    Toast.makeText(getContext(), loginResponseModel.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Navigation.findNavController(view).navigate(LoginFragmentDirections.actionLoginFragmentToDashBoardFragment());
                                    PrefManager.getInstance().setAuthToken(loginResponseModel.getData().getMessage());
                                    PrefManager.getInstance().setLoggedIn(true);
                                    Toast.makeText(getContext(), loginResponseModel.getData().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                    });
                }
            }
        });
    }
}

