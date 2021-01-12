package com.example.myapplication;

import java.util.Date;

public class Sessions {
    private int id;
    private String titre;
    private Date date;
    private int altitudeMax;
    private int vitessMax;
    private double distanceParcourue;
    private double temps;


    public static final String TABLE_NAME = "Point";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITRE = "latitude";
    public static final String COLUMN_DATE = "longitude";
    public static final String COLUMN_DISTANCE_PARCOURUE = "altitude";
    public static final String COLUMN_TEMPS = "vitesse";


    //Création de la table
    // Create table SQL query
    public static final String CREATE_TABLE=
            "CREATE TABLE "+ TABLE_NAME +"("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITRE + " REAL,"
                    + COLUMN_DATE + "REAL,"
                    + COLUMN_DISTANCE_PARCOURUE + "INTEGER,"
                    + COLUMN_TEMPS + "INTEGER,"
                    + ")";

    public Sessions(int id, String titre, Date date, int altitudeMax, int vitessMax, double distanceParcourue, double temps) {
        this.id = id;
        this.titre = titre;
        this.date = date;
        this.altitudeMax = altitudeMax;
        this.vitessMax = vitessMax;
        this.distanceParcourue = distanceParcourue;
        this.temps = temps;
    }

    public int getId() {
        return id;
    }
    public String gettitre(){
        return  titre;
    }
    public Date getdate(){
        return date;
    }
    public int getAltitudeMax(){
        return altitudeMax;
    }
    public double getDistanceParcourue(){
        return  distanceParcourue;
    }
    public double getTemps(){
        return temps;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAltitudeMax(int altitudeMax) {
        this.altitudeMax = altitudeMax;
    }

    public void setDistanceParcourue(double distanceParcourue) {
        this.distanceParcourue = distanceParcourue;
    }

    public void setTemps(double temps) {
        this.temps = temps;
    }

    public void setVitessMax(int vitessMax) {
        this.vitessMax = vitessMax;
    }
}
