package com.google.android.exoplayer2.upstream.cache;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class CacheUtil {
    public static final int DEFAULT_BUFFER_SIZE_BYTES = 131072;

    public static class CachingCounters {
        public volatile long alreadyCachedBytes;
        public volatile long contentLength = -1;
        public volatile long newlyCachedBytes;

        public long totalCachedBytes() {
            return this.alreadyCachedBytes + this.newlyCachedBytes;
        }
    }

    private CacheUtil() {
    }

    public static void cache(DataSpec dataSpec, Cache cache, DataSource dataSource, CachingCounters cachingCounters) throws EOFException {
        cache(dataSpec, cache, new CacheDataSource(cache, dataSource), new byte[131072], null, 0, cachingCounters, false);
    }

    public static String generateKey(Uri uri) {
        return uri.toString();
    }

    public static void getCached(DataSpec dataSpec, Cache cache, CachingCounters cachingCounters) {
        String key = getKey(dataSpec);
        long j9 = dataSpec.absoluteStreamPosition;
        long contentLength = dataSpec.length;
        if (contentLength == -1) {
            contentLength = cache.getContentLength(key);
        }
        cachingCounters.contentLength = contentLength;
        cachingCounters.alreadyCachedBytes = 0L;
        cachingCounters.newlyCachedBytes = 0L;
        long j10 = j9;
        long j11 = contentLength;
        while (j11 != 0) {
            long cachedLength = cache.getCachedLength(key, j10, j11 != -1 ? j11 : Long.MAX_VALUE);
            if (cachedLength > 0) {
                cachingCounters.alreadyCachedBytes += cachedLength;
            } else {
                cachedLength = -cachedLength;
                if (cachedLength == Long.MAX_VALUE) {
                    return;
                }
            }
            j10 += cachedLength;
            if (j11 == -1) {
                cachedLength = 0;
            }
            j11 -= cachedLength;
        }
    }

    public static String getKey(DataSpec dataSpec) {
        String str = dataSpec.key;
        return str != null ? str : generateKey(dataSpec.uri);
    }

    private static long readAndDiscard(DataSpec dataSpec, long j9, long j10, DataSource dataSource, byte[] bArr, PriorityTaskManager priorityTaskManager, int i9, CachingCounters cachingCounters) {
        DataSpec dataSpec2 = dataSpec;
        while (true) {
            if (priorityTaskManager != null) {
                priorityTaskManager.proceed(i9);
            }
            try {
                try {
                    if (Thread.interrupted()) {
                        throw new InterruptedException();
                    }
                    DataSpec dataSpec3 = new DataSpec(dataSpec2.uri, dataSpec2.postBody, j9, (dataSpec2.position + j9) - dataSpec2.absoluteStreamPosition, -1L, dataSpec2.key, dataSpec2.flags | 2);
                    try {
                        long jOpen = dataSource.open(dataSpec3);
                        if (cachingCounters.contentLength == -1 && jOpen != -1) {
                            cachingCounters.contentLength = dataSpec3.absoluteStreamPosition + jOpen;
                        }
                        long j11 = 0;
                        while (true) {
                            if (j11 == j10) {
                                break;
                            }
                            if (Thread.interrupted()) {
                                throw new InterruptedException();
                            }
                            int i10 = dataSource.read(bArr, 0, j10 != -1 ? (int) Math.min(bArr.length, j10 - j11) : bArr.length);
                            if (i10 != -1) {
                                long j12 = i10;
                                j11 += j12;
                                cachingCounters.newlyCachedBytes += j12;
                            } else if (cachingCounters.contentLength == -1) {
                                cachingCounters.contentLength = dataSpec3.absoluteStreamPosition + j11;
                            }
                        }
                        return j11;
                    } catch (PriorityTaskManager.PriorityTooLowException unused) {
                        dataSpec2 = dataSpec3;
                    }
                } catch (PriorityTaskManager.PriorityTooLowException unused2) {
                }
                Util.closeQuietly(dataSource);
            } finally {
                Util.closeQuietly(dataSource);
            }
        }
    }

    public static void remove(Cache cache, String str) {
        Iterator<CacheSpan> it = cache.getCachedSpans(str).iterator();
        while (it.hasNext()) {
            try {
                cache.removeSpan(it.next());
            } catch (Cache.CacheException unused) {
            }
        }
    }

    public static void cache(DataSpec dataSpec, Cache cache, CacheDataSource cacheDataSource, byte[] bArr, PriorityTaskManager priorityTaskManager, int i9, CachingCounters cachingCounters, boolean z8) throws EOFException {
        CachingCounters cachingCounters2 = cachingCounters;
        Assertions.checkNotNull(cacheDataSource);
        Assertions.checkNotNull(bArr);
        if (cachingCounters2 != null) {
            getCached(dataSpec, cache, cachingCounters2);
        } else {
            cachingCounters2 = new CachingCounters();
        }
        CachingCounters cachingCounters3 = cachingCounters2;
        String key = getKey(dataSpec);
        long j9 = dataSpec.absoluteStreamPosition;
        long contentLength = dataSpec.length;
        if (contentLength == -1) {
            contentLength = cache.getContentLength(key);
        }
        long j10 = j9;
        long j11 = contentLength;
        while (true) {
            long j12 = 0;
            if (j11 == 0) {
                return;
            }
            long cachedLength = cache.getCachedLength(key, j10, j11 != -1 ? j11 : Long.MAX_VALUE);
            if (cachedLength <= 0) {
                long j13 = -cachedLength;
                if (readAndDiscard(dataSpec, j10, j13, cacheDataSource, bArr, priorityTaskManager, i9, cachingCounters3) < j13) {
                    if (z8 && j11 != -1) {
                        throw new EOFException();
                    }
                    return;
                }
                cachedLength = j13;
            }
            j10 += cachedLength;
            if (j11 != -1) {
                j12 = cachedLength;
            }
            j11 -= j12;
        }
    }
}
