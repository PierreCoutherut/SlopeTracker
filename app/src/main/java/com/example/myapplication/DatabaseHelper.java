package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.se.omapi.Session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "SlopeTrakerDB_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL("DROP TABLE IF EXISTS " + Point.TABLE_NAME);
        db.execSQL(Point.CREATE_TABLE);
        db.execSQL(Sessions.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Point.TABLE_NAME);
      //  db.execSQL("DROP TABLE IF EXISTS " + Revenus.TABLE_NAME);
        // Create tables again
        onCreate(db);
    }
    public long insertPoint(double latitude, double longitude, int altitude, double vitesse, String tempPoint) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues données = new ContentValues();
        données.put(Point.COLUMN_LATITUDE, latitude);
        données.put(Point.COLUMN_LONGITUDE, longitude);
        données.put(Point.COLUMN_ALTITUDE, altitude);
        données.put(Point.COLUMN_VITESSE,vitesse);
        données.put(Point.COLUMN_TEMPPOINT,tempPoint);

        long id = db.insert(Point.TABLE_NAME, null, données);

        db.close();

        return id;
    }


    public Point getPoint(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Point.TABLE_NAME,
                new String[]{Point.COLUMN_ID, Point.COLUMN_LATITUDE, Point.COLUMN_LONGITUDE,Point.COLUMN_ALTITUDE,Point.COLUMN_VITESSE,Point.COLUMN_TEMPPOINT},
                Point.COLUMN_ID + Point.COLUMN_LATITUDE + Point.COLUMN_LONGITUDE + Point.COLUMN_ALTITUDE + Point.COLUMN_VITESSE + Point.COLUMN_TEMPPOINT,
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Point point = new Point (
                cursor.getInt(cursor.getColumnIndex(Point.COLUMN_ID)),
                cursor.getDouble(cursor.getColumnIndex(Point.COLUMN_LATITUDE)),
                cursor.getDouble(cursor.getColumnIndex(Point.COLUMN_LONGITUDE)),
                cursor.getInt(cursor.getColumnIndex(Point.COLUMN_ALTITUDE)),
                cursor.getInt(cursor.getColumnIndex(Point.COLUMN_VITESSE)),
                cursor.getString(cursor.getColumnIndex(Point.COLUMN_TEMPPOINT)));
        cursor.close();

        return point;
    }

    public List<Point> getAllPoint() {
        List<Point> depenses = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + Point.TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                // Erreur Inconnue ici
            //    Point point = new Point();
            //    Point.setId(cursor.getInt(cursor.getColumnIndex(Point.COLUMN_ID)));
            //    Point.setLatitude(cursor.getString(cursor.getColumnIndex(Point.COLUMN_LATITUDE)));
            //    Point.setLongitude(cursor.getString(cursor.getColumnIndex(Point.COLUMN_LONGITUDE)));
             //   Point.setAltitude(cursor.getString(cursor.getColumnIndex(Point.COLUMN_ALTITUDE)));
             //   Point.setVitesse(cursor.getString(cursor.getColumnIndex(Point.COLUMN_VITESSE)));
             //   Point.setTempPoint(cursor.getString(cursor.getColumnIndex(Point.COLUMN_TEMPPOINT)));
              //  depenses.add(point);
            } while (cursor.moveToNext());
        }

        db.close();

        return depenses;
    }



    public int getPointCount() {
        String countQuery = "SELECT  * FROM " + Point.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        return count;
    }

    public void deletePoint(Point point) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Point.TABLE_NAME, Point.COLUMN_ID + " = ?",
                new String[]{String.valueOf(point.getId())});
        db.close();
    }

}


