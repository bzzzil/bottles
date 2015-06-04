package io.bzzzil.bottles;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import io.bzzzil.bottles.database.BottlesContentProvider;
import io.bzzzil.bottles.database.BottlesTable;


public class BottleDetailsActivity extends AppCompatActivity {
    private static final String TAG = "BottleDetailsActivity";

    private Uri bottleUri;

    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottle_details);

        Intent callingIntent = getIntent();
        if (!callingIntent.hasExtra("id")) {
            Log.e(TAG, "No id provided! Closing");
            finish();
        }

        id = callingIntent.getLongExtra("id", 0);
        Log.d(TAG, "Bottle id: " + id);
        bottleUri = Uri.parse(BottlesContentProvider.CONTENT_URI + "/" + id);

        // Fill data
        Log.d(TAG, "Get details from uri: " + bottleUri);
        Cursor cursor = getContentResolver().query(bottleUri, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();

            // Populate items
            String type = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_TYPE));
            ((TextView)findViewById(R.id.bottle_details_type)).setText(type);

            String country = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_COUNTRY));
            ((TextView)findViewById(R.id.bottle_details_country)).setText(country);

            String manufacturer = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_MANUFACTURER));
            ((TextView)findViewById(R.id.bottle_details_manufacturer)).setText(manufacturer);

            String title = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_TITLE));
            ((TextView)findViewById(R.id.bottle_details_title)).setText(title);

            String volume = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_VOLUME));
            ((TextView)findViewById(R.id.bottle_details_volume)).setText(volume);

            String degree = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_DEGREE));
            ((TextView)findViewById(R.id.bottle_details_degree)).setText(degree);

            setTitle(title);

            cursor.close();
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
        int menuId = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (menuId) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_edit_bottle:
                Intent intent = new Intent(getApplicationContext(), BottleAddActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
                return true;
            case R.id.action_delete_bottle:
                // Get bottle info
                Cursor cursor = getContentResolver().query(bottleUri, null, null, null, null);
                cursor.moveToFirst();
                String text = getString(R.string.alert_delete);
                text = String.format(text, cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_TITLE)));

                // Alert
                new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.menu_action_delete_bottle))
                        .setMessage(text)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getContentResolver().delete(bottleUri, null, null);
                                Toast.makeText(BottleDetailsActivity.this, getString(R.string.toast_bottle_deleted), Toast.LENGTH_LONG).show();
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
