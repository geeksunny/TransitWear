package com.radicalninja.transitwear.api;

import android.support.annotation.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ResponseStateCallback<T> implements Callback<T> {

    public abstract void onSuccess(@Nullable T responseObject);

    public abstract void onError(Response<T> response);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (!response.isSuccessful()) {
            onError(response);
        } else {
            onSuccess(response.body());
        }
    }

}
