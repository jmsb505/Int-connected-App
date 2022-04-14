package com.example.py6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Cells> cells=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private void setData()
    {
        //cells.add(new Cells("Titulo",Drwable de thumbnail));
    }
    private void setList(){
        ListView lv= (ListView) findViewById(R.id.listV);
        Adapter adapter= new Adapter(getApplicationContext(),cells);
    }
}