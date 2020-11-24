package com.example.warcardgame.objects;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;

    /*public static void createNewDeck(){
        String imageName;
        int resourceId;
        String[] cards_name  = new String[]{"img_poker_card_a","img_poker_card_b","img_poker_card_c","img_poker_card_d"};
        ArrayList<Card> packCards = new ArrayList<Card>();
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j <= 14; j++) {
                imageName = "@drawable/" + cards_name[i] + "" + j;
                resourceId = getResourceId(imageName);
                Card card = new Card(resourceId , j);
                packCards.add(card);
            }
        }
        //Shuffling the cards
        Collections.shuffle(packCards);
    }

    private int getResourceId(String imageName){
        return this.getResources().getIdentifier(imageName,null,this.getPackageName());
    }*/
}

