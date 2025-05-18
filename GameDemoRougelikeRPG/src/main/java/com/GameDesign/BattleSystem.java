// File: BattleSystem.java
package com.GameDesign;

public class BattleSystem {
    public static void battle(Player p, Enemy e) {
        System.out.println("\n=== Battle Start ===");
        while (p.isAlive() && e.isAlive()) {
            System.out.println(p);
            System.out.printf("Enemy %s HP:%d Atk:%d%n", e.getName(), e.getHp(), e.getAtk());
            System.out.println("1) Attack  2) Skill  3) Item  4) Flee");
            int action = Utils.readInt(1, 4);

            switch (action) {
                case 1 -> {
                    p.attack(e);
                    System.out.println("You attacked for " + p.getStats().get("atk") + " damage!");
                }
                case 2 -> p.useSkillInBattle(e);
                case 3 -> p.useItemInBattle();
                case 4 -> {
                    if (RandomGenerator.randomChance(0.5)) {
                        System.out.println("You fled successfully!");
                        return;
                    } else {
                        System.out.println("Failed to flee!");
                    }
                }
            }

            if (e.isAlive()) {
                e.attack(p);
                System.out.println("Enemy attacked for " + e.getAtk() + " damage!");
            }
        }

        if (p.isAlive()) {
            int gold = RandomGenerator.generateRandom(5, 30);
            p.addCoins(gold);
            p.gainExp(e.getLevel() * 10);
            System.out.println("Victory! Gained " + gold + " gold.");
        } else {
            System.out.println("You were defeated...");
            Game.onPlayerDeath();
        }
    }
}
