package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.SeekParameters;
import java.util.List;

/* loaded from: classes.dex */
public interface ChunkSource {
    long getAdjustedSeekPositionUs(long j9, SeekParameters seekParameters);

    void getNextChunk(MediaChunk mediaChunk, long j9, long j10, ChunkHolder chunkHolder);

    int getPreferredQueueSize(long j9, List<? extends MediaChunk> list);

    void maybeThrowError();

    void onChunkLoadCompleted(Chunk chunk);

    boolean onChunkLoadError(Chunk chunk, boolean z8, Exception exc);
}
