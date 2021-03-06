package com.example.individualproject31;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class Clock {
    //constructor for the clock, this way every game can reference this class to have a clock
    public Clock(Chronometer chronometer){
        chronometer.setFormat("Time: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }
    public void pauseClock(Chronometer chronometer)
    {
        chronometer.stop();
    }
    //this method will return the time in milliseconds which will be the score given to the user
    public long getTimeScore(Chronometer chronometer){
        return  SystemClock.elapsedRealtime()-chronometer.getBase();
    }


}
