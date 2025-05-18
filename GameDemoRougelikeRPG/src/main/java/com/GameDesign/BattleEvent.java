package com.GameDesign;

public class BattleEvent implements DoorEvent {
    @Override
    public void execute(GameManager gm) {
        Player p = gm.getPlayer();
        DataDefinitions.EnemyDef def = DataDefinitions.randomEnemy();
        Enemy e = new Enemy(def);
        System.out.println("A wild " + def.name + " appears (Level " + def.level + ")!");
        BattleSystem.battle(p, e);
    }
}