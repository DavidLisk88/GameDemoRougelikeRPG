// File: MoralityEvent.java
package com.GameDesign;

/**
 * Only these four events now affect morality.
 */
public enum MoralityEvent {
    RECRUIT_SOLDIER(+2),   // ally with soldier
    FIGHT_SOLDIER(-2),     // fight soldier
    SAVE_CITIZEN(+2),      // help citizen
    KILL_CITIZEN(-2),
    STEAL_COMPANION(-2);      // fight citizen


    private final int delta;
    MoralityEvent(int d) { this.delta = d; }
    public int getDelta() { return delta; }
}
