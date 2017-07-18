package com.mannanlive.jbj.services;

import com.mannanlive.jbj.constants.GameState;
import com.mannanlive.jbj.constants.HandState;
import com.mannanlive.jbj.constants.PlayerAction;
import com.mannanlive.jbj.interfaces.PlayerInput;
import com.mannanlive.jbj.interfaces.Screen;
import com.mannanlive.jbj.models.Deck;
import com.mannanlive.jbj.models.Hand;

import static java.lang.String.format;

public class Game {
    private final Screen screen;
    private final PlayerInput playerInput;
    private final Deck deck;
    private Hand dealer;
    private Hand player;

    public Game(Screen screen, Deck deck) {
        this.screen = screen;
        this.deck = deck;
        deck.shuffle();
        playerInput = new PlayerInput(screen);
    }

    public GameState play() {
        dealer = new Hand();
        player = new Hand();
        player.draw(deck);
        dealer.draw(deck);
        player.draw(deck);
        dealer.drawFaceDown(deck);
        screen.writeOutput(format("Dealer has been dealt a %s", dealer));
        screen.writeOutput(format("You have been dealt a %s", player));
        return playersTurn();
    }

    private GameState playersTurn() {
        if (player.state() == HandState.BLACKJACK) {
            screen.writeOutput("Congratulations!! You have a natural Blackjack!");
            return dealersTurn();
        }
        PlayerAction action = playerInput.action();
        switch (action) {
            case HIT:
                player.draw(deck);
                screen.writeOutput(format("Your hand is now a %s", player));
                if (player.state() == HandState.BUST) {
                    screen.writeOutput("Oh no! You are bust!!");
                    return GameState.LOST;
                }
                return playersTurn();
            default:
                return dealersTurn();
        }
    }

    private GameState dealersTurn() {
        screen.writeOutput(format("The dealer starts off with a %s", dealer));
        dealer.revealHiddenCard();
        screen.writeOutput(format("Revealing the hidden card results in the dealer having a %s", dealer));
        while (dealer.value() < 17) {
            screen.writeOutput("The dealer is drawing another card...");
            dealer.draw(deck);
            screen.pause(3000L);
            screen.writeOutput(format("The dealers hand is now a %s", dealer));
        }
        if (player.state() == HandState.BLACKJACK) {
            if (dealer.state() == HandState.BLACKJACK) {
                screen.writeOutput("Oh no! How unlucky. The dealer has a natural Blackjack.");
                screen.writeOutput("This hand pushes (ties) with yours so you will get your original bet back.");
                return GameState.PUSH;
            }
            screen.writeOutput(format("Your natural Blackjack beats the dealer's %d", dealer.value()));
            return GameState.BLACKJACK;
        } else if (dealer.state() == HandState.BLACKJACK) {
            screen.writeOutput("Oh no! How unlucky. The dealer has a natural Blackjack.");
            return GameState.LOST;
        }
        if (dealer.state() == HandState.BUST) {
            screen.writeOutput("Yes!! You won as the dealer has gone bust.");
            return GameState.WON;
        }
        if (dealer.value() == player.value()) {
            screen.writeOutput(format(
                    "Oh no! You and the dealer have the same value (%s), your hand pushes (tie).", dealer.value()));
            return GameState.PUSH;
        }
        if (dealer.value() >= player.value()) {
            screen.writeOutput(format("Oh no! The dealer has beaten you with a value of %d compared to your %d.",
                    dealer.value(), player.value()));
            return GameState.LOST;
        }
        screen.writeOutput(format("Yes!! You won with a value of %d compared to the dealer's %d.",
                player.value(), dealer.value()));
        return GameState.WON;
    }
}
