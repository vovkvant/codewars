package algoexpert.hard.diskstacking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Program {

    public static void main(String args[]) {
        List<Integer[]> input = new ArrayList<Integer[]>();
        input.add(new Integer[]{2, 1, 2});
        input.add(new Integer[]{3, 2, 3});
        input.add(new Integer[]{2, 2, 8});
        input.add(new Integer[]{2, 3, 4});
        input.add(new Integer[]{2, 2, 1});
        input.add(new Integer[]{4, 4, 5});
        List<Integer[]> result = diskStacking(input);
        for (Integer[] arr : result) {
            System.out.print(Arrays.toString(arr));
        }
    }

    public static List<Integer[]> diskStacking(List<Integer[]> disks) {
        // Write your code here.
        Map<Integer, List<Integer>> canItStandOn = new HashMap<>();
        for (int i = 0; i < disks.size(); i++) {
            canItStandOn.put(i, new ArrayList<>());
        }
        for (int i = 0; i < disks.size(); i++) {
            for (int j = 0; j < disks.size(); j++) {
                if (i == j) continue;
                Integer[] currentDisk = disks.get(i);
                Integer[] diskToTry = disks.get(j);
                if (diskToTry[0] > currentDisk[0] && diskToTry[1] > currentDisk[1] && diskToTry[2] > currentDisk[2]) {
                    canItStandOn.get(i).add(j);
                }
            }
        }

        List<List<Integer>> stacksList = new ArrayList<>();
        for (int k = 0; k < disks.size(); k++) {
            List<Integer> stack = new ArrayList<>();
            findRelation(k, canItStandOn, stack, stacksList);
        }

        int maxHeight = 0;
        List<Integer[]> maxStack = null;
        for (List<Integer> stack : stacksList) {
            List<Integer[]> tempStack = stack.stream().map(e -> disks.get(e)).collect(Collectors.toList());
            int height = tempStack.stream().map(e -> e[2]).reduce((e1, e2) -> e1 + e2).get();
            if (height > maxHeight) {
                maxHeight = height;
                maxStack = tempStack;
            }
        }
        return maxStack;
    }

    static void findRelation(int k, Map<Integer, List<Integer>> canItStandOn,
                             List<Integer> stack, List<List<Integer>> stacksList) {
        stack.add(k);
        List<Integer> list = canItStandOn.get(k);
        if (list.size() == 0) {
            stacksList.add(stack);
            return;
        }
        for (int r : list) {
            findRelation(r, canItStandOn, new ArrayList<>(stack), stacksList);
        }
    }

    //helper method for debugging
    static void print(Map<Integer, List<Integer>> canItStandOn, List<Integer[]> disks) {
        for (Integer key : canItStandOn.keySet()) {
            List<Integer> result = canItStandOn.get(key);
            System.out.print(Arrays.toString(disks.get(key)));
            for (Integer j : result) {
                System.out.print(Arrays.toString(disks.get(j)));
            }
            System.out.print("\n");
        }
    }
}
