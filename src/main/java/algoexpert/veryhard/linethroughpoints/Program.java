package algoexpert.veryhard.linethroughpoints;

import java.util.ArrayList;
import java.util.List;

//TODO to be optimized
public class Program {

    public static void main(String args[]) {
        int[][] input = new int[][]{{1, 1}, {2, 2}, {3, 3}, {0, 4}, {-2, 6}, {4, 0}, {2, 1}};
        int[][] input1 = new int[][]{{1, 4}, {3, 5}, {7, 1}, {5, 4}, {4, 5}, {9, 2}, {1, 3}, {2, 8}};
        int[][] input2 = new int[][]{{3, 3}, {0, 4}, {-2, 6}, {4, 0}, {2, 1}, {3, 4}, {5, 6}, {0, 0}};

        int expected = 4;
        Program p = new Program();
        System.out.println(p.lineThroughPoints(input2));
    }

    public int lineThroughPoints(int[][] points) {
        // Write your code here.
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                Line line = Line.createLine(points[i][0], points[i][1], points[j][0], points[j][1]);
                lines.add(line);
            }
        }
        for (Line line : lines) {
            for (int j = 0; j < points.length; j++) {
                line.addPointIfPossible(points[j][0], points[j][1]);
            }
        }

        int result = lines.stream().map(l -> l.count).max(Integer::compareTo).orElse(1);
        return result;
    }

    static class Line {
        List<double[]> points = new ArrayList<>();
        private boolean degenerate = false;
        double x;
        private double a;
        private double b;
        private int count;

        private Line(double a, double b) {
            this.a = a;
            this.b = b;
        }

        private Line(double x) {
            this.x = x;
            this.degenerate = true;
        }

        public void addPointIfPossible(double x1, double y1) {
            if (degenerate) {
                if (x1 == x) {
                    count++;
                }
            } else if ((x1 * a + b) == y1) {
                count++;
            }
        }

        public static Line createLine(double x1, double y1, double x2, double y2) {
            if (x2 - x1 != 0) {
                double a = (y2 - y1) / (x2 - x1);
                double b = y1 - a * x1;
                return new Line(a, b);
            } else {
                return new Line(x1);
            }
        }
    }
}
