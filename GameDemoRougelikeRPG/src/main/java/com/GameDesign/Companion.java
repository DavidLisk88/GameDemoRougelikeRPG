package com.GameDesign;

import com.GameDesign.DataDefinitions;

/** A basic merchant-like companion. */
public class Companion extends Accomplice {
    public Companion(String id, String name) {
        super(id, name, AccompliceType.COMPANION, Integer.MAX_VALUE);
        stats.put("hp", 30);
        stats.put("atk", 5);
    }

    @Override
    public void offerAtCheckpoint(Player player) {
        var it = DataDefinitions.randomWeapon();
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
      if (duration > 0 && turnsUsed >= duration){
          isAlive = false;
      }
    }
}