package com.selfdev.orderkata;

/**
 * Created by vovkv on 8/9/2019.
 */
public class SortMenuKata {

    final static String[] MENU = new String[]{"Burger", "Fries", "Chicken", "Pizza",
            "Sandwich", "Onionrings", "Milkshake", "Coke"};

    public static String getOrder(String input) {
        //how to make it more optimal?
        StringBuffer result = new StringBuffer("");
        while (input.length() > 0) {
            for (String item : MENU) {
                if (input.startsWith(item.toLowerCase())) {
                    result.append(item).append(" ");
                    input = input.substring(item.length());
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println("Hello");
        long startTime = System.currentTimeMillis();
        String input = "milkshakepizzachickenfriescokeburgerpizzasandwichmilkshakepizza";
        System.out.println(getOrder(input));
        long timing = System.currentTimeMillis() - startTime;
        System.out.println("Timimng: " + timing);

    }
}
