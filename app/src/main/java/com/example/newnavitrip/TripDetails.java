package com.example.newnavitrip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.newnavitrip.adapter.RecyclerViewAdapter;

import java.util.ArrayList;

public class TripDetails extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<String> contactArrylist= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}