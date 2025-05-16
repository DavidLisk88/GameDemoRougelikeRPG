package com.GameDesign;

import java.util.Scanner;

public class CheckpointInteraction {

    private Inventory inventory;

    public CheckpointInteraction(Inventory inventory) {
        this.inventory = inventory;
    }

    public void openCheckpointMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;

        while (!done) {
            System.out.println("\n--- Checkpoint Storage ---");
            System.out.println("1. View Inventory");
            System.out.println("2. View Stored Items");
            System.out.println("3. Store Item");
            System.out.println("4. Retrieve Item");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    inventory.displayInventory();
                    break;
                case 2:
                    inventory.displayCheckpointStorage();
                    break;
                case 3:
                    inventory.displayInventory();
                    System.out.print("Enter item number to store: ");
                    int storeIndex = scanner.nextInt() - 1;
                    inventory.storeItemAtCheckpoint(storeIndex);
                    break;
                case 4:
                    inventory.displayCheckpointStorage();
                    System.out.print("Enter item number to retrieve: ");
                    int retrieveIndex = scanner.nextInt() - 1;
                    inventory.retrieveItemFromCheckpoint(retrieveIndex);
                    break;
                case 5:
                    done = true;
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }
}
