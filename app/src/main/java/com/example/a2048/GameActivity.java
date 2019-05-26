package com.example.a2048;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity{
    public static final String EXTRA_MESSAGE = "message";
    Difficulty difficulty;
    boolean gameStatus;
    float startX, startY, finishX, finishY;
    String[][] stringGrid;
    TextView zerozero, zeroone, zerotwo, onezero, oneone, onetwo, twozero,
            twoone, twotwo,  score;
    GameLogic2048 game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        String selection  = intent.getStringExtra(EXTRA_MESSAGE);
        difficulty = Difficulty.valueOf(selection);
        game = new GameLogic2048(difficulty);

        defineTextViews();
        setText();
    }

    private void defineTextViews() {
        zerozero = findViewById(R.id.zerozero);
        zeroone = findViewById(R.id.zeroone);
        zerotwo = findViewById(R.id.zerotwo);
        onezero = findViewById(R.id.onezero);
        oneone = findViewById(R.id.oneone);
        onetwo = findViewById(R.id.onetwo);
        twozero = findViewById(R.id.twozero);
        twoone = findViewById(R.id.twoone);
        twotwo = findViewById(R.id.twotwo);
        score = findViewById(R.id.score);
    }

    private void setText() {
        stringGrid = game.printGrid();
        zerozero.setText(stringGrid[0][0]);
        zeroone.setText(stringGrid[0][1]);
        zerotwo.setText(stringGrid[0][2]);
        onezero.setText(stringGrid[1][0]);
        oneone.setText(stringGrid[1][1]);
        onetwo.setText(stringGrid[1][2]);
        twozero.setText(stringGrid[2][0]);
        twoone.setText(stringGrid[2][1]);
        twotwo.setText(stringGrid[2][2]);
        score.setText(game.getScore());
    }

    public void moveLeft(){
        game.moveLeft();
        isGameOver();
        setText();
    }

    public void moveUp() {
        game.moveUp();
        isGameOver();
        setText();
    }

    public void moveDown() {
        game.moveDown();
        isGameOver();
        setText();
    }

    public void moveRight() {
        game.moveRight();
        isGameOver();
        setText();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = x;
                startY = y;
                break;
            case MotionEvent.ACTION_UP:
                finishX = x;
                finishY = y;
                getMove();
                break;
        }
        return true;
    }

    private void getMove() {
        float xDifference = Math.abs(startX - finishX);
        float yDifference = Math.abs(startY - finishY);
        if (xDifference > 50 || yDifference > 50) {
            if (xDifference > yDifference) {
                //check if left or right
                if (startX > finishX) {
                    moveLeft();
                } else moveRight();
            } else if (startY > finishY) {
                moveUp();
            } else moveDown();
        }
    }

    private void isGameOver() {
        gameStatus = game.checkGameOver();
        if (gameStatus){
            Log.i("gamestatus", "game over");
            startGameOverActivity();
        }else Log.i("gamestatus", "game continues");
    }

    private void startGameOverActivity() {
        //save score to db

        Intent intent = new Intent(this, GameOverActivity.class);
        intent.setType("text/plain");
        //will need to pass difficulty for db query later
        intent.putExtra(GameOverActivity.SCORE_MESSAGE, game.getScore());
        intent.putExtra(GameOverActivity.DIFFICULTY_MESSAGE, difficulty.toString());
        startActivity(intent);
    }
}
