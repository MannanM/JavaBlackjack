package com.mannanlive.jbj.services;

import com.mannanlive.jbj.constants.HandEndConditions;
import com.mannanlive.jbj.constants.HandState;
import com.mannanlive.jbj.interfaces.Screen;
import com.mannanlive.jbj.models.Deck;
import com.mannanlive.jbj.models.Hand;
import com.mannanlive.jbj.models.HandStateWithBetModifier;
import com.mannanlive.jbj.models.HandWithBetModifier;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class Game {
    private final Screen screen;
    private final Deck deck;
    private final PlayerActionService playerAction;
    private final DealerActionService dealerAction;
    private Hand dealer;
    private List<HandWithBetModifier> player;
    private HandComparator handComparator = new HandComparator();

    public Game(Screen screen, Deck deck) {
        this.screen = screen;
        this.deck = deck;
        this.playerAction = new PlayerActionService(screen, deck);
        this.dealerAction = new DealerActionService(screen, deck);
        deck.shuffle();
    }

    public List<HandStateWithBetModifier> play() {
        dealer = new Hand();
        HandWithBetModifier playersFirstHand = new HandWithBetModifier();
        playersFirstHand.draw(deck);
        dealer.draw(deck);
        playersFirstHand.draw(deck);
        dealer.drawFaceDown(deck);
        screen.writeOutput(format("Dealer has been dealt; %s", dealer));
        screen.writeOutput(format("You have been dealt; %s", playersFirstHand));
        return playersTurn(playersFirstHand);
    }

    private List<HandStateWithBetModifier> playersTurn(HandWithBetModifier playersFirstHand) {
        player = playerAction.play(playersFirstHand);
        int bustHands = 0;
        for (Hand hand : player) {
            if (hand.state() == HandState.BUST) {
                bustHands++;
            }
        }
        if (bustHands == player.size()) {
            return calculateResults();
        }
        return dealersTurn();
    }

    private List<HandStateWithBetModifier> dealersTurn() {
        dealerAction.play(dealer);
        return calculateResults();
    }

    private List<HandStateWithBetModifier> calculateResults() {
        List<HandStateWithBetModifier> results = new ArrayList<>(player.size());
        for (HandWithBetModifier hand : player) {
            HandEndConditions gameEndState = handComparator.compare(hand, dealer);
            results.add(publishResult(gameEndState, hand));
        }
        return results;
    }

    private HandStateWithBetModifier publishResult(HandEndConditions gameEndState, HandWithBetModifier playersHand) {
        screen.writeOutput(gameEndState.formatDescription(dealer.value(), playersHand.value()));
        return new HandStateWithBetModifier(gameEndState.getGameOutcome(), playersHand.getModifier());
    }
}
