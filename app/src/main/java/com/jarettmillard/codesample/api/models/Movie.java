package com.jarettmillard.codesample.api.models;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.math.BigDecimal;

@AutoValue
public abstract class Movie {
    public abstract int rank();

    public abstract String title();

    public abstract int year();

    public abstract String imdbId();

    public abstract BigDecimal imdbRating();

    public abstract int imdbVotes();

    public abstract String poster();

    public abstract String imdbLink();

    public static JsonAdapter<Movie> jsonAdapter(Moshi moshi) {
        return new com.jarettmillard.codesample.api.models.AutoValue_Movie.MoshiJsonAdapter(moshi);
    }
}
