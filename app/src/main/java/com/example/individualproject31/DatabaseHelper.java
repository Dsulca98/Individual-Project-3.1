package com.example.individualproject31;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.individualproject31.DatabaseContract.*;

public class DatabaseHelper extends SQLiteOpenHelper{
    // Database creation sql statement
    //Parent Table
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + RegistrationDB.TABLE_NAME + " (" +
                    RegistrationDB._ID + " INTEGER PRIMARY KEY," +
                    RegistrationDB.COLUMN_EMAIL+ " TEXT," +
                    RegistrationDB.COLUMN_PASSWORD + " TEXT)";
    // Database creation sql statement
    //Table for children
    private static final String SQL_CREATE_CHILD =
            "CREATE TABLE " + ChildRegistrationDB.TABLE_NAME + " (" +
                    ChildRegistrationDB._ID + " INTEGER PRIMARY KEY," +
                    ChildRegistrationDB.COLUMN_USERNAME+ " TEXT," +
                    ChildRegistrationDB.COLUMN_PASSWORD + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + RegistrationDB.TABLE_NAME;
    private static final String SQL_DELETE_CHILD =
            "DROP TABLE IF EXISTS " + ChildRegistrationDB.TABLE_NAME;
    private static final String DATABASE_NAME = "register.db";
    private static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //create the tables
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQL_CREATE_ENTRIES);
        database.execSQL(SQL_CREATE_CHILD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        //delete old data
        db.execSQL("DROP TABLE IF EXISTS " + RegistrationDB.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ChildRegistrationDB.TABLE_NAME);

        //recreate tables
        onCreate(db);
    }
    //this is used to check whether the user is in the databse
    public boolean checkUser(String email, String password) {
        String[] columns = {RegistrationDB._ID};
        SQLiteDatabase db = getReadableDatabase();
        String selection = RegistrationDB.COLUMN_EMAIL + "=?" + " and " + RegistrationDB.COLUMN_PASSWORD + "=?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(RegistrationDB.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }


}
//    public long addUser(String email, String password){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("email",email);
//        contentValues.put("password",password);
//        long result = db.insert("registeruser",null,contentValues);
//        db.close();
//        return  result;
//    }
//    public long addKid(String name, String password){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name",name);
//        contentValues.put("password",password);
//        long result = db.insert("registerkid",null,contentValues);
//        db.close();
//        return  result;
//    }
//
//    public boolean checkUser(String email, String password){
//        String[] columns = { COL_1 };
//        SQLiteDatabase db = getReadableDatabase();
//        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
//        String[] selectionArgs = { email, password };
//        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
//        int count = cursor.getCount();
//        cursor.close();
//        db.close();
//
//        return count > 0;
//    }