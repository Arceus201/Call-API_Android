package com.example.retrofit_upload_img;

import com.google.gson.annotations.SerializedName;

public class User {
    private int accounts;
    private String username,password;
    @SerializedName("avtt")
    private String avatar;

    public User(int accounts, String username, String password, String avatar) {
        this.accounts = accounts;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
    }

    public int getAccounts() {
        return accounts;
    }

    public void setAccounts(int accounts) {
        this.accounts = accounts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
