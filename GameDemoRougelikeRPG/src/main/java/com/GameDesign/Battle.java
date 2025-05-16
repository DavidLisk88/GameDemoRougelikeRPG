package com.GameDesign;

public class Battle {
    Player player;
    Enemy enemy;
    Accomplice accomplice; // Add accomplice for the battle

    public Battle(Player player, Accomplice accomplice, Enemy enemy) {
        this.player = player;
        this.accomplice = accomplice; // Set accomplice
        this.enemy = enemy;
    }

    // Fixed: Adding implementation for this static method
    public static void startBattle(Player player, Demon demon) {
        Battle battle = new Battle(player, null, demon);
        battle.startBattle();
    }

    public void startBattle() {
        while (player.isAlive() && enemy.isAlive()) {
            playerTurn();
            if (enemy.isAlive()) enemyTurn();
            if (accomplice != null && accomplice.isAlive() && enemy.isAlive()) accompliceTurn(); // Check if accomplice is null
        }

        if (player.isAlive()) {
            System.out.println("You won the battle!");
            player.collectLoot(enemy); // Loot collection if enemy is defeated
        } else {
            System.out.println("You lost the battle.");
            // Reset game or adjust player stats
        }
    }

    private void playerTurn() {
        System.out.println("Your turn. Choose an action.");
        // Handle player actions (Attack, Use Item, Defend)
        player.attack(enemy);
    }

    private void enemyTurn() {
        System.out.println("Enemy's turn.");
        // Handle enemy actions (attack back)
        enemy.attack(player);
    }

    private void accompliceTurn() {
        System.out.println(accomplice.getName() + "'s turn.");
        accomplice.fight(enemy);  // Allow accomplice to attack the enemy
    }
}