package com.example.py6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Adapter adaptador;
    private ListView listaImagenes;
    private Data data=Data.getInstance();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaImagenes=(ListView) findViewById(R.id.listV);
        adaptador=new Adapter(this,data.getdataPic());
        listaImagenes.setAdapter(adaptador);
        GetImageTask taskInicial=new GetImageTask();
        try {
            URL url=new URL("https://esahubble.org/images/archive/category/galaxies/?");
            taskInicial.execute(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }
    private class GetImageTask extends AsyncTask<URL,Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(URL... params) {
            HttpURLConnection connection=null;


            try {
                connection = (HttpURLConnection) params[0].openConnection();
                int response = connection.getResponseCode();

                if (response == HttpURLConnection.HTTP_OK) {
                    StringBuilder builder = new StringBuilder();

                    try (BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()))) {

                        String line;

                        while ((line = reader.readLine()) != null) {
                            builder.append(line);
                        }
                    }
                    catch (IOException e) {

                        e.printStackTrace();
                    }
                    int upperIndex=builder.indexOf("var images");
                    int lowerIndex=builder.indexOf("];",upperIndex);
                    String jsonSub=builder.substring(upperIndex,lowerIndex);//Substring de todas las iamgenes sin contar variable var
                    String jsonFinal="{\"var images\": ["+ jsonSub.substring(jsonSub.indexOf('{'), jsonSub.lastIndexOf('}') + 1) + "]}";//Construccion de string que pueda ser JSON
                    return new JSONObject(jsonFinal);

                }
                else {
                    System.out.println("Conexion refused");
                }
            }
            catch (Exception e) {

                e.printStackTrace();
            }
            finally {
                connection.disconnect(); // close the HttpURLConnection
            }

            return null;
        }
        @Override
        protected void onPostExecute(JSONObject image) {
            parsingJSON(image);
            adaptador.notifyDataSetChanged();

        }
    }
    private void parsingJSON(JSONObject space){

        try {
            if(space==null)
            {
                System.out.println("No esta procesado");
            }
            JSONArray images=space.getJSONArray("var images");
            for(int i=0;i<images.length();i++)
            {
                JSONObject singleImage=images.getJSONObject(i);
                String titulo=singleImage.getString("title");
                String url=singleImage.getString("src");
                data.addPicture(titulo,url);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}