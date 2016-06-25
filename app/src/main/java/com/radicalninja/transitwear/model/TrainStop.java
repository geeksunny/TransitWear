package com.radicalninja.transitwear.model;

import com.radicalninja.transitwear.db.TrainDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.annotation.UniqueGroup;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = TrainDB.class,
        uniqueColumnGroups = {@UniqueGroup(groupNumber = 1, uniqueConflict = ConflictAction.FAIL)})
public class TrainStop extends BaseModel {

    @PrimaryKey
    @Unique(unique = false, uniqueGroups = 1)
    private int stopId;

    @Column
    @Unique(unique = false, uniqueGroups = 1)
    private char directionId;

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
    private boolean red;

    @Column
    private boolean blue;

    @Column
    private boolean green;

    @Column
    private boolean brown;

    @Column
    private boolean purple;

    @Column
    private boolean purpleExpress;

    @Column
    private boolean yellow;

    @Column
    private boolean pink;

    @Column
    private boolean orange;

    @Column
    private LatLong location;

    public boolean isBlue() {
        return blue;
    }

    public boolean isBrown() {
        return brown;
    }

    public char getDirectionId() {
        return directionId;
    }

    public boolean isGreen() {
        return green;
    }

    public boolean isAdaAccessible() {
        return isAdaAccessible;
    }

    public LatLong getLocation() {
        return location;
    }

    public int getMapId() {
        return mapId;
    }

    public boolean isOrange() {
        return orange;
    }

    public boolean isPink() {
        return pink;
    }

    public boolean isPurple() {
        return purple;
    }

    public boolean isPurpleExpress() {
        return purpleExpress;
    }

    public boolean isRed() {
        return red;
    }

    public String getStationDescription() {
        return stationDescription;
    }

    public String getStationName() {
        return stationName;
    }

    public int getStopId() {
        return stopId;
    }

    public String getStopName() {
        return stopName;
    }

    public boolean isYellow() {
        return yellow;
    }

    public void setBlue(boolean blue) {
        this.blue = blue;
    }

    public void setBrown(boolean brown) {
        this.brown = brown;
    }

    public void setDirectionId(char directionId) {
        this.directionId = directionId;
    }

    public void setGreen(boolean green) {
        this.green = green;
    }

    public void setAdaAccessible(boolean adaAccessible) {
        isAdaAccessible = adaAccessible;
    }

    public void setLocation(LatLong location) {
        this.location = location;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public void setOrange(boolean orange) {
        this.orange = orange;
    }

    public void setPink(boolean pink) {
        this.pink = pink;
    }

    public void setPurple(boolean purple) {
        this.purple = purple;
    }

    public void setPurpleExpress(boolean purpleExpress) {
        this.purpleExpress = purpleExpress;
    }

    public void setRed(boolean red) {
        this.red = red;
    }

    public void setStationDescription(String stationDescription) {
        this.stationDescription = stationDescription;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setStopId(int stopId) {
        this.stopId = stopId;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public void setYellow(boolean yellow) {
        this.yellow = yellow;
    }

    protected TrainStop() { }

    @Override
    public String toString() {
        return "TrainStop{" +
                "blue=" + blue +
                ", stopId=" + stopId +
                ", directionId=" + directionId +
                ", stopName='" + stopName + '\'' +
                ", stationName='" + stationName + '\'' +
                ", stationDescription='" + stationDescription + '\'' +
                ", mapId=" + mapId +
                ", isAdaAccessible=" + isAdaAccessible +
                ", red=" + red +
                ", green=" + green +
                ", brown=" + brown +
                ", purple=" + purple +
                ", purpleExpress=" + purpleExpress +
                ", yellow=" + yellow +
                ", pink=" + pink +
                ", orange=" + orange +
                ", location=" + location +
                '}';
    }

    public static class Builder {
        private boolean blue;
        private boolean brown;
        private char directionId;
        private boolean green;
        private boolean isAdaAccessible;
        private LatLong location;
        private int mapId;
        private boolean orange;
        private boolean pink;
        private boolean purple;
        private boolean purpleExpress;
        private boolean red;
        private String stationDescription;
        private String stationName;
        private int stopId;
        private String stopName;
        private boolean yellow;

        public Builder setBlue(String blue) {
            this.blue = Boolean.parseBoolean(blue);
            return this;
        }

        public Builder setBrown(String brown) {
            this.brown = Boolean.parseBoolean(brown);
            return this;
        }

        public Builder setDirectionId(String directionId) {
            this.directionId = directionId.charAt(0);
            return this;
        }

        public Builder setGreen(String green) {
            this.green = Boolean.parseBoolean(green);
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

        public Builder setOrange(String orange) {
            this.orange = Boolean.parseBoolean(orange);
            return this;
        }

        public Builder setPink(String pink) {
            this.pink = Boolean.parseBoolean(pink);
            return this;
        }

        public Builder setPurple(String purple) {
            this.purple = Boolean.parseBoolean(purple);
            return this;
        }

        public Builder setPurpleExpress(String purpleExpress) {
            this.purpleExpress = Boolean.parseBoolean(purpleExpress);
            return this;
        }

        public Builder setRed(String red) {
            this.red = Boolean.parseBoolean(red);
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

        public Builder setYellow(String yellow) {
            this.yellow = Boolean.parseBoolean(yellow);
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
            newStop.red = this.red;
            newStop.blue = this.blue;
            newStop.green = this.green;
            newStop.brown = this.brown;
            newStop.purple = this.purple;
            newStop.purpleExpress = this.purpleExpress;
            newStop.yellow = this.yellow;
            newStop.pink = this.pink;
            newStop.orange = this.orange;
            newStop.location = this.location;

            return newStop;
        }
    }
}
