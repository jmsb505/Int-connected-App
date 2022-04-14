package com.example.py6;

import android.widget.ImageView;

import java.util.ArrayList;

public class Data {
    public static Data data= new Data();
    ArrayList<ImageView> thumb=new ArrayList<>();
    ArrayList<ImageView> large=new ArrayList<>();
    ArrayList<String> titles=new ArrayList<>();
    ArrayList<String> descriptions=new ArrayList<>();
    private Data(){

    }


    public static Data getInstance() {
        return data;
    }

    public ImageView getLargeAt(int i) {
        return large.get(i);
    }
    public ImageView getThumbAt(int i) {
        return thumb.get(i);
    }
    public String getTitleAt(int i) {
        return titles.get(i);
    }
    public String getDescriptionAt(int i) {
        return descriptions.get(i);
    }
}
