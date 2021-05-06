package algoexpert.veryhard.nonattackingqueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Program {
    public static long countTiming = 0;

    public static long countCopy = 0;

    public static void main(String[] args) {
        Program p = new Program();
        long start = System.currentTimeMillis();
        System.out.println(p.nonAttackingQueens(9));
        System.out.println(System.currentTimeMillis() - start);
        //System.out.println(countTiming);
        //System.out.println(countCopy);
    }

    void printArr(int[][] desc) {
        for (int arr[] : desc) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("=====");
    }



    public int nonAttackingQueens(int n) {
        // Write your code here.
        int[][] desc = new int[n][n];
        for (int[] row : desc) {
            Arrays.fill(row, 0);
        }
        int queenCounter = 0;
        List<Integer> result = new ArrayList<>();
        Set<String> usedPosition = new HashSet<>();
        putNextQueen(desc, queenCounter, n, result, usedPosition);

        return (int) result.stream().count();
    }

    public void putNextQueen(int[][] desc, int queenCounter, int n, List<Integer> result, Set<String> usedPositions) {
        int[][] descCopy = new int[n][n];
        for (int k = 0; k < n; k++) {
            descCopy[k] = Arrays.copyOf(desc[k], n);
        }

        if (n == queenCounter) {
            //System.out.println("Found");
            //printArr(desc);
            result.add(1);
            return;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                long start = System.currentTimeMillis();
                if (descCopy[i][j] == 0) {
                    String position = String.valueOf(i) + String.valueOf(j);
                    if (!usedPositions.contains(position)) {
                        usedPositions.add(position);
                        //System.out.println(queenCounter + " i=" + i + " j=" + j);
                        long startCount = System.currentTimeMillis();
                        countAttackedCells(descCopy, i, j, n);
                        countTiming+=(System.currentTimeMillis() - startCount);

                        //printArr(descCopy);
                        putNextQueen(descCopy, queenCounter + 1, n, result, new HashSet<>(usedPositions));

                        long startCopy = System.currentTimeMillis();
                        for (int k = 0; k < n; k++) {
                            descCopy[k] = Arrays.copyOf(desc[k], n);
                        }
                        countCopy+=(System.currentTimeMillis() - startCopy);
                    }
                }
                if(queenCounter == 1) {
                    //System.out.println(System.currentTimeMillis() - start +  "i=" + i + " j=" + j);
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
