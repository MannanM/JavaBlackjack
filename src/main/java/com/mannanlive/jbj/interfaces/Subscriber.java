package com.mannanlive.jbj.interfaces;

import com.mannanlive.jbj.constants.Event;

public interface Subscriber {
    void notify(Event event);
}
