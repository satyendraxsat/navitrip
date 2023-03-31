package com.example.newnavitrip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class splash extends AppCompatActivity {


    SharedPreferences SM;
    Boolean islogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SM = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        islogin = SM.getBoolean("userlogin", false);
        if(islogin){
            new Handler().postDelayed(() -> {
                // This method will be executed once the timer is over
                Intent i = new Intent(splash.this, YourJourneyActivity.class);
                startActivity(i);
                finish();
            }, 2000);
        }
        else{
            new Handler().postDelayed(() -> {
                // This method will be executed once the timer is over
                Intent i = new Intent(splash.this, login.class);
                startActivity(i);
                finish();
            }, 1000);
        }
    }


}