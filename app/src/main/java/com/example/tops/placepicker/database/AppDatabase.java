package com.example.tops.placepicker.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.tops.placepicker.dao.PlacesDao;
import com.example.tops.placepicker.model.PlaceData;


@Database(entities = {PlaceData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "app_db";
    public abstract PlacesDao orderDao();
}
