package com.example.myapplication;

public class Point {

    private int id;
    private double latitude;
    private double longitude;
    private int altitude;
    private int vitesse;
    private String tempPoint;
    private int idSession;

    public static final String TABLE_NAME_POINTS = "Points";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_ALTITUDE = "altitude";
    public static final String COLUMN_VITESSE = "vitesse";
    public static final String COLUMN_TEMPPOINT = "tempPoint";
    public static final String COLUMN_IDSESSION = "idSession";



    //Création de la table
    // Create table SQL query
    public static final String CREATE_TABLE_POINTS =
            "CREATE TABLE "+ TABLE_NAME_POINTS +"( "
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_LATITUDE + " REAL,"
                    + COLUMN_LONGITUDE + " REAL,"
                    + COLUMN_ALTITUDE + " INTEGER,"
                    + COLUMN_VITESSE + " INTEGER,"
                    + COLUMN_TEMPPOINT + " TEXT,"
                    +COLUMN_IDSESSION + " INTEGER"
                    + " )";

    //Constructeur

    public Point(){

    }

    public Point(int id, double latitude, double longitude, int altitude, int vitesse, String tempPoint, int idSession){
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.vitesse = vitesse;
        this.tempPoint = tempPoint;
        this.idSession = idSession;
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

    public int getIdSession(){return  idSession;}

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

    public void setIdSession(int idSession){this.idSession = idSession;}
}
