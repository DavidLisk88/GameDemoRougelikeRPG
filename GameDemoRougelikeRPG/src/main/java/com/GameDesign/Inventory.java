package com.GameDesign;

import java.io.Serializable;
import java.util.*;

public class Inventory implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<DataDefinitions.Item> inventory = new ArrayList<>();

    public void addItem(DataDefinitions.Item it) { inventory.add(it); }
    public List<DataDefinitions.Item> getInventory() { return inventory; }

    public void display() {
        System.out.println("--- Inventory ---");
        for (int i = 0; i < inventory.size(); i++) {
            DataDefinitions.Item it = inventory.get(i);
            System.out.printf("%d) %s (%s) [%s] %s%n", i+1, it.name, it.rarity, it.type, it.attrs);
        }
    }
}