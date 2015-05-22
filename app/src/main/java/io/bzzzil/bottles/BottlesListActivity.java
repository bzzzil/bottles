package io.bzzzil.bottles;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.io.InputStream;

import io.bzzzil.bottles.database.BottlesContentProvider;
import io.bzzzil.bottles.database.BottlesTable;


public class BottlesListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "BottlesListActivity";

    private SimpleCursorAdapter adapter;

    private static final int ACTIVITY_CHOOSE_FILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottles_list);

        String[] from = new String[] { BottlesTable.COLUMN_TITLE, BottlesTable.COLUMN_ID };
        int[] to = new int[] { R.id.bottle_title, R.id.bottle_detais };

        ListView bottlesList = (ListView)findViewById(R.id.listViewBottles);
        getLoaderManager().initLoader(0 , null, this);
        adapter = new SimpleCursorAdapter(this, R.layout.bottle_row, null, from, to, 0);
        bottlesList.setAdapter(adapter);
        bottlesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), BottleDetailsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bottles_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_add_bottle:
                startActivity(new Intent(this, BottleAddActivity.class));
                return true;
            case R.id.action_import_bottles:
                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("*/*");
                Intent intent = Intent.createChooser(chooseFile, "Choose a file for import");
                startActivityForResult(intent, ACTIVITY_CHOOSE_FILE);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult");
        switch (requestCode) {
            case ACTIVITY_CHOOSE_FILE:
                if (resultCode == RESULT_OK) {
                    Log.d(TAG, "Import file selected: " + data.getData());
                    try {
                        InputStream stream = getContentResolver().openInputStream(data.getData());
                        importFromStream(stream);
                    } catch (Exception e) {
                        Toast.makeText(BottlesListActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = { BottlesTable.COLUMN_ID, BottlesTable.COLUMN_TITLE };
        return  new CursorLoader(this,
                BottlesContentProvider.CONTENT_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    private void importFromStream(InputStream stream) {
        java.util.Scanner scanner = new java.util.Scanner(stream).useDelimiter("\t");

        String[] names = new String[] {
            BottlesTable.COLUMN_TYPE,
            BottlesTable.COLUMN_COUNTRY,
            BottlesTable.COLUMN_MANUFACTURER,
            BottlesTable.COLUMN_TITLE,
            BottlesTable.COLUMN_VOLUME,
            BottlesTable.COLUMN_DEGREE
        };

        int currentColumn = 0;
        ContentValues values = new ContentValues();
        while (scanner.hasNext())
        {
            String next = scanner.next();
            Log.d(TAG, "Scanner:" + next);

            if (next.startsWith("\r\n")) {
                // End of record
                Log.d(TAG, "Scanner insertin to db:" + values);
                getContentResolver().insert(BottlesContentProvider.CONTENT_URI, values);
                values.clear();
                currentColumn = 0;
                next = next.trim();
            }

            if (currentColumn < names.length) {
                values.put(names[currentColumn], next);
            }

            currentColumn++;
        }
        Log.d(TAG, "Scanner complete");
        scanner.close();
        Toast.makeText(BottlesListActivity.this, "Import complete", Toast.LENGTH_LONG).show();
    }
}
