package com.example.py6;

import android.media.Image;
import android.widget.ImageView;

public class Cells {
    private String title;
    private int thumb;

    public Cells(String t, int th){
        title=t;
        thumb=th;
    }

    public String getTitle() {
        return title;
    }
    public int getThumb() {
        return thumb;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setThumb(int thumb) {
        this.thumb = thumb;
    }
}
