package com.mannanlive.jbj.constants;

public enum PlayerAction {
    HIT('H', "(H)it"), STAND('S', "(S)tand"), DOUBLE('D', "(D)ouble"), SPLIT('P', "S(p)lit");

    private final char shortcut;
    private final String display;

    PlayerAction(char shortcut, String display) {
        this.shortcut = shortcut;
        this.display = display;
    }

    @Override
    public String toString() {
        return display;
    }

    public char getShortcut() {
        return shortcut;
    }
}
