package com.GameDesign;

import com.GameDesign.DataDefinitions;

/** A neutral buff-provider in evil alignment. */
public class Fairy extends Accomplice {
    public Fairy(String id, String name) {
        super(id, name, AccompliceType.FAIRY, 0);
        stats.put("buffPower", 5);
    }

    @Override
    public void offerAtCheckpoint(Player player) {
        var sk = DataDefinitions.randomSkill();
        player.learnSkill(sk);
    }

    @Override
    public void onBattleTurn(Player player, Enemy enemy) {
        // Fairies do not fight
    }
}