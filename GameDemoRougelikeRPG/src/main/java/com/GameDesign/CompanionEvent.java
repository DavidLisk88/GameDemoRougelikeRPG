// File: CompanionEvent.java
package com.GameDesign;

public class CompanionEvent implements DoorEvent {
    @Override
    public void execute(GameManager gm) {
        Player p = gm.getPlayer();
        Companion comp = new Companion("comp_" + System.currentTimeMillis(), "Merchant Companion");
        System.out.println(comp.name + " offers stuff:");
        comp.offerAtCheckpoint(p);
        Store.companionMenu(p, comp);
    }
}
