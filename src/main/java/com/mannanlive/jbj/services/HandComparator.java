package com.mannanlive.jbj.services;

import com.mannanlive.jbj.constants.HandEndConditions;
import com.mannanlive.jbj.constants.HandState;
import com.mannanlive.jbj.models.Hand;

public class HandComparator {
    public HandEndConditions compare(Hand player, Hand dealer) {
        if (player.state() == HandState.BUST) {
            return HandEndConditions.BUST_LOSS;
        }
        if (player.state() == HandState.BLACKJACK) {
            return resolvePlayerBlackJack(dealer);
        }
        if (dealer.state() == HandState.BLACKJACK) {
            return HandEndConditions.BLACKJACK_LOSS;
        }
        if (dealer.state() == HandState.BUST) {
            return HandEndConditions.BUST_WIN;
        }
        return resolveBasedOnValue(player.value(), dealer.value());
    }

    private HandEndConditions resolvePlayerBlackJack(Hand dealer) {
        if (dealer.state() == HandState.BLACKJACK) {
            return HandEndConditions.BLACKJACK_PUSH;
        }
        return HandEndConditions.BLACKJACK_WIN;
    }

    private HandEndConditions resolveBasedOnValue(int playerValue, int dealerValue) {
        if (dealerValue == playerValue) {
            return HandEndConditions.PUSH;
        }
        if (dealerValue > playerValue) {
            return HandEndConditions.LOSS;
        }
        return HandEndConditions.WIN;
    }
}
