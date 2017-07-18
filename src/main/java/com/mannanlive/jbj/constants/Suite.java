package com.mannanlive.jbj.constants;

public enum Suite {
    SPADES("Spades", Colour.BLACK),
    HEARTS("Hearts", Colour.RED),
    CLUBS("Clubs", Colour.BLACK),
    DIAMONDS("Diamonds", Colour.RED);

    private String name;
    private Colour colour;

    Suite(String name, Colour colour) {
        this.name = name;
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }
}
