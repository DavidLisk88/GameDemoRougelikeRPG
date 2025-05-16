package com.GameDesign;

import java.util.Random;
import java.util.Scanner;

public class EncounterHandler {

    private Player player;
    private Scanner scanner;

    public EncounterHandler(Player player) {
        this.player = player;
        this.scanner = new Scanner(System.in);
    }

    // Method to handle Soldier encounter
    public void handleSoldierEncounter() {
        Soldier soldier = new Soldier("Brave Soldier");
        System.out.println("You encounter a Soldier!");
        System.out.println("Do you want to fight or ally with the Soldier?");
        System.out.println("1. Fight");
        System.out.println("2. Ally");

        int choice = getValidInput(1, 2);  // Validates input
        if (choice == 1) {
            System.out.println("You chose to fight the Soldier!");
            Battle battle = new Battle(player, soldier, new Demon());
            battle.startBattle(); // Start the battle
        } else if (choice == 2) {
            System.out.println("You have allied with the Soldier!");
            soldier.provideAid(player); // The Soldier helps boost player's stats
            player.addAccomplice(soldier); // Add Soldier to player's list of allies
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // Method to handle Warrior encounter
    public void handleWarriorEncounter() {
        Warrior warrior = new Warrior("Dark Warrior");
        System.out.println("You encounter a Warrior!");
        System.out.println("Do you want to fight or ally with the Warrior?");
        System.out.println("1. Fight");
        System.out.println("2. Ally");

        int choice = getValidInput(1, 2);  // Validates input
        if (choice == 1) {
            System.out.println("You chose to fight the Warrior!");
            Battle battle = new Battle(player, warrior, new Demon());
            battle.startBattle(); // Start the battle
        } else if (choice == 2) {
            System.out.println("You have allied with the Warrior!");
            warrior.provideAid(player); // The Warrior helps boost player's stats
            player.addAccomplice(warrior); // Add Warrior to player's list of allies
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // Method to handle Maiden encounter
    public void handleMaidenEncounter() {
        Maiden maiden = new Maiden("Maiden of Light"); // Random stats for the Maiden
        System.out.println("You encounter a Maiden!");
        System.out.println("Do you want to see the Maiden's stats?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        int choice = getValidInput(1, 2);  // Validates input
        if (choice == 1) {
            // Show Maiden's stats
            System.out.println(maiden.getName() + "'s Stats:");
            System.out.println("Health: " + maiden.getHealth());
            System.out.println("Attack: " + maiden.getAttack());
            System.out.println("Defense: " + maiden.getDefense());
        } else if (choice == 2) {
            System.out.println("You chose not to see the Maiden's stats.");
        } else {
            System.out.println("Invalid choice.");
        }

        System.out.println("Do you want to bring the Maiden with you?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        choice = getValidInput(1, 2);  // Validates input
        if (choice == 1) {
            // Add Maiden to player
            player.addAccomplice(maiden);
            System.out.println("You have brought " + maiden.getName() + " with you.");
            // Merge Maiden's stats with player's stats
            player.mergeStatsWithAccomplice(maiden);
        } else if (choice == 2) {
            System.out.println("You leave the Maiden behind.");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // Method to handle Fairy encounter (evil morality)
    public void handleFairyEncounter() {
        Fairy fairy = new Fairy("Evil Fairy", 15, 7, 3); // Random stats for the Fairy
        System.out.println("You encounter an Evil Fairy!");
        System.out.println("Do you want to see the Fairy's stats?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        int choice = scanner.nextInt();
        if (choice == 1) {
            // Show Fairy's stats
            System.out.println(fairy.getName() + "'s Stats:");
            System.out.println("Health: " + fairy.getHealth());
            System.out.println("Attack: " + fairy.getAttack());
            System.out.println("Defense: " + fairy.getDefense());
        } else if (choice == 2) {
            System.out.println("You chose not to see the Fairy's stats.");
        } else {
            System.out.println("Invalid choice.");
        }

        System.out.println("Do you want to bring the Fairy with you?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        choice = scanner.nextInt();
        if (choice == 1) {
            // Add Fairy to player (if you want to track her in your party)
            player.addAccomplice(fairy);
            System.out.println("You have brought " + fairy.getName() + " with you.");
            // Merge Fairy's stats with player's stats (negative effect)
            player.mergeStatsWithAccomplice(fairy); // This merges the Fairy's stats, possibly negatively
        } else if (choice == 2) {
            System.out.println("You leave the Fairy behind.");
        } else {
            System.out.println("Invalid choice.");
        }
    }


    // Method to handle Demon encounter (battle scenario)
    public void handleDemonEncounter() {
        Demon demon = new Demon();
        System.out.println("You encounter a Demon!");
        System.out.println("Do you want to fight or flee?");
        System.out.println("1. Fight");
        System.out.println("2. Flee");

        int choice = getValidInput(1, 2);  // Validates input
        if (choice == 1) {
            System.out.println("You chose to fight the Demon!");
            Battle battle = new Battle(player, null, demon); // No accomplice for this battle
            battle.startBattle();
        } else if (choice == 2) {
            System.out.println("You flee from the Demon.");
            // Player escapes (could trigger a random event here)
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // Method to handle coin encounter
    public void handleCoinEncounter() {
        Random rand = new Random();
        int coinsFound = rand.nextInt(50) + 1; // Random number of coins between 1 and 50
        System.out.println("You find " + coinsFound + " coins!");
        player.addCoins(coinsFound); // Add coins to player's inventory
    }

    // Method to handle ally or enemy decision based on morality
    public void handleMoralityBasedEncounter() {
        Random rand = new Random();
        int encounterType = rand.nextInt(5);

        switch (encounterType) {
            case 0:
                handleSoldierEncounter();
                break;
            case 1:
                handleWarriorEncounter();
                break;
            case 2:
                handleMaidenEncounter();
                break;
            case 3:
                handleFairyEncounter();
                break;
            case 4:
                handleDemonEncounter();
                break;
            default:
                System.out.println("An unknown encounter occurred.");
        }
    }

    // Helper method to ensure valid input between a range of values
    private int getValidInput(int min, int max) {
        while (true) {
            try {
                int input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();  // Clear the invalid input
            }
        }
    }
}
