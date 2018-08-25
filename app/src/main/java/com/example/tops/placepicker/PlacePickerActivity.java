package com.example.tops.placepicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PlacePickerActivity extends AppCompatActivity {
    private String placename, address;
    private double latitude, longitude;
    private EditText edtPlaceName, edtPlaceAddress;
    private MapView mapView;
    NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placepicker);
        initMap(savedInstanceState);
    }

    private void initMap(Bundle savedInstanceState) {
        Intent intent = getIntent();
        latitude = Double.parseDouble(intent.getStringExtra("latitude"));
        longitude = Double.parseDouble(intent.getStringExtra("longitude"));
        placename = intent.getStringExtra("placename");
        address = intent.getStringExtra("address");
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        edtPlaceName = (EditText) findViewById(R.id.edtPlaceName);
        edtPlaceAddress = (EditText) findViewById(R.id.edtPlaceAddres);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nsv);
        edtPlaceName.setText(placename);
        edtPlaceAddress.setText(address);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng latLng = new LatLng(latitude, longitude);
                googleMap.addMarker(new MarkerOptions().position(latLng).title(placename));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(16).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        mapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        nestedScrollView.requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        nestedScrollView.requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return true;
            }
        });
    }

}
