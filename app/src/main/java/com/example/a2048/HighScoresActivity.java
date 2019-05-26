package com.example.a2048;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class HighScoresActivity extends AppCompatActivity {
    public static final String DIFFICULTY_MESSAGE = "difficulty";
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        Intent intent = getIntent();
        String difficulty = intent.getStringExtra(DIFFICULTY_MESSAGE);

        try {
            SQLiteOpenHelper scoreDatabaseHelper = new ScoreDatabaseHelper(this);
            db = scoreDatabaseHelper.getReadableDatabase();
            cursor = db.query("SCORE",
                                            new String[] {"_id", "SCORE"},
                                            "DIFFICULTY = ?",
                                            new String[] {difficulty},
                                            null, null, "SCORE DESC");

            CursorAdapter listAdapter = new SimpleCursorAdapter(this, R.layout.list_score,
                    cursor, new String[]{"SCORE"}, new int[]{R.id.txt}, 0 );
            ListView list = findViewById(R.id.list);
            list.setAdapter(listAdapter);

        } catch (SQLException e){
            Toast toast = Toast.makeText(this, "failed to show high scores", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}