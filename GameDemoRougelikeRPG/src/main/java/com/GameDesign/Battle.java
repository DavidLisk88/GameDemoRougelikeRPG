package com.GameDesign;

import java.util.Scanner;

public class Battle {
    private Player player;
    private Enemy enemy;
    private Inventory inventory;
    private MoralitySystem moralitySystem;

    public Battle(Player player, Enemy enemy, Inventory inventory, MoralitySystem moralitySystem) {
        this.player = player;
        this.enemy = enemy;
        this.inventory = inventory;
        this.moralitySystem = moralitySystem;
    }

    public void startBattle() {
        Scanner scanner = new Scanner(System.in);
        int round = 1;

        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("\n--- Round " + round + " ---");
            playerTurn(scanner);
            if (!enemy.isAlive()) {
                System.out.println("You defeated " + enemy.getName() + "!");
                break;
            }

            enemyTurn();
            if (!player.isAlive()) {
                System.out.println("You were defeated by " + enemy.getName() + "...");
                break;
            }

            // 🎁 Soldier/Warrior reward after 2nd or 3rd round
            if ((round == 2 || round == 3) && (enemy instanceof Soldier || enemy instanceof Warrior)) {
                ItemGenerator.Item reward = ItemGenerator.generateItem("random", moralitySystem.getMorality());
                inventory.addItem(reward);
                System.out.println(enemy.getName() + " rewards you with: " + reward);
            }

            round++;
        }
    }

    private void playerTurn(Scanner scanner) {
        System.out.println("Choose your action:");
        System.out.println("1. Attack");

        if (enemy instanceof Demon) {
            System.out.println("2. Offer valuable to recruit Demon");
        }

        int choice = scanner.nextInt();

        if (choice == 1) {
            int damage = player.attack();
            enemy.takeDamage(damage);
            System.out.println("You dealt " + damage + " damage to " + enemy.getName());
        } else if (choice == 2 && enemy instanceof Demon) {
            inventory.displayInventory();
            System.out.print("Enter the number of the item to offer: ");
            int offerIndex = scanner.nextInt() - 1;

            if (offerIndex >= 0 && offerIndex < inventory.getInventory().size()) {
                ItemGenerator.Item item = inventory.getInventory().get(offerIndex);

                if (item.type.equalsIgnoreCase("valuable")) {
                    System.out.println("The Demon accepts your offering and joins your side!");
                    inventory.removeItem(item);
                    moralitySystem.shiftTowardsEvil(); // Example logic
                    enemy.convertToAlly(); // Optional: flag that removes enemy from combat
                    enemy.setHealth(0); // Ends the battle
                } else {
                    System.out.println("The Demon rejects your offering.");
                }
            } else {
                System.out.println("Invalid item selection.");
            }
        } else {
            System.out.println("Invalid choice. Turn skipped.");
        }
    }

    private void enemyTurn() {
        int damage = enemy.attack();
        player.takeDamage(damage);
        System.out.println(enemy.getName() + " dealt " + damage + " damage to you.");
    }
}
