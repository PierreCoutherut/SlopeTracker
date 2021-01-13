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

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "tracker_db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Point.CREATE_TABLE_POINTS);
        //db.execSQL(Transaction.CREATE_TABLE_R);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Point.TABLE_NAME_POINTS);
        //db.execSQL("DROP TABLE IF EXISTS "+Transaction.TABLE_NAME_R);
        onCreate(db);

    }

    public long insertPoint(double latitude, double longitude, int altitude, double vitesse, String tempPoint) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Point.COLUMN_LATITUDE, latitude);
        values.put(Point.COLUMN_LONGITUDE, longitude);
        values.put(Point.COLUMN_ALTITUDE, altitude);
        values.put(Point.COLUMN_VITESSE, vitesse);
        values.put(Point.COLUMN_TEMPPOINT, tempPoint);

        long id = db.insert(Point.TABLE_NAME_POINTS, null, values);
        db.close();
        return id;
    }


    public void deletePoint(Point point) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Point.TABLE_NAME_POINTS, Point.COLUMN_ID + " = ?", new String[]{String.valueOf(point.getId())});
        db.close();
    }

    public Point getPoint(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Point.TABLE_NAME_POINTS,
                new String[]{Point.COLUMN_ID, Point.COLUMN_LATITUDE, Point.COLUMN_LONGITUDE, Point.COLUMN_ALTITUDE, Point.COLUMN_VITESSE, Point.COLUMN_TEMPPOINT},
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
                cursor.getString(cursor.getColumnIndex(Point.COLUMN_TEMPPOINT)));

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
                points.add(point);
            } while (cursor.moveToNext());
        }
        db.close();

        return points;
    }

    public int updateTransaction(Point point, String tableName) {
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