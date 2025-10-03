package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

/* loaded from: classes.dex */
public abstract class DataChunk extends Chunk {
    private static final int READ_GRANULARITY = 16384;
    private byte[] data;
    private int limit;
    private volatile boolean loadCanceled;

    public DataChunk(DataSource dataSource, DataSpec dataSpec, int i9, Format format, int i10, Object obj, byte[] bArr) {
        super(dataSource, dataSpec, i9, format, i10, obj, C3322C.TIME_UNSET, C3322C.TIME_UNSET);
        this.data = bArr;
    }

    private void maybeExpandData() {
        byte[] bArr = this.data;
        if (bArr == null) {
            this.data = new byte[READ_GRANULARITY];
        } else if (bArr.length < this.limit + READ_GRANULARITY) {
            this.data = Arrays.copyOf(bArr, bArr.length + READ_GRANULARITY);
        }
    }

    @Override // com.google.android.exoplayer2.source.chunk.Chunk
    public long bytesLoaded() {
        return this.limit;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public final void cancelLoad() {
        this.loadCanceled = true;
    }

    public abstract void consume(byte[] bArr, int i9);

    public byte[] getDataHolder() {
        return this.data;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public final boolean isLoadCanceled() {
        return this.loadCanceled;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public final void load() {
        try {
            this.dataSource.open(this.dataSpec);
            int i9 = 0;
            this.limit = 0;
            while (i9 != -1 && !this.loadCanceled) {
                maybeExpandData();
                i9 = this.dataSource.read(this.data, this.limit, READ_GRANULARITY);
                if (i9 != -1) {
                    this.limit += i9;
                }
            }
            if (!this.loadCanceled) {
                consume(this.data, this.limit);
            }
        } finally {
            Util.closeQuietly(this.dataSource);
        }
    }
}
