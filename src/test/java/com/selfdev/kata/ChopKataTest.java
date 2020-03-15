package com.selfdev.kata;


import org.junit.Assert;
import org.junit.Test;

public class ChopKataTest {

    @Test
    public void test(){
        System.out.println(100);
        Assert.assertEquals(-1, ChopKata.chop(3,  new int[0]));
        Assert.assertEquals(-1, ChopKata.chop(3,  new int[]{1}));
        Assert.assertEquals(0, ChopKata.chop(1,  new int[]{1}));

        Assert.assertEquals(0,  ChopKata.chop(1, new int[]{1, 3, 5}));
        Assert.assertEquals(1,  ChopKata.chop(3, new int[]{1, 3, 5}));
        Assert.assertEquals(2,  ChopKata.chop(5, new int[]{1, 3, 5}));

        Assert.assertEquals(-1, ChopKata.chop(0, new int[]{1, 3, 5}));
        Assert.assertEquals(-1, ChopKata.chop(2, new int[]{1, 3, 5}));
        Assert.assertEquals(-1, ChopKata.chop(4, new int[]{1, 3, 5}));
        Assert.assertEquals(-1, ChopKata.chop(6, new int[]{1, 3, 5}));

        Assert.assertEquals(0,  ChopKata.chop(1, new int[]{1, 3, 5, 7}));
        Assert.assertEquals(1,  ChopKata.chop(3, new int[]{1, 3, 5, 7}));
        Assert.assertEquals(2,  ChopKata.chop(5, new int[]{1, 3, 5, 7}));

        Assert.assertEquals(3,  ChopKata.chop(7, new int[]{1, 3, 5, 7}));
        Assert.assertEquals(-1, ChopKata.chop(0, new int[]{1, 3, 5, 7}));
        Assert.assertEquals(-1, ChopKata.chop(2, new int[]{1, 3, 5, 7}));

        Assert.assertEquals(-1, ChopKata.chop(4, new int[]{1, 3, 5, 7}));
        Assert.assertEquals(-1, ChopKata.chop(6, new int[]{1, 3, 5, 7}));
        Assert.assertEquals(-1, ChopKata.chop(8, new int[]{1, 3, 5, 7}));

        Assert.assertEquals(-1, ChopKata.chop(4, new int[]{1, 3, 5, 7, 9}));
        Assert.assertEquals(-1, ChopKata.chop(6, new int[]{1, 3, 5, 7, 9}));
        Assert.assertEquals(-1, ChopKata.chop(8, new int[]{1, 3, 5, 7, 9}));

        Assert.assertEquals(4, ChopKata.chop(9, new int[]{1, 3, 5, 7, 9}));
    }

}
