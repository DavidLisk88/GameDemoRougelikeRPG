package com.GameDesign;

public abstract class Accomplice {

    String name;
    int health;
    int attack;
    private int defense;

    public Accomplice(String name, int health, int attack, int defense) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    // Abstract method to be implemented by subclasses
    abstract void provideAid(Player player);

    public abstract boolean isAlive();

    public abstract void fight(Enemy enemy);
}
