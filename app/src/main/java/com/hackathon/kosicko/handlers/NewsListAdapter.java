package com.hackathon.kosicko.handlers;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.LayoutInflater;

import com.hackathon.kosicko.R;
import com.squareup.picasso.Picasso;

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
            super(context, R.layout.news_parent, data);
            this.context = context;


            this.data = data;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.news_parent, parent, false);
            JSONObject json = this.data[position];
            rowView.setTag(json);
            try {
                TextView tv;
                // title
                tv = (TextView) rowView.findViewById(R.id.news_title);
                tv.setText(json.getString("TITLE"));
                // date
                tv = (TextView) rowView.findViewById(R.id.news_date);
                tv.setText(json.getString("DATE"));
                // image
                ImageView image = (ImageView) rowView.findViewById(R.id.news_image);
                String newsimage = json.getString("PICTURE");
                Picasso.with(context).load(newsimage)
                        .centerCrop()
                        .resize(100, 100)
                        .into(image);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return rowView;
        }
    }


