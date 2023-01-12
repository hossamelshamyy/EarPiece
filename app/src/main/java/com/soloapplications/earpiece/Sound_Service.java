package com.soloapplications.earpiece;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class Sound_Service extends Service {
    AudioManager audioManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (audioManager.getMode() == 3) {
            Toast.makeText(getApplicationContext(), "Unknown Error", Toast.LENGTH_LONG).show();
        } else {
            audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
            Log.e("Intent", String.valueOf(audioManager.getMode()));
            audioManager.setSpeakerphoneOn(false);
            Toast.makeText(getApplicationContext(), "EarPiece is On", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroy() {
        audioManager.setMode(AudioManager.MODE_NORMAL);
        audioManager.setSpeakerphoneOn(true);
        Toast.makeText(getApplicationContext(), "EarPiece is Off", Toast.LENGTH_LONG).show();
    }
}
