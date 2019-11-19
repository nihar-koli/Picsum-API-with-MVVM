package com.example.recyclerviewdemo.models;

public class ShowImage {

    private String imageUrl;
    private String imageAuthor;

    public ShowImage(String imageAuthor , String imageUrl) {
        this.imageUrl = imageUrl;
        this.imageAuthor = imageAuthor;
    }

    public String getImageAuthor(){
        return imageAuthor;
    }

    public void setImageAuthor(){
        this.imageAuthor = imageAuthor;
    }

    public String getImageUrl() {
        return  imageUrl;
    }

    public void setImageUrl() {
        this.imageUrl = imageUrl;
    }

}
