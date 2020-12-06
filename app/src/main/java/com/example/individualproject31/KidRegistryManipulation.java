package com.example.individualproject31;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static com.example.individualproject31.DatabaseContract.*;

public class KidRegistryManipulation {

    // Database fields
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    private String[] allColumns = {
            ChildRegistrationDB._ID,
            ChildRegistrationDB.COLUMN_USERNAME,
            ChildRegistrationDB.COLUMN_PASSWORD,
    };
    public KidRegistryManipulation(Context context) {
        dbHelper = new DatabaseHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }
    //creates the table for the kids registry
    public KidRegistryOrganizer createRegistryKid(String username, String password) {
        ContentValues values = new ContentValues();
        values.put(ChildRegistrationDB.COLUMN_USERNAME,username);
        values.put(ChildRegistrationDB.COLUMN_PASSWORD, password);
        long insertId = database.insert(ChildRegistrationDB.TABLE_NAME, null,
                values);
        Cursor cursor = database.query(ChildRegistrationDB.TABLE_NAME,
                allColumns, ChildRegistrationDB._ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        KidRegistryOrganizer newRegistry = cursorToRegistry(cursor);
        cursor.close();
        return newRegistry;
    }

    private KidRegistryOrganizer cursorToRegistry(Cursor cursor) {
        KidRegistryOrganizer registry = new KidRegistryOrganizer();
        registry.setId(cursor.getLong(0));
        registry.setUsername(cursor.getString(1));
        registry.setPassword(cursor.getString(2));
        return registry;}
}
