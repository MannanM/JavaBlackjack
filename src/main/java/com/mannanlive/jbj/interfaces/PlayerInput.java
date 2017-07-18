package com.mannanlive.jbj.interfaces;

import com.mannanlive.jbj.constants.PlayerAction;

import java.util.Arrays;

public class PlayerInput {
    private final Screen screen;

    public PlayerInput(Screen screen) {
        this.screen = screen;
    }

    public PlayerAction action() {
        screen.writeOutput(String.format("What would you like to do? %s", Arrays.toString(PlayerAction.values())));
        PlayerAction action = null;
        while (action == null) {
            String input = screen.getInput().toUpperCase();
            try {
                action = PlayerAction.valueOf(input);
            } catch (IllegalArgumentException ex) {
                screen.writeOutput(String.format("Sorry, '%s' is not a valid option", input));
            }
        }
        return action;
    }
}
