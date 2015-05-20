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
import android.widget.Button;
import android.widget.EditText;
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

        Intent callingIntent = getIntent();
        if (callingIntent.hasExtra("id")) {
            id = callingIntent.getLongExtra("id", 0);
            Log.d(TAG, "Bottle id: " + id);
            bottleUri = Uri.parse(BottlesContentProvider.CONTENT_URI + "/" + id);

            // Get data
            String[] projection = { BottlesTable.COLUMN_TITLE };
            Log.d(TAG, "Get details from uri: " + bottleUri);
            Cursor cursor = getContentResolver().query(bottleUri, projection, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();

                // Populate items
                String title = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_TITLE));
                ((EditText)findViewById(R.id.editTitle)).setText(title);

                setTitle("Edit details");
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

    }

    /**
     * Add bottle to database or update existing (if id is known)
      */
    private void addOrUpdateBottle()
    {
        EditText title = (EditText)findViewById(R.id.editTitle);

        ContentValues values = new ContentValues();
        values.put(BottlesTable.COLUMN_TITLE, title.getText().toString());

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
