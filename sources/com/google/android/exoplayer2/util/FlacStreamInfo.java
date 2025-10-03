package com.google.android.exoplayer2.util;

import com.google.android.exoplayer2.C3322C;

/* loaded from: classes.dex */
public final class FlacStreamInfo {
    public final int bitsPerSample;
    public final int channels;
    public final int maxBlockSize;
    public final int maxFrameSize;
    public final int minBlockSize;
    public final int minFrameSize;
    public final int sampleRate;
    public final long totalSamples;

    public FlacStreamInfo(byte[] bArr, int i9) {
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr);
        parsableBitArray.setPosition(i9 * 8);
        this.minBlockSize = parsableBitArray.readBits(16);
        this.maxBlockSize = parsableBitArray.readBits(16);
        this.minFrameSize = parsableBitArray.readBits(24);
        this.maxFrameSize = parsableBitArray.readBits(24);
        this.sampleRate = parsableBitArray.readBits(20);
        this.channels = parsableBitArray.readBits(3) + 1;
        this.bitsPerSample = parsableBitArray.readBits(5) + 1;
        this.totalSamples = ((parsableBitArray.readBits(4) & 15) << 32) | (parsableBitArray.readBits(32) & 4294967295L);
    }

    public int bitRate() {
        return this.bitsPerSample * this.sampleRate;
    }

    public long durationUs() {
        return (this.totalSamples * C3322C.MICROS_PER_SECOND) / this.sampleRate;
    }

    public int maxDecodedFrameSize() {
        return this.maxBlockSize * this.channels * (this.bitsPerSample / 8);
    }

    public FlacStreamInfo(int i9, int i10, int i11, int i12, int i13, int i14, int i15, long j9) {
        this.minBlockSize = i9;
        this.maxBlockSize = i10;
        this.minFrameSize = i11;
        this.maxFrameSize = i12;
        this.sampleRate = i13;
        this.channels = i14;
        this.bitsPerSample = i15;
        this.totalSamples = j9;
    }
}
