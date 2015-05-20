package io.bzzzil.bottles.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class BottlesTable {
    // Database table
    /**
     * Bottles: table name
     */
    public static final String TABLE_BOTTLES = "bottles";

    /**
     * Bottles: column "id"
     */
    public static final String COLUMN_ID = "_id";

    /**
     * Bottles: column "type"
     */
    public static final String COLUMN_TYPE = "type";

    /**
     * Bottles: column "country"
     */
    public static final String COLUMN_COUNTRY = "country";

    /**
     * Bottles: column "manufaturer"
     */
    public static final String COLUMN_MANUFACTURER = "manufacturer";

    /**
     * Bottles: column "title"
     */
    public static final String COLUMN_TITLE = "title";

    /**
     * Bottles: column "volume"
     */
    public static final String COLUMN_VOLUME = "volume";

    /**
     * Bottles: column "degree"
     */
    public static final String COLUMN_DEGREE = "degree";

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_BOTTLES + " ("
                + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_TYPE + " text not null, "
                + COLUMN_COUNTRY + " text not null, "
                + COLUMN_MANUFACTURER + " text not null, "
                + COLUMN_TITLE + " text not null, "
                + COLUMN_VOLUME + " integer not null, "
                + COLUMN_DEGREE + " real not null "
                + ")");
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(BottlesSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion
                        + ". ALL DATA WILL BE DESTROYED");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOTTLES);
        onCreate(db);
    }
}
