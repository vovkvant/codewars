package algoexpert.medium.firstduplicatevalue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Program {

    public static void main(String args[]) {
        int[] input = new int[] {2, 1, 5, 3, 3, 2, 4};
        Program p = new Program();
        System.out.println(p.firstDuplicateValue(input));
    }

    public int firstDuplicateValue(int[] array) {
        Set<Integer> values = new HashSet<>();
        // Write your code here.
        for (int i = 0; i < array.length; i++) {
            if (values.contains(array[i])) {
                return array[i];
            } else {
                values.add(array[i]);
            }
        }
        return -1;
    }
}
