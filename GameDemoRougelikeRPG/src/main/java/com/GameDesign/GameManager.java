package com.GameDesign;

import java.io.Serializable;
import java.util.*;

public class GameManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private Player player;
    private transient Scanner sc = new Scanner(System.in);

    public GameManager(Player p) { player = p; }
    public static GameManager newGame() {
        Player p = new Player();
        System.out.println("Choose world: 1) Portal 2) Real");
        p.setCurrentWorld(Utils.readInt(1,2)==1?World.PORTAL:World.REAL);
        return new GameManager(p);
    }
    public Player getPlayer() { return player; }

    public void run() {
        while (true) {
            List<Door> doors = Door.generateDoors();
            System.out.println("Choose a door (1-3):");
            for (int i = 0; i < doors.size(); i++) System.out.println((i+1)+") Door "+(i+1));
            int d = Utils.readInt(1,3) - 1;
            for (DoorEvent ev : doors.get(d).getEvents()) {
                ev.execute(this);
                if (!player.isAlive()) break;
            }
            new Checkpoint(player).interact();
            System.out.println("Save game? 1)Yes 2)No");
            if (Utils.readInt(1,2)==1) SaveLoadSystem.saveGame(this);
        }
    }
}