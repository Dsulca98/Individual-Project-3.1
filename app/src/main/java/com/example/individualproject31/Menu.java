package com.example.individualproject31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

//buttons will open new activities, the specific level menus
    public void launchMenu1(View view) {
        startActivity(new Intent(Menu.this,Level1Menu.class));
        finish();
    }

    public void launchMenu2(View view) {
        startActivity(new Intent(Menu.this,Level2Menu.class));
        finish();
    }


}
