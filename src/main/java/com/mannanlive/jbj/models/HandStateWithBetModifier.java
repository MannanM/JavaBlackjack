package com.mannanlive.jbj.models;


import com.mannanlive.jbj.constants.HandOutcomes;

public class HandStateWithBetModifier {
    private HandOutcomes outcome;
    private double modifier;

    public HandStateWithBetModifier(HandOutcomes outcome, double modifier) {
        this.outcome = outcome;
        this.modifier = modifier;
    }

    public double calculatePayout(double bet) {
        return outcome.calculatePayout(bet) * modifier;
    }
}