package com.GameDesign;

public class Enemy {
    private String name;
    private int health;
    private int attack;
    private int defense;
    private int coins;  // Loot for the player

    public Enemy(String name, int health, int attack, int defense, int coins) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.coins = coins;
    }

    // Method to handle taking damage
    public void takeDamage(int damage) {
        health -= damage;
    }

    // Method for the enemy to attack the player
    public void attack(Player player) {
        int damage = Math.max(0, attack - player.getDefense());  // Simple damage calculation
        player.takeDamage(damage);
        System.out.println(name + " attacks you for " + damage + " damage!");
    }

    public int getAttack() {
        return attack;
    }

    // Getters for health, defense, and loot (coins)
    public int getHealth() {
        return health;
    }

    public int getDefense() {
        return defense;
    }

    public int getCoins() {
        return coins;
    }

    // Check if the enemy is alive
    public boolean isAlive() {
        return health > 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}