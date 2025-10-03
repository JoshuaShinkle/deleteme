package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;

/* loaded from: classes.dex */
public abstract class BaseMediaChunk extends MediaChunk {
    private int[] firstSampleIndices;
    private BaseMediaChunkOutput output;

    public BaseMediaChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i9, Object obj, long j9, long j10, long j11) {
        super(dataSource, dataSpec, format, i9, obj, j9, j10, j11);
    }

    public final int getFirstSampleIndex(int i9) {
        return this.firstSampleIndices[i9];
    }

    public final BaseMediaChunkOutput getOutput() {
        return this.output;
    }

    public void init(BaseMediaChunkOutput baseMediaChunkOutput) {
        this.output = baseMediaChunkOutput;
        this.firstSampleIndices = baseMediaChunkOutput.getWriteIndices();
    }
}
