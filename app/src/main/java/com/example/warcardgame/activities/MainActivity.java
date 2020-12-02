package com.example.warcardgame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.warcardgame.R;
import com.example.warcardgame.objects.MoveActivity;

public class MainActivity extends Activity_Base {
    private ImageView main_IMG_deck_background;
    private Button main_BTN_start_game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_BTN_start_game = findViewById(R.id.main_BTN_start_game);
        main_IMG_deck_background = findViewById(R.id.main_IMG_deck_background);
        glide(this,"img_deck_table",main_IMG_deck_background);

        main_BTN_start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = moveBetweenActivity(MainActivity.this,GameActivity.class,null, MoveActivity.GAME);
                startActivity(intent);
            }
        });
    }
}