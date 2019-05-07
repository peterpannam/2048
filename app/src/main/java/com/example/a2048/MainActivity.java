package com.example.a2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int[][] grid;
    String[][] stringGrid;
    TextView zerozero, zeroone, zerotwo, onezero, oneone, onetwo, twozero, twoone, twotwo;
    GameLogic2048 game;
    //OldLogic game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new GameLogic2048();
        //game = new OldLogic();

        zerozero = findViewById(R.id.zerozero);
        zeroone = findViewById(R.id.zeroone);
        zerotwo = findViewById(R.id.zerotwo);
        onezero = findViewById(R.id.onezero);
        oneone = findViewById(R.id.oneone);
        onetwo = findViewById(R.id.onetwo);
        twozero = findViewById(R.id.twozero);
        twoone = findViewById(R.id.twoone);
        twotwo = findViewById(R.id.twotwo);
        setText();

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
    }

    public void moveLeft(View view){
        game.moveLeft();
        setText();
    }

    public void moveUp(View view) {
        game.moveUp();
        setText();
    }

    public void moveDown(View view) {
        game.moveDown();
        setText();
    }

    public void moveRight(View view) {
        game.moveRight();
        setText();
    }
}
