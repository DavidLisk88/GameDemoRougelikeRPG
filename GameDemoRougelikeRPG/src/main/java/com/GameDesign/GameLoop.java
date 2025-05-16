
package com.GameDesign;
import java.util.Scanner;

public class GameLoop {
    private Player player;
    private World world;
    private EncounterHandler encounterHandler;

    public GameLoop(Player player, World world) {
        this.player = player;
        this.world = world;
        this.encounterHandler = new EncounterHandler(player, world);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("You are in a dungeon. What do you want to do?");
            System.out.println("1. Move forward");
            System.out.println("2. Check stats");
            System.out.println("3. Exit game");
            String input = scanner.nextLine();

            if (input.equals("1")) {
                encounterHandler.handleEncounter();
            } else if (input.equals("2")) {
                System.out.println("Name: " + player.getName());
                System.out.println("HP: " + player.getHealth());
                System.out.println("Attack: " + player.getAttack());
            } else if (input.equals("3")) {
                System.out.println("Exiting game...");
                break;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }
}
