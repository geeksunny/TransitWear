package com.radicalninja.transitwear.data.api.train;

import com.google.gson.annotations.SerializedName;


public class ArrivalPrediction extends BaseInnerResponse {

    @SerializedName("stpId")
    String stopId;

    @SerializedName("rt")
    String route;

    @SerializedName("staId")
    String stationId;

    @SerializedName("isSch")
    String isSch;

    @SerializedName("staNm")
    String stationName;

    @SerializedName("isFlt")
    String isFault;

    @SerializedName("stpDe")
    String stopDescription;

    public String getStopId() {
        return this.stopId;
    }

    public void setStopId(String value) {
        this.stopId = value;
    }

    public String getRoute() {
        return this.route;
    }

    public void setRoute(String value) {
        this.route = value;
    }

    public String getStationId() {
        return this.stationId;
    }

    public void setStationId(String value) {
        this.stationId = value;
    }

    public String getIsSch() {
        return this.isSch;
    }

    public void setIsSch(String value) {
        this.isSch = value;
    }

    public String getStationName() {
        return this.stationName;
    }

    public void setStationName(String value) {
        this.stationName = value;
    }

    public String getIsFault() {
        return this.isFault;
    }

    public void setIsFault(String value) {
        this.isFault = value;
    }

    public String getStopDescription() {
        return this.stopDescription;
    }

    public void setStopDescription(String value) {
        this.stopDescription = value;
    }

}
