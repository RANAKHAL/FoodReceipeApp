package com.project.foodrecipes.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insertUser(String username, String email, int phone, String password) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.USERNAME, username);
        contentValue.put(DatabaseHelper.EMAIL, email);
        contentValue.put(DatabaseHelper.PHONE, phone);
        contentValue.put(DatabaseHelper.PASSWORD, password);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public boolean fetchLogin(String username, String password) {
        String[] selectionArgs = new String[]{username, password};
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.USERNAME, DatabaseHelper.EMAIL, DatabaseHelper.PHONE, DatabaseHelper.PASSWORD };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, DatabaseHelper.USERNAME + " = ? AND "+DatabaseHelper.PASSWORD+" = ?", selectionArgs, null, null, null);
        if (cursor != null) {
            return cursor.getCount() > 0;
        }
        return false;
    }

    public boolean isUserExists(String username) {
        String[] selectionArgs = new String[]{username};
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.USERNAME, DatabaseHelper.EMAIL, DatabaseHelper.PHONE, DatabaseHelper.PASSWORD };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, DatabaseHelper.USERNAME + " = ?", selectionArgs, null, null, null);
        if (cursor != null) {
            return cursor.getCount() > 0;
        }
        return false;
    }

    public int update(long _id, String username, String email, int phone, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USERNAME, username);
        contentValues.put(DatabaseHelper.EMAIL, email);
        contentValues.put(DatabaseHelper.PHONE, phone);
        contentValues.put(DatabaseHelper.PASSWORD, password);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}
