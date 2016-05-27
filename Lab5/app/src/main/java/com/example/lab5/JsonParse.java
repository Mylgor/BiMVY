package com.example.lab5;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import Data.Data;

public class JsonParse extends AppCompatActivity {

    static String REQUEST_URL = "http://api.openweathermap.org/data/2.5/forecast?q=Yoshkar-Ola&appid=e05006793291b7a7553633a16983ea36";
    private static String TAG_LIST = "list";
    private static String NAME_CITY = "Yoshkar-Ola";
    private static String TAG_TEMP = "temp";
    private static String TAG_PRESSURE = "pressure";
    private static String TAG_WIND = "wind";
    private static String TAG_DATE = "dt_txt";
    private static String TAG_WEATHER = "weather";
    private static String TAG_MAIN = "main";
    private static String TAG_SPEED = "speed";
    private static String TAG_DESC = "description";

    ArrayList<Data> arrData;
    TextView txtView;
    Button btnParse, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_parse);

        arrData = new ArrayList<Data>();
        txtView = (TextView) findViewById(R.id.textView);
        btnBack = (Button) findViewById(R.id.btnBackJActiv);
        btnParse = (Button) findViewById(R.id.btnParseJActiv);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonParse.this.finish();
            }
        });

        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ParseJSONTask().execute();
            }
        });
    }

    private class ParseJSONTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(REQUEST_URL);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            }catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }

        @Override
        protected void onPostExecute(String strJSON) {
            super.onPostExecute(strJSON);
            try {
                JSONObject jsonObject = new JSONObject(strJSON);
                JSONArray listJSON = jsonObject.getJSONArray(TAG_LIST);

                for (int i = 0; i < listJSON.length();i++){
                    Data data = new Data();
                    data.city = NAME_CITY;

                    JSONObject oneD = listJSON.getJSONObject(i);
                    data.date = oneD.getString(TAG_DATE);

                    JSONObject main = oneD.getJSONObject(TAG_MAIN);
                    data.temp = main.getString(TAG_TEMP);
                    data.pressure = main.getString(TAG_PRESSURE);

                    JSONObject wind = oneD.getJSONObject(TAG_WIND);
                    data.windSpeed = wind.getString(TAG_SPEED);

                    JSONArray weather = oneD.getJSONArray(TAG_WEATHER);
                    JSONObject weatherObj = weather.getJSONObject(0);

                    data.weather = weatherObj.getString(TAG_MAIN);
                    data.weatherDescription = weatherObj.getString(TAG_DESC);

                    arrData.add(data);
                }
                String concatStr = "";
                for (Data data : arrData){
                    concatStr += data.toString();
                }
                txtView.setText(concatStr);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
