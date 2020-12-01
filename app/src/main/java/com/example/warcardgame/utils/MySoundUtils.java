package com.example.warcardgame.utils;

import android.content.Context;
import android.media.MediaPlayer;

public class MySoundUtils {
    private MediaPlayer mp = new MediaPlayer();
    private int rawId;

    public MySoundUtils() {
    }

    public MySoundUtils(int rawId) {
        this.rawId = rawId;
    }

    public void playSound(Context context) {
        mp = MediaPlayer.create(context, rawId);
        mp.setOnCompletionListener(mp -> {
            mp.reset();
            mp.release();
            mp=null;
        });
        mp.start();
    }
}
