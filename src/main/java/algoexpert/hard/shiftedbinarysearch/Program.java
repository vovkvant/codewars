package algoexpert.hard.shiftedbinarysearch;

public class Program {

    public static void main(String str[]) {
        int[] arr = new int[]{45, 61, 71, 72, 73, 0, 1, 21, 33, 37};
        System.out.println(shiftedBinarySearch(arr, 33));

        int[] arr1 = new int[]{5, 23, 111, 1};
        System.out.println(shiftedBinarySearch(arr1, 111));

        //int[] arr2 = new int[]{23, 111, 1, 5};
        //System.out.println(shiftedBinarySearch(arr2, 35));
    }

    public static int shiftedBinarySearch(int[] array, int target) {
        int start = 0;
        int end = array.length - 1;
        while(start <= end) {
            int mid = (start + end)/2;
            if(array[mid] == target) {
                return mid;
            } else if(array[mid] > target) {
                if(array[start] > target
                        && array[end] < array[mid]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                if(array[end] < target
                        && array[end] > array[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }

            }
        }
        return -1;
    }
}
