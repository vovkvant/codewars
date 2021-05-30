package algoexpert.veryhard.palindromepartitioningmincuts;

public class Program {

    public static void main(String args[]) {
        //noonabbad
        //System.out.println(palindromePartitioningMinCuts("noon"));
        System.out.println(palindromePartitioningMinCuts("noonabbad"));
        System.out.println(palindromePartitioningMinCuts("abcbm"));
    }


    public static int palindromePartitioningMinCuts(String str) {
        boolean palindromicity[][] = new boolean[str.length()][str.length()];
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                palindromicity[i][j] = isPalindrome(str.substring(i, j + 1));
            }
        }

        int cuts[] = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            if (palindromicity[0][i]) {
                cuts[i] = 0;
            } else {
                cuts[i] = cuts[i-1] + 1;
                for (int j = 1; j < i; j++) {
                    if(palindromicity[j][i]) {
                        if (cuts[j-1] < cuts[i]) cuts[i] = cuts[j-1] + 1;
                    }

                }
            }
        }
        return cuts[str.length() - 1];
    }

    // TODO write 2nd solution for finding palindrome
    static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length(); i++) {
            int j = str.length() - i - 1;
            if (str.charAt(i) != str.charAt(j)) return false;
        }
        return true;
    }


}
