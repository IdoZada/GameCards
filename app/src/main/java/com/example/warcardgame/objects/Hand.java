package com.example.warcardgame.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterators;

public class Hand {
    private ArrayList<Card> cardsInHand;
    private Deck deck;

    private boolean isEmpty = false;

    public Hand(Deck deck) {
        this.cardsInHand = new ArrayList<Card>();
        this.deck = deck;
    }

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

    public Card getCardFromHand(int index){
        return this.cardsInHand.get(index);
    }

    public void splitDeckToHand(int from , int to){
        List<Card> list = this.deck.getDeck().subList(from,to);
        this.cardsInHand.addAll(list);
    }

    public boolean isEmpty() {
        if(this.cardsInHand.size() == 0){
            return true;
        }else
            return false;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}
