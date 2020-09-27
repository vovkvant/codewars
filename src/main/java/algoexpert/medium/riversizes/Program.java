package algoexpert.medium.riversizes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Program {
    public static void main(String args[]) {
        int[][] input = {{1, 1, 0, 0, 0, 0, 1, 1},
                {1, 0, 1, 1, 1, 1, 0, 1},
                {0, 1, 1, 0, 0, 0, 1, 1}};

        List<Integer> output = Program.riverSizes(input);
        Collections.sort(output);
        System.out.println(output);
    }


    static void checkAndAdd(int i, int k, int[][] matrix, List<Integer> adj) {
        if ((i >= 0 && i < matrix.length) &&
                (k >= 0 && k < matrix[i].length)) {
            int position = i * matrix[0].length + k;
            adj.add(position);
        }
    }

    static int getByPosition(int[][] matrix, int position) {
        int k = position % matrix[0].length;
        int i = (position - k) / matrix[0].length;
        return matrix[i][k];
    }


    static int dfg(List<List<Integer>> graph, boolean[] marked, int current, int[][] matrix) {
        marked[current] = true;
        boolean isRiver = (getByPosition(matrix, current) == 1);
        int counter = 0;
        if (isRiver) {
            counter++;
            for (int adj : graph.get(current)) {
                boolean isAdjRiver = (getByPosition(matrix, adj) == 1);
                if (isAdjRiver && !marked[adj]) {
                    counter += dfg(graph, marked, adj, matrix);
                }
            }
        }
        return counter;
    }

    public static List<Integer> riverSizes(int[][] matrix) {
        List<List<Integer>> graph = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        int graphSize = matrix.length * matrix[0].length;
        System.out.println(graphSize);
        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[i].length; k++) {
                List<Integer> adj = new ArrayList<>();
                checkAndAdd(i + 1, k, matrix, adj);
                checkAndAdd(i - 1, k, matrix, adj);
                checkAndAdd(i, k + 1, matrix, adj);
                checkAndAdd(i, k - 1, matrix, adj);
                graph.add(adj);
            }
        }
        System.out.println(graph);
        boolean[] marked = new boolean[graphSize];
        for (int i = 0; i < marked.length; i++) {
            if (!marked[i]) {
                int counter = dfg(graph, marked, i, matrix);
                if (counter != 0) {
                    result.add(counter);
                }
            }
        }
        return result;
    }
}
