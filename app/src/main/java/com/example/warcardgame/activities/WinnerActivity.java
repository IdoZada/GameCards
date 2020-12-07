package com.example.warcardgame.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;


import com.example.warcardgame.R;
import com.example.warcardgame.objects.MoveActivity;
import com.example.warcardgame.objects.WinnerPlayer;

import com.example.warcardgame.utils.MySP;
import com.example.warcardgame.utils.MySharedPreference;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class WinnerActivity extends Activity_Base {
    public static final String EXTRA_KEY_WINNER_NAME = "EXTRA_KEY_WINNER_NAME";
    public static final String EXTRA_KEY_WINNER_SCORE = "EXTRA_KEY_WINNER_SCORE";
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

        if (this != null) {
            MySP shared = new MySP(this);
            list = shared.readDataFromStorage();
        }

        for (int i = 0; i < list.size(); i++) {
            Log.d("list", "player name: " + list.get(i).getPlayerName() + "score: " + list.get(i).getScore() + "lat: " + list.get(i).getLatitude() + "long: " + list.get(i).getLongitude() + "date:" + list.get(i).getDate() + "\n");
        }



    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void findViews(){
        winner_BTN_close = findViewById(R.id.winner_BTN_close);
        winner_LBL_playerName = findViewById(R.id.winner_LBL_playerName);
        winner_LBL_score = findViewById(R.id.winner_LBL_score);
        winner_BTN_topTen = findViewById(R.id.winner_BTN_topTen);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createNewRecord(){
        winnerPlayer = new WinnerPlayer();
        winnerPlayer.setPlayerName(winnerName);
        winnerPlayer.setScore(winnerScore);
        winnerPlayer.setLatitude(lat);
        winnerPlayer.setLongitude(lon);
        winnerPlayer.setDate(DateFormat.format("dd-MM-yyyy HH:mm:ss a", new Date()).toString());

        MySP prefs = new MySP(getApplicationContext());
        prefs.add(winnerPlayer, (u,v)-> {
            if (u.getScore() == v.getScore())
                return 0;
            return u.getScore() < v.getScore() ? 1 : -1;
        });
    }

//    private void accessClientLocation() {
//        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
//        ActivityCompat.checkSelfPermission(WinnerActivity.this, ACCESS_FINE_LOCATION);
//        client.getLastLocation().addOnSuccessListener(WinnerActivity.this, location -> {
//            if (location != null)
//                Log.d("yyyy", "accessClientLocation: hi ");
//                userLocation = location;
//                Log.d("yyyy", "accessClientLocation: hey");
//        });
//    }

}