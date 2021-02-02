package com.example.systemedetransfertdargent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnvoieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envoie);

       Button button=findViewById(R.id.button_envoie);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View view){
                Intent otherActivity = new  Intent ( EnvoieActivity.this,RecepteurActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });
    }
}