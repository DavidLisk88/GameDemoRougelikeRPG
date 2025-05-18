package com.GameDesign;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Tracks player morality and fires events on threshold crossings.
 */
public class MoralitySystem implements Serializable {
    private static final long serialVersionUID = 1L;

    private int score = 0;
    private static final int EVIL_THRESHOLD = -10;
    private static final int HERO_THRESHOLD = 10;

    public enum Alignment { EVIL, NEUTRAL, GOOD, HERO }

    public interface MoralityListener {
        void onAlignmentChange(Alignment newAlign);
    }

    // Transient so not serialized; reinitialized lazily
    private transient List<MoralityListener> listeners;

    public MoralitySystem() {
        listeners = new ArrayList<>();
    }

    public void addListener(MoralityListener listener) {
        if (listeners == null) listeners = new ArrayList<>();
        listeners.add(listener);
    }

    public int getScore() {
        return score;
    }

    public Alignment getAlignment() {
        if (score <= EVIL_THRESHOLD) return Alignment.EVIL;
        if (score >= HERO_THRESHOLD) return Alignment.HERO;
        if (score >= 2) return Alignment.GOOD;
        return Alignment.NEUTRAL;
    }

    public void registerEvent(MoralityEvent event) {
        Alignment before = getAlignment();
        score += event.getDelta();
        Alignment after = getAlignment();
        if (before != after) {
            fireAlignmentChange(after);
        }
    }

    private void fireAlignmentChange(Alignment newAlign) {
        // Ensure listeners list is ready
        if (listeners == null) listeners = new ArrayList<>();

        // Notify global game of extreme flips
        if (newAlign == Alignment.EVIL) Game.onAlignmentFlipped(true);
        if (newAlign == Alignment.HERO) Game.onAlignmentFlipped(false);

        // Call back any UI or systems
        for (MoralityListener listener : listeners) {
            listener.onAlignmentChange(newAlign);
        }
    }
}
