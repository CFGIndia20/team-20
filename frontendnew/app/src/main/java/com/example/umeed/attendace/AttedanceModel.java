package com.example.umeed.attendace;

import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;

public class AttedanceModel {

    private String name;
    private int image;


    public AttedanceModel(String name, int image) {
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