package com.google.android.exoplayer2.extractor.p037ts;

import android.util.Pair;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.p037ts.TsPayloadReader;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.util.Arrays;
import java.util.Collections;

/* loaded from: classes.dex */
public final class H262Reader implements ElementaryStreamReader {
    private static final double[] FRAME_RATE_VALUES = {23.976023976023978d, 24.0d, 25.0d, 29.97002997002997d, 30.0d, 50.0d, 59.94005994005994d, 60.0d};
    private static final int START_EXTENSION = 181;
    private static final int START_GROUP = 184;
    private static final int START_PICTURE = 0;
    private static final int START_SEQUENCE_HEADER = 179;
    private String formatId;
    private long frameDurationUs;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs;
    private boolean sampleHasPicture;
    private boolean sampleIsKeyframe;
    private long samplePosition;
    private long sampleTimeUs;
    private boolean startedFirstSample;
    private long totalBytesWritten;
    private final boolean[] prefixFlags = new boolean[4];
    private final CsdBuffer csdBuffer = new CsdBuffer(128);

    public static final class CsdBuffer {
        private static final byte[] START_CODE = {0, 0, 1};
        public byte[] data;
        private boolean isFilling;
        public int length;
        public int sequenceExtensionPosition;

        public CsdBuffer(int i9) {
            this.data = new byte[i9];
        }

        public void onData(byte[] bArr, int i9, int i10) {
            if (this.isFilling) {
                int i11 = i10 - i9;
                byte[] bArr2 = this.data;
                int length = bArr2.length;
                int i12 = this.length;
                if (length < i12 + i11) {
                    this.data = Arrays.copyOf(bArr2, (i12 + i11) * 2);
                }
                System.arraycopy(bArr, i9, this.data, this.length, i11);
                this.length += i11;
            }
        }

        public boolean onStartCode(int i9, int i10) {
            if (this.isFilling) {
                int i11 = this.length - i10;
                this.length = i11;
                if (this.sequenceExtensionPosition != 0 || i9 != H262Reader.START_EXTENSION) {
                    this.isFilling = false;
                    return true;
                }
                this.sequenceExtensionPosition = i11;
            } else if (i9 == H262Reader.START_SEQUENCE_HEADER) {
                this.isFilling = true;
            }
            byte[] bArr = START_CODE;
            onData(bArr, 0, bArr.length);
            return false;
        }

        public void reset() {
            this.isFilling = false;
            this.length = 0;
            this.sequenceExtensionPosition = 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Pair<Format, Long> parseCsdBuffer(CsdBuffer csdBuffer, String str) {
        float f9;
        int i9;
        float f10;
        int i10;
        long j9;
        byte[] bArrCopyOf = Arrays.copyOf(csdBuffer.data, csdBuffer.length);
        int i11 = bArrCopyOf[4] & UnsignedBytes.MAX_VALUE;
        int i12 = bArrCopyOf[5] & UnsignedBytes.MAX_VALUE;
        int i13 = (i11 << 4) | (i12 >> 4);
        int i14 = ((i12 & 15) << 8) | (bArrCopyOf[6] & UnsignedBytes.MAX_VALUE);
        int i15 = (bArrCopyOf[7] & 240) >> 4;
        if (i15 == 2) {
            f9 = i14 * 4;
            i9 = i13 * 3;
        } else if (i15 == 3) {
            f9 = i14 * 16;
            i9 = i13 * 9;
        } else {
            if (i15 != 4) {
                f10 = 1.0f;
                Format formatCreateVideoSampleFormat = Format.createVideoSampleFormat(str, MimeTypes.VIDEO_MPEG2, null, -1, -1, i13, i14, -1.0f, Collections.singletonList(bArrCopyOf), -1, f10, null);
                i10 = (bArrCopyOf[7] & Ascii.f15389SI) - 1;
                if (i10 < 0) {
                    double[] dArr = FRAME_RATE_VALUES;
                    if (i10 < dArr.length) {
                        double d9 = dArr[i10];
                        byte b9 = bArrCopyOf[csdBuffer.sequenceExtensionPosition + 9];
                        int i16 = (b9 & 96) >> 5;
                        if (i16 != (b9 & Ascii.f15392US)) {
                            d9 *= (i16 + 1.0d) / (r0 + 1);
                        }
                        j9 = (long) (1000000.0d / d9);
                    } else {
                        j9 = 0;
                    }
                }
                return Pair.create(formatCreateVideoSampleFormat, Long.valueOf(j9));
            }
            f9 = i14 * 121;
            i9 = i13 * 100;
        }
        f10 = f9 / i9;
        Format formatCreateVideoSampleFormat2 = Format.createVideoSampleFormat(str, MimeTypes.VIDEO_MPEG2, null, -1, -1, i13, i14, -1.0f, Collections.singletonList(bArrCopyOf), -1, f10, null);
        i10 = (bArrCopyOf[7] & Ascii.f15389SI) - 1;
        if (i10 < 0) {
        }
        return Pair.create(formatCreateVideoSampleFormat2, Long.valueOf(j9));
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        boolean z8;
        boolean z9;
        int position = parsableByteArray.getPosition();
        int iLimit = parsableByteArray.limit();
        byte[] bArr = parsableByteArray.data;
        this.totalBytesWritten += parsableByteArray.bytesLeft();
        this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
        while (true) {
            int iFindNalUnit = NalUnitUtil.findNalUnit(bArr, position, iLimit, this.prefixFlags);
            if (iFindNalUnit == iLimit) {
                break;
            }
            int i9 = iFindNalUnit + 3;
            int i10 = parsableByteArray.data[i9] & UnsignedBytes.MAX_VALUE;
            if (!this.hasOutputFormat) {
                int i11 = iFindNalUnit - position;
                if (i11 > 0) {
                    this.csdBuffer.onData(bArr, position, iFindNalUnit);
                }
                if (this.csdBuffer.onStartCode(i10, i11 < 0 ? -i11 : 0)) {
                    Pair<Format, Long> csdBuffer = parseCsdBuffer(this.csdBuffer, this.formatId);
                    this.output.format((Format) csdBuffer.first);
                    this.frameDurationUs = ((Long) csdBuffer.second).longValue();
                    this.hasOutputFormat = true;
                }
            }
            if (i10 == 0 || i10 == START_SEQUENCE_HEADER) {
                int i12 = iLimit - iFindNalUnit;
                if (this.startedFirstSample && this.sampleHasPicture && this.hasOutputFormat) {
                    this.output.sampleMetadata(this.sampleTimeUs, this.sampleIsKeyframe ? 1 : 0, ((int) (this.totalBytesWritten - this.samplePosition)) - i12, i12, null);
                }
                boolean z10 = this.startedFirstSample;
                if (!z10 || this.sampleHasPicture) {
                    this.samplePosition = this.totalBytesWritten - i12;
                    long j9 = this.pesTimeUs;
                    if (j9 == C3322C.TIME_UNSET) {
                        j9 = z10 ? this.sampleTimeUs + this.frameDurationUs : 0L;
                    }
                    this.sampleTimeUs = j9;
                    z8 = false;
                    this.sampleIsKeyframe = false;
                    this.pesTimeUs = C3322C.TIME_UNSET;
                    z9 = true;
                    this.startedFirstSample = true;
                } else {
                    z8 = false;
                    z9 = true;
                }
                this.sampleHasPicture = i10 == 0 ? z9 : z8;
            } else if (i10 == START_GROUP) {
                this.sampleIsKeyframe = true;
            }
            position = i9;
        }
        if (this.hasOutputFormat) {
            return;
        }
        this.csdBuffer.onData(bArr, position, iLimit);
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 2);
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void packetFinished() {
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void packetStarted(long j9, boolean z8) {
        this.pesTimeUs = j9;
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void seek() {
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.csdBuffer.reset();
        this.totalBytesWritten = 0L;
        this.startedFirstSample = false;
    }
}
