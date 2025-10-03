package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Arrays;

/* loaded from: classes.dex */
public interface TrackOutput {

    public static final class CryptoData {
        public final int clearBlocks;
        public final int cryptoMode;
        public final int encryptedBlocks;
        public final byte[] encryptionKey;

        public CryptoData(int i9, byte[] bArr, int i10, int i11) {
            this.cryptoMode = i9;
            this.encryptionKey = bArr;
            this.encryptedBlocks = i10;
            this.clearBlocks = i11;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || CryptoData.class != obj.getClass()) {
                return false;
            }
            CryptoData cryptoData = (CryptoData) obj;
            return this.cryptoMode == cryptoData.cryptoMode && this.encryptedBlocks == cryptoData.encryptedBlocks && this.clearBlocks == cryptoData.clearBlocks && Arrays.equals(this.encryptionKey, cryptoData.encryptionKey);
        }

        public int hashCode() {
            return (((((this.cryptoMode * 31) + Arrays.hashCode(this.encryptionKey)) * 31) + this.encryptedBlocks) * 31) + this.clearBlocks;
        }
    }

    void format(Format format);

    int sampleData(ExtractorInput extractorInput, int i9, boolean z8);

    void sampleData(ParsableByteArray parsableByteArray, int i9);

    void sampleMetadata(long j9, int i9, int i10, int i11, CryptoData cryptoData);
}
