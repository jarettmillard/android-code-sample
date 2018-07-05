package com.jarettmillard.codesample.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Set;

/* package */ class MoshiAdapterFactory implements JsonAdapter.Factory {
    @Override
    public JsonAdapter<?> create(Type type, Set<? extends Annotation> annotations, Moshi moshi) {
        if (type == BigDecimal.class) {
            return BIG_DECIMAL_JSON_ADAPTER;
        }

        return null;
    }

    private static final JsonAdapter<BigDecimal> BIG_DECIMAL_JSON_ADAPTER = new JsonAdapter<BigDecimal>() {
        @Override
        public BigDecimal fromJson(JsonReader reader) throws IOException {
            String numberString = reader.nextString();
            try {
                return new BigDecimal(numberString);
            } catch (NumberFormatException e) {
                return null;
            }
        }

        @Override
        public void toJson(JsonWriter writer, BigDecimal value) throws IOException {
            writer.value(value.toString());
        }
    }.nullSafe();
}
