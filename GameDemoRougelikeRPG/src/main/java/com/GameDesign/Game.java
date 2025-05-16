package com.GameDesign;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Game {

    static Scanner scanner = new Scanner(System.in);
    Player player;
    private String currentWorld;  // "RealWorld" or "PortalWorld"
    private static final String SAVE_FILE = "src/main/java/resources/game_save.txt"; // File to save progress
    private EncounterHandler encounterHandler; // Added EncounterHandler reference
    private World world; // Added World reference to track world state properly

    public Game(Player player) {
        this.player = player;
        this.currentWorld = "RealWorld"; // Default start world
        this.encounterHandler = new EncounterHandler(player, world); // Initialize EncounterHandler
        this.world = new World(); // Initialize World to match the currentWorld state
    }

    // Getter for player
    public Player getPlayer() {
        return player;
    }

    // Getter for currentWorld
    public String getCurrentWorld() {
        return currentWorld;
    }

    // Setter for currentWorld (synchronized with World object)
    public void setCurrentWorld(String currentWorld) {
        this.currentWorld = currentWorld;
        if (this.world != null) {
            this.world.setCurrentWorld(currentWorld); // Update the World object's state too
        }
    }

    public static void main(String[] args) {
        // Try loading previous progress
        Game game = loadProgress();

        // Start the game loop
        game.startGameLoop();
    }

    private static Game loadProgress() {
        Player player = null;
        String world = "RealWorld"; // Default world in case no saved game exists
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            String playerName = reader.readLine();
            int playerHealth = Integer.parseInt(reader.readLine());
            int playerAttack = Integer.parseInt(reader.readLine());
            int playerDefense = Integer.parseInt(reader.readLine());
            int playerCoins = Integer.parseInt(reader.readLine());
            world = reader.readLine(); // Load the world state from the save file

            player = new Player(playerName);
            player.setHealth(playerHealth);
            player.setAttack(playerAttack);
            player.setDefense(playerDefense);
            player.setCoins(playerCoins);

            System.out.println("Game state loaded!");
        } catch (IOException | NumberFormatException e) {
            // If no saved game found, start a new one
            System.out.println("No saved game found. Starting fresh.");
            player = setupNewPlayer();
        }

        Game game = new Game(player);
        game.setCurrentWorld(world); // Use setter to ensure World object is updated too
        return game;
    }

    private static Player setupNewPlayer() {
        System.out.print("Enter your character's name: ");
        String playerName = scanner.nextLine();
        Player newPlayer = new Player(playerName);
        System.out.println("Welcome, " + playerName + "!");
        return newPlayer;
    }

    public void startGameLoop() {
        System.out.println("Welcome to the Roguelike Game!");
        displayMorality();

        // Choose initial world
        chooseWorld();

        // Main game loop
        boolean isPlaying = true;
        while (isPlaying) {
            showGameMenu();

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    choosePath(); // Go on a path in the world
                    break;
                case 2:
                    showStats(); // Display player stats
                    break;
                case 3:
                    saveProgress(); // Save progress and exit
                    isPlaying = false;
                    break;
                case 4:
                    isPlaying = false; // Exit game without saving
                    System.out.println("Thanks for playing!");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }

            // Simulate player death
            if (!player.isAlive()) {
                System.out.println("You have died!");
                resetGame();
            }
        }
    }

    private void chooseWorld() {
        System.out.println("Choose your world:");
        System.out.println("1. Enter the Portal World");
        System.out.println("2. Stay in the Real World");
        int worldChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        setCurrentWorld((worldChoice == 1) ? "PortalWorld" : "RealWorld"); // Use setter instead of direct assignment
        System.out.println("You are in the " + getCurrentWorld()); // Use getter instead of direct access
    }

    private void showGameMenu() {
        System.out.println("\nChoose an action:");
        System.out.println("1. Choose a path");
        System.out.println("2. View stats");
        System.out.println("3. Save progress and exit");
        System.out.println("4. Exit game without saving");
    }

    public void choosePath() {
        Random rand = new Random();
        int randomEvent = rand.nextInt(5);  // Increased options

        switch (randomEvent) {
            case 0:
                // Encounter a battle
                System.out.println("You encounter a wild Demon!");
                encounterHandler.handleDemonEncounter(); // Fixed: Use encounterHandler
                break;
            case 1:
                // Find a checkpoint (Add coins)
                encounterHandler.handleCoinEncounter(); // Fixed: Use encounterHandler
                break;
            case 2:
                // Meet a Maiden (Good morality)
                System.out.println("You encounter a Maiden! Would you like to meet her?");
                encounterHandler.handleMaidenEncounter(); // Fixed: Use encounterHandler
                break;
            case 3:
                // Meet a Fairy (Evil morality)
                System.out.println("You encounter a Fairy! Would you like to meet her?");
                encounterHandler.handleFairyEncounter(); // Fixed: Use encounterHandler
                break;
            case 4:
                // Meet a Soldier or Warrior (Choose morality)
                System.out.println("You encounter a Soldier or Warrior!");
                handleSoldierWarriorEncounter();
                break;
            default:
                System.out.println("You wander aimlessly.");
                break;
        }
    }

    private void handleSoldierWarriorEncounter() {
        System.out.println("1. Ally with Soldier (Good morality) for 3 paths");
        System.out.println("2. Fight Warrior (Evil morality)");

        int choice = scanner.nextInt();
        scanner.nextLine();  // consume newline

        if (choice == 1) {
            System.out.println("You have allied with the Soldier for 3 paths!");
            encounterHandler.handleSoldierEncounter(); // Fixed: Use encounterHandler
        } else {
            System.out.println("You choose to fight the Warrior!");
            encounterHandler.handleWarriorEncounter(); // Fixed: Use encounterHandler
        }
    }

    private void adjustMorality() {
        Random rand = new Random();
        int action = rand.nextInt(5);
        switch (action) {
            case 0:
                player.morality.adjustMorality(-5); // Evil action
                break;
            case 1:
                player.morality.adjustMorality(5); // Good action
                break;
            // Add other actions as needed
        }
    }

    private void showStats() {
        System.out.println("\nPlayer Stats:");
        System.out.println("Name: " + player.getName());
        System.out.println("Health: " + player.getHealth());
        System.out.println("Attack: " + player.getAttack());
        System.out.println("Defense: " + player.getDefense());
        System.out.println("Coins: " + player.getCoins());
        System.out.println("Morality: " + (player.morality.getMorality() >= 0 ? "Good" : "Evil") + " (" + player.morality.getMorality() + ")");
    }

    private void saveProgress() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE))) {
            writer.write(player.getName() + "\n");
            writer.write(player.getHealth() + "\n");
            writer.write(player.getAttack() + "\n");
            writer.write(player.getDefense() + "\n");
            writer.write(player.getCoins() + "\n");
            writer.write(currentWorld + "\n"); // Save current world state
            System.out.println("Game saved!");
        } catch (IOException e) {
            System.out.println("Error saving game state.");
        }
    }

    private void resetGame() {
        player.resetStats();
        setCurrentWorld("RealWorld"); // Use setter instead of direct assignment
        System.out.println("Game reset. Your stats have been reduced.");
    }

    private void displayMorality() {
        System.out.println("Your morality is: " + (player.morality.getMorality() >= 0 ? "Good" : "Evil") + " (" + player.morality.getMorality() + ")");
    }
}