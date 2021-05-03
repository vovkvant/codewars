package algoexpert.hard.maxsumincreasingsubsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Program {
    public static void main(String args[]) {
        int[] input = {10, 70, 20, 30, 50, 11, 30};
        //System.out.println(maxSumIncreasingSubsequence(input));
        int[] input1 = {-1, 1};
        System.out.println(maxSumIncreasingSubsequence(input1));
        int[] input2 = {8, 12, 2, 3, 15, 5, 7};
        System.out.println(maxSumIncreasingSubsequence(input2));

        //10, 15, 4, 5, 11, 14, 31, 25, 31, 23, 25, 31, 50
        int[] input3 = {10, 15, 4, 5, 11, 14, 31, 25, 31, 23, 25, 31, 50};
        System.out.println(maxSumIncreasingSubsequence(input3));
    }

    public static List<List<Integer>> maxSumIncreasingSubsequence(int[] array) {
        // Write your code here.
        int[] sums = Arrays.copyOf(array, array.length);
        Integer[] sequence = new Integer[array.length];
        int maxSum = Integer.MIN_VALUE;
        Integer maxIdx = null;
        for (int i = 0; i < array.length; i++) {
            int currentNum = array[i];
            for (int j = 0; j < i; j++) {
                int otherNum = array[j];
                if (currentNum > otherNum && sums[j] + currentNum > sums[i]) {
                    sums[i] = sums[j] + currentNum;
                    sequence[i] = j;
                }
            }
            if (sums[i] > maxSum) {
                maxSum = sums[i];
                maxIdx = i;
            }
        }
        final Integer s = maxSum;
        final LinkedList<Integer> result = new LinkedList<>();
        Integer k = maxIdx;
        while (k != null) {
            result.addFirst(array[k]);
            k = sequence[k];
        }

        return new ArrayList<List<Integer>>() {
            {
                add(Collections.singletonList(s)); // Example max sum
                add(result); // Example max sequence
            }
        };
    }
}
