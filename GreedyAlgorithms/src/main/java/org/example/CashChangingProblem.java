package org.example;

import java.util.Arrays;

/**
 * Task text:
 * Vending machine bug
 *
 * When people buy item in vending machine, sometimes there is a need of change.
 * Your colleague released an updated version of ATM software and went on vacation.
 * Unfortunately, after update, one of the functions for change process works incorrectly.
 * It returns a wrong message in all cases.
 *
 * Check the code and fix a bug.
 */
public class CashChangingProblem {
    public static void demo(){
        int[] coins = {1, 5, 10, 25}; // Available coin denominations
        int amount = 48; // Target amount in cents

        CashChanging.printCoinsAmount(coins, amount);
    }
}

class CashChanging {

    public static void printCoinsAmount(int[] coins, int amount){
        int minCoins = CashChanging.getMinimumCoinsAmount(coins, amount);

        if (minCoins == -1) {
            System.out.println("It is not possible to make " + amount + " cents using the given denominations.");
        } else {
            System.out.println("Minimum number of coins needed to make " + amount + " cents: " + minCoins);
        }
    }

    private static int getMinimumCoinsAmount(int[] coins, int amount) {
        Arrays.sort(coins);
        int count = 0;
        int index = coins.length - 1;

        while (amount > 0 && index >= 0) {
            if (coins[index] <= amount) {
                int numCoins = amount / coins[index];
                count += numCoins;
                amount -= numCoins * coins[index];
            }
            index--;
        }

        // !!! DO NOT CHANGE THE CODE ABOVE !!!
        return amount == 0 ? count : -1;
//        return -1;
    }
}
