import java.awt.*;
import java.util.LinkedList;
import java.util.Stack;

public class AlgoVisualizer {

    private static int DELAY = 5;

    private static int blockSide = 8;

    private MazeData data;
    private AlgoFrame frame;


    // 四个方向
    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};


    public AlgoVisualizer(int row, int col) {

        // 初始化数据
        data = new MazeData(row, col);
        int sceneHeight = data.getRow() * blockSide;
        int sceneWidth = data.getCol() * blockSide;

        // 初始化视图
        EventQueue.invokeLater(()->{
            frame = new AlgoFrame("Random Maze Generation Visualization", sceneHeight, sceneWidth);

            new Thread(() -> {
                run();
            }).start();
        });

    }

    private void run() {
        setData();
    }


    private void setData() {
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        AlgoVisualizer vis = new AlgoVisualizer(101,101);
    }
}
