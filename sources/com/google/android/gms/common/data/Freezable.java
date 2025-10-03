package com.google.android.gms.common.data;

import androidx.annotation.RecentlyNonNull;

/* loaded from: classes2.dex */
public interface Freezable<T> {
    @RecentlyNonNull
    T freeze();

    @RecentlyNonNull
    boolean isDataValid();
}
