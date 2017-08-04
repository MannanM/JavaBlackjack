package com.mannanlive.jbj.services;

import com.mannanlive.jbj.interfaces.Screen;
import com.mannanlive.jbj.models.Deck;
import com.mannanlive.jbj.models.Hand;

import static java.lang.String.format;

public class DealerActionService {
    private static final int DEALER_MINIMUM_HAND_VALUE = 17;
    private final Screen screen;
    private final Deck deck;

    public DealerActionService(Screen screen, Deck deck) {
        this.screen = screen;
        this.deck = deck;
    }

    public void play(Hand dealer) {
        screen.writeOutput(format("The dealer starts off with; %s", dealer));
        dealer.revealHiddenCard();
        screen.writeOutput(format("Revealing the hidden card results in the dealer having a %s", dealer));
        drawSufficentCards(dealer);
    }

    private void drawSufficentCards(Hand dealer) {
        //todo: add 'soft' 17 stop logic
        while (dealer.value() < DEALER_MINIMUM_HAND_VALUE) {
            screen.writeOutput("The dealer is drawing another card...");
            dealer.draw(deck);
            screen.writeOutput(format("The dealers hand is now; %s", dealer));
        }
    }
}
