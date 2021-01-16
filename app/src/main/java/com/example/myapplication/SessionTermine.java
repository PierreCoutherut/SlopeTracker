package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SessionTermine extends AppCompatActivity {
    private Button RetourAccueilButton;
    private  Button mapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_termine);
        final Intent accueil = new Intent(this, Accueil.class);
        final Intent map = new Intent(this, MapsActivity.class);
        RetourAccueilButton =  findViewById(R.id.RetourAccueilButton);
        mapButton = findViewById(R.id.mapButton);

        RetourAccueilButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(accueil);
                    }
                }
        );

        mapButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(map);
                    }
                }
        );
    }
}