package com.GameDesign;

import java.util.Random;

class World {
    String currentWorld;
    Random rand = new Random();

    public World() {
        currentWorld = "Real World";  // Default start in the real world
    }

    // Switch between worlds with additional events
    public void switchWorld() {
        if (currentWorld.equals("Real World")) {
            currentWorld = "Portal World";
            System.out.println("You have entered the Portal World. The air feels different here...");
            // Add world-specific events, such as random encounters or item drops
            triggerPortalWorldEvent();
        } else {
            currentWorld = "Real World";
            System.out.println("You have entered the Real World. The world feels calm and stable.");
            // Add world-specific events for the Real World
            triggerRealWorldEvent();
        }
    }

    // Example of world-specific event in the Portal World
    private void triggerPortalWorldEvent() {
        System.out.println("You feel a strange power emanating from the portal...");
        int eventChoice = rand.nextInt(3);

        switch (eventChoice) {
            case 0:
                System.out.println("You encounter a wild creature! Prepare for battle!");
                // Add battle logic here
                break;
            case 1:
                System.out.println("You find a mysterious artifact glowing faintly...");
                // Add item discovery or quest-related logic here
                break;
            case 2:
                System.out.println("A powerful sorcerer offers you a dangerous deal...");
                // Add morality or quest decisions
                break;
        }
    }

    // Example of world-specific event in the Real World
    private void triggerRealWorldEvent() {
        System.out.println("You notice the peace of the Real World. Everything seems normal.");
        int eventChoice = rand.nextInt(3);

        switch (eventChoice) {
            case 0:
                System.out.println("You come across a peaceful village. Do you wish to rest here?");
                // Add quest or recovery options
                break;
            case 1:
                System.out.println("You spot a traveling merchant. Would you like to trade?");
                // Add trading or items system
                break;
            case 2:
                System.out.println("You hear a strange noise in the distance. Investigate?");
                // Add exploration or random encounters
                break;
        }
    }

    public String getCurrentWorld() {
        return currentWorld;
    }
}
