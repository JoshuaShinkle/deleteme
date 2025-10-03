package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.util.Assertions;

/* loaded from: classes.dex */
public abstract class Chunk implements Loader.Loadable {
    protected final DataSource dataSource;
    public final DataSpec dataSpec;
    public final long endTimeUs;
    public final long startTimeUs;
    public final Format trackFormat;
    public final Object trackSelectionData;
    public final int trackSelectionReason;
    public final int type;

    public Chunk(DataSource dataSource, DataSpec dataSpec, int i9, Format format, int i10, Object obj, long j9, long j10) {
        this.dataSource = (DataSource) Assertions.checkNotNull(dataSource);
        this.dataSpec = (DataSpec) Assertions.checkNotNull(dataSpec);
        this.type = i9;
        this.trackFormat = format;
        this.trackSelectionReason = i10;
        this.trackSelectionData = obj;
        this.startTimeUs = j9;
        this.endTimeUs = j10;
    }

    public abstract long bytesLoaded();

    public final long getDurationUs() {
        return this.endTimeUs - this.startTimeUs;
    }
}
