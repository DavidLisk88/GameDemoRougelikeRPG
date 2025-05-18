package com.GameDesign;

import com.GameDesign.DataDefinitions.EnemyDef;

/** Game enemy instance created from DataDefinitions.EnemyDef. */
public class Enemy {
    private final EnemyDef def;
    private int hp;
    private int atk;

    public Enemy(EnemyDef def) {
        this.def = def;
        this.hp = def.stats.getOrDefault("hp", 10);
        this.atk = def.stats.getOrDefault("atk", 5);
    }

    public void takeDamage(int d) { hp -= d; }
    public boolean isAlive()   { return hp > 0; }
    public void attack(Player p) {
        int baseAtk = this.atk;
        int min = Math.max(1, baseAtk / 2);
        int dmg = RandomGenerator.generateRandom(min, baseAtk);
        p.takeDamage(dmg);

    }

    public String getName() { return def.name; }
    public int getHp()      { return hp; }
    public int getAtk()     { return atk; }
    public int getLevel()   { return def.level; }
    public DataDefinitions.EnemyType getType() { return def.type; }
}
