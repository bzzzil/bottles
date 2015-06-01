package io.bzzzil.bottles.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CountriesTable {
    // Database table
    /**
     * Bottles: table name
     */
    public static final String TABLE_COUNTRIES = "countries";

    /**
     * Bottles: column "id"
     */
    public static final String COLUMN_ID = "_id";

    /**
     * Bottles: column "name"
     */
    public static final String COLUMN_NAME = "name";

    /**
     * Bottles: column "flag_resource_id"
     */
    public static final String COLUMN_FLAG_RESOURCE_ID = "flag_resource_id";

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_COUNTRIES + " ("
                + COLUMN_ID + " varchar(2) primary key, "
                + COLUMN_NAME + " text not null, "
                + COLUMN_FLAG_RESOURCE_ID+ " integer not null "
                + ")");
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(BottlesSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion
                        + ". ALL DATA WILL BE DESTROYED");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTRIES);
        onCreate(db);
    }
}
