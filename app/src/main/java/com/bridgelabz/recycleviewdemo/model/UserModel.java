package com.bridgelabz.recycleviewdemo.model;

/**
 * Created by bridgelabz5 on 23/5/16.
 */
public class UserModel {

    private int birthYear;
    private String fullName;
    private String image;
    public UserModel() {}
    public UserModel(String fullName, int birthYear,String image) {
        this.fullName = fullName;
        this.birthYear = birthYear;
        this.image=image;
    }
    public String getBirthYear() {
        return String.valueOf(birthYear);
    }
    public String getFullName() {
        return fullName;
    }
    public String getImage() {
        return image;
    }
}
