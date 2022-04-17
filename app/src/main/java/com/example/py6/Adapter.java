package com.example.py6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Adapter extends ArrayAdapter<Picture> {
    private Data instance=Data.getInstance();
    private int counter=0;
    private static class ViewHolder {
        ImageView iconoImagen;
        TextView title;
    }

    public Adapter(Context context,ArrayList<Picture>pics){
        super(context,-1,pics);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Picture item= (Picture) getItem(position);
        ViewHolder viewHolder;
        if(convertView==null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.cell, parent, false);
            viewHolder.iconoImagen = (ImageView) convertView.findViewById(R.id.thumbV);
            viewHolder.title = (TextView) convertView.findViewById(R.id.titleV);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        if(instance.getCache().containsKey(item.getimageUrl()))
        {
            viewHolder.iconoImagen.setImageBitmap(instance.getCache().get(item.getimageUrl()));
            counter++;
            System.out.println(counter);//Para ver si se esta cargando en cache [rueba
        }
        else
        {
            new LoadImageTask(viewHolder.iconoImagen).execute(item.getimageUrl());

        }
        String temp=(position+1)+".-"+item.getTitle();
        SpannableString temps=new SpannableString(temp);
        temps.setSpan(new StyleSpan(Typeface.BOLD),0,temps.length(),0);
        viewHolder.title.setText(temps);
        viewHolder.title.append("\n"+item.getimageUrl());
        return convertView;


    }
    private class LoadImageTask extends AsyncTask<String,Void,Bitmap> {
        private ImageView imageView;
        public LoadImageTask(ImageView imageView)
        {
            this.imageView=imageView;
        }



        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            HttpURLConnection connection = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();

                try (InputStream inputStream = connection.getInputStream()) {
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    instance.addtoCache(params[0], bitmap);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                connection.disconnect();
            }

            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){imageView.setImageBitmap(bitmap);}
    }
}
