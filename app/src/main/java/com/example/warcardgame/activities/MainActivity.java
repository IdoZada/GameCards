package com.example.warcardgame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.warcardgame.GameManager;
import com.example.warcardgame.R;
import com.example.warcardgame.objects.MoveActivity;
import com.example.warcardgame.objects.RetrieveData;

public class MainActivity extends Activity_Base {

    private GameManager game = new GameManager();
    private RetrieveData retrieveData;
    private TextView main_LBL_score_one;
    private ImageView main_IMG_card_one;
    private TextView main_LBL_score_two;
    private ImageView main_IMG_card_two;
    private ImageView main_IMG_play_button;
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
                displayManagerActivity();
            }
        });
    }
    private void displayManagerActivity(){
        retrieveData = game.gameStep();
        if(retrieveData.getWinner() == null){
            main_LBL_score_one.setText("" + retrieveData.getPlayer1Score());
            main_LBL_score_two.setText("" + retrieveData.getPlayer2Score());

            //TODO Fix blinking image glide
            glide(MainActivity.this, retrieveData.getPlayer1ImgIconName(),main_IMG_card_one);
            glide(MainActivity.this, retrieveData.getPlayer2ImgIconName(),main_IMG_card_two);

        }else{
            Intent intent;
            if(retrieveData.getWinner().getMoveActivity() == MoveActivity.WINNER) {
                intent = moveBetweenActivity(MainActivity.this, WinnerActivity.class, retrieveData, MoveActivity.WINNER);
            }else{
                intent = moveBetweenActivity(MainActivity.this, DrawActivity.class, retrieveData, MoveActivity.DRAW);
            }
            startActivity(intent);
            closeActivity(MainActivity.this);
        }
    }
}