package com.radicalninja.transitwear.data.api;

import com.radicalninja.transitwear.BuildConfig;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class BusApi {

    private final RestAdapter<BusClient> adapter;

    private interface BusClient {
        @GET("v2/api/example")
        Call<String> getExample(@Path("user") String user);
    }

    public BusApi() {
        adapter = new RestAdapter<>(BuildConfig.API_ENDPOINT_BUS, BusClient.class);
    }

}
