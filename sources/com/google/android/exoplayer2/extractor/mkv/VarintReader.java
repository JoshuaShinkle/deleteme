package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.common.primitives.UnsignedBytes;

/* loaded from: classes.dex */
final class VarintReader {
    private static final int STATE_BEGIN_READING = 0;
    private static final int STATE_READ_CONTENTS = 1;
    private static final long[] VARINT_LENGTH_MASKS = {128, 64, 32, 16, 8, 4, 2, 1};
    private int length;
    private final byte[] scratch = new byte[8];
    private int state;

    public static long assembleVarint(byte[] bArr, int i9, boolean z8) {
        long j9 = bArr[0] & 255;
        if (z8) {
            j9 &= ~VARINT_LENGTH_MASKS[i9 - 1];
        }
        for (int i10 = 1; i10 < i9; i10++) {
            j9 = (j9 << 8) | (bArr[i10] & 255);
        }
        return j9;
    }

    public static int parseUnsignedVarintLength(int i9) {
        int i10 = 0;
        while (true) {
            long[] jArr = VARINT_LENGTH_MASKS;
            if (i10 >= jArr.length) {
                return -1;
            }
            if ((jArr[i10] & i9) != 0) {
                return i10 + 1;
            }
            i10++;
        }
    }

    public int getLastLength() {
        return this.length;
    }

    public long readUnsignedVarint(ExtractorInput extractorInput, boolean z8, boolean z9, int i9) {
        if (this.state == 0) {
            if (!extractorInput.readFully(this.scratch, 0, 1, z8)) {
                return -1L;
            }
            int unsignedVarintLength = parseUnsignedVarintLength(this.scratch[0] & UnsignedBytes.MAX_VALUE);
            this.length = unsignedVarintLength;
            if (unsignedVarintLength == -1) {
                throw new IllegalStateException("No valid varint length mask found");
            }
            this.state = 1;
        }
        int i10 = this.length;
        if (i10 > i9) {
            this.state = 0;
            return -2L;
        }
        if (i10 != 1) {
            extractorInput.readFully(this.scratch, 1, i10 - 1);
        }
        this.state = 0;
        return assembleVarint(this.scratch, this.length, z9);
    }

    public void reset() {
        this.state = 0;
        this.length = 0;
    }
}
