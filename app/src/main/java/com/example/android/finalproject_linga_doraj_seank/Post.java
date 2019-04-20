package com.example.android.finalproject_linga_doraj_seank;

public class Post {
    int photoUrl;
    int title;
    int author;
    int liked;

    public Post(int photoUrl, int title, int author, int liked) {
        this.photoUrl = photoUrl;
        this.title = title;
        this.author = author;
        this.liked = liked;
    }

    public Post() {
    }

    public int getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(int photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }
}
