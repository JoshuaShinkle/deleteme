package com.google.android.exoplayer2.offline;

import android.net.Uri;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.offline.Downloader;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheUtil;
import com.google.android.exoplayer2.util.PriorityTaskManager;

/* loaded from: classes.dex */
public final class ProgressiveDownloader implements Downloader {
    private static final int BUFFER_SIZE_BYTES = 131072;
    private final Cache cache;
    private final CacheUtil.CachingCounters cachingCounters = new CacheUtil.CachingCounters();
    private final CacheDataSource dataSource;
    private final DataSpec dataSpec;
    private final PriorityTaskManager priorityTaskManager;

    public ProgressiveDownloader(String str, String str2, DownloaderConstructorHelper downloaderConstructorHelper) {
        this.dataSpec = new DataSpec(Uri.parse(str), 0L, -1L, str2, 0);
        this.cache = downloaderConstructorHelper.getCache();
        this.dataSource = downloaderConstructorHelper.buildCacheDataSource(false);
        this.priorityTaskManager = downloaderConstructorHelper.getPriorityTaskManager();
    }

    @Override // com.google.android.exoplayer2.offline.Downloader
    public void download(Downloader.ProgressListener progressListener) {
        this.priorityTaskManager.add(C3322C.PRIORITY_DOWNLOAD);
        try {
            CacheUtil.cache(this.dataSpec, this.cache, this.dataSource, new byte[131072], this.priorityTaskManager, C3322C.PRIORITY_DOWNLOAD, this.cachingCounters, true);
            if (progressListener != null) {
                progressListener.onDownloadProgress(this, 100.0f, this.cachingCounters.contentLength);
            }
        } finally {
            this.priorityTaskManager.remove(C3322C.PRIORITY_DOWNLOAD);
        }
    }

    @Override // com.google.android.exoplayer2.offline.Downloader
    public float getDownloadPercentage() {
        long j9 = this.cachingCounters.contentLength;
        if (j9 == -1) {
            return Float.NaN;
        }
        return (this.cachingCounters.totalCachedBytes() * 100.0f) / j9;
    }

    @Override // com.google.android.exoplayer2.offline.Downloader
    public long getDownloadedBytes() {
        return this.cachingCounters.totalCachedBytes();
    }

    @Override // com.google.android.exoplayer2.offline.Downloader
    public void init() {
        CacheUtil.getCached(this.dataSpec, this.cache, this.cachingCounters);
    }

    @Override // com.google.android.exoplayer2.offline.Downloader
    public void remove() {
        CacheUtil.remove(this.cache, CacheUtil.getKey(this.dataSpec));
    }
}
