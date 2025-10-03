package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

/* loaded from: classes.dex */
final class TrackSampleTable {
    public final long durationUs;
    public final int[] flags;
    public final int maximumSize;
    public final long[] offsets;
    public final int sampleCount;
    public final int[] sizes;
    public final long[] timestampsUs;

    public TrackSampleTable(long[] jArr, int[] iArr, int i9, long[] jArr2, int[] iArr2, long j9) {
        Assertions.checkArgument(iArr.length == jArr2.length);
        Assertions.checkArgument(jArr.length == jArr2.length);
        Assertions.checkArgument(iArr2.length == jArr2.length);
        this.offsets = jArr;
        this.sizes = iArr;
        this.maximumSize = i9;
        this.timestampsUs = jArr2;
        this.flags = iArr2;
        this.durationUs = j9;
        this.sampleCount = jArr.length;
    }

    public int getIndexOfEarlierOrEqualSynchronizationSample(long j9) {
        for (int iBinarySearchFloor = Util.binarySearchFloor(this.timestampsUs, j9, true, false); iBinarySearchFloor >= 0; iBinarySearchFloor--) {
            if ((this.flags[iBinarySearchFloor] & 1) != 0) {
                return iBinarySearchFloor;
            }
        }
        return -1;
    }

    public int getIndexOfLaterOrEqualSynchronizationSample(long j9) {
        for (int iBinarySearchCeil = Util.binarySearchCeil(this.timestampsUs, j9, true, false); iBinarySearchCeil < this.timestampsUs.length; iBinarySearchCeil++) {
            if ((this.flags[iBinarySearchCeil] & 1) != 0) {
                return iBinarySearchCeil;
            }
        }
        return -1;
    }
}
