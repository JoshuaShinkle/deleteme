package com.google.android.exoplayer2.offline;

import android.net.Uri;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.offline.Downloader;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheUtil;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public abstract class SegmentDownloader<M, K> implements Downloader {
    private static final int BUFFER_SIZE_BYTES = 131072;
    private final Cache cache;
    private final CacheDataSource dataSource;
    private volatile long downloadedBytes;
    private volatile int downloadedSegments;
    private K[] keys;
    private M manifest;
    private final Uri manifestUri;
    private final CacheDataSource offlineDataSource;
    private final PriorityTaskManager priorityTaskManager;
    private volatile int totalSegments;

    public static class Segment implements Comparable<Segment> {
        public final DataSpec dataSpec;
        public final long startTimeUs;

        public Segment(long j9, DataSpec dataSpec) {
            this.startTimeUs = j9;
            this.dataSpec = dataSpec;
        }

        @Override // java.lang.Comparable
        public int compareTo(Segment segment) {
            long j9 = this.startTimeUs - segment.startTimeUs;
            if (j9 == 0) {
                return 0;
            }
            return j9 < 0 ? -1 : 1;
        }
    }

    public SegmentDownloader(Uri uri, DownloaderConstructorHelper downloaderConstructorHelper) {
        this.manifestUri = uri;
        this.cache = downloaderConstructorHelper.getCache();
        this.dataSource = downloaderConstructorHelper.buildCacheDataSource(false);
        this.offlineDataSource = downloaderConstructorHelper.buildCacheDataSource(true);
        this.priorityTaskManager = downloaderConstructorHelper.getPriorityTaskManager();
        resetCounters();
    }

    private DataSource getDataSource(boolean z8) {
        return z8 ? this.offlineDataSource : this.dataSource;
    }

    private M getManifestIfNeeded(boolean z8) {
        if (this.manifest == null) {
            this.manifest = getManifest(getDataSource(z8), this.manifestUri);
        }
        return this.manifest;
    }

    private synchronized List<Segment> initStatus(boolean z8) {
        List<Segment> segments;
        DataSource dataSource = getDataSource(z8);
        if (this.keys == null) {
            this.keys = getAllRepresentationKeys();
        }
        segments = getSegments(dataSource, this.manifest, this.keys, z8);
        CacheUtil.CachingCounters cachingCounters = new CacheUtil.CachingCounters();
        this.totalSegments = segments.size();
        this.downloadedSegments = 0;
        this.downloadedBytes = 0L;
        for (int size = segments.size() - 1; size >= 0; size--) {
            CacheUtil.getCached(segments.get(size).dataSpec, this.cache, cachingCounters);
            this.downloadedBytes += cachingCounters.alreadyCachedBytes;
            if (cachingCounters.alreadyCachedBytes == cachingCounters.contentLength) {
                this.downloadedSegments++;
                segments.remove(size);
            }
        }
        return segments;
    }

    private void notifyListener(Downloader.ProgressListener progressListener) {
        if (progressListener != null) {
            progressListener.onDownloadProgress(this, getDownloadPercentage(), this.downloadedBytes);
        }
    }

    private void resetCounters() {
        this.totalSegments = -1;
        this.downloadedSegments = -1;
        this.downloadedBytes = -1L;
    }

    @Override // com.google.android.exoplayer2.offline.Downloader
    public final synchronized void download(Downloader.ProgressListener progressListener) {
        this.priorityTaskManager.add(C3322C.PRIORITY_DOWNLOAD);
        try {
            getManifestIfNeeded(false);
            List<Segment> listInitStatus = initStatus(false);
            notifyListener(progressListener);
            Collections.sort(listInitStatus);
            byte[] bArr = new byte[131072];
            CacheUtil.CachingCounters cachingCounters = new CacheUtil.CachingCounters();
            for (int i9 = 0; i9 < listInitStatus.size(); i9++) {
                CacheUtil.cache(listInitStatus.get(i9).dataSpec, this.cache, this.dataSource, bArr, this.priorityTaskManager, C3322C.PRIORITY_DOWNLOAD, cachingCounters, true);
                this.downloadedBytes += cachingCounters.newlyCachedBytes;
                this.downloadedSegments++;
                notifyListener(progressListener);
            }
        } finally {
            this.priorityTaskManager.remove(C3322C.PRIORITY_DOWNLOAD);
        }
    }

    public abstract K[] getAllRepresentationKeys();

    @Override // com.google.android.exoplayer2.offline.Downloader
    public float getDownloadPercentage() {
        int i9 = this.totalSegments;
        int i10 = this.downloadedSegments;
        if (i9 == -1 || i10 == -1) {
            return Float.NaN;
        }
        if (i9 == 0) {
            return 100.0f;
        }
        return (i10 * 100.0f) / i9;
    }

    @Override // com.google.android.exoplayer2.offline.Downloader
    public final long getDownloadedBytes() {
        return this.downloadedBytes;
    }

    public final int getDownloadedSegments() {
        return this.downloadedSegments;
    }

    public final M getManifest() {
        return getManifestIfNeeded(false);
    }

    public abstract M getManifest(DataSource dataSource, Uri uri);

    public abstract List<Segment> getSegments(DataSource dataSource, M m8, K[] kArr, boolean z8);

    public final int getTotalSegments() {
        return this.totalSegments;
    }

    @Override // com.google.android.exoplayer2.offline.Downloader
    public final void init() {
        try {
            getManifestIfNeeded(true);
            try {
                initStatus(true);
            } catch (IOException | InterruptedException e9) {
                resetCounters();
                throw e9;
            }
        } catch (IOException unused) {
        }
    }

    @Override // com.google.android.exoplayer2.offline.Downloader
    public final void remove() {
        List<Segment> segments;
        try {
            getManifestIfNeeded(true);
        } catch (IOException unused) {
        }
        resetCounters();
        M m8 = this.manifest;
        if (m8 != null) {
            try {
                segments = getSegments(this.offlineDataSource, m8, getAllRepresentationKeys(), true);
            } catch (IOException unused2) {
                segments = null;
            }
            if (segments != null) {
                for (int i9 = 0; i9 < segments.size(); i9++) {
                    remove(segments.get(i9).dataSpec.uri);
                }
            }
            this.manifest = null;
        }
        remove(this.manifestUri);
    }

    public final void selectRepresentations(K[] kArr) {
        this.keys = (kArr == null || kArr.length <= 0) ? null : (K[]) ((Object[]) kArr.clone());
        resetCounters();
    }

    private void remove(Uri uri) {
        CacheUtil.remove(this.cache, CacheUtil.generateKey(uri));
    }
}
