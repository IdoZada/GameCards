package com.example.gamecards;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private TextView main_LBL_score_one;
    private ImageView main_IMG_card_one;
    private TextView main_LBL_score_two;
    private ImageView main_IMG_card_two;
    private ImageView main_IMG_play_button;
    private ArrayList<Card> packCards = new ArrayList<Card>();
    private TextView main_LBL_nameOne;
    private TextView main_LBL_nameTwo;
    private int scorePlayerOne = 0;
    private int scorePlayerTwo = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_LBL_score_one = findViewById(R.id.main_LBL_score_one);
        main_IMG_card_one = findViewById(R.id.main_IMG_card_one);
        main_LBL_score_two = findViewById(R.id.main_LBL_score_two);
        main_IMG_card_two = findViewById(R.id.main_IMG_card_two);
        main_IMG_play_button = findViewById((R.id.main_IMG_play_button));
        main_LBL_nameOne = findViewById((R.id.main_LBL_nameOne));
        main_LBL_nameTwo = findViewById((R.id.main_LBL_nameTwo));

        packCards = loadAllImagesOfCards();

        main_IMG_play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(packCards.size() < 2){
                    moveToWinnerActivity();
                } else{
                    Card[] twoCards = getTwoCardsFromPack(packCards);
                    Log.d("cardPack", "Remaining cards in pack: " + packCards.size());
                    updateCardsView(twoCards);
                    updateScore(twoCards);
                }
            }
        });

    }
    /**
     * This function loads all images of cards from the drawable folder and save
     * resource id + value card
     * @return Array list of cards
     */
    private ArrayList<Card> loadAllImagesOfCards(){
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
        return packCards;
    }
    /**
     * This function get resource id of image
     * @param imageName The name of image in drawable folder
     * @return int The associated resource identifier. Returns 0 if no such resource was found.
     * (0 is not a valid resource ID.)
     */
    private int getResourceId(String imageName){
        return this.getResources().getIdentifier(imageName,null,this.getPackageName());
    }
    /**
     * This function get two cards from the pack (52 cards at the begining), initialize
     * and remove them from the pack
     * @param packCards Array list of cards
     * @return Array of two cards
     */
    private Card[] getTwoCardsFromPack(ArrayList<Card> packCards) {
        Card[] cards = new Card[2];
        for (int i = 0; i < 2; i++) {
            cards[i] = new Card();
            cards[i].setResourceId(packCards.get(0).getResourceId());
            cards[i].setValue(packCards.get(0).getValue());
            packCards.remove(0);
        }
        return cards;
    }
    /**
     * This function update cards view in the main activity
     * @param twoCards Array of 2 cards
     */
    private void updateCardsView(Card[] twoCards){
        main_IMG_card_one.setImageResource(twoCards[0].getResourceId());
        main_IMG_card_one.setVisibility(View.VISIBLE);
        main_IMG_card_two.setImageResource(twoCards[1].getResourceId());
        main_IMG_card_two.setVisibility(View.VISIBLE);
    }
    /**
     * This function switch the screen activity
     * from the main activity to winner activity
     */
    private void moveToWinnerActivity() {
        Intent myIntent = new Intent(MainActivity.this,WinnerActivity.class);
        if (this.scorePlayerOne > this.scorePlayerTwo){
            //Pass the count to new activity, save the count in map object that contain key and primitiv types
            myIntent.putExtra(WinnerActivity.EXTRA_KEY_WINNER_NAME, main_LBL_nameOne.getText().toString());
            myIntent.putExtra(WinnerActivity.EXTRA_KEY_WINNER_SCORE, scorePlayerOne);
        }else{
            myIntent.putExtra(WinnerActivity.EXTRA_KEY_WINNER_NAME, main_LBL_nameTwo.getText().toString());
            myIntent.putExtra(WinnerActivity.EXTRA_KEY_WINNER_SCORE, scorePlayerTwo);
        }
        startActivity(myIntent);
        finish();
    }
    /**
     * This function compare between the value of 2 cards and
     * update the score of the players in the main activity
     * @param twoCards Array of 2 cards
     */
    private void updateScore(Card[] twoCards) {
        if(twoCards[0].getValue() > twoCards[1].getValue()){
            scorePlayerOne += 1;
            main_LBL_score_one.setText("" + scorePlayerOne);
        }else if(twoCards[0].getValue() < twoCards[1].getValue()) {
            scorePlayerTwo += 1;
            main_LBL_score_two.setText("" + scorePlayerTwo);
        }
    }
}