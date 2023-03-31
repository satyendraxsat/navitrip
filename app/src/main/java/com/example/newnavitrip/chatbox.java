package com.example.newnavitrip;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class chatbox extends AppCompatActivity {
    private static final int REQUEST_PHONE_CALL = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbox);

TextView call= findViewById(R.id.call);
call.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if (ContextCompat.checkSelfPermission(chatbox.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(chatbox.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
        }
        else
        {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+" 123456789"));
            startActivity(callIntent);
        }







    }
});
        TextView mail= findViewById(R.id.mail);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:satendra@xsatindia.in"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        TextView whatsapp= findViewById(R.id.whatsapp);
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //So you can get the edittext value
                String mobileNumber = "8802389820";
                String message = "Hello";
                boolean installed = appInstalledOrNot("com.whatsapp");
                if (installed){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+91"+mobileNumber + "&text="+message));
                    startActivity(intent);
                }else {
                    Toast.makeText(chatbox.this, "Whats app not installed on your device", Toast.LENGTH_SHORT).show();
                }
            }
        });




        TextView logout=findViewById(R.id.tv_logout);
        logout.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putBoolean("userlogin", false);
            myEdit.apply();
            Intent intent = new Intent(chatbox.this, login.class);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
            startActivity(intent);
            finishAffinity();
        });

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.chatbox);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.person:
                        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                        startActivity(new Intent(getApplicationContext(),curency_conversion.class));
                        return true;
                    case R.id.home:
                        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                        startActivity(new Intent(getApplicationContext(),YourJourneyActivity.class));

                    case R.id.chatbox:
                        return true;
                }
                return false;
            }
        });










    }


    //Create method appInstalledOrNot
    private boolean appInstalledOrNot(String url){
        PackageManager packageManager =getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(url,PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }catch (PackageManager.NameNotFoundException e){
            app_installed = false;
        }
        return app_installed;
    }


}