package com.GameDesign;

public class Fairy extends Accomplice {

    // Constructor for Fairy, inheriting from Accomplice
    public Fairy(String name, int health, int attack, int defense) {
        super(name, health, attack, defense); // Inherit stats from Accomplice class
    }

    // Similar to Maiden, we could implement this method to merge stats with the player
    @Override
    void provideAid(Player player) {
        // Evil Fairies may decrease the player's stats (evil effect)
        player.setHealth(player.getHealth() - 5);  // Example: decrease health
        player.setAttack(player.getAttack() - 2);  // Example: decrease attack
        player.setDefense(player.getDefense() - 1);  // Example: decrease defense

        System.out.println(name + " decreases your stats!");
        System.out.println(player.getName() + "'s new stats: ");
        System.out.println("Health: " + player.getHealth());
        System.out.println("Attack: " + player.getAttack());
        System.out.println("Defense: " + player.getDefense());
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public void fight(Enemy enemy) {

    }

    // Additional functionality for Fairies could include additional evil actions,
    // but here it behaves similarly to the Maiden for now.
}
