package com.mannanlive.jbj.constants;

public enum HandEndConditions {
    BLACKJACK_PUSH(HandOutcomes.PUSH, "Oh no! How unlucky. The dealer has a natural Blackjack. " +
            "This hand pushes (ties) with your hand so you will get your original bet back."),
    BLACKJACK_WIN(HandOutcomes.BLACKJACK, "Your natural Blackjack beats the dealer's %d"),
    BLACKJACK_LOSS(HandOutcomes.LOST, "Oh no! How unlucky. The dealer has a natural Blackjack."),
    BUST_LOSS(HandOutcomes.LOST, "Oh no! The dealer had %d but you over did it and now are bust with %d!!"),
    BUST_WIN(HandOutcomes.WON, "Yes!! You won as the dealer has gone bust with %d."),
    PUSH(HandOutcomes.PUSH, "Oh no! You and the dealer have the same value (%d), your hand pushes (ties)."),
    LOSS(HandOutcomes.LOST, "Oh no! The dealer has beaten you with a value of %d compared to your %d."),
    WIN(HandOutcomes.WON, "Yes!! You won, beating the dealer's %d with your hand of %d.");

    private HandOutcomes state;
    private String description;

    HandEndConditions(HandOutcomes state, String description) {
        this.state = state;
        this.description = description;
    }

    public String formatDescription(int dealersValue, int playersValue) {
        return String.format(description, dealersValue, playersValue);
    }

    public HandOutcomes getGameOutcome() {
        return state;
    }
}
