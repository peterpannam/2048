package com.example.a2048;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GameOverActivity extends AppCompatActivity {
    public static final String SCORE_MESSAGE = "score";
    public static final String DIFFICULTY_MESSAGE = "difficulty";
    TextView scoreDisplay;
    String score, difficulty, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent = getIntent();
        score  = intent.getStringExtra(SCORE_MESSAGE);
        difficulty = intent.getStringExtra(DIFFICULTY_MESSAGE);
        scoreDisplay = findViewById(R.id.scoreDisplay);
        scoreDisplay.setText(score);

        SQLiteOpenHelper scoreDatabaseHelper = new ScoreDatabaseHelper(this);
        try {
            SQLiteDatabase db = scoreDatabaseHelper.getWritableDatabase();
            ContentValues scoreValues = new ContentValues();
            scoreValues.put("SCORE", Integer.parseInt(score));
            scoreValues.put("NAME", name);
            scoreValues.put("DIFFICULTY", difficulty);
            db.insert("SCORE", null, scoreValues);
            db.close();
        }catch (SQLException e){
            Toast toast = Toast.makeText(this, "failed to store", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void returnHome(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void startHighScore(View view) {
        Intent intent = new Intent(this, HighScoresActivity.class);
        intent.setType("text/plain");
        intent.putExtra(GameOverActivity.DIFFICULTY_MESSAGE, difficulty);
        startActivity(intent);
    }
}
