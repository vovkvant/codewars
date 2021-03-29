package algoexpert.hard.longestsubstring;

import java.util.HashMap;

public class Program {

    public static void main(String[] args) {
        String str = "a";
        System.out.println(longestSubstringWithoutDuplication(str));
    }

    public static String longestSubstringWithoutDuplication(String str) {
        // Write your code here
        char[] arr = str.toCharArray();
        HashMap<Character, Integer> repeated = new HashMap<>();
        String longestSubstring = "";
        StringBuffer substring = new StringBuffer("");
        for(int i = 0; i < arr.length; i++) {
            if(repeated.containsKey(arr[i])) {
                if(substring.length() > longestSubstring.length()) {
                    longestSubstring = substring.toString();
                }
                i = repeated.get(arr[i]);
                substring.setLength(0);
                repeated.clear();
            } else {
                repeated.put(arr[i], i);
                substring.append(arr[i]);
            }
        }

        if(substring.length() > longestSubstring.length()) {
            longestSubstring = substring.toString();
        }

        return longestSubstring;
    }
}
