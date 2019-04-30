package com.example.android.finalproject_linga_doraj_seank;

public class Post {
    String photoUrl;
    String title;
    String author;
    String liked;

    public Post(String photoUrl, String title, String author, String liked) {
        this.photoUrl = photoUrl;
        this.title = title;
        this.author = author;
        this.liked = liked;
    }

    public Post() {
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getLiked() {
        return liked;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }
}
