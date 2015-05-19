package io.bzzzil.bottles;

import android.content.ContentValues;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.bzzzil.bottles.database.BottlesContentProvider;
import io.bzzzil.bottles.database.BottlesTable;


public class BottleAddActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottle_add);

        Button addButton = (Button)findViewById(R.id.button_add_bottle);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OOO", "ONCLICK");

                EditText title = (EditText)findViewById(R.id.editTitle);

                ContentValues values = new ContentValues();
                values.put(BottlesTable.COLUMN_TITLE, title.getText().toString());
                getContentResolver().insert(BottlesContentProvider.CONTENT_URI, values);
                Toast.makeText(BottleAddActivity.this, "New item was inserted", Toast.LENGTH_LONG).show();
                setResult(RESULT_OK);
                finish();
            }
        });

    }
}
