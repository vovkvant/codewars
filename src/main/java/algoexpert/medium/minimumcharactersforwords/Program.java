package algoexpert.medium.minimumcharactersforwords;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program {

    public static void main(String[] args) {
        String[] words = new String[]{"this", "that", "did", "deed", "them!", "a"};
        System.out.println(Arrays.toString(new Program().minimumCharactersForWords(words)));
    }

    public char[] minimumCharactersForWords(String[] words) {
        // Write your code here.
        List<Map<Character, Integer>> list = new ArrayList<>();
        Arrays.stream(words).forEach(w -> {
            Map<Character, Integer> map = new HashMap();
            for (Character ch : w.toCharArray()) {
                map.compute(ch, (k, v) -> v == null ? 1 : v + 1);
            }
            list.add(map);
        });

        Map<Character, Integer> charFrequency = list.stream().reduce((k1, k2) -> {
            Map<Character, Integer> map = new HashMap();
            Stream.concat(k1.keySet().stream(), k2.keySet().stream())
                    .collect(Collectors.toSet())
                    .stream()
                    .forEach(k -> {
                        int maxValue = Math.max(k1.getOrDefault(k, 0), k2.getOrDefault(k, 0));
                        map.put(k, maxValue);
                    });
            return map;
        }).orElseGet(() -> new HashMap<>());

        List<Character> charList = new ArrayList<>();
        for (Map.Entry<Character, Integer> e : charFrequency.entrySet()) {
            for (int i = 0; i < e.getValue(); i++) {
                charList.add(e.getKey());
            }
        }
        char[] result =new char[charList.size()];
        for(int i = 0; i < result.length; i++) {
            result[i] = charList.get(i);
        }
        return result;
    }
}
