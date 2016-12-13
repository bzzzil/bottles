package io.bzzzil.bottles.database;

import android.content.Context;
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
     * Bottles: column "id" full specification
     */
    public static final String FULL_ID = TABLE_BOTTLES + "._id";

    /**
     * Bottles: column "type"
     */
    public static final String COLUMN_TYPE = "type";

    /**
     * Bottles: column "country"
     */
    public static final String COLUMN_COUNTRY = "country";

    /**
     * Bottles: column "manufacturer"
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

    /**
     * Bottles: column "package"
     */
    public static final String COLUMN_PACKAGE = "package";

    /**
     * Bottles: column "income_date"
     */
    public static final String COLUMN_INCOME_DATE = "income_date";

    /**
     * Bottles: column "income_source"
     */
    public static final String COLUMN_INCOME_SOURCE = "income_source";

    /**
     * Bottles: column "price"
     */
    public static final String COLUMN_PRICE = "price";

    /**
     * Bottles: column "price_currency"
     */
    public static final String COLUMN_PRICE_CURRENCY = "price_currency";

    /**
     * Bottles: column "comments"
     */
    public static final String COLUMN_COMMENTS = "comments";

    /**
     * Bottles: service column "search_words"
     *
     * This column represents simplified and normalized words to use by search features
     * (all important fields, lowercase, removed diacritics, etc)
     */
    public static final String COLUMN_INT_SEARCHWORDS = "_searchwords";

    public static void onCreate(SQLiteDatabase db, Context context) {
        db.execSQL("create table " + TABLE_BOTTLES + " ("
                + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_TYPE + " text not null, "
                + COLUMN_COUNTRY + " text not null, "
                + COLUMN_MANUFACTURER + " text not null, "
                + COLUMN_TITLE + " text not null, "
                + COLUMN_VOLUME + " integer not null, "
                + COLUMN_DEGREE + " real not null, "
                + COLUMN_PACKAGE + " text not null, "
                + COLUMN_INCOME_DATE + " integer not null, "
                + COLUMN_INCOME_SOURCE + " integer not null, "
                + COLUMN_PRICE + " real, "
                + COLUMN_PRICE_CURRENCY + " text not null, "
                + COLUMN_COMMENTS + " text not null, "
                + COLUMN_INT_SEARCHWORDS + " text not null "
                + ")");
    }

    public static void onUpgrade(SQLiteDatabase db, Context context, int oldVersion, int newVersion) {
        Log.w(BottlesTable.class.getName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion
                        + ". ALL DATA WILL BE DESTROYED");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOTTLES);
        onCreate(db, context);
    }
}
