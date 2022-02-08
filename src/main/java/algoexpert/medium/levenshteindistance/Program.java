package algoexpert.medium.levenshteindistance;

import java.util.Arrays;

class Program {

    public static void main(String[] args) {
        System.out.println(levenshteinDistance("yabd", "abc"));
    }

    //TODO Refactor it
    public static int levenshteinDistance(String str1, String str2) {
        // Write your code here.
        String xstr1 = " " + str1;
        String ystr2 = " " + str2;
        int[][] result = new int[ystr2.length()][xstr1.length()];

        for (int y = 0; y < ystr2.length(); y++) {
            for (int x = 0; x < xstr1.length(); x++) {
                if (ystr2.charAt(y) == xstr1.charAt(x)) {
                    result[y][x] = getDiag(result, x, y);
                } else {
                    result[y][x] = getMin(result, x, y);
                }
            }
            //System.out.println(Arrays.toString(result));
        }
        return result[ystr2.length() - 1][xstr1.length() - 1];
    }

    static int getMin(int[][] result, int x, int y) {
        return 1 + Math.min(get(result, x - 1, y - 1),
                Math.min(get(result, x - 1, y), get(result, x, y - 1)));
    }

    static int getDiag(int[][] result, int x, int y) {
        int res = get(result, x - 1, y - 1);
        if(res == Integer.MAX_VALUE) return 0;
        return res;
    }

    static int get(int[][] result, int x, int y) {
        if (x < 0 || y < 0) return Integer.MAX_VALUE;
        return result[y][x];
    }
}
