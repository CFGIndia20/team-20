package com.example.umeed.ExperienceDetails;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.umeed.R;

public class ExperienceFragment extends Fragment {

//    String task = "Task 1";
//    String progress = "25/100";
//    ArrayList<Entry> values = new ArrayList<Entry>();
    TextView experienceTitleDetailTextView, experienceDetailsTextViewFull;
    String title = "Karigari";
    String desc = "and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.experience_details_fragment, container, false);
        experienceTitleDetailTextView = (TextView) root.findViewById(R.id.experienceTitleDetailTextView);
        experienceDetailsTextViewFull = (TextView) root.findViewById(R.id.experienceDetailsTextViewFull);
        experienceTitleDetailTextView.setText(title);
        experienceDetailsTextViewFull.setText(desc);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}