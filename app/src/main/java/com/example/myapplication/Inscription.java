package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Inscription extends AppCompatActivity {

    private EditText login;
    private EditText mdp;
    private EditText mdp2;
    private EditText mail;

    private Button valider;

    private String myLogin;
    private String myMdp;
    private String myMdp2;
    private String myMail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        valider = findViewById(R.id.buttonInscription);
        login = findViewById(R.id.editTextLogin);
        mail = findViewById(R.id.editEmailAddress);
        mdp = findViewById(R.id.editTextPassword1);
        mdp2 = findViewById(R.id.editTextPassword2);

        final Intent accueil = new Intent(this, Accueil.class);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myLogin = login.getText().toString();
                myMail = mail.getText().toString();
                myMdp = mdp.getText().toString();
                myMdp2 = mdp2.getText().toString();

                if (myLogin.isEmpty() || myMail.isEmpty() || myMail.isEmpty() || myMdp.isEmpty()
                        || myMdp2.isEmpty()){
                    Toast.makeText(Inscription.this,"Un champs est vide ou mal rempli.",Toast.LENGTH_SHORT).show();
                }else if(!myMdp.equals(myMdp2)){
                    Toast.makeText(Inscription.this,"Le mot de passe n'est pas identique",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(accueil);

                }
            }
        });


    }
}