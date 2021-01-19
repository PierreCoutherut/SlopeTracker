package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.se.omapi.Session;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "tracker_db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Point.CREATE_TABLE_POINTS);
        db.execSQL(Sessions.CREATE_TABLE_SESSION);
        db.execSQL(Skieurs.CREATE_TABLE_SKIEURS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Point.TABLE_NAME_POINTS);
        db.execSQL("DROP TABLE IF EXISTS " + Sessions.TABLE_NAME_SESSION);
        db.execSQL("DROP TABLE IF EXISTS " + Skieurs.TABLE_NAME_SKIEURS);
        onCreate(db);

    }

    public long insertPoint(double latitude, double longitude, int altitude, double vitesse, String tempPoint, int idSession) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Point.COLUMN_LATITUDE, latitude);
        values.put(Point.COLUMN_LONGITUDE, longitude);
        values.put(Point.COLUMN_ALTITUDE, altitude);
        values.put(Point.COLUMN_VITESSE, vitesse);
        values.put(Point.COLUMN_TEMPPOINT, tempPoint);
        values.put(Point.COLUMN_IDSESSION,idSession);

        long id = db.insert(Point.TABLE_NAME_POINTS, null, values);
        db.close();
        return id;
    }

    public long insertSessions(String titre, String date, int altitudeMax, int vitessMax, double distanceParcourue, double temps) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Sessions.COLUMN_TITRE, titre);
        values.put(Sessions.COLUMN_DATE, date);
        values.put(Sessions.COLUMN_ALTITUDE_MAX,altitudeMax);
        values.put(Sessions.COlUMN_VITESSE_MAX,vitessMax);
        values.put(Sessions.COLUMN_DISTANCE_PARCOURUE, distanceParcourue);
        values.put(Sessions.COLUMN_TEMPS, temps);


        long id = db.insert(Sessions.TABLE_NAME_SESSION, null, values);
        db.close();
        return id;
    }

    public long insertSkieurs(String login, String password, String mail) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Skieurs.COLUMN_LOGIN, login);
        values.put(Skieurs.COLUMN_PASSWORD, password);
        values.put(Skieurs.COLUMN_MAIL, mail);


        long id = db.insert(Skieurs.TABLE_NAME_SKIEURS, null, values);
        db.close();
        return id;
    }


    public void deleteSkieur(Skieurs skieur) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Skieurs.TABLE_NAME_SKIEURS, Skieurs.COLUMN_ID + " = ?", new String[]{String.valueOf(skieur.getId())});
        db.close();
    }


    public void deletePoint(Point point) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Point.TABLE_NAME_POINTS, Point.COLUMN_ID + " = ?", new String[]{String.valueOf(point.getId())});
        db.close();
    }

    public void deleteSessions(Sessions sessions){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(sessions.TABLE_NAME_SESSION, Sessions.COLUMN_ID + " = ?", new String[]{String.valueOf(sessions.getId())});
        db.close();
    }

    //Récupère les sessions d'un skieur
    public List<Sessions> getSessionsWhereIdSkieur( int idSkieur) {
        List<Sessions> sessions = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + Sessions.TABLE_NAME_SESSION + " ORDER BY " + Sessions.COLUMN_ID + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Sessions session = new Sessions();
                session.setId(cursor.getInt(cursor.getColumnIndex(Sessions.COLUMN_ID)));
                session.setDate(cursor.getString(cursor.getColumnIndex(Sessions.COLUMN_DATE)));
                session.setAltitudeMax(cursor.getInt(cursor.getColumnIndex(Sessions.COLUMN_ALTITUDE_MAX)));
                session.setTemps(cursor.getInt(cursor.getColumnIndex(Sessions.COLUMN_TEMPS)));
                session.setDistanceParcourue(cursor.getDouble(cursor.getColumnIndex(Sessions.COLUMN_DISTANCE_PARCOURUE)));
                session.setTitre(cursor.getString(cursor.getColumnIndex(Sessions.COLUMN_TITRE)));
                session.setVitessMax(cursor.getInt(cursor.getColumnIndex(Sessions.COlUMN_VITESSE_MAX)));
                session.setIdSkieur(cursor.getInt(cursor.getColumnIndex(Sessions.COLUMN_ID_SKIEUR)));
                sessions.add(session);
            } while (cursor.moveToNext());
        }
        db.close();

        return sessions;
    }


    public Point getPoint(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Point.TABLE_NAME_POINTS,
                new String[]{Point.COLUMN_ID, Point.COLUMN_LATITUDE, Point.COLUMN_LONGITUDE, Point.COLUMN_ALTITUDE, Point.COLUMN_VITESSE, Point.COLUMN_TEMPPOINT,Point.COLUMN_IDSESSION},
                Point.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Point point = new Point(
                cursor.getInt(cursor.getColumnIndex(Point.COLUMN_ID)),
                cursor.getDouble(cursor.getColumnIndex(Point.COLUMN_LATITUDE)),
                cursor.getDouble(cursor.getColumnIndex(Point.COLUMN_LONGITUDE)),
                cursor.getInt(cursor.getColumnIndex(Point.COLUMN_ALTITUDE)),
                cursor.getInt(cursor.getColumnIndex(Point.COLUMN_VITESSE)),
                cursor.getString(cursor.getColumnIndex(Point.COLUMN_TEMPPOINT)),
                cursor.getInt(cursor.getColumnIndex(Point.COLUMN_IDSESSION))
        );

        cursor.close();
        return point;
    }

    public Point getPointWhereSessions(int idSession) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Point.TABLE_NAME_POINTS,
                new String[]{Point.COLUMN_ID, Point.COLUMN_LATITUDE, Point.COLUMN_LONGITUDE, Point.COLUMN_ALTITUDE, Point.COLUMN_VITESSE, Point.COLUMN_TEMPPOINT},
                Point.COLUMN_IDSESSION + "=" + idSession,
                new String[]{String.valueOf(idSession)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Point point = new Point(
                cursor.getInt(cursor.getColumnIndex(Point.COLUMN_ID)),
                cursor.getDouble(cursor.getColumnIndex(Point.COLUMN_LATITUDE)),
                cursor.getDouble(cursor.getColumnIndex(Point.COLUMN_LONGITUDE)),
                cursor.getInt(cursor.getColumnIndex(Point.COLUMN_ALTITUDE)),
                cursor.getInt(cursor.getColumnIndex(Point.COLUMN_VITESSE)),
                cursor.getString(cursor.getColumnIndex(Point.COLUMN_TEMPPOINT)),
                cursor.getInt(cursor.getColumnIndex(Point.COLUMN_IDSESSION))
        );

        cursor.close();
        return point;
    }

    public boolean hasObjectSkieur(String login, String mdp){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM "+Skieurs.TABLE_NAME_SKIEURS+ " WHERE "+ Skieurs.COLUMN_LOGIN + " = '"+login+ "' AND "+ Skieurs.COLUMN_PASSWORD+" = '"+mdp+"'";
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            return true;
        }
        if (cursor!=null) {
            cursor.close();
        }
        db.close();
        return false;
    }

    public Skieurs getSkieur(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Skieurs.TABLE_NAME_SKIEURS,
                new String[]{Skieurs.COLUMN_ID, Skieurs.COLUMN_LOGIN, Skieurs.COLUMN_PASSWORD,Skieurs.COLUMN_MAIL},
                Skieurs.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Skieurs skieurs = new Skieurs(
                cursor.getInt(cursor.getColumnIndex(Skieurs.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Skieurs.COLUMN_LOGIN)),
                cursor.getString(cursor.getColumnIndex(Skieurs.COLUMN_PASSWORD)),
                cursor.getString(cursor.getColumnIndex(Skieurs.COLUMN_MAIL))
        );

        cursor.close();
        return skieurs;
    }


    public Sessions getSessions(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Sessions.TABLE_NAME_SESSION,
                new String[]{Sessions.COLUMN_ID, Sessions.COLUMN_TITRE, Sessions.COLUMN_DATE,Sessions.COLUMN_ALTITUDE_MAX, Sessions.COlUMN_VITESSE_MAX,Sessions.COLUMN_DISTANCE_PARCOURUE, Sessions.COLUMN_TEMPS },
                Sessions.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Sessions point = new Sessions(
                cursor.getInt(cursor.getColumnIndex(Sessions.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Sessions.COLUMN_TITRE)),
                cursor.getString(cursor.getColumnIndex(Sessions.COLUMN_DATE)),
                cursor.getInt(cursor.getColumnIndex(Sessions.COLUMN_ALTITUDE_MAX)),
                cursor.getInt(cursor.getColumnIndex(Sessions.COlUMN_VITESSE_MAX)),
                cursor.getDouble(cursor.getColumnIndex(Sessions.COLUMN_DISTANCE_PARCOURUE)),
                cursor.getDouble(cursor.getColumnIndex(Sessions.COLUMN_TEMPS)),
                cursor.getInt(cursor.getColumnIndex(Sessions.COLUMN_ID_SKIEUR))
        );

        cursor.close();
        return point;
    }

    public List<Point> getAllPoints() {
        List<Point> points = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + Point.TABLE_NAME_POINTS + " ORDER BY " + Point.COLUMN_ID + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Point point = new Point();
                point.setId(cursor.getInt(cursor.getColumnIndex(Point.COLUMN_ID)));
                point.setLatitude(cursor.getDouble(cursor.getColumnIndex(Point.COLUMN_LATITUDE)));
                point.setLongitude(cursor.getDouble(cursor.getColumnIndex(Point.COLUMN_LONGITUDE)));
                point.setAltitude(cursor.getInt(cursor.getColumnIndex(Point.COLUMN_ALTITUDE)));
                point.setVitesse(cursor.getInt(cursor.getColumnIndex(Point.COLUMN_VITESSE)));
                point.setTempPoint(cursor.getString(cursor.getColumnIndex(Point.COLUMN_TEMPPOINT)));
                point.setIdSession(cursor.getInt(cursor.getColumnIndex(Point.COLUMN_IDSESSION)));
                points.add(point);
            } while (cursor.moveToNext());
        }
        db.close();

        return points;
    }

    public int updatePoint(Point point, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Point.COLUMN_LATITUDE, point.getLatitude());
        values.put(Point.COLUMN_LONGITUDE, point.getLongitude());
        values.put(Point.COLUMN_ALTITUDE, point.getAltitude());
        values.put(Point.COLUMN_VITESSE, point.getVitesse());
        values.put(Point.COLUMN_TEMPPOINT, point.getTempPoint());
        return db.update(tableName, values, Point.COLUMN_ID + " = ?", new String[]{String.valueOf(point.getId())});
    }
}