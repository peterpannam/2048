package com.example.a2048;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
    public static final String SCORE_MESSAGE = "score";
    TextView scoreDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent = getIntent();
        String score  = intent.getStringExtra(SCORE_MESSAGE);
        scoreDisplay = findViewById(R.id.scoreDisplay);
        scoreDisplay.setText(score);

    }
}
