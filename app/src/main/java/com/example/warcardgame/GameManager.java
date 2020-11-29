package com.example.warcardgame;

import android.util.Log;

import com.example.warcardgame.objects.Card;
import com.example.warcardgame.objects.Deck;
import com.example.warcardgame.objects.Hand;
import com.example.warcardgame.objects.Player;
import com.example.warcardgame.objects.RetrieveData;
import com.example.warcardgame.objects.Winner;

public class GameManager {
    private Player player1;
    private Player player2;
    private Winner winner;

    public GameManager() {
        initGame();
    }

    public void initGame(){
        Deck deck = new Deck();
        Hand hand1 = new Hand(deck);
        hand1.splitDeckToHand(0,26);
        Hand hand2 = new Hand(deck);
        hand2.splitDeckToHand(26,52);
        player1 = new Player("Ido",hand1);
        player2 = new Player("Romi",hand2);

    }

    public RetrieveData gameStep(){
        if(!player1.getHand().isEmpty()){
            Card[] cards = getTwoCardsFromPlayers();
            Log.d("test",  " " + player1.getHand().getCardsInHand().size());
            compareAndUpdateScore(cards);
            RetrieveData retrieveData = new RetrieveData(cards[0].getImgIconName(),
                    cards[1].getImgIconName(),
                    getScore(player1),
                    getScore(player2));
            return retrieveData;
        }else {
            winner = checkWinner();
            RetrieveData retrieveData = new RetrieveData(winner);
            return retrieveData;
        }
    }


    public Card[] getTwoCardsFromPlayers(){
        Card[] cards = new Card[2];
        cards[0] = player1.getHand().getCardFromHand(0);
        player1.getHand().getCardsInHand().remove(0);
        cards[1] = player2.getHand().getCardFromHand(0);
        player2.getHand().getCardsInHand().remove(0);
        return cards;
    }

    public int getScore(Player player){
        return player.getScore();
    }

    public void compareAndUpdateScore(Card[] cards){
        if(cards[0].getValue() > cards[1].getValue())
            player1.setScore(1);
        else if(cards[1].getValue() > cards[0].getValue())
            player2.setScore(1);
    }

    public Winner checkWinner(){
        //Player 1 winner
        if(player1.getScore() > player2.getScore()){
            return new Winner(player1);
            //Player 2 winner
        }else if(player1.getScore() < player2.getScore()){
            return new Winner(player2);
        //Draw
        }else
            return new Winner(player1,player2);
    }

}
