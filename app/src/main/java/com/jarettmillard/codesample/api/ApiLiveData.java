package com.jarettmillard.codesample.api;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiLiveData<T> extends LiveData<ApiLiveData.Resource<T>> implements Callback<T> {
    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        setValue(new Resource<>(call, response, response.code()));
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        setValue(new Resource<>(call, null, Resource.STATUS_NO_RESPONSE));
    }

    public final void load(Call<T> call, boolean forceReload) {
        Resource<T> resource = getValue();
        if (!forceReload && resource != null && resource.status != Resource.STATUS_NO_RESPONSE) {
            return;
        }
        if (resource != null && resource.status == Resource.STATUS_LOADING) {
            resource.call.cancel();
        }
        call.enqueue(this);
        setValue(new Resource<>(call, null, Resource.STATUS_LOADING));
    }

    public final boolean isLoading() {
        Resource<T> resource = getValue();
        return (resource != null && resource.status == Resource.STATUS_LOADING);
    }

    @Nullable
    public final T getResult() {
        Resource<T> resource = getValue();
        if (resource != null
                && resource.response != null) {
            return resource.response.body();
        }
        return null;
    }

    public final void clear() {
        setValue(null);
    }

    static final class Resource<T> {
        static final int STATUS_LOADING = -1;
        static final int STATUS_NO_RESPONSE = 0;

        final @NonNull Call<T> call;
        final @Nullable Response<T> response;
        final int status;

        private Resource(@NonNull Call<T> call, @Nullable Response<T> response, int status) {
            this.call = call;
            this.response = response;
            this.status = status;
        }
    }
}

