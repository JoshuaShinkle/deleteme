package com.google.android.exoplayer2.extractor.mp3;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.extractor.MpegAudioHeader;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.extractor.mp3.Mp3Extractor;
import com.google.android.exoplayer2.util.Util;

/* loaded from: classes.dex */
final class ConstantBitrateSeeker implements Mp3Extractor.Seeker {
    private static final int BITS_PER_BYTE = 8;
    private final int bitrate;
    private final long dataSize;
    private final long durationUs;
    private final long firstFramePosition;
    private final int frameSize;

    public ConstantBitrateSeeker(long j9, long j10, MpegAudioHeader mpegAudioHeader) {
        this.firstFramePosition = j10;
        this.frameSize = mpegAudioHeader.frameSize;
        this.bitrate = mpegAudioHeader.bitrate;
        if (j9 == -1) {
            this.dataSize = -1L;
            this.durationUs = C3322C.TIME_UNSET;
        } else {
            this.dataSize = j9 - j10;
            this.durationUs = getTimeUs(j9);
        }
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j9) {
        long j10 = this.dataSize;
        if (j10 == -1) {
            return new SeekMap.SeekPoints(new SeekPoint(0L, this.firstFramePosition));
        }
        int i9 = this.frameSize;
        long jConstrainValue = Util.constrainValue((((this.bitrate * j9) / 8000000) / i9) * i9, 0L, j10 - i9);
        long j11 = this.firstFramePosition + jConstrainValue;
        long timeUs = getTimeUs(j11);
        SeekPoint seekPoint = new SeekPoint(timeUs, j11);
        if (timeUs < j9) {
            long j12 = this.dataSize;
            int i10 = this.frameSize;
            if (jConstrainValue != j12 - i10) {
                long j13 = j11 + i10;
                return new SeekMap.SeekPoints(seekPoint, new SeekPoint(getTimeUs(j13), j13));
            }
        }
        return new SeekMap.SeekPoints(seekPoint);
    }

    @Override // com.google.android.exoplayer2.extractor.mp3.Mp3Extractor.Seeker
    public long getTimeUs(long j9) {
        return ((Math.max(0L, j9 - this.firstFramePosition) * C3322C.MICROS_PER_SECOND) * 8) / this.bitrate;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public boolean isSeekable() {
        return this.dataSize != -1;
    }
}
