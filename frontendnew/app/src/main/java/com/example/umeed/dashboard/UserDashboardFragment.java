package com.example.umeed.dashboard;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umeed.MainActivity;
import com.example.umeed.R;
import com.example.umeed.data.PrefManager;
import com.example.umeed.data.UmeedRepository;
import com.example.umeed.data.model.request.AttendanceRequestModel;
import com.example.umeed.data.model.response.AttendanceResponseModel;
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

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class UserDashboardFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener{

    String[] tasks = {"Item 1", "Item 2", "Item 3"};
    String[] progress = {"25/100", "35/100", "50/100"};
    ArrayList<ArrayList<Entry>> values = new ArrayList<ArrayList<Entry>>();
    private NavController navController;
    private DrawerLayout drawerLayout;
    private UmeedRepository umeedRepository;
    public NavigationView navigationDrawerView;
    private CircularProgressButton button;
    private CircularProgressButton experienceTestButton;
    private CircularProgressButton profileButton;
    private CircularProgressButton attendanceManagementButton;
    private CircularProgressButton meetingButton;
    private CircularProgressButton createMeetingButton;
    private AttendanceRequestModel attendanceRequestModel;
    private AttendanceViewModel attendanceViewModel;
    ListView dasboardListView;
    //    ArrayList<Entry> values = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user_dashboard, container, false);
        ListView dasboardListView = (ListView) root.findViewById(R.id.dashboardListView);
        values.add(new ArrayList<Entry>());
        values.add(new ArrayList<Entry>());
        values.add(new ArrayList<Entry>());
        values.get(0).add(new Entry(25f,0));
        values.get(0).add(new Entry(75f,1));
        button=root.findViewById(R.id.managerTest);
        meetingButton=root.findViewById(R.id.getMeetings);
        createMeetingButton=root.findViewById(R.id.createMeeting);
        values.get(1).add(new Entry(35f,0));
        values.get(1).add(new Entry(65f,1));
        profileButton=root.findViewById(R.id.profileDetails);
        attendanceManagementButton=root.findViewById(R.id.attendanceManagement);
        experienceTestButton=root.findViewById(R.id.experienceTest);
        values.get(2).add(new Entry(50f,0));
        values.get(2).add(new Entry(50f,1));
        CustomAdapter customAdapter = new CustomAdapter();
        dasboardListView.setAdapter(customAdapter);
        dasboardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Navigation.findNavController(view).navigate(UserDashboardFragmentDirections.actionDashBoardFragmentToTaskDetailsFragment());
            }
        });
        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drawerLayout = view.findViewById(R.id.drawer_layout);
        umeedRepository=new UmeedRepository();
        navigationDrawerView = view.findViewById(R.id.side_nav_view);
        experienceTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(UserDashboardFragmentDirections.actionDashBoardFragmentToExperienceDetailsFragment());
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(UserDashboardFragmentDirections.actionDashBoardFragmentToManagerFragment());
            }
        });

        attendanceManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(UserDashboardFragmentDirections.actionDashBoardFragmentToAttendanceManagement());
            }
        });
        meetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(UserDashboardFragmentDirections.actionDashBoardFragmentToGetMeeting());
            }
        });
        createMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(UserDashboardFragmentDirections.actionDashBoardFragmentToCreateMeeting());
            }
        });
        attendanceViewModel=new AttendanceViewModel();
//        set top level destinations to show drawer handle instead of back button
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileViewModel profileViewModel=new ProfileViewModel();
                Log.d(TAG, "onClick: "+PrefManager.getInstance().getAuthToken());
                profileViewModel.getInfo(PrefManager.getInstance().getAuthToken()).observe(getViewLifecycleOwner(), new Observer<ProfileDetailsResponseModel>() {
                    @Override
                    public void onChanged(ProfileDetailsResponseModel profileDetailsResponseModel) {
                        if(profileDetailsResponseModel!=null){
                           // UserDashboardFragmentDirections.ActionDashBoardFragmentToProfileFragment action2=UserDashboardFragmentDirections
                            attendanceViewModel.getAttendance("Token "+"805f829c139dd6359617714e7ed355db59465a13",profileDetailsResponseModel.getData().getMessage().getUserAcc().getUsername()).observe(getViewLifecycleOwner(), new Observer<AttendanceResponseModel>() {
                                @Override
                                public void onChanged(AttendanceResponseModel attendanceResponseModel) {
                                    if(attendanceResponseModel!=null){
                                        UserDashboardFragmentDirections.ActionDashBoardFragmentToProfileFragment action = UserDashboardFragmentDirections.actionDashBoardFragmentToProfileFragment(profileDetailsResponseModel.getData().getMessage().getUserAcc().getFirstName(),profileDetailsResponseModel.getData().getMessage().getArea(),profileDetailsResponseModel.getData().getMessage().getSkills(),profileDetailsResponseModel.getData().getMessage().getImage(),String.valueOf(attendanceResponseModel.getData().getMessage().getAttended()/attendanceResponseModel.getData().getMessage().getTotal()));
                                        Navigation.findNavController(view).navigate(action);
                                    }else{
                                        Toast.makeText(getContext(),"No details",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }else{
                            Toast.makeText(getContext(),"No details",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//                Navigation.findNavController(view).navigate(UserDashboardFragmentDirections.actionDashBoardFragmentToProfileFragment());
            }
        });

//        setup side nav and fragment view
//        side nav change listener
        navigationDrawerView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        navigationDrawerView.setCheckedItem(R.id.menu_dashboard);
//        show or hide toolbar based on destination
//        values.add(new Entry(25f,0));
//        values.add(new Entry(75f,1));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(false);

        drawerLayout.closeDrawers();
        int id = item.getItemId();
        Log.d("hi nav", "onNavigationItemSelected: " + item.getTitle());
        switch (id) {

            case R.id.menu_earn_money:
                Navigation.findNavController(requireActivity(),R.id.side_nav_view).navigate(UserDashboardFragmentDirections.actionDashBoardFragmentToTaskDetailsFragment());
//                Log.d(TAG, "onClick: " + PrefManager.getInstance().isUserAlreadyRegistered());
//                if (PrefManager.getInstance().isUserAlreadyRegistered()) {
////                    if (PrefManager.getInstance().getAddressId() != 0) {
//                    Log.d("CheckWorker", Boolean.toString(PrefManager.getInstance().isWorkerAlreadyRegistered()));
//                    if (PrefManager.getInstance().isWorkerAlreadyRegistered()) {
////                            AddNewSubWalletRequestModel addNewSubWalletRequestModel = new AddNewSubWalletRequestModel();
////                            addNewSubWalletRequestModel.setWorker_id(PrefManager.getInstance().getWorkerId());
////                            addNewSubWalletRequestModel.setSubcategory_id(1);
////
////                            AhelperRepository ahelperRepository = AhelperRepository.getInstance();
////                            ahelperRepository.addNewSubWallet(addNewSubWalletRequestModel).observe(getViewLifecycleOwner(), new Observer<AddNewSubWalletResponseModel>() {
////                                @Override
////                                public void onChanged(AddNewSubWalletResponseModel addNewSubWalletResponseModel) {
////                                    if (addNewSubWalletRequestModel != null) {
////                                        if (!addNewSubWalletResponseModel.getData().getMessage().equals("Worker not yet approved.")) {
////                                            sendWorkerLocation();
////
////                                        } else {
////                                            Navigation.findNavController(requireActivity(), R.id.nav_view).navigate(UserDashboardFragmentDirections.actionBottomNavigationFragmentToWaitApproval());
////                                        }
////                                    } else {
////                                        Toast.makeText(requireActivity(), "Error loading Worker Dashboard", Toast.LENGTH_SHORT).show();
////                                    }
////
////                                }
////                            });
//                        AhelperRepository ahelperRepository = AhelperRepository.getInstance();
//                        ahelperRepository.getWorkerStatus(PrefManager.getInstance().getWorkerId()).observe(getViewLifecycleOwner(), new Observer<WorkerApprovedStatusResponseModel>() {
//                            @Override
//                            public void onChanged(WorkerApprovedStatusResponseModel workerApprovedStatusResponseModel) {
//
//                                if (Integer.parseInt(workerApprovedStatusResponseModel.getData().getWorkerStatus()) == 0) {
//                                    Navigation.findNavController(requireActivity(), R.id.nav_view).navigate(UserDashboardFragmentDirections.actionBottomNavigationFragmentToWaitApproval());
//                                } else if (Integer.parseInt(workerApprovedStatusResponseModel.getData().getWorkerStatus()) == 1) {
//                                    Navigation.findNavController(requireActivity(), R.id.nav_view).navigate(UserDashboardFragmentDirections.actionBottomNavigationFragmentToVendorDashboardFragment());
//
//                                } else if (Integer.parseInt(workerApprovedStatusResponseModel.getData().getWorkerStatus()) == 2) {
//                                    Navigation.findNavController(getActivity(), R.id.nav_view).navigate(UserDashboardFragmentDirections.actionBottomNavigationFragmentToRegistration1());
//                                }
//
//
//                            }
//                        });
//
//
//                    } else {
//                        Navigation.findNavController(getActivity(), R.id.nav_view).navigate(UserDashboardFragmentDirections.actionBottomNavigationFragmentToRegistration1());
//                    }
//
//
//                } else {
//                    Navigation.findNavController(requireActivity(), R.id.nav_view).navigate(UserDashboardFragmentDirections.actionBottomNavigationFragmentToEditProfileFragment());
//                }
//
//                //  Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(UserDashboardFragmentDirections.actionBottomNavigationFragmentToVendorDashboardFragment());
//                break;
            case R.id.menu_dashboard:
                break;
            case R.id.menu_contact_us:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:09321627470"));
                startActivity(intent);
                break;
//
//            case R.id.third:
//                navController.navigate(R.id.thirdFragment);
//                break;

        }
//        navController.navigate(id);
        return true;
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return tasks.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.progress, null);
            TextView listText = (TextView) convertView.findViewById(R.id.listTextView);
            TextView listText2 = (TextView) convertView.findViewById(R.id.listTextView2);
            PieChart pieChart = (PieChart) convertView.findViewById(R.id.listPiechart);
            listText.setText(tasks[position]);
            listText2.setText(progress[position]);
            PieDataSet dataSet = new PieDataSet(values.get(position), null);

            ArrayList<String> xVals = new ArrayList<String>();

            xVals.add("");
            xVals.add("");
            PieData data = new PieData(xVals, dataSet);
//            data.setValueFormatter(new PercentFormatter());
            pieChart.setData(data);
//            pieChart.setDescription("This is Pie Chart");
            pieChart.setDrawHoleEnabled(true);
            pieChart.setTransparentCircleRadius(58f);

            pieChart.setHoleRadius(58f);
            dataSet.setColors(new int[]{Color.GREEN, Color.RED});//Color.GREEN, Color.RED)
            data.setValueTextSize(0f);
//            data.setValueTextColor(Color.DKGRAY);
            return convertView;
        }
    }
}
