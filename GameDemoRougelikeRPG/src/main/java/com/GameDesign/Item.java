package com.GameDesign;

public class Item {

    // Enum to define item types for better type safety
    public enum ItemType {
        WEAPON, ARMOR, CONSUMABLE, MISC
    }

    private String name;
    private ItemType type; // Using Enum for item type
    private int value;      // Value for selling or trading
    private int statBoost;  // If it's a weapon or armor, this could affect player stats

    // Constructor
    public Item(String name, ItemType type, int value, int statBoost) {
        this.name = name;
        this.type = type;
        this.value = Math.max(0, value);  // Ensure value is not negative
        this.statBoost = Math.max(0, statBoost);  // Ensure statBoost is not negative
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public int getStatBoost() {
        return statBoost;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public void setValue(int value) {
        this.value = Math.max(0, value);  // Ensure value is not negative
    }

    public void setStatBoost(int statBoost) {
        this.statBoost = Math.max(0, statBoost);  // Ensure statBoost is not negative
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", value=" + value +
                ", statBoost=" + statBoost +
                '}';
    }
}
