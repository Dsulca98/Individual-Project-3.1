package com.example.individualproject31;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ClipData;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;

import android.media.MediaPlayer;
import android.os.Build;

import android.os.Handler;
import android.view.DragEvent;
import android.view.View;

import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;

public class Level1Game3 extends AppCompatActivity {
    private final int NUMBER_OF_MOVES = 9;
    private int moveTracker;
    private String moveCounter;
    private String strMoves="Number of moves Left: ";
    private ImageView mOwl;
    private StartDragLstnr mStart;
    private EndDragLstnr mEnd;
    private Button mInput;
    private MediaPlayer mSong;
    private TextView mCounter;
    private Sounds mError;

    Chronometer chronometer;
    Clock mClock;
    long timeScore;
    private String score;
    long resetCounter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_game3);
        mOwl = findViewById(R.id.lvl1Gm2);
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

        mSong = MediaPlayer.create(Level1Game3.this, R.raw.arcade_puzzler);
        mSong.start();
        mError=new Sounds(Level1Game3.this);

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
        startActivity(new Intent(Level1Game3.this,Menu.class));
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
            ;
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
            case 8:
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
            case 7:
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
        if (moveTracker == NUMBER_OF_MOVES) {

            ObjectAnimator move1 = ObjectAnimator.ofFloat(mOwl, "translationX", 220);
            move1.setDuration(600);

            ObjectAnimator move2 = ObjectAnimator.ofFloat(mOwl, "translationY", -110);
            move2.setDuration(600);

            ObjectAnimator move3 = ObjectAnimator.ofFloat(mOwl, "translationX", 490);
            move3.setDuration(600);

            ObjectAnimator move4 = ObjectAnimator.ofFloat(mOwl, "translationY", -250);
            move4.setDuration(600);
            ObjectAnimator move5 = ObjectAnimator.ofFloat(mOwl, "translationX", 755);
            move5.setDuration(600);
            ObjectAnimator move6 = ObjectAnimator.ofFloat(mOwl, "translationY", 280);
            move6.setDuration(600);
            ObjectAnimator move7 = ObjectAnimator.ofFloat(mOwl, "translationX", 1020);
            move7.setDuration(600);
            ObjectAnimator move8 = ObjectAnimator.ofFloat(mOwl, "translationY", 160);
            move8.setDuration(600);
            ObjectAnimator move9 = ObjectAnimator.ofFloat(mOwl, "translationX", 1615);
            move9.setDuration(600);
            ObjectAnimator jumpUp = ObjectAnimator.ofFloat(mOwl, "translationY", 110);
            jumpUp.setDuration(200);
            ObjectAnimator jumpDown = ObjectAnimator.ofFloat(mOwl, "translationY", 160);
            jumpDown.setDuration(200);

            AnimatorSet animate=new AnimatorSet();
            animate.play(move1);
            animate.play(move2).after(move1);
            animate.play(move3).after(move2);
            animate.play(move4).after(move3);
            animate.play(move5).after(move4);
            animate.play(move6).after(move5);
            animate.play(move7).after(move6);
            animate.play(move8).after(move7);
            animate.play(move9).after(move8);
            animate.play(jumpUp).after(move9);
            animate.play(jumpDown).after(jumpUp);
            animate.start();

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Level1Game3.this, CurrentLevelScore.class);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            }
        }, 6000);
    }


    public void setScore(){
        timeScore= 30000-mClock.getTimeScore(chronometer);
        int placeHolder=(int)(timeScore-resetCounter);
        if(placeHolder<0)
        {
            placeHolder=0;
        }
        score=""+placeHolder;

    }
}