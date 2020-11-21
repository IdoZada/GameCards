package com.example.warcardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class WinnerActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_WINNER_NAME = "EXTRA_KEY_WINNER_NAME";
    public static final String EXTRA_KEY_WINNER_SCORE = "EXTRA_KEY_WINNER_SCORE";
    private Button winner_BTN_close;
    private TextView winner_LBL_playerName;
    private TextView winner_LBL_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        winner_BTN_close = findViewById(R.id.winner_BTN_close);
        winner_LBL_playerName = findViewById(R.id.winner_LBL_playerName);
        winner_LBL_score = findViewById(R.id.winner_LBL_score);

        winner_BTN_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        String nameOneFromPreviousActivity = getIntent().getStringExtra(EXTRA_KEY_WINNER_NAME);
        winner_LBL_playerName.setText(nameOneFromPreviousActivity);

        int scoreFromPreviousActivity = getIntent().getIntExtra(EXTRA_KEY_WINNER_SCORE,-1);
        winner_LBL_score.setText("" + scoreFromPreviousActivity);

    }

    /**
     * This function destroy the activity
     */
    private void closeActivity() { finish();
    }
}