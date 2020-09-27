package algoexpert.veryhard.binarytreetopologies;

class Program {
    public static void main(String args[]) {
        System.out.println(numberOfBinaryTreeTopologies(7));
    }

    public static int numberOfBinaryTreeTopologies(int n) {
        if (n == 0 || n == 1) return 1;
        if (n == 2) return 2;

        n--;
        int result = 0;
        for (int i = 0; i <= n; i++) {
            int leftResult = 1;
            int rightResult = 1;
            if(n - i>=0) {
                leftResult = numberOfBinaryTreeTopologies(n - i);
            }
            if(i>= 0) {
                rightResult = numberOfBinaryTreeTopologies(i);
            }
            result = result + leftResult * rightResult;

        }
        return result;
    }
}