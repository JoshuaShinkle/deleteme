package com.google.android.gms.common.data;

import android.os.Bundle;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Iterator;

/* loaded from: classes2.dex */
public abstract class AbstractDataBuffer<T> implements DataBuffer<T> {

    @RecentlyNullable
    @KeepForSdk
    protected final DataHolder mDataHolder;

    @KeepForSdk
    public AbstractDataBuffer(DataHolder dataHolder) {
        this.mDataHolder = dataHolder;
    }

    @Override // com.google.android.gms.common.data.DataBuffer, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        release();
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    @RecentlyNonNull
    public abstract T get(@RecentlyNonNull int i9);

    @Override // com.google.android.gms.common.data.DataBuffer
    @RecentlyNonNull
    public int getCount() {
        DataHolder dataHolder = this.mDataHolder;
        if (dataHolder == null) {
            return 0;
        }
        return dataHolder.getCount();
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    @RecentlyNullable
    public Bundle getMetadata() {
        DataHolder dataHolder = this.mDataHolder;
        if (dataHolder == null) {
            return null;
        }
        return dataHolder.getMetadata();
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    @RecentlyNonNull
    @Deprecated
    public boolean isClosed() {
        DataHolder dataHolder = this.mDataHolder;
        return dataHolder == null || dataHolder.isClosed();
    }

    @Override // com.google.android.gms.common.data.DataBuffer, java.lang.Iterable
    @RecentlyNonNull
    public Iterator<T> iterator() {
        return new DataBufferIterator(this);
    }

    @Override // com.google.android.gms.common.data.DataBuffer, com.google.android.gms.common.api.Releasable
    public void release() {
        DataHolder dataHolder = this.mDataHolder;
        if (dataHolder != null) {
            dataHolder.close();
        }
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    @RecentlyNonNull
    public Iterator<T> singleRefIterator() {
        return new SingleRefDataBufferIterator(this);
    }
}
