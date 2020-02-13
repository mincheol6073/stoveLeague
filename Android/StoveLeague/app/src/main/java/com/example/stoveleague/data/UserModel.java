package com.example.stoveleague.data;

public class UserModel {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public UserModel() {
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public UserModel(String userProfile, String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.userProfile = userProfile;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    private String userName;
    private String userProfile;

}
