package com.mannanlive.jbj.interfaces;

import com.mannanlive.jbj.constants.Event;

import java.util.Scanner;

public class Screen implements Subscriber {
    private Scanner scanner = new Scanner(System.in);

    public String getInput() {
        return scanner.nextLine();
    }

    public void writeOutput(String output) {
        pause(1500);
        System.out.println(output);
    }

    public int getNumber() {
        String s = scanner.nextLine();
        try {
            int i = Integer.parseInt(s);
            if (i < 0) {
                System.out.println("Please enter a positive number e.g 30");
                return getNumber();
            }
            return i;
        } catch (Exception ex) {
            System.out.println("Please try again and enter a number e.g. 65");
            return getNumber();
        }
    }

    private void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notify(Event event) {
        if (event == Event.OUT_OF_CARDS) {
            writeOutput("Deck is out of cards, requesting a new deck.");
        }
    }
}
