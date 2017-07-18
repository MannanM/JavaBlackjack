package com.mannanlive.jbj;

import com.mannanlive.jbj.interfaces.Screen;
import com.mannanlive.jbj.services.Table;

public class Main {

    public static void main(String[] args) throws Exception {
        new Table().play(new Screen());
    }

}
