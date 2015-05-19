package io.bzzzil.bottles;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.List;

import io.bzzzil.bottles.database.BottlesContentProvider;
import io.bzzzil.bottles.database.BottlesTable;


public class MainActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] from = new String[] { BottlesTable.COLUMN_TITLE, BottlesTable.COLUMN_ID };
        int[] to = new int[] { R.id.bottle_title, R.id.bottle_detais };

        ListView bottlesList = (ListView)findViewById(R.id.listViewBottles);
        getLoaderManager().initLoader(0 , null, this);
        adapter = new SimpleCursorAdapter(this, R.layout.bottle_row, null, from, to, 0);
        bottlesList.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_bottle) {
            startActivity(new Intent(this, BottleAddActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = { BottlesTable.COLUMN_ID, BottlesTable.COLUMN_TITLE };
        CursorLoader cursorLoader = new CursorLoader(this,
                BottlesContentProvider.CONTENT_URI, projection, null, null, null);
        return  cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
