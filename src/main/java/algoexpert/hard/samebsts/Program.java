package algoexpert.hard.samebsts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {

    public static void main(String args[]) {
        List<Integer> arrayOne = new ArrayList<Integer>(Arrays.asList(10, 15, 8, 12, 94, 81, 5, 2, 11));
        List<Integer> arrayTwo = new ArrayList<Integer>(Arrays.asList(10, 8, 5, 15, 2, 12, 11, 94, 81));
        //System.out.print(Program.sameBsts(arrayOne, arrayTwo));
        //System.out.print(Program.sameBsts(arrayOne, arrayTwo, 0, 0));
    }

    //TODO make it more optimal
    public static boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo) {
        if (arrayOne.size() != arrayTwo.size()) return false;
        if (arrayOne.size() == 0 || arrayTwo.size() == 0) {
            return true;
        }
        // Write your code here.
        if (arrayOne.get(0) == arrayTwo.get(0)) {
            List<Integer> newArrayOne_1 = new ArrayList<>();
            List<Integer> newArrayOne_2 = new ArrayList<>();
            List<Integer> newArrayTwo_1 = new ArrayList<>();
            List<Integer> newArrayTwo_2 = new ArrayList<>();
            for (int i = 1; i < arrayOne.size(); i++) {
                if (arrayOne.get(i) > arrayOne.get(0)) {
                    newArrayOne_1.add(arrayOne.get(i));
                } else {
                    newArrayOne_2.add(arrayOne.get(i));
                }
                if (arrayTwo.get(i) > arrayTwo.get(0)) {
                    newArrayTwo_1.add(arrayTwo.get(i));
                } else {
                    newArrayTwo_2.add(arrayTwo.get(i));
                }
            }
            return sameBsts(newArrayOne_1, newArrayTwo_1) && sameBsts(newArrayOne_2, newArrayTwo_2);
        }
        return false;
    }

    /*
    public static boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo,
                                   int max, int min) {
        if (arrayOne.size() != arrayTwo.size()) return false;
        // Write your code here.
        if (arrayOne.get(arrayOneIndex) == arrayTwo.get(arrayTwoIndex)) {
            Integer arrayOne_1_index = null;
            Integer arrayOne_2_index = null;
            Integer arrayTwo_1_index = null;
            Integer arrayTwo_2_index = null;
            for (int i = arrayOneIndex + 1; i < arrayOne.size(); i++) {
                if (arrayOne.get(i) > arrayOne.get(arrayOneIndex)) {
                    if(arrayOne_1_index == null) {
                        arrayOne_1_index = i;
                    }
                } else if (arrayOne_2_index == null) {
                    arrayOne_2_index = i;
                }
                if (arrayOne_1_index != null && arrayOne_2_index != null) break;
            }
            for (int i = arrayTwoIndex + 1; i < arrayOne.size(); i++) {
                if (arrayTwo.get(i) > arrayTwo.get(0)) {
                    if(arrayTwo_1_index == null) {
                        arrayTwo_1_index = i;
                    }
                } else if (arrayTwo_2_index == null) {
                    arrayTwo_2_index = i;
                }
                if (arrayTwo_1_index != null && arrayTwo_2_index != null) break;
            }
            return sameBsts(arrayOne, arrayTwo, arrayOne_1_index, arrayTwo_1_index) &&
                    sameBsts(arrayOne, arrayTwo, arrayOne_2_index, arrayTwo_2_index);
        }

        return false;
    }

     */


}
