package com.google.android.exoplayer2.extractor.wav;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.util.Util;

/* loaded from: classes.dex */
final class WavHeader implements SeekMap {
    private final int averageBytesPerSecond;
    private final int bitsPerSample;
    private final int blockAlignment;
    private long dataSize;
    private long dataStartPosition;
    private final int encoding;
    private final int numChannels;
    private final int sampleRateHz;

    public WavHeader(int i9, int i10, int i11, int i12, int i13, int i14) {
        this.numChannels = i9;
        this.sampleRateHz = i10;
        this.averageBytesPerSecond = i11;
        this.blockAlignment = i12;
        this.bitsPerSample = i13;
        this.encoding = i14;
    }

    public int getBitrate() {
        return this.sampleRateHz * this.bitsPerSample * this.numChannels;
    }

    public int getBytesPerFrame() {
        return this.blockAlignment;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public long getDurationUs() {
        return ((this.dataSize / this.blockAlignment) * C3322C.MICROS_PER_SECOND) / this.sampleRateHz;
    }

    public int getEncoding() {
        return this.encoding;
    }

    public int getNumChannels() {
        return this.numChannels;
    }

    public int getSampleRateHz() {
        return this.sampleRateHz;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j9) {
        long j10 = (this.averageBytesPerSecond * j9) / C3322C.MICROS_PER_SECOND;
        int i9 = this.blockAlignment;
        long jConstrainValue = Util.constrainValue((j10 / i9) * i9, 0L, this.dataSize - i9);
        long j11 = this.dataStartPosition + jConstrainValue;
        long timeUs = getTimeUs(j11);
        SeekPoint seekPoint = new SeekPoint(timeUs, j11);
        if (timeUs < j9) {
            long j12 = this.dataSize;
            int i10 = this.blockAlignment;
            if (jConstrainValue != j12 - i10) {
                long j13 = j11 + i10;
                return new SeekMap.SeekPoints(seekPoint, new SeekPoint(getTimeUs(j13), j13));
            }
        }
        return new SeekMap.SeekPoints(seekPoint);
    }

    public long getTimeUs(long j9) {
        return (Math.max(0L, j9 - this.dataStartPosition) * C3322C.MICROS_PER_SECOND) / this.averageBytesPerSecond;
    }

    public boolean hasDataBounds() {
        return (this.dataStartPosition == 0 || this.dataSize == 0) ? false : true;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }

    public void setDataBounds(long j9, long j10) {
        this.dataStartPosition = j9;
        this.dataSize = j10;
    }
}
