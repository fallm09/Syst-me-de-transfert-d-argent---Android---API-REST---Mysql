package com.example.systemedetransfertdargent;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class EnvoieActivity extends AppCompatActivity {
    private Button button_envoi;
    private TextView editTextDate;
    private TextView editTextNumber2;
    private RequestQueue mQueue;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envoie);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        Button button1 = findViewById(R.id.button_envoi);
        editTextDate = findViewById(R.id.editTextDate);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        Button button = findViewById(R.id.button_envoie);


        mQueue = Volley.newRequestQueue(this);
        button_envoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonEnvoi();

            }

            private void jsonEnvoi() {
                String url = "http://192.168.1.208:8080/envoie";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(EnvoieActivity.this, EmetteurActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });

    }

}