package algoexpert.veryhard.waterfallstreams;

public class Program {

    public static void main(String args[]) {
        double[][] array =
                new double[][]{
                        {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                        {1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                        {0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0},
                        {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                        {1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0},
                        {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0},
                        {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                };
        int source = 3;
        Program p = new Program();
        //System.out.println(p.waterfallStreams(array, source));
    }

    final static int VERTICAL = 0;
    final static int HORIZONTAL = 1;
    final static int RIGHT = 2;
    final static int LEFT = 3;

    public double[] waterfallStreams(double[][] array, int source) {
        // Write your code here.
        double[] result = new double[array[0].length];
        calculateStream(result, source, 0, array, -1, 100.0d, VERTICAL);
        return result;
    }

    private void calculateStream(double[] result, int x, int y, double[][] array, int hdirection,
                                 double stream, int direction) {
        while (true) {
            if (x < 0 || x >= array[0].length) {
                break;
            }
            if (array[y][x] == 1) break;
            if (y == array.length - 1) {
                result[x] = result[x] + stream;
                break;
            }
            if (array[y + 1][x] == 0) {
                y++;
                direction = VERTICAL;
            } else if (direction == VERTICAL && array[y + 1][x] == 1) {
                calculateStream(result, x - 1, y, array, LEFT, stream / 2, HORIZONTAL);
                calculateStream(result, x + 1, y, array, RIGHT, stream / 2, HORIZONTAL);
                break;
            } else if (direction == HORIZONTAL && array[y + 1][x] == 1) {
                if (x > 0 && hdirection == RIGHT) {
                    x++;
                } else if (x < array[0].length && hdirection == LEFT) x--;
            }
        }
    }
}
