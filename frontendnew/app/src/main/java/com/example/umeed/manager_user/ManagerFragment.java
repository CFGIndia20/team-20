package com.example.umeed.manager_user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umeed.R;

import java.util.ArrayList;

public class ManagerFragment extends Fragment {
    int addressId;
    private RecyclerView rvSubcategory;
    private ManagerUserViewModel subcategoryViewModel;
    private ArrayList<ManagerUserModel> subcategoryModels;
    private ManagerUserAdapter subcategoryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        subcategoryViewModel = ViewModelProviders.of(requireActivity()).get(SubcategoryViewModel.class);
        return inflater.inflate(R.layout.fragment_manager_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvSubcategory = view.findViewById(R.id.rvSubcategory);
//        ibBack = view.findViewById(R.id.ibBack);
//        tvMainCategoryTitle = view.findViewById(R.id.tvMainCategoryTitle);
//        progressBar = view.findViewById(R.id.progressBar);
//        SubcategoryFragmentArgs subcategoryFragmentArgs = SubcategoryFragmentArgs.fromBundle(getArguments());
//        tvMainCategoryTitle.setText(subcategoryFragmentArgs.getMainCatTitle());
//        mainCategoryId = subcategoryFragmentArgs.getMainCategoryId();
//        id = subcategoryFragmentArgs.getId();
//        Log.d("Ahelper", String.valueOf(id));
        subcategoryViewModel = ViewModelProviders.of(requireActivity()).get(ManagerUserViewModel.class);
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
        subcategoryModels.add(new ManagerUserModel("Romit", R.drawable.umeed_logo));
        subcategoryAdapter = new ManagerUserAdapter(getActivity(), subcategoryModels);
        rvSubcategory.setAdapter(subcategoryAdapter);
//        progressBar.setVisibility(View.VISIBLE);

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
