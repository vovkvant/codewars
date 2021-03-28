package algoexpert.hard.interweavingstrings;

public class Program {

    public static void main(String[] args) {
        //String str1 = "algoexpert";
        //String str2 = "your-dream-job";
        //String str3 = "your-algodream-expertjob";

        String str1 = "aaaaaaa";
        String str2 = "aaaaaaaaaaaaaa";
        String str3 = "aaaaaaa";

        System.out.println(interweavingStrings(str1, str2, str3));
    }

    public static boolean interweavingStrings(String one, String two, String three) {
        // Write your code here.
        char[] arr3 = three.toCharArray();
        char[] arr2 = two.toCharArray();
        char[] arr1 = one.toCharArray();
        return subSearch(0, 0, 0, arr1, arr2, arr3);
    }

    private static boolean subSearch(int k, int i, int j, char[] arr1, char[] arr2, char[] arr3) {
        boolean result = false;
        for (; k < arr3.length; k++) {
            boolean first = false;
            if (i < arr1.length && arr3[k] == arr1[i]) {
                first = true;
                i++;
            }
            boolean second = false;
            if (j < arr2.length && arr3[k] == arr2[j]) {
                second = true;
                j++;
            }
            if (first && second) {
                if (subSearch(k+1, i - 1, j, arr1, arr2, arr3)) i--;
                if (subSearch(k+1, i, j - 1, arr1, arr2, arr3)) j--;
                continue;
            } else {
                result = first || second;
            }
            if (!result) {
                break;
            }
        }
        return result && i == arr1.length && j == arr2.length || arr3.length == j;
    }
}
