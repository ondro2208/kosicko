package com.hackathon.kosicko.handlers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackathon.kosicko.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Martin on 11/27/2016.
 */

public class EventsListAdapter extends ArrayAdapter {
    private static final String TAG = "JSONAdapter";
    private final JSONObject[] data;
    private final Context context;


    public EventsListAdapter(Context context, JSONObject[] data) {
        super(context, R.layout.news_parent, data);
        this.context = context;

        this.data = data;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.events_parent, parent, false);
        JSONObject json = this.data[position];
        rowView.setTag(json);
        try {
            TextView tv;
            // title
            tv = (TextView) rowView.findViewById(R.id.event_title);
            tv.setText(json.getString("name"));
            // date
            tv = (TextView) rowView.findViewById(R.id.event_date);
            tv.setText(json.getString("date") + " " + json.getString("time"));
            // place
            tv = (TextView) rowView.findViewById(R.id.event_place);
            tv.setText(json.getString("place"));
            //category
            tv = (TextView) rowView.findViewById(R.id.event_category);
            tv.setText(json.getString("category"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowView;
    }
}
