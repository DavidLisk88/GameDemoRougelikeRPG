package com.GameDesign;

public class Maiden extends Accomplice {

    public Maiden(String name) {
        super(name, 30, 5, 3);  // Example stats
    }

    @Override
    void provideAid(Player player) {
        // Example of providing buffs/debuffs to the player
        int healAmount = 15;
        if (player.health + healAmount > 100) {
            healAmount = 100 - player.health;  // Cap health to a maximum of 100
        }

        player.health += healAmount;  // Healing the player by the calculated amount
        System.out.println(name + " heals " + player.name + " for " + healAmount + " health!");
    }

    @Override
    public boolean isAlive() {
        return health > 0; // Fixed: Return true if health is greater than 0
    }

    @Override
    public void fight(Enemy enemy) {
        // Fixed: Implement fight logic
        int damage = Math.max(0, getAttack() - enemy.getDefense());
        enemy.takeDamage(damage);
        System.out.println(name + " attacks " + enemy.getName() + " for " + damage + " damage!");
    }
}