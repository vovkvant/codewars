package algoexpert.veryhard.nonattackingqueens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Program_1 {

    public static long countCopy = 0;


    public static void main(String[] args) {
        Program_1 p = new Program_1();
        long start = System.currentTimeMillis();
        System.out.println(p.nonAttackingQueens(10));
        System.out.println(countCopy);
        System.out.println(System.currentTimeMillis() - start);
    }
    // 21305
    // 38076

    String convert1(int i, int j) {
        String position = String.valueOf(i) + String.valueOf(j);
        return position;
    }

    public int nonAttackingQueens(int n) {
        // Write your code here.
        String[][] convertMap = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                convertMap[i][j] = convert1(i, j);
            }
        }
        HashMap<String, Set<String>> toRemoveMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Set<String> toRemove = preCalculateAttackedCells(i, j, n, convertMap);
                toRemoveMap.put(convertMap[i][j], toRemove);
            }
        }


        Set<String> desc = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                desc.add(convertMap[i][j]);
            }
        }

        int queenCounter = 0;
        List<Integer> result = new ArrayList<>();
        Set<String> usedPosition = new HashSet<>();

        System.out.println(desc);
        putNextQueen(desc, queenCounter, n, result, usedPosition, toRemoveMap);

        return (int) result.stream().count();
    }

    public void putNextQueen(Set<String> desc, int queenCounter, int n, List<Integer> result, Set<String> usedPositions,
                             HashMap<String, Set<String>> toRemoveMap) {
        if (n == queenCounter) {
            result.add(1);
            return;
        }
        for (String cell : desc) {
            if (!usedPositions.contains(cell)) {
                usedPositions.add(cell);
                long startCopy = System.currentTimeMillis();
                Set<String> descCopy = new HashSet<>(desc);
                countCopy += (System.currentTimeMillis() - startCopy);
                Set<String> toRemove = toRemoveMap.get(cell);
                descCopy.removeAll(toRemove);

                //descCopy.remove(usedPositions);
                putNextQueen(descCopy, queenCounter + 1, n, result, new HashSet<>(usedPositions),
                        toRemoveMap);
            }
        }
    }

    Set<String> preCalculateAttackedCells(int oi, int oj, int n, String[][] convertMap) {
        Set<String> toRemove = new HashSet<>();
        for (int t = 0; t < n; t++) {
            toRemove.add(convertMap[oi][t]);
        }
        for (int t = 0; t < n; t++) {
            toRemove.add(convertMap[t][oj]);
        }

        int i = oi;
        int j = oj;
        while (i < n && j < n) {
            toRemove.add(convertMap[i][j]);
            i++;
            j++;
        }

        i = oi;
        j = oj;
        while (i >= 0 && j >= 0) {
            toRemove.add(convertMap[i][j]);
            i--;
            j--;
        }

        i = oi;
        j = oj;
        while (i < n && j >= 0) {
            toRemove.add(convertMap[i][j]);
            i++;
            j--;
        }

        i = oi;
        j = oj;
        while (i >= 0 && j < n) {
            toRemove.add(convertMap[i][j]);
            i--;
            j++;
        }

        return toRemove;
    }

    //i - row
    //j - column
    void countAttackedCells(Set<String> desc, String cell, int n, String[][] convertMap) {
        int oi = Integer.valueOf(cell.substring(0, 1));
        int oj = Integer.valueOf(cell.substring(1, 2));
        for (int t = 0; t < n; t++) {
            desc.remove(convertMap[oi][t]);
        }
        for (int t = 0; t < n; t++) {
            desc.remove(convertMap[t][oj]);
        }

        int i = oi;
        int j = oj;
        while (i < n && j < n) {
            desc.remove(convertMap[i][j]);
            i++;
            j++;
        }

        i = oi;
        j = oj;
        while (i >= 0 && j >= 0) {
            desc.remove(convertMap[i][j]);
            i--;
            j--;
        }

        i = oi;
        j = oj;
        while (i < n && j >= 0) {
            desc.remove(convertMap[i][j]);
            i++;
            j--;
        }

        i = oi;
        j = oj;
        while (i >= 0 && j < n) {
            desc.remove(convertMap[i][j]);
            i--;
            j++;
        }
    }
}
