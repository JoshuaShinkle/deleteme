package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;

/* loaded from: classes.dex */
public abstract class MediaChunk extends Chunk {
    public final long chunkIndex;

    public MediaChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i9, Object obj, long j9, long j10, long j11) {
        super(dataSource, dataSpec, 1, format, i9, obj, j9, j10);
        Assertions.checkNotNull(format);
        this.chunkIndex = j11;
    }

    public long getNextChunkIndex() {
        return this.chunkIndex + 1;
    }

    public abstract boolean isLoadCompleted();
}
