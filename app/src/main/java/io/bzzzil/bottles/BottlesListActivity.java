package io.bzzzil.bottles;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
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
import java.util.ArrayList;
import java.util.List;

import io.bzzzil.bottles.database.BottlesContentProvider;
import io.bzzzil.bottles.database.BottlesTable;


public class BottlesListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "BottlesListActivity";

    /**
     * Name for shared preferences
     */
    public static String PREFS = "bottles";
    public static String PREFS_SEARCH = "search";

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
                // Create query from search string
                Log.d(TAG, "Run filter query \"" + searchBox.getText() + "\"");
                String[] searchWords = searchBox.getText().toString().split("\\s+");

                StringBuilder selection = new StringBuilder();
                List<String> selectionArgs = new ArrayList<>();

                for (String word : searchWords) {
                    word = word.trim();
                    if (word.length() > 0) {
                        if (selection.length() > 0) {
                            selection.append(" AND ");
                        }

                        selection.append("(");
                        for (String searchColumn: BottlesContentProvider.getSearchableColumns()) {
                            if (selection.charAt(selection.length()-1) != '(')
                            {
                                selection.append( " OR " );
                            }
                            selection.append( searchColumn );
                            selection.append( " LIKE ? " );
                            selectionArgs.add("%" + word + "%");
                        }
                        selection.append(")");
                    }
                }
                // TODO: crashing on screen rotate
                String[] selectionArgsArray = new String[selectionArgs.size()];
                selectionArgs.toArray(selectionArgsArray);
                return getContentResolver().query(BottlesContentProvider.CONTENT_URI, null, selection.toString(), selectionArgsArray, null);
            }
        });

        // Restore shared preferences
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        String searchValue = prefs.getString(PREFS_SEARCH, null);
        if (searchValue != null) {
            searchBox.setText("");
            searchBox.append(searchValue);
        }
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
    protected void onDestroy() {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS, MODE_PRIVATE).edit();
        editor.putString(PREFS_SEARCH, searchBox.getText().toString());
        editor.apply();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        refreshBottlesList();
        super.onResume();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        Log.d(TAG, "Context menu for item " + info.id);
        switch (item.getItemId()) {
            case R.id.action_edit_bottle:
                Intent intent = new Intent(getApplicationContext(), BottleAddActivity.class);
                intent.putExtra("id", info.id);
                startActivity(intent);
                return true;
            case R.id.action_delete_bottle:
                final Uri bottleUri = Uri.parse(BottlesContentProvider.CONTENT_URI + "/" + info.id);
                Cursor cursor = getContentResolver().query(bottleUri, null, null, null, null);
                cursor.moveToFirst();
                String title = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_TITLE));
                String text = getString(R.string.alert_delete);
                text = String.format(text, title);
                new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.menu_action_delete_bottle))
                        .setMessage(text)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getContentResolver().delete(Uri.parse(BottlesContentProvider.CONTENT_URI + "/" + info.id), null, null);
                                refreshBottlesList();
                                Toast.makeText(BottlesListActivity.this, getString(R.string.toast_bottle_deleted), Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                cursor.close();
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
            case R.id.action_statistics:
                startActivity(new Intent(this, StatisticsActivity.class));
                return true;
            case R.id.action_add_bottle:
                startActivity(new Intent(this, BottleAddActivity.class));
                return true;
            case R.id.action_delete_all:
                new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.menu_action_delete_all))
                        .setMessage(getString(R.string.alert_delete_all))
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getContentResolver().delete(BottlesContentProvider.CONTENT_URI, null, null);
                                Toast.makeText(BottlesListActivity.this, getString(R.string.toast_all_bottles_deleted), Toast.LENGTH_LONG).show();
                                refreshBottlesList();
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
        java.util.Scanner scanner = new java.util.Scanner(stream).useDelimiter(";|\\r|\\r\\n|\\n");

        String[] names = new String[] {
                BottlesTable.COLUMN_TYPE,
                BottlesTable.COLUMN_COUNTRY,
                BottlesTable.COLUMN_MANUFACTURER,
                BottlesTable.COLUMN_TITLE,
                BottlesTable.COLUMN_VOLUME,
                BottlesTable.COLUMN_DEGREE,
                BottlesTable.COLUMN_PACKAGE,
                BottlesTable.COLUMN_INCOME_DATE,
                BottlesTable.COLUMN_INCOME_SOURCE,
                BottlesTable.COLUMN_PRICE,
                BottlesTable.COLUMN_COMMENTS,
        };

        int currentColumn = 0;
        int imported = 0;
        ContentValues values = new ContentValues();
        while (scanner.hasNext())
        {
            String next = scanner.next();
            Log.d(TAG, "Scanner:" + next);


            if (currentColumn == names.length) {
                // End of record
                Log.d(TAG, "Scanner inserting to db:" + values);
                getContentResolver().insert(BottlesContentProvider.CONTENT_URI, values);
                values.clear();
                currentColumn = 0;
                next = next.trim();
                imported++;
            }

            if (currentColumn < names.length) {
                if ( names[currentColumn].equals(BottlesTable.COLUMN_PRICE) ) {
                    // Parse and prepare price
                    String[] price = next.split("\\s+");
                    if (price.length == 2) {
                        Log.d(TAG, "Scanner: price:" + price[0]);
                        values.put(BottlesTable.COLUMN_PRICE, price[0]);
                        Log.d(TAG, "Scanner: price currency:" + price[1]);
                        values.put(BottlesTable.COLUMN_PRICE_CURRENCY, price[1]);
                    } else {
                        Log.w(TAG, "Scanner did not parsed price " + next);
                        values.put(BottlesTable.COLUMN_PRICE, next);
                        values.put(BottlesTable.COLUMN_PRICE_CURRENCY, "");
                    }
                } else {
                    // Other columns
                    values.put(names[currentColumn], next);
                }
            }

            currentColumn++;
        }

        if (currentColumn == names.length) {
            // End of record
            Log.d(TAG, "Scanner inserting to db:" + values);
            getContentResolver().insert(BottlesContentProvider.CONTENT_URI, values);
            imported++;
        }

        Log.d(TAG, "Scanner complete");
        scanner.close();
        String toast = getString(R.string.toast_import_complete);
        toast = String.format(toast, imported);
        Toast.makeText(BottlesListActivity.this, toast, Toast.LENGTH_LONG).show();
        refreshBottlesList();
    }

    private void refreshBottlesList()
    {
        adapter.getFilter().filter("");
        adapter.notifyDataSetChanged();
    }
}
