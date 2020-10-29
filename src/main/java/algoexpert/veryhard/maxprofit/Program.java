package algoexpert.veryhard.maxprofit;

public class Program {
    public static void main(String args[]) {
        int[] prices = {5, 11, 3, 50, 60, 90};
        System.out.println(maxProfitWithKTransactions(prices, 2));
    }

    public static int maxProfitWithKTransactions(int[] prices, int k) {
        return maxProfit1(prices, 0, prices.length, 1, k);
    }

    public static int maxProfit1(int[] prices, int from, int to, int curK, int k) {
        if (k == curK) {
            int max = 0;
            int min = Integer.MAX_VALUE;
            int lastMaxProfit = 0;
            for (int i = from; i < to; i++) {
                if (prices[i] < min) {
                    if(min == Integer.MAX_VALUE) {
                        min = 0;
                    }
                    int profit = max - min;
                    if(profit > lastMaxProfit) lastMaxProfit = profit;
                    max = 0;
                    min = prices[i];
                }
                if (prices[i] >= max) max = prices[i];
            }
            if(min == Integer.MAX_VALUE) {
                min = 0;
            }
            int profit = max - min;
            if(profit > lastMaxProfit) lastMaxProfit = profit;

            return lastMaxProfit;
        } else {
            int maxProfit = 0;
            int profit_ = maxProfit1(prices, from, to, curK + 1, k);
            maxProfit = profit_;
            for (int i = from + 2; i < to; i++) {
                int profit_1 = maxProfit1(prices, from, i, curK + 1, k);
                int profit_2 = maxProfit1(prices, i, to, curK + 1, k);
                if (profit_1 + profit_2 > maxProfit) maxProfit = profit_1 + profit_2;
            }
            return maxProfit;
        }
    }

}
