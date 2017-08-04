package com.mannanlive.jbj.constants;

public enum HandOutcomes {
    LOST(-1), PUSH(0), WON(1), BLACKJACK(1.5);

    private double payout;
    public double calculatePayout(double bet) {
        return payout * bet;
    }

    HandOutcomes(double payout) {
        this.payout = payout;
    }
}
