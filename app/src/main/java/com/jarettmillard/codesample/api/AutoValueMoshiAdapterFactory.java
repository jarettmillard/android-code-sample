package com.jarettmillard.codesample.api;

import com.squareup.moshi.JsonAdapter;

@com.ryanharter.auto.value.moshi.MoshiAdapterFactory
/* package */ abstract class AutoValueMoshiAdapterFactory implements JsonAdapter.Factory {
    public static JsonAdapter.Factory create() {
        return new com.jarettmillard.codesample.api.AutoValueMoshi_AutoValueMoshiAdapterFactory();
    }
}
