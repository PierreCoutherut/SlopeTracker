package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Bienvenue extends AppCompatActivity {

    private Button suivantButton;
    private TextView bienvenue;
    private final String rememberKey = "rememberKey";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenue);
        final SharedPreferences pref = getSharedPreferences("Bienvenue", MODE_PRIVATE);
        suivantButton = findViewById(R.id.suivantButton);
        bienvenue = findViewById(R.id.textViewBienvenue);
        final Intent inscription = new Intent(this, Inscription.class);

        suivantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bienvenue.setText(R.string.bienvenue_part2);
                suivantButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(rememberKey,true);
                        editor.apply();
                        startActivity(inscription);
                        finish();
                    }
                });
            }
        });
    }
}