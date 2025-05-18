package com.GameDesign;

import java.io.Serializable;
import java.util.*;
import com.GameDesign.DataDefinitions.Item;

/**
 * Base class for companions, soldiers, maidens, demons, etc.
 */
public abstract class Accomplice implements Serializable {
    public enum AccompliceType { COMPANION, SOLDIER, MAIDEN, DEMON, WARRIOR, FAIRY }

    protected String id;
    protected String name;
    protected AccompliceType type;
    protected int duration;         // for Soldiers/Warriors
    protected int turnsUsed;
    protected boolean isAlive = true;
    protected Inventory inventory = new Inventory();
    protected Map<String,Integer> stats = new HashMap<>();

    public Accomplice(String id, String name, AccompliceType type, int duration) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.turnsUsed = 0;
    }

    /** Called at a checkpoint to seed offers into inventory. */
    public abstract void offerAtCheckpoint(Player player);

    /** Called at the start of each run. */
    public void onJoinRun() {
        turnsUsed = 0;
    }

    /** Called each battle turn if they fight. */
    public abstract void onBattleTurn(Player player, Enemy enemy);

    /** Transfer all carried items to the player on exit. */
    public void onExit(Player player) {
        for (Item it : inventory.getInventory()) {
            player.getInventory().addItem(it);
        }
    }

    /** Expose stats map for penalty calculations. */
    public Map<String,Integer> getStats() {
        return stats;
    }
}