package algoexpert.hard.underscorifysubstring;

import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Program {

    public static void main(String str[]) {
        String input = "testthis is a testtest to see if testestest it works";
        String sub = "test";
        System.out.print(underscorifySubstring(input, sub));
    }

    //TODO how to make it more beatiful?
    public static String underscorifySubstring(String str, String substring) {
        // Write your code here.
        String input = str;
        List<Integer[]> indexes = new ArrayList<>();
        int start = 0;
        int shift = 0;
        while (start != -1) {
            start = str.indexOf(substring);
            if(start!=-1) {
                Integer[] index = new Integer[]{start + shift, start+substring.length() + shift};
                indexes.add(index);
                shift+=(start + 1);
                str = str.substring(start + 1);
            }
        }

        LinkedList<Integer[]> simplify = new LinkedList<>();
        int lastIndex = -1;
        for(Integer arr[] : indexes) {
            if(lastIndex == -1 || lastIndex < arr[0]) {
                lastIndex = arr[1];
                simplify.add(arr);
            } else {
                lastIndex = arr[1];
                simplify.getLast()[1] = lastIndex;
            }
        }

        StringBuffer sb = new StringBuffer("");
        lastIndex = 0;
        for(Integer[] arr: simplify) {
            sb.append(input, lastIndex, arr[0]);
            sb.append("_");
            sb.append(input, arr[0], arr[1]);
            sb.append("_");
            lastIndex = arr[1];
        }
        sb.append(input.substring(lastIndex));

        return sb.toString();
    }


}
