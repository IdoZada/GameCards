package com.example.warcardgame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.warcardgame.R;
import com.example.warcardgame.objects.MoveActivity;
import com.example.warcardgame.utils.MyScreenUtils;


public class WinnerActivity extends Activity_Base {

    public static final String EXTRA_KEY_WINNER_NAME = "EXTRA_KEY_WINNER_NAME";
    public static final String EXTRA_KEY_WINNER_SCORE = "EXTRA_KEY_WINNER_SCORE";
    private Button winner_BTN_close;
    private Button  winner_BTN_topTen;
    private TextView winner_LBL_playerName;
    private TextView winner_LBL_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        findViews();

        winner_BTN_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity(WinnerActivity.this);
            }
        });

        winner_BTN_topTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = moveBetweenActivity(WinnerActivity.this,TopTenActivity.class, null,MoveActivity.TOPTEN);
                startActivity(intent);
                closeActivity(WinnerActivity.this);
            }
        });

        String nameOneFromPreviousActivity = getIntent().getStringExtra(EXTRA_KEY_WINNER_NAME);
        winner_LBL_playerName.setText(nameOneFromPreviousActivity);

        int scoreFromPreviousActivity = getIntent().getIntExtra(EXTRA_KEY_WINNER_SCORE,-1);
        winner_LBL_score.setText("" + scoreFromPreviousActivity);

    }

    private void findViews(){
        winner_BTN_close = findViewById(R.id.winner_BTN_close);
        winner_LBL_playerName = findViewById(R.id.winner_LBL_playerName);
        winner_LBL_score = findViewById(R.id.winner_LBL_score);
        winner_BTN_topTen = findViewById(R.id.winner_BTN_topTen);
    }

}