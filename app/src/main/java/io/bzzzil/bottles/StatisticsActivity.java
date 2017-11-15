package io.bzzzil.bottles;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.Locale;

public class StatisticsActivity extends AppCompatActivity {
    private static final String TAG = "StatisticsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        // TODO
        /*Uri statisticsUri = Uri.parse(BottlesContentProvider.STATISTICS_URI + "");

        Log.d(TAG, "Get statistics from uri: " + statisticsUri);
        Cursor cursor = getContentResolver().query(statisticsUri, null, null, null, null);
        if (cursor==null) {
            return;
        }
        cursor.moveToFirst();

        String totalBottles = "" + cursor.getInt(cursor.getColumnIndexOrThrow("total"));
        String totalLitres = String.format(Locale.getDefault(), "%.1f %s", cursor.getFloat(cursor.getColumnIndexOrThrow("litres")), getText(R.string.volume_measure_l));
        String averageDegree = String.format(Locale.getDefault(),  "%.1f%s", cursor.getFloat(cursor.getColumnIndexOrThrow("degree")), getText(R.string.degree_measure_percent));

        ((TextView)findViewById(R.id.statistics_total_bottles_value)).setText(totalBottles);
        ((TextView)findViewById(R.id.statistics_total_liters_value)).setText(totalLitres);
        ((TextView)findViewById(R.id.statistics_average_degree_value)).setText(averageDegree);

        cursor.close();*/
    }
}
