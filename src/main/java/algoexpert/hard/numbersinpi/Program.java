package algoexpert.hard.numbersinpi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Program {
    public static void main(String args[]) {
        String PI = "3141592653589793238462643383279";
        String[] numbers =
                new String[]{
                        "314159265358979323846", "26433", "8", "3279", "314159265", "35897932384626433832", "79"
                };
        String[] numbers1 =
                new String[]{
                        "3", "314", "49", "9001", "15926535897", "14", "9323", "8462643383279", "4", "793"
                };
        System.out.println(numbersInPi(PI, numbers));
        System.out.println(numbersInPi(PI, numbers1));
    }


    //final solution
    public static int numbersInPi(String pi, String[] numbers) {
        Set<String> numberSet = new HashSet<>();
        Arrays.stream(numbers).forEach(n -> numberSet.add(n));
        Map<Integer, Integer> cache = new HashMap<>();
        int result = subNumbersInPi(numberSet, 0, pi, cache);
        return result == Integer.MAX_VALUE ? -1 : result;
    }


    public static int subNumbersInPi(Set<String> numberSet, int index, String pi, Map<Integer, Integer> cache) {
        if (index == pi.length()) return -1;
        if (cache.containsKey(index)) return cache.get(index);
        int minCounter = Integer.MAX_VALUE;
        for (int i = index; i <= pi.length(); i++) {
            String sub = pi.substring(index, i);
            if (numberSet.contains(sub)) {
                int restCounter = subNumbersInPi(numberSet, i, pi, cache);
                if (restCounter == Integer.MAX_VALUE) {
                    minCounter = Math.min(minCounter, restCounter);
                } else {
                    minCounter = Math.min(minCounter, restCounter + 1);
                }
            }
        }
        cache.put(index, minCounter);
        return minCounter;
    }

    //first solution
    public static int numbersInPi2(String pi, String[] numbers) {
        Set<String> numberSet = new HashSet<>();
        Arrays.stream(numbers).forEach(n -> numberSet.add(n));
        List<Integer> results = new ArrayList<>();
        subNumbersInPi2(numberSet, 0, pi, results, 0);
        System.out.println(results);
        return results.stream().min(Integer::compareTo).orElseGet(() -> -1);
    }

    public static void subNumbersInPi2(Set<String> numberSet, int index, String pi, List<Integer> results, int counter) {
        if (index == pi.length()) results.add(counter - 1);
        for (int i = index; i <= pi.length(); i++) {
            String sub = pi.substring(index, i);
            if (numberSet.contains(sub)) {
                subNumbersInPi2(numberSet, i, pi, results, counter + 1);
            }
        }
        return;
    }
}
