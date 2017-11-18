package io.bzzzil.bottles.imports;

import android.content.ContentValues;
import android.util.Log;

import com.google.firebase.firestore.CollectionReference;

import java.io.InputStream;

import io.bzzzil.bottles.database.Bottle;

class CsvImport {
    private static final String TAG = CsvImport.class.getName();

    private int parseVolume(String in) {
        try {
            return Integer.getInteger(in);
        } catch (Exception e) {
            return 0;
        }
    }

    private float parseDegree(String in) {
        try {
            return Float.parseFloat(in);
        } catch (Exception e) {
            return 0;
        }
    }

    private int parseIncomeDate(String in) {
        try {
            // TODO
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private float parsePrice(String in) {
        try {
            return Float.parseFloat(in);
        } catch (Exception e) {
            return 0;
        }
    }

    private void insert(CollectionReference to, ContentValues values)
    {
        Bottle bottle = new Bottle(
                values.getAsString(Bottle.COLUMN_TYPE),
                values.getAsString(Bottle.COLUMN_COUNTRY),
                values.getAsString(Bottle.COLUMN_MANUFACTURER),
                values.getAsString(Bottle.COLUMN_TITLE),
                parseVolume(values.getAsString(Bottle.COLUMN_VOLUME)),
                parseDegree(values.getAsString(Bottle.COLUMN_DEGREE)),
                values.getAsString(Bottle.COLUMN_PACKAGING),
                parseIncomeDate(values.getAsString(Bottle.COLUMN_INCOME_DATE)),
                values.getAsString(Bottle.COLUMN_INCOME_SOURCE),
                parsePrice(values.getAsString(Bottle.COLUMN_PRICE)),
                values.getAsString(Bottle.COLUMN_PRICE_CURRENCY),
                values.getAsString(Bottle.COLUMN_COMMENTS)
        );

        to.add(bottle);
    }

    public int doImport(InputStream from, CollectionReference to)
    {
        java.util.Scanner scanner = new java.util.Scanner(from).useDelimiter(";|\\r|\\r\\n|\\n");

        String[] names = new String[] {
                Bottle.COLUMN_TYPE,
                Bottle.COLUMN_COUNTRY,
                Bottle.COLUMN_MANUFACTURER,
                Bottle.COLUMN_TITLE,
                Bottle.COLUMN_VOLUME,
                Bottle.COLUMN_DEGREE,
                Bottle.COLUMN_PACKAGING,
                Bottle.COLUMN_INCOME_DATE,
                Bottle.COLUMN_INCOME_SOURCE,
                Bottle.COLUMN_PRICE,
                Bottle.COLUMN_COMMENTS,
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

                insert(to, values);
                imported++;

                values.clear();
                currentColumn = 0;
                next = next.trim();
                imported++;
            }

            if (currentColumn < names.length) {
                if ( names[currentColumn].equals(Bottle.COLUMN_PRICE) ) {
                    // Parse and prepare price
                    String[] price = next.split("\\s+");
                    if (price.length == 2) {
                        Log.d(TAG, "Scanner: price:" + price[0]);
                        values.put(Bottle.COLUMN_PRICE, price[0]);
                        Log.d(TAG, "Scanner: price currency:" + price[1]);
                        values.put(Bottle.COLUMN_PRICE_CURRENCY, price[1]);
                    } else {
                        Log.w(TAG, "Scanner did not parsed price " + next);
                        values.put(Bottle.COLUMN_PRICE, next);
                        values.put(Bottle.COLUMN_PRICE_CURRENCY, "");
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
            insert(to, values);
            imported++;
        }

        Log.d(TAG, "Scanner complete");
        scanner.close();
        return imported;
    }
}
