package com.example.individualproject31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class ParentLogin extends AppCompatActivity {

    DatabaseHelper db;
    String email,password;
    EditText mEmail,mPassword;
    String currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);
        //this links the views to java
        mEmail=(EditText) findViewById(R.id.editText_email);
        mPassword=(EditText) findViewById(R.id.editText_password);
        db = new DatabaseHelper(this);
    }
    //This will activate the login
    public void login(View view) {
        //get the strings to be able to match and check against the database
        email = mEmail.getText().toString().trim();
        password = mPassword.getText().toString().trim();
        setCurrentUser(email);
        Boolean res = db.checkUser(email, password);
        //if true this will launch the game menu else it will show a toast
        if(res == true)
        {
            Intent HomePage = new Intent(ParentLogin.this, Menu.class);
            startActivity(HomePage);
        }
        else
        {
            Toast.makeText(ParentLogin.this,"Login Error",Toast.LENGTH_SHORT).show();
        }
    }
    //this wills start new activity to register
    public void intentRegister(View view) {
        startActivity(new Intent(ParentLogin.this,Registration.class));
        finish();
    }

    //these two methods were supposed to be for the barGraph, setting the current user and retrieve
    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

}