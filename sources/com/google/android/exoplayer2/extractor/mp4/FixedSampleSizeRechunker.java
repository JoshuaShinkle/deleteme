package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.util.Util;

/* loaded from: classes.dex */
final class FixedSampleSizeRechunker {
    private static final int MAX_SAMPLE_SIZE = 8192;

    public static final class Results {
        public final long duration;
        public final int[] flags;
        public final int maximumSize;
        public final long[] offsets;
        public final int[] sizes;
        public final long[] timestamps;

        private Results(long[] jArr, int[] iArr, int i9, long[] jArr2, int[] iArr2, long j9) {
            this.offsets = jArr;
            this.sizes = iArr;
            this.maximumSize = i9;
            this.timestamps = jArr2;
            this.flags = iArr2;
            this.duration = j9;
        }
    }

    public static Results rechunk(int i9, long[] jArr, int[] iArr, long j9) {
        int i10 = 8192 / i9;
        int iCeilDivide = 0;
        for (int i11 : iArr) {
            iCeilDivide += Util.ceilDivide(i11, i10);
        }
        long[] jArr2 = new long[iCeilDivide];
        int[] iArr2 = new int[iCeilDivide];
        long[] jArr3 = new long[iCeilDivide];
        int[] iArr3 = new int[iCeilDivide];
        int i12 = 0;
        int i13 = 0;
        int iMax = 0;
        for (int i14 = 0; i14 < iArr.length; i14++) {
            int i15 = iArr[i14];
            long j10 = jArr[i14];
            while (i15 > 0) {
                int iMin = Math.min(i10, i15);
                jArr2[i13] = j10;
                int i16 = i9 * iMin;
                iArr2[i13] = i16;
                iMax = Math.max(iMax, i16);
                jArr3[i13] = i12 * j9;
                iArr3[i13] = 1;
                j10 += iArr2[i13];
                i12 += iMin;
                i15 -= iMin;
                i13++;
            }
        }
        return new Results(jArr2, iArr2, iMax, jArr3, iArr3, j9 * i12);
    }
}
