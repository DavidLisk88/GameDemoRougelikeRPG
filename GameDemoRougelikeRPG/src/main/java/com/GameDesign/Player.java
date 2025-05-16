package com.GameDesign;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    String name;
    int health;
    int attack;
    int defense;
    int coins;
    private ArrayList<Accomplice> accomplices;
    MoralitySystem morality;

    public Player(String name) {
        this.name = name;
        this.health = 100;  // Example starting stats
        this.attack = 15;
        this.defense = 10;
        this.coins = 0;
        this.morality = new MoralitySystem();
        this.accomplices = new ArrayList<>();// Initialize morality system
    }

    // Methods to handle player actions, attack, and health adjustments
    public boolean isAlive() {
        return health > 0;
    }

    public void addAccomplice(Accomplice accomplice) {
        accomplices.add(accomplice);
    }

    public void mergeStatsWithAccomplice(Accomplice accomplice) {
        this.health += accomplice.getHealth();
        this.attack += accomplice.getAttack();
        this.defense += accomplice.getDefense();
    }

    public void attack(Enemy enemy) {
        // Basic attack calculation
        int damage = Math.max(0, attack - enemy.getDefense());
        enemy.takeDamage(damage);
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;  // Ensures health never goes below zero
            // Trigger death event or game over logic here if needed
        }
    }

    public void resetStats() {
        health = 100;  // Reset player health
        attack = 15;   // Reset attack power
        defense = 10;  // Reset defense
    }

    // Scales enemy difficulty or the game world state
    public void scaleDifficulty() {
        // Example scaling logic: increase enemy stats as player progresses
        if (this.health < 50) {
            this.attack += 5;  // Increase attack if health is low
            this.defense += 3;  // Increase defense if health is low
        }
    }


    public MoralitySystem getMorality() {
        return morality;
    }

    public int getCoins() {
        return coins;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }


    public void addCoins(int amount) {
        this.coins += amount;
        System.out.println("You now have " + coins + " coins.");
    }



    public void setMorality(MoralitySystem morality) {
        this.morality = morality;
    }

    public void collectLoot(Enemy enemy) {
        // Example logic for collecting loot (coins or items)
        int loot = 10;  // Just an example
        coins += loot;
        System.out.println("You collected " + loot + " coins!");
    }
}
