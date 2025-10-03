package com.google.android.gms.common.api;

import android.os.Bundle;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataBuffer;
import java.util.Iterator;

@KeepForSdk
/* loaded from: classes2.dex */
public class DataBufferResponse<T, R extends AbstractDataBuffer<T> & Result> extends Response<R> implements DataBuffer<T> {
    @KeepForSdk
    public DataBufferResponse() {
    }

    @Override // com.google.android.gms.common.data.DataBuffer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        ((AbstractDataBuffer) getResult()).close();
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    @RecentlyNonNull
    public T get(@RecentlyNonNull int i9) {
        return (T) ((AbstractDataBuffer) getResult()).get(i9);
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    @RecentlyNonNull
    public int getCount() {
        return ((AbstractDataBuffer) getResult()).getCount();
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    @RecentlyNullable
    public Bundle getMetadata() {
        return ((AbstractDataBuffer) getResult()).getMetadata();
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    @RecentlyNonNull
    public boolean isClosed() {
        return ((AbstractDataBuffer) getResult()).isClosed();
    }

    @Override // com.google.android.gms.common.data.DataBuffer, java.lang.Iterable
    @RecentlyNonNull
    public Iterator<T> iterator() {
        return ((AbstractDataBuffer) getResult()).iterator();
    }

    @Override // com.google.android.gms.common.data.DataBuffer, com.google.android.gms.common.api.Releasable
    public void release() {
        ((AbstractDataBuffer) getResult()).release();
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    @RecentlyNonNull
    public Iterator<T> singleRefIterator() {
        return ((AbstractDataBuffer) getResult()).singleRefIterator();
    }

    /* JADX WARN: Incorrect types in method signature: (TR;)V */
    /* JADX WARN: Multi-variable type inference failed */
    @KeepForSdk
    public DataBufferResponse(@RecentlyNonNull AbstractDataBuffer abstractDataBuffer) {
        super(abstractDataBuffer);
    }
}
