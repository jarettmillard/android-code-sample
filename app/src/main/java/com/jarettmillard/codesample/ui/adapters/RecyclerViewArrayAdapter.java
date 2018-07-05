package com.jarettmillard.codesample.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class RecyclerViewArrayAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private final List<T> mItems = new ArrayList<>();

    public T getItem(int position) {
        return mItems.get(position);
    }

    public int getItemPosition(T item) {
        return mItems.indexOf(item);
    }

    public void addItem(T item) {
        mItems.add(item);
        notifyItemInserted(mItems.size() - 1);
    }

    public void addItem(int position, T item) {
        mItems.add(position, item);
        notifyItemInserted(position);
    }

    public void addAll(@NonNull Collection<? extends T> items) {
        final int start = mItems.size();
        if (mItems.addAll(items)) {
            notifyItemRangeInserted(start, items.size());
        }
    }

    public void addAll(int position, @NonNull Collection<? extends T> items) {
        if (mItems.addAll(position, items)) {
            notifyItemRangeInserted(position, items.size());
        }
    }

    public void moveItem(int position, T item) {
        final int oldPosition = mItems.indexOf(item);
        if (oldPosition != -1) {
            mItems.remove(item);
            mItems.add(position, item);
            notifyItemMoved(oldPosition, position);
        } else {
            addItem(position, item);
        }
    }

    public void moveItem(int newPosition, int oldPosition) {
        final T item = mItems.remove(oldPosition);
        mItems.add(newPosition, item);
        notifyItemMoved(oldPosition, newPosition);
    }

    public void replaceItem(int position, T item) {
        mItems.set(position, item);
        notifyItemChanged(position);
    }

    public void replaceItem(T oldItem, T newItem) {
        replaceItem(getItemPosition(oldItem), newItem);
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public void removeItem(T item) {
        if (mItems.contains(item)) {
            removeItem(mItems.indexOf(item));
        }
    }

    public void removeAll(@NonNull Collection<? extends T> items) {
        for (T item : items) {
            removeItem(item);
        }
    }

    public void clear() {
        int originalSize = mItems.size();
        mItems.clear();
        notifyItemRangeRemoved(0, originalSize);
    }

    @Override
    public final int getItemCount() {
        return mItems.size();
    }
}
