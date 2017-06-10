package com.radicalninja.transitwear.data.api;

import com.radicalninja.transitwear.BuildConfig;
import com.radicalninja.transitwear.data.api.bus.BusTimeResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public class BusApi {

    private final RestAdapter<BusClient> adapter;

    private interface BusClient {
        @GET("gettime")
        Call<BusTimeResponse> getTime();
    }

    public BusApi() {
        adapter = new RestAdapter<>(BuildConfig.API_ENDPOINT_BUS, BusClient.class);
        adapter.addUrlKeyValuePair("key", BuildConfig.API_KEY_BUS);
        adapter.addUrlKeyValuePair("outputType", "JSON");
    }

    public void getServerTime(final ResponseStateCallback<BusTimeResponse> callback) {
        adapter.getClient().getTime().enqueue(callback);
    }

}
