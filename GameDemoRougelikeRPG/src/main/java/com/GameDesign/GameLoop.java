package com.GameDesign;

import java.util.Random;

public class GameLoop {
    public static void main(String[] args) {
        Player player = new Player("Hero");
        World world = new World();  // Assuming World is initialized here
        MoralitySystem morality = new MoralitySystem();  // Initialized but not yet used

        // Run the game loop
        while (player.isAlive()) {  // Loop will continue until the player is alive
            // Show current world and options
            System.out.println("You are in the " + world.currentWorld);  // Assuming World has a currentWorld field
            System.out.println("Choose a path:");

            Path path = new Path();  // Create a new Path instance
            path.displayPath();  // Show path options

            // Simulate random path choices for simplicity
            if (new Random().nextBoolean()) {
                world.switchWorld();  // Switch world at a checkpoint (assuming switchWorld is implemented)
            }

            // Simulate random events (battle, checkpoint, Maiden, etc.)
            if (path.hasBattle) {
                // Create a random enemy
                Enemy enemy = createRandomEnemy();

                // Start a battle with the enemy
                Battle battle = new Battle(player, null, enemy); // Fixed: Added null for accomplice parameter
                battle.startBattle();  // Assuming startBattle method in Battle class handles the logic
            }

            if (path.hasCheckpoint) {
                // Handle checkpoint logic
                System.out.println("You've reached a checkpoint!");
                // Option to switch worlds or save
            }

            if (path.hasMaiden) {
                // Handle Maiden interaction
                System.out.println("You've encountered a Maiden!");
                // Choose to accept/reject Maiden
            }

            // Handle other game mechanics like saving, handling death, etc.
        }

        // Player is dead
        System.out.println("Game Over. You have died.");
    }

    // Method to create a random enemy
    public static Enemy createRandomEnemy() {
        Random rand = new Random();
        String[] names = {"Goblin", "Orc", "Dragon", "Bandit", "Wolf"};

        // Randomly select enemy name, health, attack, defense, and loot
        String name = names[rand.nextInt(names.length)];
        int health = rand.nextInt(50) + 50;  // Random health between 50 and 100
        int attack = rand.nextInt(10) + 5;   // Random attack between 5 and 15
        int defense = rand.nextInt(5) + 3;   // Random defense between 3 and 8
        int coins = rand.nextInt(20) + 10;   // Random loot between 10 and 30 coins

        return new Enemy(name, health, attack, defense, coins);
    }
}