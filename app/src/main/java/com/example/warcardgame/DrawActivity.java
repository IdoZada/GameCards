package com.example.warcardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class DrawActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_DRAW_SCORE = "EXTRA_KEY_DRAW_SCORE";
    private Button draw_BTN_close;
    private TextView draw_LBL_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_draw);

        draw_BTN_close = findViewById(R.id.draw_BTN_close);
        draw_LBL_score = findViewById(R.id.draw_LBL_score);

        draw_BTN_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        int scoreFromPreviousActivity = getIntent().getIntExtra(EXTRA_KEY_DRAW_SCORE,-1);
        draw_LBL_score.setText("" + scoreFromPreviousActivity);

    }
    /**
     * This function destroy the activity
     */
    private void closeActivity() { finish();
    }
}