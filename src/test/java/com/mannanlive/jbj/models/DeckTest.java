package com.mannanlive.jbj.models;

import org.junit.Assert;
import org.junit.Test;

public class DeckTest {

    @Test(expected = IllegalArgumentException.class)
    public void canNotInitialiseDeckWithABadNumber() {
        new Deck(-1);
    }

    @Test
    public void deckCreationWorksAsExpected() {
        Assert.assertEquals(52, new Deck(1).count());
        Assert.assertEquals(104, new Deck(2).count());
    }

    @Test
    public void deckCanShuffle() {
        new Deck(1).shuffle();
    }

    @Test
    public void deckCanDrawACardAndRemovesIt() {
        Deck deck = new Deck(1);
        Card card = deck.draw();
        Assert.assertNotNull(card);

        //Assert it has been removed from the deck
        Assert.assertEquals(51, deck.count());
        for (int i = 0; i < 51; i++) {
            Assert.assertNotEquals(card, deck.draw());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void deckCanRunOutOfCards() {
        Deck deck = new Deck(1);
        for (int i = 0; i < 53; i++) {
            deck.draw();
        }
    }


}