package io.bzzzil.bottles;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;

import io.bzzzil.bottles.database.BottlesContentProvider;
import io.bzzzil.bottles.database.BottlesTable;


public class BottlesListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "BottlesListActivity";

    private BottlesListCustomAdapter adapter;

    private ListView bottlesList;
    private EditText searchBox;

    private static final int ACTIVITY_CHOOSE_FILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottles_list);

        searchBox = (EditText)findViewById(R.id.editSearch);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.getFilter().filter(s.toString());
                adapter.notifyDataSetChanged();
            }
        });

        String[] from = new String[] { BottlesTable.COLUMN_TITLE, BottlesTable.COLUMN_TYPE };
        int[] to = new int[] { R.id.bottle_title, R.id.bottle_detais };

        bottlesList = (ListView)findViewById(R.id.listViewBottles);
        getLoaderManager().initLoader(0, null, this);
        adapter = new BottlesListCustomAdapter(this, R.layout.bottle_row, null, from, to, 0);
        bottlesList.setAdapter(adapter);
        bottlesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), BottleDetailsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        registerForContextMenu(bottlesList);
        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                // Fill data
                String selection = null;
                String[] selectionArgs = null;
                if (constraint.length() > 0) {
                    String text = searchBox.getText().toString();
                    selection = "type LIKE ? OR country LIKE ? OR manufacturer LIKE ? OR title LIKE ?";
                    selectionArgs = new String[]{
                            "%" + text + "%",
                            "%" + text + "%",
                            "%" + text + "%",
                            "%" + text + "%"
                    };
                }
                // TODO: crashing on screen rotate
                return getContentResolver().query(BottlesContentProvider.CONTENT_URI, null, selection, selectionArgs, null);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()) {
            case R.id.listViewBottles:
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.menu_bottle_details, menu);
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        Log.d(TAG, "Context menu for item " + info.id);
        final Uri bottleUri = Uri.parse(BottlesContentProvider.CONTENT_URI + "/" + info.id);

        Cursor cursor = getContentResolver().query(bottleUri, null, null, null, null);
        cursor.moveToFirst();

        switch (item.getItemId()) {
            case R.id.action_edit_bottle:
                Intent intent = new Intent(getApplicationContext(), BottleAddActivity.class);
                intent.putExtra("id", info.id);
                startActivity(intent);
                return true;
            case R.id.action_delete_bottle:
                String title = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_TITLE));
                new AlertDialog.Builder(this)
                        .setTitle("Delete bottle")
                        .setMessage("Are you sure you want to remove " + title + " from collection ? ")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getContentResolver().delete(Uri.parse(BottlesContentProvider.CONTENT_URI + "/" + info.id), null, null);
                                Toast.makeText(BottlesListActivity.this, "Bottle was deleted", Toast.LENGTH_LONG).show();
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
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bottles_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_add_bottle:
                startActivity(new Intent(this, BottleAddActivity.class));
                return true;
            case R.id.action_delete_all:
                new AlertDialog.Builder(this)
                        .setTitle("Delete all bottles")
                        .setMessage("Are you sure you want to remove ALL bottles from collection? \n\nTHIS CAN NOT BE UNDONE!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getContentResolver().delete(BottlesContentProvider.CONTENT_URI, null, null);
                                Toast.makeText(BottlesListActivity.this, "Bottles were deleted", Toast.LENGTH_LONG).show();
                                adapter.notifyDataSetChanged();
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
            case R.id.action_import_bottles:
                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("*/*");
                Intent intent = Intent.createChooser(chooseFile, "Choose a file for import");
                startActivityForResult(intent, ACTIVITY_CHOOSE_FILE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult");
        switch (requestCode) {
            case ACTIVITY_CHOOSE_FILE:
                if (resultCode == RESULT_OK) {
                    Log.d(TAG, "Import file selected: " + data.getData());
                    try {
                        InputStream stream = getContentResolver().openInputStream(data.getData());
                        importFromStream(stream);
                    } catch (Exception e) {
                        Toast.makeText(BottlesListActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return  new CursorLoader(this,
                BottlesContentProvider.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    private void importFromStream(InputStream stream) {
        java.util.Scanner scanner = new java.util.Scanner(stream).useDelimiter("\t");

        String[] names = new String[] {
            BottlesTable.COLUMN_TYPE,
            BottlesTable.COLUMN_COUNTRY,
            BottlesTable.COLUMN_MANUFACTURER,
            BottlesTable.COLUMN_TITLE,
            BottlesTable.COLUMN_VOLUME,
            BottlesTable.COLUMN_DEGREE
        };

        int currentColumn = 0;
        int imported = 0;
        ContentValues values = new ContentValues();
        while (scanner.hasNext())
        {
            String next = scanner.next();
            Log.d(TAG, "Scanner:" + next);

            if (next.startsWith("\r\n")) {
                // End of record
                Log.d(TAG, "Scanner inserting to db:" + values);
                getContentResolver().insert(BottlesContentProvider.CONTENT_URI, values);
                values.clear();
                currentColumn = 0;
                next = next.trim();
                imported++;
            }

            if (currentColumn < names.length) {
                values.put(names[currentColumn], next);
            }

            currentColumn++;
        }
        Log.d(TAG, "Scanner complete");
        scanner.close();
        Toast.makeText(BottlesListActivity.this, "Imported " + imported + " new bottles", Toast.LENGTH_LONG).show();
        adapter.notifyDataSetChanged();
    }
}
