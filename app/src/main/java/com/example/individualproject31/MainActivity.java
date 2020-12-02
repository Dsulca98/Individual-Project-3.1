package com.example.individualproject31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //allows the SplashScreen to run first
//postDelayed handles the time in milliseconds how long is the SplashScreen is on
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//the intent is used to start the activity and finish() destroys the Splashscreen Activity
                Intent activityMain =new Intent(MainActivity.this,ParentLogin.class);
                startActivity(activityMain);
                finish();
            }
        },2000);
    }
}