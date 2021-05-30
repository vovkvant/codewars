package algoexpert.medium.mergeoverlappingintervals;

import java.util.Arrays;
import java.util.LinkedList;

public class Program {

    public static void main(String args[]) {
        int[][] intervals =
                new int[][]{
                        {20, 21},
                        {22, 23},
                        {0, 1},
                        {3, 4},
                        {23, 24},
                        {25, 27},
                        {5, 6},
                        {7, 19}
                };
        Program p = new Program();
        int[][] result = p.mergeOverlappingIntervals(intervals);
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }

    }

    //write seconf solution with just array
    public int[][] mergeOverlappingIntervals(int[][] intervals) {
        if (intervals.length == 0) return intervals;

        Arrays.sort(intervals, (o1, o2) -> {
            if(o1[0] > o2[0]) return 1;
            if(o1[0] < o2[0]) return -1;
            return 0;
        });

        LinkedList<Integer> stack = new LinkedList<>();
        stack.addLast(intervals[0][0]);
        stack.addLast(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            System.out.println(stack);
            if (stack.peekLast() >= intervals[i][0]) {
                int maxEl = Integer.MIN_VALUE;
                while (stack.peekLast() != null && stack.peekLast() >= intervals[i][0]) {
                    int el = stack.pollLast();
                    maxEl = Math.max(el, maxEl);
                }
                if (stack.size() == 0) stack.addLast(intervals[i][0]);
                stack.addLast(Math.max(intervals[i][1], maxEl));
            } else {
                stack.addLast(intervals[i][0]);
                stack.addLast(intervals[i][1]);
            }
        }
        System.out.println(stack);

        int[][] result = new int[stack.size() / 2][2];
        for (int i = 0; i < result.length; i++) {
            result[i][0] = stack.pollFirst();
            result[i][1] = stack.pollFirst();
        }
        return result;
    }
}

