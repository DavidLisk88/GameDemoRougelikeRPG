package com.GameDesign;

import java.io.*;

public class SaveLoadSystem {
    private static final String SAVE_FILE = "src/main/resources/game_save.txt";// Path to save file

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
            String playerName = reader.readLine();
            int playerHealth = Integer.parseInt(reader.readLine());
            int playerAttack = Integer.parseInt(reader.readLine());
            int playerDefense = Integer.parseInt(reader.readLine());
            int playerCoins = Integer.parseInt(reader.readLine());
            String world = reader.readLine(); // Load the world state from the save file

            Player player = new Player(playerName);
            player.setHealth(playerHealth);
            player.setAttack(playerAttack);
            player.setDefense(playerDefense);
            player.setCoins(playerCoins);

            Game game = new Game(player);
            game.setCurrentWorld(world);

            System.out.println("Game progress loaded.");
            return game;
        } catch (IOException | NumberFormatException e) {
            System.out.println("No saved game found or error loading game. Starting new game.");
            // Create a new player and game if loading fails
            Player newPlayer = new Player("Hero");
            return new Game(newPlayer);
        }
    }
}