package com.jarettmillard.codesample.api;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import retrofit2.Call;

public abstract class ApiObserver<T> implements Observer<ApiLiveData.Resource<T>> {
    @Override
    public final void onChanged(@Nullable ApiLiveData.Resource<T> resource) {
        if (resource == null) {
            return;
        }

        if (resource.status == ApiLiveData.Resource.STATUS_LOADING) {
            onLoading();
        } else if (resource.response != null && resource.response.isSuccessful()) {
            onSuccess(resource.call, resource.response.body());
        } else {
            onFailure(resource.call, resource.status);
        }
    }

    public abstract void onLoading();

    public abstract void onSuccess(Call<T> call, T data);

    public abstract void onFailure(Call<T> call, int status);
}
