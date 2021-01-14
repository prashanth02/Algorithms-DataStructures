package com.arrays;
/*
    Given a list of daily stock prices (integers for simplicity),
    return the buy and sell prices for making the maximum profit.

    My approach : Just find min & max looping an array once O(n), need to hold 2 variables

    The values in the array represent the cost of a stock each day.
    As we can buy and sell the stock only once, we need to find the best buy and sell prices for
    which our profit is maximized (or loss is minimized) over a given span of time.

    A naive solution, with runtime complexity of O(n^2), is to find the maximum gain between each element and its succeeding elements.

    There is a tricky linear solution to this problem that requires maintaining
    current_buy_price (which is the smallest number seen so far), current_profit, and global_profit
    as we iterate through the entire array of stock prices. At each iteration,
    we will compare the current_profit with the global_profit and update the global_profit accordingly.
 */
class Tuple<X, Y> {
    public X x;
    public Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }
}

class StockPrices{
    public static Tuple findBuySellStockPrices(int[] array) {
        if(array == null || array.length < 2) {
            return null;
        }

        int current_buy = array[0];
        int global_sell = array[1];
        int global_profit = global_sell - current_buy;

        int current_profit = Integer.MIN_VALUE;

        for(int i=1; i<array.length; i++) {
            current_profit = array[i] - current_buy;

            if(current_profit > global_profit) {
                global_profit = current_profit;
                global_sell = array[i];
            }

            if(current_buy > array[i]) {
                current_buy = array[i];
            }
        }

        Tuple<Integer, Integer> result =
                new Tuple<Integer, Integer>(global_sell - global_profit, global_sell);

        return result;
    }
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 3, 2, 1, 2, 5};
        Tuple result = null;
        result = findBuySellStockPrices(array);
        System.out.println(String.format("Buy Price: %d, Sell Price: %d", result.x, result.y));

        int[] array2 = {8, 6, 5, 4, 3, 2, 1};
        result = findBuySellStockPrices(array2);
        System.out.println(String.format("Buy Price: %d, Sell Price: %d", result.x, result.y));

    }
}