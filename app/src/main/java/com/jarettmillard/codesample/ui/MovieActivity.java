package com.jarettmillard.codesample.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jarettmillard.codesample.R;
import com.jarettmillard.codesample.api.ApiObserver;
import com.jarettmillard.codesample.api.Apis;
import com.jarettmillard.codesample.api.models.Movie;
import com.jarettmillard.codesample.constants.IntentExtra;
import com.jarettmillard.codesample.databinding.MovieActivityBinding;
import com.jarettmillard.codesample.viewmodel.MovieViewModel;

import retrofit2.Call;

public class MovieActivity extends AppCompatActivity {
    private MovieActivityBinding mBinding;
    private MovieViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Movie movie = getIntent().getParcelableExtra(IntentExtra.MOVIE);
        if (movie == null) {
            finish();
            return;
        }

        mBinding = DataBindingUtil.setContentView(this, R.layout.movie_activity);

        mViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        mViewModel.movieData.observe(this, new ApiObserver<Movie>() {
            @Override
            public void onLoading() {
                mBinding.progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSuccess(Call<Movie> call, Movie movie) {
                mBinding.progressBar.setVisibility(View.GONE);
                setMovie(movie);
            }

            @Override
            public void onFailure(Call<Movie> call, int status) {
                mBinding.progressBar.setVisibility(View.GONE);
                new AlertDialog.Builder(MovieActivity.this)
                        .setMessage(R.string.connection_error)
                        .setPositiveButton(android.R.string.ok, null)
                        .show();
            }
        });
        mViewModel.movieData.load(Apis.MOVIES_API.getMovieDetails(movie.imdbId()), false);

        setMovie(movie);
    }

    private void setMovie(Movie movie) {
        mBinding.setMovie(movie);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.title_and_year, movie.title(), movie.year()));
        }
    }
}
