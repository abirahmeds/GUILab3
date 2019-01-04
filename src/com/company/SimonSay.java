package com.company;


import java.util.ArrayList;

public class SimonSay {
    private ArrayList<String> pattern;
    private final String[] options = { "Green", "Red", "Yellow", "Blue"};
    private final int STARTAMOUNT = 3;
    private int currentIndex;
    private boolean gameOn;

    public SimonSay() {
        currentIndex = 0;
        pattern = new ArrayList<>();
        generateRandomPattern();
        gameOn = true;
    }

    private void generateRandomPattern() {
        for (int i = 0; i < STARTAMOUNT; i++) {
            pattern.add(options[(int)(Math.random() * options.length)]);
        }
    }

    private void addToPattern() {
        pattern.add(options[(int)(Math.random() * options.length)]);
    }

    public boolean checkInput(String input) {
        if (pattern.get(currentIndex).equals(input)) {
            if (currentIndex == (pattern.size() - 1)) {
                addToPattern();
                currentIndex = 0;
                return true;
            }
            currentIndex++;
            return true;
        } else {
            endGame();
            return false;
        }
    }

    public void endGame() {
        pattern.clear();
        currentIndex = 0;
        gameOn = false;
    }

    public ArrayList<String> getPattern() { return this.pattern; }
    public int getCurrentIndex() { return this.currentIndex; }
}