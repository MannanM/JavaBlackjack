package com.mannanlive.jbj.models;

import com.mannanlive.jbj.constants.Event;
import com.mannanlive.jbj.constants.Suite;
import com.mannanlive.jbj.constants.Value;
import com.mannanlive.jbj.interfaces.Screen;
import com.mannanlive.jbj.interfaces.Subscriber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Deck {
    private final List<Subscriber> subscribers = new ArrayList<>();
    private final List<Card> cards = new ArrayList<>();
    private final int numberOfDecks;

    public Deck(int numberOfDecks) {
        if (numberOfDecks < 1) {
            throw new IllegalArgumentException("Must start with a positive number of standard card decks (52 cards).");
        }
        this.numberOfDecks = numberOfDecks;
        populateDeck();
    }

    private void populateDeck() {
        for (int i = 0; i < numberOfDecks; i++) {
            addBaseDeck();
        }
        notifySubscribers();
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
            addBaseDeck();
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

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    private void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.notify(Event.OUT_OF_CARDS);
        }
    }
}
