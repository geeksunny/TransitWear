package com.radicalninja.transitwear.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.radicalninja.transitwear.data.db.TransitDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.ManyToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.annotation.UniqueGroup;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = TransitDB.class,
        uniqueColumnGroups = {@UniqueGroup(groupNumber = 1, uniqueConflict = ConflictAction.FAIL)})
@ManyToMany(referencedTable = Route.class)
public class TrainStop extends BaseModel implements Stop, Parcelable {

    @PrimaryKey
    @Unique(unique = false, uniqueGroups = 1)
    private int stopId;

    @Column
    @Unique(unique = false, uniqueGroups = 1)
    private Direction directionId;

    @Column
    private String stopName;

    @Column
    private String stationName;

    @Column
    private String stationDescription;

    @Column
    private int mapId;

    @Column
    private boolean isAdaAccessible;

    @Column
    private LatLong location;

    @Override
    public Direction getDirectionId() {
        return directionId;
    }

    public boolean isAdaAccessible() {
        return isAdaAccessible;
    }

    @Override
    public LatLong getLocation() {
        return location;
    }

    @Override
    public int getMapId() {
        return mapId;
    }

    public String getStationDescription() {
        return stationDescription;
    }

    @Override
    public String getStationName() {
        return stationName;
    }

    @Override
    public int getStopId() {
        return stopId;
    }

    @Override
    public String getStopName() {
        return stopName;
    }

    public void setDirectionId(Direction directionId) {
        this.directionId = directionId;
    }

    public void setAdaAccessible(boolean adaAccessible) {
        isAdaAccessible = adaAccessible;
    }

    @Override
    public void setLocation(LatLong location) {
        this.location = location;
    }

    @Override
    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    @Override
    public void setStationDescription(String stationDescription) {
        this.stationDescription = stationDescription;
    }

    @Override
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Override
    public void setStopId(int stopId) {
        this.stopId = stopId;
    }

    @Override
    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    protected TrainStop() { }

    @Override
    public String toString() {
        return "TrainStop{" +
                "stopId=" + stopId +
                ", directionId=" + directionId +
                ", stopName='" + stopName + '\'' +
                ", stationName='" + stationName + '\'' +
                ", stationDescription='" + stationDescription + '\'' +
                ", mapId=" + mapId +
                ", isAdaAccessible=" + isAdaAccessible +
                ", location=" + location +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.stopId);
        dest.writeInt(this.directionId == null ? -1 : this.directionId.ordinal());
        dest.writeString(this.stopName);
        dest.writeString(this.stationName);
        dest.writeString(this.stationDescription);
        dest.writeInt(this.mapId);
        dest.writeByte(this.isAdaAccessible ? (byte) 1 : (byte) 0);
        dest.writeSerializable(this.location);
    }

    protected TrainStop(Parcel in) {
        this.stopId = in.readInt();
        int tmpDirectionId = in.readInt();
        this.directionId = tmpDirectionId == -1 ? null : Direction.values()[tmpDirectionId];
        this.stopName = in.readString();
        this.stationName = in.readString();
        this.stationDescription = in.readString();
        this.mapId = in.readInt();
        this.isAdaAccessible = in.readByte() != 0;
        this.location = (LatLong) in.readSerializable();
    }

    public static final Parcelable.Creator<TrainStop> CREATOR = new Parcelable.Creator<TrainStop>() {
        @Override
        public TrainStop createFromParcel(Parcel source) {
            return new TrainStop(source);
        }

        @Override
        public TrainStop[] newArray(int size) {
            return new TrainStop[size];
        }
    };

    public static class Builder {
        private Direction directionId;
        private boolean isAdaAccessible;
        private LatLong location;
        private int mapId;
        private String stationDescription;
        private String stationName;
        private int stopId;
        private String stopName;

        public Builder setDirectionId(String directionId) {
            this.directionId = Direction.fromCode(directionId.charAt(0));
            return this;
        }

        public Builder setIsAdaAccessible(String isAdaAccessible) {
            this.isAdaAccessible = Boolean.parseBoolean(isAdaAccessible);
            return this;
        }

        public Builder setLocation(String location) {
            this.location = new LatLong(location);
            return this;
        }

        public Builder setMapId(String mapId) {
            this.mapId = Integer.parseInt(mapId);
            return this;
        }

        public Builder setStationDescription(String stationDescription) {
            this.stationDescription = stationDescription;
            return this;
        }

        public Builder setStationName(String stationName) {
            this.stationName = stationName;
            return this;
        }

        public Builder setStopId(String stopId) {
            this.stopId = Integer.parseInt(stopId);
            return this;
        }

        public Builder setStopName(String stopName) {
            this.stopName = stopName;
            return this;
        }

        public TrainStop build() {
            final TrainStop newStop = new TrainStop();
            newStop.stopId = this.stopId;
            newStop.directionId = this.directionId;
            newStop.stopName = this.stopName;
            newStop.stationName = this.stationName;
            newStop.stationDescription = this.stationDescription;
            newStop.mapId = this.mapId;
            newStop.isAdaAccessible = this.isAdaAccessible;
            newStop.location = this.location;

            return newStop;
        }
    }
}
