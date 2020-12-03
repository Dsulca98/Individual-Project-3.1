package com.example.individualproject31;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Sounds {

    private SoundPool sound;
    private int error;

    public Sounds(Context context){
        sound= new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        error=sound.load(context, R.raw.error,1);
    }

    public void playError(){
        sound.play(error,1.0f,1.0f,1,0,1.0f);
    }
}
