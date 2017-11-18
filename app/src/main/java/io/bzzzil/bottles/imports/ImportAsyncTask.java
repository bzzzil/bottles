package io.bzzzil.bottles.imports;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;

import java.io.InputStream;

import io.bzzzil.bottles.BottlesListActivity;
import io.bzzzil.bottles.R;

/**
 * Asynchronous task for importing data from given URI
 */
public class ImportAsyncTask extends AsyncTask<Object, Void, Object> {

    private final BottlesListActivity activity;

    private final ProgressDialog dialog;

    private final CollectionReference colRef;


    public ImportAsyncTask(BottlesListActivity activity, CollectionReference colRef) {
        this.activity = activity;
        this.colRef = colRef;
        this.dialog = new ProgressDialog(activity);
    }

    @Override
    protected void onPreExecute() {
        this.dialog.setMessage(this.activity.getString(R.string.dialog_text_importing));
        this.dialog.show();
    }

    @Override
    protected void onPostExecute(Object message) {
        if (dialog.isShowing()) {
            this.dialog.dismiss();
        }
        Toast.makeText(this.activity, (String)message, Toast.LENGTH_LONG).show();
    }

    protected Object doInBackground(Object[] params) {
        String message;
        try {
            InputStream stream = this.activity.getContentResolver().openInputStream((Uri) params[0]);

            CsvImport importer = new CsvImport();
            int importedCount = importer.doImport(stream, colRef);
            message = this.activity.getResources().getQuantityString(R.plurals.toast_import_complete, importedCount, importedCount);
        } catch (Exception e) {
            message = e.getMessage();
        }

        return message;
    }
}
