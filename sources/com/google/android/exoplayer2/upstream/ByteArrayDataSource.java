package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;

/* loaded from: classes.dex */
public final class ByteArrayDataSource implements DataSource {
    private int bytesRemaining;
    private final byte[] data;
    private int readPosition;
    private Uri uri;

    public ByteArrayDataSource(byte[] bArr) {
        Assertions.checkNotNull(bArr);
        Assertions.checkArgument(bArr.length > 0);
        this.data = bArr;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void close() {
        this.uri = null;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public Uri getUri() {
        return this.uri;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        this.uri = dataSpec.uri;
        long j9 = dataSpec.position;
        int i9 = (int) j9;
        this.readPosition = i9;
        long length = dataSpec.length;
        if (length == -1) {
            length = this.data.length - j9;
        }
        int i10 = (int) length;
        this.bytesRemaining = i10;
        if (i10 > 0 && i9 + i10 <= this.data.length) {
            return i10;
        }
        throw new IOException("Unsatisfiable range: [" + this.readPosition + ", " + dataSpec.length + "], length: " + this.data.length);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public int read(byte[] bArr, int i9, int i10) {
        if (i10 == 0) {
            return 0;
        }
        int i11 = this.bytesRemaining;
        if (i11 == 0) {
            return -1;
        }
        int iMin = Math.min(i10, i11);
        System.arraycopy(this.data, this.readPosition, bArr, i9, iMin);
        this.readPosition += iMin;
        this.bytesRemaining -= iMin;
        return iMin;
    }
}
