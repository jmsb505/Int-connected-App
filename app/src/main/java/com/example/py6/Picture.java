package com.example.py6;

import android.media.Image;
import android.widget.ImageView;

public class Picture {
    private String title;
    private String imageUrl;


    public Picture(String t, String imageUrl){
        this.title=t;
        this.imageUrl=imageUrl;
    }

    public String getTitle() {
        return title;
    }
    public String getimageUrl() {
        return imageUrl;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setimageUrl(String thumb) {
        this.imageUrl = thumb;
    }
}
