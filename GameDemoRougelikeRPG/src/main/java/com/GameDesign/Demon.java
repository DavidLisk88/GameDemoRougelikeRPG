package com.GameDesign;

public class Demon extends Enemy {
    // Removed redundant fields as they shadow parent class fields

    public Demon() {
        super("Demon of Despair", 100, 25, 10, 50);  // Example stats
    }

    @Override
    public boolean isAlive() {
        return getHealth() > 0;
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
    }
}