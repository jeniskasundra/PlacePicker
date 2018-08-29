package com.example.tops.placepicker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tops.placepicker.adapter.PlacesAdapter;
import com.example.tops.placepicker.model.PlaceData;
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
    private Button btnSavePlace;
    NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placepicker);
        bindView(savedInstanceState);
        init();
        addListner();
    }

    private void bindView(Bundle savedInstanceState) {

        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        edtPlaceName = (EditText) findViewById(R.id.edtPlaceName);
        edtPlaceAddress = (EditText) findViewById(R.id.edtPlaceAddres);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nsv);
        btnSavePlace = (Button) findViewById(R.id.btnSave);
    }


    private void init() {
        Intent intent = getIntent();
        latitude = Double.parseDouble(intent.getStringExtra("latitude"));
        longitude = Double.parseDouble(intent.getStringExtra("longitude"));
        placename = intent.getStringExtra("placename");
        address = intent.getStringExtra("address");
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
    }

    private void addListner() {
        mapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        nestedScrollView.requestDisallowInterceptTouchEvent(false);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        nestedScrollView.requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return true;
            }
        });

        btnSavePlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pName = edtPlaceName.getText().toString();
                String pAddress = edtPlaceAddress.getText().toString();
                if (pName.equals("")) {
                    edtPlaceName.setError("Please enter place name");
                } else if (pAddress.equals("")) {
                    edtPlaceAddress.setError("Please enter place address");
                } else {
                    addItemDialog(pName, pAddress);
                }

            }
        });
    }

    private void addItemDialog(final String pName, final String pAddress) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(
                PlacePickerActivity.this, R.style.AppAlertDialog);
        dialog.setTitle("Add Item");
        dialog.setMessage("Are you sure you want to add this place?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String latitude = String.valueOf(PlacePickerActivity.this.latitude);
                String longitude = String.valueOf(PlacePickerActivity.this.longitude);
                PlaceData placeData = new PlaceData();
                placeData.setPname(pName);
                placeData.setPaddress(pAddress);
                placeData.setLatitude(latitude);
                placeData.setLongitude(longitude);
                MyApplication.getInstance().getDB().orderDao().insertAll(placeData);

                Toast.makeText(PlacePickerActivity.this, "Place add successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PlacePickerActivity.this,ViewAllPlacesActivity.class));
                finish();

            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }

}
