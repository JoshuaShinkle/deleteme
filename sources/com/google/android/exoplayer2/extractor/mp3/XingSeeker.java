package com.google.android.exoplayer2.extractor.mp3;

import android.util.Log;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.extractor.MpegAudioHeader;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.extractor.mp3.Mp3Extractor;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* loaded from: classes.dex */
final class XingSeeker implements Mp3Extractor.Seeker {
    private static final String TAG = "XingSeeker";
    private final long dataSize;
    private final long dataStartPosition;
    private final long durationUs;
    private final long[] tableOfContents;
    private final int xingFrameSize;

    private XingSeeker(long j9, int i9, long j10) {
        this(j9, i9, j10, -1L, null);
    }

    public static XingSeeker create(long j9, long j10, MpegAudioHeader mpegAudioHeader, ParsableByteArray parsableByteArray) {
        int unsignedIntToInt;
        int i9 = mpegAudioHeader.samplesPerFrame;
        int i10 = mpegAudioHeader.sampleRate;
        int i11 = parsableByteArray.readInt();
        if ((i11 & 1) != 1 || (unsignedIntToInt = parsableByteArray.readUnsignedIntToInt()) == 0) {
            return null;
        }
        long jScaleLargeTimestamp = Util.scaleLargeTimestamp(unsignedIntToInt, i9 * C3322C.MICROS_PER_SECOND, i10);
        if ((i11 & 6) != 6) {
            return new XingSeeker(j10, mpegAudioHeader.frameSize, jScaleLargeTimestamp);
        }
        long unsignedIntToInt2 = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[100];
        for (int i12 = 0; i12 < 100; i12++) {
            jArr[i12] = parsableByteArray.readUnsignedByte();
        }
        if (j9 != -1) {
            long j11 = j10 + unsignedIntToInt2;
            if (j9 != j11) {
                Log.w(TAG, "XING data size mismatch: " + j9 + ", " + j11);
            }
        }
        return new XingSeeker(j10, mpegAudioHeader.frameSize, jScaleLargeTimestamp, unsignedIntToInt2, jArr);
    }

    private long getTimeUsForTableIndex(int i9) {
        return (this.durationUs * i9) / 100;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j9) {
        if (!isSeekable()) {
            return new SeekMap.SeekPoints(new SeekPoint(0L, this.dataStartPosition + this.xingFrameSize));
        }
        long jConstrainValue = Util.constrainValue(j9, 0L, this.durationUs);
        double d9 = (jConstrainValue * 100.0d) / this.durationUs;
        double d10 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        if (d9 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            if (d9 >= 100.0d) {
                d10 = 256.0d;
            } else {
                int i9 = (int) d9;
                double d11 = this.tableOfContents[i9];
                d10 = d11 + ((d9 - i9) * ((i9 == 99 ? 256.0d : r3[i9 + 1]) - d11));
            }
        }
        return new SeekMap.SeekPoints(new SeekPoint(jConstrainValue, this.dataStartPosition + Util.constrainValue(Math.round((d10 / 256.0d) * this.dataSize), this.xingFrameSize, this.dataSize - 1)));
    }

    @Override // com.google.android.exoplayer2.extractor.mp3.Mp3Extractor.Seeker
    public long getTimeUs(long j9) {
        long j10 = j9 - this.dataStartPosition;
        if (!isSeekable() || j10 <= this.xingFrameSize) {
            return 0L;
        }
        double d9 = (j10 * 256.0d) / this.dataSize;
        int iBinarySearchFloor = Util.binarySearchFloor(this.tableOfContents, (long) d9, true, true);
        long timeUsForTableIndex = getTimeUsForTableIndex(iBinarySearchFloor);
        long j11 = this.tableOfContents[iBinarySearchFloor];
        int i9 = iBinarySearchFloor + 1;
        long timeUsForTableIndex2 = getTimeUsForTableIndex(i9);
        return timeUsForTableIndex + Math.round((j11 == (iBinarySearchFloor == 99 ? 256L : this.tableOfContents[i9]) ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : (d9 - j11) / (r8 - j11)) * (timeUsForTableIndex2 - timeUsForTableIndex));
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public boolean isSeekable() {
        return this.tableOfContents != null;
    }

    private XingSeeker(long j9, int i9, long j10, long j11, long[] jArr) {
        this.dataStartPosition = j9;
        this.xingFrameSize = i9;
        this.durationUs = j10;
        this.dataSize = j11;
        this.tableOfContents = jArr;
    }
}
