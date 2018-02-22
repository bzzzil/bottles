package io.bzzzil.bottles.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class BottlesTable {
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
     * Bottles: column "manufacturer"
     */
    public static final String COLUMN_MANUFACTURER = "manufacturer";

    /**
     * Bottles: column "title"
     */
    private static final String COLUMN_TITLE = "title";

    /**
     * Bottles: column "volume"
     */
    private static final String COLUMN_VOLUME = "volume";

    /**
     * Bottles: column "degree"
     */
    private static final String COLUMN_DEGREE = "degree";

    /**
     * Bottles: column "package"
     */
    private static final String COLUMN_PACKAGE = "package";

    /**
     * Bottles: column "income_date"
     */
    private static final String COLUMN_INCOME_DATE = "income_date";

    /**
     * Bottles: column "income_source"
     */
    private static final String COLUMN_INCOME_SOURCE = "income_source";

    /**
     * Bottles: column "price"
     */
    private static final String COLUMN_PRICE = "price";

    /**
     * Bottles: column "price_currency"
     */
    private static final String COLUMN_PRICE_CURRENCY = "price_currency";

    /**
     * Bottles: column "comments"
     */
    private static final String COLUMN_COMMENTS = "comments";

    /**
     * Bottles: service column "search_words"
     *
     * This column represents simplified and normalized words to use by search features
     * (all important fields, lowercase, removed diacritics, etc)
     */
    private static final String COLUMN_INT_SEARCHWORDS = "_searchwords";
}
