package com.jarettmillard.codesample.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.jarettmillard.codesample.api.ApiLiveData;
import com.jarettmillard.codesample.api.models.Movie;

import java.util.List;

public class MainViewModel extends ViewModel {
    public final ApiLiveData<List<Movie>> moviesData = new ApiLiveData<>();
}
