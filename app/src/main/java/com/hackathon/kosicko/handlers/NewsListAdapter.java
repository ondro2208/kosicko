package com.hackathon.kosicko.handlers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.LayoutInflater;

import com.hackathon.kosicko.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by petertelepcak on 26.11.2016.
 */

public class NewsListAdapter extends ArrayAdapter {
        private static final String TAG = "JSONAdapter";
        private final JSONObject[] data;
        private final Context context;


        public NewsListAdapter(Context context, JSONObject[] data) {
            //super(context, R.layout.row, data);
            this.context = context;
            this.data = data;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           // View rowView = inflater.inflate(R.layout.row, parent, false);
            JSONObject json = this.data[position];
            rowView.setTag(json);
            try {
                TextView tv;
                // username
                tv = (TextView) rowView.findViewById(R.id.makac_view);
                tv.setText(json.getString("TITLE"));
                // distance
                tv = (TextView) rowView.findViewById(R.id.distance_view);
                tv.setText(json.getString("DATE"));
                // duration
                tv = (TextView) rowView.findViewById(R.id.duration_view);
                tv.setText(json.getString("PICTURE"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return rowView;
        }
    }

}
