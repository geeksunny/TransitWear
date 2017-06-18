package com.radicalninja.transitwear.data.api.bus;

import com.google.gson.annotations.SerializedName;
import com.radicalninja.transitwear.util.NestedIn;

@NestedIn("bustime-response")
public class BusTimeResponse {

    @SerializedName("tm")
    String time;

    @SerializedName("error")
    BusError error;

    public String getTime() { return this.time; }
    public void setTime(String _value) { this.time = _value; }

    public BusError getError() { return this.error; }
    public void setError(BusError _value) { this.error = _value; }

}