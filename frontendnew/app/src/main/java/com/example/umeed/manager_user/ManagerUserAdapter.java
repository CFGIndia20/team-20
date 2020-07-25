package com.example.umeed.manager_user;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.umeed.R;
import com.example.umeed.manager_user.ManagerUserViewModel;

import java.util.ArrayList;

import retrofit2.HttpException;

public class ManagerUserAdapter extends RecyclerView.Adapter<ManagerUserAdapter.ManagerViewHolder> {
    private Context context;
    private ArrayList<ManagerUserModel> subcategoryModels;

    public ManagerUserAdapter(Context context, ArrayList<ManagerUserModel> subcategoryModels) {
        this.context = context;
        this.subcategoryModels = subcategoryModels;
    }

    @NonNull
    @Override
    public ManagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.manager_user, parent, false);
        return new ManagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerViewHolder holder, int position) {
        holder.tvCategoryTitle.setText("Romit");
        Glide.with(context).load(R.drawable.umeed_logo).into(holder.subcatImage);
//        holder.cardViewSubCategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ahelperRepository = AhelperRepository.getInstance();
//                LiveData<GetTimeSlotResponseModel> timeSlotResponseModelLiveData = ahelperRepository.getTimeSlot(subcategoryModels.get(position).getSub_categoryId());
//                try {
//                    timeSlotResponseModelLiveData.observe((LifecycleOwner) holder.itemView.getContext(), new Observer<GetTimeSlotResponseModel>() {
//                        @Override
//                        public void onChanged(GetTimeSlotResponseModel getTimeSlotResponseModel) {
//                            if (getTimeSlotResponseModel == null && mainCategoryId != 3) {
//                                Toast.makeText(context, "No timeslots currently available", Toast.LENGTH_SHORT).show();
//                            } else {
//                                SubcategoryModel.Datum subcategoryModel = subcategoryModels.get(position);
//                                SubcategoryFragmentDirections.ActionSubcategoryFragmentToServiceFragment action = SubcategoryFragmentDirections
//                                        .actionSubcategoryFragmentToServiceFragment(
//                                                subcategoryModel.getSub_categoryId(),
//                                                subcategoryModel.getSub_categoryName(),
//                                                subcategoryModel.getSub_categoryDescription(),
//                                                mainCategoryId
//                                        );
//                                Navigation.findNavController((MainActivity) context, R.id.nav_host_fragment).navigate(action);
//                                //Navigation.findNavController((MainActivity) context, R.id.nav_host_fragment).navigate(R.id.action_subcategoryFragment_to_serviceFragment);
////                                PrefManager.getInstance().getPreferences().edit().putInt("subcategory_id", subcategoryModels.get(position).getSub_categoryId()).commit();
//
//                            }
//                        }
//                    });
//                } catch (HttpException e) {
//                    //  Toast.makeText(context, "No timeslots currently available", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return subcategoryModels.size();
    }

    public static class ManagerViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCategoryTitle;
        private CardView cardViewSubCategory;
        private ImageView subcatImage;


        public ManagerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategoryTitle = itemView.findViewById(R.id.tvCategoryTitle);
            subcatImage = itemView.findViewById(R.id.ivService);
        }
    }
}
