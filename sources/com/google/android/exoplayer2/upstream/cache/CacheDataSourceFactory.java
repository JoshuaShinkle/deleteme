package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.FileDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;

/* loaded from: classes.dex */
public final class CacheDataSourceFactory implements DataSource.Factory {
    private final Cache cache;
    private final DataSource.Factory cacheReadDataSourceFactory;
    private final DataSink.Factory cacheWriteDataSinkFactory;
    private final CacheDataSource.EventListener eventListener;
    private final int flags;
    private final DataSource.Factory upstreamFactory;

    public CacheDataSourceFactory(Cache cache, DataSource.Factory factory) {
        this(cache, factory, 0);
    }

    public CacheDataSourceFactory(Cache cache, DataSource.Factory factory, int i9) {
        this(cache, factory, i9, 2097152L);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
    public CacheDataSource createDataSource() {
        Cache cache = this.cache;
        DataSource dataSourceCreateDataSource = this.upstreamFactory.createDataSource();
        DataSource dataSourceCreateDataSource2 = this.cacheReadDataSourceFactory.createDataSource();
        DataSink.Factory factory = this.cacheWriteDataSinkFactory;
        return new CacheDataSource(cache, dataSourceCreateDataSource, dataSourceCreateDataSource2, factory != null ? factory.createDataSink() : null, this.flags, this.eventListener);
    }

    public CacheDataSourceFactory(Cache cache, DataSource.Factory factory, int i9, long j9) {
        this(cache, factory, new FileDataSourceFactory(), new CacheDataSinkFactory(cache, j9), i9, null);
    }

    public CacheDataSourceFactory(Cache cache, DataSource.Factory factory, DataSource.Factory factory2, DataSink.Factory factory3, int i9, CacheDataSource.EventListener eventListener) {
        this.cache = cache;
        this.upstreamFactory = factory;
        this.cacheReadDataSourceFactory = factory2;
        this.cacheWriteDataSinkFactory = factory3;
        this.flags = i9;
        this.eventListener = eventListener;
    }
}
