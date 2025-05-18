package com.GameDesign;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Morality Roguelike ===");
        System.out.println("1) New Game  2) Load Game");
        int c = Utils.readInt(1,2);
        GameManager gm = c==1 ? GameManager.newGame() : SaveLoadSystem.loadGame();
        if (gm == null) gm = GameManager.newGame();
        gm.run();
    }
}