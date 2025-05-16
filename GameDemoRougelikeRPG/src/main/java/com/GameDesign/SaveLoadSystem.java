package com.GameDesign;

import java.io.*;

public class SaveLoadSystem {
    private static final String SAVE_FILE = "src/main/resources/game_save.txt";// Path to save file
    // Fixed: Removed redundant path field

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
    public static