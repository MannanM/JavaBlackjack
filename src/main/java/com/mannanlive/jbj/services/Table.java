package com.mannanlive.jbj.services;

import com.mannanlive.jbj.constants.GameState;
import com.mannanlive.jbj.interfaces.Screen;
import com.mannanlive.jbj.models.Deck;

public class Table {
    private double monies = 100.0;

    public void play(Screen screen) {
        screen.writeOutput("Welcome to Java Blackjack!");
        while (monies > 0) {
            screen.writeOutput(String.format("\n\nYou currently have $%f, what would you like to bet?", monies));
            int bet = screen.getNumber();
            GameState result = new Game(screen, new Deck(6)).play();
            if (result == GameState.LOST) {
                monies -= bet;
            } else if (result == GameState.WON) {
                monies += bet;
            } else if (result == GameState.BLACKJACK) {
                monies += (bet * 1.5);
            }
        }
    }
}
