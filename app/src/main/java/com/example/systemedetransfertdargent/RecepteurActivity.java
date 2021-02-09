package com.example.systemedetransfertdargent;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
import java.util.Queue;

public class RecepteurActivity extends AppCompatActivity {

    private EditText editTextTextPersonName;
    private EditText editTextTextPersonName2;
    private EditText editTextNumber;
    private Button button3;
    private RequestQueue mQueue;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepteur);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        editTextNumber = findViewById(R.id.editTextNumber);

        mQueue = Volley.newRequestQueue(this);
        Button button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonButton();

            }

            private void jsonButton() {

                String url = "http://192.168.1.4:8080/recepteur";
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {
                            CharArrayWriter mTextViewResult;

                            @Override
                            public void onResponse(JSONArray response) {

                                try {
                                    JSONObject jsonArray = response.getJSONObject(0);
                                    mTextViewResult = new CharArrayWriter();

                                    for (int i = 0; i < response.length(); i++) {
                                        JSONObject recepteur = response.getJSONObject(i);
                                        String nomE = recepteur.getString("nomE");
                                        String prenomE = recepteur.getString("prenomE");
                                        String telE = recepteur.getString("telE");
                                        // String cinE =  recepteur .getString("cinE");
                                        mTextViewResult.append(nomE + "," + prenomE + "" + telE + "\n\n");

                                    }
                                    Intent otherActivity = new Intent(RecepteurActivity.this, MainActivity.class);
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
    }

}
