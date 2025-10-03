package com.google.android.exoplayer2.extractor.mp3;

import android.util.Log;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.extractor.MpegAudioHeader;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.extractor.mp3.Mp3Extractor;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

/* loaded from: classes.dex */
final class VbriSeeker implements Mp3Extractor.Seeker {
    private static final String TAG = "VbriSeeker";
    private final long durationUs;
    private final long[] positions;
    private final long[] timesUs;

    private VbriSeeker(long[] jArr, long[] jArr2, long j9) {
        this.timesUs = jArr;
        this.positions = jArr2;
        this.durationUs = j9;
    }

    public static VbriSeeker create(long j9, long j10, MpegAudioHeader mpegAudioHeader, ParsableByteArray parsableByteArray) {
        int unsignedByte;
        parsableByteArray.skipBytes(10);
        int i9 = parsableByteArray.readInt();
        if (i9 <= 0) {
            return null;
        }
        int i10 = mpegAudioHeader.sampleRate;
        long jScaleLargeTimestamp = Util.scaleLargeTimestamp(i9, (i10 >= 32000 ? 1152 : 576) * C3322C.MICROS_PER_SECOND, i10);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int unsignedShort2 = parsableByteArray.readUnsignedShort();
        int unsignedShort3 = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(2);
        long j11 = j10 + mpegAudioHeader.frameSize;
        long[] jArr = new long[unsignedShort];
        long[] jArr2 = new long[unsignedShort];
        int i11 = 0;
        long j12 = j10;
        while (i11 < unsignedShort) {
            long j13 = j11;
            long j14 = jScaleLargeTimestamp;
            jArr[i11] = (i11 * jScaleLargeTimestamp) / unsignedShort;
            jArr2[i11] = Math.max(j12, j13);
            if (unsignedShort3 == 1) {
                unsignedByte = parsableByteArray.readUnsignedByte();
            } else if (unsignedShort3 == 2) {
                unsignedByte = parsableByteArray.readUnsignedShort();
            } else if (unsignedShort3 == 3) {
                unsignedByte = parsableByteArray.readUnsignedInt24();
            } else {
                if (unsignedShort3 != 4) {
                    return null;
                }
                unsignedByte = parsableByteArray.readUnsignedIntToInt();
            }
            j12 += unsignedByte * unsignedShort2;
            i11++;
            j11 = j13;
            jScaleLargeTimestamp = j14;
        }
        long j15 = jScaleLargeTimestamp;
        if (j9 != -1 && j9 != j12) {
            Log.w(TAG, "VBRI data size mismatch: " + j9 + ", " + j12);
        }
        return new VbriSeeker(jArr, jArr2, j15);
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j9) {
        int iBinarySearchFloor = Util.binarySearchFloor(this.timesUs, j9, true, true);
        SeekPoint seekPoint = new SeekPoint(this.timesUs[iBinarySearchFloor], this.positions[iBinarySearchFloor]);
        if (seekPoint.timeUs >= j9 || iBinarySearchFloor == this.timesUs.length - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        int i9 = iBinarySearchFloor + 1;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(this.timesUs[i9], this.positions[i9]));
    }

    @Override // com.google.android.exoplayer2.extractor.mp3.Mp3Extractor.Seeker
    public long getTimeUs(long j9) {
        return this.timesUs[Util.binarySearchFloor(this.positions, j9, true, true)];
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }
}
