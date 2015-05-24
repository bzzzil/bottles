package io.bzzzil.bottles;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import io.bzzzil.bottles.database.BottlesContentProvider;
import io.bzzzil.bottles.database.BottlesTable;


public class BottleAddActivity extends AppCompatActivity {
    private static final String TAG = "BottleAddActivity";

    private Uri bottleUri;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottle_add);

        Button addButton = (Button)findViewById(R.id.button_add_bottle);

        /**
         * Read info about bottle
         */
        Intent callingIntent = getIntent();
        if (callingIntent.hasExtra("id")) {
            id = callingIntent.getLongExtra("id", 0);
            Log.d(TAG, "Bottle id: " + id);
            bottleUri = Uri.parse(BottlesContentProvider.CONTENT_URI + "/" + id);

            // Get data
            Log.d(TAG, "Get details from uri: " + bottleUri);
            Cursor cursor = getContentResolver().query(bottleUri, null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();

                // Populate items
                String type = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_TYPE));
                ((EditText)findViewById(R.id.editType)).setText(type);

                String country = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_COUNTRY));
                ((EditText)findViewById(R.id.editCountry)).setText(country);

                String manufacturer = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_MANUFACTURER));
                ((EditText)findViewById(R.id.editManufacturer)).setText(manufacturer);

                String title = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_TITLE));
                ((EditText)findViewById(R.id.editTitle)).setText(title);

                String volume = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_VOLUME));
                ((EditText)findViewById(R.id.editVolume)).setText(volume);

                String degree = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_DEGREE));
                ((EditText)findViewById(R.id.editDegree)).setText(degree);

                setTitle("Edit details");

                cursor.close();
            }

            addButton.setText("Save");
        } else {
            bottleUri = BottlesContentProvider.CONTENT_URI;
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrUpdateBottle();
                setResult(RESULT_OK);
                finish();
            }
        });

        /**
         * "Types" dropdown population
         */
        AutoCompleteTextView editType = (AutoCompleteTextView)findViewById(R.id.editType);
        SimpleCursorAdapter typesAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_dropdown_item_1line, null,
                new String[] { BottlesTable.COLUMN_TYPE },
                new int[] { android.R.id.text1}, 0);
        typesAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                String select = BottlesTable.COLUMN_TYPE + " LIKE ? ";
                String[] selectArgs = { "%" + constraint + "%" };
                String[] projection = {
                        BottlesTable.COLUMN_ID, BottlesTable.COLUMN_TYPE
                };
                return getContentResolver().query(BottlesContentProvider.TYPES_URI, projection, select, selectArgs, null);
            }
        });
        typesAdapter.setCursorToStringConverter(new SimpleCursorAdapter.CursorToStringConverter() {
            @Override
            public CharSequence convertToString(Cursor cursor) {
                return cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_TYPE));
            }
        });
        editType.setAdapter(typesAdapter);

        /**
         * "Countires" dropdown population
         */
        AutoCompleteTextView editCountry = (AutoCompleteTextView)findViewById(R.id.editCountry);
        SimpleCursorAdapter countriesAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_dropdown_item_1line, null,
                new String[] { BottlesTable.COLUMN_COUNTRY },
                new int[] { android.R.id.text1}, 0);
        countriesAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                String select = BottlesTable.COLUMN_COUNTRY + " LIKE ? ";
                String[] selectArgs = { "%" + constraint + "%" };
                String[] projection = {
                        BottlesTable.COLUMN_ID, BottlesTable.COLUMN_COUNTRY
                };
                return getContentResolver().query(BottlesContentProvider.COUNTRIES_URI, projection, select, selectArgs, null);
            }
        });
        countriesAdapter.setCursorToStringConverter(new SimpleCursorAdapter.CursorToStringConverter() {
            @Override
            public CharSequence convertToString(Cursor cursor) {
                return cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_COUNTRY));
            }
        });
        editCountry.setAdapter(countriesAdapter);

        /**
         * "Manufacturers" dropdown population
         */
        AutoCompleteTextView editManufacturer = (AutoCompleteTextView)findViewById(R.id.editManufacturer);
        SimpleCursorAdapter manufacturersAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_dropdown_item_1line, null,
                new String[] { BottlesTable.COLUMN_MANUFACTURER },
                new int[] { android.R.id.text1}, 0);
        manufacturersAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                String select = BottlesTable.COLUMN_MANUFACTURER + " LIKE ? ";
                String[] selectArgs = { "%" + constraint + "%" };
                String[] projection = {
                        BottlesTable.COLUMN_ID, BottlesTable.COLUMN_MANUFACTURER
                };
                return getContentResolver().query(BottlesContentProvider.MANUFACTURERS_URI, projection, select, selectArgs, null);
            }
        });
        manufacturersAdapter.setCursorToStringConverter(new SimpleCursorAdapter.CursorToStringConverter() {
            @Override
            public CharSequence convertToString(Cursor cursor) {
                return cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_MANUFACTURER));
            }
        });
        editManufacturer.setAdapter(manufacturersAdapter);
    }

    /**
     * Add bottle to database or update existing (if id is known)
      */
    private void addOrUpdateBottle()
    {
        ContentValues values = new ContentValues();
        values.put(BottlesTable.COLUMN_TYPE, ((EditText) findViewById(R.id.editType)).getText().toString());
        values.put(BottlesTable.COLUMN_COUNTRY, ((EditText) findViewById(R.id.editCountry)).getText().toString());
        values.put(BottlesTable.COLUMN_MANUFACTURER, ((EditText) findViewById(R.id.editManufacturer)).getText().toString());
        values.put(BottlesTable.COLUMN_TITLE, ((EditText)findViewById(R.id.editTitle)).getText().toString());
        values.put(BottlesTable.COLUMN_VOLUME, ((EditText)findViewById(R.id.editVolume)).getText().toString());
        values.put(BottlesTable.COLUMN_DEGREE, ((EditText) findViewById(R.id.editDegree)).getText().toString());

        if (id != 0) {
            // Update existing item
            getContentResolver().update(bottleUri, values, null, null);
            Toast.makeText(BottleAddActivity.this, "Item was updated", Toast.LENGTH_LONG).show();
        }
        else {
            // Insert new item
            getContentResolver().insert(bottleUri, values);
            Toast.makeText(BottleAddActivity.this, "New item was inserted", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int menuId = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (menuId) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
