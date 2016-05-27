package com.example.lab8;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Point;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    public final static String EXTRA_MESSAGE = "com.example.lab8.MESSAGE";

    private ArrayList<Marker> allMarkers = new ArrayList<Marker>();
    private EditText txtLatitude, txtLongtitude;
    private Button btnSetMark;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLatitude = (EditText)findViewById(R.id.txtLatitude);
        txtLongtitude = (EditText)findViewById(R.id.txtLongtitude);
        btnSetMark = (Button)findViewById(R.id.btnSetMark);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnSetMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtLatitude.getText().toString() != "" && txtLongtitude.getText().toString() != "")
                {
                    double latitude = Integer.parseInt(txtLatitude.getText().toString());
                    double longtitude = Integer.parseInt(txtLongtitude.getText().toString());
                    createNewMark(latitude, longtitude);
                }
            }
        });
    }

    //добавление марки
    private void createNewMark(double latitude, double longtitude)
    {
        LatLng position = new LatLng(latitude, longtitude);
        allMarkers.add(mMap.addMarker(new MarkerOptions().position(position).title("mark\n" + position.latitude + ", " + position.longitude)));

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker m : allMarkers) {
            builder.include(m.getPosition());
        }
        LatLngBounds bounds = builder.build();
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(com.google.android.gms.maps.model.Marker marker) {
                if (marker != null) {
                    Intent intent = new Intent(MainActivity.this, DataMark.class);
                    intent.putExtra(EXTRA_MESSAGE, marker.getTitle());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng losAngeles = new LatLng(34, -118.2436800);
        allMarkers.add(mMap.addMarker(new MarkerOptions().position(losAngeles).title("Marker in Los Angeles \n" + losAngeles.latitude + ", " + losAngeles.longitude)));

        LatLng newYork = new LatLng(40.7142700, -74.0059700);
        allMarkers.add(mMap.addMarker(new MarkerOptions().position(newYork).title("Marker in New York \n" + newYork.latitude + ", " + newYork.longitude)));


        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (Marker m : allMarkers) {
                    builder.include(m.getPosition());
                }
                LatLngBounds bounds = builder.build();
                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
            }
        });
    }
}