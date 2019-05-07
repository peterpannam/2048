package com.example.a2048;

import java.util.Random;

public class OldLogic {
    private int score = 0;
    private int grid[][];
    private Random random = new Random();
    private boolean stateChange = false;

    OldLogic(){
        this.grid = new int[3][3];
        populateGrid();
    }

   OldLogic(int[][] grid){
        this.grid = grid;
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
        for (int x=0; x < grid.length; x++){
            if (grid[x][1] > 0 && grid[x][1] == grid[x][y]){
                grid[x][0] = grid[x][1] + grid[x][y];
                score+= grid[x][1] + grid[x][1];
                grid[x][1] = 0;
                stateChange = true;
            }else if (grid[x][y] == 0 && grid[x][1] > 0){
                grid[x][y] = grid[x][1];
                grid[x][1] = 0;
                stateChange = true;
            }
            if (grid[x][y2] > 0 && grid[x][y2] == grid[x][1]){
                grid[x][1] = grid[x][1] + grid[x][y2];
                score+= grid[x][1] + grid[x][1];
                grid[x][y2] = 0;
                stateChange = true;
                break;
            }else if (grid[x][1] == 0 && grid[x][y2] > 0){
                grid[x][1] = grid[x][y2];
                grid[x][y2] = 0;
                stateChange = true;
            }if (grid[x][y] == 0 && grid[x][1] >= 1){
                grid[x][y] = grid[x][1];
                grid[x][1] = 0;
                stateChange = true;
            }else if (grid[x][y] == grid[x][1] && grid[x][y] > 0){
                grid[x][y] = grid[x][1] + grid[x][y];
                score+= grid[x][1] + grid[x][1];
                grid[x][1] = 0;
                stateChange = true;
            }
        }
        if (stateChange){
            createNewNumber();
        }stateChange = false;
        printGrid();
    }

    private void moveVertical(int y, int y2){
        for (int x=0; x < grid.length; x++){
            if (grid[1][x] > 0 && grid[1][x] == grid[y][x]){
                grid[y][x] = grid[1][x] + grid[y][x];
                score+= grid[1][x] + grid[1][x];
                grid[1][x] = 0;
                stateChange = true;
            }else if (grid[y][x] == 0 && grid[1][x] > 0){
                grid[y][x] = grid[1][x];
                grid[1][x] = 0;
                stateChange = true;
            }if (grid[y2][x] > 0 && grid[y2][x] == grid[1][x]){
                grid[1][x] = grid[1][x] + grid[0][x];
                score+= grid[1][x] + grid[1][x];
                grid[y2][x] = 0;
                stateChange = true;
                break;
            }else if (grid[1][x] == 0 && grid[y2][x] > 0){
                grid[1][x] = grid[y2][x];
                grid[y2][x] = 0;
                stateChange = true;
            }if (grid[y][x] == 0 && grid[1][x] >= 1){
                grid[y][x] = grid[1][x];
                grid[1][x] = 0;
                stateChange = true;
            }else if (grid[y][x] == grid[1][x] && grid[y][x] > 0){
                grid[y][x] = grid[y][x] + grid[1][x];
                score+= grid[1][x] + grid[1][x];
                grid[1][x] = 0;
                stateChange = true;
            }
        }
        if (stateChange){
            createNewNumber();
        }stateChange = false;
        printGrid();
    }

    private void createNewNumber() {
        int gameOverCount = 0, z = random.nextInt(3), y = random.nextInt(3);
        for (int[] aGrid : grid) {
            if (aGrid[0] != 0 && aGrid[1] != 0 && aGrid[2] != 0){
                gameOverCount++;
            }
        }
        if (gameOverCount == 3){ printGrid();
            System.out.println("game over");
            System.exit(0);
        }
        while (grid[z][y] != 0){
            z = random.nextInt(3);
            y = random.nextInt(3);
        }
        grid[z][y] = 1;
    }

    public int[][] getGrid(){
        return grid;
    }

    String[][] printGrid() {
        String[][] stringGrid = new String[3][3];
        for (int x = 0; x < grid.length; x++) {
            stringGrid[x][0] =String.valueOf(grid[x][0]);
            stringGrid[x][1] =String.valueOf(grid[x][1]);
            stringGrid[x][2] =String.valueOf(grid[x][2]);
        }
        return stringGrid;
    }
}
