package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;

public class PointDB  {
    public static final String TABLE_NAME = "Point";
    public static final String KEY = "id";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String ALTITUDE = "altitude";
    public static final String VITESSE = "vitesse";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + LATITUDE + " REAL, " + LONGITUDE + " REAL" + ALTITUDE + " INTEGER" + VITESSE + " INTEGER);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public void ajouter(Point p) {
        ContentValues value = new ContentValues();
        value.put(this.LATITUDE, p.getLatitude());
        value.put(this.LONGITUDE, p.getLongitude());
        value.put(this.ALTITUDE, p.getAltitude());
        value.put(this.VITESSE, p.getVitesse());


       // mDb.insert(this.TABLE_NAME, null, value);
    }

    public void supprimer(long id) {
        // CODE
    }

    public void modifier(Point m) {
        // CODE
    }

}
