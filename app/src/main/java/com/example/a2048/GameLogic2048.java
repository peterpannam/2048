package com.example.a2048;

import android.util.Log;

import java.util.Arrays;
import java.util.Random;

// TODO: 14/04/2019  create game over (find better way)
// TODO: 14/04/2019  create a score (broken)
// TODO: 14/04/2019  simplify logic (can probably just one method)
// TODO: fix addition to middle column

class GameLogic2048 {
    private Difficulty difficulty;
    private int score = 0;
    private int grid[][];
    private Random random = new Random();
    private boolean stateChange = false;

    GameLogic2048(){
        this.grid = new int[4][4];
        difficulty = Difficulty.EASY;
        populateGrid();
    }

    GameLogic2048(int[][] grid){
        this.grid = grid;
    }

    GameLogic2048(Difficulty difficulty){
        this.difficulty = difficulty;
        this.grid = new int[4][4];
        populateGrid();
    }

    private void populateGrid() {
        for (int x = 0; x <2; x++){
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
        Log.i("movement", "enter verticle");
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
        if (stateChange){
            createNewNumber();
        }
        //else getGameOver();
        stateChange = false;
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
        if (stateChange){
            createNewNumber();
        }
        //else getGameOver();
        stateChange = false;
    }

    private void createNewNumber() {
        int gameOverCount = 0, z = random.nextInt(3), y = random.nextInt(3);
        for (int[] aGrid : grid) {
            if (aGrid[0] != 0 && aGrid[1] != 0 && aGrid[2] != 0){
                gameOverCount++;
                System.out.println(gameOverCount);
            }
        }
        if (gameOverCount == 3){ printGrid();
            System.exit(0);
        }
        while (grid[z][y] != 0){
            z = random.nextInt(3);
            y = random.nextInt(3);
        }
        grid[z][y] = 1;
        System.out.println(gameOverCount);
    }

    public int[][] getGrid(){
        return grid;
     }

    public int getScore(){return score;}

    String[][] printGrid() {
        String[][] stringGrid = new String[4][4];
        for (int x = 0; x < grid.length; x++) {
            stringGrid[x][0] =String.valueOf(grid[x][0]);
            stringGrid[x][1] =String.valueOf(grid[x][1]);
            stringGrid[x][2] =String.valueOf(grid[x][2]);
        }
        return stringGrid;
    }

    private void getGameOver() {
        int[][] gridTwo = grid;
        moveUp();
        moveDown();
        moveLeft();
        moveRight();
        if (Arrays.deepEquals(gridTwo, grid)){
            System.out.println("game over");
        }else grid = gridTwo;
    }

    private void merge(){

    }
}
