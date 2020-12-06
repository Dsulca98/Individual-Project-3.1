package com.example.individualproject31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



public class CurrentLevelScore extends AppCompatActivity {
    private MediaPlayer mSong;
    TextView mScore;
    String currentScore;
    int scores;
    Intent intent;
    int retrievedScore;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String INT = "int";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_level_score);
        //this will start new level song for the score actvity
        mSong= MediaPlayer.create(this, R.raw.level_completed);
        mSong.start();
        //links up the views
        mScore=(TextView)findViewById(R.id.scoreView);
        //this receives a string that contains the score the user scored from the previous game
        intent = getIntent();
        currentScore = intent.getStringExtra("score");
        mScore.setText(currentScore);
        //converts the current score into an int to be used in the barGraph,
        // but after many attempts it did not work
        scores = Integer.parseInt(currentScore);
    }
    //these are the methods for shared preferences
    public void saveScore ()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(INT, scores);
        editor.apply();

    }
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        retrievedScore = sharedPreferences.getInt(INT,0);
    }
    public int getScore(){
        loadData();
        return retrievedScore;
    }
    //this will return to the menu
    public void returnToMenu(View view) {
        startActivity(new Intent(CurrentLevelScore.this,Menu.class));
        finish();
    }
}