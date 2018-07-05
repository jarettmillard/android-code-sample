package com.jarettmillard.codesample.api;

import com.squareup.moshi.Moshi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public final class Apis {
    private static final String MOVIES_BASE_URL = "https://raw.githubusercontent.com/MercuryIntermedia/Sample_Json_Movies/8ae08ea14506aeca9f7b052518fee4237efc3616/";
    public static final MoviesApi MOVIES_API;

    private Apis() {}

    static {
        Moshi moshi = new Moshi.Builder()
                .add(AutoValueMoshiAdapterFactory.create())
                .add(new MoshiAdapterFactory())
                .build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MOVIES_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build();
        MOVIES_API = retrofit.create(MoviesApi.class);
    }
}
