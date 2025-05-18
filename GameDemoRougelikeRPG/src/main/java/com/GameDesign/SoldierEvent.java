// File: SoldierEvent.java
package com.GameDesign;

public class SoldierEvent implements DoorEvent {
    @Override
    public void execute(GameManager gm) {
        Player p = gm.getPlayer();
        Soldier s = new Soldier("soldier_" + System.currentTimeMillis(), "Ally Soldier");
        System.out.println(s.name + " offers to join for 2 battles or you can fight:");
        System.out.println("1) Recruit | 2) Fight");
        int choice = Utils.readInt(1, 2);

        if (choice == 1) {
            if (p.getAccomplices().size() < 3) {
                p.getAccomplices().add(s);
                p.getMorality().registerEvent(MoralityEvent.RECRUIT_SOLDIER);
                System.out.println("You recruited " + s.name + ".");
            } else {
                System.out.println("Your party is full — cannot recruit.");
            }
        } else {
            System.out.println("Battle with soldier begins!");
            Enemy e = new Enemy(DataDefinitions.randomEnemy());
            BattleSystem.battle(p, e);
            p.getMorality().registerEvent(MoralityEvent.FIGHT_SOLDIER);
        }
    }
}
