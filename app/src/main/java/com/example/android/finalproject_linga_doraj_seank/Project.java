package com.example.android.finalproject_linga_doraj_seank;

public class Project {
    public int name;
    public int level;
    public int time;
    public int rating;
    public int photoID;

    public Project(int name, int level, int time, int rating, int photoID) {
        this.name = name;
        this.level = level;
        this.time = time;
        this.photoID = photoID;
        this.rating = rating;
    }

    public Project(){

    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
