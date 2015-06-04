package io.bzzzil.bottles.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BottlesSQLiteHelper extends SQLiteOpenHelper {

    /**
     * Database name
     */
    private static final String DATABASE_NAME = "bottles.db";

    /**
     * Database version
     */
    private static final int DATABASE_VERSION = 18;

    public BottlesSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        BottlesTable.onCreate(db);
        CountriesTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        BottlesTable.onUpgrade(db, oldVersion, newVersion);
        CountriesTable.onUpgrade(db, oldVersion, newVersion);
    }
}
