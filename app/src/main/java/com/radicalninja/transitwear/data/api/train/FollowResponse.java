package com.radicalninja.transitwear.data.api.train;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Transient;

public class FollowResponse extends ArrivalResponse {

    public class Position {

        @Element(name = "heading", required = false)
        String heading;

        @Element(name = "lon", required = false)
        String lon;

        @Element(name = "lat", required = false)
        String lat;

    }

    @Transient
    @Element(name = "TimeStamp", required =  false)
    String _timestamp;

    @Element(name = "position", required = false)
    Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
