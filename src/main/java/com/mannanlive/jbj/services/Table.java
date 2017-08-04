package com.mannanlive.jbj.services;

import com.mannanlive.jbj.interfaces.Screen;
import com.mannanlive.jbj.models.Deck;
import com.mannanlive.jbj.models.HandStateWithBetModifier;

import java.util.List;

import static java.lang.String.format;

public class Table {
    private static final int NUMBER_OF_DECKS = 6;
    private double monies = 100.0;

    public void play(Screen screen) {
        screen.writeOutput("Welcome to Mannan's Vanilla Java Blackjack!");
        Deck deck = new Deck(NUMBER_OF_DECKS);
        deck.addSubscriber(screen);

        Game game = new Game(screen, deck);
        while (monies > 0) {
            betForRound(screen, game);
        }
        screen.writeOutput("Sorry, you are out of monies!");
    }

    private void betForRound(Screen screen, Game game) {
        screen.writeOutput(format("\n\nYou currently have $%f, what would you like to bet?", monies));
        int bet = screen.getNumber();
        if (bet <= monies) {
            playAGame(game, bet);
        } else {
            screen.writeOutput(format("Nice try but you don't have enough monies to place a $%d bet!", bet));
        }
    }

    private void playAGame(Game game, int bet) {
        List<HandStateWithBetModifier> results = game.play();
        for (HandStateWithBetModifier result : results) {
            monies += result.calculatePayout(bet);
        }
    }
}
