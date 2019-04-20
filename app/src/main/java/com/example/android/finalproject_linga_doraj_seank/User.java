package com.example.android.finalproject_linga_doraj_seank;

import java.util.ArrayList;

public class User {
    String userHomeOwnership;
    String userDIYExperience;
    ArrayList<String> userInterests;

    public User(String userHomeOwnership, String userDIYExperience, ArrayList<String> userInterests) {
        this.userHomeOwnership = userHomeOwnership;
        this.userDIYExperience = userDIYExperience;
        this.userInterests = userInterests;
    }

    public User() {
    }

    public String getUserHomeOwnership() {
        return userHomeOwnership;
    }

    public void setUserHomeOwnership(String userHomeOwnership) {
        this.userHomeOwnership = userHomeOwnership;
    }

    public String getUserDIYExperience() {
        return userDIYExperience;
    }

    public void setUserDIYExperience(String userDIYExperience) {
        this.userDIYExperience = userDIYExperience;
    }

    public ArrayList<String> getUserInterests() {
        return userInterests;
    }

    public void setUserInterests(ArrayList<String> userInterests) {
        this.userInterests = userInterests;
    }
}
