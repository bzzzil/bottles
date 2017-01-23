package io.bzzzil.bottles;

import android.content.Context;
import android.database.Cursor;
import android.text.SpannableStringBuilder;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import io.bzzzil.bottles.database.BottlesTable;
import io.bzzzil.bottles.database.CountriesTable;

class BottlesListCustomAdapter extends SimpleCursorAdapter {

    private final int layout;
    private final LayoutInflater inflater;

    public BottlesListCustomAdapter(Context context, int layout, String[] from, int[] to) {
        super(context, layout, null, from, to, 0);
        this.layout = layout;
        this.inflater = LayoutInflater.from(context);
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
        TextView viewDetails = (TextView)view.findViewById(R.id.bottle_details);

        String title = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_TITLE));
        String type = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_TYPE));
        String country = cursor.getString(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_COUNTRY));
        int volume = cursor.getInt(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_VOLUME));
        int degree = cursor.getInt(cursor.getColumnIndexOrThrow(BottlesTable.COLUMN_DEGREE));

        SpannableStringBuilder details = new SpannableStringBuilder();
        details.append(type);

        if (!country.isEmpty()) {
            if (details.length() > 0) {
                details.append(", ");
            }
            int flag_resource = cursor.getInt(cursor.getColumnIndexOrThrow(CountriesTable.COLUMN_FLAG_RESOURCE_ID));
            if ( flag_resource != 0 && flag_resource != R.drawable.no_flag ) {
                // Flag resource exists and is not "no flag"
                details.append(" ", new ImageSpan(context, flag_resource, DynamicDrawableSpan.ALIGN_BASELINE), 0);
            }
            details.append(" ");
            details.append(country);
        }

        if (volume != 0) {
            if (details.length() > 0) {
                details.append(", ");
            }
            details.append(String.valueOf(volume));
            details.append(" ");
            details.append(context.getString(R.string.volume_measure_ml));
        }

        if (degree != 0) {
            if (details.length() > 0) {
                details.append(", ");
            }
            details.append(String.valueOf(degree));
            details.append(context.getString(R.string.degree_measure_percent));
        }


        viewTitle.setText(title);
        viewDetails.setText(details);
    }
}
