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

public class MainActivity extends Activity_Base {

    private static final long DELAY = 1000;
    private GameManager game = new GameManager();
    private RetrieveData retrieveData;
    private TextView main_LBL_score_one;
    private ImageView main_IMG_card_one;
    private TextView main_LBL_score_two;
    private ImageView main_IMG_card_two;
    private ImageView main_IMG_play_button;
    private TextView main_LBL_round;
    private ProgressBar main_PRB_progressBar;
    private Timer carousalTimer;
    private boolean flag = false;
    private MySoundUtils mySound = new MySoundUtils(R.raw.snd_card_flip);
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();

        glide(MainActivity.this, "img_deck",main_IMG_card_one);
        glide(MainActivity.this, "img_deck",main_IMG_card_two);

        main_IMG_play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
                flag = true;
                main_IMG_play_button.setClickable(false);
                glide(MainActivity.this, "img_timer",main_IMG_play_button);
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
            onStop();
            startActivity(intent);
            closeActivity(MainActivity.this);
        }
    }

    private void findView(){
        main_LBL_score_one = findViewById(R.id.main_LBL_score_one);
        main_IMG_card_one = findViewById(R.id.main_IMG_card_one);
        main_LBL_score_two = findViewById(R.id.main_LBL_score_two);
        main_IMG_card_two = findViewById(R.id.main_IMG_card_two);
        main_IMG_play_button = findViewById(R.id.main_IMG_play_button);
        main_PRB_progressBar = findViewById(R.id.main_PRB_progressBar);
        main_LBL_round = findViewById(R.id.main_LBL_round);

    }

    private void progressBar(){
        new Thread(new Runnable() {
            public void run() {
                progressStatus += 1;
                // Update the progress bar
                handler.post(new Runnable() {
                    public void run() {
                        main_PRB_progressBar.setProgress(progressStatus);
                        main_LBL_round.setText("Round " + progressStatus);
                    }
                });
            }
        }).start();
    }
}