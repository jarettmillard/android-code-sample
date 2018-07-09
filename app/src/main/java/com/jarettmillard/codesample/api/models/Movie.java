package com.jarettmillard.codesample.api.models;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.math.BigDecimal;
import java.util.List;

@AutoValue
public abstract class Movie implements Parcelable {
    @Nullable
    public abstract Integer rank();

    public abstract String title();

    public abstract int year();

    public abstract String imdbId();

    public abstract BigDecimal imdbRating();

    public abstract int imdbVotes();

    public abstract String poster();

    @Nullable
    public abstract String imdbLink();

    @Nullable
    public abstract String rated();

    @Nullable
    public abstract String released();

    @Nullable
    public abstract String runtime();

    @Nullable
    public abstract List<String> genre();

    @Nullable
    public abstract String director();

    @Nullable
    public abstract String writer();

    @Nullable
    public abstract List<String> actors();

    @Nullable
    public abstract String plot();

    @Nullable
    public abstract List<String> language();

    @Nullable
    public abstract String country();

    @Nullable
    public abstract String awards();

    @Nullable
    public abstract String metascore();

    public static JsonAdapter<Movie> jsonAdapter(Moshi moshi) {
        return new com.jarettmillard.codesample.api.models.AutoValue_Movie.MoshiJsonAdapter(moshi);
    }
}
