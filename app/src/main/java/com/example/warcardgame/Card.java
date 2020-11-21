package com.example.warcardgame;

public class Card {
    private int resourceId;
    private int value;

    public Card (){

    }

    public Card(int resourceId, int value) {
        this.resourceId = resourceId;
        this.value = value;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
