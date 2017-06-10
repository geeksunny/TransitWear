package com.radicalninja.transitwear.data.api.train;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//@Root(name = "ctatt")
public class LocationsResponse extends BaseOuterResponse {

    @SerializedName("route")
    List<Route> routes;


    public List<Route> getRoutes() { return this.routes; }
    public void setRoutes(List<Route> _value) { this.routes = _value; }

    public static class Route {

        @SerializedName("train")
        List<Train> trains;

        @SerializedName("name")
        String name;


        public List<Train> getTrains() { return this.trains; }
        public void setTrains(List<Train> _value) { this.trains = _value; }

        public String getName() { return this.name; }
        public void setName(String _value) { this.name = _value; }

    }

    public static class Train extends BaseInnerResponse {

        @SerializedName("nextStaId")
        String nextStaId;

        @SerializedName("nextStpId")
        String nextStpId;

        @SerializedName("nextStaNm")
        String nextStaNm;


        public String getNextStaId() { return this.nextStaId; }
        public void setNextStaId(String _value) { this.nextStaId = _value; }

        public String getNextStpId() { return this.nextStpId; }
        public void setNextStpId(String _value) { this.nextStpId = _value; }

        public String getNextStaNm() { return this.nextStaNm; }
        public void setNextStaNm(String _value) { this.nextStaNm = _value; }

    }
}
