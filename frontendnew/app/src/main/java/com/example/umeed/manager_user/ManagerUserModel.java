package com.example.umeed.manager_user;

import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;

public class ManagerUserModel {


    private String name;
    private int image;


    public ManagerUserModel(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
