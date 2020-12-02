package com.example.individualproject31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.ButterKnife;

public class Level1Game1 extends AppCompatActivity {
    StartDragLstnr mStart;
    EndDragLstnr mEnd;

    Button mInput;
    private MediaPlayer mSong;

    private Sounds mError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_game1);
        ImageView mOwl = findViewById(R.id.lvl1Gm1);
        Animation owlAnim = AnimationUtils.loadAnimation(Level1Game1.this, R.anim.level1_game1);
        mOwl.startAnimation(owlAnim);

        mInput= (Button)findViewById(R.id.arrowInput);

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

    }
    private void key(){


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

    private class EndDragLstnr implements View.OnDragListener{
        @Override
        public boolean onDrag(View v, DragEvent event) {

            if(event.getAction()==DragEvent.ACTION_DROP){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    v.setBackground(((Button)event.getLocalState()).getBackground());
                }
            }
            mError.playArcade();
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
}