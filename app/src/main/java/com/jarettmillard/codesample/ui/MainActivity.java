package com.jarettmillard.codesample.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jarettmillard.codesample.R;
import com.jarettmillard.codesample.api.ApiObserver;
import com.jarettmillard.codesample.api.Apis;
import com.jarettmillard.codesample.api.models.Movie;
import com.jarettmillard.codesample.constants.IntentExtra;
import com.jarettmillard.codesample.databinding.MainActivityBinding;
import com.jarettmillard.codesample.ui.adapters.MovieAdapter;
import com.jarettmillard.codesample.viewmodel.MainViewModel;

import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    private MainActivityBinding mBinding;
    private MainViewModel mViewModel;
    private MovieAdapter mMovieAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.moviesData.observe(this, new ApiObserver<List<Movie>>() {
            @Override
            public void onLoading() {
                mBinding.progressBar.setVisibility(View.VISIBLE);
                mBinding.recyclerView.setVisibility(View.GONE);
            }

            @Override
            public void onSuccess(Call<List<Movie>> call, List<Movie> data) {
                mBinding.progressBar.setVisibility(View.GONE);
                mBinding.recyclerView.setVisibility(View.VISIBLE);
                mMovieAdapter.addAll(data);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, int status) {
                mBinding.progressBar.setVisibility(View.GONE);
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage(R.string.connection_error)
                        .setPositiveButton(android.R.string.ok, null)
                        .show();
            }
        });

        mMovieAdapter = new MovieAdapter(new MovieAdapter.Handlers() {
            @Override
            public void itemClicked(View view, Movie movie) {
                startActivity(new Intent(MainActivity.this, MovieActivity.class)
                        .putExtra(IntentExtra.MOVIE, movie)
                );
            }
        });
        mBinding.recyclerView.setAdapter(mMovieAdapter);

        mViewModel.moviesData.load(Apis.MOVIES_API.getTopMovies(), false);
    }
}
