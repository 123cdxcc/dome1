package com.hnvist.apptest1.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String airTemperature = "create table airTemperature(id INTEGER primary key autoincrement not null, number int(50), time text(10))";
        String airHumidity = "create table airHumidity(id INTEGER primary key autoincrement not null, number int(50), time text(10))";
        String light = "create table light(id INTEGER primary key autoincrement not null, number int(50), time text(10))";
        String soilTemperature = "create table soilTemperature(id INTEGER primary key autoincrement not null, number int(50), time text(10))";
        String soilHumidity = "create table soilHumidity(id INTEGER primary key autoincrement not null, number int(50), time text(10))";
        String co2 = "create table co2(id INTEGER primary key autoincrement not null, number int(50), time text(10))";

        db.execSQL(airTemperature);
        db.execSQL(airHumidity);
        db.execSQL(light);
        db.execSQL(soilTemperature);
        db.execSQL(soilHumidity);
        db.execSQL(co2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
