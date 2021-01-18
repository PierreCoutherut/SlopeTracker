package com.example.myapplication;

import java.util.Date;
//Classe Sessions
public class Sessions {
    private int id;
    private String titre;
    private String date;
    private int altitudeMax;
    private int vitessMax;
    private double distanceParcourue;
    private double temps;


    public static final String TABLE_NAME = "Session";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITRE = "titre";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_ALTITUDE_MAX ="altitudeMax";
    public static final String COlUMN_VITESSE_MAX = "vitesMax";
    public static final String COLUMN_DISTANCE_PARCOURUE = "distanceParcourue";
    public static final String COLUMN_TEMPS = "temps";


    //Création de la table
    // Create table SQL query
    public static final String CREATE_TABLE_SESSION=
            "CREATE TABLE "+ TABLE_NAME +"("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITRE + " REAL,"
                    + COLUMN_DATE + "REAL,"
                    + COLUMN_ALTITUDE_MAX + "REAL,"
                    + COlUMN_VITESSE_MAX + "REAL,"
                    + COLUMN_DISTANCE_PARCOURUE + "INTEGER,"
                    + COLUMN_TEMPS + "INTEGER,"
                    + ")";

    public Sessions(int id, String titre, String date, int altitudeMax, int vitessMax, double distanceParcourue, double temps) {
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
    public String getdate(){
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

    public void setDate(String date) {
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
