package MonteCarlo_PI;

import util.AlgoVisHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;

public class AlgoVisualizer {

    private static int DELAY = 20;

    private MonteCarloPIData data;
    private AlgoFrame frame;  // 视图
    private int N;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        if (sceneWidth != sceneHeight) {
            throw new IllegalArgumentException("");
        }

        // 初始化数据
        this.N = N;
        Circle circle = new Circle(sceneWidth / 2, sceneHeight / 2, sceneWidth / 2);
        data = new MonteCarloPIData(circle);

        // 初始化视图
        EventQueue.invokeLater(()->{
            frame = new AlgoFrame("Get PI with Monte Carlo", sceneWidth, sceneHeight);


            new Thread(() -> {
                run();
            }).start();

        });
    }

    // 动画逻辑
    private void run() {
        for (int i = 0; i < N; i++) {
            if (i%100==0) {
                frame.render(data);
                AlgoVisHelper.pause(DELAY);

                System.out.println(data.estimatePI());
            }

            int x = (int) (Math.random() * frame.getCanvasWidth());
            int y = (int) (Math.random() * frame.getCanvasHeight());

            data.addPoint(new Point(x, y));
        }
    }



    public static void main(String[] args) {
        int sceneWidth = 500;
        int sceneHeight = 500;

        int N = 100000;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
