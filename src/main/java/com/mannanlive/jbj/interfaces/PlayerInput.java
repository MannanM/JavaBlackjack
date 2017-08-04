package com.mannanlive.jbj.interfaces;

import com.mannanlive.jbj.constants.PlayerAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerInput {
    private final Screen screen;
    private final String options;

    public PlayerInput(Screen screen) {
        this.screen = screen;
        options = Arrays.toString(PlayerAction.values());
    }

    public PlayerAction action() {
        screen.writeOutput(String.format("What would you like to do? %s", options));
        PlayerAction action = null;
        while (action == null) {
            String input = screen.getInput().toUpperCase();
            try {
                action = PlayerAction.valueOf(input);
            } catch (IllegalArgumentException ex) {
                action = checkForShortcut(input);
                if (action == null) {
                    screen.writeOutput(String.format("Sorry, '%s' is not a valid option", input));
                }
            }
        }
        return action;
    }

    private PlayerAction checkForShortcut(String input) {
        if (input.length() >= 1) {
            for (PlayerAction playerAction : PlayerAction.values()) {
                if (playerAction.getShortcut() == input.charAt(0)) {
                    return playerAction;
                }
            }
        }
        return null;
    }
}
