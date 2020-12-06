package com.example.individualproject31;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.individualproject31.DatabaseContract.*;

public class DatabaseManipulation {
    // Database fields
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = {
            RegistrationDB._ID,
            RegistrationDB.COLUMN_EMAIL,
            RegistrationDB.COLUMN_PASSWORD
    };

    public DatabaseManipulation(Context context) {
        dbHelper = new DatabaseHelper(context);
    }
    //will open the database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    //will close the databse
    public void close() {
        dbHelper.close();
    }
    //this is responsible to create the table for the parents
    public DatabaseOrganizer createRegistry(String email, String password) {
        ContentValues values = new ContentValues();
        values.put(RegistrationDB.COLUMN_EMAIL,email);
        values.put(RegistrationDB.COLUMN_PASSWORD, password);
        long insertId = database.insert(RegistrationDB.TABLE_NAME, null,
                values);
        Cursor cursor = database.query(RegistrationDB.TABLE_NAME,
                allColumns, RegistrationDB._ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        DatabaseOrganizer newRegistry = cursorToRegistry(cursor);
        cursor.close();
        return newRegistry;
    }
    private DatabaseOrganizer cursorToRegistry(Cursor cursor) {
        DatabaseOrganizer registry = new DatabaseOrganizer();
        registry.setId(cursor.getLong(0));
        registry.setEmail(cursor.getString(1));
        registry.setPassword(cursor.getString(2));
        return registry;
    }

}
