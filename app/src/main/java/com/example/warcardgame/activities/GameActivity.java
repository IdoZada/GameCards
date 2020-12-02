package com.example.warcardgame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.warcardgame.GameManager;
import com.example.warcardgame.R;
import com.example.warcardgame.objects.MoveActivity;
import com.example.warcardgame.objects.RetrieveData;
import com.example.warcardgame.utils.MySoundUtils;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends Activity_Base {

    private static final long DELAY = 1000;
    private GameManager game = new GameManager();
    private RetrieveData retrieveData;
    private TextView game_LBL_score_one;
    private ImageView game_IMG_card_one;
    private TextView game_LBL_score_two;
    private ImageView game_IMG_card_two;
    private ImageView game_IMG_play_button;
    private TextView game_LBL_round;
    private ProgressBar game_PRB_progressBar;
    private Timer carousalTimer;
    private boolean flag = false;
    private MySoundUtils mySound = new MySoundUtils(R.raw.snd_card_flip);
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        findView();

        glide(GameActivity.this, "img_deck", game_IMG_card_one);
        glide(GameActivity.this, "img_deck", game_IMG_card_two);

        game_IMG_play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
                flag = true;
                game_IMG_play_button.setClickable(false);
                glide(GameActivity.this, "img_timer", game_IMG_play_button);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(flag){
            startGame();
        }
    }

    @Override
    protected void onStop() {

        super.onStop();
        if(flag){
            stopGame();
        }

    }


    private void startGame() {
        carousalTimer = new Timer();
        carousalTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        displayManagerActivity();

                    }
                });
            }
        }, 0L, DELAY);
    }

    private void stopGame() {
        carousalTimer.cancel();
    }

    private void displayManagerActivity(){
        retrieveData = game.gameStep();
        if(retrieveData.getWinner() == null){
            mySound.playSound(this);
            progressBar();
            game_LBL_score_one.setText("" + retrieveData.getPlayer1Score());
            game_LBL_score_two.setText("" + retrieveData.getPlayer2Score());

            //TODO Fix blinking image glide
            glide(GameActivity.this, retrieveData.getPlayer1ImgIconName(), game_IMG_card_one);
            glide(GameActivity.this, retrieveData.getPlayer2ImgIconName(), game_IMG_card_two);

        }else{
            Intent intent;
            if(retrieveData.getWinner().getMoveActivity() == MoveActivity.WINNER) {
                intent = moveBetweenActivity(GameActivity.this, WinnerActivity.class, retrieveData, MoveActivity.WINNER);
            }else{
                intent = moveBetweenActivity(GameActivity.this, DrawActivity.class, retrieveData, MoveActivity.DRAW);
            }
            onStop();
            startActivity(intent);
            closeActivity(GameActivity.this);
        }
    }

    private void findView(){
        game_LBL_score_one = findViewById(R.id.game_LBL_score_one);
        game_IMG_card_one = findViewById(R.id.game_IMG_card_one);
        game_LBL_score_two = findViewById(R.id.game_LBL_score_two);
        game_IMG_card_two = findViewById(R.id.game_IMG_card_two);
        game_IMG_play_button = findViewById(R.id.game_IMG_play_button);
        game_PRB_progressBar = findViewById(R.id.game_PRB_progressBar);
        game_LBL_round = findViewById(R.id.game_LBL_round);

    }

//    private void progressBar(){
//        new Thread(new Runnable() {
//            public void run() {
//                progressStatus += 1;
//                // Update the progress bar and round text
//                handler.post(new Runnable() {
//                    public void run() {
//                        main_PRB_progressBar.setProgress(progressStatus);
//                        main_LBL_round.setText("Round " + progressStatus);
//                    }
//                });
//            }
//        }).start();
//    }

    private void progressBar(){
        progressStatus += 1;
        // Update the progress bar and round text
        game_PRB_progressBar.setProgress(progressStatus);
        game_LBL_round.setText("Round " + progressStatus);
    }
}