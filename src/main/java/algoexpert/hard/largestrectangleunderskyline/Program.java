package algoexpert.hard.largestrectangleunderskyline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Program {

    public static void main(String[] args) {
        ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(1, 3, 3, 2, 4, 1, 5, 3, 2));
        Program p = new Program();
        System.out.println(p.largestRectangleUnderSkyline(input));
    }

    //optimal solution O(n)
    public int largestRectangleUnderSkyline(ArrayList<Integer> buildings) {
        LinkedList<Integer> stack = new LinkedList<>();
        int maxArea = 0;
        for (int i = 0; i < buildings.size() + 1; i++) {
            int currentHeight = 0;
            if (i < buildings.size()) {
                currentHeight = buildings.get(i);
            }

            Integer right = i;
            Integer lastBuilding = stack.peekLast();
            if (lastBuilding == null) {
                stack.addLast(i);
                continue;
            }

            if (buildings.get(lastBuilding) < currentHeight) {
                stack.addLast(i);
            } else {
                Integer left = stack.peekLast();
                while (left != null && left!=-1 && buildings.get(left) >= currentHeight) {
                    lastBuilding = stack.pollLast();
                    left = stack.peekLast();
                    left = left == null ? -1 : left;
                    int area = (right - left - 1) * buildings.get(lastBuilding);
                    if (area > maxArea) {
                        maxArea = area;
                    }
                }

                if (left == null) {
                    int area = (right - 1) * buildings.get(lastBuilding);
                    if (area > maxArea) {
                        maxArea = area;
                    }
                }
                stack.addLast(i);
            }
        }
        return maxArea;
    }

    //solution 1, not optimal O(n^2)
    public int largestRectangleUnderSkyline2(ArrayList<Integer> buildings) {
        // Write your code here.
        int maxArea = 0;
        for (int i = 0; i < buildings.size(); i++) {
            int minHeight = buildings.get(i);
            for (int j = i; j < buildings.size(); j++) {
                if (buildings.get(j) < minHeight) {
                    minHeight = buildings.get(j);
                }
                int area = minHeight * (j - i + 1);
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }
}
