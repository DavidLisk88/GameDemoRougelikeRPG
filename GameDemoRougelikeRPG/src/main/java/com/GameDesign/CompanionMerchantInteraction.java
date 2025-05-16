package com.GameDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompanionMerchantInteraction {

    private Inventory inventory;
    private MoralitySystem moralitySystem;

    public CompanionMerchantInteraction(Inventory inventory, MoralitySystem moralitySystem) {
        this.inventory = inventory;
        this.moralitySystem = moralitySystem;
    }

    public void interact() {
        Scanner scanner = new Scanner(System.in);
        String morality = String.valueOf(moralitySystem.getMorality());

        List<ItemGenerator.Item> merchantItems = generateTradeStock(morality);

        System.out.println("You encounter a " + (morality.equalsIgnoreCase("good") ? "Companion" : "Merchant") + "!");
        System.out.println("They offer you the following items:");

        for (int i = 0; i < merchantItems.size(); i++) {
            ItemGenerator.Item item = merchantItems.get(i);
            System.out.println((i + 1) + ". " + item.name + " (Type: " + item.name + ", Level: " + item.level + ", Effectiveness: " + item.effectiveness + ")");
        }

        System.out.println("Enter the number of the item you'd like to take (or 0 to skip):");
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= merchantItems.size()) {
            ItemGenerator.Item chosenItem = merchantItems.get(choice - 1);
            inventory.addItem(chosenItem);
            System.out.println("You obtained: " + chosenItem.name);
        } else {
            System.out.println("You decided not to trade.");
        }
    }

    private List<ItemGenerator.Item> generateTradeStock(String morality) {
        List<ItemGenerator.Item> stock = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            stock.add(ItemGenerator.generateItem("random", morality));
        }
        return stock;
    }
}

