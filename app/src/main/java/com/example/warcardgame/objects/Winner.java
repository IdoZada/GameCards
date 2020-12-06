package com.example.warcardgame.objects;

import android.location.Location;

public class Winner {
    private Player player;
    private Player player2;
    private MoveActivity moveActivity;
    //location
    //date


    public Winner() {
    }

    public Winner(Player player) {
        this.player = player;
        this.moveActivity = MoveActivity.WINNER;
    }

    public Winner(Player player, Player player2) {
        this.player = player;
        this.player2 = player2;
        this.moveActivity = MoveActivity.DRAW;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public MoveActivity getMoveActivity() {
        return moveActivity;
    }

    public void setMoveActivity(MoveActivity moveActivity) {
        this.moveActivity = moveActivity;
    }

}
