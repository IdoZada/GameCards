package com.example.warcardgame;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cardsInHand;

    public Hand(){

    }

    public Hand(ArrayList<Card> cardsInHand) {
        this.cardsInHand = cardsInHand;
    }

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    public void setCardsInHand(ArrayList<Card> cardsInHand) {
        this.cardsInHand = cardsInHand;
    }
}
