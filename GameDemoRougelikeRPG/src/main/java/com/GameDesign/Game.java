package com.GameDesign;

/**
 * Global game callbacks.
 */
public class Game {
    /** Called when the player dies. */
    public static void onPlayerDeath() {
        System.out.println("You have died. Game over.");
        System.exit(0);
    }

    /** Called when the player crosses a morality threshold. */
    public static void onAlignmentFlipped(boolean nowEvil) {
        System.out.println(nowEvil ? "You have embraced Evil!" : "You have returned to the Light!");
    }
}
