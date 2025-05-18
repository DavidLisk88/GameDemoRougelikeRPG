package com.GameDesign;

import java.util.Scanner;

public class Utils {
    private static final Scanner SC = new Scanner(System.in);

    public static int readInt(int min, int max) {
        while (true) {
            try {
                int v = Integer.parseInt(SC.nextLine().trim());
                if (v >= min && v <= max) return v;
            } catch (Exception e) {}
            System.out.print("Enter " + min + "-" + max + ": ");
        }
    }

    public static void pause() {
        System.out.print("Press Enter to continue..."); SC.nextLine();
    }
}
