package com.GameDesign;

import java.util.Random;

class Path {
    boolean hasBattle;
    boolean hasCheckpoint;
    boolean hasMaiden;
    String hint;

    public Path() {
        Random rand = new Random();

        // Randomly generate events on this path (for balance, no path should have all three)
        this.hasBattle = rand.nextBoolean();
        this.hasCheckpoint = rand.nextBoolean();
        this.hasMaiden = rand.nextBoolean();

        // Ensure no path has all events
        if (hasBattle && hasCheckpoint && hasMaiden) {
            // Remove one random event if all three are selected
            int eventToRemove = rand.nextInt(3);
            if (eventToRemove == 0) hasBattle = false;
            else if (eventToRemove == 1) hasCheckpoint = false;
            else hasMaiden = false;
        }

        // Add a more diverse hint to the path
        String[] hints = {"A great treasure awaits", "A hidden danger lurks", "A mysterious figure watches you", "The air smells of magic", "The path seems oddly quiet"};
        this.hint = hints[rand.nextInt(hints.length)];
    }

    public void displayPath() {
        System.out.println("Path: " + hint);
        if (hasBattle) System.out.println("Battle ahead!");
        if (hasCheckpoint) System.out.println("Checkpoint ahead!");
        if (hasMaiden) System.out.println("A Maiden is nearby.");
    }
}
