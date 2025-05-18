// File: Demon.java
package com.GameDesign;

import com.GameDesign.DataDefinitions;
import com.GameDesign.DataDefinitions.Item;

public class Demon extends Accomplice {
    public Demon(String id, String name) {
        super(id, name, AccompliceType.DEMON, Integer.MAX_VALUE);
        stats.put("hp", 40);
        stats.put("atk", 10);
    }

    @Override
    public void offerAtCheckpoint(Player player) {
        Item offer = DataDefinitions.randomPotion();
        inventory.addItem(offer);
        System.out.println(name + " offers " + offer.name);
    }

    @Override
    public void onBattleTurn(Player player, Enemy enemy) {
        if (!isAlive) return;
        enemy.takeDamage(stats.getOrDefault("atk", 0));
    }
}
