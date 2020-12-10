package com.example.warcardgame.activities;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.warcardgame.R;
import com.example.warcardgame.objects.MoveActivity;
import com.example.warcardgame.objects.Player;
import com.example.warcardgame.objects.RetrieveData;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.Date;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
public class MainActivity extends Activity_Base  {
    private ImageView main_IMG_deck_background;
    private Button main_BTN_start_game;
    private Button game_BTN_exit;
    private Button game_BTN_top_ten;
    private EditText main_EDT_playerName1;
    private EditText main_EDT_playerName2;
    //private Location userLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("adad", "onCreate: ");
        setContentView(R.layout.activity_main);
        main_BTN_start_game = findViewById(R.id.main_BTN_start_game);
        game_BTN_exit = findViewById(R.id.game_BTN_exit);
        main_IMG_deck_background = findViewById(R.id.main_IMG_deck_background);
        game_BTN_top_ten = findViewById(R.id.game_BTN_top_ten);
        main_EDT_playerName1 = findViewById(R.id.main_EDT_playerName1);
        main_EDT_playerName2 = findViewById(R.id.main_EDT_playerName2);

        requestPermission();

        glide(this,"img_deck_table",main_IMG_deck_background);

        main_BTN_start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(main_EDT_playerName1.getText().toString().trim().length() > 0 && main_EDT_playerName2.getText().toString().trim().length() > 0){
                    RetrieveData retrieveData = new RetrieveData();
                    retrieveData.setPlayer1(new Player(main_EDT_playerName1.getText().toString(),null));
                    retrieveData.setPlayer2(new Player(main_EDT_playerName2.getText().toString(),null));
                    Intent intent = moveBetweenActivity(MainActivity.this,GameActivity.class,retrieveData, MoveActivity.GAME);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, R.string.enter_name, Toast.LENGTH_SHORT).show();
                }
            }
        });

        game_BTN_top_ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = moveBetweenActivity(MainActivity.this,TopTenActivity.class,null, MoveActivity.TOPTEN);
                startActivity(intent);
            }
        });

        game_BTN_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity(MainActivity.this);
            }
        });
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("adad", "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("adad", "onStart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("adad", "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("adad", "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("adad", "onDestroy: ");
    }
}