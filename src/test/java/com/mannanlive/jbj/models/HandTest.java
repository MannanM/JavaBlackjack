package com.mannanlive.jbj.models;

import com.mannanlive.jbj.constants.HandState;
import com.mannanlive.jbj.constants.Suite;
import com.mannanlive.jbj.constants.Value;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HandTest {
    @Mock
    private Deck deck;

    @InjectMocks
    private Hand hand;

    @Test
    public void handInitialisesToZero() {
        Assert.assertEquals(0, hand.count());
        Assert.assertEquals(0, hand.value());
    }

    @Test
    public void drawAddsToCountAndValue() throws Exception {
        when(deck.draw()).thenReturn(new Card(Suite.CLUBS, Value.TWO));

        hand.draw(deck);

        Assert.assertEquals(1, hand.count());
        Assert.assertEquals(2, hand.value());
    }

    @Test
    public void aceIsCalculatedCorrectly() throws Exception {
        when(deck.draw()).thenReturn(new Card(Suite.CLUBS, Value.ACE));
        hand.draw(deck);
        Assert.assertEquals(11, hand.value());

        when(deck.draw()).thenReturn(new Card(Suite.CLUBS, Value.TWO));
        hand.draw(deck);
        Assert.assertEquals(13, hand.value());

        when(deck.draw()).thenReturn(new Card(Suite.CLUBS, Value.JACK));
        hand.draw(deck);
        Assert.assertEquals(13, hand.value());

        when(deck.draw()).thenReturn(new Card(Suite.CLUBS, Value.JACK));
        hand.draw(deck);
        Assert.assertEquals(23, hand.value());
    }

    @Test
    public void stateIsCalculatedCorrectly() throws Exception {
        when(deck.draw()).thenReturn(new Card(Suite.CLUBS, Value.TEN));
        hand.draw(deck);
        Assert.assertEquals(HandState.NORMAL, hand.state());

        when(deck.draw()).thenReturn(new Card(Suite.CLUBS, Value.ACE));
        hand.draw(deck);
        Assert.assertEquals(HandState.BLACKJACK, hand.state());

        when(deck.draw()).thenReturn(new Card(Suite.CLUBS, Value.TEN));
        hand.draw(deck);
        Assert.assertEquals(HandState.NORMAL, hand.state());

        when(deck.draw()).thenReturn(new Card(Suite.CLUBS, Value.TEN));
        hand.draw(deck);
        Assert.assertEquals(HandState.BUST, hand.state());
    }

}