package com.example.myapplication;

public class Point {

    private int id;
    private double latitude;
    private double longitude;
    private int altitude;
    private int vitesse;
    private String tempPoint;

    public static final String TABLE_NAME = "Point";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_ALTITUDE = "altitude";
    public static final String COLUMN_VITESSE = "vitesse";
    public static final String COLUMN_TEMPPOINT = "tempPoint";


    //Création de la table
    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME +"("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_LATITUDE + " REAL,"
                    + COLUMN_LONGITUDE + "REAL,"
                    + COLUMN_ALTITUDE + "INTEGER,"
                    + COLUMN_VITESSE + "INTEGER,"
                    + COLUMN_TEMPPOINT + "TEXT"
                    + ")";

    //Constructeur

    public Point(){

    }

    public Point(int id, double latitude, double longitude, int altitude, int vitesse, String tempPoint){
        super();
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.vitesse = vitesse;
        this.tempPoint = tempPoint;
    }

    //Getter
    public int getId(){
        return id;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public int getAltitude(){
        return altitude;
    }

    public float getVitesse(){
        return  vitesse;
    }

    public String getTempPoint(){ return  tempPoint;}


    //Setter
    public void setId(int id){
        this.id = id;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    public void setAltitude(int altitude){
        this.altitude = altitude;
    }

    public void setVitesse(int vitesse)
    {
        this.vitesse = vitesse;
    }

    public void setTempPoint(String tempPoint){this.tempPoint = tempPoint;}
}
