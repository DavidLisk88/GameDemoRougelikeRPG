package com.GameDesign;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<ItemGenerator.Item> inventory;
    private List<ItemGenerator.Item> checkpointStorage;

    public Inventory() {
        inventory = new ArrayList<>();
        checkpointStorage = new ArrayList<>();
    }

    public void addItem(ItemGenerator.Item item) {
        inventory.add(item);
        System.out.println("Added to inventory: " + item);
    }

    public void removeItem(ItemGenerator.Item item) {
        inventory.remove(item);
        System.out.println("Removed from inventory: " + item);
    }

    public List<ItemGenerator.Item> getInventory() {
        return inventory;
    }

    public void displayInventory() {
        System.out.println("----- Inventory -----");
        for (int i = 0; i < inventory.size(); i++) {
            ItemGenerator.Item item = inventory.get(i);
            System.out.println((i + 1) + ". " + item.name + " (Type: " + item.type + ", Level: " + item.level + ", Effectiveness: " + item.effectiveness + ")");
        }
    }

    public void storeItemAtCheckpoint(int index) {
        if (index >= 0 && index < inventory.size()) {
            ItemGenerator.Item item = inventory.remove(index);
            checkpointStorage.add(item);
            System.out.println("Stored " + item.name + " at checkpoint.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void retrieveItemFromCheckpoint(int index) {
        if (index >= 0 && index < checkpointStorage.size()) {
            ItemGenerator.Item item = checkpointStorage.remove(index);
            inventory.add(item);
            System.out.println("Retrieved " + item.name + " from checkpoint.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void displayCheckpointStorage() {
        System.out.println("----- Checkpoint Storage -----");
        for (int i = 0; i < checkpointStorage.size(); i++) {
            ItemGenerator.Item item = checkpointStorage.get(i);
            System.out.println((i + 1) + ". " + item.name + " (Type: " + item.type + ", Level: " + item.level + ", Effectiveness: " + item.effectiveness + ")");
        }
    }

    public List<ItemGenerator.Item> getCheckpointStorage() {
        return checkpointStorage;
    }
}
