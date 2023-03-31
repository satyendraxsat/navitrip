package com.example.newnavitrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class triplist extends AppCompatActivity {
    ListView list;
    String s1,tripdata;
    String trip_id;
    ArrayList<String> maintitle= new ArrayList<>();
    ArrayList<String> subtitle= new ArrayList<>();
    ArrayList<String> imgid= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triplist);
        list = findViewById(R.id.list);

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        s1 = sh.getString("auth_key", "");
        tripdata = sh.getString("tripdata", "");

        try {
            JSONArray jsonArr = new JSONArray(tripdata);
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject explrObject = jsonArr.getJSONObject(i);
                String a=explrObject.getString("trip_name");
                String b=explrObject.getString("tour_desctiption");
                String c=explrObject.getString("path");
                trip_id=explrObject.getString("trip_id");
                maintitle.add(a);
                subtitle.add(b);
                imgid.add(c);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        MyListAdapter adapter=new MyListAdapter(this, maintitle, subtitle,imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                    //code specific to first list item
                    Intent i=new Intent(getApplicationContext(), Welcome.class);
                    i.putExtra("trip_id",trip_id);
                    startActivity(i);

            }
        });
    }
}