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

import io.bzzzil.bottles.database.Bottle;
import io.bzzzil.bottles.database.BottleDocument;
import io.bzzzil.bottles.database.BottlesTable;
import io.bzzzil.bottles.database.CountriesTable;
import io.bzzzil.bottles.imports.ImportAsyncTask;


public class BottleDetailsActivity extends AppCompatActivity {
    private static final String TAG = "BottleDetailsActivity";

    private long id;

    private BottleDocument bottleDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottle_details);

        if (!getIntent().hasExtra("bottle")) {
            Log.e(TAG, "No bottle provided! Closing");
            finish();
        }

        bottleDoc = (BottleDocument)getIntent().getSerializableExtra("bottle");
        Bottle bottle = bottleDoc.getData();

        // Line 1 (type + title)
        SpannableStringBuilder details1 = new SpannableStringBuilder();
        if (!bottle.getType().isEmpty()) {
            details1.append(bottle.getType());
        }

        if (details1.length() > 0) {
            details1.append(" ");
        }
        details1.append(bottle.getTitle());

        // Line 2 (volume + degree + package)
        SpannableStringBuilder details2 = new SpannableStringBuilder();
        if (bottle.getVolume() != 0) {
            details2.append(String.valueOf(bottle.getVolume()));
            details2.append(" ");
            details2.append(getString(R.string.volume_measure_ml));
        }

        if (bottle.getDegree() != 0) {
            if (details2.length() > 0) {
                details2.append(", ");
            }
            details2.append(String.valueOf(bottle.getDegree()));
            details2.append(getString(R.string.degree_measure_percent));
        }

        if (!bottle.getPackaging().isEmpty()) {
            if (details2.length() > 0) {
                details2.append(", ");
            }
            details2.append(bottle.getPackaging());
        }

        // Line 3 (country + manufacturer)
        SpannableStringBuilder details3 = new SpannableStringBuilder();

        if (!bottle.getManufacturer().isEmpty()) {
            details3.append(bottle.getManufacturer());
        }

        if (!bottle.getCountry().isEmpty()) {
            if (details3.length() > 0) {
                details3.append(", ");
            }
            /*int flag_resource = cursor.getInt(cursor.getColumnIndexOrThrow(CountriesTable.COLUMN_FLAG_RESOURCE_ID));
            if ( flag_resource != 0 && flag_resource != R.drawable.no_flag ) {
                // Flag resource exists and is not "no flag"
                details3.append(" ", new ImageSpan(this, flag_resource, DynamicDrawableSpan.ALIGN_BASELINE), 0);
            }*/
            details3.append(" ");
            details3.append(bottle.getCountry());
        }

        // Line 4 (income date, income source, price)
        SpannableStringBuilder details4 = new SpannableStringBuilder();

        if (bottle.getIncomeDate() != 0) {
            details4.append(getString(R.string.details_in_collection_since));
            details4.append(" ");
            details4.append(String.valueOf(bottle.getIncomeDate()));
            details4.append(". ");
        }

        if (!bottle.getIncomeSource().isEmpty()) {
            details4.append(getString(R.string.details_source));
            details4.append(": ");
            details4.append(bottle.getIncomeSource());
            details4.append(". ");
        }

        if (bottle.getPrice() != 0) {
            details4.append(getString(R.string.details_bought_for));
            details4.append(" ");
            details4.append(String.valueOf(bottle.getPrice()));

            if (!bottle.getPriceCurrency().isEmpty()) {
                details4.append(" ");
                details4.append(String.valueOf(bottle.getPriceCurrency()));
            }
            details4.append(".");
        }

        // Line 5 (comments)
        SpannableStringBuilder details5 = new SpannableStringBuilder();
        if (!bottle.getComments().isEmpty()) {
            details5.append(getString(R.string.column_comments));
            details5.append(": ");
            details5.append(bottle.getComments());
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
        setTitle(bottle.getTitle());
        ((TextView)findViewById(R.id.bottle_details)).setText(details);
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
                intent.putExtra("bottle", bottleDoc);
                startActivityForResult(intent, BottlesListActivity.ACTIVITY_ADD_EDIT_BOTTLE);
                return true;
            case R.id.action_delete_bottle:
                // Get bottle info
                String text = getString(R.string.alert_delete);
                text = String.format(text, bottleDoc.getData().getTitle());

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
