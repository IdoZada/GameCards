package com.example.warcardgame.objects;

import android.util.Log;
import com.example.warcardgame.objects.Card;
import com.example.warcardgame.objects.Deck;
import com.example.warcardgame.objects.Hand;
import com.example.warcardgame.objects.MoveActivity;
import com.example.warcardgame.objects.Player;
import com.example.warcardgame.objects.RetrieveData;
import com.example.warcardgame.objects.WinnerPlayer;

public class GameManager {
    private Player player1;
    private Player player2;
    private RetrieveData retrieveData = new RetrieveData();

    public GameManager() {

    }

    public void initGame(String playerNameOne,String playerNameTwo){
        Deck deck = new Deck();
        Hand hand1 = new Hand(deck);
        hand1.splitDeckToHand(0,26);
        Hand hand2 = new Hand(deck);
        hand2.splitDeckToHand(26,52);
        player1 = new Player(playerNameOne,hand1);
        player2 = new Player(playerNameTwo,hand2);

    }

    public RetrieveData gameStep(){
        if(!player1.getHand().isEmpty()){
            Card[] cards = getTwoCardsFromPlayers();
            Log.d("test",  " " + player1.getHand().getCardsInHand().size());
            compareAndUpdateScore(cards);
            retrieveData.setPlayer1ImgIconName(cards[0].getImgIconName());
            retrieveData.setPlayer2ImgIconName(cards[1].getImgIconName());
            retrieveData.setPlayer1(player1);
            retrieveData.setPlayer2(player2);
        }else {
            retrieveData.setWinnerPlayer(checkWinner());
        }
        return retrieveData;
    }


    public Card[] getTwoCardsFromPlayers(){
        Card[] cards = new Card[2];
        cards[0] = player1.getHand().getCardFromHand(0);
        player1.getHand().getCardsInHand().remove(0);
        cards[1] = player2.getHand().getCardFromHand(0);
        player2.getHand().getCardsInHand().remove(0);
        return cards;
    }

    public void compareAndUpdateScore(Card[] cards){
        if(cards[0].getValue() > cards[1].getValue())
            player1.setScore(1);
        else if(cards[1].getValue() > cards[0].getValue())
            player2.setScore(1);
    }


    public WinnerPlayer checkWinner() {
        //Player 1 winner
        if (player1.getScore() > player2.getScore()) {
            return new WinnerPlayer(player1.getName(),player1.getScore(), MoveActivity.WINNER);
            //Player 2 winner
        } else if(player1.getScore() < player2.getScore()){
            return new WinnerPlayer(player2.getName(),player2.getScore(), MoveActivity.WINNER);
        }else
            return new WinnerPlayer(player1.getName(),player1.getScore(), MoveActivity.DRAW);
    }

}
