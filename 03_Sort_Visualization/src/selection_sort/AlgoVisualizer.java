package selection_sort;

import util.AlgoVisHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgoVisualizer {

    private SelectionSortData data;  // 数据
    private AlgoFrame frame;  // 视图
    private static int DELAY = 10;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {
        // 初始化数据
        data = new SelectionSortData(N, sceneHeight);

        // 初始化视图
        EventQueue.invokeLater(()->{
            frame = new AlgoFrame("Selection Sort", sceneWidth, sceneHeight);


            new Thread(() -> {
                run();
            }).start();

        });
    }

    // 动画逻辑
    private void run() {
        setData(0, -1, -1);

        for (int i = 0; i < data.N(); i++) {
            // 寻找 [i,n) 区间内最小值的索引
            int minIndex = i;

            setData(i, -1, minIndex);

            for (int j = i + 1; j < data.N(); j++) {
                setData(i, j, minIndex);
                if (data.get(j) < data.get(minIndex)) {
                    minIndex = j;
                    setData(i, j, minIndex);
                }
            }
            data.swap(i, minIndex);

            setData(i + 1, -1, -1);
        }

        setData(data.N(), -1, -1);
    }


    private void setData(int orderedIndex, int currentCompareIndex, int currentMinIndex) {
        data.currentCompareIndex = currentCompareIndex;
        data.currentMinIndex = currentMinIndex;
        data.orderedIndex = orderedIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }



    public static void main(String[] args) {
        int sceneWidth = 1000;
        int sceneHeight = 500;
        int N = 100;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
