package com.example.tops.placepicker.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
@Entity(tableName = "places")
public class PlaceData {

    @ColumnInfo(name = "id")
    @PrimaryKey()
    private int id;
    @ColumnInfo(name = "pname")
    private String pname;
    @ColumnInfo(name = "paddress")
    private String paddress;
    @ColumnInfo(name = "longitude")
    private String longitude;
    @ColumnInfo(name = "latitude")
    private String latitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPaddress() {
        return paddress;
    }

    public void setPaddress(String paddress) {
        this.paddress = paddress;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
