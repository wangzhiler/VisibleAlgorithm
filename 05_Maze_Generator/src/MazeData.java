import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class MazeData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';


    public boolean[][] visited;
    public boolean[][] path;
    public boolean[][] result;


    private int row, col; // N为行数 M为列数
    public char[][] maze;

    private int entranceX, entranceY;
    private int exitX, exitY;

    public MazeData(int row, int col) {
        if (row % 2 == 0 || col % 2 == 0) {
            throw new IllegalArgumentException("row and col are odds");
        }

        this.row = row;
        this.col = col;

        maze = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i % 2 == 1 && j % 2 == 1) {
                    maze[i][j] = ROAD;
                } else {
                    maze[i][j] = WALL;
                }
            }
        }


        entranceX = 1;
        entranceY = 0;
        exitX = row - 2;
        exitY = col - 1;

        maze[entranceX][entranceY] = ROAD;
        maze[exitX][exitY] = ROAD;
    }

    public int getEntranceX() {
        return entranceX;
    }

    public int getEntranceY() {
        return entranceY;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getMaze(int i, int j) {
        if (!inArea(i, j)) {
            throw new IllegalArgumentException("i or j is out of index in getMaze");
        }
        return maze[i][j];
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }

    public void print() {
        System.out.println(row + " " + col);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
        return;
    }
}
