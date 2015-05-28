package io.bzzzil.bottles;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import io.bzzzil.bottles.database.BottlesTable;

public class BottlesListCustomAdapter extends SimpleCursorAdapter {

    private Context context;
    private int layout;
    private Cursor cursor;
    private final LayoutInflater inflater;

    public BottlesListCustomAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.layout = layout;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.cursor = c;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        //return super.newView(context, cursor, parent);
        return  inflater.inflate(layout, null);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);

        TextView viewTitle = (TextView)view.findViewById(R.id.bottle_title);
        TextView viewDetails = (TextView)view.findViewById(R.id.bottle_detais);

        String title = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_TITLE));
        String type = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_TYPE));
        String country = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_COUNTRY));
        int volume = cursor.getInt(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_VOLUME));
        int degree = cursor.getInt(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_DEGREE));

        StringBuilder details = new StringBuilder();
        details.append(type);

        if (!country.isEmpty()) {
            if (details.length() > 0) {
                details.append(", ");
            }
            details.append(country);
        }

        if (volume != 0) {
            if (details.length() > 0) {
                details.append(", ");
            }
            details.append(volume);
            details.append(" ");
            details.append(context.getString(R.string.volume_measure_ml));
        }

        if (degree != 0) {
            if (details.length() > 0) {
                details.append(", ");
            }
            details.append(degree);
            details.append(context.getString(R.string.degree_measure_percent));
        }


        viewTitle.setText(title);
        viewDetails.setText(details);
    }
}
