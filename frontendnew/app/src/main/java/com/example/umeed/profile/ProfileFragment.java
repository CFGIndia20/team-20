package com.example.umeed.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.umeed.R;
import com.example.umeed.data.PrefManager;
import com.example.umeed.data.model.request.ProfileRequestModel;
import com.example.umeed.data.model.response.ProfileDetailsResponseModel;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    CircleImageView circleImageView;
    TextView profileNameTextView, profileCompensationTextView, profileAttendanceTextView,skills,area;
   @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.profile_details_fragment, container, false);
        profileNameTextView = (TextView) root.findViewById(R.id.profileNameTextView);
        profileCompensationTextView = (TextView) root.findViewById(R.id.profileCompensationTextView);
        profileAttendanceTextView = (TextView) root.findViewById(R.id.profileAttendanceTextView);
        circleImageView = (CircleImageView) root.findViewById(R.id.profile_image);
        skills=(TextView)root.findViewById(R.id.profileSkillsTV);
        area=(TextView)root.findViewById(R.id.profileAreaTV);

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String name=ProfileFragmentArgs.fromBundle(getArguments()).getName();
        String area1=ProfileFragmentArgs.fromBundle(getArguments()).getArea();
        String skill1=ProfileFragmentArgs.fromBundle(getArguments()).getSkills();
        String imageLink=ProfileFragmentArgs.fromBundle(getArguments()).getImage();
        String textAttendance=ProfileFragmentArgs.fromBundle(getArguments()).getAttendance();
        ProfileViewModel profileViewModel=new ProfileViewModel();
        profileViewModel.getInfo(PrefManager.getInstance().getAuthToken()).observe(getViewLifecycleOwner(), new Observer<ProfileDetailsResponseModel>() {
            @Override
            public void onChanged(ProfileDetailsResponseModel profileDetailsResponseModel) {
                if(profileDetailsResponseModel!=null){
                    profileNameTextView.setText("Romit");
                    Uri uri= Uri.parse(imageLink);
                    circleImageView.setImageResource(R.drawable.umeed_logo);
                    profileCompensationTextView.setText("1000");
                    profileAttendanceTextView.setText(textAttendance);
                    skills.setText(skill1);
                    area.setText(area1);
                }
            }
        });
//        profileNameTextView.setText("ABCDEF");

    }
}