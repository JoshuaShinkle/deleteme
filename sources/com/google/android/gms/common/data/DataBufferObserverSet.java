package com.google.android.gms.common.data;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.data.DataBufferObserver;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes2.dex */
public final class DataBufferObserverSet implements DataBufferObserver, DataBufferObserver.Observable {
    private HashSet<DataBufferObserver> zaa = new HashSet<>();

    @Override // com.google.android.gms.common.data.DataBufferObserver.Observable
    public final void addObserver(@RecentlyNonNull DataBufferObserver dataBufferObserver) {
        this.zaa.add(dataBufferObserver);
    }

    public final void clear() {
        this.zaa.clear();
    }

    @RecentlyNonNull
    public final boolean hasObservers() {
        return !this.zaa.isEmpty();
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver
    public final void onDataChanged() {
        Iterator<DataBufferObserver> it = this.zaa.iterator();
        while (it.hasNext()) {
            it.next().onDataChanged();
        }
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver
    public final void onDataRangeChanged(@RecentlyNonNull int i9, @RecentlyNonNull int i10) {
        Iterator<DataBufferObserver> it = this.zaa.iterator();
        while (it.hasNext()) {
            it.next().onDataRangeChanged(i9, i10);
        }
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver
    public final void onDataRangeInserted(@RecentlyNonNull int i9, @RecentlyNonNull int i10) {
        Iterator<DataBufferObserver> it = this.zaa.iterator();
        while (it.hasNext()) {
            it.next().onDataRangeInserted(i9, i10);
        }
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver
    public final void onDataRangeMoved(@RecentlyNonNull int i9, @RecentlyNonNull int i10, @RecentlyNonNull int i11) {
        Iterator<DataBufferObserver> it = this.zaa.iterator();
        while (it.hasNext()) {
            it.next().onDataRangeMoved(i9, i10, i11);
        }
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver
    public final void onDataRangeRemoved(@RecentlyNonNull int i9, @RecentlyNonNull int i10) {
        Iterator<DataBufferObserver> it = this.zaa.iterator();
        while (it.hasNext()) {
            it.next().onDataRangeRemoved(i9, i10);
        }
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver.Observable
    public final void removeObserver(@RecentlyNonNull DataBufferObserver dataBufferObserver) {
        this.zaa.remove(dataBufferObserver);
    }
}
