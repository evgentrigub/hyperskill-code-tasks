package org.example.dynamicProgramming;

import java.util.Arrays;

public class MinimumStepsCalculator {

    // Function to find the minimum number of steps to reach a target number
    public static int calculateMinimumSteps(int targetNumber) {
        // Array to store the minimum steps for each number up to the target
        int[] minimumStepsToReach = new int[targetNumber + 1];

        // It takes 0 steps to reach 0 (starting point)
        minimumStepsToReach[0] = 0;

        // Calculate the minimum steps for each number from 1 to targetNumber
        for (int currentNumber = 1; currentNumber <= targetNumber; currentNumber++) {
            // Assume the minimum steps to reach currentNumber is one more than the steps to reach the previous number
            minimumStepsToReach[currentNumber] = minimumStepsToReach[currentNumber - 1] + 1;

            System.out.println(currentNumber);
            System.out.println(Arrays.toString(minimumStepsToReach));

            // If currentNumber is even, check if halving it results in fewer steps
            if (currentNumber % 2 == 0) {
                int halfOfCurrent = currentNumber / 2;
                System.out.println("Even: " + halfOfCurrent);
                minimumStepsToReach[currentNumber] = Math.min(minimumStepsToReach[currentNumber], minimumStepsToReach[halfOfCurrent] + 1);
                System.out.println(Arrays.toString(minimumStepsToReach));
                System.out.println("---------------");
            } else {
            System.out.println("---------------");
            }
        }

        // Return the calculated minimum steps to reach the target number
        return minimumStepsToReach[targetNumber];
    }

    // Main method to test the calculateMinimumSteps function
    public static void run() {
        int targetNumber = 10; // Example target number to find the minimum steps for
        int minimumSteps = calculateMinimumSteps(targetNumber);
        System.out.println("Minimum steps to reach " + targetNumber + ": " + minimumSteps);
    }
}
