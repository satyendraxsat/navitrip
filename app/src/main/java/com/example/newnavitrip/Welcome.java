package com.example.newnavitrip;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Welcome extends AppCompatActivity {
    TextView guestName;
    TextView datetime;
    TextView welcome;
    SimpleDateFormat inSimpleDateFormat;
    SimpleDateFormat outSimpleDateFormat;
    Date date = null;
    Date dates = null;
    String fromTourDate = "";
    String toTourDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcome = findViewById(R.id.welcome);
        guestName = findViewById(R.id.name);
        datetime = findViewById(R.id.date);

        getuserdetails();

    }

    private void getuserdetails() {
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("auth_key", "");
        String s2 = sh.getString("trip_id", "");
        @SuppressLint("SetTextI18n") StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_tripdetails,
                response -> {
                    try {
                        //converting response to json object
                        JSONObject obj = new JSONObject(response);
                        //if no error in response
                        if (!obj.getBoolean("error")) {
                            //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            //getting the trips from the response
                            JSONObject userdetail = new JSONObject(obj.getString("data"));
//                            Log.e("aaaaaaaaaa", String.valueOf(userdetail));
                            String guestnamee = userdetail.getString("user_name");
//                            Log.e("aaaaaaaaaa", guestnamee);
                            guestName.setText("" + guestnamee);
                            welcome.setText(userdetail.getString("trip_name"));
                            String fromDate = userdetail.getString("start_date");
                            String toDate = userdetail.getString("end_date");
                            inSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                            outSimpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                            try {
                                date = inSimpleDateFormat.parse(fromDate.split(" ")[0]);
                                dates = inSimpleDateFormat.parse(toDate.split(" ")[0]);

                                assert date != null;
                                assert dates != null;

                                fromTourDate = outSimpleDateFormat.format(date);
                                toTourDate = outSimpleDateFormat.format(dates);

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            datetime.setText("From "+fromTourDate+" "+ "---" + "To "+toTourDate);
                            JSONArray tripPlanObj = new JSONArray(userdetail.getString("trip_plan"));
                            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            myEdit.putString("trip_data", String.valueOf(userdetail));
                            myEdit.putString("trip_plan_data", String.valueOf(tripPlanObj));
                            myEdit.apply();
                            redirectPageNavigation();
                        } else {
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show()) {

            @Override
            public Map getHeaders() throws AuthFailureError {
                Intent intent = getIntent();
                String tripid = intent.getStringExtra("trip_id");
                HashMap headers = new HashMap();
                headers.put("key", s1);
                headers.put("tripid", s2);
                return headers;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void redirectPageNavigation() {
        new Handler().postDelayed(() -> {
            Intent i = new Intent(getApplicationContext(), YourJourneyActivity.class);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
            startActivity(i);
            finish();
        }, 3000);

    }

}