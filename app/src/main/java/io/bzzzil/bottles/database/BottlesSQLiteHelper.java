package io.bzzzil.bottles.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.bzzzil.bottles.Bottle;

public class BottlesSQLiteHelper extends SQLiteOpenHelper {

    /**
     * Database name
     */
    private static final String DATABASE_NAME = "bottles.db";

    /**
     * Database version
     */
    private static final int DATABASE_VERSION = 2;

    public BottlesSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        BottlesTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        BottlesTable.onUpgrade(db, oldVersion, newVersion);
    }
}
