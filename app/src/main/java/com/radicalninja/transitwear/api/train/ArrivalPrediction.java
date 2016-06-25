package com.radicalninja.transitwear.api.train;

import org.simpleframework.xml.Element;

public class ArrivalPrediction extends BaseInnerResponse {

    @Element(name = "stpId", required = false)
    String stopId;

    @Element(name = "rt", required = false)
    String route;

    @Element(name = "staId", required = false)
    String stationId;

    @Element(name = "isSch", required = false)
    String isSch;

    @Element(name = "staNm", required = false)
    String stationName;

    @Element(name = "isFlt", required = false)
    String isFault;

    @Element(name = "stpDe", required = false)
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
