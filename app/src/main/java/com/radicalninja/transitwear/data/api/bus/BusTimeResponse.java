package com.radicalninja.transitwear.data.api.bus;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "bustime-response")
public class BusTimeResponse {

    @Element(name="tm", required = false)
    String time;

    @Element(name="error", required = false)
    BusError error;

    public String getTime() { return this.time; }
    public void setTime(String _value) { this.time = _value; }

    public BusError getError() { return this.error; }
    public void setError(BusError _value) { this.error = _value; }

}