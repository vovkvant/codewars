package algoexpert.veryhard.longeststringchain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {

    public static void main(String args[]) {
        List<String> strings =
                new ArrayList<String>(
                        Arrays.asList("abde", "abc", "abd", "abcde", "ade", "ae", "1abde", "abcdef"));
        List<String> strings1 =
                new ArrayList<String>(
                        Arrays.asList("abcdefg1", "1234c", "abdefg2", "abdfg", "123", "122", "bgg", "g", "1a2345", "12a345"));
        System.out.println(longestStringChain(strings1));
    }

    public static List<String> longestStringChain(List<String> strings) {
        strings.sort((o1, o2) -> {
            if (o1.length() > o2.length()) return 1;
            if (o1.length() < o2.length()) return -1;
            return 0;
        });
        System.out.println(strings);
        Integer[] sequenceSize = new Integer[strings.size()];
        Integer[] sequence = new Integer[strings.size()];

        for (int i = 0; i < strings.size(); i++) {
            String str = strings.get(i);
            for (int k = 0; k < str.length(); k++) {
                String mutated = new StringBuffer(str).deleteCharAt(k).toString();
                for (int j = 0; j < i; j++) {
                    if (mutated.equals(strings.get(j))) {
                        if (sequenceSize[i] == null) {
                            sequenceSize[i] = 1;
                            sequence[i] = j;
                            if (sequenceSize[j] != null) {
                                sequenceSize[i] += sequenceSize[j];
                            }
                        } else {
                            if (sequenceSize[j] != null && (sequenceSize[j] + 1) > sequenceSize[i]) {
                                sequenceSize[i] = sequenceSize[j] + 1;
                                sequence[i] = j;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.toString(sequence));
        System.out.println(Arrays.toString(sequenceSize));
        Integer maxIndex = -1;
        Integer max = -1;
        for (int i = 0; i < sequenceSize.length; i++) {
            if (sequenceSize[i] != null && sequenceSize[i] > max) {
                max = sequenceSize[i];
                maxIndex = i;
            }
        }
        System.out.println(max);
        System.out.println(maxIndex);
        List<String> result = new ArrayList<>();
        while (maxIndex != null && maxIndex != -1) {
            result.add(strings.get(maxIndex));
            maxIndex = sequence[maxIndex];
        }
        return result;
    }
}
