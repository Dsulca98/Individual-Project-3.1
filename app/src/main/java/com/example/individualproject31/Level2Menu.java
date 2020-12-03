package com.example.individualproject31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Level2Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_menu);
    }

    public void launchGame1(View view) {
        startActivity(new Intent(Level2Menu.this,Level2Game1.class));
        finish();
    }

    public void launchGame2(View view) {
        startActivity(new Intent(Level2Menu.this,Level2Game2.class));
        finish();
    }

    public void launchGame3(View view) {
        startActivity(new Intent(Level2Menu.this,Level2Game3.class));
        finish();
    }
}