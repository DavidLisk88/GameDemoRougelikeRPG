package com.GameDesign;

import java.util.Random;

public class RandomGenerator {

    private static final Random rand = new Random();

    // Generate a random number between min and max (inclusive)
    public static int generateRandom(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min value cannot be greater than max value.");
        }
        return rand.nextInt((max - min) + 1) + min;
    }

    // Choose a random item from an array
    public static <T> T chooseRandom(T[] items) {
        if (items == null || items.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty.");
        }
        return items[rand.nextInt(items.length)];
    }

    // Randomly generate a boolean value (for chance-based events)
    public static boolean randomChance(double probability) {
        if (probability < 0 || probability > 1) {
            throw new IllegalArgumentException("Probability must be between 0 and 1.");
        }
        return rand.nextDouble() < probability;  // Probability is a value between 0 and 1
    }
}
