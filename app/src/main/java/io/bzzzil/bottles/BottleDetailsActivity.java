package io.bzzzil.bottles;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import io.bzzzil.bottles.database.BottlesContentProvider;
import io.bzzzil.bottles.database.BottlesTable;


public class BottleDetailsActivity extends AppCompatActivity {
    private static final String TAG = "BottleDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottle_details);

        Intent callingIntent = getIntent();
        if (callingIntent.hasExtra("id") == false) {
            Log.e(TAG, "No id provided! Closing");
            finish();
        }

        long id = callingIntent.getLongExtra("id", 0);
        Log.d(TAG, "Bottle id: " + id);

        // Fill data
        String[] projection = { BottlesTable.COLUMN_TITLE };
        Uri uri = Uri.parse(BottlesContentProvider.CONTENT_URI + "/" + id);
        Log.d(TAG, "Get details from uri: "+uri);
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            String title = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_TITLE));
            ((TextView)findViewById(R.id.bottle_details_title)).setText(title);
            setTitle(title);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bottle_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
