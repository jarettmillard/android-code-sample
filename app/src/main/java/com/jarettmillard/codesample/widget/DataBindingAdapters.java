package com.jarettmillard.codesample.widget;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.jarettmillard.codesample.GlideApp;

public final class DataBindingAdapters {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        GlideApp.with(view.getContext())
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view);
    }

    @BindingAdapter("visible")
    public static void visibility(View view, boolean visible) {
        if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
