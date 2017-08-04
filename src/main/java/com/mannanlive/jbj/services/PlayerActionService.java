package com.mannanlive.jbj.services;

import com.mannanlive.jbj.constants.HandState;
import com.mannanlive.jbj.constants.PlayerAction;
import com.mannanlive.jbj.interfaces.PlayerInput;
import com.mannanlive.jbj.interfaces.Screen;
import com.mannanlive.jbj.models.Deck;
import com.mannanlive.jbj.models.Hand;
import com.mannanlive.jbj.models.HandWithBetModifier;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class PlayerActionService {
    private final PlayerInput playerInput;
    private final Screen screen;
    private final Deck deck;
    private List<HandWithBetModifier> hands;

    public PlayerActionService(Screen screen, Deck deck) {
        this.screen = screen;
        this.playerInput = new PlayerInput(screen);
        this.deck = deck;
    }

    public List<HandWithBetModifier> play(HandWithBetModifier player) {
        hands = new ArrayList<>();
        hands.add(player);
        calculateModifiers(player);
        return hands;
    }

    private void calculateModifiers(HandWithBetModifier player) {
        PlayerAction action = playerInput.action();
        switch (action) {
            case HIT:
                playerHits(player);
                return;
            case DOUBLE:
                playerDoubles(player);
                return;
            case SPLIT:
                playerSplits(player);
                return;
            default:
                playerStands();
        }
    }

    private void playerSplits(HandWithBetModifier player) {
        if (!player.canSplit()) {
            screen.writeOutput("Sorry, you can only split on your first turn and with two cards with the same value.");
            calculateModifiers(player);
            return;
        }
        HandWithBetModifier newHand = new HandWithBetModifier();
        player.split(newHand);
        hands.add(newHand);

        screen.writeOutput("The dealer deals a second card to your first split pair...");
        player.draw(deck);
        screen.writeOutput(format("Your first split pair hand is now; %s", player));

        screen.writeOutput("The dealer deals a second card to your second split pair...");
        newHand.draw(deck);
        screen.writeOutput(format("Your second split pair hand is now; %s", newHand));

        screen.writeOutput(format("For your first split pair (%s)...", player));
        calculateModifiers(player);

        screen.writeOutput(format("Now for your second split pair (%s)...", newHand));
        calculateModifiers(newHand);
    }

    private void playerStands() {
        //do nothing
    }

    private void playerDoubles(HandWithBetModifier player) {
        if (player.count() > 2) {
            screen.writeOutput("Sorry, you can only double on your first turn.");
            calculateModifiers(player);
        } else {
            screen.writeOutput("You double your original bet and draw your final card.");
            player.doubleBet();
            player.draw(deck);
            screen.writeOutput(format("Your hand is now; %s", player));
        }
    }

    private void playerHits(HandWithBetModifier player) {
        player.draw(deck);
        screen.writeOutput(format("Your hand is now; %s", player));
        if (!playerHasGoneBust(player)) {
            calculateModifiers(player);
        }
    }

    private boolean playerHasGoneBust(Hand player) {
        return player.state() == HandState.BUST;
    }
}
