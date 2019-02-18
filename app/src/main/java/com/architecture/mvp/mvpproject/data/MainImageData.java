package com.architecture.mvp.mvpproject.data;

public class MainImageData {

    private String title;

    private int photoId;

    public MainImageData(String title, int photoId) {
        this.title = title;
        this.photoId = photoId;
    }

    public String getTitle() {
        return title;
    }

    public int getPhotoId() {
        return photoId;
    }
}
