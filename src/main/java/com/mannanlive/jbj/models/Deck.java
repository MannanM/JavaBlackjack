package com.mannanlive.jbj.models;

import com.mannanlive.jbj.constants.Suite;
import com.mannanlive.jbj.constants.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck(int numberOfDecks) {
        if (numberOfDecks < 1) {
            throw new IllegalArgumentException("Must start with a positive number of standard card decks (52 cards).");
        }
        for (int i = 0; i < numberOfDecks; i++) {
            addBaseDeck();
        }
    }

    private void addBaseDeck() {
        for (Suite suite : Suite.values()) {
            for (Value value : Value.values()) {
                cards.add(new Card(suite, value));
            }
        }
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("Can not draw a card when there are none remaining.");
        }
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }

    public void shuffle() {
        Collections.shuffle(cards, new Random(new Date().getTime()));
    }

    public int count() {
        return cards.size();
    }

}
