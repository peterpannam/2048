package com.example.a2048;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ScoreDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "score";
    private static final int DB_VERSION = 1;

    ScoreDatabaseHelper(Context context){
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

    private static void insertScore(SQLiteDatabase db, String name, String difficulty, int score){
        ContentValues scoreValues = new ContentValues();
        scoreValues.put("SCORE", score);
        scoreValues.put("NAME", name);
        scoreValues.put("DIFFICULTY", difficulty);
        db.insert("SCORE", null, scoreValues);
    }


}
