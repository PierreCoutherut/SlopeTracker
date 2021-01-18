package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Bienvenue extends AppCompatActivity {

    private Button suivantButton;
    private TextView bienvenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenue);

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
                        startActivity(inscription);
                        finish();
                    }
                });
            }
        });
    }
}