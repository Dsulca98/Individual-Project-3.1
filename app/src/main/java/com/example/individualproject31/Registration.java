package com.example.individualproject31;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;


public class Registration extends AppCompatActivity {

    DatabaseManipulation newData;
    DatabaseHelper db;
    String newName,newEmail,newPassWord,confirmPassword,message;
    EditText mName,mEmail,mPassword,mConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //opens up and instantiates the database class
        db = new DatabaseHelper(this);
        newData = new DatabaseManipulation(this);
        newData.open();
        //links up the views
        mName=(EditText) findViewById(R.id.register_name);
        mEmail=(EditText) findViewById(R.id.register_email);
        mPassword=(EditText) findViewById(R.id.register_password);
        mConfirmPassword=(EditText) findViewById(R.id.register_confirm_password);

    }
    public void registration(View view) {
        //converts to string the value in the edittexts
        newName=mName.getText().toString();
        newEmail=mEmail.getText().toString();
        newPassWord=mPassword.getText().toString();
        confirmPassword=mConfirmPassword.getText().toString();
        //if statement will check whether to launch new activity or not
        //data validation
        if(validateData()){
            Toast.makeText(this,message,Toast.LENGTH_LONG).show();
        }
        else {
            newData.createRegistry(newEmail, newPassWord);
            newData.close();
            startActivity(new Intent(Registration.this,KidRegistration.class));
        }
    }

    public boolean validateData(){
        //makes sure that the user has entered the fields asked for
        if (newName.equals("")||newEmail.equals("")||newPassWord.equals("")||confirmPassword.equals("")) {
            message="Invalid!! Try Again";
            return true;
        }
        //the name cannot be less than 3 or more than 30 characters
        else if(newName.length() < 3 || newName.length() > 30){
            message="Invalid Name";
            return true;
        }
        //Password length must be at least 5 characters and no more then 15
        else if(newPassWord.length()< 5|| newPassWord.length()> 15) {
            message="Invalid Password";
            return true;
            //This makes sure the passwords match
        } else if(!(newPassWord.equals(confirmPassword))){
            message="Password Does Not Match";
            return true;
        }
        //if the program reaches here, then the app will start the new activity
        else{
            message="Registration Successful";
            return false;}
    }

}