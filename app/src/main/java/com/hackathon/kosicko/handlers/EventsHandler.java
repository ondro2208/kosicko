package com.hackathon.kosicko.handlers;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Martin on 11/26/2016.
 */
public class EventsHandler {

    private Document doc = null;
    private Elements elements;
    EventsDBHandler db;

    public EventsHandler(EventsDBHandler db) {
        this.db = db;
//        new RetrieveEventsTask().execute();
    }

    public class RetrieveEventsTask extends AsyncTask<String, Void, String> {

        private static final String HTML = "http://mickosice.sk/index.php?html=vyhladavanie.html";
        private ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... cities) {
            String content = "";
            try {
                doc = Jsoup.connect(HTML).get();
                elements = doc.select("table tbody tr td.STRUCNE");
            } catch (IOException e) {
                e.printStackTrace();
            }
                String[] name = new String[10000];
                String[] url = new String[10000];
                String[] date = new String[10000];
                String[] time = new String[10000];
                String[] place = new String[10000];
                String[] category = new String[10000];
                String el = "";
                Pattern pattern;
                Matcher matcher;
                int i = 0;

                for (Element element : elements) {

                    el = element.toString();

                    pattern = Pattern.compile("href=\"(.*?)\"");
                    matcher = pattern.matcher(el);
                    if (matcher.find()) {
                        i++;
                        url[i] = matcher.group(1);
                        Log.d("url", url[i]);

                    }

                    pattern = Pattern.compile("scrollTop;\">(.*?)<");
                    matcher = pattern.matcher(el);
                    if (matcher.find()) {
                        name[i] = matcher.group(1);
                        Log.d("name", name[i]);
                    }

                    pattern = Pattern.compile("\">([a-ž]+?)</td>$");
                    matcher = pattern.matcher(el);
                    if (matcher.find()) {
                        category[i] = matcher.group(1);
                        Log.d("category", category[i]);
                    }

                    pattern = Pattern.compile("\">([A-Ž].+)</td>$");
                    matcher = pattern.matcher(el);
                    if (matcher.find() && matcher.group(1)!=name[i]+"</a></b>") {
                        place[i] = matcher.group(1);
                        if (!place.equals(category[i]) && !place.equals(name[i] + "</a></b>"))
                            Log.d("place", place[i]);
                    }

                    pattern = Pattern.compile("([0-3][0-9]\\.[0-1][0-9]\\.)");
                    matcher = pattern.matcher(el);
                    if (matcher.find()) {
                        date[i] = matcher.group(1);
                        Log.d("date", date[i]);
                    }

                    pattern = Pattern.compile("([0-2][0-9]:[0-5][0-9])");
                    matcher = pattern.matcher(el);
                    if (matcher.find()) {
                        time[i] = matcher.group(1);
                        Log.d("time", time[i]);
                    }
                }

                for (int j = 1; j < i; j++) {
                    Events events = new Events(category[j], name[j], url[j], date[j], time[j], place[j]);
                    db.addAnotherEvents(events);
                }
                return null;

        }


        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);
//            if (data == null) {
//                //Toast.makeText(getApplicationContext(), "Connection error", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            // change activity
//            Intent intent = new Intent(getApplicationContext(), EventsHandler.class);
//            intent.putExtra("data", data);
//            startActivity(intent);
        }
    }
}
