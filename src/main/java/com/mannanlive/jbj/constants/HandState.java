package com.mannanlive.jbj.constants;

public enum HandState {
    BLACKJACK(1), NORMAL(0), BUST(-1);

    private int rank;
    HandState(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}
