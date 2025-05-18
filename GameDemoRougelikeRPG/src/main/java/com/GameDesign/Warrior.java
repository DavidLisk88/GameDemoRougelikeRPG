package com.GameDesign;

import com.GameDesign.DataDefinitions;

/** A demon soldier who offers limited combat support. */
public class Warrior extends Accomplice {
    public Warrior(String id, String name) {
        super(id, name, AccompliceType.WARRIOR, 2);
        stats.put("hp", 60);
        stats.put("atk", 12);
    }

    @Override
    public void offerAtCheckpoint(Player player) {
        var it = DataDefinitions.randomWeapon();
        inventory.addItem(it);
    }

    @Override
    public void onBattleTurn(Player player, Enemy enemy) {
        if (!isAlive) return;
        enemy.takeDamage(stats.get("atk"));
        turnsUsed++;
        if (turnsUsed >= duration) isAlive = false;
    }
}