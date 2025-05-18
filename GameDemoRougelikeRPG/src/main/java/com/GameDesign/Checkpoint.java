package com.GameDesign;

import java.util.*;

public class Checkpoint {
    private Player player;
    public Checkpoint(Player p) { player = p; }
    public void interact() {
        System.out.println("\n=== CHECKPOINT ===");
        System.out.println(player);
        List<Accomplice> acs = player.getAccomplices();
        if (acs.isEmpty()) System.out.println("No accomplices stored.");
        else {
            System.out.println("Stored Accomplices:");
            for (int i = 0; i < acs.size(); i++) {
                Accomplice a = acs.get(i);
                System.out.printf("%d) %s [%s]\n", i+1, a.name, a.type);
            }
            System.out.println("1) Swap out an accomplice | 2) Continue");
            int choice = Utils.readInt(1,2);
            if (choice == 1) {
                System.out.print("Which to remove: ");
                int rem = Utils.readInt(1, acs.size()) - 1;
                System.out.println(acs.remove(rem).name + " removed.");
            }
        }
        Utils.pause();
    }
}