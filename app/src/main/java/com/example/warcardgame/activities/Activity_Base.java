package com.example.warcardgame.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.warcardgame.objects.MoveActivity;
import com.example.warcardgame.objects.RetrieveData;
import com.example.warcardgame.utils.MyScreenUtils;

public class Activity_Base extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyScreenUtils.hideSystemUI(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            MyScreenUtils.hideSystemUI(this);
        }
    }

    protected boolean isDoubleBackPressToClose = false;
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    @Override
    public void onBackPressed() {
        if (isDoubleBackPressToClose) {
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                super.onBackPressed();
                return;
            }
            else {
                Toast.makeText(this, "Tap back button again to exit", Toast.LENGTH_SHORT).show();
            }

            mBackPressed = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * This function destroy the activity
     * @param activity receive the activity to close
     */
    public static void closeActivity(AppCompatActivity activity) {
        activity.finish();
    }

    //TODO Create func that allow move to another activity
    public static Intent moveBetweenActivity(Context context, Class newActivity , RetrieveData retrieveData, MoveActivity moveActivity){
        Intent intent = new Intent(context, newActivity);
        switch (moveActivity){
            case WINNER:
                Log.d("aaaaaaaaaaaa", "moveBetweenActivity: " + retrieveData.getWinnerPlayer().getPlayerName());
                intent.putExtra(WinnerActivity.EXTRA_KEY_WINNER_NAME, retrieveData.getWinnerPlayer().getPlayerName());
                intent.putExtra(WinnerActivity.EXTRA_KEY_WINNER_SCORE, retrieveData.getWinnerPlayer().getScore());
                break;
            case DRAW:
                intent.putExtra(DrawActivity.EXTRA_KEY_DRAW_SCORE, retrieveData.getWinnerPlayer().getScore());
                break;
            case GAME:
                intent.putExtra(GameActivity.EXTRA_KEY_GAME_PLAYER_NAME_ONE, retrieveData.getPlayer1().getName());
                intent.putExtra(GameActivity.EXTRA_KEY_GAME_PLAYER_NAME_TWO, retrieveData.getPlayer2().getName());
            default:
                break;
        }
        return intent;
    }

    public static void glide(Context activity, String imageName, ImageView view){
        Glide
                .with(activity)
                .load(getResourceId(imageName,activity))
                .into(view);
    }

    public static int getResourceId(String imageName,Context activity){
        return activity.getResources().getIdentifier(imageName,"drawable",activity.getPackageName());
    }
}