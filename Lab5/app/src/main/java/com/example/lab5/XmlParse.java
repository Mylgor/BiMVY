package com.example.lab5;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import Data.Data;

public class XmlParse extends AppCompatActivity {

    protected static final String REQUEST_URL = "http://api.openweathermap.org/data/2.5/forecast?q=Yoshkar-Ola&mode=xml&appid=e05006793291b7a7553633a16983ea36";
    private static String NAME_CITY = "Yoshkar-Ola";
    private static String TAG_TEMP = "temperature";
    private static String TAG_PRESSURE = "pressure";
    private static String TAG_WIND_SPEED = "windSpeed";
    private static String TAG_TIME = "time";
    private static String TAG_WEATHER = "symbol";
    private static String TAG_CLOUDS = "clouds";

    private Button btnBack, btnParse;
    private TextView txtView;
    private ArrayList<Data> arrData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_parse);

        btnBack = (Button) findViewById(R.id.btnBackX);
        btnParse = (Button) findViewById(R.id.btnXmlRarseX);
        txtView = (TextView) findViewById(R.id.textView2);
        arrData = new ArrayList<Data>();

        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ParseData().execute();
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XmlParse.this.finish();
            }
        });
    }

    class ParseData extends AsyncTask<Context, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Context... params) {
            InputStream is = null;
            InputStreamReader reader = null;
            try {
                URL requestUrl = new URL(REQUEST_URL);
                HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
                is = connection.getInputStream();

                if (null != is) {
                    //parse
                    XmlPullParser mParser = Xml.newPullParser();
                    reader = new InputStreamReader(is);
                    mParser.setInput(reader);

                    Data data = new Data();
                    String name;
                    name = mParser.getName();

                    while (mParser.getEventType() != XmlPullParser.END_DOCUMENT) {
                        if ((mParser.getEventType() == XmlPullParser.START_TAG) && (mParser.getName().equalsIgnoreCase(TAG_TIME))){
                            data.date = mParser.getAttributeValue(null, "from");
                        }
                        else if ((mParser.getEventType() == XmlPullParser.START_TAG) && (mParser.getName().equalsIgnoreCase(TAG_WEATHER))){
                            data.weather = mParser.getAttributeValue(null, "name");
                        }
                        else if ((mParser.getEventType() == XmlPullParser.START_TAG) && (mParser.getName().equalsIgnoreCase(TAG_WIND_SPEED))){
                            data.windSpeed = mParser.getAttributeValue(null, "mps");
                        }
                        else if ((mParser.getEventType() == XmlPullParser.START_TAG) && (mParser.getName().equalsIgnoreCase(TAG_CLOUDS))){
                            data.weatherDescription = mParser.getAttributeValue(null, "value");
                        }
                        else if ((mParser.getEventType() == XmlPullParser.START_TAG) && (mParser.getName().equalsIgnoreCase(TAG_PRESSURE))){
                            data.pressure = mParser.getAttributeValue(null, "value");
                        }
                        else if ((mParser.getEventType() == XmlPullParser.START_TAG) && (mParser.getName().equalsIgnoreCase(TAG_TEMP))){
                            data.temp = mParser.getAttributeValue(null, "value");
                        }
                        else if ((mParser.getEventType() == XmlPullParser.END_TAG) && (mParser.getName().equalsIgnoreCase(TAG_TIME))){
                            data.city = NAME_CITY;
                            arrData.add(data);
                            data = new Data();
                        }
                        mParser.next();
                    }
                }

            } catch (Exception mfe) {

            } finally {
                try {
                    if (null != is)
                        is.close();
                } catch (Exception e){}
                try {
                    if (null != reader)
                        reader.close();
                } catch (Exception e){}
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (null != txtView) {
                String concatStr = "";
                for (Data data : arrData){
                    concatStr += data.toString();
                }
                txtView.setText(concatStr);
            }
        }
    }
}
