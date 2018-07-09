package com.jarettmillard.codesample.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jarettmillard.codesample.api.models.Movie;
import com.jarettmillard.codesample.databinding.MovieListItemBinding;

public class MovieAdapter extends RecyclerViewArrayAdapter<Movie, MovieAdapter.ViewHolder> {
    private final Handlers mHandlers;

    public MovieAdapter(@NonNull Handlers handlers) {
        mHandlers = handlers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieListItemBinding binding = MovieListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        binding.setHandlers(mHandlers);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.binding.setMovie(getItem(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final MovieListItemBinding binding;

        public ViewHolder(MovieListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface Handlers {
        void itemClicked(View view, Movie movie);
    }
}
