package algoexpert.hard.maximizeexpression;

import java.util.Arrays;

public class Program {

    public static void main(String[] args) {
        int[] input = new int[]{3, 6, 1, -3, 2, 7};
        Program p = new Program();
        //System.out.println(p.maximizeExpression(input));
        int[] input2 = new int[]{1, -1, 1, -1, -2};
        System.out.println(p.maximizeExpression(input2));
        //int[] input3 = new int[] {0, 0, 0, 0, 0, 0, 0, 1, 1, 0};
        //System.out.println(p.maximizeExpression(input3));

    }

    public int maximizeExpression(int[] array) {
        if (array.length < 4) return 0;
        int[] original = Arrays.copyOf(array, array.length);
        String operation[] = new String[]{"-", "+", "-"};
        int i = 0;
        Integer lastValue = Integer.MIN_VALUE;
        for (; i < array.length; i++) {
            if (array[i] > lastValue) {
                lastValue = array[i];
            } else {
                array[i] = lastValue;
            }
        }

        i = 1;
        for (String op : operation) {
            lastValue = Integer.MIN_VALUE;
            for (int j = 0; j < array.length; j++) {
                if (j + i < original.length) {
                    int result;
                    if ("-".equals(op)) {
                        result = array[j] - original[j + i];
                    } else {
                        result = array[j] + original[j + i];
                    }
                    if (result > lastValue) {
                        array[j] = result;
                        lastValue = result;
                    } else {
                        array[j] = lastValue;
                    }

                } else {
                    array[j] = Integer.MIN_VALUE;
                }
            }
            i++;
        }
        return Arrays.stream(array).max().getAsInt();
    }
}
