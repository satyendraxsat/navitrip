package com.example.newnavitrip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    String device="android";
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextUsername = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login_btn);
        checkInternetConenction();
        //if user presses on login
        //calling the method login
        login.setOnClickListener(view -> {
            userLogin();
        });

    }

    private void userLogin() {
        //first getting the values
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Please enter your username", Toast.LENGTH_LONG).show();
//            editTextUsername.setError("Please enter your username");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_LONG).show();
//            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_login,
                response -> {

                    try {
                        //converting response to json object
                        JSONObject obj = new JSONObject(response);
                        // Log.d("tag", obj.toString(4));
                        //if no error in response
                        if (!obj.getBoolean("error")) {
                           // Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            //getting the trips from the response
                            JSONArray triplist=new JSONArray(obj.getString("data"));
                            String s = String.valueOf(obj.getString("auth_key"));
                            String tripid = String.valueOf(obj.getString("trip_id"));
                            // Storing data into SharedPreferences
                            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                            // Creating an Editor object to edit(write to the file)
                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            // Storing the key and its value as the data fetched from edittext
                            myEdit.putString("tripdata", String.valueOf(triplist));
                            myEdit.putString("auth_key", s);
                            myEdit.putString("trip_id", tripid);
                            myEdit.putBoolean("userlogin",true);
                            myEdit.apply();
                            finish();
                            startActivity(new Intent(getApplicationContext(), Welcome.class));

                        } else {
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_login", username);
                params.put("user_pass", password);
                params.put("device", device);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }




    private boolean checkInternetConenction() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec
                =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() ==
                android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
//            Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;
        }else if (
                connec.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() ==
                                android.net.NetworkInfo.State.DISCONNECTED  ) {
            Toast.makeText(this, " Not Connected with internet. Please connect your device with internet", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }
}