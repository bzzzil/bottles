package io.bzzzil.bottles;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import io.bzzzil.bottles.database.Bottle;
import io.bzzzil.bottles.database.BottleDocument;
import io.bzzzil.bottles.database.BottlesTable;
import io.bzzzil.bottles.database.CountriesTable;


public class BottleAddActivity extends AppCompatActivity {
    private static final String TAG = "BottleAddActivity";

    private BottleDocument bottleDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottle_add);


        // Read info about bottle
        if (getIntent().hasExtra("bottle")) {
            bottleDoc = (BottleDocument)getIntent().getSerializableExtra("bottle");
            Log.d(TAG, "Bottle title: " + bottleDoc.getData().getTitle());

            Bottle bottle = bottleDoc.getData();

            // Populate items
            ((EditText)findViewById(R.id.editType)).setText(bottle.getType());
            ((EditText)findViewById(R.id.editCountry)).setText(bottle.getCountry());
            ((EditText)findViewById(R.id.editManufacturer)).setText(bottle.getManufacturer());
            ((EditText)findViewById(R.id.editTitle)).setText(bottle.getTitle());
            ((EditText)findViewById(R.id.editVolume)).setText(bottle.getVolumeAsString());
            ((EditText)findViewById(R.id.editDegree)).setText(bottle.getDegreeAsString());
            ((EditText)findViewById(R.id.editPackage)).setText(bottle.getPackaging());
            ((EditText)findViewById(R.id.editIncomeDate)).setText(bottle.getIncomeDateAsString());
            ((EditText)findViewById(R.id.editIncomeSource)).setText(bottle.getIncomeSource());
            ((EditText)findViewById(R.id.editPrice)).setText(bottle.getPriceAsString());
            ((EditText)findViewById(R.id.editPriceCurrency)).setText(bottle.getPriceCurrency());
            ((EditText)findViewById(R.id.editComments)).setText(bottle.getComments());

            setTitle(getString(R.string.title_activity_bottle_edit));

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
        Intent intent = new Intent();

        // return intent with form values
        Bottle bottle = new Bottle(
            ((EditText) findViewById(R.id.editType)).getText().toString(),
            ((EditText) findViewById(R.id.editCountry)).getText().toString(),
            ((EditText) findViewById(R.id.editManufacturer)).getText().toString(),
            ((EditText)findViewById(R.id.editTitle)).getText().toString(),
            Integer.parseInt(((EditText)findViewById(R.id.editVolume)).getText().toString()),
            Float.parseFloat(((EditText) findViewById(R.id.editDegree)).getText().toString()),
            ((EditText) findViewById(R.id.editPackage)).getText().toString(),
            Integer.parseInt(((EditText) findViewById(R.id.editIncomeDate)).getText().toString()),
            ((EditText) findViewById(R.id.editIncomeSource)).getText().toString(),
            Float.parseFloat(((EditText) findViewById(R.id.editPrice)).getText().toString()),
            ((EditText) findViewById(R.id.editPriceCurrency)).getText().toString(),
            ((EditText) findViewById(R.id.editComments)).getText().toString()
        );

        if (bottleDoc != null && bottleDoc.getId() != null) {
            // Existing document
            intent.putExtra("bottle", new BottleDocument(
                    bottleDoc.getId(), bottle
            ));
        } else {
            // New document
            intent.putExtra("bottle", new BottleDocument(
                    null, bottle
            ));
        }


        setResult(RESULT_OK, intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int menuId = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (menuId == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        } else if (menuId == R.id.action_save_bottle) {
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
