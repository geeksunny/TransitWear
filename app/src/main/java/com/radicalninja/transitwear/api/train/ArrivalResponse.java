package com.radicalninja.transitwear.api.train;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="ctatt")
public class ArrivalResponse extends BaseOuterResponse {

    /**
     * A list of prediction records
     */
    @ElementList(name="eta", required=false, entry="eta", inline=true)
    List<ArrivalPrediction> arrivalPredictions;

    public List<ArrivalPrediction> getArrivalPredictions() {return this.arrivalPredictions;}
    public void setArrivalPredictions(List<ArrivalPrediction> value) {this.arrivalPredictions = value;}

}