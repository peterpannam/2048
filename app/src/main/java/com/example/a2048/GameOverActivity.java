package com.example.a2048;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2048.database.ScoreDatabaseHelper;

/**
 * runs once game is over, allows user to share score and view the high scores
 */
public class GameOverActivity extends AppCompatActivity {
    public static final String SCORE_MESSAGE = "score";
    public static final String DIFFICULTY_MESSAGE = "difficulty";
    private String score, difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent = getIntent();                    //retrieving info from game class
        score  = intent.getStringExtra(SCORE_MESSAGE);
        difficulty = intent.getStringExtra(DIFFICULTY_MESSAGE);

        TextView scoreDisplay = findViewById(R.id.scoreDisplay);
        scoreDisplay.setText(score);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        EditText nameDisplay = findViewById(R.id.nameDisplay);
        String name = nameDisplay.toString();
        if (name.equals("Enter Name Here")){
            name = "unknown";
        }
        //storing score difficulty and name to the database
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

    public void shareToMedia(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, score);
        startActivity(intent);
    }
}
