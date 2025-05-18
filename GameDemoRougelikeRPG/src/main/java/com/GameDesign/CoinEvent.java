package com.GameDesign;

public class CoinEvent implements DoorEvent {
    @Override
    public void execute(GameManager gm) {
        Player p = gm.getPlayer();
        int coins = RandomGenerator.generateRandom(10,50);
        p.addCoins(coins);
        System.out.println("Found " + coins + " coins on the ground.");
    }
}
