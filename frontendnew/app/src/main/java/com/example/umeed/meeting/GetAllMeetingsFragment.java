package com.example.umeed.meeting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umeed.R;
import com.example.umeed.data.model.response.GetAllMeetingsResponseModel;
import com.example.umeed.data.model.response.ManagerUserResponseModel;
import com.example.umeed.manager_user.ManagerFragment;
import com.example.umeed.manager_user.ManagerUserAdapter;
import com.example.umeed.manager_user.ManagerUserViewModel;

import java.util.ArrayList;

public class GetAllMeetingsFragment extends Fragment {
    private RecyclerView rvSubcategory;
    private GetAllMeetingsViewModel subcategoryViewModel;
    private ArrayList<GetAllMeetingsResponseModel.Data.Message> subcategoryModels;
    private GetAllMeetingsAdapter subcategoryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        subcategoryViewModel = ViewModelProviders.of(requireActivity()).get(SubcategoryViewModel.class);
        return inflater.inflate(R.layout.fragment_meeting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvSubcategory = view.findViewById(R.id.rvMeeting);
//        ibBack = view.findViewById(R.id.ibBack);
//        tvMainCategoryTitle = view.findViewById(R.id.tvMainCategoryTitle);
//        progressBar = view.findViewById(R.id.progressBar);
//        SubcategoryFragmentArgs subcategoryFragmentArgs = SubcategoryFragmentArgs.fromBundle(getArguments());
//        tvMainCategoryTitle.setText(subcategoryFragmentArgs.getMainCatTitle());
//        mainCategoryId = subcategoryFragmentArgs.getMainCategoryId();
//        id = subcategoryFragmentArgs.getId();
//        Log.d("Ahelper", String.valueOf(id));
        subcategoryViewModel = ViewModelProviders.of(requireActivity()).get(GetAllMeetingsViewModel.class);
        rvSubcategory.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSubcategory.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                rvSubcategory.getContext(),
                linearLayoutManager.getOrientation()
        );
        rvSubcategory.addItemDecoration(dividerItemDecoration);

        subcategoryModels = new ArrayList<>();
        subcategoryAdapter = new GetAllMeetingsAdapter(getActivity(),subcategoryModels);
        rvSubcategory.setAdapter(subcategoryAdapter);
//        progressBar.setVisibility(View.VISIBLE);
        subcategoryViewModel.getSubcategoryModelLiveData("805f829c139dd6359617714e7ed355db59465a13").observe(getViewLifecycleOwner(), new Observer<GetAllMeetingsResponseModel>() {
            @Override
            public void onChanged(GetAllMeetingsResponseModel getAllMeetingsResponseModel) {

                GetAllMeetingsFragment.this.subcategoryModels.clear();
                GetAllMeetingsFragment.this.subcategoryModels.addAll(getAllMeetingsResponseModel.getData().getMessage());
                subcategoryAdapter.notifyDataSetChanged();
            }
        });
//        subcategoryViewModel.getSubcategoryModelLiveData(mainCategoryId).observe(getViewLifecycleOwner(), new Observer<SubcategoryModel>() {
//            @Override
//            public void onChanged(SubcategoryModel subcategoryModel) {
//                progressBar.setVisibility(View.GONE);
//                SubcategoryFragment.this.subcategoryModels.clear();
//                SubcategoryFragment.this.subcategoryModels.addAll(subcategoryModel.getData());
//                subcategoryAdapter.notifyDataSetChanged();
//            }
//        });


    }
}
