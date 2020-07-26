package com.example.umeed.meeting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.umeed.MainActivity;
import com.example.umeed.R;
import com.example.umeed.data.PrefManager;
import com.example.umeed.data.model.response.CreateMeetingResponseModel;
import com.example.umeed.data.model.response.ProfileDetailsResponseModel;
import com.example.umeed.profile.ProfileViewModel;
import com.github.mikephil.charting.charts.PieChart;
 import com.github.mikephil.charting.data.Entry;
 import com.github.mikephil.charting.data.PieData;
 import com.github.mikephil.charting.data.PieDataSet;
 import com.github.mikephil.charting.formatter.PercentFormatter;
 import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Calendar;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class ManagerDateTimeFragment extends Fragment {

    private NavController navController;
    private DrawerLayout drawerLayout;
    public NavigationView navigationDrawerView;
    private CircularProgressButton pickDate;
    private CircularProgressButton pickTime;
    private CircularProgressButton submitDateTime;
    private TextView showDate, showTime;
    private String date,time;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_manager_datetime, container, false);

        pickDate = root.findViewById(R.id.pickDate);
        pickTime = root.findViewById(R.id.pickTime);
        submitDateTime = root.findViewById(R.id.submitMeetingBtn);
        showDate = root.findViewById(R.id.showDate);
        showTime = root.findViewById(R.id.showTime);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationDrawerView = view.findViewById(R.id.side_nav_view);

        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);

                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                DatePickerDialog datepicker = new DatePickerDialog(requireActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                if(monthOfYear<9){
                                    date=year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth;
                                    showDate.setText(year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);
                                }else{
                                    date=year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                    showDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                }

                            }
                        }, year, month, day);
                datepicker.show();
            }
        });
        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog timePickerDialog = new TimePickerDialog(requireActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        showTime.setText(hourOfDay + ":" + minute);
                        time=hourOfDay + ":" + minute;
                    }
                }, now.HOUR_OF_DAY, now.MINUTE, false);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });
        CreateMeetingViewModel createMeetingViewModel=new CreateMeetingViewModel();
        submitDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createMeetingViewModel.signUp("Token "+"805f829c139dd6359617714e7ed355db59465a13",date,time).observe(getViewLifecycleOwner(), new Observer<CreateMeetingResponseModel>() {
                    @Override
                    public void onChanged(CreateMeetingResponseModel createMeetingResponseModel) {
                        if(!createMeetingResponseModel.equals(null)){
                            Toast.makeText(getContext(),createMeetingResponseModel.getData().getMessage(),Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(view).popBackStack();
                        }
                    }
                });
            }
        });


//        set top level destinations to show drawer handle instead of back button
//        profileButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ProfileViewModel profileViewModel=new ProfileViewModel();
//                Log.d(TAG, "onClick: "+PrefManager.getInstance().getAuthToken());
//                profileViewModel.getInfo(PrefManager.getInstance().getAuthToken()).observe(getViewLifecycleOwner(), new Observer<ProfileDetailsResponseModel>() {
//                    @Override
//                    public void onChanged(ProfileDetailsResponseModel profileDetailsResponseModel) {
//                        if(profileDetailsResponseModel!=null){
//                           // UserDashboardFragmentDirections.ActionDashBoardFragmentToProfileFragment action2=UserDashboardFragmentDirections
//                            UserDashboardFragmentDirections.ActionDashBoardFragmentToProfileFragment action = UserDashboardFragmentDirections.actionDashBoardFragmentToProfileFragment(profileDetailsResponseModel.getData().getMessage().getUserAcc().getFirstName(),profileDetailsResponseModel.getData().getMessage().getArea(),profileDetailsResponseModel.getData().getMessage().getSkills(),profileDetailsResponseModel.getData().getMessage().getImage(),"100");
//                            Navigation.findNavController(view).navigate(action);
//                        }else{
//                            Toast.makeText(getContext(),"No details",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
////                Navigation.findNavController(view).navigate(UserDashboardFragmentDirections.actionDashBoardFragmentToProfileFragment());
//            }
//        });

//        setup side nav and fragment view
//        side nav change listener
//        navigationDrawerView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
//        navigationDrawerView.setCheckedItem(R.id.menu_dashboard);
////        show or hide toolbar based on destination
////        values.add(new Entry(25f,0));
////        values.add(new Entry(75f,1));
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        item.setChecked(false);
//
//        drawerLayout.closeDrawers();
//        int id = item.getItemId();
//        Log.d("hi nav", "onNavigationItemSelected: " + item.getTitle());
//        switch (id) {
//
//            case R.id.menu_earn_money:
//                Navigation.findNavController(requireActivity(),R.id.side_nav_view).navigate(ManagerDateTimeDirections.actionDashBoardFragmentToTaskDetailsFragment());
////                Log.d(TAG, "onClick: " + PrefManager.getInstance().isUserAlreadyRegistered());
////                if (PrefManager.getInstance().isUserAlreadyRegistered()) {
//////                    if (PrefManager.getInstance().getAddressId() != 0) {
////                    Log.d("CheckWorker", Boolean.toString(PrefManager.getInstance().isWorkerAlreadyRegistered()));
////                    if (PrefManager.getInstance().isWorkerAlreadyRegistered()) {
//////                            AddNewSubWalletRequestModel addNewSubWalletRequestModel = new AddNewSubWalletRequestModel();
//////                            addNewSubWalletRequestModel.setWorker_id(PrefManager.getInstance().getWorkerId());
//////                            addNewSubWalletRequestModel.setSubcategory_id(1);
//////
//////                            AhelperRepository ahelperRepository = AhelperRepository.getInstance();
//////                            ahelperRepository.addNewSubWallet(addNewSubWalletRequestModel).observe(getViewLifecycleOwner(), new Observer<AddNewSubWalletResponseModel>() {
//////                                @Override
//////                                public void onChanged(AddNewSubWalletResponseModel addNewSubWalletResponseModel) {
//////                                    if (addNewSubWalletRequestModel != null) {
//////                                        if (!addNewSubWalletResponseModel.getData().getMessage().equals("Worker not yet approved.")) {
//////                                            sendWorkerLocation();
//////
//////                                        } else {
//////                                            Navigation.findNavController(requireActivity(), R.id.nav_view).navigate(UserDashboardFragmentDirections.actionBottomNavigationFragmentToWaitApproval());
//////                                        }
//////                                    } else {
//////                                        Toast.makeText(requireActivity(), "Error loading Worker Dashboard", Toast.LENGTH_SHORT).show();
//////                                    }
//////
//////                                }
//////                            });
////                        AhelperRepository ahelperRepository = AhelperRepository.getInstance();
////                        ahelperRepository.getWorkerStatus(PrefManager.getInstance().getWorkerId()).observe(getViewLifecycleOwner(), new Observer<WorkerApprovedStatusResponseModel>() {
////                            @Override
////                            public void onChanged(WorkerApprovedStatusResponseModel workerApprovedStatusResponseModel) {
////
////                                if (Integer.parseInt(workerApprovedStatusResponseModel.getData().getWorkerStatus()) == 0) {
////                                    Navigation.findNavController(requireActivity(), R.id.nav_view).navigate(UserDashboardFragmentDirections.actionBottomNavigationFragmentToWaitApproval());
////                                } else if (Integer.parseInt(workerApprovedStatusResponseModel.getData().getWorkerStatus()) == 1) {
////                                    Navigation.findNavController(requireActivity(), R.id.nav_view).navigate(UserDashboardFragmentDirections.actionBottomNavigationFragmentToVendorDashboardFragment());
////
////                                } else if (Integer.parseInt(workerApprovedStatusResponseModel.getData().getWorkerStatus()) == 2) {
////                                    Navigation.findNavController(getActivity(), R.id.nav_view).navigate(UserDashboardFragmentDirections.actionBottomNavigationFragmentToRegistration1());
////                                }
////
////
////                            }
////                        });
////
////
////                    } else {
////                        Navigation.findNavController(getActivity(), R.id.nav_view).navigate(UserDashboardFragmentDirections.actionBottomNavigationFragmentToRegistration1());
////                    }
////
////
////                } else {
////                    Navigation.findNavController(requireActivity(), R.id.nav_view).navigate(UserDashboardFragmentDirections.actionBottomNavigationFragmentToEditProfileFragment());
////                }
////
////                //  Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(UserDashboardFragmentDirections.actionBottomNavigationFragmentToVendorDashboardFragment());
////                break;
//            case R.id.menu_dashboard:
//                break;
//            case R.id.menu_contact_us:
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:09321627470"));
//                startActivity(intent);
//                break;
////
////            case R.id.third:
////                navController.navigate(R.id.thirdFragment);
////                break;
//
//        }
////        navController.navigate(id);
//        return true;
//    }
//}
    }
}
