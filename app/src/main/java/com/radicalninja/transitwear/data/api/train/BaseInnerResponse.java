package com.radicalninja.transitwear.data.api.train;

import org.simpleframework.xml.Element;

public class BaseInnerResponse {

    @Element(name = "heading", required = false)
    String heading;

    @Element(name = "prdt", required = false)
    String predictionTime;

    @Element(name = "destNm", required = false)
    String destinationName;

    @Element(name = "flags", required = false)
    String flags;

    @Element(name = "destSt", required = false)
    String destinationStopId;

    @Element(name = "lon", required = false)
    String lon;

    @Element(name = "isApp", required = false)
    String isApproaching;

    @Element(name = "trDr", required = false)
    String trainDirectionCode;

    @Element(name = "isDly", required = false)
    String isDelayed;

    /**
     * Run number of train being predicted for
     */
    @Element(name = "rn", required = false)
    String runNumber;

    /**
     * Date-time format stamp for when a train is expected to arrive/depart:
     * yyyyMMdd HH:mm:ss (24-hour format, time local to Chicago)
     */
    @Element(name = "arrT", required = false)
    String arrivalTime;

    /**
     * Longtitude position
     */
    @Element(name = "lat", required = false)
    String lat;

    public String getHeading() {
        return this.heading;
    }

    public void setHeading(String value) {
        this.heading = value;
    }

    public String getPredictionTime() {
        return this.predictionTime;
    }

    public void setPredictionTime(String value) {
        this.predictionTime = value;
    }

    public String getDestinationName() {
        return this.destinationName;
    }

    public void setDestinationName(String value) {
        this.destinationName = value;
    }

    public String getFlags() {
        return this.flags;
    }

    public void setFlags(String value) {
        this.flags = value;
    }

    public String getDestinationStopId() {
        return this.destinationStopId;
    }

    public void setDestinationStopId(String value) {
        this.destinationStopId = value;
    }

    public String getLon() {
        return this.lon;
    }

    public void setLon(String value) {
        this.lon = value;
    }

    public String getIsApproaching() {
        return this.isApproaching;
    }

    public void setIsApproaching(String value) {
        this.isApproaching = value;
    }

    public String getTrainDirectionCode() {
        return this.trainDirectionCode;
    }

    public void setTrainDirectionCode(String value) {
        this.trainDirectionCode = value;
    }

    public String getIsDelayed() {
        return this.isDelayed;
    }

    public void setIsDelayed(String value) {
        this.isDelayed = value;
    }

    public String getRunNumber() {
        return this.runNumber;
    }

    public void setRunNumber(String value) {
        this.runNumber = value;
    }

    public String getArrivalTime() {
        return this.arrivalTime;
    }

    public void setArrivalTime(String value) {
        this.arrivalTime = value;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLat(String value) {
        this.lat = value;
    }

}
