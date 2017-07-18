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

    @Override
    public int compareTo(Object o) {
        return value.getValue() - ((Card)o).getValue().getValue();
    }

    public Suite getSuite() {
        return suite;
    }
}
