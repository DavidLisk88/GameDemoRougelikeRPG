package com.GameDesign;

import java.io.*;

public class SaveLoadSystem {
    private static final String SAVE_FILE = "src/main/resources/game_save.txt";// Path to save file
    String path = System.getProperty("user.dir") + "/src/main/resources/game_save.txt";


    // Save player progress to a file
    public static void saveProgress(Game game) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE))) {
            writer.write(game.player.getName() + "\n");  // Save player's name
            writer.write(game.player.getHealth() + "\n");  // Save player's health
            writer.write(game.player.getAttack() + "\n");  // Save player's attack
            writer.write(game.player.getDefense() + "\n");  // Save player's defense
            writer.write(game.player.getCoins() + "\n");  // Save player's coins
            writer.write(game.getCurrentWorld() + "\n");  // Save the current world using the getter
            System.out.println("Game progress saved.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the game progress.");
        }
    }

    // Load player progress from a file
    public static Game loadProgress() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            String playerName = reader.readLine();  // Load player's name
            int playerHealth = Integer.parseInt(reader.readLine());  // Load player's health
            int playerAttack = Integer.parseInt(reader.readLine());  // Load player's attack
            int playerDefense = Integer.parseInt(reader.readLine());  // Load player's defense
            int playerCoins = Integer.parseInt(reader.readLine());  // Load player's coins
            String currentWorld = reader.readLine();  // Load the current world

            // Create player object
            Player player = new Player(playerName);
            player.setHealth(playerHealth);
            player.setAttack(playerAttack);
            player.setDefense(playerDefense);
            player.setCoins(playerCoins);

            // Return the game object with the loaded data
            Game game = new Game(player);
            game.setCurrentWorld(currentWorld);  // Use setter to update the world
            System.out.println("Loaded previous progress.");

            return game;

        } catch (IOException | NumberFormatException e) {
            // If the file doesn't exist or is corrupted, start a new game
            System.out.println("No saved game found. Starting a new game...");
            Player player = new Player("Hero");  // Default new player
            return new Game(player);
        }
    }
}
