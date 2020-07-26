package com.example.umeed.attendace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.umeed.R;

import java.util.ArrayList;

public class ManagerAttendanceAdapter extends RecyclerView.Adapter<ManagerAttendanceAdapter.ManagerAttendanceViewHolder> {
    private Context context;
    private ArrayList<AttedanceModel> subcategoryModels;

    public ManagerAttendanceAdapter(Context context, ArrayList<AttedanceModel> subcategoryModels) {
        this.context = context;
        this.subcategoryModels = subcategoryModels;
    }

    @NonNull
    @Override
    public ManagerAttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.attendance_list, parent, false);
        return new ManagerAttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerAttendanceViewHolder holder, int position) {
        holder.tvCategoryTitle.setText("Romit");
        Glide.with(context).load(R.drawable.umeed_logo);
    }

    @Override
    public int getItemCount() {
        return subcategoryModels.size();
    }

    public static class ManagerAttendanceViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCategoryTitle;
        private CheckBox checkBox;
        private ImageView subcatImage;


        public ManagerAttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategoryTitle = itemView.findViewById(R.id.attendanceListTextView);
            subcatImage = itemView.findViewById(R.id.attendanceListImage);
            checkBox=itemView.findViewById(R.id.attendanceListCheckBox);
        }

    }
}
