package com.hnvist.apptest1.service;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hnvist.apptest1.BaseApplication;
import com.hnvist.apptest1.pojo.StateSec;
import com.hnvist.apptest1.sql.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLEnvironmentService {
    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase db;

    public SQLEnvironmentService(){
        dataBaseHelper = new DataBaseHelper(BaseApplication.context, "environmentInfo", null, 1);
        db = dataBaseHelper.getWritableDatabase();
    }

    public void add(String table, StateSec stateSec){
        int max = max(table);
        Log.e("count", table + ":" + max + "");
        if (max > 50){
            int min = max - 49;
            int a = db.delete(table, "id<=?", new String[]{String.valueOf(min)});
        }
        ContentValues values = new ContentValues();
        values.put("number", stateSec.getNumber());
        values.put("time", stateSec.getTime());
        db.insert(table, null, values);
    }

    public int max(String table){
        Cursor cursor = db.query(table, new String[]{"id"}, null, null, null, null, null);
        if (cursor.moveToLast()){
            int a = cursor.getInt(cursor.getColumnIndex("id"));
            return a;
        }
        return 0;
    }

    public List<StateSec> list(String table){
        List data = new ArrayList();
        Cursor cursor = db.query(table, new String[]{"number", "time"}, null, null, null, null, null);

        while (cursor.moveToNext()){
            StateSec stateSec = new StateSec();
            stateSec.setNumber(cursor.getInt(cursor.getColumnIndex("number")));
            stateSec.setTime(cursor.getString(cursor.getColumnIndex("time")));
            data.add(stateSec);
        }
        return data;
    }
}
