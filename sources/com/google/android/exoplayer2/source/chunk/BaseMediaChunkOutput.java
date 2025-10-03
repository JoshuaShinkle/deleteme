package com.google.android.exoplayer2.source.chunk;

import android.util.Log;
import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.source.chunk.ChunkExtractorWrapper;

/* loaded from: classes.dex */
final class BaseMediaChunkOutput implements ChunkExtractorWrapper.TrackOutputProvider {
    private static final String TAG = "BaseMediaChunkOutput";
    private final SampleQueue[] sampleQueues;
    private final int[] trackTypes;

    public BaseMediaChunkOutput(int[] iArr, SampleQueue[] sampleQueueArr) {
        this.trackTypes = iArr;
        this.sampleQueues = sampleQueueArr;
    }

    public int[] getWriteIndices() {
        int[] iArr = new int[this.sampleQueues.length];
        int i9 = 0;
        while (true) {
            SampleQueue[] sampleQueueArr = this.sampleQueues;
            if (i9 >= sampleQueueArr.length) {
                return iArr;
            }
            SampleQueue sampleQueue = sampleQueueArr[i9];
            if (sampleQueue != null) {
                iArr[i9] = sampleQueue.getWriteIndex();
            }
            i9++;
        }
    }

    public void setSampleOffsetUs(long j9) {
        for (SampleQueue sampleQueue : this.sampleQueues) {
            if (sampleQueue != null) {
                sampleQueue.setSampleOffsetUs(j9);
            }
        }
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkExtractorWrapper.TrackOutputProvider
    public TrackOutput track(int i9, int i10) {
        int i11 = 0;
        while (true) {
            int[] iArr = this.trackTypes;
            if (i11 >= iArr.length) {
                Log.e(TAG, "Unmatched track of type: " + i10);
                return new DummyTrackOutput();
            }
            if (i10 == iArr[i11]) {
                return this.sampleQueues[i11];
            }
            i11++;
        }
    }
}
