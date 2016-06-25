package com.radicalninja.transitwear.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RestAdapter<T> {

    private final T client;
    private final Map<String, String> requiredHeaders = new HashMap<>();
    private final Object lock = new Object();

    protected RestAdapter(final String apiServerUrl, final Class<T> clientInterface) {
        final Retrofit retrofit = buildAdapter(apiServerUrl);
        client = retrofit.create(clientInterface);
    }

    private Retrofit buildAdapter(final String apiServerUrl) {
        return new Retrofit.Builder()
                .baseUrl(apiServerUrl)
                .client(buildHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create(/*Serializer*/))
                .build();
    }

    private OkHttpClient buildHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new RestInterceptor())
                .build();
    }

    protected T getClient() {
        return client;
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

    class RestInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            if (requiredHeaders.isEmpty()) {
                return chain.proceed(chain.request());
            }
            final Request baseRequest = chain.request();
            final Request.Builder builder = baseRequest.newBuilder();
            final Map<String, String> headerCopy;
            synchronized (lock) {
                headerCopy = new HashMap<>(requiredHeaders);
            }
            //noinspection ConstantConditions
            if (headerCopy != null) {
                for (final Map.Entry<String, String> header : headerCopy.entrySet()) {
                    builder.addHeader(header.getKey(), header.getValue());
                }
            }
            return chain.proceed(builder.build());
        }
    }

}
