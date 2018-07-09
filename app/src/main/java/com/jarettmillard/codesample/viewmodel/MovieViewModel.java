package com.jarettmillard.codesample.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.jarettmillard.codesample.api.ApiLiveData;
import com.jarettmillard.codesample.api.models.Movie;

public class MovieViewModel extends ViewModel {
    public final ApiLiveData<Movie> movieData = new ApiLiveData<>();
}
