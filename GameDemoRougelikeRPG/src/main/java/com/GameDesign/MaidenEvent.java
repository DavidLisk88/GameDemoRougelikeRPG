// File: MaidenEvent.java
package com.GameDesign;

import java.util.Map;

public class MaidenEvent implements DoorEvent {
    @Override
    public void execute(GameManager gm) {
        Player p = gm.getPlayer();
        Map<String,Integer> buff = Map.of(
                "atk", RandomGenerator.generateRandom(1,20),
                "def", RandomGenerator.generateRandom(1,20),
                "hp", RandomGenerator.generateRandom(1,20)
        );
        System.out.println("A Maiden offers buffs: " + buff);
        System.out.println("1) Accept | 2) Decline");
        if (Utils.readInt(1, 2) == 1) {
            buff.forEach((k,v) -> p.getStats().merge(k, v, Integer::sum));
            p.getAccomplices().add(new Maiden("maid_" + System.currentTimeMillis(), "Blessed Maiden"));
            System.out.println("Buff applied and Maiden joined your party.");
        } else {
            System.out.println("You declined their offer.");
        }
    }
}
