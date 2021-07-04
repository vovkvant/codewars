package algoexpert.easy.runlengthencoding;

import java.util.HashMap;
import java.util.Map;

//a few easy tasks included here
public class Program {

    public static void main(String args[]) {
        Program p = new Program();
        String characters = "hello worldO";
        String document = "hello wOrld";
        System.out.println(p.generateDocument(characters, document));
    }

    public int firstNonRepeatingCharacter(String string) {
        // Write your code here.
        Map<Character, Integer> map = new HashMap();
        char[] arr = string.toCharArray();
        for (char c : arr) {
            map.compute(c, (k, v) -> v == null ? 1 : v + 1);
        }
        for (int i = 0; i < arr.length; i++) {
            if(map.get(arr[i]) == 1) return i;
        }
        return -1;
    }

    public boolean generateDocument(String characters, String document) {
        // Write your code here.
        Map<Character, Integer> map = new HashMap();
        char[] arr = characters.toCharArray();
        for (char c : arr) {
            map.compute(c, (k, v) -> v == null ? 1 : v + 1);
        }
        arr = document.toCharArray();
        for (char c : arr) {
            Integer counter = map.get(c);
            if ((counter == null || counter == 0) && !Character.valueOf(' ').equals(c)) {
                return false;
            }
            map.computeIfPresent(c, (k, v) -> v - 1);
        }
        return true;
    }

    public String runLengthEncoding(String str) {
        // Write your code here.
        StringBuffer result = new StringBuffer();
        char[] arr = str.toCharArray();
        int counter = 0;
        Character currentChar = null;
        for (int i = 0; i < arr.length; i++) {
            if (currentChar == null) {
                currentChar = arr[i];
                counter++;
            } else if (currentChar.equals(arr[i])) {
                if (counter < 9) {
                    counter++;
                } else {
                    result.append(counter).append(currentChar);
                    counter = 1;
                }
            } else {
                result.append(counter).append(currentChar);
                counter = 1;
                currentChar = arr[i];
            }
        }
        result.append(counter).append(currentChar);
        return result.toString();
    }
}
