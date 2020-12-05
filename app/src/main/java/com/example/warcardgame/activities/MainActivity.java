package com.example.warcardgame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.warcardgame.R;
import com.example.warcardgame.objects.MoveActivity;

public class MainActivity extends Activity_Base {
    private ImageView main_IMG_deck_background;
    private Button main_BTN_start_game;
    private Button game_BTN_exit;
    private Button game_BTN_top_ten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("adad", "onCreate: ");
        setContentView(R.layout.activity_main);
        main_BTN_start_game = findViewById(R.id.main_BTN_start_game);
        game_BTN_exit = findViewById(R.id.game_BTN_exit);
        main_IMG_deck_background = findViewById(R.id.main_IMG_deck_background);
        game_BTN_top_ten = findViewById(R.id.game_BTN_top_ten);

        glide(this,"img_deck_table",main_IMG_deck_background);

        main_BTN_start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = moveBetweenActivity(MainActivity.this,GameActivity.class,null, MoveActivity.GAME);
                startActivity(intent);
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