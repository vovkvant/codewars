package algoexpert.medium.removeislands;

import java.util.Arrays;

public class Program {
    //++see video about palindrome task one more time


    public static void main(String[] args) {
        int[][] input =
                new int[][]{
                        {1, 0, 0, 0, 0, 0},
                        {0, 1, 0, 1, 1, 1},
                        {0, 0, 1, 0, 1, 0},
                        {1, 1, 0, 0, 1, 0},
                        {1, 0, 1, 1, 0, 0},
                        {1, 0, 0, 0, 0, 1},
                };

        input = new int[0][0];
        Program p = new Program();
        int[][] result = p.removeIslands(input);
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }

    }

    public int[][] removeIslands(int[][] matrix) {
        // Write your code here.
        if(matrix.length == 0) return matrix;

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        for (boolean[] row : visited) {
            Arrays.fill(row, false);
        }

        for (int i = 0; i < matrix[0].length; i++) {
            checkAround(i, 0, matrix, visited);
            checkAround(i, matrix.length - 1, matrix, visited);
        }

        for (int i = 0; i < matrix.length; i++) {
            checkAround(0, i, matrix, visited);
            checkAround(matrix[0].length - 1, i, matrix, visited);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (!visited[i][j]) {
                    matrix[i][j] = 0;
                }
            }
        }

        return matrix;
    }

    void checkAround(int x, int y, int[][] matrix, boolean[][] visited) {
        if (matrix[y][x] == 1 && !visited[y][x]) {
            visited[y][x] = true;
            int x1, y1;
            int height = matrix.length;
            int width = matrix[0].length;
            x1 = x + 1;
            y1 = y;
            if (x1 >= 0 && y1 >= 0 && x1 < width && y1 < height) {
                checkAround(x1, y1, matrix, visited);
            }

            x1 = x - 1;
            y1 = y;
            if (x1 >= 0 && y1 >= 0 && x1 < width && y1 < height) {
                checkAround(x1, y1, matrix, visited);
            }

            x1 = x;
            y1 = y + 1;
            if (x1 >= 0 && y1 >= 0 && x1 < width && y1 < height) {
                checkAround(x1, y1, matrix, visited);
            }

            x1 = x;
            y1 = y - 1;
            if (x1 >= 0 && y1 >= 0 && x1 < width && y1 < height) {
                checkAround(x1, y1, matrix, visited);
            }
        }
    }
}
