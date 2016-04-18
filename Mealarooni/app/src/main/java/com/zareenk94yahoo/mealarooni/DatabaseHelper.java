package com.zareenk94yahoo.mealarooni;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by znk on 4/5/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // declare the DB name here
    public static final String DATABASE_NAME = "Mealarooni.db";

    // now declare the TABLE name that will be part of the DB
    public static final String TABLE_NAME = "users_table";

    // declare the COLUMNS of the TABLE
    public static final String COL_1 = "EMAIL";
    public static final String COL_2 = "PASSWORD";
    public static final String COL_3 = "NAME";


    // this is referencing the java class that will manage the SQL DB
    public DatabaseHelper(Context context) {

        // whenever the constructor below is called, our DB will now be created
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // this is the execute sql query method that takes a string sql query and executes this query
        db.execSQL("create table " + TABLE_NAME + " (EMAIL TEXT PRIMARY KEY, PASSWORD TEXT, NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // upgrade the table if version number is increased and call onCreate to create a new DB
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String email, String password, String name) {

        // Open the database for reading and writing
        SQLiteDatabase db = this.getWritableDatabase();

        // This class is used to store a set of values that a ContentResolver can process.
        ContentValues contentValues = new ContentValues();

        // you need to specify the column and the data for that column
        contentValues.put(COL_1, email);
        contentValues.put(COL_2, password);
        contentValues.put(COL_3, name);

        // need to give this the table name and the content values
        long result = db.insert(TABLE_NAME,null,contentValues);

        // method will return -1 if the insert did not work
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean checkEmail (String email) {
        // Open the database for reading and writing
        SQLiteDatabase db = this.getWritableDatabase();

        //check to see if Email is already in database
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where EMAIL = ?", new String[] {email});
        if (res.getCount() == 0) {
            return true;
        } else {
            return false;
        }
    }


}
