package com.exercicio05;

public class Secreto {
    private String guess;
    private String feedback;

    public Secreto(String guess, String feedback) {
        this.guess = guess;
        this.feedback = feedback;
    }

    public String getGuess() {
        return guess;
    }

    public String getFeedback() {
        return feedback;
    }
}
