package com.hackathon.kosicko.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hackathon.kosicko.R;
import com.hackathon.kosicko.handlers.NewsListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class NewsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);




        //putFakeDataToListView();

        //String url = "https://slack-redir.net/link?url=http%3A%2F%2Fs.sme.sk%2Fr-rss%2F20392432%2Fkosice.korzar.sme.sk%2Ftana-pauhofova-zakladom-uspechu-je-neporovnavat.html&v=3";
        //openPage(url);




        RetrieveNewsTask task = new RetrieveNewsTask();
        task.execute(null, null, null, null);    //zatial null
    }

    private void openPage(String URL) {

        // Missing URL will cause crash!
        Uri uri = Uri.parse(URL);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


    private void putFakeDataToListView() {

        // putting fake data
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("TITLE", "V Kosiciach dnes usmrtili 23 rocne dievca. Utocnik bol..");
            jsonObj.put("DATE", "26-NOV 2016  23:46");
            jsonObj.put("PICTURE", "https://parsefiles.back4app.com/OKppgL69MFQfC3mWYxrgKTGCjotMd9T6c9Vc7cV0/e35b1fc80fdbdd240d6d5f01fd9cd5fb_23775833.jpg");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject[] data = new JSONObject[2];

        data[0] = jsonObj;

        JSONObject js = new JSONObject();
        try {
            js.put("TITLE", "Počas letného festivalu sa našiel vzácny drahokam. Jeho cena je nevyčísliteľná!");
            js.put("DATE", "27-NOV 2016  2:12");
            js.put("PICTURE", "https://www.sperky-a-diamanty.sk/images/diamonds/emerald.jpg");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        data[1] = js;


        NewsListAdapter adapter = new NewsListAdapter(getApplicationContext(), data);

        ListView lv = (ListView) findViewById(R.id.news_list_view);

        lv.setAdapter(adapter);

    }



    // New class to get data from database

    public class RetrieveNewsTask extends AsyncTask<String, Void, String> {

        private static final String APPID = "OKppgL69MFQfC3mWYxrgKTGCjotMd9T6c9Vc7cV0";
        private static final String WEATHER_URL = "https://api.rss2json.com/v1/api.json?rss_url=http%3A%2F%2Frss.sme.sk%2Frss%2Frss.asp%3Fsek%3Dreg_ke";
        private static final String TAG = "RetrieveNewsTask";
        private ProgressDialog progress;

        private JSONObject[] finalObject;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            this.progress = new ProgressDialog(NewsActivity.this);
            this.progress.setMessage("Downloading...");
            //this.progress.show();
        }

        @Override
        protected String doInBackground(String... cities) {
            try {
                URL url = new URL("https://api.rss2json.com/v1/api.json?rss_url=http%3A%2F%2Frss.sme.sk%2Frss%2Frss.asp%3Fsek%3Dreg_ke");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                Log.i(TAG, String.format("Connecting to %s", url.toString()));
                Log.i(TAG, String.format("HTTP Status Code: %d", connection.getResponseCode()));

                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return null;
                }

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + '\n');
                }

                String data = stringBuilder.toString();

                parseStringDataToJson(data);

                Log.i(TAG, String.format("GET: %s", stringBuilder.toString()));

                return stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException exc){
                exc.printStackTrace();
            }

            return null;
        }

        private void parseStringDataToJson(String data) throws JSONException {

            JSONObject jsonObj = new JSONObject(data);

            JSONArray arr = new JSONArray(jsonObj.getString("items"));

            this.finalObject = new JSONObject[arr.length()];

            for(int i = 0; i < arr.length(); i++){
                JSONObject js = new JSONObject();
                js.put("TITLE", arr.getJSONObject(i).getString("title"));

                // try catch to separate today news from yesterday's news and show that info
                try {
                    String s = arr.getJSONObject(i).getString("pubDate");
                    DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                    Date result = df.parse(s);

                    int hours = result.getHours();

                    if(result.getDay() == new Date().getDay() + 2)
                        js.put("DATE", "Dnes o " + hours + "h");
                    else
                        js.put("DATE", "Včera o " + hours + "h");
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                js.put("PICTURE", arr.getJSONObject(i).getJSONObject("enclosure").getString("link"));
                js.put("LINK", arr.getJSONObject(i).getString("link"));

                this.finalObject[i] = js;



            }



        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);

            this.progress.dismiss();

            if (data == null) {
                Toast.makeText(getApplicationContext(), "Connection error", Toast.LENGTH_SHORT).show();
                return;
            }

            NewsListAdapter adapter = new NewsListAdapter(NewsActivity.super.getApplicationContext(), this.finalObject);

            ListView lv = (ListView) findViewById(R.id.news_list_view);

            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> Parent, View view, int position, long id) {
                    try {
                        openPage(finalObject[position].getString("LINK"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            // change activity
            //Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
            //intent.putExtra("data", data);
            //startActivity(intent);
        }

    }


}
