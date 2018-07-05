package com.jarettmillard.codesample.widget;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.jarettmillard.codesample.GlideApp;

public final class DataBindingAdapters {
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        GlideApp.with(view.getContext())
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view);
    }
}
