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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);

        mEmail=(EditText) findViewById(R.id.editText_email);
        mPassword=(EditText) findViewById(R.id.editText_password);
        db = new DatabaseHelper(this);
    }
    public void login(View view) {
        email = mEmail.getText().toString().trim();
        password = mPassword.getText().toString().trim();
        Boolean res = db.checkUser(email, password);

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

    public void intentRegister(View view) {
        startActivity(new Intent(ParentLogin.this,Registration.class));
    }
}