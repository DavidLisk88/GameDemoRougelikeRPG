// File: CitizenEvent.java
package com.GameDesign;

public class CitizenEvent implements DoorEvent {
    @Override
    public void execute(GameManager gm) {
        Player p = gm.getPlayer();
        System.out.println("A frightened citizen appears:");
        System.out.println("1) Help | 2) Ignore | 3) Fight");
        int c = Utils.readInt(1, 3);

        if (c == 1) {
            p.gainExp(10);
            int coins = RandomGenerator.generateRandom(5, 15);
            p.addCoins(coins);
            p.getMorality().registerEvent(MoralityEvent.SAVE_CITIZEN);
            System.out.println("You helped and earned " + coins + " coins.");
        } else if (c == 3) {
            System.out.println("You fight the citizen (you win easily).");
            p.gainExp(10);
            DataDefinitions.Item loot = DataDefinitions.randomPotion();
            p.getInventory().addItem(loot);
            p.getMorality().registerEvent(MoralityEvent.KILL_CITIZEN);
            System.out.println("Looted " + loot.name + ".");
        } else {
            System.out.println("You ignored the citizen.");
        }
    }
}
