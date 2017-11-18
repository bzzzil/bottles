package io.bzzzil.bottles;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import io.bzzzil.bottles.database.Bottle;
import io.bzzzil.bottles.database.BottleDocument;

class BottlesListCustomAdapter extends BaseAdapter {


    private final LayoutInflater inflater;
    private final ArrayList<BottleDocument> objects;
    private final Context context;

    public BottlesListCustomAdapter(Context context, ArrayList<BottleDocument> objects) {
        this.context = context;
        this.objects = objects;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.bottle_row, parent, false);
        }

        Bottle bottle = objects.get(position).getData();

        TextView viewTitle = (TextView)view.findViewById(R.id.bottle_title);
        TextView viewDetails = (TextView)view.findViewById(R.id.bottle_details);

        // Build details string
        SpannableStringBuilder details = new SpannableStringBuilder();
        details.append(bottle.getType() == null ? "" : bottle.getType());

        if (bottle.getCountry() != null && !bottle.getCountry().isEmpty()) {
            if (details.length() > 0) {
                details.append(", ");
            }
            /*int flag_resource = cursor.getInt(cursor.getColumnIndexOrThrow(CountriesTable.COLUMN_FLAG_RESOURCE_ID));
            if ( flag_resource != 0 && flag_resource != R.drawable.no_flag ) {
                // Flag resource exists and is not "no flag"
                details.append(" ", new ImageSpan(context, flag_resource, DynamicDrawableSpan.ALIGN_BASELINE), 0);
            }*/
            details.append(" ");
            details.append(bottle.getCountry());
        }

        if (bottle.getVolume() != 0) {
            if (details.length() > 0) {
                details.append(", ");
            }
            details.append(String.valueOf(bottle.getVolume()));
            details.append(" ");
            details.append(context.getString(R.string.volume_measure_ml));
        }

        if (bottle.getDegree() != 0) {
            if (details.length() > 0) {
                details.append(", ");
            }
            details.append(String.valueOf(bottle.getDegree()));
            details.append(context.getString(R.string.degree_measure_percent));
        }

        viewTitle.setText(bottle.getTitle() != null ? bottle.getTitle() : "");
        viewDetails.setText(details);

        return view;
    }
}
