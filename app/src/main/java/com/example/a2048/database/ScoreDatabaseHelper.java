package com.example.a2048.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ScoreDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "score";
    private static final int DB_VERSION = 1;

    public ScoreDatabaseHelper(Context context){
        super(context, DB_NAME, null ,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE SCORE ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "SCORE INTEGER, "
                + "NAME TEXT, "
                + "DIFFICULTY TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }
}
