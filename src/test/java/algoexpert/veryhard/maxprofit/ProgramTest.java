package algoexpert.veryhard.maxprofit;

import org.junit.Assert;
import org.junit.Test;

public class ProgramTest {

    @Test
    public void test_basic(){
        //{"k": 1, "prices": []}
        int[] prices = {5, 11, 3, 50};
        Assert.assertEquals(47, Program.maxProfitWithKTransactions(prices, 1));
    }

    @Test
    public void test1(){
        //{"k": 1, "prices": []}
        int[] prices = {};
        Assert.assertEquals(0, Program.maxProfitWithKTransactions(prices, 1));
    }

    @Test
    public void test2() {
        //{"k": 3, "prices": [1, 10]}
        int[] prices = {1, 10};
        Assert.assertEquals(9, Program.maxProfitWithKTransactions(prices, 3));
    }

    @Test
    public void test3() {
        //{"k": 3, "prices": [5, 11, 3, 50, 60, 90]}
        int[] prices = {5, 11, 3, 50, 60, 90};
        Assert.assertEquals(93, Program.maxProfitWithKTransactions(prices, 3));
    }
}
