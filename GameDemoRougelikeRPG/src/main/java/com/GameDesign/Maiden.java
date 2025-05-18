package com.GameDesign;

import com.GameDesign.DataDefinitions;

/** A passive buff-provider who never fights. */
public class Maiden extends Accomplice {
    public Maiden(String id, String name) {
        super(id, name, AccompliceType.MAIDEN, 0);
        stats.put("buffPower", 5);
    }

    @Override
    public void offerAtCheckpoint(Player player) {
        var sk = DataDefinitions.randomSkill();
        player.learnSkill(sk);
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