package algoexpert.medium.minnumberofcoinsforchange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {
    //TODO investigate solution from algoexpert
    public static void main(String args[]) {
        int[] input = new int[]{1, 2, 3};
        System.out.println(minNumberOfCoinsForChange(0, input));
    }

    public static int minNumberOfCoinsForChange(int n, int[] denoms) {
        if(n == 0) return 0;
        int[] count = new int[denoms.length];
        List<Integer> result = new ArrayList<>();
        loop(n, denoms, count, 0, result);
        System.out.println(result);
        return result.stream().min(Integer::compareTo).orElse(-1);
    }

    static void loop(int n, int[] denoms, int[] count, int k, List<Integer> result) {
        if (k < denoms.length) {
            int sum = 0;
            while (sum <= n) {
                loop(n, denoms, Arrays.copyOf(count, count.length), k + 1, result);
                count[k]++;
                sum = calculateSum(denoms, count);
                if (sum == n) {
                    result.add(Arrays.stream(count).sum());
                }
            }
        } else return;
    }

    static int calculateSum(int[] denoms, int[] count) {
        int sum = 0;
        for (int i = 0; i < denoms.length; i++) {
            sum += denoms[i] * count[i];
        }
        return sum;
    }


}
