package com.example.gamecards;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    private ImageView main_IMG_player_left;
    private TextView main_LBL_score_one;
    private ImageView main_IMG_card_one;
    private TextView main_LBL_score_two;
    private ImageView main_IMG_card_two;
    private ImageView main_IMG_crown;
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
        main_IMG_crown = findViewById((R.id.main_IMG_crown));

        main_LBL_nameOne = findViewById((R.id.main_LBL_nameOne));
        main_LBL_nameTwo = findViewById((R.id.main_LBL_nameTwo));

        packCards = loadAllImages();
        main_IMG_crown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(packCards.size() < 2){
                    moveToWinnerActivity();
                }
                else{
                Card[] twoCards = getTwoCardsFromPack(packCards);
                Log.d("pack", "After get two cards: " + packCards.size());
                main_IMG_card_one.setImageResource(twoCards[0].getResourceId());
                main_IMG_card_one.setVisibility(View.VISIBLE);
                main_IMG_card_two.setImageResource(twoCards[1].getResourceId());
                main_IMG_card_two.setVisibility(View.VISIBLE);
                updateScore(twoCards);
                }
            }
        });

    }

    private Card[] getTwoCardsFromPack(ArrayList<Card> packCards) {
        Card[] cards = new Card[2];
        cards[0] = new Card();
        cards[1] = new Card();
        cards[0].setResourceId(packCards.get(0).getResourceId());
        cards[0].setValue(packCards.get(0).getValue());
        packCards.remove(0);
        cards[1].setResourceId(packCards.get(0).getResourceId());
        cards[1].setValue(packCards.get(0).getValue());
        packCards.remove(0);
        return cards;
    }

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


    private void updateScore(Card[] twoCards) {
        if(twoCards[0].getValue() > twoCards[1].getValue()){
            scorePlayerOne += 1;
            main_LBL_score_one.setText("" + scorePlayerOne);
        }else if(twoCards[0].getValue() < twoCards[1].getValue()) {
            scorePlayerTwo += 1;
            main_LBL_score_two.setText("" + scorePlayerTwo);
        }else{
            scorePlayerTwo += 1;
            scorePlayerOne += 0;
            main_LBL_score_one.setText("" + scorePlayerOne);
            main_LBL_score_two.setText("" + scorePlayerTwo);
        }

    }

    private ArrayList<Card> loadAllImages(){
        ArrayList<Card> packCards = new ArrayList<Card>();
        String[] names  = new String[]{"img_poker_card_a","img_poker_card_b","img_poker_card_c","img_poker_card_d"};
        String imageName;
        int resourceId;
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j <= 14; j++) {
                imageName = "@drawable/" + names[i] + "" + j;
                resourceId = getResourceId(imageName);
               // Log.d("test", "before resourceID:" + resourceId);
//                Log.d("test", imageName);
                Card card = new Card(resourceId , j);
                //Log.d("test", "resourceId :"+ card.getResourceId());
                packCards.add(card);
            }
        }
        //Shuffling the cards
        Collections.shuffle(packCards);
        return packCards;
    }

    //Get the resource id of image
    private int getResourceId(String imageName){
        return this.getResources().getIdentifier(imageName,null,this.getPackageName());
    }
}