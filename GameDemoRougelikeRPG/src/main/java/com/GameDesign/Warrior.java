package com.GameDesign;

public class Warrior extends Accomplice {

    public Warrior(String name) {
        super(name, 120, 25, 15);  // Example stats: health, attack, defense
    }

    @Override
    void provideAid(Player player) {
        // Boost the player's attack by 7
        player.attack += 7;
        System.out.println(name + " boosts " + player.name + "'s attack by 7!");
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    // New method: Join the battle to fight alongside the player
    public void fight(Enemy enemy) {
        int damage = Math.max(0, attack - enemy.getDefense());
        enemy.takeDamage(damage);
        System.out.println(name + " attacks " + enemy.getName() + " for " + damage + " damage!");
    }

    // Method to take damage (if the warrior is hit in battle)
    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " takes " + damage + " damage. Remaining health: " + health);
    }
}

