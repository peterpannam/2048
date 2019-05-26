package com.example.a2048;

import android.util.Log;
import java.util.Random;

// TODO: 14/04/2019  simplify logic (can probably just one method)

class GameLogic2048 {
    private Difficulty difficulty;
    private int score = 0, difficultyNumber;
    private int grid[][], grid2[][];
    private Random random = new Random();
    private boolean stateChange = false, runningStatus;

    GameLogic2048(){
        this.grid = new int[3][3];
        difficulty = Difficulty.EASY;
        populateGrid();
    }

    GameLogic2048(int[][] grid, int score, Difficulty difficulty){
        this.grid = grid;
        this.score = score;
        this.difficulty = difficulty;
        setDifficultyNumber();
    }

    GameLogic2048(Difficulty difficulty){
        this.difficulty = difficulty;
        this.grid = new int[3][3];
        this.grid2= new int[3][3];
        setDifficultyNumber();
        populateGrid();
    }

    private void setDifficultyNumber() {
        switch (difficulty){
            case EASY:
                difficultyNumber = 5;
                break;
            case MEDIUM:
                difficultyNumber = 4;
                break;
            case HARD:
                difficultyNumber = 3;
                break;
            case EXPERT:
                difficultyNumber = 2;
                break;
        }
    }

    private void populateGrid() {

        for (int x = 0; x <difficultyNumber; x++){
            int z = random.nextInt(3);
            int y = random.nextInt(3);
            while (grid[z][y] == 1){
                z = random.nextInt(3);
                y = random.nextInt(3);
            }
            grid[z][y] = 1;
        }
    }

    void moveLeft() {
        System.out.println("move left");
        moveHorizontal(0,2);
    }

    void moveRight() {
        System.out.println("move right");
        moveHorizontal(2,0);
    }

    void moveDown() {
        System.out.println("move down");
        moveVertical(2,0);
    }

    void moveUp() {
        System.out.println("move up");
        moveVertical(0,2);
    }

    private void moveHorizontal(int y, int y2){
        Log.i("movement", "enter horizontal");
        for (int x=0; x < grid.length; x++){
            if (grid[x][1] > 0 && grid[x][1] == grid[x][y]){
                Log.i("movement", "case 1");
                grid[x][y] = grid[x][1] + grid[x][y];
                score+= grid[x][y];
                grid[x][1] = 0;
                stateChange = true;
            }else if (grid[x][y] == 0 && grid[x][1] > 0){
                Log.i("movement", "case 2");
                grid[x][y] = grid[x][1];
                grid[x][1] = 0;
                stateChange = true;
            }
            if (grid[x][y2] > 0 && grid[x][y2] == grid[x][1]){
                Log.i("movement", "case 3");
                grid[x][1] = grid[x][1] + grid[x][y2];
                score+= grid[x][1];
                grid[x][y2] = 0;
                stateChange = true;
                //break;
                continue;
            }else if (grid[x][1] == 0 && grid[x][y2] > 0){
                Log.i("movement", "case 4");
                grid[x][1] = grid[x][y2];
                grid[x][y2] = 0;
                stateChange = true;
            }if (grid[x][y] == 0 && grid[x][1] >= 1){
                Log.i("movement", "case 5");
                grid[x][y] = grid[x][1];
                grid[x][1] = 0;
                stateChange = true;
            }else if (grid[x][y] == grid[x][1] && grid[x][y] > 0){
                Log.i("movement", "case 6");
                grid[x][y] = grid[x][1] + grid[x][y];
                score+= grid[x][y];
                grid[x][1] = 0;
                stateChange = true;
            }
        }
    }

    private void moveVertical(int y, int y2){
        Log.i("movement", "enter verticle");
        for (int x=0; x < grid.length; x++){
            if (grid[1][x] > 0 && grid[1][x] == grid[y][x]){
                Log.i("movement", "case 1");
                grid[y][x] = grid[1][x] + grid[y][x];
                score+= grid[y][x];
                grid[1][x] = 0;
                stateChange = true;
            }else if (grid[y][x] == 0 && grid[1][x] > 0){
                Log.i("movement", "case 2");
                grid[y][x] = grid[1][x];
                grid[1][x] = 0;
                stateChange = true;
            }if (grid[y2][x] > 0 && grid[y2][x] == grid[1][x]){
                Log.i("movement", "case 3");
                grid[1][x] = grid[1][x] + grid[y2][x];
                score+= grid[1][x];
                grid[y2][x] = 0;
                stateChange = true;
                //break;
                continue;
            }else if (grid[1][x] == 0 && grid[y2][x] > 0){
                Log.i("movement", "case 4");
                grid[1][x] = grid[y2][x];
                grid[y2][x] = 0;
                stateChange = true;
            }if (grid[y][x] == 0 && grid[1][x] >= 1){
                Log.i("movement", "case 5");
                grid[y][x] = grid[1][x];
                grid[1][x] = 0;
                stateChange = true;
            }else if (grid[y][x] == grid[1][x] && grid[y][x] > 0){
                Log.i("movement", "case 6");
                grid[y][x] = grid[y][x] + grid[1][x];
                score+= grid[y][x];
                grid[1][x] = 0;
                stateChange = true;
            }
        }
    }

    private void createNewNumber() {
        int z = random.nextInt(3), y = random.nextInt(3);

        while (grid[z][y] != 0){
            z = random.nextInt(3);
            y = random.nextInt(3);
        }
        grid[z][y] = (int)Math.pow(2, random.nextInt(difficultyNumber));
    }

    public int[][] getGrid(){
        return grid;
     }

    String getScore(){return String.valueOf(score)  ;}

    String[][] printGrid() {
        String[][] stringGrid = new String[3][3];
        for (int x = 0; x < grid.length; x++) {
            stringGrid[x][0] =String.valueOf(grid[x][0]);
            stringGrid[x][1] =String.valueOf(grid[x][1]);
            stringGrid[x][2] =String.valueOf(grid[x][2]);
        }
        return stringGrid;
    }

    boolean checkGameOver() {
        Log.i("game status", "checking if game is over");

        for (int[] aGrid : grid) {
            for (int y = 0; y < grid.length; y++) {
                if (aGrid[y] == 0) {
                    System.out.println("hello");
                    createNewNumber();
                    return false;
                }
            }
        }

        deepCopy(grid, grid2);
        moveUp();
        moveRight();
        moveDown();
        moveLeft();

        for (int x = 0; x < grid.length; x++){
            for (int y = 0; y < grid.length; y++){
                Log.i("comparason", "grid: " + grid[x][y] + " grid 2: " +  grid2[x][y]);
                if (grid[x][y] != grid2[x][y]){
                    Log.i("gamestatus", "arrays not equal");
                    deepCopy(grid2, grid);
                    createNewNumber();
                    return false;
                }
            }
        }
        Log.i("gamestatus", "arrays are equal");
        return true;
    }

    private void deepCopy(int[][] original, int[][] copy) {
        for (int x = 0; x < grid.length; x++){
            for (int y = 0; y < grid.length; y++){
                copy[x][y] = original[x][y];
            }
        }
    }
}
