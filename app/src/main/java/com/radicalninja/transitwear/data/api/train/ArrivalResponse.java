package com.radicalninja.transitwear.data.api.train;

import com.google.gson.annotations.SerializedName;
import com.radicalninja.transitwear.util.NestedIn;

import java.util.List;

@NestedIn("ctatt")
public class ArrivalResponse extends BaseOuterResponse {

    /**
     * A list of prediction records
     */
    @SerializedName("eta")
    List<ArrivalPrediction> arrivalPredictions;

    public List<ArrivalPrediction> getArrivalPredictions() {return this.arrivalPredictions;}
    public void setArrivalPredictions(List<ArrivalPrediction> value) {this.arrivalPredictions = value;}

}