package com.example.umeed.task_details;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.umeed.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class TaskDetailsFragment extends Fragment {
    String task = "Task 1";
    String progress = "25/100";
    private CircularProgressButton button;
    ArrayList<Entry> values = new ArrayList<Entry>();
    TextView detailsTextView, detailsProgress;
    PieChart detailsPieChart;
    GridView detailsGridView;
    int[] images = {R.drawable.umeed_logo, R.drawable.umeed_logo,R.drawable.umeed_logo,R.drawable.umeed_logo,R.drawable.umeed_logo,R.drawable.umeed_logo,R.drawable.umeed_logo};
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_task_details, container, false);
        values.add(new Entry(25f,0));
        values.add(new Entry(75f,1));
        button=root.findViewById(R.id.uploadPhoto);
        detailsTextView = (TextView) root.findViewById(R.id.detailsTextView);
        detailsTextView.setText(task);
        detailsProgress = (TextView) root.findViewById(R.id.detailsProgress);
        detailsProgress.setText(progress);
        detailsPieChart = (PieChart) root.findViewById(R.id.detailsPieChart);
        detailsPieChart.setUsePercentValues(true);
        PieDataSet dataSet = new PieDataSet(values, "Progress");
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("Done");
        xVals.add("Pending");
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        detailsPieChart.setData(data);
        detailsPieChart.setDrawHoleEnabled(true);
        detailsPieChart.setTransparentCircleRadius(30f);
        detailsPieChart.setHoleRadius(30f);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
        detailsPieChart.animateXY(1400, 1400);
        dataSet.setColors(new int[]{Color.GREEN, Color.RED});
        detailsGridView = (GridView) root.findViewById(R.id.detailsGridView);
        CustomAdapter1 customAdapter = new CustomAdapter1();
        detailsGridView.setAdapter(customAdapter);
        return root;
    }

    public class CustomAdapter1 extends BaseAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.images_grid, null);
            ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
            icon.setImageResource(images[position]);
            return convertView;
        }
    }
}
