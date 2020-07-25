package com.example.umeedcfg.ui.dashboard;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.example.umeedcfg.R;
import com.example.umeedcfg.ui.login.LoginFragmentDirections;
import com.google.android.material.navigation.NavigationView;

public class UserDashboardFragment extends Fragment {
    private DrawerLayout drawerLayout;
    public NavigationView navigationDrawerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_user_dashboard, container, false);
        drawerLayout=root.findViewById(R.id.drawer_layout);
        navigationDrawerView=root.findViewById(R.id.side_nav_view);
        return root;

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Fragment navHostFragment = getChildFragmentManager().getPrimaryNavigationFragment();
        Fragment fragment = navHostFragment.getChildFragmentManager().getFragments().get(0);
        fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

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
////
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
//
//                //  Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(UserDashboardFragmentDirections.actionBottomNavigationFragmentToVendorDashboardFragment());
//                break;
//            case R.id.menu_dashboard:
//                break;
//            case R.id.menu_contact_us:
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:09082005942"));
//                startActivity(intent);
//                break;
////            case R.id.menu_logout:
////                logout();
////                break;
//
////
////            case R.id.third:
////                navController.navigate(R.id.thirdFragment);
////                break;
//
//        }
////        navController.navigate(id);
//        return true;
//    }
}
