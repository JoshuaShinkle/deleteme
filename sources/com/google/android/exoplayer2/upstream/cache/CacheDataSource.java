package com.google.android.exoplayer2.upstream.cache;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSourceException;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.TeeDataSource;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class CacheDataSource implements DataSource {
    public static final long DEFAULT_MAX_CACHE_FILE_SIZE = 2097152;
    public static final int FLAG_BLOCK_ON_CACHE = 1;
    public static final int FLAG_IGNORE_CACHE_FOR_UNSET_LENGTH_REQUESTS = 4;
    public static final int FLAG_IGNORE_CACHE_ON_ERROR = 2;
    private static final long MIN_READ_BEFORE_CHECKING_CACHE = 102400;
    private final boolean blockOnCache;
    private long bytesRemaining;
    private final Cache cache;
    private final DataSource cacheReadDataSource;
    private final DataSource cacheWriteDataSource;
    private long checkCachePosition;
    private DataSource currentDataSource;
    private boolean currentDataSpecLengthUnset;
    private CacheSpan currentHoleSpan;
    private boolean currentRequestIgnoresCache;
    private final EventListener eventListener;
    private int flags;
    private final boolean ignoreCacheForUnsetLengthRequests;
    private final boolean ignoreCacheOnError;
    private String key;
    private long readPosition;
    private boolean seenCacheError;
    private long totalCachedBytesRead;
    private final DataSource upstreamDataSource;
    private Uri uri;

    public interface EventListener {
        void onCachedBytesRead(long j9, long j10);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public CacheDataSource(Cache cache, DataSource dataSource) {
        this(cache, dataSource, 0, 2097152L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void closeCurrentSource() {
        DataSource dataSource = this.currentDataSource;
        if (dataSource == null) {
            return;
        }
        try {
            dataSource.close();
        } finally {
            this.currentDataSource = null;
            this.currentDataSpecLengthUnset = false;
            CacheSpan cacheSpan = this.currentHoleSpan;
            if (cacheSpan != null) {
                this.cache.releaseHoleSpan(cacheSpan);
                this.currentHoleSpan = null;
            }
        }
    }

    private void handleBeforeThrow(IOException iOException) {
        if (this.currentDataSource == this.cacheReadDataSource || (iOException instanceof Cache.CacheException)) {
            this.seenCacheError = true;
        }
    }

    private static boolean isCausedByPositionOutOfRange(IOException iOException) {
        for (IOException cause = iOException; cause != null; cause = cause.getCause()) {
            if ((cause instanceof DataSourceException) && ((DataSourceException) cause).reason == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isWritingToCache() {
        return this.currentDataSource == this.cacheWriteDataSource;
    }

    private void notifyBytesRead() {
        EventListener eventListener = this.eventListener;
        if (eventListener == null || this.totalCachedBytesRead <= 0) {
            return;
        }
        eventListener.onCachedBytesRead(this.cache.getCacheSpace(), this.totalCachedBytesRead);
        this.totalCachedBytesRead = 0L;
    }

    private void openNextSource(boolean z8) throws InterruptedIOException {
        CacheSpan cacheSpanStartReadWrite;
        long jMin;
        DataSpec dataSpec;
        DataSource dataSource;
        if (this.currentRequestIgnoresCache) {
            cacheSpanStartReadWrite = null;
        } else if (this.blockOnCache) {
            try {
                cacheSpanStartReadWrite = this.cache.startReadWrite(this.key, this.readPosition);
            } catch (InterruptedException unused) {
                throw new InterruptedIOException();
            }
        } else {
            cacheSpanStartReadWrite = this.cache.startReadWriteNonBlocking(this.key, this.readPosition);
        }
        if (cacheSpanStartReadWrite == null) {
            dataSource = this.upstreamDataSource;
            dataSpec = new DataSpec(this.uri, this.readPosition, this.bytesRemaining, this.key, this.flags);
        } else if (cacheSpanStartReadWrite.isCached) {
            Uri uriFromFile = Uri.fromFile(cacheSpanStartReadWrite.file);
            long j9 = this.readPosition - cacheSpanStartReadWrite.position;
            long jMin2 = cacheSpanStartReadWrite.length - j9;
            long j10 = this.bytesRemaining;
            if (j10 != -1) {
                jMin2 = Math.min(jMin2, j10);
            }
            dataSpec = new DataSpec(uriFromFile, this.readPosition, j9, jMin2, this.key, this.flags);
            dataSource = this.cacheReadDataSource;
        } else {
            if (cacheSpanStartReadWrite.isOpenEnded()) {
                jMin = this.bytesRemaining;
            } else {
                jMin = cacheSpanStartReadWrite.length;
                long j11 = this.bytesRemaining;
                if (j11 != -1) {
                    jMin = Math.min(jMin, j11);
                }
            }
            DataSpec dataSpec2 = new DataSpec(this.uri, this.readPosition, jMin, this.key, this.flags);
            DataSource dataSource2 = this.cacheWriteDataSource;
            if (dataSource2 == null) {
                dataSource2 = this.upstreamDataSource;
                this.cache.releaseHoleSpan(cacheSpanStartReadWrite);
                cacheSpanStartReadWrite = null;
            }
            dataSpec = dataSpec2;
            dataSource = dataSource2;
        }
        this.checkCachePosition = (this.currentRequestIgnoresCache || dataSource != this.upstreamDataSource) ? Long.MAX_VALUE : this.readPosition + MIN_READ_BEFORE_CHECKING_CACHE;
        if (z8) {
            Assertions.checkState(this.currentDataSource == this.upstreamDataSource);
            if (dataSource == this.upstreamDataSource) {
                return;
            }
            try {
                closeCurrentSource();
            } catch (Throwable th) {
                if (cacheSpanStartReadWrite.isHoleSpan()) {
                    this.cache.releaseHoleSpan(cacheSpanStartReadWrite);
                }
                throw th;
            }
        }
        if (cacheSpanStartReadWrite != null && cacheSpanStartReadWrite.isHoleSpan()) {
            this.currentHoleSpan = cacheSpanStartReadWrite;
        }
        this.currentDataSource = dataSource;
        this.currentDataSpecLengthUnset = dataSpec.length == -1;
        long jOpen = dataSource.open(dataSpec);
        if (!this.currentDataSpecLengthUnset || jOpen == -1) {
            return;
        }
        setBytesRemaining(jOpen);
    }

    private void setBytesRemaining(long j9) {
        this.bytesRemaining = j9;
        if (isWritingToCache()) {
            this.cache.setContentLength(this.key, this.readPosition + j9);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void close() throws IOException {
        this.uri = null;
        notifyBytesRead();
        try {
            closeCurrentSource();
        } catch (IOException e9) {
            handleBeforeThrow(e9);
            throw e9;
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public Uri getUri() {
        DataSource dataSource = this.currentDataSource;
        return dataSource == this.upstreamDataSource ? dataSource.getUri() : this.uri;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        try {
            this.uri = dataSpec.uri;
            this.flags = dataSpec.flags;
            String key = CacheUtil.getKey(dataSpec);
            this.key = key;
            this.readPosition = dataSpec.position;
            boolean z8 = (this.ignoreCacheOnError && this.seenCacheError) || (dataSpec.length == -1 && this.ignoreCacheForUnsetLengthRequests);
            this.currentRequestIgnoresCache = z8;
            long j9 = dataSpec.length;
            if (j9 != -1 || z8) {
                this.bytesRemaining = j9;
            } else {
                long contentLength = this.cache.getContentLength(key);
                this.bytesRemaining = contentLength;
                if (contentLength != -1) {
                    long j10 = contentLength - dataSpec.position;
                    this.bytesRemaining = j10;
                    if (j10 <= 0) {
                        throw new DataSourceException(0);
                    }
                }
            }
            openNextSource(false);
            return this.bytesRemaining;
        } catch (IOException e9) {
            handleBeforeThrow(e9);
            throw e9;
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public int read(byte[] bArr, int i9, int i10) throws IOException {
        if (i10 == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        try {
            if (this.readPosition >= this.checkCachePosition) {
                openNextSource(true);
            }
            int i11 = this.currentDataSource.read(bArr, i9, i10);
            if (i11 != -1) {
                if (this.currentDataSource == this.cacheReadDataSource) {
                    this.totalCachedBytesRead += i11;
                }
                long j9 = i11;
                this.readPosition += j9;
                long j10 = this.bytesRemaining;
                if (j10 != -1) {
                    this.bytesRemaining = j10 - j9;
                }
            } else {
                if (!this.currentDataSpecLengthUnset) {
                    long j11 = this.bytesRemaining;
                    if (j11 <= 0) {
                        if (j11 == -1) {
                        }
                    }
                    closeCurrentSource();
                    openNextSource(false);
                    return read(bArr, i9, i10);
                }
                setBytesRemaining(0L);
            }
            return i11;
        } catch (IOException e9) {
            if (this.currentDataSpecLengthUnset && isCausedByPositionOutOfRange(e9)) {
                setBytesRemaining(0L);
                return -1;
            }
            handleBeforeThrow(e9);
            throw e9;
        }
    }

    public CacheDataSource(Cache cache, DataSource dataSource, int i9) {
        this(cache, dataSource, i9, 2097152L);
    }

    public CacheDataSource(Cache cache, DataSource dataSource, int i9, long j9) {
        this(cache, dataSource, new FileDataSource(), new CacheDataSink(cache, j9), i9, null);
    }

    public CacheDataSource(Cache cache, DataSource dataSource, DataSource dataSource2, DataSink dataSink, int i9, EventListener eventListener) {
        this.cache = cache;
        this.cacheReadDataSource = dataSource2;
        this.blockOnCache = (i9 & 1) != 0;
        this.ignoreCacheOnError = (i9 & 2) != 0;
        this.ignoreCacheForUnsetLengthRequests = (i9 & 4) != 0;
        this.upstreamDataSource = dataSource;
        if (dataSink != null) {
            this.cacheWriteDataSource = new TeeDataSource(dataSource, dataSink);
        } else {
            this.cacheWriteDataSource = null;
        }
        this.eventListener = eventListener;
    }
}
