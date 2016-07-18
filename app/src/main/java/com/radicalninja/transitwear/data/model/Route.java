package com.radicalninja.transitwear.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.radicalninja.transitwear.data.db.TransitDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = TransitDB.class)
public class Route extends BaseModel implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this._id);
        dest.writeString(this.routeId);
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
        dest.writeString(this.longName);
        dest.writeString(this.shortName);
        dest.writeInt(this.colorHex);
    }

    protected Route(Parcel in) {
        this._id = in.readInt();
        this.routeId = in.readString();
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : Type.values()[tmpType];
        this.longName = in.readString();
        this.shortName = in.readString();
        this.colorHex = in.readInt();
    }

    public static final Parcelable.Creator<Route> CREATOR = new Parcelable.Creator<Route>() {
        @Override
        public Route createFromParcel(Parcel source) {
            return new Route(source);
        }

        @Override
        public Route[] newArray(int size) {
            return new Route[size];
        }
    };
}
