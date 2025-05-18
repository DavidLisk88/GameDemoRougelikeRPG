package com.GameDesign;

import java.util.Random;

public class RandomGenerator {
    private static final Random RAND = new Random();
    public static int nextInt(int bound) { return RAND.nextInt(bound); }
    public static int generateRandom(int min,int max) { return RAND.nextInt(max-min+1)+min; }
    public static boolean randomChance(double p) { return RAND.nextDouble() < p; }
}
