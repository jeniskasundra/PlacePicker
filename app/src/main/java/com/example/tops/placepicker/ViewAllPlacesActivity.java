package com.example.tops.placepicker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tops.placepicker.adapter.PlacesAdapter;
import com.example.tops.placepicker.model.SeparatorDecoration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ViewAllPlacesActivity extends AppCompatActivity {
    private RecyclerView rvPlaces;
    private PlacesAdapter placesAdapter;
    private Toolbar toolbar;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_places);
        bindView();
        init();
        addListner();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAdapters();
    }

    private void bindView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rvPlaces = (RecyclerView) findViewById(R.id.rvPlaces);
    }

    private void init() {
        rvPlaces.setLayoutManager(new LinearLayoutManager(this));
        SeparatorDecoration decoration = new SeparatorDecoration(this, Color.GRAY, 1.5f);
        rvPlaces.addItemDecoration(decoration);
    }

    private void setAdapters() {
        Log.d("DATA", "==" + MyApplication.getInstance().getDB().orderDao().getAllPlaces().size());
        placesAdapter = new PlacesAdapter(ViewAllPlacesActivity.this, MyApplication.getInstance().getDB().orderDao().getAllPlaces());
        rvPlaces.setAdapter(placesAdapter);
    }

    private void addListner() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

}
