package com.example.individualproject31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;





/////////////////////////////////////////////////////////////////////////

public class CurrentLevelScore extends AppCompatActivity {
    private MediaPlayer mSong;
    TextView mScore;
    String currentScore;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_level_score);
        mSong= MediaPlayer.create(this, R.raw.level_completed);
        mSong.start();



        mScore=(TextView)findViewById(R.id.scoreView);


        intent = getIntent();
        currentScore = intent.getStringExtra("score");

        mScore.setText(currentScore);

    }



    public void returnToMenu(View view) {
        startActivity(new Intent(CurrentLevelScore.this,Menu.class));
        finish();
    }
}