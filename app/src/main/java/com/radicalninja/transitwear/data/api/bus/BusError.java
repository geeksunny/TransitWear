package com.radicalninja.transitwear.data.api.bus;

import com.google.gson.annotations.SerializedName;

public class BusError {

    @SerializedName("msg")
    String message;

    @SerializedName("pid")
    String patternId;

    @SerializedName("rt")
    String route;

    @SerializedName("vid")
    String vehicleId;

    public String getMessage() { return this.message; }
    public void setMessage(String _value) { this.message = _value; }

    public String getPatternId() { return this.patternId; }
    public void setPatternId(String _value) { this.patternId = _value; }

    public String getRoute() { return this.route; }
    public void setRoute(String _value) { this.route = _value; }

    public String getVehicleId() { return this.vehicleId; }
    public void setVehicleId(String _value) { this.vehicleId = _value; }


}