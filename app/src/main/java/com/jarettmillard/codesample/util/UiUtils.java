package com.jarettmillard.codesample.util;

import android.text.TextUtils;

public final class UiUtils {
    private UiUtils() {}

    public static String nullSafeJoin(CharSequence delimiter, Iterable tokens) {
        if (tokens == null) {
            return null;
        }
        return TextUtils.join(delimiter, tokens);
    }
}
