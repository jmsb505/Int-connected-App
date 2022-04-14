package com.example.py6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter {
    private static class ViewHolder {
        ImageView thumb;
        TextView title;
    }
    public Adapter(Context context,ArrayList<Cells>s){
        super(context,-1,s);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cells cells= (Cells) getItem(position);
        ViewHolder viewHolder = new ViewHolder();
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.cell,parent,false);
        }
        viewHolder.thumb=(ImageView) convertView.findViewById(R.id.thumbV);
        viewHolder.title=(TextView)  convertView.findViewById(R.id.titleV);
        //viewHolder.thumb.setImageDrawable();
        //viewHolder.title.setImageDrawable();
        return convertView;

    }
}
