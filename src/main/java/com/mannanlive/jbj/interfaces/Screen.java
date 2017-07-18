package com.mannanlive.jbj.interfaces;

import java.util.Scanner;

public class Screen {
    private Scanner scanner = new Scanner(System.in);

    public String getInput() {
        return scanner.nextLine();
    }

    public void writeOutput(String output) {
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

    public void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
