package com.radicalninja.transitwear.data.api.train;

import com.google.gson.annotations.SerializedName;

public class FollowResponse extends ArrivalResponse {

    public class Position {

        @SerializedName("heading")
        String heading;

        @SerializedName("lon")
        String lon;

        @SerializedName("lat")
        String lat;

    }

    //@Transient
    @SerializedName("TimeStamp")
    String _timestamp;

    @SerializedName("position")
    Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
