package com.mannanlive.jbj.models;

import com.mannanlive.jbj.constants.Event;
import com.mannanlive.jbj.interfaces.Subscriber;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DeckTest {
    @Mock
    private Subscriber subscriber;

    @InjectMocks
    private Deck deck = new Deck(1);

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
        deck.shuffle();
    }

    @Test
    public void deckCanDrawACardAndRemovesIt() {
        Card card = deck.draw();
        Assert.assertNotNull(card);

        //Assert it has been removed from the deck
        Assert.assertEquals(51, deck.count());
        for (int i = 0; i < 51; i++) {
            Assert.assertNotEquals(card, deck.draw());
        }
    }

    @Test
    public void deckCanRunOutOfCards() {
        deck.addSubscriber(subscriber);
        subscriber.notify(Event.OUT_OF_CARDS);
        for (int i = 0; i < 53; i++) {
            deck.draw();
        }
    }


}