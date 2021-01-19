package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Connexion extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);

    private EditText login;
    private EditText mdp;

    private Button connexion;
    private Button inscrire;

    private String myLogin;
    private String myMdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        login = findViewById(R.id.loginConnexion);
        mdp = findViewById(R.id.passwordConnexion);

        connexion = findViewById(R.id.buttonConnexion);
        inscrire = findViewById(R.id.buttonInscrire);

        final Intent inscription = new Intent(this, Inscription.class);
        final Intent accueil = new Intent(this, Accueil.class);

        inscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(inscription);
            }
        });

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myLogin = login.getText().toString();
                myMdp = mdp.getText().toString();

                if(db.hasObjectSkieur(myLogin,myMdp)){
                    startActivity(accueil);
                }else{
                    Toast.makeText(Connexion.this,"Le Login ou le mot de passe est érronée",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}