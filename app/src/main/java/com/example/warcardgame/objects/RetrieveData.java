package com.example.warcardgame.objects;

public class RetrieveData {
    private String player1ImgIconName;
    private String player2ImgIconName;
    private int player1Score;
    private int player2Score;

    public RetrieveData() {

    }

    public RetrieveData(String player1ImgIconName, String player2ImgIconName, int player1Score, int player2Score) {
        this.player1ImgIconName = player1ImgIconName;
        this.player2ImgIconName = player2ImgIconName;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
    }

    public String getPlayer1ImgIconName() {
        return player1ImgIconName;
    }

    public String getPlayer2ImgIconName() {
        return player2ImgIconName;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

}
