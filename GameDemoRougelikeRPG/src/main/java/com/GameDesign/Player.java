package com.GameDesign;

import java.io.Serializable;
import java.util.*;

import com.GameDesign.DataDefinitions;
import com.GameDesign.DataDefinitions.Skill;

/**
 * Player character: stats, progression, inventory, skills, accomplices, and morality.
 */
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    private int level = 1;
    private int xp = 0;
    private int coins = 0;
    private Map<String,Integer> stats = new HashMap<>();
    private List<Skill> skills = new ArrayList<>();
    private Inventory inventory = new Inventory();
    private List<Accomplice> accomplices = new ArrayList<>();
    private MoralitySystem morality = new MoralitySystem();
    private World currentWorld = World.REAL;

    public Player() {
        stats.put("hp", 100);
        stats.put("atk", 10);
        stats.put("def", 5);
        // Initialize listeners list
        morality.addListener(new MoralitySystem.MoralityListener() {
            @Override
            public void onAlignmentChange(MoralitySystem.Alignment newAlign) {
                // no-op listener
            }
        });
    }

    // --- Stats & Progression ---
    public Map<String,Integer> getStats() { return stats; }
    public int getLevel() { return level; }
    public void setLevel(int lvl) { level = lvl; }
    public boolean isAlive() { return stats.getOrDefault("hp", 0) > 0; }

    public void gainExp(int amount) {
        xp += amount;
        if (xp >= level * 100) {
            level++;
            xp = 0;
            System.out.println("Leveled up! Now level " + level);
        }
    }

    public void takeDamage(int dmg) {
        int hp = stats.getOrDefault("hp", 0) - dmg;
        stats.put("hp", hp);
        if (hp <= 0) {
            Game.onPlayerDeath();
        }
    }

    public void attack(Enemy e) {
        int baseAtk = stats.getOrDefault("atk", 0);
        int min = Math.max(1, baseAtk / 2);
        int dmg = RandomGenerator.generateRandom(min, baseAtk);
        e.takeDamage(dmg);
    }

    // --- World Management ---
    public World getCurrentWorld() { return currentWorld; }
    public void setCurrentWorld(World w) { currentWorld = w; }
    public void toggleWorld() { currentWorld = (currentWorld == World.REAL ? World.PORTAL : World.REAL); }

    // --- Coins & Inventory ---
    public int getCoins() { return coins; }
    public void addCoins(int amount) { coins = Math.max(0, coins + amount); }
    public Inventory getInventory() { return inventory; }

    // --- Skills ---
    public List<Skill> getSkills() { return skills; }
    public void learnSkill(Skill sk) {
        if (skills.stream().noneMatch(s -> s.id.equals(sk.id))) {
            skills.add(sk);
            System.out.println("Learned skill: " + sk.name);
        }
    }

    public boolean useSkillInBattle(Enemy e) {
        if (skills.isEmpty()) {
            System.out.println("No skills available.");
            return false;
        }
        System.out.println("Choose a skill:");
        for (int i = 0; i < skills.size(); i++) {
            Skill sk = skills.get(i);
            System.out.printf("%d) %s - %s%n", i+1, sk.name, sk.desc);
        }
        int choice = Utils.readInt(1, skills.size()) - 1;
        Skill sk = skills.get(choice);
        if (sk.effect.containsKey("dmg")) {
            int dmg = sk.effect.get("dmg");
            System.out.println("Used " + sk.name + " for " + dmg + " damage.");
            e.takeDamage(dmg);
        } else if (sk.effect.containsKey("hpCost")) {
            int cost = sk.effect.get("hpCost");
            int boost = sk.effect.getOrDefault("dmgBoost", 0);
            stats.put("hp", stats.getOrDefault("hp", 0) - cost);
            System.out.println("Used " + sk.name + ", sacrificed " + cost + " HP for " + boost + " damage.");
            e.takeDamage(boost);
        }
        return true;
    }

    // --- Item Use in Battle ---
    public boolean useItemInBattle() {
        List<DataDefinitions.Item> usable = new ArrayList<>();
        for (DataDefinitions.Item it : inventory.getInventory()) {
            if (it.type == DataDefinitions.ItemType.POTION || it.type == DataDefinitions.ItemType.STONE) {
                usable.add(it);
            }
        }
        if (usable.isEmpty()) {
            System.out.println("No usable items.");
            return false;
        }
        System.out.println("Choose an item:");
        for (int i = 0; i < usable.size(); i++) {
            DataDefinitions.Item it = usable.get(i);
            System.out.printf("%d) %s (%s) %s%n", i+1, it.name, it.rarity, it.attrs);
        }
        int choice = Utils.readInt(1, usable.size()) - 1;
        DataDefinitions.Item used = usable.get(choice);
        if (used.type == DataDefinitions.ItemType.POTION) {
            int heal = used.attrs.getOrDefault("heal", 0);
            stats.put("hp", stats.getOrDefault("hp", 0) + heal);
            System.out.println("Used " + used.name + ", healed " + heal + " HP.");
        } else {
            used.attrs.forEach((k, v) ->
                    stats.put(k, stats.getOrDefault(k, 0) + v)
            );
            System.out.println("Used " + used.name + ", applied buffs.");
        }
        inventory.getInventory().remove(used);
        return true;
    }

    // --- Accomplices & Morality ---
    public List<Accomplice> getAccomplices() { return accomplices; }
    public void clearAccomplices() { accomplices.clear(); }
    public MoralitySystem getMorality() { return morality; }

    @Override
    public String toString() {
        return String.format(
                "[Lvl %d | HP %d | ATK %d | DEF %d | Coins %d | Morality %d (%s)]",
                level,
                stats.getOrDefault("hp", 0),
                stats.getOrDefault("atk", 0),
                stats.getOrDefault("def", 0),
                coins,
                morality.getScore(),
                morality.getAlignment()
        );
    }
}
