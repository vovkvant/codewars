package com.selfdev.philosoph;

import java.util.Arrays;

/**
 * Created by vovkv on 4/30/2018.
 */
public class Test1 {




    public static void main(String args[]) {
        System.out.println("Hello");
        //int array[][] = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
        //int array[][] = new int[][]{{1,2}, {3,4}};
        //int array[][] = new int[][]{{1}};
        //int array[][] = new int[][]{{1,2,3, 4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
        int array[][] = new int[][]{{}};
        int[] res = snail(array);
    /*
        Arrays.stream(res).forEach(x -> {
            System.out.println(x);
        });
        */
    }


    public static int[] snail(int[][] array) {
        // enjoy
        int height = array.length;
        int width = 0;
        if (height > 0) {
            width = array[0].length;
        }
        int result[] = new int[width * height];
        if (width == 0) {
            return  result;
        }

            System.out.println("Result length: " + width * height);
        int delta[] = new int[width * 2 - 1];
        delta[0] = width;

        for (int i = 1; i < delta.length; i++) {
            width--;
            delta[i] = width;
            i++;
            delta[i] = width;
        }

        String[] directionsArr = new String[]{"left", "down", "right", "up"};
        int resultIndex = 0;
        int x = 0;
        int y = 0;
        for (int i = 0; i < delta.length; i++) {
            int sideDelta = delta[i];
            String direction = directionsArr[i % 4];
            for (int k = 0; k < sideDelta; k++) {
                result[resultIndex] = array[y][x];
                resultIndex++;
                if (k == sideDelta - 1) {
                    // change direction
                    direction = directionsArr[(i + 1) % 4];
                }
                /*
                switch (direction) {
                    case "left":
                        x++;
                        break;
                    case "down":
                        y++;
                        break;
                    case "right":
                        x--;
                        break;
                    case "up":
                        y--;
                        break;
                }
                */


            }
        }


        return result;
    }
}
