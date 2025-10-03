package com.google.android.exoplayer2.offline;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DummyDataSource;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.PriorityDataSource;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.PriorityTaskManager;

/* loaded from: classes.dex */
public final class DownloaderConstructorHelper {
    private final Cache cache;
    private final DataSource.Factory cacheReadDataSourceFactory;
    private final DataSink.Factory cacheWriteDataSinkFactory;
    private final PriorityTaskManager priorityTaskManager;
    private final DataSource.Factory upstreamDataSourceFactory;

    public DownloaderConstructorHelper(Cache cache, DataSource.Factory factory) {
        this(cache, factory, null, null, null);
    }

    public CacheDataSource buildCacheDataSource(boolean z8) {
        DataSource.Factory factory = this.cacheReadDataSourceFactory;
        DataSource dataSourceCreateDataSource = factory != null ? factory.createDataSource() : new FileDataSource();
        if (z8) {
            return new CacheDataSource(this.cache, DummyDataSource.INSTANCE, dataSourceCreateDataSource, null, 1, null);
        }
        DataSink.Factory factory2 = this.cacheWriteDataSinkFactory;
        DataSink dataSinkCreateDataSink = factory2 != null ? factory2.createDataSink() : new CacheDataSink(this.cache, 2097152L);
        DataSource dataSourceCreateDataSource2 = this.upstreamDataSourceFactory.createDataSource();
        PriorityTaskManager priorityTaskManager = this.priorityTaskManager;
        return new CacheDataSource(this.cache, priorityTaskManager == null ? dataSourceCreateDataSource2 : new PriorityDataSource(dataSourceCreateDataSource2, priorityTaskManager, C3322C.PRIORITY_DOWNLOAD), dataSourceCreateDataSource, dataSinkCreateDataSink, 1, null);
    }

    public Cache getCache() {
        return this.cache;
    }

    public PriorityTaskManager getPriorityTaskManager() {
        PriorityTaskManager priorityTaskManager = this.priorityTaskManager;
        return priorityTaskManager != null ? priorityTaskManager : new PriorityTaskManager();
    }

    public DownloaderConstructorHelper(Cache cache, DataSource.Factory factory, DataSource.Factory factory2, DataSink.Factory factory3, PriorityTaskManager priorityTaskManager) {
        Assertions.checkNotNull(factory);
        this.cache = cache;
        this.upstreamDataSourceFactory = factory;
        this.cacheReadDataSourceFactory = factory2;
        this.cacheWriteDataSinkFactory = factory3;
        this.priorityTaskManager = priorityTaskManager;
    }
}
