package com.radicalninja.transitwear.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NestedIn {

    String value();

    class AdapterFactory implements TypeAdapterFactory {
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            final Class<? super T> targetClass = type.getRawType();
            final NestedIn rootAnnotation = targetClass.getAnnotation(NestedIn.class);

            if (null != rootAnnotation) {
                if (TextUtils.isEmpty(rootAnnotation.value())) {
                    throw new RuntimeException("Annotation value must not be empty!");
                    // TODO: Can this be set as lint check in the NestedIn code itself?
                }
                final TypeAdapter<T> rootAdapter = gson.getDelegateAdapter(this, type);
                return new Adapter<>(rootAdapter, rootAnnotation.value());
                // TODO: Should this adapter get cached?
            }

            return null;
        }
    }

    class Adapter<T> extends TypeAdapter<T> {

        final String nestedKey;
        final TypeAdapter<T> nestedTypeAdapter;

        Adapter(final TypeAdapter<T> nestedTypeAdapter, final String nestedKey) {
            this.nestedKey = nestedKey;
            this.nestedTypeAdapter = nestedTypeAdapter;
        }

        @Override
        public void write(JsonWriter out, T value) throws IOException {
            out.beginObject();
            out.name(nestedKey);
            nestedTypeAdapter.write(out, value);
            out.endObject();
        }

        @Override
        public T read(JsonReader in) throws IOException {
            in.beginObject();
            while (in.hasNext()) {
                if (nestedKey.equals(in.nextName())) {
                    return nestedTypeAdapter.read(in);
                }
            }
            in.endObject();
            return null;
        }
    }

}
