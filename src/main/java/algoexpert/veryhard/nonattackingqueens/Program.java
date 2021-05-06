package algoexpert.veryhard.nonattackingqueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        Program p = new Program();
        System.out.println(p.nonAttackingQueens(10));
    }

    public int nonAttackingQueens(int n) {
        // Write your code here.
        int[][] desc = new int[n][n];
        for (int[] row : desc) {
            Arrays.fill(row, 0);
        }

        int queenCounter = 0;
        List<Integer> result = new ArrayList<>();
        putNextQueen(desc, queenCounter, n, result, 0);
        return (int) result.stream().count();
    }


    public void putNextQueen(int[][] desc, int queenCounter, int n, List<Integer> result,
                             int rowNumber) {
        int[][] descCopy = new int[n][n];
        for (int k = rowNumber; k < n; k++) {
            descCopy[k] = Arrays.copyOf(desc[k], n);
        }

        if (n == queenCounter) {
            result.add(1);
            return;
        }
        for (int j = 0; j < n; j++) {
            if (descCopy[rowNumber][j] == 0) {
                countAttackedCells(descCopy, rowNumber, j, n);
                putNextQueen(descCopy, queenCounter + 1, n, result, rowNumber + 1);
                for (int k = 0; k < n; k++) {
                    descCopy[k] = Arrays.copyOf(desc[k], n);
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
