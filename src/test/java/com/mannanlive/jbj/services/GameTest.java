package com.mannanlive.jbj.services;

import com.mannanlive.jbj.constants.Suite;
import com.mannanlive.jbj.constants.Value;
import com.mannanlive.jbj.interfaces.Screen;
import com.mannanlive.jbj.models.Card;
import com.mannanlive.jbj.models.Deck;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {
    @Mock
    private Deck deck;

    @Mock
    private Screen screen;

    @InjectMocks
    private Game game;

    @Test
    public void gameNaturalBlackJack() throws Exception {
        when(deck.draw()).thenReturn(
                new Card(Suite.CLUBS, Value.TEN), //player
                new Card(Suite.CLUBS, Value.TEN),
                new Card(Suite.CLUBS, Value.ACE), //player
                new Card(Suite.CLUBS, Value.TEN));

        when(screen.getInput()).thenReturn("STAND");
        Assert.assertEquals(150.0, game.play().get(0).calculatePayout(100), 0);
    }

    @Test
    public void winWithLargerNumber() throws Exception {
        when(deck.draw()).thenReturn(
                new Card(Suite.CLUBS, Value.TEN), //player
                new Card(Suite.CLUBS, Value.TEN),
                new Card(Suite.CLUBS, Value.TEN), //player
                new Card(Suite.CLUBS, Value.TEN),
                new Card(Suite.CLUBS, Value.ACE)); //player

        when(screen.getInput()).thenReturn("HIT", "STAND");

        Assert.assertEquals(100.0, game.play().get(0).calculatePayout(100), 0);
    }

}