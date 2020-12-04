package com.example.individualproject31;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
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

public class Level2Game1 extends AppCompatActivity {
    private final int NUMBER_OF_MOVES = 7;
    private int moveTracker = 0;
    String moveCounter;
    String strMoves="Number of moves Left: ";
    TextView mCounter;
    ImageView mOwl;
    StartDragLstnr mStart;
    EndDragLstnr mEnd;
    Button mInput;
    private MediaPlayer mSong;
    private Sounds mError;
    Chronometer chronometer;
    Clock mClock;
    long timeScore;
    private String score;
    long resetCounter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_game1);
        mOwl = findViewById(R.id.lvl2Gm1);
        mInput = (Button) findViewById(R.id.arrowInput);
        mCounter=(TextView) findViewById(R.id.moveCounter);

        ButterKnife.bind(this);


        mStart = new StartDragLstnr();
        mEnd = new EndDragLstnr();
        findViewById(R.id.bottomArrow).setOnLongClickListener(mStart);
        findViewById(R.id.topArrow).setOnLongClickListener(mStart);
        findViewById(R.id.leftArrow).setOnLongClickListener(mStart);
        findViewById(R.id.rightArrow).setOnLongClickListener(mStart);

        findViewById(R.id.arrowInput).setOnDragListener(mEnd);

        mSong = MediaPlayer.create(Level2Game1.this, R.raw.arcade_puzzler);
        mSong.start();
        mError=new Sounds(Level2Game1.this);

        chronometer = findViewById(R.id.chronometer);
        mClock=new Clock(chronometer);


    }

    @Override
    protected void onPause() {
        super.onPause();
        mSong.stop();

    }

    public void resetMoves(View view) {
        moveTracker=0;
        resetCounter+=50;
        moveCounter=strMoves+(NUMBER_OF_MOVES-moveTracker);
        mCounter.setText(moveCounter);

    }

    public void goToMenu(View view) {
        startActivity(new Intent(Level2Game1.this,Menu.class));
    }

    public void muteVolume(View view) {
        if(mSong.isPlaying()){
            mSong.pause();
        }
        else {
            mSong.start();
        }


    }

    private class StartDragLstnr implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {

            WithDragShadow shadow = new WithDragShadow(v);
            ClipData data = ClipData.newPlainText("", "");

            v.startDrag(data, shadow, v, 0);
            return false;
        }
    }

    private class EndDragLstnr implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            if (event.getAction() == DragEvent.ACTION_DROP) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {


                    if (keyValidate(event)) {
                        moveCounter=strMoves+(NUMBER_OF_MOVES-moveTracker);
                        mCounter.setText(moveCounter);
                        if(moveTracker==NUMBER_OF_MOVES){
                            startAnim();
                        }
                    } else {
                        mError.playError();
                        return false;
                    }


                }
            }

            return true;
        }
    }

    private class WithDragShadow extends View.DragShadowBuilder {

        public WithDragShadow(View v) {
            super(v);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            super.onDrawShadow(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
            super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);
        }
    }
    private boolean keyValidate(DragEvent view) {

        switch(moveTracker) {
            case 6:
            case 4:
            case 2:
            case 0:

                if (((Button) view.getLocalState()).getId() == R.id.rightArrow) {
                    moveTracker++;
                }
                break;
            case 5:
                if (((Button) view.getLocalState()).getId() == R.id.bottomArrow) {
                    moveTracker++;
                }
                break;
            case 3:
            case 1:
                if (((Button) view.getLocalState()).getId() == R.id.topArrow) {
                    moveTracker++;
                }
                break;
            default:

                return false;
        }

        return true;
    }

    private void startAnim() {
        mClock.pauseClock(chronometer);
        setScore();


            ObjectAnimator move1 = ObjectAnimator.ofFloat(mOwl, "translationX", 260);
            move1.setDuration(800);

            ObjectAnimator move2 = ObjectAnimator.ofFloat(mOwl, "translationY", -100);
            move2.setDuration(800);

            ObjectAnimator move3 = ObjectAnimator.ofFloat(mOwl, "translationX", 380);
            move3.setDuration(800);
            ObjectAnimator move4 = ObjectAnimator.ofFloat(mOwl, "translationY", -230);
            move4.setDuration(800);
            ObjectAnimator move5 = ObjectAnimator.ofFloat(mOwl, "translationX", 850);
            move5.setDuration(800);
            ObjectAnimator move6 = ObjectAnimator.ofFloat(mOwl, "translationY", -20);
            move6.setDuration(800);
            ObjectAnimator move7 = ObjectAnimator.ofFloat(mOwl, "translationX", 1600);
            move7.setDuration(800);
            ObjectAnimator jumpUp = ObjectAnimator.ofFloat(mOwl, "translationY", -70);
            jumpUp.setDuration(200);
            ObjectAnimator jumpDown = ObjectAnimator.ofFloat(mOwl, "translationY", -20);
            jumpDown.setDuration(200);



            AnimatorSet animate=new AnimatorSet();
            animate.play(move1);
            animate.play(move2).after(move1);
            animate.play(move3).after(move2);
            animate.play(move4).after(move3);
            animate.play(move5).after(move4);
            animate.play(move6).after(move5);
            animate.play(move7).after(move6);
            animate.play(jumpUp).after(move7);
            animate.play(jumpDown).after(jumpUp);
            animate.start();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Level2Game1.this, CurrentLevelScore.class);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            }
        }, 6000);
    }


    public void setScore(){
        timeScore= 20000-mClock.getTimeScore(chronometer);
        int placeHolder=(int)(timeScore-resetCounter);
        if(placeHolder<0)
        {
            placeHolder=0;
        }
        score=""+placeHolder;

    }
}


