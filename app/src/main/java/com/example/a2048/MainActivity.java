package com.example.a2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    public static final String EXTRA_MESSAGE = "message";
    Difficulty difficulty;
    int[][] grid;
    boolean gameStatus;
    float startX, startY, finishX, finishY;
    String[][] stringGrid;
    TextView zerozero, zeroone, zerotwo, zerothree, onezero, oneone, onetwo, onethree, twozero,
            twoone, twotwo, twothree, threezero, threeone, threetwo, threethree;
    GameLogic2048 game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent intent = getIntent();
        //difficulty = Difficulty.valueOf(intent.getStringExtra(EXTRA_MESSAGE));

        game = new GameLogic2048(difficulty);

        zerozero = findViewById(R.id.zerozero);
        zeroone = findViewById(R.id.zeroone);
        zerotwo = findViewById(R.id.zerotwo);
        zerothree = findViewById(R.id.zerothree);
        onezero = findViewById(R.id.onezero);
        oneone = findViewById(R.id.oneone);
        onetwo = findViewById(R.id.onetwo);
        onethree = findViewById(R.id.onethree);
        twozero = findViewById(R.id.twozero);
        twoone = findViewById(R.id.twoone);
        twotwo = findViewById(R.id.twotwo);
        twothree = findViewById(R.id.twothree);
        threezero = findViewById(R.id.threezero);
        threeone = findViewById(R.id.threeone);
        threetwo = findViewById(R.id.threetwo);
        threethree = findViewById(R.id.threethree);
        setText();
    }

    private void setText() {
        stringGrid = game.printGrid();
        zerozero.setText(stringGrid[0][0]);
        zeroone.setText(stringGrid[0][1]);
        zerotwo.setText(stringGrid[0][2]);
        zerothree.setText(stringGrid[0][3]);
        onezero.setText(stringGrid[1][0]);
        oneone.setText(stringGrid[1][1]);
        onetwo.setText(stringGrid[1][2]);
        onethree.setText(stringGrid[1][3]);
        twozero.setText(stringGrid[2][0]);
        twoone.setText(stringGrid[2][1]);
        twotwo.setText(stringGrid[2][2]);
        twothree.setText(stringGrid[2][3]);
        threezero.setText(stringGrid[3][0]);
        threeone.setText(stringGrid[3][1]);
        threetwo.setText(stringGrid[3][2]);
        threethree.setText(stringGrid[3][3]);
    }

    public void moveLeft(){
        game.moveLeft();
        setText();
    }

    public void moveUp() {
        game.moveUp();
        setText();
    }

    public void moveDown() {
        game.moveDown();
        setText();
    }

    public void moveRight() {
        game.moveRight();
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
}
