package com.example.warcardgame.activities;

import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;


import com.example.warcardgame.R;
import com.example.warcardgame.objects.GpsTracker;
import com.example.warcardgame.objects.MoveActivity;
import com.example.warcardgame.objects.WinnerPlayer;

import com.example.warcardgame.utils.MySP;

import java.util.ArrayList;
import java.util.Date;


public class WinnerActivity extends Activity_Base {
    public static final String EXTRA_KEY_WINNER_NAME = "EXTRA_KEY_WINNER_NAME";
    public static final String EXTRA_KEY_WINNER_SCORE = "EXTRA_KEY_WINNER_SCORE";
    public static final double AFEKA_LONGITUDE = 34.817816499999935;
    public static final double AFEKA_LATITUDE = 32.1133371;
    private Button winner_BTN_close;
    private Button winner_BTN_topTen;
    private TextView winner_LBL_playerName;
    private TextView winner_LBL_score;
    private WinnerPlayer winnerPlayer;
    private double lat = 23.22222222;
    private double lon = 30.23135569;
    private String winnerName;
    private int winnerScore;
    private ArrayList<WinnerPlayer> list = new ArrayList<>();
    private Location userLocation;
    private GpsTracker gpsTracker;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        findViews();

        winner_BTN_topTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = moveBetweenActivity(WinnerActivity.this, TopTenActivity.class, null, MoveActivity.TOPTEN);
                startActivity(intent);
                closeActivity(WinnerActivity.this);
            }
        });

        winner_BTN_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity(WinnerActivity.this);
            }
        });
        winnerName = getIntent().getStringExtra(EXTRA_KEY_WINNER_NAME);
        winner_LBL_playerName.setText(winnerName);
        winnerScore = getIntent().getIntExtra(EXTRA_KEY_WINNER_SCORE, -1);
        winner_LBL_score.setText("" + winnerScore);

        createNewRecord();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void findViews() {
        winner_BTN_close = findViewById(R.id.winner_BTN_close);
        winner_LBL_playerName = findViewById(R.id.winner_LBL_playerName);
        winner_LBL_score = findViewById(R.id.winner_LBL_score);
        winner_BTN_topTen = findViewById(R.id.winner_BTN_topTen);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createNewRecord() {
        winnerPlayer = new WinnerPlayer();
        winnerPlayer.setPlayerName(winnerName);
        winnerPlayer.setScore(winnerScore);
        winnerPlayer.setDate(DateFormat.format("dd-MM-yyyy HH:mm:ss a", new Date()).toString());
        getLocation();

        MySP prefs = new MySP(getApplicationContext());
        prefs.add(winnerPlayer, (u, v) -> {
            if (u.getScore() == v.getScore())
                return 0;
            return u.getScore() < v.getScore() ? 1 : -1;
        });
    }

    public void getLocation(){
        gpsTracker = new GpsTracker(WinnerActivity.this);
        if(gpsTracker.canGetLocation()) {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            winnerPlayer.setLongitude(longitude);
            winnerPlayer.setLatitude(latitude);
        }
    }

}