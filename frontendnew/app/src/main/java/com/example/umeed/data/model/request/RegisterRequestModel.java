package com.example.umeed.data.model.request;

public class RegisterRequestModel {
    private String first_name;
    private String last_name;
    private String phone;
    private String password;
    private String area;
    private String skills;

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setMobile(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
