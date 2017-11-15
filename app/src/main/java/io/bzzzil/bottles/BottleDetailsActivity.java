package io.bzzzil.bottles;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;

import io.bzzzil.bottles.database.BottlesTable;
import io.bzzzil.bottles.database.CountriesTable;
import io.bzzzil.bottles.imports.ImportAsyncTask;


public class BottleDetailsActivity extends AppCompatActivity {
    private static final String TAG = "BottleDetailsActivity";

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

        // Fill data
        // TODO: fetch data
        Cursor cursor = null;

        if (cursor != null) {
            cursor.moveToFirst();

            // Populate items
            String type = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_TYPE));
            String country = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_COUNTRY));
            String manufacturer = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_MANUFACTURER));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_TITLE));
            int volume = cursor.getInt(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_VOLUME));
            double degree = cursor.getDouble(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_DEGREE));
            String package_ = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_PACKAGE));

            String incomeDate = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_INCOME_DATE));
            String incomeSource = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_INCOME_SOURCE));
            double price = cursor.getDouble(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_PRICE));
            String priceCurrency = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_PRICE_CURRENCY));
            String comments = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_COMMENTS));

            // Line 1 (type + title)
            SpannableStringBuilder details1 = new SpannableStringBuilder();
            if (!type.isEmpty()) {
                details1.append(type);
            }

            if (details1.length() > 0) {
                details1.append(" ");
            }
            details1.append(title);

            // Line 2 (volume + degree + package)
            SpannableStringBuilder details2 = new SpannableStringBuilder();
            if (volume != 0) {
                details2.append(String.valueOf(volume));
                details2.append(" ");
                details2.append(getString(R.string.volume_measure_ml));
            }

            if (degree != 0) {
                if (details2.length() > 0) {
                    details2.append(", ");
                }
                details2.append(String.valueOf(degree));
                details2.append(getString(R.string.degree_measure_percent));
            }

            if (!package_.isEmpty()) {
                if (details2.length() > 0) {
                    details2.append(", ");
                }
                details2.append(package_);
            }

            // Line 3 (country + manufacturer)
            SpannableStringBuilder details3 = new SpannableStringBuilder();

            if (!manufacturer.isEmpty()) {
                details3.append(manufacturer);
            }

            if (!country.isEmpty()) {
                if (details3.length() > 0) {
                    details3.append(", ");
                }
                int flag_resource = cursor.getInt(cursor.getColumnIndexOrThrow(CountriesTable.COLUMN_FLAG_RESOURCE_ID));
                if ( flag_resource != 0 && flag_resource != R.drawable.no_flag ) {
                    // Flag resource exists and is not "no flag"
                    details3.append(" ", new ImageSpan(this, flag_resource, DynamicDrawableSpan.ALIGN_BASELINE), 0);
                }
                details3.append(" ");
                details3.append(country);
            }

            // Line 4 (income date, income source, price)
            SpannableStringBuilder details4 = new SpannableStringBuilder();

            if (!incomeDate.isEmpty()) {
                details4.append(getString(R.string.details_in_collection_since));
                details4.append(" ");
                details4.append(incomeDate);
                details4.append(". ");
            }

            if (!incomeSource.isEmpty()) {
                details4.append(getString(R.string.details_source));
                details4.append(": ");
                details4.append(incomeSource);
                details4.append(". ");
            }

            if (price != 0) {
                details4.append(getString(R.string.details_bought_for));
                details4.append(" ");
                details4.append(String.valueOf(price));

                if (!priceCurrency.isEmpty()) {
                    details4.append(" ");
                    details4.append(String.valueOf(priceCurrency));
                }
                details4.append(".");
            }

            // Line 5 (comments)
            SpannableStringBuilder details5 = new SpannableStringBuilder();
            if (!comments.isEmpty()) {
                details5.append(getString(R.string.column_comments));
                details5.append(": ");
                details5.append(comments);
            }

            // All together
            SpannableStringBuilder details = new SpannableStringBuilder();
            details.append(details1);
            details.append("\n\n");
            details.append(details2);
            details.append("\n\n");
            details.append(details3);
            details.append("\n\n");
            details.append(details4);
            details.append("\n\n");
            details.append(details5);

            // Draw info
            setTitle(title);
            ((TextView)findViewById(R.id.bottle_details)).setText(details);

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
                startActivityForResult(intent, BottlesListActivity.ACTIVITY_ADD_EDIT_BOTTLE);
                return true;
            case R.id.action_delete_bottle:
                // Get bottle info
                Cursor cursor = null;//getContentResolver().query(bottleUri, null, null, null, null);
                if (cursor == null)
                {
                    return true;
                }
                cursor.moveToFirst();
                String text = getString(R.string.alert_delete);
                text = String.format(text, cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_TITLE)));
                cursor.close();

                // Alert
                new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.menu_action_delete_bottle))
                        .setMessage(text)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO: delete bottle
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult " + requestCode);
        switch (requestCode) {
            case BottlesListActivity.ACTIVITY_ADD_EDIT_BOTTLE:
                // Pass bottle edit activity results to parent
                setResult(resultCode, data);
                finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
