package com.google.android.exoplayer2.extractor.p037ts;

import android.util.Log;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.p037ts.TsPayloadReader;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.ParsableNalUnitBitArray;
import com.google.common.primitives.UnsignedBytes;
import java.util.Collections;

/* loaded from: classes.dex */
public final class H265Reader implements ElementaryStreamReader {
    private static final int BLA_W_LP = 16;
    private static final int CRA_NUT = 21;
    private static final int PPS_NUT = 34;
    private static final int PREFIX_SEI_NUT = 39;
    private static final int RASL_R = 9;
    private static final int SPS_NUT = 33;
    private static final int SUFFIX_SEI_NUT = 40;
    private static final String TAG = "H265Reader";
    private static final int VPS_NUT = 32;
    private String formatId;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs;
    private SampleReader sampleReader;
    private final SeiReader seiReader;
    private long totalBytesWritten;
    private final boolean[] prefixFlags = new boolean[3];
    private final NalUnitTargetBuffer vps = new NalUnitTargetBuffer(32, 128);
    private final NalUnitTargetBuffer sps = new NalUnitTargetBuffer(33, 128);
    private final NalUnitTargetBuffer pps = new NalUnitTargetBuffer(34, 128);
    private final NalUnitTargetBuffer prefixSei = new NalUnitTargetBuffer(39, 128);
    private final NalUnitTargetBuffer suffixSei = new NalUnitTargetBuffer(40, 128);
    private final ParsableByteArray seiWrapper = new ParsableByteArray();

    public static final class SampleReader {
        private static final int FIRST_SLICE_FLAG_OFFSET = 2;
        private boolean isFirstParameterSet;
        private boolean isFirstSlice;
        private boolean lookingForFirstSliceFlag;
        private int nalUnitBytesRead;
        private boolean nalUnitHasKeyframeData;
        private long nalUnitStartPosition;
        private long nalUnitTimeUs;
        private final TrackOutput output;
        private boolean readingSample;
        private boolean sampleIsKeyframe;
        private long samplePosition;
        private long sampleTimeUs;
        private boolean writingParameterSets;

        public SampleReader(TrackOutput trackOutput) {
            this.output = trackOutput;
        }

        private void outputSample(int i9) {
            boolean z8 = this.sampleIsKeyframe;
            this.output.sampleMetadata(this.sampleTimeUs, z8 ? 1 : 0, (int) (this.nalUnitStartPosition - this.samplePosition), i9, null);
        }

        public void endNalUnit(long j9, int i9) {
            if (this.writingParameterSets && this.isFirstSlice) {
                this.sampleIsKeyframe = this.nalUnitHasKeyframeData;
                this.writingParameterSets = false;
            } else if (this.isFirstParameterSet || this.isFirstSlice) {
                if (this.readingSample) {
                    outputSample(i9 + ((int) (j9 - this.nalUnitStartPosition)));
                }
                this.samplePosition = this.nalUnitStartPosition;
                this.sampleTimeUs = this.nalUnitTimeUs;
                this.readingSample = true;
                this.sampleIsKeyframe = this.nalUnitHasKeyframeData;
            }
        }

        public void readNalUnitData(byte[] bArr, int i9, int i10) {
            if (this.lookingForFirstSliceFlag) {
                int i11 = this.nalUnitBytesRead;
                int i12 = (i9 + 2) - i11;
                if (i12 >= i10) {
                    this.nalUnitBytesRead = i11 + (i10 - i9);
                } else {
                    this.isFirstSlice = (bArr[i12] & UnsignedBytes.MAX_POWER_OF_TWO) != 0;
                    this.lookingForFirstSliceFlag = false;
                }
            }
        }

        public void reset() {
            this.lookingForFirstSliceFlag = false;
            this.isFirstSlice = false;
            this.isFirstParameterSet = false;
            this.readingSample = false;
            this.writingParameterSets = false;
        }

        public void startNalUnit(long j9, int i9, int i10, long j10) {
            this.isFirstSlice = false;
            this.isFirstParameterSet = false;
            this.nalUnitTimeUs = j10;
            this.nalUnitBytesRead = 0;
            this.nalUnitStartPosition = j9;
            if (i10 >= 32) {
                if (!this.writingParameterSets && this.readingSample) {
                    outputSample(i9);
                    this.readingSample = false;
                }
                if (i10 <= 34) {
                    this.isFirstParameterSet = !this.writingParameterSets;
                    this.writingParameterSets = true;
                }
            }
            boolean z8 = i10 >= 16 && i10 <= 21;
            this.nalUnitHasKeyframeData = z8;
            this.lookingForFirstSliceFlag = z8 || i10 <= 9;
        }
    }

    public H265Reader(SeiReader seiReader) {
        this.seiReader = seiReader;
    }

    private void endNalUnit(long j9, int i9, int i10, long j10) {
        if (this.hasOutputFormat) {
            this.sampleReader.endNalUnit(j9, i9);
        } else {
            this.vps.endNalUnit(i10);
            this.sps.endNalUnit(i10);
            this.pps.endNalUnit(i10);
            if (this.vps.isCompleted() && this.sps.isCompleted() && this.pps.isCompleted()) {
                this.output.format(parseMediaFormat(this.formatId, this.vps, this.sps, this.pps));
                this.hasOutputFormat = true;
            }
        }
        if (this.prefixSei.endNalUnit(i10)) {
            NalUnitTargetBuffer nalUnitTargetBuffer = this.prefixSei;
            this.seiWrapper.reset(this.prefixSei.nalData, NalUnitUtil.unescapeStream(nalUnitTargetBuffer.nalData, nalUnitTargetBuffer.nalLength));
            this.seiWrapper.skipBytes(5);
            this.seiReader.consume(j10, this.seiWrapper);
        }
        if (this.suffixSei.endNalUnit(i10)) {
            NalUnitTargetBuffer nalUnitTargetBuffer2 = this.suffixSei;
            this.seiWrapper.reset(this.suffixSei.nalData, NalUnitUtil.unescapeStream(nalUnitTargetBuffer2.nalData, nalUnitTargetBuffer2.nalLength));
            this.seiWrapper.skipBytes(5);
            this.seiReader.consume(j10, this.seiWrapper);
        }
    }

    private void nalUnitData(byte[] bArr, int i9, int i10) {
        if (this.hasOutputFormat) {
            this.sampleReader.readNalUnitData(bArr, i9, i10);
        } else {
            this.vps.appendToNalUnit(bArr, i9, i10);
            this.sps.appendToNalUnit(bArr, i9, i10);
            this.pps.appendToNalUnit(bArr, i9, i10);
        }
        this.prefixSei.appendToNalUnit(bArr, i9, i10);
        this.suffixSei.appendToNalUnit(bArr, i9, i10);
    }

    private static Format parseMediaFormat(String str, NalUnitTargetBuffer nalUnitTargetBuffer, NalUnitTargetBuffer nalUnitTargetBuffer2, NalUnitTargetBuffer nalUnitTargetBuffer3) {
        float f9;
        int i9 = nalUnitTargetBuffer.nalLength;
        byte[] bArr = new byte[nalUnitTargetBuffer2.nalLength + i9 + nalUnitTargetBuffer3.nalLength];
        System.arraycopy(nalUnitTargetBuffer.nalData, 0, bArr, 0, i9);
        System.arraycopy(nalUnitTargetBuffer2.nalData, 0, bArr, nalUnitTargetBuffer.nalLength, nalUnitTargetBuffer2.nalLength);
        System.arraycopy(nalUnitTargetBuffer3.nalData, 0, bArr, nalUnitTargetBuffer.nalLength + nalUnitTargetBuffer2.nalLength, nalUnitTargetBuffer3.nalLength);
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(nalUnitTargetBuffer2.nalData, 0, nalUnitTargetBuffer2.nalLength);
        parsableNalUnitBitArray.skipBits(44);
        int bits = parsableNalUnitBitArray.readBits(3);
        parsableNalUnitBitArray.skipBit();
        parsableNalUnitBitArray.skipBits(88);
        parsableNalUnitBitArray.skipBits(8);
        int i10 = 0;
        for (int i11 = 0; i11 < bits; i11++) {
            if (parsableNalUnitBitArray.readBit()) {
                i10 += 89;
            }
            if (parsableNalUnitBitArray.readBit()) {
                i10 += 8;
            }
        }
        parsableNalUnitBitArray.skipBits(i10);
        if (bits > 0) {
            parsableNalUnitBitArray.skipBits((8 - bits) * 2);
        }
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (unsignedExpGolombCodedInt == 3) {
            parsableNalUnitBitArray.skipBit();
        }
        int unsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int unsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (parsableNalUnitBitArray.readBit()) {
            int unsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt5 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt6 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt7 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            unsignedExpGolombCodedInt2 -= ((unsignedExpGolombCodedInt == 1 || unsignedExpGolombCodedInt == 2) ? 2 : 1) * (unsignedExpGolombCodedInt4 + unsignedExpGolombCodedInt5);
            unsignedExpGolombCodedInt3 -= (unsignedExpGolombCodedInt == 1 ? 2 : 1) * (unsignedExpGolombCodedInt6 + unsignedExpGolombCodedInt7);
        }
        int i12 = unsignedExpGolombCodedInt2;
        int i13 = unsignedExpGolombCodedInt3;
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int unsignedExpGolombCodedInt8 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        for (int i14 = parsableNalUnitBitArray.readBit() ? 0 : bits; i14 <= bits; i14++) {
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        }
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (parsableNalUnitBitArray.readBit() && parsableNalUnitBitArray.readBit()) {
            skipScalingList(parsableNalUnitBitArray);
        }
        parsableNalUnitBitArray.skipBits(2);
        if (parsableNalUnitBitArray.readBit()) {
            parsableNalUnitBitArray.skipBits(8);
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.skipBit();
        }
        skipShortTermRefPicSets(parsableNalUnitBitArray);
        if (parsableNalUnitBitArray.readBit()) {
            for (int i15 = 0; i15 < parsableNalUnitBitArray.readUnsignedExpGolombCodedInt(); i15++) {
                parsableNalUnitBitArray.skipBits(unsignedExpGolombCodedInt8 + 4 + 1);
            }
        }
        parsableNalUnitBitArray.skipBits(2);
        float f10 = 1.0f;
        if (parsableNalUnitBitArray.readBit() && parsableNalUnitBitArray.readBit()) {
            int bits2 = parsableNalUnitBitArray.readBits(8);
            if (bits2 == 255) {
                int bits3 = parsableNalUnitBitArray.readBits(16);
                int bits4 = parsableNalUnitBitArray.readBits(16);
                if (bits3 != 0 && bits4 != 0) {
                    f10 = bits3 / bits4;
                }
            } else {
                float[] fArr = NalUnitUtil.ASPECT_RATIO_IDC_VALUES;
                if (bits2 < fArr.length) {
                    f9 = fArr[bits2];
                } else {
                    Log.w(TAG, "Unexpected aspect_ratio_idc value: " + bits2);
                }
            }
            f9 = f10;
        } else {
            f9 = f10;
        }
        return Format.createVideoSampleFormat(str, MimeTypes.VIDEO_H265, null, -1, -1, i12, i13, -1.0f, Collections.singletonList(bArr), -1, f9, null);
    }

    private static void skipScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        for (int i9 = 0; i9 < 4; i9++) {
            int i10 = 0;
            while (i10 < 6) {
                int i11 = 1;
                if (parsableNalUnitBitArray.readBit()) {
                    int iMin = Math.min(64, 1 << ((i9 << 1) + 4));
                    if (i9 > 1) {
                        parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                    }
                    for (int i12 = 0; i12 < iMin; i12++) {
                        parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                    }
                } else {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                }
                if (i9 == 3) {
                    i11 = 3;
                }
                i10 += i11;
            }
        }
    }

    private static void skipShortTermRefPicSets(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        boolean bit = false;
        int i9 = 0;
        for (int i10 = 0; i10 < unsignedExpGolombCodedInt; i10++) {
            if (i10 != 0) {
                bit = parsableNalUnitBitArray.readBit();
            }
            if (bit) {
                parsableNalUnitBitArray.skipBit();
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                for (int i11 = 0; i11 <= i9; i11++) {
                    if (parsableNalUnitBitArray.readBit()) {
                        parsableNalUnitBitArray.skipBit();
                    }
                }
            } else {
                int unsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int unsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int i12 = unsignedExpGolombCodedInt2 + unsignedExpGolombCodedInt3;
                for (int i13 = 0; i13 < unsignedExpGolombCodedInt2; i13++) {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    parsableNalUnitBitArray.skipBit();
                }
                for (int i14 = 0; i14 < unsignedExpGolombCodedInt3; i14++) {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    parsableNalUnitBitArray.skipBit();
                }
                i9 = i12;
            }
        }
    }

    private void startNalUnit(long j9, int i9, int i10, long j10) {
        if (this.hasOutputFormat) {
            this.sampleReader.startNalUnit(j9, i9, i10, j10);
        } else {
            this.vps.startNalUnit(i10);
            this.sps.startNalUnit(i10);
            this.pps.startNalUnit(i10);
        }
        this.prefixSei.startNalUnit(i10);
        this.suffixSei.startNalUnit(i10);
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            int position = parsableByteArray.getPosition();
            int iLimit = parsableByteArray.limit();
            byte[] bArr = parsableByteArray.data;
            this.totalBytesWritten += parsableByteArray.bytesLeft();
            this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
            while (position < iLimit) {
                int iFindNalUnit = NalUnitUtil.findNalUnit(bArr, position, iLimit, this.prefixFlags);
                if (iFindNalUnit == iLimit) {
                    nalUnitData(bArr, position, iLimit);
                    return;
                }
                int h265NalUnitType = NalUnitUtil.getH265NalUnitType(bArr, iFindNalUnit);
                int i9 = iFindNalUnit - position;
                if (i9 > 0) {
                    nalUnitData(bArr, position, iFindNalUnit);
                }
                int i10 = iLimit - iFindNalUnit;
                long j9 = this.totalBytesWritten - i10;
                endNalUnit(j9, i10, i9 < 0 ? -i9 : 0, this.pesTimeUs);
                startNalUnit(j9, i10, h265NalUnitType, this.pesTimeUs);
                position = iFindNalUnit + 3;
            }
        }
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        TrackOutput trackOutputTrack = extractorOutput.track(trackIdGenerator.getTrackId(), 2);
        this.output = trackOutputTrack;
        this.sampleReader = new SampleReader(trackOutputTrack);
        this.seiReader.createTracks(extractorOutput, trackIdGenerator);
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
        this.vps.reset();
        this.sps.reset();
        this.pps.reset();
        this.prefixSei.reset();
        this.suffixSei.reset();
        this.sampleReader.reset();
        this.totalBytesWritten = 0L;
    }
}
