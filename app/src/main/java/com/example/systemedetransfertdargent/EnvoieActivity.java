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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.CharArrayWriter;




public class EnvoieActivity extends AppCompatActivity {
    private Button button_envoi;
    private TextView editTextDate;
    private TextView editTextNumber2;
    private RequestQueue mQueue;
    private Button button_envoie1;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envoie);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
         button_envoie1 = findViewById(R.id.button_envoie1);
        editTextDate = findViewById(R.id.editTextDate);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
       // Button button = findViewById(R.id.button_envoie);


        mQueue = Volley.newRequestQueue(this);
        button_envoie1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonButton();

            }

            private void jsonButton() {
                String url = "http://192.168.1.43:8080/envoie";
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {
                            CharArrayWriter mTextViewResult;
                            @Override
                            public void onResponse(JSONArray response) {

                                try {
                                    JSONObject jsonArray = response.getJSONObject(0);
                                    mTextViewResult= new CharArrayWriter();

                                    for (int i = 0; i < response.length(); i++) {
                                        JSONObject envoie = response.getJSONObject(i);
                                        String dateEnv = envoie.getString("dateEnv");
                                        String montant = envoie.getString("montant");
                                        mTextViewResult.append(dateEnv + "," + montant + "\n\n");
                                    }

                                    Intent otherActivity = new Intent(EnvoieActivity.this, EmetteurActivity.class);
                                    startActivity(otherActivity);
                                    finish();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                mQueue.add(request);
            }
        });

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent otherActivity = new Intent(EnvoieActivity.this, EmetteurActivity.class);
//                startActivity(otherActivity);
//                finish();
//            }
//        });



    }

}