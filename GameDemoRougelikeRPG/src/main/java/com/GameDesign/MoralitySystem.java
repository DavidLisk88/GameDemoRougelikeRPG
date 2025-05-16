package com.GameDesign;

class MoralitySystem {
    private int morality;

    public MoralitySystem() {
        this.morality = 0;  // Neutral start
    }

    public void adjustMorality(int amount) {
        morality += amount;

        // Cap morality to -100 to 100 range
        if (morality > 100) {
            morality = 100;
            System.out.println("You have reached the peak of goodness!");
        } else if (morality < -100) {
            morality = -100;
            System.out.println("You have reached the depths of evil!");
        } else if (morality > 50) {
            System.out.println("You are becoming more good.");
        } else if (morality < -50) {
            System.out.println("You are becoming more evil.");
        } else {
            System.out.println("You remain neutral.");
        }
    }

    public int getMorality() {
        return morality;
    }
}
