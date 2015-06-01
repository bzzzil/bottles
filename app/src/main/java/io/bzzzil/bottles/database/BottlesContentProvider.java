package io.bzzzil.bottles.database;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.Arrays;
import java.util.HashSet;

public class BottlesContentProvider extends ContentProvider {
    private BottlesSQLiteHelper db;

    private static final String TAG = "BottlesContentProvider";

    // Used for UriMatcher
    private static final int BOTTLES = 10;
    private static final int TYPES = 20;
    private static final int COUNTRIES = 30;
    private static final int MANUFACTURERS = 40;
    private static final int STATISTICS = 50;
    private static final int BOTTLE_ID = 60;


    private static final String AUTHORITY = "io.bzzzil.bottles.contentprovider";

    private static final String BASE_PATH = "bottles";

    private static final String TYPES_PATH = BASE_PATH + "/types";

    private static final String COUNTRIES_PATH = BASE_PATH + "/countries";

    private static final String MANUFACTURERS_PATH = BASE_PATH + "/manufacturers";

    private static final String STATISTICS_PATH = BASE_PATH + "/statistics";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    public static final Uri TYPES_URI = Uri.parse( "content://" + AUTHORITY + "/" + TYPES_PATH );

    public static final Uri COUNTRIES_URI = Uri.parse( "content://" + AUTHORITY + "/" + COUNTRIES_PATH );

    public static final Uri MANUFACTURERS_URI = Uri.parse( "content://" + AUTHORITY + "/" + MANUFACTURERS_PATH );

    public static final Uri STATISTICS_URI = Uri.parse( "content://" + AUTHORITY + "/" + STATISTICS_PATH );

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/bottles";

    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/bottle";

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, BOTTLES);
        sURIMatcher.addURI(AUTHORITY, TYPES_PATH, TYPES);
        sURIMatcher.addURI(AUTHORITY, COUNTRIES_PATH, COUNTRIES);
        sURIMatcher.addURI(AUTHORITY, MANUFACTURERS_PATH, MANUFACTURERS);
        sURIMatcher.addURI(AUTHORITY, STATISTICS_PATH, STATISTICS);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", BOTTLE_ID);
    }

    @Override
    public boolean onCreate() {
        db = new BottlesSQLiteHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Using SQLite query builder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables(BottlesTable.TABLE_BOTTLES);

        SQLiteDatabase sqlite = db.getWritableDatabase();
        Cursor cursor = null;

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case BOTTLES:
                break;
            case TYPES:
                Log.d(TAG, "Fetch bottle types from url " + uri);
                cursor = queryBuilder.query(sqlite, projection, selection, selectionArgs, BottlesTable.COLUMN_TYPE, null, sortOrder);
                break;
            case COUNTRIES:
                Log.d(TAG, "Fetch bottle countries from url " + uri);
                queryBuilder.setTables(CountriesTable.TABLE_COUNTRIES);
                cursor = queryBuilder.query(sqlite, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case MANUFACTURERS:
                Log.d(TAG, "Fetch bottle manufacturers from url " + uri);
                cursor = queryBuilder.query(sqlite, projection, selection, selectionArgs, BottlesTable.COLUMN_MANUFACTURER, null, sortOrder);
                break;
            case STATISTICS:
                Log.d(TAG, "Fetch bottle statistics from url " + uri);
                cursor = sqlite.rawQuery("SELECT COUNT(*) AS total, SUM(volume)/1000 AS litres, AVG(degree) AS degree FROM " + BottlesTable.TABLE_BOTTLES, null);
                break;
            case BOTTLE_ID:
                Log.d(TAG, "Fetch bottle details from url " + uri);
                queryBuilder.appendWhere(BottlesTable.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        if (cursor == null) {
            cursor = queryBuilder.query(sqlite, projection, selection, selectionArgs, null, null, sortOrder);
        }

        // Ensure that potential listeners will be notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlite = db.getWritableDatabase();
        long id;
        switch (uriType) {
            case BOTTLES:
                Log.d(TAG, "Insert bottle from url " + uri);
                id = sqlite.insert(BottlesTable.TABLE_BOTTLES, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlite = db.getWritableDatabase();
        int rowsDeleted;
        switch (uriType) {
            case BOTTLES:
                Log.d(TAG, "Delete bottles from url " + uri);
                rowsDeleted = sqlite.delete(BottlesTable.TABLE_BOTTLES, selection, selectionArgs);
                break;
            case BOTTLE_ID:
                Log.d(TAG, "Delete bottle from url " + uri);
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlite.delete(BottlesTable.TABLE_BOTTLES, BottlesTable.COLUMN_ID + "=" + id, null);
                } else {
                    rowsDeleted = sqlite.delete(BottlesTable.TABLE_BOTTLES, BottlesTable.COLUMN_ID + "=" + id
                            + " and " + selection, selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlite = db.getWritableDatabase();
        int rowsUpdated;
        switch (uriType) {
            case BOTTLES:
                Log.d(TAG, "Update bottles from url " + uri);
                rowsUpdated = sqlite.update(BottlesTable.TABLE_BOTTLES, values, selection, selectionArgs);
                break;
            case BOTTLE_ID:
                Log.d(TAG, "Update bottle from url " + uri);
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlite.update(BottlesTable.TABLE_BOTTLES, values,
                            BottlesTable.COLUMN_ID + "=" + id, null);
                } else {
                    rowsUpdated = sqlite.update(BottlesTable.TABLE_BOTTLES, values,
                            BottlesTable.COLUMN_ID + "=" + id + " and " + selection, selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }
}
