package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ReusableBufferedOutputStream;
import com.google.android.exoplayer2.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes.dex */
public final class CacheDataSink implements DataSink {
    public static final int DEFAULT_BUFFER_SIZE = 20480;
    private final int bufferSize;
    private ReusableBufferedOutputStream bufferedOutputStream;
    private final Cache cache;
    private DataSpec dataSpec;
    private long dataSpecBytesWritten;
    private File file;
    private final long maxCacheFileSize;
    private OutputStream outputStream;
    private long outputStreamBytesWritten;
    private FileOutputStream underlyingFileOutputStream;

    public static class CacheDataSinkException extends Cache.CacheException {
        public CacheDataSinkException(IOException iOException) {
            super(iOException);
        }
    }

    public CacheDataSink(Cache cache, long j9) {
        this(cache, j9, DEFAULT_BUFFER_SIZE);
    }

    private void closeCurrentOutputStream() throws IOException {
        OutputStream outputStream = this.outputStream;
        if (outputStream == null) {
            return;
        }
        try {
            outputStream.flush();
            this.underlyingFileOutputStream.getFD().sync();
            Util.closeQuietly(this.outputStream);
            this.outputStream = null;
            File file = this.file;
            this.file = null;
            this.cache.commitFile(file);
        } catch (Throwable th) {
            Util.closeQuietly(this.outputStream);
            this.outputStream = null;
            File file2 = this.file;
            this.file = null;
            file2.delete();
            throw th;
        }
    }

    private void openNextOutputStream() {
        long j9 = this.dataSpec.length;
        long jMin = j9 == -1 ? this.maxCacheFileSize : Math.min(j9 - this.dataSpecBytesWritten, this.maxCacheFileSize);
        Cache cache = this.cache;
        DataSpec dataSpec = this.dataSpec;
        this.file = cache.startFile(dataSpec.key, this.dataSpecBytesWritten + dataSpec.absoluteStreamPosition, jMin);
        FileOutputStream fileOutputStream = new FileOutputStream(this.file);
        this.underlyingFileOutputStream = fileOutputStream;
        if (this.bufferSize > 0) {
            ReusableBufferedOutputStream reusableBufferedOutputStream = this.bufferedOutputStream;
            if (reusableBufferedOutputStream == null) {
                this.bufferedOutputStream = new ReusableBufferedOutputStream(this.underlyingFileOutputStream, this.bufferSize);
            } else {
                reusableBufferedOutputStream.reset(fileOutputStream);
            }
            this.outputStream = this.bufferedOutputStream;
        } else {
            this.outputStream = fileOutputStream;
        }
        this.outputStreamBytesWritten = 0L;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSink
    public void close() throws CacheDataSinkException {
        if (this.dataSpec == null) {
            return;
        }
        try {
            closeCurrentOutputStream();
        } catch (IOException e9) {
            throw new CacheDataSinkException(e9);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSink
    public void open(DataSpec dataSpec) throws CacheDataSinkException {
        if (dataSpec.length == -1 && !dataSpec.isFlagSet(2)) {
            this.dataSpec = null;
            return;
        }
        this.dataSpec = dataSpec;
        this.dataSpecBytesWritten = 0L;
        try {
            openNextOutputStream();
        } catch (IOException e9) {
            throw new CacheDataSinkException(e9);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSink
    public void write(byte[] bArr, int i9, int i10) throws IOException {
        if (this.dataSpec == null) {
            return;
        }
        int i11 = 0;
        while (i11 < i10) {
            try {
                if (this.outputStreamBytesWritten == this.maxCacheFileSize) {
                    closeCurrentOutputStream();
                    openNextOutputStream();
                }
                int iMin = (int) Math.min(i10 - i11, this.maxCacheFileSize - this.outputStreamBytesWritten);
                this.outputStream.write(bArr, i9 + i11, iMin);
                i11 += iMin;
                long j9 = iMin;
                this.outputStreamBytesWritten += j9;
                this.dataSpecBytesWritten += j9;
            } catch (IOException e9) {
                throw new CacheDataSinkException(e9);
            }
        }
    }

    public CacheDataSink(Cache cache, long j9, int i9) {
        this.cache = (Cache) Assertions.checkNotNull(cache);
        this.maxCacheFileSize = j9;
        this.bufferSize = i9;
    }
}
