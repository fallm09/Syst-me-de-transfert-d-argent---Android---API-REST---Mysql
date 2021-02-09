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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.CharArrayWriter;


public class EmetteurActivity extends AppCompatActivity {
    private TextView editTextTextPersonName3;
    private TextView editTextTextPersonName4;
    private TextView editTextPhone2;
    private RequestQueue mQueue;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emetteur);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        editTextPhone2 = findViewById(R.id.editTextPhone2);
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
        editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4);


        mQueue = Volley.newRequestQueue(this);
        Button button = findViewById(R.id.button_emetteur);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonButton();

            }

            private void jsonButton() {

                String url = "http://192.168.1.43:8080/emetteurs";
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {
                            CharArrayWriter mTextViewResult;
                            @Override
                            public void onResponse(JSONArray response) {

                                try {
                                    JSONObject jsonArray = response.getJSONObject(0);
                                    mTextViewResult = new CharArrayWriter();

                                    for (int i = 0; i < response.length(); i++) {
                                        JSONObject emetteurs = response.getJSONObject(i);
                                        String nomE = emetteurs.getString("nomE");
                                        String prenomE = emetteurs.getString("prenomE");
                                        String telE = emetteurs.getString("telE");
                                        String cinE = emetteurs.getString("cinE");
                                        mTextViewResult.append(cinE+ "," + nomE+ ","+prenomE+ ""+ telE+"\n\n");

                                    }
                                    Intent otherActivity = new Intent( EmetteurActivity.this, RecepteurActivity.class);
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

