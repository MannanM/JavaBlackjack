package com.mannanlive.jbj.models;

import com.mannanlive.jbj.constants.HandState;
import com.mannanlive.jbj.constants.Value;

import java.util.*;

public class Hand {
    private List<Card> cards = new ArrayList<>();
    private List<Card> hiddenCards = new ArrayList<>();
    private boolean containsAce;

    public void draw(Deck deck) {
        Card draw = deck.draw();
        addCardToHand(draw);
    }

    public int count() {
        return cards.size();
    }

    public int value() {
        int value = 0;
        for (Card card : cards) {
            value += card.getValue().getValue();
        }
        if (value <= 11 && containsAce) {
            value += 10;
        }
        return value;
    }

    public HandState state() {
        int value = value();
        if (value > 21) {
            return HandState.BUST;
        }
        if (count() == 2 && containsAce && value == 21) {
            return HandState.BLACKJACK;
        }
        return HandState.NORMAL;
    }

    public void drawFaceDown(Deck deck) {
        hiddenCards.add(deck.draw());
    }

    public void revealHiddenCard() {
        if (hiddenCards.isEmpty()) {
            throw new IllegalArgumentException("You can't reveal a hidden card if you don't have any.");
        }
        addCardToHand(hiddenCards.get(0));
        hiddenCards.remove(0);
    }

    private void addCardToHand(Card draw) {
        if (draw.getValue() == Value.ACE) {
            containsAce = true;
        }
        cards.add(draw);
    }

    @Override
    public String toString() {
        String cards = join(this.cards);
        return String.format("%s - Total: %d", cards, value());
    }

    private static String join(Collection collection) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Iterator iterator = collection.iterator(); iterator.hasNext(); stringBuilder.append(iterator.next())) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append(", ");
            }
        }

        return stringBuilder.toString();
    }

    public boolean canSplit() {
        return count() == 2 && cardValue(0) == cardValue(1);
    }

    public void split(Hand newHand) {
        if (canSplit()) {
            Card secondCard = cards.get(1);
            cards.remove(secondCard);
            newHand.addCardToHand(secondCard);
        }
    }

    private int cardValue(int index) {
        return cards.get(index).getValue().getValue();
    }
}
