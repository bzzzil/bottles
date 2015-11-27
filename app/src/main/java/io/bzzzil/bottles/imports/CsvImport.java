package io.bzzzil.bottles.imports;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.util.Log;

import java.io.InputStream;

import io.bzzzil.bottles.database.BottlesContentProvider;
import io.bzzzil.bottles.database.BottlesTable;

public class CsvImport {
    private static final String TAG = CsvImport.class.getName();

    public int doImport(ContentResolver contentResolver, InputStream stream)
    {
        java.util.Scanner scanner = new java.util.Scanner(stream).useDelimiter(";|\\r|\\r\\n|\\n");

        String[] names = new String[] {
                BottlesTable.COLUMN_TYPE,
                BottlesTable.COLUMN_COUNTRY,
                BottlesTable.COLUMN_MANUFACTURER,
                BottlesTable.COLUMN_TITLE,
                BottlesTable.COLUMN_VOLUME,
                BottlesTable.COLUMN_DEGREE,
                BottlesTable.COLUMN_PACKAGE,
                BottlesTable.COLUMN_INCOME_DATE,
                BottlesTable.COLUMN_INCOME_SOURCE,
                BottlesTable.COLUMN_PRICE,
                BottlesTable.COLUMN_COMMENTS,
        };

        int currentColumn = 0;
        int imported = 0;
        ContentValues values = new ContentValues();
        while (scanner.hasNext())
        {
            String next = scanner.next();
            Log.d(TAG, "Scanner:" + next);


            if (currentColumn == names.length) {
                // End of record
                Log.d(TAG, "Scanner inserting to db:" + values);
                contentResolver.insert(BottlesContentProvider.CONTENT_URI, values);
                values.clear();
                currentColumn = 0;
                next = next.trim();
                imported++;
            }

            if (currentColumn < names.length) {
                if ( names[currentColumn].equals(BottlesTable.COLUMN_PRICE) ) {
                    // Parse and prepare price
                    String[] price = next.split("\\s+");
                    if (price.length == 2) {
                        Log.d(TAG, "Scanner: price:" + price[0]);
                        values.put(BottlesTable.COLUMN_PRICE, price[0]);
                        Log.d(TAG, "Scanner: price currency:" + price[1]);
                        values.put(BottlesTable.COLUMN_PRICE_CURRENCY, price[1]);
                    } else {
                        Log.w(TAG, "Scanner did not parsed price " + next);
                        values.put(BottlesTable.COLUMN_PRICE, next);
                        values.put(BottlesTable.COLUMN_PRICE_CURRENCY, "");
                    }
                } else {
                    // Other columns
                    values.put(names[currentColumn], next);
                }
            }

            currentColumn++;
        }

        if (currentColumn == names.length) {
            // End of record
            Log.d(TAG, "Scanner inserting to db:" + values);
            contentResolver.insert(BottlesContentProvider.CONTENT_URI, values);
            imported++;
        }

        Log.d(TAG, "Scanner complete");
        scanner.close();
        return imported;
    }
}
