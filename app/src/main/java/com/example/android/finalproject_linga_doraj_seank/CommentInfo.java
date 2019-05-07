package com.example.android.finalproject_linga_doraj_seank;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class CommentInfo {
    public String name ;
    public String content;

    public CommentInfo() {

    }

    public CommentInfo(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}