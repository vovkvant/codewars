package algoexpert.hard.longestcommon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Program {

    public static void main(String args[]) {
        //String str1 = "ZXVVYZW";
        //String str2 = "XKYKZPW";
        String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String str2 = "CCCDDEGDHAGKGLWAJWKJAWGKGWJAKLGGWAFWLFFWAGJWKAG";
        //String str1 = "clement";
        //String str2 = "antoine";
        //String str2 = "282432";
        //String str1 = "CDEFGHIJKLMNOPQRSTUVW";//XYZ";
        //String str2 = "CDDEGDHAGKGLWAJWKJAWGKGWJAKLGGWAF";
        //AFWLFFWAGJWKAG";
        //System.out.println(longestCommonSubsequence(str2, str1));
        System.out.println(longestCommonSubsequence(str1, str2));
        //"C", "D", "E", "G", "H", "J", "K", "L", "W"
        //C, D, E, F, G, J, K
    }

    public static List<Character> longestCommonSubsequence(String str1, String str2) {
        if (str1.isEmpty() || str2.isEmpty()) return new ArrayList<Character>();
        // Write your code here.
        char[] strArr1 = str1.toCharArray();
        char[] strArr2 = str2.toCharArray();
        LinkedList<LinkedList<Integer>> subLists = new LinkedList<>();
        List<Integer> longestSublist = null;

        for (int i = 0; i < strArr1.length; i++) {
            for (int j = 0; j < strArr2.length; j++) {
                if (strArr1[i] == strArr2[j]) {
                    LinkedList<Integer> sublist = null;
                    if (!subLists.isEmpty()) {
                        for (int k = 0; k < subLists.size(); k++) {
                            int index = subLists.get(k).peekLast();
                            if (index < j && strArr2[index] != strArr2[j]) {
                                sublist = new LinkedList<>(subLists.get(k));
                                sublist.add(j);
                                if (sublist.size() > longestSublist.size()) {
                                    longestSublist = sublist;
                                }
                            }
                        }
                    }
                    if (sublist == null) {
                        sublist = new LinkedList<>();
                        sublist.add(j);
                        subLists.add(sublist);
                        if (longestSublist == null) {
                            longestSublist = sublist;
                        }
                    } else {
                        subLists.add(sublist);
                    }
                }
            }
        }
        for(List<Integer> sub:subLists) {
            List<Character> result = new ArrayList<>();
            for(Integer i : sub) {
                result.add(strArr2[i]);
            }
            System.out.println(result);
        }
        List<Character> result = new ArrayList<>();
        for(Integer i : longestSublist) {
            result.add(strArr2[i]);
        }
        return result;
    }

}
