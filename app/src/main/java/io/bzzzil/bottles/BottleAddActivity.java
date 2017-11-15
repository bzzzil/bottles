package io.bzzzil.bottles;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.HashMap;

import io.bzzzil.bottles.database.BottlesTable;
import io.bzzzil.bottles.database.CountriesTable;


public class BottleAddActivity extends AppCompatActivity {
    private static final String TAG = "BottleAddActivity";

    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottle_add);

        /**
         * Read info about bottle
         */
        Intent callingIntent = getIntent();
        if (callingIntent.hasExtra("id")) {
            id = callingIntent.getLongExtra("id", 0);
            Log.d(TAG, "Bottle id: " + id);

            // TODO: get data
            // Get data
            Cursor cursor = null;

            if (cursor != null) {
                cursor.moveToFirst();

                // Populate items
                ((EditText)findViewById(R.id.editType)).setText(cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_TYPE)));
                ((EditText)findViewById(R.id.editCountry)).setText(cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_COUNTRY)));
                ((EditText)findViewById(R.id.editManufacturer)).setText(cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_MANUFACTURER)));
                ((EditText)findViewById(R.id.editTitle)).setText(cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_TITLE)));
                ((EditText)findViewById(R.id.editVolume)).setText(cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_VOLUME)));
                ((EditText)findViewById(R.id.editDegree)).setText(cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_DEGREE)));
                ((EditText)findViewById(R.id.editPackage)).setText(cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_PACKAGE)));
                ((EditText)findViewById(R.id.editIncomeDate)).setText(cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_INCOME_DATE)));
                ((EditText)findViewById(R.id.editIncomeSource)).setText(cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_INCOME_SOURCE)));
                ((EditText)findViewById(R.id.editPrice)).setText(cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_PRICE)));
                ((EditText)findViewById(R.id.editPriceCurrency)).setText(cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_PRICE_CURRENCY)));
                ((EditText)findViewById(R.id.editComments)).setText(cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_COMMENTS)));

                setTitle(getString(R.string.title_activity_bottle_edit));

                cursor.close();
            }
        }

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
                return null;
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
         * "Countries" dropdown population
         */
        AutoCompleteTextView editCountry = (AutoCompleteTextView)findViewById(R.id.editCountry);
        SimpleCursorAdapter countriesAdapter = new SimpleCursorAdapter(this, R.layout.country_row, null,
                new String[] { BottlesTable.COLUMN_COUNTRY, CountriesTable.COLUMN_FLAG_RESOURCE_ID },
                new int[] { R.id.country_name, R.id.country_flag }, 0);
        countriesAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                String select = BottlesTable.COLUMN_COUNTRY + " LIKE ? ";
                String[] selectArgs = { constraint + "%" };
                String[] projection = { BottlesTable.COLUMN_ID, BottlesTable.COLUMN_COUNTRY, CountriesTable.COLUMN_FLAG_RESOURCE_ID };
                return null;
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
                return null;
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
        // return intent with form values
        HashMap<String, String> values = new HashMap<>();
        values.put(BottlesTable.COLUMN_TYPE, ((EditText) findViewById(R.id.editType)).getText().toString());
        values.put(BottlesTable.COLUMN_COUNTRY, ((EditText) findViewById(R.id.editCountry)).getText().toString());
        values.put(BottlesTable.COLUMN_MANUFACTURER, ((EditText) findViewById(R.id.editManufacturer)).getText().toString());
        values.put(BottlesTable.COLUMN_TITLE, ((EditText)findViewById(R.id.editTitle)).getText().toString());
        values.put(BottlesTable.COLUMN_VOLUME, ((EditText)findViewById(R.id.editVolume)).getText().toString());
        values.put(BottlesTable.COLUMN_DEGREE, ((EditText) findViewById(R.id.editDegree)).getText().toString());
        values.put(BottlesTable.COLUMN_PACKAGE, ((EditText) findViewById(R.id.editPackage)).getText().toString());
        values.put(BottlesTable.COLUMN_INCOME_DATE, ((EditText) findViewById(R.id.editIncomeDate)).getText().toString());
        values.put(BottlesTable.COLUMN_INCOME_SOURCE, ((EditText) findViewById(R.id.editIncomeSource)).getText().toString());
        values.put(BottlesTable.COLUMN_PRICE, ((EditText) findViewById(R.id.editPrice)).getText().toString());
        values.put(BottlesTable.COLUMN_PRICE_CURRENCY, ((EditText) findViewById(R.id.editPriceCurrency)).getText().toString());
        values.put(BottlesTable.COLUMN_COMMENTS, ((EditText) findViewById(R.id.editComments)).getText().toString());
        Intent intent = new Intent();
        intent.putExtra("data", values);
        setResult(RESULT_OK, intent);
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
            case R.id.action_save_bottle:
                addOrUpdateBottle();
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bottle_add, menu);
        return true;
    }
}
