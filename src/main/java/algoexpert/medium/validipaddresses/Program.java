package algoexpert.medium.validipaddresses;

import java.util.ArrayList;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        String input = "1921680";
        Program p = new Program();
        System.out.print(p.validIPAddresses(input));
    }


    public ArrayList<String> validIPAddresses(String string) {
        // Write your code here.
        ArrayList<String> result = new ArrayList<>();
        validIPAddresses(string, 0, result, 0, new StringBuffer(""));
        return result;
    }

    public void validIPAddresses(String string, int start, List<String> result, int depth, StringBuffer sb) {
        if (depth == 3) {
            if (isValidPart(string, start, string.length())) {
                sb.append(string.substring(start, string.length()));
                result.add(sb.toString());
            }
        } else {
            for (int i = 1; i <= 3; i++) {
                int end = start + i;
                if (isValidPart(string, start, end)) {
                    sb.append(string.substring(start + i - 1, end));
                    validIPAddresses(string, start + i, result, depth + 1, new StringBuffer(sb).append("."));
                }
            }
        }
    }


    public boolean isValidPart(String string, int start, int end) {
        if (end > string.length() || start == end) return false;
        String str = string.substring(start, end);
        if (str.startsWith("0")) {
            if(str.length() == 1) return true;
            else return false;
        }
        int value = Integer.valueOf(str);
        if (value <= 255) return true;
        return false;
    }
}
