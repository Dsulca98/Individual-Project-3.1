package com.example.individualproject31;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
///////////////////////////////////////////////////////
public class Level1Game1 extends AppCompatActivity {
    private final int NUMBER_OF_MOVES=1;
    private int moveTracker=0;
    String strMoves="Number of moves Left: ";
    TextView mCounter;
    String moveCounter;
    ImageView mOwl;
    StartDragLstnr mStart;
    EndDragLstnr mEnd;
    Button mInput;
    private MediaPlayer mSong;
    private Sounds mError;
/////////////
    Chronometer chronometer;
    Clock mClock;
    long timeScore;
    private String score;
    long resetCounter=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_game1);
        mOwl = findViewById(R.id.lvl1Gm1);

        mInput= (Button)findViewById(R.id.arrowInput);
        mCounter=(TextView) findViewById(R.id.moveCounter);

        ButterKnife.bind(this);


        mStart= new StartDragLstnr();
        mEnd=new EndDragLstnr();
        findViewById(R.id.bottomArrow).setOnLongClickListener(mStart);
        findViewById(R.id.topArrow).setOnLongClickListener(mStart);
        findViewById(R.id.leftArrow).setOnLongClickListener(mStart);
        findViewById(R.id.rightArrow).setOnLongClickListener(mStart);

        findViewById(R.id.arrowInput).setOnDragListener(mEnd);

        mSong= MediaPlayer.create(Level1Game1.this, R.raw.arcade_puzzler);
        mSong.start();
        mError= new Sounds(this);
        //insert in the desired method to cue the error sound
        //mError.playError();



//////////////////////////
        chronometer = findViewById(R.id.chronometer);
        mClock=new Clock(chronometer);


    }

    @Override
    protected void onPause() {
        super.onPause();
        mSong.stop();
    }

    private boolean keyValidate(DragEvent view){
        if (moveTracker == 0) {
            if (((Button) view.getLocalState()).getId() == R.id.rightArrow) {
                moveTracker++;
            }
        } else {
            return false;
        }

        return true;
    }





    public void resetMoves(View view) {
        moveTracker=0;
        //////////////////////////
        resetCounter+=50;
        moveCounter=strMoves+(NUMBER_OF_MOVES-moveTracker);
        mCounter.setText(moveCounter);
    }

    public void goToMenu(View view) {
        startActivity(new Intent(Level1Game1.this,Menu.class));
    }

    public void muteVolume(View view) {
        if(mSong.isPlaying()){
            mSong.pause();
        }
        else {
            mSong.start();
        }
    }


    private class StartDragLstnr implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View v) {

            WithDragShadow shadow= new WithDragShadow(v);
            ClipData data= ClipData.newPlainText("","");;
            v.startDrag(data,shadow,v,0);
            return false;
        }
    }

    private class EndDragLstnr implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            if (event.getAction() == DragEvent.ACTION_DROP) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                    if (keyValidate(event)) {
                        moveCounter =strMoves + (NUMBER_OF_MOVES - moveTracker);
                        mCounter.setText(moveCounter);
                        if (moveTracker == NUMBER_OF_MOVES) {
                            startAnim();
                        } else {
                            mError.playError();
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }
    private class WithDragShadow extends View.DragShadowBuilder{

        public WithDragShadow(View v){ super(v);  }

        @Override public void onDrawShadow(Canvas canvas) { super.onDrawShadow(canvas);}

        @Override
        public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
            super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);
        }
    }


    private void startAnim()
    {



        mClock.pauseClock(chronometer);
        setScore();




        if(moveTracker==NUMBER_OF_MOVES){
            ObjectAnimator moveOwlRight = ObjectAnimator.ofFloat(mOwl, "translationX", 1600);
            moveOwlRight.setDuration(3000);
            ObjectAnimator jumpUp = ObjectAnimator.ofFloat(mOwl, "translationY", -50);
            jumpUp.setDuration(200);
            ObjectAnimator jumpDown = ObjectAnimator.ofFloat(mOwl, "translationY", 0);
            jumpDown.setDuration(200);

            AnimatorSet animate=new AnimatorSet();
            animate.play(moveOwlRight);
            animate.play(jumpUp).after(moveOwlRight);
            animate.play(jumpDown).after(jumpUp);
            animate.start();

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {





                Intent intent=new Intent(Level1Game1.this,CurrentLevelScore.class);
                intent.putExtra("score", score);
                startActivity(intent);





                finish();
            }
        },3500);
    }



    public void setScore(){
        timeScore= 10000-mClock.getTimeScore(chronometer);
        int placeHolder=(int)(timeScore-resetCounter);
        if(placeHolder<0)
        {
            placeHolder=0;
        }
        score=""+placeHolder;

    }


}