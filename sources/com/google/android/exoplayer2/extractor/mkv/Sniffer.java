package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.common.primitives.UnsignedBytes;

/* loaded from: classes.dex */
final class Sniffer {
    private static final int ID_EBML = 440786851;
    private static final int SEARCH_LENGTH = 1024;
    private int peekLength;
    private final ParsableByteArray scratch = new ParsableByteArray(8);

    private long readUint(ExtractorInput extractorInput) {
        int i9 = 0;
        extractorInput.peekFully(this.scratch.data, 0, 1);
        int i10 = this.scratch.data[0] & UnsignedBytes.MAX_VALUE;
        if (i10 == 0) {
            return Long.MIN_VALUE;
        }
        int i11 = 128;
        int i12 = 0;
        while ((i10 & i11) == 0) {
            i11 >>= 1;
            i12++;
        }
        int i13 = i10 & (~i11);
        extractorInput.peekFully(this.scratch.data, 1, i12);
        while (i9 < i12) {
            i9++;
            i13 = (this.scratch.data[i9] & UnsignedBytes.MAX_VALUE) + (i13 << 8);
        }
        this.peekLength += i12 + 1;
        return i13;
    }

    public boolean sniff(ExtractorInput extractorInput) {
        long length = extractorInput.getLength();
        long j9 = 1024;
        if (length != -1 && length <= 1024) {
            j9 = length;
        }
        int i9 = (int) j9;
        extractorInput.peekFully(this.scratch.data, 0, 4);
        long unsignedInt = this.scratch.readUnsignedInt();
        this.peekLength = 4;
        while (unsignedInt != 440786851) {
            int i10 = this.peekLength + 1;
            this.peekLength = i10;
            if (i10 == i9) {
                return false;
            }
            extractorInput.peekFully(this.scratch.data, 0, 1);
            unsignedInt = ((unsignedInt << 8) & (-256)) | (this.scratch.data[0] & UnsignedBytes.MAX_VALUE);
        }
        long uint = readUint(extractorInput);
        long j10 = this.peekLength;
        if (uint == Long.MIN_VALUE) {
            return false;
        }
        if (length != -1 && j10 + uint >= length) {
            return false;
        }
        while (true) {
            int i11 = this.peekLength;
            long j11 = j10 + uint;
            if (i11 >= j11) {
                return ((long) i11) == j11;
            }
            if (readUint(extractorInput) == Long.MIN_VALUE) {
                return false;
            }
            long uint2 = readUint(extractorInput);
            if (uint2 < 0 || uint2 > 2147483647L) {
                break;
            }
            if (uint2 != 0) {
                extractorInput.advancePeekPosition((int) uint2);
                this.peekLength = (int) (this.peekLength + uint2);
            }
        }
        return false;
    }
}
