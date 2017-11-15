package io.bzzzil.bottles;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.bzzzil.bottles.database.BottlesContentProvider;
import io.bzzzil.bottles.database.BottlesTable;
import io.bzzzil.bottles.imports.ImportAsyncTask;


public class BottlesListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "BottlesListActivity";

    /**
     * Name for shared preferences
     */
    private static final String PREFS = "bottles";
    private static final String PREFS_SEARCH = "search";

    private BottlesListCustomAdapter adapter;

    private EditText searchBox;

    FirebaseFirestore db  = FirebaseFirestore.getInstance();

    public static final int ACTIVITY_CHOOSE_FILE = 1;
    public static final int ACTIVITY_ADD_EDIT_BOTTLE = 2;
    public static final int ACTIVITY_BOTTLE_DETAILS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottles_list);

        searchBox = (EditText) findViewById(R.id.editSearch);
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

        String[] from = new String[]{BottlesTable.COLUMN_TITLE, BottlesTable.COLUMN_TYPE};
        int[] to = new int[]{R.id.bottle_title, R.id.bottle_details};

        ListView bottlesList = (ListView) findViewById(R.id.listViewBottles);
        getLoaderManager().initLoader(0, null, this);
        adapter = new BottlesListCustomAdapter(this, R.layout.bottle_row, from, to);
        bottlesList.setAdapter(adapter);
        bottlesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), BottleDetailsActivity.class);
                intent.putExtra("id", id);
                startActivityForResult(intent, ACTIVITY_BOTTLE_DETAILS);
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
                        for (String searchColumn : BottlesContentProvider.getSearchableColumns()) {
                            if (selection.charAt(selection.length() - 1) != '(') {
                                selection.append(" OR ");
                            }
                            selection.append(searchColumn);
                            selection.append(" LIKE ? ");
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
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Log.d(TAG, "Context menu for item " + info.id);
        switch (item.getItemId()) {
            case R.id.action_edit_bottle:
                Intent intent = new Intent(getApplicationContext(), BottleAddActivity.class);
                intent.putExtra("id", info.id);
                startActivityForResult(intent, ACTIVITY_ADD_EDIT_BOTTLE);
                return true;
            case R.id.action_delete_bottle:
                final Uri bottleUri = Uri.parse(BottlesContentProvider.CONTENT_URI + "/" + info.id);
                Cursor cursor = getContentResolver().query(bottleUri, null, null, null, null);
                if (cursor == null) {
                    Log.d(TAG, "No cursor for deleting item!");
                    return true;
                }
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
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            case R.id.action_add_bottle:
                startActivityForResult(new Intent(this, BottleAddActivity.class), ACTIVITY_ADD_EDIT_BOTTLE);
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
        Log.d(TAG, "onActivityResult " + requestCode);
        switch (requestCode) {
            case ACTIVITY_CHOOSE_FILE:
                if (resultCode == RESULT_OK) {
                    Log.d(TAG, "Import file selected: " + data.getData());
                    ImportAsyncTask importTask = new ImportAsyncTask(this);
                    importTask.execute(data.getData());
                }
            case ACTIVITY_BOTTLE_DETAILS:
                // Here we expect same results as for add/edit bottle. Fallthru
            case ACTIVITY_ADD_EDIT_BOTTLE:
                if (resultCode == RESULT_OK && data != null) {
                    HashMap<String, String> hashMap = (HashMap<String, String>)data.getSerializableExtra("data");
                    db.collection("bottles").add(hashMap).
                            addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "Saved to firebird!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "Save to firebird failed: " + e);
                                }
                            });
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
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

    public void refreshBottlesList() {
        adapter.getFilter().filter("");
        adapter.notifyDataSetChanged();
    }
}
