package com.example.individualproject31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class KidRegistration extends AppCompatActivity {
    DatabaseHelper db;
    EditText mKidName,mKidPassWord;
    String kidName,kidPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_registration);
        db = new DatabaseHelper(this);
        mKidName=(EditText)findViewById(R.id.register_kid_name);
        mKidPassWord=(EditText)findViewById(R.id.register_kid_password);
    }

    public void kidRegistration(View view) {

        long val= db.addUser(kidName,kidPassWord);
        startActivity(new Intent(KidRegistration.this,ParentLogin.class));
    }
}
