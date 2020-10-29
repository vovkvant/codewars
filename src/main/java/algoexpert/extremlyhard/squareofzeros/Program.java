package algoexpert.extremlyhard.squareofzeros;

import java.util.*;

public class Program {

    public static void main (String args[]) {
        List<List<Integer>> test = new ArrayList<List<Integer>>();
        test.add(new ArrayList<Integer>(Arrays.asList(new Integer[] {1, 1, 1, 0, 1, 0})));
        test.add(new ArrayList<Integer>(Arrays.asList(new Integer[] {0, 0, 0, 0, 0, 1})));
        test.add(new ArrayList<Integer>(Arrays.asList(new Integer[] {0, 1, 1, 1, 0, 1})));
        test.add(new ArrayList<Integer>(Arrays.asList(new Integer[] {0, 0, 0, 1, 0, 1})));
        test.add(new ArrayList<Integer>(Arrays.asList(new Integer[] {0, 1, 1, 1, 0, 1})));
        test.add(new ArrayList<Integer>(Arrays.asList(new Integer[] {0, 0, 0, 0, 0, 1})));
        System.out.println(Program.squareOfZeroes(test));
    }

    //try another solution
    public static boolean squareOfZeroes(List<List<Integer>> matrix) {
        boolean isSquare = false;
        for (int y = 0; y < matrix.size(); y++) {
            for (int x = 0; x < matrix.get(y).size(); x++) {
                int n = 1;
                while (y + n < matrix.size() && x + n < matrix.get(0).size()) {
                    Set<Integer> lines = new HashSet<>();

                    //put this cycles in cache:
                    for (int kx = x; kx <= x + n; kx++) {
                        lines.add(matrix.get(y).get(kx));
                        lines.add(matrix.get(y + n).get(kx));
                    }
                    for (int ky = y; ky <= y + n; ky++) {
                        lines.add(matrix.get(ky).get(x));
                        lines.add(matrix.get(ky).get(x + n));
                    }
                    if(lines.size() == 1 && lines.contains(0)) {
                        isSquare = true;
                        break;
                    }
                    n++;
                }
                if(isSquare) break;
            }
            if(isSquare) break;
        }
        // Write your code here.
        return isSquare;
    }
}
