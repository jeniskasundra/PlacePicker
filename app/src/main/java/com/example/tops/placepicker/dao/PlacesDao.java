package com.example.tops.placepicker.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tops.placepicker.model.PlaceData;

import java.util.List;

@Dao
public interface PlacesDao {


    @Query("SELECT * FROM `places`")
    List<PlaceData> getAllPlaces();

    @Query("SELECT * FROM `places` WHERE id=:id")
    PlaceData getPlaces(int id);

    @Insert
    void insertAll(PlaceData placeData);

    @Update
    void update(PlaceData placeData);

    @Delete
    void delete(PlaceData placeData);

    @Query("DELETE FROM `places`")
    void deleteTable();
}
