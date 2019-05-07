package com.example.android.finalproject_linga_doraj_seank;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class CommentInfo {
    public String name ;
    public String content;
    public long create_at ;

    public CommentInfo() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public CommentInfo(String name, String content) {
        this.name = name ;
        this.content = content ;
        this.create_at = System.currentTimeMillis() ;
    }

}