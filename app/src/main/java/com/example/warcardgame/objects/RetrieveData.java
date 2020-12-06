package com.example.warcardgame.objects;

public class RetrieveData {
    private String player1ImgIconName;
    private String player2ImgIconName;
    private Player player1;
    private Player player2;
    private Winner winner = null;

    public RetrieveData() {

    }

    public RetrieveData(Winner winner) {
        this.winner = winner;
    }

    public RetrieveData(String player1ImgIconName, String player2ImgIconName, Player player1, Player player2) {
        this.player1ImgIconName = player1ImgIconName;
        this.player2ImgIconName = player2ImgIconName;
        this.player1 = player1;
        this.player2 = player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public String getPlayer1ImgIconName() {
        return player1ImgIconName;
    }

    public String getPlayer2ImgIconName() {
        return player2ImgIconName;
    }


    public Winner getWinner() {
        return winner;
    }

    public void setWinner(Winner winner) {
        this.winner = winner;
    }


}
