package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class record extends AppCompatActivity {
    //Déclaration
    DatabaseHelper db = new DatabaseHelper(this);

    private Button playButton;
    private Button pauseButton;
    private TextView chronotextView;
    private final static int REQUEST_CODE_UPDATE_LOCATION=42;
    private LocationManager androidLocationManager;
    private int compteur;
    private int temoinBouton;
    private Timer mTimer1;
    private TimerTask mTt1;
    private int idPoint;
    private Handler mTimerHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        final Chronometer chronometer = (Chronometer) findViewById(R.id.simpleChronometer); // initiate a chronometer
        final Intent SessionTermine = new Intent(this, SessionTermine.class);
        final Intent record = new Intent(this, record.class);

        //Affectation
        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        temoinBouton = 0;
        compteur = 0;
        idPoint = 0;
        temoinBouton = 1;

        //Démarrage de la localisation
        playButton.setBackgroundResource(R.drawable.ic_pauseicon);
        Toast toast = Toast.makeText(record.this, "Démarrage de la localisation", Toast.LENGTH_SHORT);
        toast.show();
        démarrageLocalisation();

        //Détecte si on appuie sur le bouton pause
        playButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (temoinBouton == 0) {
                            temoinBouton = 1;
                            playButton.setBackgroundResource(R.drawable.ic_pauseicon);
                            Toast toast = Toast.makeText(record.this, "redémarrage de la localisation", Toast.LENGTH_SHORT);
                            toast.show();

                            démarrageLocalisation();
                        }else {
                            ArretLocalisation(chronometer);
                        }
                    }
                }
        );
        pauseButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArretLocalisation(chronometer);
                        Toast toast = Toast.makeText(record.this,"Arret", Toast.LENGTH_SHORT);
                        toast.show();
                        Chronometer chronometer = (Chronometer) findViewById(R.id.simpleChronometer); // initiate a chronometer
                        startActivity(SessionTermine);
                    }
                }
        );
    }

    public void ArretLocalisation(Chronometer chronometer ){
        temoinBouton = 0;
        playButton.setBackgroundResource(R.drawable.ic_starticon);
        chronometer.stop(); // stop le chronometre
        onPause();

    }
    public void démarrageLocalisation(){
        Chronometer chronometer = (Chronometer) findViewById(R.id.simpleChronometer); // initiate a chronometer
        chronometer.start(); // démarrage du chrono

        mTimer1 = new Timer();
        mTt1 = new TimerTask() {
            public void run() {
                mTimerHandler.post(new Runnable() {
                    public void run() {
                        androidUpdateLocation();
                        idPoint ++;
                        Toast toast = Toast.makeText(record.this, "actualisé", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }
        };

        mTimer1.schedule(mTt1, 1, 5000);
    }

    public LocationListener androidLocationListener = new LocationListener() {
        public void onLocationChanged(Location loc) {
            Toast toast = Toast.makeText(record.this, "Vous êtes ici : "+loc.getLatitude()+" / "+loc.getLongitude(), Toast.LENGTH_SHORT);
            toast.show();
        }
        public void onStatusChanged(String provider, int status, Bundle extras) {}
        public void onProviderEnabled(String provider) {}
        public void onProviderDisabled(String provider) {}
    };
    public void androidUpdateLocation(){
        if (ActivityCompat.checkSelfPermission(record.this, Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(record.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_UPDATE_LOCATION);
        } else {
            androidLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            androidLocationListener = new LocationListener() {
                public void onLocationChanged(Location loc) {
                    Chronometer chronometer = (Chronometer) findViewById(R.id.simpleChronometer);
                    String temps = chronometer.getText().toString();
                //  double temps = Double.valueOf(tempstring);
                    int altitude = (int) loc.getAltitude();
                    double latitude = loc.getLatitude();
                    double longitude = loc.getLongitude();
                    int vitesse = (int) Math.round(loc.getSpeed() * 3.6);

                    Toast toast = Toast.makeText(record.this, "vous êtes ici : "+ latitude +" / "+ longitude + " | Alitude : "+ altitude +" | Vitesse : "+ vitesse + " | Temps : " + temps, Toast.LENGTH_SHORT);
                    toast.show();
                    Log.i("GPS","Tracking..");
                   // Point point = new Point(idPoint,latitude,longitude,altitude,vitesse,temps);
                   // SharedPreferences pref = getSharedPreferences("profils", MODE_PRIVATE);
                  //  SharedPreferences.Editor editor = pref.edit();

                  //  editor.putString("session","1");
                    db.insertPoint(latitude, longitude, altitude, vitesse, temps);

                }
                public void onStatusChanged(String provider, int status, Bundle extras) {}
                public void onProviderEnabled(String provider) {}
                public void onProviderDisabled(String provider) {}
            };

            androidLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    5, // 1000 en millisecondes
                    1, // 50 en mètres
                    androidLocationListener);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_UPDATE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast toast = Toast.makeText(record.this, "Permission refusée.", Toast.LENGTH_LONG);
                    toast.show();
                }
                return;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(androidLocationListener!=null) {
            if (androidLocationManager == null) {
                androidLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            }
            androidLocationManager.removeUpdates(androidLocationListener);
            androidLocationManager=null;
            androidLocationListener=null;
        }
    }

}