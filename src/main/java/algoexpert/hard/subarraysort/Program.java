package algoexpert.hard.subarraysort;

import java.util.Arrays;

class Program {
    public static void main(String args[]) {
        //int[] arr = new int[]{1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
        int[] arr = new int[]{2, 1};
        System.out.println(Arrays.toString(subarraySort(arr)));
    }

    public static int[] subarraySort(int[] array) {
        // Write your code here.
        int[] boundary = new int[]{-1, -1};
        int lastMax = array[0];
        for (int i = 0; i < array.length - 1; i++) {
            if (lastMax < array[i + 1]) {
                lastMax = array[i + 1];
                continue;
            } else {
                int index = i + 1;
                // find position
                // TODO i don't like this solution
                for (int j = index - 1; j >= 0; j--) {
                    if(j == 0 && array[index] < array[j]) {
                        if (j < boundary[0] || boundary[0] == -1) boundary[0] = j;
                        if (index > boundary[1]) boundary[1] = index;
                        break;
                    } else if (array[j] > array[index]) {
                        continue;
                    }  else {
                        if (j < boundary[0] || boundary[0] == -1) boundary[0] = j+1;
                        if (index > boundary[1]) boundary[1] = index;
                        break;
                    }
                }
            }
        }
        return boundary;
    }
}