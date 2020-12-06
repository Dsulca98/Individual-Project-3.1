package com.example.individualproject31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Level1Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_menu);
    }
//menu for level 1, will launch games
    public void launchGame1(View view) {
        startActivity(new Intent(Level1Menu.this,Level1Game1.class));
        finish();
    }

    public void launchGame2(View view) {
        startActivity(new Intent(Level1Menu.this,Level1Game2.class));
        finish();
    }

    public void launchGame3(View view) {
        startActivity(new Intent(Level1Menu.this,Level1Game3.class));
        finish();
    }
}