package com.example.newnavitrip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class curency_conversion extends AppCompatActivity {

    EditText firstnum,secondnum;
    TextView r;
    Button bt;
    double a;
    double b;
    double c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curency_conversion);



        firstnum=(EditText) findViewById(R.id.curr1);
        firstnum.requestFocus();
        secondnum=(EditText) findViewById(R.id.curr2);
        bt=(Button) findViewById(R.id.convert);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=Double.parseDouble(firstnum.getText().toString());
                c=a*82.33;
                String s= String.valueOf(c);
                secondnum.setText(s);
            }
        });







        TextView logout=findViewById(R.id.tv_logout);
        logout.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putBoolean("userlogin", false);
            myEdit.apply();
            Intent intent = new Intent(curency_conversion.this, login.class);
            startActivity(intent);
            finishAffinity();
        });


        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.person);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.person:
                        return true;
                    case R.id.home:
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                        startActivity(new Intent(getApplicationContext(),YourJourneyActivity.class));
                        return true;
                    case R.id.chatbox:
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                        startActivity(new Intent(getApplicationContext(),chatbox.class));
                        return true;
                }
                return false;
            }
        });
    }
}