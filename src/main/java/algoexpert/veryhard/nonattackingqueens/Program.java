package algoexpert.veryhard.nonattackingqueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Program {

    void printArr(int[][] desc) {
        for (int arr[] : desc) {
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
        Program p = new Program();
        long start = System.currentTimeMillis();
        System.out.println(p.nonAttackingQueens(10));
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(countCopy);
    }


    public static long countCopy = 0;

    public int nonAttackingQueens(int n) {
        // Write your code here.
        int[][] desc = new int[n][n];
        for (int[] row : desc) {
            Arrays.fill(row, 0);
        }

        int queenCounter = 0;
        List<Integer> result = new ArrayList<>();
        Set<Integer> excludedRow = new HashSet<>();
        putNextQueen(desc, queenCounter, n, result, excludedRow);

        return (int) result.stream().count();
    }

    public void putNextQueen(int[][] desc, int queenCounter, int n, List<Integer> result,
                             Set<Integer> excludedRow) {
        int[][] descCopy = new int[n][n];

        long startCopy = System.currentTimeMillis();
        for (int k = 0; k < n; k++) {
            descCopy[k] = Arrays.copyOf(desc[k], n);
        }
        countCopy += (System.currentTimeMillis() - startCopy);

        if (n == queenCounter) {
            result.add(1);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!excludedRow.contains(i)) {
                excludedRow.add(i);
                for (int j = 0; j < n; j++) {
                    if (descCopy[i][j] == 0) {
                        startCopy = System.currentTimeMillis();
                        countAttackedCells(descCopy, i, j, n);
                        Set<Integer> excludedRowCopy  = new HashSet<>(excludedRow);
                        countCopy += (System.currentTimeMillis() - startCopy);

                        putNextQueen(descCopy, queenCounter + 1, n, result, excludedRowCopy);
                        startCopy = System.currentTimeMillis();
                        for (int k = 0; k < n; k++) {
                            descCopy[k] = Arrays.copyOf(desc[k], n);
                        }
                        countCopy += (System.currentTimeMillis() - startCopy);
                    }
                }
            }
        }
    }

    //i - row
    //j - column
    void countAttackedCells(int[][] desc, int oi, int oj, int n) {
        for (int t = 0; t < n; t++) {
            desc[oi][t] = 1;
        }
        for (int t = 0; t < n; t++) {
            desc[t][oj] = 1;
        }

        int i = oi;
        int j = oj;
        while (i < n && j < n) {
            desc[i][j] = 1;
            i++;
            j++;
        }

        i = oi;
        j = oj;
        while (i >= 0 && j >= 0) {
            desc[i][j] = 1;
            i--;
            j--;
        }

        i = oi;
        j = oj;
        while (i < n && j >= 0) {
            desc[i][j] = 1;
            i++;
            j--;
        }

        i = oi;
        j = oj;
        while (i >= 0 && j < n) {
            desc[i][j] = 1;
            i--;
            j++;
        }
    }
}
