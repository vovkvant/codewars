package algoexpert.medium.threesum;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program {
    public static void main(String args[]) {
        /*
        int[] array = new int[]{12, 3, 1, 2, -6, 5, -8, 6};
        List<Integer[]> result = threeNumberSum(array, 0);
        for (Integer[] integers : result) {
            System.out.println(Arrays.toString(integers));
        }
        */

        List<Integer> list = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());
        moveElementToEnd(list, 3);
        System.out.println(list);
    }


    public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
        // Write your code here.
        int lastPosition = array.size() - 1;
        while(lastPosition > 0 && array.get(lastPosition) == toMove)
            lastPosition--;

        for(int i = 0; i < lastPosition; i++) {
            if(array.get(i) == toMove) {
                array.set(i, array.get(lastPosition));
                array.set(lastPosition, toMove);
                while(lastPosition > 0 && array.get(lastPosition) == toMove)
                    lastPosition--;
            }
        }
        return array;
    }

    public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
        // Write your code here.
        ArrayList<Integer> targetSums2 = new ArrayList<Integer>();
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            targetSums2.add(targetSum - array[i]);
        }
        //Collections.sort(targetSums2);
        Set<Integer> checker = Arrays.stream(array).boxed().collect(Collectors.toSet());
        HashSet<List<Integer>> result = new HashSet<>();
        ArrayList<Integer[]> resultList = new ArrayList<>();
        for (int targetSum2 : targetSums2) {
            //Arrays.sort(array);
            for (int i = 0; i < array.length; i++) {
                int lookingValue = targetSum2 - array[i];
                if (lookingValue == array[i]
                        || (targetSum - targetSum2 == array[i])
                        || (lookingValue == targetSum - targetSum2))
                    continue;
                if (checker.contains(lookingValue)) {
                    List<Integer> list = Stream.of(lookingValue, array[i], targetSum - targetSum2).collect(Collectors.toList());
                    Collections.sort(list);
                    if(!result.contains(list)) {
                        result.add(list);
                        resultList.add(list.toArray(new Integer[list.size()]));
                    }
                }
            }
        }
        return resultList;
    }
}
