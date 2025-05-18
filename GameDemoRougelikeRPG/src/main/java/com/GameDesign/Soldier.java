package com.GameDesign;

import com.GameDesign.DataDefinitions;

/** A temporary ally soldier who fights for a few turns. */
public class Soldier extends Accomplice {
    public Soldier(String id, String name) {
        super(id, name, AccompliceType.SOLDIER, 2);
        stats.put("hp", 50);
        stats.put("atk", 8);
    }

    @Override
    public void offerAtCheckpoint(Player player) {
        var it = RandomGenerator.randomChance(0.5)
                ? DataDefinitions.randomWeapon()
                : DataDefinitions.randomArmor();
        inventory.addItem(it);
    }

    @Override
    public void onBattleTurn(Player player, Enemy enemy) {
        if (!isAlive) return;
        int baseAtk = stats.getOrDefault("atk", 0);
        int min = Math.max(1, baseAtk / 2);
        int dmg = RandomGenerator.generateRandom(min, baseAtk);
        System.out.println(name + " attacks for " + dmg + " damage!");
        enemy.takeDamage(dmg);
        turnsUsed++;
        if (duration > 0 && turnsUsed >= duration) {
            isAlive = false;
        }
    }
}