package com.radicalninja.transitwear.data.api;

import android.util.Log;

import com.radicalninja.transitwear.BuildConfig;
import com.radicalninja.transitwear.data.api.train.ArrivalResponse;
import com.radicalninja.transitwear.data.api.train.FollowResponse;
import com.radicalninja.transitwear.data.api.train.LocationsResponse;
import com.radicalninja.transitwear.data.model.Stop;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class TrainApi {

    private final static int DEFAULT_MAX = 10;

    private final RestAdapter<TrainClient> adapter;

    private interface TrainClient {
        @GET("ttarrivals.aspx")
        Call<ArrivalResponse> getArrivals(@Query("mapid") int mapid, @Query("max") int max);

        @GET("ttfollow.aspx")
        Call<FollowResponse> followTrain(@Query("runnumber") int runNumber);

        @GET("ttpositions.aspx")
        Call<LocationsResponse> getLocations(@Query("rt") String route);

        @GET("ttpositions.aspx")
        Call<LocationsResponse> getLocations(@Query("rt") String[] routes);

    }

    public TrainApi() {
        adapter = new RestAdapter<>(BuildConfig.API_ENDPOINT_TRAIN, TrainClient.class);
        adapter.addUrlKeyValuePair("key", BuildConfig.API_KEY_TRAIN);
        adapter.addUrlKeyValuePair("outputType", "JSON");
    }

    public void getTrainArrivals(final Stop stop, final Callback<ArrivalResponse> callback) {
        adapter.getClient().getArrivals(stop.getMapId(), DEFAULT_MAX).enqueue(callback);
    }

    public void testLocations() {
        adapter.getClient().getLocations("red").enqueue(
                new Callback<LocationsResponse>() {
                    @Override
                    public void onFailure(Call<LocationsResponse> call, Throwable t) {
                        Log.e("API", "failed");
                    }

                    @Override
                    public void onResponse(Call<LocationsResponse> call, Response<LocationsResponse> response) {
                        Log.e("API", "response");
                    }
                }
        );
    }

    public void testLocationsList() {
        adapter.getClient().getLocations(new String[]{"red","brn"}).enqueue(
                new Callback<LocationsResponse>() {
                    @Override
                    public void onFailure(Call<LocationsResponse> call, Throwable t) {
                        Log.e("API", "failed");
                    }

                    @Override
                    public void onResponse(Call<LocationsResponse> call, Response<LocationsResponse> response) {
                        Log.e("API", "response");
                    }
                }
        );
    }

    public void testFollow() {
        adapter.getClient().followTrain(40380).enqueue(
                new Callback<FollowResponse>() {
                    @Override
                    public void onFailure(Call<FollowResponse> call, Throwable t) {
                        Log.e("API", "failed");
                    }

                    @Override
                    public void onResponse(Call<FollowResponse> call, Response<FollowResponse> response) {
                        Log.e("API", "response");
                    }
                }
        );
    }

    public void testArrivals() {
        adapter.getClient().getArrivals(40380, 5).enqueue(
                new Callback<ArrivalResponse>() {
                    @Override
                    public void onFailure(Call<ArrivalResponse> call, Throwable t) {
                        Log.e("API", "failed");
                    }

                    @Override
                    public void onResponse(Call<ArrivalResponse> call, Response<ArrivalResponse> response) {
                        Log.e("API", "response");
                    }
                }
        );
    }

}
