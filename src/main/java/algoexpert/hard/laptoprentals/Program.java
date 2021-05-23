package algoexpert.hard.laptoprentals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        int[][] times = new int[][]{{0, 2}, {1, 4}, {4, 6}, {0, 4}, {7, 8}, {9, 11}, {3, 10}};
        ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
        for (int[] time : times) {
            input.add(new ArrayList(Arrays.asList(time[0], time[1])));
        }
        System.out.println(new Program().laptopRentals(input));
    }

    //solution 2
    public int laptopRentals(ArrayList<ArrayList<Integer>> times) {
        if (times.size() == 0) return 0;
        // Write your code here.
        List<Integer> startTimes = times.stream().map(l -> l.get(0)).collect(Collectors.toList());
        List<Integer> endTimes = times.stream().map(l -> l.get(1)).collect(Collectors.toList());
        Collections.sort(startTimes);
        Collections.sort(endTimes);
        int start = 0;
        int end = 0;
        int count = 0;
        while (start < startTimes.size()) {
            if (startTimes.get(start) >= endTimes.get(end)) {
                count--;
                end++;
            }
            count++;
            start++;
        }
        return count;
    }


    //solution 1
    public int laptopRentals2(ArrayList<ArrayList<Integer>> times) {
        if (times.size() == 0) return 0;
        // Write your code here.
        Collections.sort(times, (o1, o2) -> {
            if (o1.get(0) == o2.get(0)) return 0;
            if (o1.get(0) < o2.get(0)) return -1;
            else return 1;
        });
        System.out.println(times);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int minSize = 1;
        for (ArrayList<Integer> interval : times) {
            Integer leastTime = pq.peek();
            if (leastTime != null) {
                if (leastTime <= interval.get(0)) {
                    pq.poll();
                }
            }
            pq.add(interval.get(1));
            if (pq.size() > minSize) {
                minSize = pq.size();
            }
        }

        return minSize;
    }
}
