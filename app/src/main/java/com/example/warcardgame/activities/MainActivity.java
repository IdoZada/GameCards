package com.example.warcardgame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.warcardgame.GameManager;
import com.example.warcardgame.objects.Card;
import com.example.warcardgame.R;
import com.example.warcardgame.objects.Deck;
import com.example.warcardgame.objects.Hand;
import com.example.warcardgame.objects.RetrieveData;
import com.example.warcardgame.utils.MyScreenUtils;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends Activity_Base {
    private GameManager game = new GameManager();
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



        main_IMG_play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrieveData retrieveData = game.gameStepCard();
                main_LBL_score_one.setText("" + retrieveData.getPlayer1Score());
                main_LBL_score_two.setText("" + retrieveData.getPlayer2Score());
                Glide
                        .with(MainActivity.this)
                        .load("@drawable/" + retrieveData.getPlayer1ImgIconName())
                        .into(main_IMG_card_one);
                Glide
                        .with(MainActivity.this)
                        .load("@drawable/" + retrieveData.getPlayer2ImgIconName())
                        .into(main_IMG_card_two);
            }
        });

        //packCards = loadAllImagesOfCards();


        /*Deck deck = new Deck();
        Card card = deck.getCardFromDeck(0);
        Log.d("test", " "+ card.getCardSuit() + " " + card.getCardRank()
                + " " + card.getValue() + " " + card.getImgIconName());

        Hand hand1 = new Hand(deck);
        hand1.splitDeckToHand(0,26);
        hand1.getCardsInHand();*/

    }

    //TODO Create func that allow move to another activity

    //TODO Set score TextView

    //TODO Set card ImageView

}