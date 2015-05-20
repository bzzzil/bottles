package io.bzzzil.bottles;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
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
        String[] projection = { BottlesTable.COLUMN_TITLE };
        Log.d(TAG, "Get details from uri: " + bottleUri);
        Cursor cursor = getContentResolver().query(bottleUri, projection, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();

            // Populate items
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
        int menuId = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (menuId) {
            case R.id.action_edit_bottle:
                Intent intent = new Intent(getApplicationContext(), BottleAddActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
                return true;
            case R.id.action_delete_bottle:
                new AlertDialog.Builder(this)
                        .setTitle("Delete bottle")
                        .setMessage("Are you sure you want to remove this bottle from collection?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getContentResolver().delete(bottleUri, null, null);
                                Toast.makeText(BottleDetailsActivity.this, "Bottle was deleted", Toast.LENGTH_LONG).show();
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
