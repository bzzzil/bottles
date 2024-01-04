package io.bzzzil.bottles;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import io.bzzzil.bottles.database.BottleDocument;
import io.bzzzil.bottles.imports.ImportAsyncTask;


public class BottlesListActivity extends AppCompatActivity implements EventListener<QuerySnapshot> {
    private static final String TAG = "BottlesListActivity";

    /**
     * Name for shared preferences
     */
    private static final String PREFS = "bottles";
    private static final String PREFS_SEARCH = "search";

    private EditText searchBox;

    public static final String DB_BOTTLES = "bottles";
    /**
     * Remote Firebase collection reference
     */
    private final CollectionReference bottlesCollection = FirebaseFirestore.getInstance().collection(DB_BOTTLES);
    /**
     * Local storage of Firebase bottle documents
     */
    private final ArrayList<BottleDocument> bottlesCollectionList = new ArrayList<>();

    public static final int ACTIVITY_CHOOSE_FILE = 1;
    public static final int ACTIVITY_ADD_EDIT_BOTTLE = 2;
    public static final int ACTIVITY_BOTTLE_DETAILS = 3;

    private BottlesListCustomAdapter adapter;

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
                adapter.getFilter().filter(s);
            }
        });

        ListView bottlesList = (ListView) findViewById(R.id.listViewBottles);
        adapter = new BottlesListCustomAdapter(this, bottlesCollectionList);
        bottlesList.setAdapter(adapter);
        bottlesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), BottleDetailsActivity.class);
                intent.putExtra("bottle", (BottleDocument)adapter.getItem(position));
                startActivityForResult(intent, ACTIVITY_BOTTLE_DETAILS);
            }
        });
        registerForContextMenu(bottlesList);

        // Add Firebase sync
        bottlesCollection.addSnapshotListener(this);

        // Restore shared preferences
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        String searchValue = prefs.getString(PREFS_SEARCH, null);
        if (searchValue != null) {
            searchBox.setText("");
            searchBox.append(searchValue);
        }
    }


    /**
     * Changes listener to sync Firebase <-> bottlesCollectionList <-> ListView
     * @param documentSnapshots
     * @param e
     */
    @Override
    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
        if (e != null) {
            Log.w(TAG, "listen:error", e);
            return;
        }

        for (DocumentChange dc : documentSnapshots.getDocumentChanges()) {
            switch (dc.getType()) {
                case ADDED:
                    Log.d(TAG, "FIREBIRD: New bottle: " + dc.getDocument().getId());
                    bottlesCollectionList.add(new BottleDocument(dc.getDocument()));
                    break;
                case MODIFIED:
                    Log.d(TAG, "FIREBIRD: Modified bottle: " + dc.getDocument().getId());
                    BottleDocument newDoc = new BottleDocument(dc.getDocument());
                    int pos = bottlesCollectionList.indexOf(newDoc);
                    bottlesCollectionList.set(pos, newDoc);
                    break;
                case REMOVED:
                    Log.d(TAG, "FIREBIRD: Removed bottle: " + dc.getDocument().getId());
                    BottleDocument removedDoc = new BottleDocument(dc.getDocument());
                    bottlesCollectionList.remove(removedDoc);
                    break;
            }
            adapter.getFilter().filter(searchBox.getText());
        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.listViewBottles) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_bottle_details, menu);
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
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Log.d(TAG, "Context menu for item " + info.id);
        int itemId = item.getItemId();
        if (itemId == R.id.action_edit_bottle) {
            Intent intent = new Intent(getApplicationContext(), BottleAddActivity.class);
            intent.putExtra("bootle", bottlesCollectionList.get(info.position));
            startActivityForResult(intent, ACTIVITY_ADD_EDIT_BOTTLE);
            return true;
        } else if (itemId == R.id.action_delete_bottle) {
            final BottleDocument docToDelete = bottlesCollectionList.get(info.position);
            String text = getString(R.string.alert_delete);
            text = String.format(text, docToDelete.getData().getTitle());
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.menu_action_delete_bottle))
                    .setMessage(text)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            bottlesCollection.document(docToDelete.getId()).delete();
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
            return true;
        }
        return super.onContextItemSelected(item);
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
        if (id == R.id.action_statistics) {
            startActivity(new Intent(this, StatisticsActivity.class));
            return true;
        } else if (id == R.id.action_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        } else if (id == R.id.action_add_bottle) {
            startActivityForResult(new Intent(this, BottleAddActivity.class), ACTIVITY_ADD_EDIT_BOTTLE);
            return true;
        } else if (id == R.id.action_delete_all) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.menu_action_delete_all))
                    .setMessage(getString(R.string.alert_delete_all))
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO: delete all
                            Toast.makeText(BottlesListActivity.this, getString(R.string.toast_all_bottles_deleted), Toast.LENGTH_LONG).show();
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
        } else if (id == R.id.action_import_bottles) {
            Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
            chooseFile.setType("*/*");
            Intent intent = Intent.createChooser(chooseFile, "Choose a file for import");
            startActivityForResult(intent, ACTIVITY_CHOOSE_FILE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult " + requestCode);
        switch (requestCode) {
            case ACTIVITY_CHOOSE_FILE:
                if (resultCode == RESULT_OK) {
                    Log.d(TAG, "Import file selected: " + data.getData());
                    ImportAsyncTask importTask = new ImportAsyncTask(this, bottlesCollection);
                    importTask.execute(data.getData());
                }
                break;
            case ACTIVITY_BOTTLE_DETAILS:
                // Here we expect same results as for add/edit bottle. Fallthru
            case ACTIVITY_ADD_EDIT_BOTTLE:
                if (resultCode == RESULT_OK && data != null) {
                    BottleDocument bottleDoc = (BottleDocument) data.getSerializableExtra("bottle");
                    if (bottleDoc.getId() == null) {
                        // New bottle
                        bottlesCollection.add(bottleDoc.getData()).
                                addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Log.e(TAG, "Save new bottle to firebase failed: " + e);
                                    }
                                });
                    } else {
                        // Existing bottle
                        bottlesCollection.document(bottleDoc.getId()).set(bottleDoc.getData()).
                                addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Log.e(TAG, "Save bottle to firebase failed: " + e);
                                    }
                                });
                    }
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
