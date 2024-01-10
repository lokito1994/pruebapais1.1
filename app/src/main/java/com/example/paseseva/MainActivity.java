package com.example.paseseva;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private ListView listViewCountries;
    private Paisadaptador adapter;
    private ArrayList<nombrepais> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewCountries = findViewById(R.id.listViewCountries);
        countryList = new ArrayList<>();
        adapter = new Paisadaptador(this, countryList);
        listViewCountries.setAdapter(adapter);
        new FetchCountryData().execute();
    }

    private class FetchCountryData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://www.geognos.com/api/en/countries/info/all.json");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                String json = stringBuilder.toString();
                JSONObject jsonObject = new JSONObject(json);
                JSONObject countries = jsonObject.getJSONObject("Results");
                Iterator<String> keys = countries.keys();
                while (keys.hasNext()) {
                    String countryCode = keys.next();
                    JSONObject countryInfo = countries.getJSONObject(countryCode);

                    String countryName = countryInfo.getString("Name");
                    String countryCapital = countryInfo.getJSONObject("Capital").getString("Name");

                    String flagURL = "http://www.geognos.com/api/en/countries/flag/" + countryCode + ".png";

                    nombrepais country = new nombrepais(countryName, countryCapital, flagURL);
                    countryList.add(country);
                }
                inputStream.close();
                connection.disconnect();

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }
}