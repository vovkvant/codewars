package algoexpert.veryhard.longestincreasingsubsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Program {

    public static void main(String args[]) {
        int[] input = new int[] {5, 7, -24, 12, 10, 2, 3, 12, 5, 6, 35};
        System.out.print(longestIncreasingSubsequence(input));
    }

    //TODO repeat this task
    public static List<Integer> longestIncreasingSubsequence(int[] array) {
        int[] chainLengths = new int[array.length];
        int[] sequence = new int[array.length];
        int maxIdx = Integer.MIN_VALUE;
        int maxLength = Integer.MIN_VALUE;
        Arrays.fill(sequence, -1);
        // Write your code here.
        for (int i = 0; i < array.length; i++) {
            int currNum = array[i];
            for (int j = 0; j < i; j++) {
                int otherNum = array[j];
                if (currNum > otherNum && chainLengths[j] + 1 > chainLengths[i]) {
                    chainLengths[i] = chainLengths[j] + 1;
                    sequence[i] = j;
                }
            }
            if (chainLengths[i] > maxLength) {
                maxLength = chainLengths[i];
                maxIdx = i;
            }
        }

        int k = maxIdx;
        LinkedList<Integer> result = new LinkedList<>();
        while (k != -1) {
            result.addFirst(array[k]);
            k = sequence[k];
        }
        return result;
    }
}
