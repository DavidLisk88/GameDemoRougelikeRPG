package com.GameDesign;

public class Soldier extends Accomplice {

    public Soldier(String name) {
        super(name, 100, 20, 10);  // Example stats: health, attack, defense
    }

    @Override
    void provideAid(Player player) {
        // Boost the player's attack by 5
        player.attack += 5;
        System.out.println(name + " boosts " + player.name + "'s attack by 5!");
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    // New method: Join the battle to fight alongside the player
    public void fight(Enemy enemy) {
        int damage = Math.max(0, getAttack() - enemy.getDefense());
        enemy.takeDamage(damage);
        System.out.println(name + " attacks " + enemy.getName() + " for " + damage + " damage!");
    }

    // Method to take damage (if the soldier is hit in battle)
    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " takes " + damage + " damage. Remaining health: " + health);
    }
}
