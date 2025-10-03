package com.google.android.gms.common.data;

import androidx.annotation.RecentlyNonNull;

/* loaded from: classes2.dex */
public interface DataBufferObserver {

    public interface Observable {
        void addObserver(@RecentlyNonNull DataBufferObserver dataBufferObserver);

        void removeObserver(@RecentlyNonNull DataBufferObserver dataBufferObserver);
    }

    void onDataChanged();

    void onDataRangeChanged(@RecentlyNonNull int i9, @RecentlyNonNull int i10);

    void onDataRangeInserted(@RecentlyNonNull int i9, @RecentlyNonNull int i10);

    void onDataRangeMoved(@RecentlyNonNull int i9, @RecentlyNonNull int i10, @RecentlyNonNull int i11);

    void onDataRangeRemoved(@RecentlyNonNull int i9, @RecentlyNonNull int i10);
}
