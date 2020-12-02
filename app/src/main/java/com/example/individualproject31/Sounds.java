package com.example.individualproject31;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Sounds {

    private SoundPool sound;
    private int arcade;

    public Sounds(Context context){
        sound= new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        arcade=sound.load(context, R.raw.arcade_puzzler,1);
    }

    public void playArcade(){
        sound.play(arcade,1.0f,1.0f,1,0,1.0f);
    }
}
