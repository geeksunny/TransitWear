package com.radicalninja.transitwear.data.api.bus;

import org.simpleframework.xml.Element;

public class BusError {

    @Element(name="msg", required = true)
    String message;

    @Element(name="pid", required = false)
    String patternId;

    @Element(name="rt", required = false)
    String route;

    @Element(name="vid", required = false)
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