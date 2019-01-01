package MonteCarlo_PI_without_rendering;

import java.awt.*;
import java.util.LinkedList;

public class MonteCarloPIData {

    private Circle circle;  // 数据
    private int insideCircle = 0;
    private LinkedList<Point> points;

    public MonteCarloPIData(Circle circle) {
        this.circle = circle;
        points = new LinkedList<>();
    }

    public Circle getCircle() {
        return circle;
    }

    public Point getPoint(int i) {
        if (i < 0 || i >= points.size()) {
            throw new IllegalArgumentException("out of bound in getPoint");
        }
        return points.get(i);
    }

    public int getPointsNumber() {
        return points.size();
    }

    public void addPoint(Point p) {
        points.add(p);
        if (circle.contain(p)) {
            insideCircle++;
        }
    }

    public double estimatePI() {
        if (points.size() == 0) {
            return 0.0;
        }
        int circleArea = insideCircle;
        int squareArea = points.size();
        return (double) circleArea * 4 / squareArea;
    }
}
