package com.radicalninja.transitwear.data.model;

import com.radicalninja.transitwear.data.db.TransitDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = TransitDB.class)
public class Route extends BaseModel{

    @PrimaryKey(autoincrement = true)
    private int _id;
    @Column
    @Unique
    private String routeId;
    @Column
    private Type type;
    @Column
    private String longName;
    @Column
    private String shortName;
    @Column
    private int colorHex;

    public Route() {
        //
    }

    public Route(String routeId, String shortName, String longName, Type type, int colorHex) {
        this.routeId = routeId;
        this.shortName = shortName;
        this.longName = longName;
        this.type = type;
        this.colorHex = colorHex;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getColorHex() {
        return colorHex;
    }

    public void setColorHex(int colorHex) {
        this.colorHex = colorHex;
    }
}
