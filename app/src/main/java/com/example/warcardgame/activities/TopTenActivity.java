package com.example.warcardgame.activities;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.warcardgame.R;
import com.example.warcardgame.fragments.Fragment_list;

public class TopTenActivity extends Activity_Base {
    private FrameLayout topTen_LAY_list;
    private FrameLayout topTen_LAY_map;
    private Button topTen_BTN_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_ten);
        findViews();

        topTen_BTN_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity(TopTenActivity.this);
            }
        });

        Fragment_list fragment_list = new Fragment_list();
        getSupportFragmentManager().beginTransaction().add(R.id.topTen_LAY_list,fragment_list).commit();


    }

    private void findViews(){
        topTen_LAY_list = findViewById(R.id.topTen_LAY_list);
        topTen_LAY_map = findViewById(R.id.topTen_LAY_map);
        topTen_BTN_close = findViewById(R.id.topTen_BTN_close);

    }
}