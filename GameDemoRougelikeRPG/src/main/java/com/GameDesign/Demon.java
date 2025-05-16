package com.GameDesign;

public class Demon extends Enemy {
    private int health;
    private int attack;
    private int defense;

    public Demon() {
        super("Demon of Despair", 100, 25, 10, 50);  // Example stats
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }
}
