package com.GameDesign;

public class Warrior extends Accomplice {

    public Warrior(String name) {
        super(name, 120, 25, 15);  // Example stats: health, attack, defense
    }

    @Override
    void provideAid(Player player) {
        // Boost the player's attack by 7
        player.setAttack(player.getAttack() + 7); // Fixed: Use setter instead of direct assignment
        System.out.println(name + " boosts " + player.getName() + "'s attack by 7!"); // Fixed: Use getter for player's name
    }

    @Override
    public boolean isAlive() {
        return health > 0; // Fixed: Return true if health is greater than 0
    }

    @Override
    public void fight(Enemy enemy) {
        int damage = Math.max(0, getAttack() - enemy.getDefense()); // Fixed: Use getAttack() instead of direct field access
        enemy.takeDamage(damage);
        System.out.println(name + " attacks " + enemy.getName() + " for " + damage + " damage!");
    }

    // Method to take damage (if the warrior is hit in battle)
    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " takes " + damage + " damage. Remaining health: " + health);
    }
}