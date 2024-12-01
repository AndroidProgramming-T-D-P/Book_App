package com.example.bookapp.models;

import com.google.gson.annotations.SerializedName;

public class Photo {
    @SerializedName("id") // Ánh xạ với trường "id" từ API
    private int id;

    @SerializedName("title") // Ánh xạ với trường "title" từ API
    private String title;

    @SerializedName("cover_image") // Ánh xạ với trường "cover_image" từ API
    private String cover_image;

    // Constructor
    public Photo(int id, String title, String cover_image) {
        this.id = id;
        this.title = title;
        this.cover_image = cover_image;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }
}
