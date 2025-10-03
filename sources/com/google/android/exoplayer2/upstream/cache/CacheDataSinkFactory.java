package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.upstream.DataSink;

/* loaded from: classes.dex */
public final class CacheDataSinkFactory implements DataSink.Factory {
    private final int bufferSize;
    private final Cache cache;
    private final long maxCacheFileSize;

    public CacheDataSinkFactory(Cache cache, long j9) {
        this(cache, j9, CacheDataSink.DEFAULT_BUFFER_SIZE);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSink.Factory
    public DataSink createDataSink() {
        return new CacheDataSink(this.cache, this.maxCacheFileSize, this.bufferSize);
    }

    public CacheDataSinkFactory(Cache cache, long j9, int i9) {
        this.cache = cache;
        this.maxCacheFileSize = j9;
        this.bufferSize = i9;
    }
}
