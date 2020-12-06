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
    KidRegistryManipulation newData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_registration);
        //instantiates the database classes
        db = new DatabaseHelper(this);
        newData=new KidRegistryManipulation(this);
        newData.open();
        mKidName=(EditText)findViewById(R.id.register_kid_name);
        mKidPassWord=(EditText)findViewById(R.id.register_kid_password);
        }
//will launch new activity after creating a new user for the kids table
    public void kidRegistration(View view) {
        kidName=mKidName.getText().toString();
        kidPassWord=mKidPassWord.getText().toString();
        newData.createRegistryKid(kidName,kidPassWord);
        newData.close();
        startActivity(new Intent(KidRegistration.this,ParentLogin.class));
    }
}
