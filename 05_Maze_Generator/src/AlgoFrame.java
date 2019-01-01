import javax.swing.*;
import java.awt.*;

public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);

        this.canvasHeight = canvasHeight;
        this.canvasWidth = canvasWidth;

        AlgoCanvas canvas = new AlgoCanvas();

        // 设置内容面板
        setContentPane(canvas);
        pack();

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    public AlgoFrame(String title){
        this(title, 1024, 768);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }
    public int getCanvasHeight() {
        return canvasHeight;
    }

    private MazeData data;

    // 根据传入数据进行相应渲染
    public void render(MazeData data) {
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel {

        public AlgoCanvas(){
            // 默认开启了双缓存
            super(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(hints);

            int w = canvasWidth / data.getCol();
            int h = canvasHeight / data.getRow();

            // 具体绘制
            for (int i = 0; i < data.getRow(); i++) {
                for (int j = 0; j < data.getCol(); j++) {

                    if (data.maze[i][j]==MazeData.WALL) {
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
                    } else {
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.White);
                    }


                    AlgoVisHelper.fillRectangle(g2d, j * w, i * h, w, h);
                }
            }


        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}

