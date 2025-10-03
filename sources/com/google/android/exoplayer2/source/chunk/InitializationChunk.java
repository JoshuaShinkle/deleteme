package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

/* loaded from: classes.dex */
public final class InitializationChunk extends Chunk {
    private volatile int bytesLoaded;
    private final ChunkExtractorWrapper extractorWrapper;
    private volatile boolean loadCanceled;

    public InitializationChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i9, Object obj, ChunkExtractorWrapper chunkExtractorWrapper) {
        super(dataSource, dataSpec, 2, format, i9, obj, C3322C.TIME_UNSET, C3322C.TIME_UNSET);
        this.extractorWrapper = chunkExtractorWrapper;
    }

    @Override // com.google.android.exoplayer2.source.chunk.Chunk
    public long bytesLoaded() {
        return this.bytesLoaded;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public void cancelLoad() {
        this.loadCanceled = true;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public boolean isLoadCanceled() {
        return this.loadCanceled;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public void load() {
        DataSpec dataSpecSubrange = this.dataSpec.subrange(this.bytesLoaded);
        try {
            DataSource dataSource = this.dataSource;
            DefaultExtractorInput defaultExtractorInput = new DefaultExtractorInput(dataSource, dataSpecSubrange.absoluteStreamPosition, dataSource.open(dataSpecSubrange));
            if (this.bytesLoaded == 0) {
                this.extractorWrapper.init(null);
            }
            try {
                Extractor extractor = this.extractorWrapper.extractor;
                int i9 = 0;
                while (i9 == 0 && !this.loadCanceled) {
                    i9 = extractor.read(defaultExtractorInput, null);
                }
                Assertions.checkState(i9 != 1);
            } finally {
                this.bytesLoaded = (int) (defaultExtractorInput.getPosition() - this.dataSpec.absoluteStreamPosition);
            }
        } finally {
            Util.closeQuietly(this.dataSource);
        }
    }
}
