package com.mannanlive.jbj.models;

public class HandWithBetModifier extends Hand {
    private double modifier = 1.0;

    public double getModifier() {
        return modifier;
    }

    public void doubleBet() {
        this.modifier = 2.0;
    }
}
