package com.example.py6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PicActivity extends AppCompatActivity {
    private TextView titleText;
    private ImageView imageBig;
    private Data myInstance=Data.getInstance();
    private class LoadBigImageTask extends AsyncTask<String,Void, Bitmap> {
        private ImageView imageView;
        public LoadBigImageTask(ImageView imageView)
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
                    myInstance.addtoBigPicsCache(params[0], bitmap);
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
        protected void onPostExecute(Bitmap bitmap){
            imageView.setImageBitmap(bitmap);

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        titleText=(TextView)findViewById(R.id.titleText);
        imageBig=(ImageView) findViewById(R.id.imageBig);
        Intent intent=getIntent();
        Bundle extras=intent.getExtras();
        String title=extras.getString("titlePic");
        String url=extras.getString("siteUrl");
        titleText.setText(title);
        if(myInstance.getBigPicsCache().containsKey(url))
        {
           imageBig.setImageBitmap(myInstance.getBigPicsCache().get(url));
           System.out.println("Cache working");
        }
        else
        {
            new LoadBigImageTask(imageBig).execute(url);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent=getIntent();
        Bundle extras=intent.getExtras();
        String title=extras.getString("titlePic");
        String url=extras.getString("siteUrl");
        titleText.setText(title);
        if(myInstance.getBigPicsCache().containsKey(url))
        {
            imageBig.setImageBitmap(myInstance.getBigPicsCache().get(url));
            System.out.println("Cache working");
        }
        else
        {
            new LoadBigImageTask(imageBig).execute(url);
        }
    }

}

