package com.hackathon.kosicko.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.hackathon.kosicko.R;
import com.hackathon.kosicko.handlers.Events;
import com.hackathon.kosicko.handlers.EventsDBHandler;
import com.hackathon.kosicko.handlers.EventsHandler;
import com.hackathon.kosicko.handlers.EventsListAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class EventsActivity extends AppCompatActivity {

    ListView view;
    JSONObject[] finalObjects = new JSONObject[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        view = (ListView) findViewById(R.id.news_list_view);

        EventsDBHandler db = new EventsDBHandler(this);
        EventsHandler eventsHandler = new EventsHandler(db);
//        db.dropTable();
//        db.createTable();
        List<Events> events = db.getAllEvents();
        String data = null;


        int i = 0;

        for(Events event : events){
            JSONObject jsonObj = new JSONObject();
            StringBuilder log = new StringBuilder();
            log.append(event.getName());
            ////////////////////////  ALSO CREATING JSON THATS WHY THE CODE IS DUMB.. LAST MINUTES TO PRESENTATION SO....
            try {
                jsonObj.put("name", event.getName());

            log.append("\n");
            log.append(event.getPlace());
            jsonObj.put("place", event.getPlace());
            log.append("\n");
            log.append(event.getDate());
            jsonObj.put("date", event.getDate());
            log.append(getResources().getString(R.string.at));
            log.append(event.getTime());
            jsonObj.put("time", event.getTime());
            log.append("\n(");
            log.append(event.getCategory());
            jsonObj.put("category", event.getCategory());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ////////////////////////////////
            log.append(")\n");
            log.append(event.getUrl());
            Log.d("Event", log.toString());
            data = log.toString();

            if(i > 99)
                return;
            this.finalObjects[i] = jsonObj;

                    i++;

            ListView lv = (ListView) findViewById(R.id.events_list_view);
            EventsListAdapter adapter = new EventsListAdapter(this, this.finalObjects);
            lv.setAdapter(adapter);

        }
    }


}
