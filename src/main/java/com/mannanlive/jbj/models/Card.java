package com.mannanlive.jbj.models;

import com.mannanlive.jbj.constants.Suite;
import com.mannanlive.jbj.constants.Value;

public class Card implements Comparable {
    private Suite suite;
    private Value value;

    public Card(Suite suite, Value value) {
        this.suite = suite;
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

    public Suite getSuite() {
        return suite;
    }

    @Override
    public int compareTo(Object o) {
        return value.getValue() - ((Card)o).getValue().getValue();
    }

    @Override
    public String toString() {
        return String.format("%s of %s", value.getDisplay(), suite.getName());
    }
}
