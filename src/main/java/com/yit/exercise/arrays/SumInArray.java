package com.yit.exercise.arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SumInArray {

    public static void main(String[] args) {
        int[] array = {-2, 4, 5, 6, 75, 78, 83};
        int sum = 81;

        int pair =
//                bruteForce(array, sum);
//                 twoPointers(array, sum);
                 difference(array, sum);

        System.out.printf("Has %d pair%n", pair);
    }

    private static int difference(int[] array, int sum) {

        Map<Integer, Integer> differenceState = new HashMap<>();
        for (int number : array) {
            int difference = sum - number;

            if (!differenceState.containsKey(number)) {
                differenceState.put(difference, 0);
            } else {
                int newFrequencyValue = differenceState.get(number) + 1;
                differenceState.put(difference, newFrequencyValue);
            }
        }


        return differenceState.values().stream().mapToInt(Integer::intValue).sum();
    }

    /**
     * O(n)
     * @param array
     * @param sum
     * @return
     */
    private static int twoPointers(int[] array, int sum) {
        int pointerFromLeft = 0;
        int pointerFromRight = array.length - 1;
        int count = 0;

        while (true) {
            int valueFromLeft = array[pointerFromLeft];
            int valueFromRight = array[pointerFromRight];

            int sumOfValues = valueFromLeft + valueFromRight;
            if (sumOfValues == sum) {
                count++;
                pointerFromLeft++;
                pointerFromRight--;
            } else if (pointerFromLeft == pointerFromRight || pointerFromLeft > pointerFromRight) {
                break;
            } else if (sumOfValues > sum) {
                pointerFromRight--;
            } else if (sumOfValues < sum) {
                pointerFromLeft++;
            }
        }

        return count;
    }

    /**
     * O(n2)
     *
     * @param array
     * @param sum
     * @return
     */
    private static int bruteForce(int[] array, int sum) {
        int count = 0;
        for (int firstPointer = 0; firstPointer < array.length - 1; firstPointer++) {
            int firstNumber = array[firstPointer];

            for (int secondPointer = 1; secondPointer < array.length; secondPointer++) {
                int secondNumber = array[secondPointer];

                if (firstNumber + secondNumber == sum) {
                    count++;
                }
            }
        }

        return count;
    }

}
