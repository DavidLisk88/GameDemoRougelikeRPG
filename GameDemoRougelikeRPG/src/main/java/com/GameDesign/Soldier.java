package com.GameDesign;

public class Soldier extends Accomplice {

    public Soldier(String name) {
        super(name, 100, 20, 10);  // Example stats: health, attack, defense
    }

    @Override
    void provideAid(Player player) {
        // Boost the player's attack by 5
        player.setAttack(player.getAttack() + 5); // Fixed: Use setter instead of direct assignment
        System.out.println(name + " boosts " + player.getName() + "'s attack by 5!"); // Fixed: Use getter for player's name
    }

    @Override
    public boolean isAlive() {
        return health > 0; // Fixed: Return true if health is greater than 0
    }

    @Override
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