package com.GameDesign;

import java.util.*;

public class Door {
    private List<DoorEvent> events;
    private Door(List<DoorEvent> ev) { events = ev; }
    public List<DoorEvent> getEvents() { return events; }
    public static List<Door> generateDoors() {
        List<Door> ds = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int cnt = RandomGenerator.generateRandom(1,3);
            List<DoorEvent> evs = new ArrayList<>();
            for (int j = 0; j < cnt; j++) evs.add(DoorEvent.randomEvent());
            ds.add(new Door(evs));
        }
        return ds;
    }
}