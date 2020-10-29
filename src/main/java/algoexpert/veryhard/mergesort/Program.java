package algoexpert.veryhard.mergesort;

//repeat it again from scratch
public class Program {
    static int[] copy;

    public static int[] mergeSort(int[] array) {
        // Write your code here.
        copy = new int[array.length];
        sort(array, 0, array.length - 1);
        return array;
    }

    private static void sort(int[] array, int lo, int hi) {
        if(lo >= hi) return;
        int mid = lo + (hi - lo)/2;
        sort(array, lo, mid);
        sort(array, mid+1, hi);
        merge(array, lo, mid, hi);
    }

    private static void merge(int[] array, int start, int mid, int end) {
        for(int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        int i = start;
        int j = mid+1;
        for (int k = start; k <= end; k++) {
            if(i > mid) array[k] = copy[j++]; else
            if(j > end) array[k] = copy[i++]; else
            if(copy[i] > copy[j]) {
                array[k] = copy[j++];
            } else {
                array[k] = copy[i++];
            }
        }
    }
}
