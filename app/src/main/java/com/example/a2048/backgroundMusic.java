package com.example.a2048;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class backgroundMusic extends Service {
    private MediaPlayer background;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        background = MediaPlayer.create(this, R.raw.background_music);
        background.setLooping(true);
        background.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        background.release();
    }
}
