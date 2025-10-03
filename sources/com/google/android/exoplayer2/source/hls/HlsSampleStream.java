package com.google.android.exoplayer2.source.hls;

import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.SampleStream;

/* loaded from: classes.dex */
final class HlsSampleStream implements SampleStream {
    private int sampleQueueIndex = -1;
    private final HlsSampleStreamWrapper sampleStreamWrapper;
    private final int trackGroupIndex;

    public HlsSampleStream(HlsSampleStreamWrapper hlsSampleStreamWrapper, int i9) {
        this.sampleStreamWrapper = hlsSampleStreamWrapper;
        this.trackGroupIndex = i9;
    }

    private boolean ensureBoundSampleQueue() {
        if (this.sampleQueueIndex != -1) {
            return true;
        }
        int iBindSampleQueueToSampleStream = this.sampleStreamWrapper.bindSampleQueueToSampleStream(this.trackGroupIndex);
        this.sampleQueueIndex = iBindSampleQueueToSampleStream;
        return iBindSampleQueueToSampleStream != -1;
    }

    @Override // com.google.android.exoplayer2.source.SampleStream
    public boolean isReady() {
        return ensureBoundSampleQueue() && this.sampleStreamWrapper.isReady(this.sampleQueueIndex);
    }

    @Override // com.google.android.exoplayer2.source.SampleStream
    public void maybeThrowError() throws SampleQueueMappingException {
        if (!ensureBoundSampleQueue() && this.sampleStreamWrapper.isMappingFinished()) {
            throw new SampleQueueMappingException(this.sampleStreamWrapper.getTrackGroups().get(this.trackGroupIndex).getFormat(0).sampleMimeType);
        }
        this.sampleStreamWrapper.maybeThrowError();
    }

    @Override // com.google.android.exoplayer2.source.SampleStream
    public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z8) {
        if (ensureBoundSampleQueue()) {
            return this.sampleStreamWrapper.readData(this.sampleQueueIndex, formatHolder, decoderInputBuffer, z8);
        }
        return -3;
    }

    @Override // com.google.android.exoplayer2.source.SampleStream
    public int skipData(long j9) {
        if (ensureBoundSampleQueue()) {
            return this.sampleStreamWrapper.skipData(this.sampleQueueIndex, j9);
        }
        return 0;
    }

    public void unbindSampleQueue() {
        if (this.sampleQueueIndex != -1) {
            this.sampleStreamWrapper.unbindSampleQueue(this.trackGroupIndex);
            this.sampleQueueIndex = -1;
        }
    }
}
