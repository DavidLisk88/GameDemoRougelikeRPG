package com.GameDesign;

public class Companion extends Accomplice {

    public Companion(String name) {
        super(name, 50, 10, 5);  // Example stats
    }

    @Override
    void provideAid(Player player) {
        // Example of providing aid to the player, e.g., giving an item or healing
        player.health += 10;  // Healing the player by 10 health
        System.out.println(name + " heals " + player.name + " for 10 health!");
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