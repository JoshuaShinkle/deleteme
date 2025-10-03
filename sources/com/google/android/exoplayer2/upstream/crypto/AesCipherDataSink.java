package com.google.android.exoplayer2.upstream.crypto;

import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSpec;

/* loaded from: classes.dex */
public final class AesCipherDataSink implements DataSink {
    private AesFlushingCipher cipher;
    private final byte[] scratch;
    private final byte[] secretKey;
    private final DataSink wrappedDataSink;

    public AesCipherDataSink(byte[] bArr, DataSink dataSink) {
        this(bArr, dataSink, null);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSink
    public void close() {
        this.cipher = null;
        this.wrappedDataSink.close();
    }

    @Override // com.google.android.exoplayer2.upstream.DataSink
    public void open(DataSpec dataSpec) {
        this.wrappedDataSink.open(dataSpec);
        this.cipher = new AesFlushingCipher(1, this.secretKey, CryptoUtil.getFNV64Hash(dataSpec.key), dataSpec.absoluteStreamPosition);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSink
    public void write(byte[] bArr, int i9, int i10) {
        if (this.scratch == null) {
            this.cipher.updateInPlace(bArr, i9, i10);
            this.wrappedDataSink.write(bArr, i9, i10);
            return;
        }
        int i11 = 0;
        while (i11 < i10) {
            int iMin = Math.min(i10 - i11, this.scratch.length);
            this.cipher.update(bArr, i9 + i11, iMin, this.scratch, 0);
            this.wrappedDataSink.write(this.scratch, 0, iMin);
            i11 += iMin;
        }
    }

    public AesCipherDataSink(byte[] bArr, DataSink dataSink, byte[] bArr2) {
        this.wrappedDataSink = dataSink;
        this.secretKey = bArr;
        this.scratch = bArr2;
    }
}
