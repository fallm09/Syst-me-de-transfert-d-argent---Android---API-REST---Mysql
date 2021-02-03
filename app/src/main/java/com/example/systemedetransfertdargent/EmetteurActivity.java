package com.example.systemedetransfertdargent;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class EmetteurActivity extends AppCompatActivity {

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emetteur);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        Button button=findViewById(R.id.button_emetteur);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(EmetteurActivity.this,RecepteurActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });
    }
}