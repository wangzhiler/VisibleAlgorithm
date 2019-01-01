import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class MazeData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';

    private int entranceX, entranceY;
    private int exitX, exitY;

    public boolean[][] visited;
    public boolean[][] path;
    public boolean[][] result;


    private int row, col; // N为行数 M为列数
    private char[][] maze;

    public MazeData(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("Filename cannot be null");
        }

        Scanner scanner = null;

        try {
            File file = new File(fileName);
            if (!file.exists()) {
                throw new IllegalArgumentException("File " + fileName + " not exists");
            }
            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");

            // 读取第一行
            String nmline = scanner.nextLine();
            String[] nm = nmline.trim().split("\\s+");
            row = Integer.parseInt(nm[0]);
            col = Integer.parseInt(nm[1]);

            maze = new char[row][col];
            visited = new boolean[row][col];
            path = new boolean[row][col];
            result = new boolean[row][col];

            // 读后续row行
            for (int i = 0; i < row; i++) {
                String line = scanner.nextLine();

                // 每行保证有col个字符
                if (line.length() != col) {
                    throw new IllegalArgumentException("Maze file " + fileName + " is invalid");
                }

                for (int j = 0; j < col; j++) {
                    maze[i][j] = line.charAt(j);
                    visited[i][j] = false;
                    path[i][j] = false;
                    result[i][j] = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        entranceX = 1;
        entranceY = 0;
        exitX = row - 2;
        exitY = col - 1;

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
