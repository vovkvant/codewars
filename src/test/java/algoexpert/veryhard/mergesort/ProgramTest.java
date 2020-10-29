package algoexpert.veryhard.mergesort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ProgramTest {

    @Test
    public void testCase1() {
        int[] array = new int[]{8, 5, 2, 9, 5, 6, 3};
        int[] result = Program.mergeSort(array);
        System.out.println(Arrays.toString(result));
        Assert.assertArrayEquals(new int[]{2, 3, 5, 5, 6, 8, 9}, result);
    }

    @Test
    public void testCase2() {
        int[] array = new int[]{8};
        int[] result = Program.mergeSort(array);
        Assert.assertArrayEquals(new int[]{8}, result);
    }

    @Test
    public void testCase3() {
        int[] array = new int[]{8, 8, 5, 5};
        int[] result = Program.mergeSort(array);
        Assert.assertArrayEquals(new int[]{8}, result);
    }
}
