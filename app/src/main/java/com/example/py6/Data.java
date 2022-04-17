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
    private Map<String,Picture> dataMap;
    private Map<String, Bitmap> cache;
    private Map<String, Bitmap> cacheBigPics;
    private static Data instancia=new Data();
    public static Data getInstance(){return instancia;}
    private Data()
    {
        dataPic=new ArrayList<>();
        dataMap=new HashMap<>();
        cache=new HashMap<>();
        cacheBigPics=new HashMap<>();
    }
    public void clearDataSet()
    {
        dataPic.clear();
        dataMap.clear();
        cache.clear();
        cacheBigPics.clear();
    }
    public ArrayList<Picture> getdataPic()
    {
        return dataPic;
    }
    public Map<String,Picture> getdataMap(){return dataMap;}
    public void addPicture(String title,String url)
    {
        dataPic.add(new Picture(title,url));
    }
    public void addToDataMap(String title,Picture pic){dataMap.put(title,pic);}
    public void addtoCache(String url,Bitmap bmap)
    {
        cache.put(url,bmap);
    }
    public void addtoBigPicsCache(String url,Bitmap bmap)
    {
        cacheBigPics.put(url,bmap);
    }
    public Map<String, Bitmap> getCache() {
        return cache;
    }
    public Map<String, Bitmap> getBigPicsCache() {
        return cacheBigPics;
    }


}
