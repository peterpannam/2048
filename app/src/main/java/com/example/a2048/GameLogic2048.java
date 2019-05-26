package com.example.a2048;

import android.util.Log;
import java.util.Random;

/**
 * logic class for the game
 */
class GameLogic2048 {
    private Difficulty difficulty;
    private int score = 0, difficultyNumber;
    private int grid[][], grid2[][];
    private Random random = new Random();

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

    /**
     * determines how many numbers spawn and how large those numbers are
     */
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

    /**
     * spawns initial numbers randomly onto the grid
     */
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
        Log.i("movement", "move left");
        moveHorizontal(0,2);
    }

    void moveRight() {
        Log.i("movement", "move right");
        moveHorizontal(2,0);
    }

    void moveDown() {
        Log.i("movement", "move up");
        moveVertical(2,0);
    }

    void moveUp() {
        Log.i("movement", "move down");
        moveVertical(0,2);
    }

    /**
     * logic for movement of left and right, works essentially by merging two cells together
     * same as logic for vertical movement only the grid is reversed
     */
    private void moveHorizontal(int y, int y2){
        for (int x=0; x < grid.length; x++){
            if (grid[x][1] > 0 && grid[x][1] == grid[x][y]){
                Log.i("movement", "case 1");
                grid[x][y] = grid[x][1] + grid[x][y];
                score+= grid[x][y];
                grid[x][1] = 0;
            }else if (grid[x][y] == 0 && grid[x][1] > 0){
                Log.i("movement", "case 2");
                grid[x][y] = grid[x][1];
                grid[x][1] = 0;
            }
            if (grid[x][y2] > 0 && grid[x][y2] == grid[x][1]){
                Log.i("movement", "case 3");
                grid[x][1] = grid[x][1] + grid[x][y2];
                score+= grid[x][1];
                grid[x][y2] = 0;
                continue;
            }else if (grid[x][1] == 0 && grid[x][y2] > 0){
                Log.i("movement", "case 4");
                grid[x][1] = grid[x][y2];
                grid[x][y2] = 0;
            }if (grid[x][y] == 0 && grid[x][1] >= 1){
                Log.i("movement", "case 5");
                grid[x][y] = grid[x][1];
                grid[x][1] = 0;
            }else if (grid[x][y] == grid[x][1] && grid[x][y] > 0){
                Log.i("movement", "case 6");
                grid[x][y] = grid[x][1] + grid[x][y];
                score+= grid[x][y];
                grid[x][1] = 0;
            }
        }
    }

    /**
     * logic for movement of up and down, works essentially by merging two cells together
     * same as logic for horizontal movement only the grid is reversed
     */
    private void moveVertical(int y, int y2){
        Log.i("movement", "enter verticle");
        for (int x=0; x < grid.length; x++){
            if (grid[1][x] > 0 && grid[1][x] == grid[y][x]){
                Log.i("movement", "case 1");
                grid[y][x] = grid[1][x] + grid[y][x];
                score+= grid[y][x];
                grid[1][x] = 0;
            }else if (grid[y][x] == 0 && grid[1][x] > 0){
                Log.i("movement", "case 2");
                grid[y][x] = grid[1][x];
                grid[1][x] = 0;
            }if (grid[y2][x] > 0 && grid[y2][x] == grid[1][x]){
                Log.i("movement", "case 3");
                grid[1][x] = grid[1][x] + grid[y2][x];
                score+= grid[1][x];
                grid[y2][x] = 0;
                continue;
            }else if (grid[1][x] == 0 && grid[y2][x] > 0){
                Log.i("movement", "case 4");
                grid[1][x] = grid[y2][x];
                grid[y2][x] = 0;
            }if (grid[y][x] == 0 && grid[1][x] >= 1){
                Log.i("movement", "case 5");
                grid[y][x] = grid[1][x];
                grid[1][x] = 0;
            }else if (grid[y][x] == grid[1][x] && grid[y][x] > 0){
                Log.i("movement", "case 6");
                grid[y][x] = grid[y][x] + grid[1][x];
                score+= grid[y][x];
                grid[1][x] = 0;
            }
        }
    }

    /**
     * creates new number, size of number depends on the difficulty
     */
    private void createNewNumber() {
        int z = random.nextInt(3), y = random.nextInt(3);

        while (grid[z][y] != 0){
            z = random.nextInt(3);
            y = random.nextInt(3);
        }
        grid[z][y] = (int)Math.pow(2, random.nextInt(difficultyNumber));
    }

    /**
     * returns a specific row of the grid
     */
    int[] getGrid(int gridRow){
        int[] copiedGrid = new int[3];
        for (int x = 0; x < grid.length; x++ ){
            if (x == gridRow){
                for (int y = 0; x < grid.length; x++){
                    copiedGrid[y] = grid[x][y];
                }
                return copiedGrid;
            }
        }return null;
    }

    /**
     * returns the score as a string
     */
    String getScore(){return String.valueOf(score);}

    /**
     * returns a 2d string of the game grid
     */
    String[][] printGrid() {
        String[][] stringGrid = new String[3][3];
        for (int x = 0; x < grid.length; x++) {
            stringGrid[x][0] =String.valueOf(grid[x][0]);
            stringGrid[x][1] =String.valueOf(grid[x][1]);
            stringGrid[x][2] =String.valueOf(grid[x][2]);
        }
        return stringGrid;
    }

    /**
     * method that checks if their are any more moves left to make
     */
    boolean checkGameOver() {
        Log.i("game status", "checking if game is over");
        for (int[] aGrid : grid) {
            for (int y = 0; y < grid.length; y++) { //segments determines if there are any 0"s remaining
                if (aGrid[y] == 0) {                //if so stops adds new number to the field then ends method
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

    /**
     * copys the current grid to allow for a comparison
     */
    private void deepCopy(int[][] original, int[][] copy) {
        for (int x = 0; x < grid.length; x++){
            System.arraycopy(original[x], 0, copy[x], 0, grid.length);
        }
    }
}
