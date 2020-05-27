package com.selfdev.anagrams;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Anagrams {

    public static void main(String args[]) {

        System.out.println(listPosition("A"));
        System.out.println(listPosition("ABAB"));
        System.out.println(listPosition("AAAB"));
        System.out.println(listPosition("BAAA"));
        System.out.println(listPosition("QUESTION"));
        System.out.println(listPosition("BOOKKEEPER"));


    }

    static BigInteger listPosition(String s) {
        if (s.length() == 1) {
            return BigInteger.ONE;
        }

        List<Character> list =  s.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        Character sortedArr[] = list.toArray(new Character[list.size()]);
        Arrays.sort(sortedArr);
        List<Integer> repeated = calculateRepeated(s.toCharArray());

        BigInteger d = BigInteger.ONE;
        for(Integer x:repeated) {
            d = d.multiply(fact(BigInteger.valueOf(x)));
        }
        //System.out.println("s=" + s);
        BigInteger variants = fact(BigInteger.valueOf(sortedArr.length - 1)).divide(d);
        //System.out.println("variants=" + variants);
        int index = findIndex(sortedArr, s.toCharArray()[0]);
        BigInteger result = BigInteger.valueOf(index).multiply(variants);
        //System.out.println(result);
        result = result.add(listPosition(s.substring(1)));
        return result;
    }

    static List<Integer> calculateRepeated(char sortedArr[]) {
        HashMap<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < sortedArr.length; i++) {
            counter.merge(sortedArr[i], 1, (oldVal, newVal) -> oldVal + newVal);
        }
        List<Integer> result = counter.entrySet().stream().filter(e -> e.getValue() > 1)
                .map(e -> e.getValue()).collect(Collectors.toList());
        return result;
    }

    static int findIndex(Character arr[], Character input) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(input)) {
                return i;
            }
        }
        return -1;
    }

    static BigInteger fact(BigInteger input) {
        if (input.compareTo(BigInteger.ZERO) == 0) {
            return BigInteger.ONE;
        }
        return fact(input.subtract(BigInteger.ONE)).multiply(input);
    }
}
