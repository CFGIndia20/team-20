package com.example.umeed.attendace;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umeed.R;

import java.util.ArrayList;

public class ManagerAttendanceFragment extends Fragment {
    private RecyclerView rvSubcategory;
    private ArrayList<AttedanceModel> attedanceModel;
    private ManagerAttendanceAdapter managerAttendanceAdapter;
    int[] images = {R.drawable.umeed_logo,R.drawable.umeed_logo,R.drawable.umeed_logo,R.drawable.umeed_logo};
    String[] names = {"abc","def","ghi","jkl"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_manager_attendance, container, false);
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvSubcategory = view.findViewById(R.id.attendanceListView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSubcategory.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                rvSubcategory.getContext(),
                linearLayoutManager.getOrientation()
        );
        attedanceModel=new ArrayList<>();
        rvSubcategory.addItemDecoration(dividerItemDecoration);
        attedanceModel.add(new AttedanceModel("Romit", R.drawable.umeed_logo));
        attedanceModel.add(new AttedanceModel("Romit", R.drawable.umeed_logo));
        attedanceModel.add(new AttedanceModel("Romit", R.drawable.umeed_logo));
        attedanceModel.add(new AttedanceModel("Romit", R.drawable.umeed_logo));
        attedanceModel.add(new AttedanceModel("Romit", R.drawable.umeed_logo));
         managerAttendanceAdapter = new ManagerAttendanceAdapter(getActivity(), attedanceModel);
        rvSubcategory.setAdapter(managerAttendanceAdapter);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Fragment navHostFragment = getChildFragmentManager().getPrimaryNavigationFragment();
        Fragment fragment = navHostFragment.getChildFragmentManager().getFragments().get(0);
        fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
