package com.example.py6;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Data {
    private ArrayList<Picture> dataPic;
    private Map<String, Bitmap> cache;
    private static Data instancia=new Data();
    public static Data getInstance(){return instancia;}
    private Data()
    {
        dataPic=new ArrayList<>();
        cache=new HashMap<>();
    }
    public void clearDataSet()
    {
        dataPic.clear();
        cache.clear();
    }
    public ArrayList<Picture> getdataPic()
    {
        return dataPic;
    }
    public void addPicture(String title,String url)
    {
        dataPic.add(new Picture(title,url));
    }
    public void addtoCache(String url,Bitmap bmap)
    {
        cache.put(url,bmap);
    }


    public Map<String, Bitmap> getCache() {
        return cache;
    }

    public String getName(int index) {
        return dataPic.get(index).getTitle();
    }


}
