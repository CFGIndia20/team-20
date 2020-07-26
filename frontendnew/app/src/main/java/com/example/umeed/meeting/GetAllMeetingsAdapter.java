package com.example.umeed.meeting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.umeed.R;
import com.example.umeed.data.model.response.GetAllMeetingsResponseModel;
import com.example.umeed.data.model.response.ManagerUserResponseModel;
import com.example.umeed.manager_user.ManagerUserAdapter;

import java.util.ArrayList;

public class GetAllMeetingsAdapter extends RecyclerView.Adapter<GetAllMeetingsAdapter.GetAllMeetingsViewHolder> {
    private Context context;
    private ArrayList<GetAllMeetingsResponseModel.Data.Message> subcategoryModels;

    public GetAllMeetingsAdapter(Context context, ArrayList<GetAllMeetingsResponseModel.Data.Message> subcategoryModels) {
        this.context = context;
        this.subcategoryModels = subcategoryModels;
    }

    @NonNull
    @Override
    public GetAllMeetingsAdapter.GetAllMeetingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_meeting, parent, false);
        return new GetAllMeetingsAdapter.GetAllMeetingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetAllMeetingsAdapter.GetAllMeetingsViewHolder holder, int position) {
        holder.tvCategoryTitle.setText("ID"+subcategoryModels.get(position).getId());
        holder.tvTotal.setText("People: "+subcategoryModels.get(position).getAttendedUsers().size());
        holder.tvDate.setText(subcategoryModels.get(position).getDateTime().substring(0,subcategoryModels.get(position).getDateTime().indexOf('T')));
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

    public static class GetAllMeetingsViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCategoryTitle;
        private CardView cardViewSubCategory;
        private ImageView subcatImage;
        private TextView tvDate;
        private TextView tvTotal;

        public GetAllMeetingsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategoryTitle = itemView.findViewById(R.id.tvMeetingId);
            subcatImage = itemView.findViewById(R.id.ivService);
            tvTotal=itemView.findViewById(R.id.tvMeetingAttendanceTotal);
            tvDate=itemView.findViewById(R.id.tvMeetingDate);
        }
    }
}
