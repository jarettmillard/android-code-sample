package com.jarettmillard.codesample.api;

import com.jarettmillard.codesample.api.models.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesApi {
    @GET("top_movies.json")
    Call<List<Movie>> getTopMovies();
}
