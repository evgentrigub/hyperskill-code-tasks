package org.example.greedyAlgorithms;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Task text:
 * Fractional Knapsack Problem
 *
 * You are given a solution to the Fractional Knapsack Problem.
 * Unfortunately, a previous developer forgot to complete the part when you put item to the knapsack.
 * Your task is finish only a missing part.
 */

class Knapsack {
    public static double getMaxKnapsackValue(int[] weights, int[] values, int capacity) {
        int itemNumber = weights.length;
        Item[] items = new Item[itemNumber];

        // 1. Create an array of items with weight and value pairs
        for (int i = 0; i < itemNumber; i++) {
            items[i] = new Item(weights[i], values[i]);
        }

        // 2. Sort items based on the value per weight ratio
        Comparator<Item> comparator = Comparator.comparingDouble(Item::getRatio).reversed();
        Arrays.sort(items, comparator);

        double totalValue = 0.0;
        int remainingCapacity = capacity;

        // 3. Fill the knapsack with items
        for (int i = 0; i < itemNumber; i++) {
            if (remainingCapacity >= items[i].weight) {
                // 3.1 Put the whole item
                // !!! DO NOT CHANGE THE CODE ABOVE !!!

                // totalValue = ???
                // remainingCapacity = ???

                totalValue = totalValue + items[i].value;
                remainingCapacity = remainingCapacity - items[i].weight;
                // !!! DO NOT CHANGE THE CODE BELOW !!!
            } else {
                // 3.2 Take a fraction possible capacity of the item and put it
                // Break the cycle when put last fractional item
                double fractionCapacity = (double) remainingCapacity / items[i].weight;
                totalValue += fractionCapacity * items[i].value;
                break;
            }
        }

        return totalValue;
    }

    static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        public double getRatio() {
            return (double) value / weight;
        }
    }
}

public class KnapsackProblem {
    public static void demo() {
        int[] weights = {10, 15, 30};
        int[] values = {60, 80, 120};

        int capacity = 50;
        System.out.println("Maximum Knapsack capacity: " + capacity);

        double maxValue = Knapsack.getMaxKnapsackValue(weights, values, capacity);
        System.out.println("Maximum value of given items: " + maxValue);
    }
}


