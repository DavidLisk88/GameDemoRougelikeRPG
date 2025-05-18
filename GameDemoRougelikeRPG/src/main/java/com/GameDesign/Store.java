// File: Store.java
package com.GameDesign;
public class Store {
    public static void companionMenu(Player p, Companion c) {
        while (true) {
            System.out.println("1) Buy | 2) Sell | 3) Steal | 4) Leave");
            int c1 = Utils.readInt(1,4);
            if (c1 == 4) break;
            switch (c1) {
                case 1 -> buy(p, c);
                case 2 -> sell(p, c);
                case 3 -> steal(p, c);
            }
        }
    }

    public static void buy(Player p, Companion c) {
        var inv = c.inventory.getInventory();
        if (inv.isEmpty()) { System.out.println("Nothing to buy."); return; }
        DataDefinitions.Item it = inv.remove(0);
        int cost = it.attrs.getOrDefault("value", 10);
        if (p.getCoins() < cost) {
            System.out.println("Not enough coins.");
            inv.add(0, it);
            return;
        }
        p.addCoins(-cost);
        p.getInventory().addItem(it);
        System.out.println("Bought " + it.name + " for " + cost + " coins.");
    }

    public static void sell(Player p, Companion c) {
        var inv = p.getInventory().getInventory();
        if (inv.isEmpty()) { System.out.println("Nothing to sell."); return; }
        DataDefinitions.Item it = inv.remove(0);
        int gain = it.attrs.getOrDefault("value", 5);
        p.addCoins(gain);
        System.out.println("Sold " + it.name + " for " + gain + " coins.");
    }

    public static void steal(Player p, Companion c) {
        var inv = c.inventory.getInventory();
        if (inv.isEmpty()) {
            System.out.println("Nothing to steal.");
            return;
        }
        DataDefinitions.Item it = inv.remove(0);
        p.getInventory().addItem(it);
        p.getMorality().registerEvent(MoralityEvent.STEAL_COMPANION);
        System.out.println("Stole " + it.name);
    }
}