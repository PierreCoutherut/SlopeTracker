package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {
    private Boolean Myremember;
    private final String rememberKey = "rememberKey";
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        boolean h = new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sharedPreferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
                Myremember = sharedPreferences.contains(rememberKey);
                Intent i = new Intent(Splash.this, Bienvenue.class);
                if (Myremember){
                     i = new Intent(Splash.this, Connexion.class);
                }else{

                }
                startActivity(i);
                finish();
            }
        },3000);
    }
}



