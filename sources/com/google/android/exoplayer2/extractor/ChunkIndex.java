package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.util.Util;

/* loaded from: classes.dex */
public final class ChunkIndex implements SeekMap {
    private final long durationUs;
    public final long[] durationsUs;
    public final int length;
    public final long[] offsets;
    public final int[] sizes;
    public final long[] timesUs;

    public ChunkIndex(int[] iArr, long[] jArr, long[] jArr2, long[] jArr3) {
        this.sizes = iArr;
        this.offsets = jArr;
        this.durationsUs = jArr2;
        this.timesUs = jArr3;
        int length = iArr.length;
        this.length = length;
        if (length > 0) {
            this.durationUs = jArr2[length - 1] + jArr3[length - 1];
        } else {
            this.durationUs = 0L;
        }
    }

    public int getChunkIndex(long j9) {
        return Util.binarySearchFloor(this.timesUs, j9, true, true);
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j9) {
        int chunkIndex = getChunkIndex(j9);
        SeekPoint seekPoint = new SeekPoint(this.timesUs[chunkIndex], this.offsets[chunkIndex]);
        if (seekPoint.timeUs >= j9 || chunkIndex == this.length - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        int i9 = chunkIndex + 1;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(this.timesUs[i9], this.offsets[i9]));
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }
}
