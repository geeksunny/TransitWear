package com.radicalninja.transitwear.data.api;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.radicalninja.transitwear.util.NestedIn;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAdapter<T> {

    private final T client;
    private final Gson gson = buildGson();
    private final Map<String, String> requiredHeaders = new HashMap<>();
    private final Map<String, String> requiredUrlKeyValuePairs = new HashMap<>();
    private final Object lock = new Object();

    protected RestAdapter(final String apiServerUrl, final Class<T> clientInterface) {
        final Retrofit retrofit = buildAdapter(apiServerUrl);
        client = retrofit.create(clientInterface);
    }

    private Retrofit buildAdapter(final String apiServerUrl) {
        return new Retrofit.Builder()
                .baseUrl(apiServerUrl)
                .client(buildHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private OkHttpClient buildHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new RestInterceptor())
                .build();
    }

    protected Gson buildGson() {
        return new GsonBuilder()
//                .excludeFieldsWithoutExposeAnnotation()
//                .serializeNulls()
//                .setPrettyPrinting()
                .registerTypeAdapterFactory(new NestedIn.AdapterFactory())
                .create();
    }

    protected T getClient() {
        return client;
    }

    protected Gson getGson() {
        // TODO: Determine if this is necessary.
        return gson;
    }

    public void addHeader(final String name, final String value) {
        synchronized (lock) {
            requiredHeaders.put(name, value);
        }
    }

    public boolean removeHeader(final String name) {
        boolean wasRemoved = false;
        synchronized (lock) {
            if (requiredHeaders.containsKey(name)) {
                wasRemoved = true;
                requiredHeaders.remove(name);
            }
        }
        return wasRemoved;
    }

    public void removeAllHeaders() {
        synchronized (lock) {
            requiredHeaders.clear();
        }
    }

    public void addUrlKeyValuePair(final String key, final String value) {
        synchronized (lock) {
            requiredUrlKeyValuePairs.put(key, value);
        }
    }

    public boolean removeUrlKeyValuePair(final String name) {
        boolean wasRemoved = false;
        synchronized (lock) {
            if (requiredUrlKeyValuePairs.containsKey(name)) {
                wasRemoved = true;
                requiredUrlKeyValuePairs.remove(name);
            }
        }
        return wasRemoved;
    }

    public void removeAllKeyValuePairs() {
        synchronized (lock) {
            requiredUrlKeyValuePairs.clear();
        }
    }

    private class RestInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            if (requiredHeaders.isEmpty() && requiredUrlKeyValuePairs.isEmpty()) {
                return chain.proceed(chain.request());
            }
            final Request baseRequest = chain.request();
            final Request.Builder builder = baseRequest.newBuilder();

            setupUrl(builder, baseRequest.url());
            setupHeaders(builder);

            return chain.proceed(builder.build());
        }

        void setupUrl(final Request.Builder builder, final HttpUrl url) {
            if (requiredUrlKeyValuePairs.isEmpty()) {
                return;
            }
            final Map<String, String> valuesCopy;
            synchronized (lock) {
                valuesCopy = new HashMap<>(requiredUrlKeyValuePairs);
            }
            final HttpUrl.Builder urlBuilder = url.newBuilder();
            for (final Map.Entry<String, String> urlValue : valuesCopy.entrySet()) {
                final String key = urlValue.getKey();
                final String value = urlValue.getValue();
                if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
                    continue;
                }
                urlBuilder.addQueryParameter(key, value);
            }
            builder.url(urlBuilder.build());
        }

        void setupHeaders(final Request.Builder builder) {
            if (requiredHeaders.isEmpty()) {
                return;
            }
            final Map<String, String> headerCopy;
            synchronized (lock) {
                headerCopy = new HashMap<>(requiredHeaders);
            }
            for (final Map.Entry<String, String> header : headerCopy.entrySet()) {
                builder.addHeader(header.getKey(), header.getValue());
            }
        }
    }

}
