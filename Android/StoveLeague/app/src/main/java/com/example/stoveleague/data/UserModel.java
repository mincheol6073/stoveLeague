package com.example.stoveleague.data;

public class UserModel {
    private String email;

    public UserModel() {
    }

    public UserModel(String email, String name, String profile) {
        this.email = email;
        this.name = name;
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    private String name;
    private String profile;

}
