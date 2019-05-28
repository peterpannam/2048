package com.example.a2048;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;
import java.util.*;

/**
 * Controller class for game
 */
public class GameActivity extends AppCompatActivity{
    public static final String EXTRA_MESSAGE = "message", scoreMessage = "Score: ", COLOUR_STRING = "colour";
    private Difficulty difficulty;
    private ColourScheme colourScheme;
    private float startX, startY, finishX, finishY;
    TextView zerozero, zeroone, zerotwo, onezero, oneone, onetwo, twozero,
            twoone, twotwo, score;
    private List<TextView> textViewList = new ArrayList<>();
    private GameLogic2048 game;
    private Dictionary<String, String> colorScheme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        startService(new Intent(this, backgroundMusic.class));

        colorScheme = new Hashtable<>();
        if(savedInstanceState == null){
            Intent intent = getIntent();
            String selection  = intent.getStringExtra(EXTRA_MESSAGE);
            String scheme = intent.getStringExtra(COLOUR_STRING);
            colourScheme = ColourScheme.valueOf(scheme);
            difficulty = Difficulty.valueOf(selection);
            game = new GameLogic2048(difficulty);
            setColourScheme();
        }else {
            int[][] grid = new int[3][3]; //have to stitch back together
            int x = 0;                    // grid since 2d grids can't be saved
            while ( x < 2){
                int savedGrid[] = savedInstanceState.getIntArray("grid" + x);
                for (int y = 0; y < 2; y++){
                        if (savedGrid != null) {
                            grid[x][y] = savedGrid[x];
                        }else grid[x][y] = 0;
                }
                x++;
            }
            game = new GameLogic2048(grid,
                    savedInstanceState.getInt("score"),
                    Difficulty.valueOf(savedInstanceState.getString("difficulty")));
        }
        defineTextViews();
        setText();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(new Intent(this, backgroundMusic.class));
    }

    /**
     * sets the color scheme of the game using a switch
     */
    private void setColourScheme() {
        switch (colourScheme){
            case RED:
                colorScheme.put("0", "#FFFFFF");
                colorScheme.put("1", "#FFC400");
                colorScheme.put("2", "#FFB330");
                colorScheme.put("4", "#FF9A00");
                colorScheme.put("8", "#FF6F00");
                colorScheme.put("16", "#FF6A52");
                colorScheme.put("32", "#FF5136");
                colorScheme.put("64", "#FF2200");
                colorScheme.put("128", "#08C2D0");
                colorScheme.put("256", "#009AFF");
                break;
            case BLUE:
                colorScheme.put("0", "#FFFFFF");
                colorScheme.put("1", "#06FFD6");
                colorScheme.put("2", "#06F8FF");
                colorScheme.put("4", "#06DFFF");
                colorScheme.put("8", "#06CEFF");
                colorScheme.put("16", "#06ADFF");
                colorScheme.put("32", "#069CFF");
                colorScheme.put("64", "#067BFF");
                colorScheme.put("128", "#0659FF");
                colorScheme.put("256", "#143BFF");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("difficulty", difficulty.toString());
        savedInstanceState.putInt("score", Integer.parseInt(game.getScore()));
        savedInstanceState.putIntArray("grid0", game.getGrid(0));
        savedInstanceState.putIntArray("grid1", game.getGrid(1));
        savedInstanceState.putIntArray("grid2", game.getGrid(2));
    }

    /**
     * initializes textviews and adds them an array list
     */
    private void defineTextViews() {
        textViewList.add(zerozero = findViewById(R.id.zerozero));
        textViewList.add(zeroone = findViewById(R.id.zeroone));
        textViewList.add(zerotwo = findViewById(R.id.zerotwo));
        textViewList.add(onezero = findViewById(R.id.onezero));
        textViewList.add(oneone = findViewById(R.id.oneone));
        textViewList.add(onetwo = findViewById(R.id.onetwo));
        textViewList.add(twozero = findViewById(R.id.twozero));
        textViewList.add(twoone = findViewById(R.id.twoone));
        textViewList.add(twotwo = findViewById(R.id.twotwo));
        score = findViewById(R.id.score);
    }

    /**
     * sets the score and passes the grid it styleCell()
     */
    @SuppressLint("SetTextI18n")
    private void setText() {
        int length = 0;
        String[][] stringGrid = game.printGrid();
        for (String[] aStringGrid : stringGrid) {
            for (int y = 0; y < stringGrid.length; y++) {
                System.out.println(length);
                styleCell(aStringGrid[y], length);
                length++;
            }
        }
        score.setText( scoreMessage + game.getScore());
    }

    /**
     * uses switch to determine colour of the cell then sets the view accordingly
     */
    private void styleCell(String cell, int length) {
        switch (cell){
            case "0":
                textViewList.get(length).setText("  ");
                textViewList.get(length).setBackgroundColor(Color.parseColor(colorScheme.get("0")));
                break;
            case "1":
                textViewList.get(length).setText(cell);
                textViewList.get(length).setBackgroundColor(Color.parseColor(colorScheme.get("1")));
                break;
            case "2":
                textViewList.get(length).setText(cell);
                textViewList.get(length).setBackgroundColor(Color.parseColor(colorScheme.get("2")));
                break;
            case "4":
                textViewList.get(length).setText(cell);
                textViewList.get(length).setBackgroundColor(Color.parseColor(colorScheme.get("4")));
                break;
            case "8":
                textViewList.get(length).setText(cell);
                textViewList.get(length).setBackgroundColor(Color.parseColor(colorScheme.get("8")));
                break;
            case "16":
                textViewList.get(length).setText(cell);
                textViewList.get(length).setBackgroundColor(Color.parseColor(colorScheme.get("16")));
                break;
            case "32":
                textViewList.get(length).setText(cell);
                textViewList.get(length).setBackgroundColor(Color.parseColor(colorScheme.get("32")));
                break;
            case "64":
                textViewList.get(length).setText(cell);
                textViewList.get(length).setBackgroundColor(Color.parseColor(colorScheme.get("64")));
                break;
            case "128":
                textViewList.get(length).setText(cell);
                textViewList.get(length).setBackgroundColor(Color.parseColor(colorScheme.get("128")));
                break;
            case "256":
                textViewList.get(length).setText(cell);
                textViewList.get(length).setBackgroundColor(Color.parseColor(colorScheme.get("256")));
                break;
            default:
                textViewList.get(length).setText(cell);
                textViewList.get(length).setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
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

    /**
     * measures distance of a swipe
     */
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

    /**
     * determines the direction of the swipe
     */
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

    /**
     * checks if game is over, starts GameOverActivity if there are no moves left
     */
    private void isGameOver() {
        boolean gameStatus = game.checkGameOver();
        if (gameStatus){
            Log.i("gamestatus", "game over");
            Intent intent = new Intent(this, GameOverActivity.class);
            intent.setType("text/plain");
            //will need to pass difficulty for db query later
            intent.putExtra(GameOverActivity.SCORE_MESSAGE, game.getScore());
            intent.putExtra(GameOverActivity.DIFFICULTY_MESSAGE, difficulty.toString());
            startActivity(intent);
        }else Log.i("gamestatus", "game continues");
    }
}
