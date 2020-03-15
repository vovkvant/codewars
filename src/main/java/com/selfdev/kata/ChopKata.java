package com.selfdev.kata;

public class ChopKata {

    static int chop(int target, int arr[]) {
        if (arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length;
        int arrLength = arr.length;
        int arrMiddle = (arrLength - (arrLength % 2)) / 2;

        /*
        4
        1, 3, 5, 7
        right = 2;
        left = 1;

        */

        while (true) {
            if (arrMiddle < arr.length && target == arr[arrMiddle])
                return arrMiddle;
            if(arrMiddle == 0 || (arrMiddle == arr.length - 1)) {
                return -1;
            }

            if (target > arr[arrMiddle]) {
                left = arrMiddle;
                arrMiddle = ((arrLength + arrMiddle) - ((arrLength + arrMiddle) % 2)) / 2;
                if(arrMiddle >= right) return -1;
            } else {
                right = arrMiddle;
                arrMiddle = (arrMiddle - (arrMiddle % 2)) / 2;
                if(arrMiddle < left) return -1;
            }
        }
    }


    public static void main(String args[]) {
        System.out.println(1 / 2);
    }


}
