package MonteCarlo_PI_without_rendering;

import java.awt.*;

public class MonteCarloExperiment {

    private int squareSide;
    private int N;
    private int outputInterval = 1000;

    public MonteCarloExperiment(int squareSide, int N, int outputInterval) {
        if (squareSide <= 0 || N <= 0) {
            throw new IllegalArgumentException("squareSide and N must be larger than 0");
        }

        this.outputInterval = outputInterval > 0 ? outputInterval : this.outputInterval;
        this.squareSide = squareSide;
        this.N = N;
    }

    public void run() {
        Circle circle = new Circle(squareSide / 2, squareSide / 2, squareSide / 2);
        MonteCarloPIData data = new MonteCarloPIData(circle);
        for (int i = 0; i < N; i++) {
            if (i % outputInterval == 0) {
                System.out.println(data.estimatePI());
            }

            int x = (int) (Math.random() * squareSide);
            int y = (int) (Math.random() * squareSide);
            data.addPoint(new Point(x, y));
        }
    }

    public static void main(String[] args) {
        int squareSide = 800;
        int N = 1000000;
        int outputInterval = 10000;

        MonteCarloExperiment exp = new MonteCarloExperiment(squareSide, N, outputInterval);
        exp.run();

    }
}
