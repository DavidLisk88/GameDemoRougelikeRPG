package com.GameDesign;

public interface DoorEvent {
    void execute(GameManager gm);
    static DoorEvent randomEvent() {
        int r = RandomGenerator.generateRandom(1,6);
        return switch(r) {
            case 1 -> new BattleEvent();
            case 2 -> new CompanionEvent();
            case 3 -> new SoldierEvent();
            case 4 -> new MaidenEvent();
            case 5 -> new CitizenEvent();
            default -> new CoinEvent();
        };
    }
}