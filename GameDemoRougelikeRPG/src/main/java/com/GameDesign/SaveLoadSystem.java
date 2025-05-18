package com.GameDesign;

import java.io.*;

public class SaveLoadSystem {
    private static final String SAVE_FILE = "src/main/resources/savegame.dat";
    public static void saveGame(GameManager gm) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(gm);
            System.out.println("Game saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static GameManager loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            System.out.println("Game loaded.");
            return (GameManager) ois.readObject();
        } catch (Exception e) {
            System.out.println("No save found.");
            return null;
        }
    }
}