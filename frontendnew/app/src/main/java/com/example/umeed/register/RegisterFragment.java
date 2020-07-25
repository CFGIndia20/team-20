package com.example.umeed.register;

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
import androidx.navigation.Navigation;

import com.example.umeed.R;
import com.example.umeed.data.model.response.RegisterResponseModel;
import com.example.umeed.login.LoginFragmentDirections;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class RegisterFragment extends Fragment {
    private EditText etMobileNumberRegister;
    private EditText etPasswordRegister;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etSkills;
    private EditText etArea;
    private CircularProgressButton btnRegister;
    private RegisterViewModel registerViewModel;
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
        etFirstName=root.findViewById(R.id.etNameRegister);
        etLastName=root.findViewById(R.id.etLastNameRegister);
        btnRegister = root.findViewById(R.id.btnProceedSignup);
        etArea=root.findViewById(R.id.etArea);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registerViewModel=new RegisterViewModel();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mobile = etMobileNumberRegister.getText().toString();
                final String firstname=etFirstName.getText().toString();
                final String lastname=etFirstName.getText().toString();
                final String area=etArea.getText().toString();
                final String skills=etSkills.getText().toString();
                final String password=etPasswordRegister.getText().toString();
                if(mobile.length()<10){
                    Toast.makeText(RegisterFragment.this.getActivity(), "Enter a valid mobile number", Toast.LENGTH_SHORT).show();
                }else if(firstname.equals(null)){
                    Toast.makeText(RegisterFragment.this.getActivity(),"Name cannot be empty",Toast.LENGTH_SHORT).show();
                }else if(lastname.equals(null)){
                    Toast.makeText(RegisterFragment.this.getActivity(),"Name cannot be empty",Toast.LENGTH_SHORT).show();
                }else if(password.length()<6) {
                    Toast.makeText(RegisterFragment.this.getActivity(), "Password cannot be less than 6 characters", Toast.LENGTH_SHORT).show();
                }else if(skills.equals(null)){
                    Toast.makeText(RegisterFragment.this.getActivity(),"Skills cannot be empty",Toast.LENGTH_SHORT).show();
                }else if(area.equals(null)){
                    Toast.makeText(RegisterFragment.this.getActivity(),"Area cannot be empty",Toast.LENGTH_SHORT).show();
                }else{
                    registerViewModel.signUp(firstname,firstname,mobile,area,skills,password).observe(getViewLifecycleOwner(), new Observer<RegisterResponseModel>() {
                        @Override
                        public void onChanged(RegisterResponseModel registerResponseModel) {
                            if(registerResponseModel!=null){
                                if(registerResponseModel.getStatus().equals("failure")){
                                    Toast.makeText(RegisterFragment.this.getActivity(),"Mobile number already exists",Toast.LENGTH_SHORT).show();
                                }else if(registerResponseModel.getStatus().equals("success")){
                                    Navigation.findNavController(view).navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment());
                                }
                            }

                        }
                    });
                }
            }
        });
    }
}
