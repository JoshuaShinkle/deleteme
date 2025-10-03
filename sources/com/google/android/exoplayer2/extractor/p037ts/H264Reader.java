package com.google.android.exoplayer2.extractor.p037ts;

import android.util.SparseArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.p037ts.TsPayloadReader;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.ParsableNalUnitBitArray;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class H264Reader implements ElementaryStreamReader {
    private static final int NAL_UNIT_TYPE_PPS = 8;
    private static final int NAL_UNIT_TYPE_SEI = 6;
    private static final int NAL_UNIT_TYPE_SPS = 7;
    private final boolean allowNonIdrKeyframes;
    private final boolean detectAccessUnits;
    private String formatId;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs;
    private SampleReader sampleReader;
    private final SeiReader seiReader;
    private long totalBytesWritten;
    private final boolean[] prefixFlags = new boolean[3];
    private final NalUnitTargetBuffer sps = new NalUnitTargetBuffer(7, 128);
    private final NalUnitTargetBuffer pps = new NalUnitTargetBuffer(8, 128);
    private final NalUnitTargetBuffer sei = new NalUnitTargetBuffer(6, 128);
    private final ParsableByteArray seiWrapper = new ParsableByteArray();

    public static final class SampleReader {
        private static final int DEFAULT_BUFFER_SIZE = 128;
        private static final int NAL_UNIT_TYPE_AUD = 9;
        private static final int NAL_UNIT_TYPE_IDR = 5;
        private static final int NAL_UNIT_TYPE_NON_IDR = 1;
        private static final int NAL_UNIT_TYPE_PARTITION_A = 2;
        private final boolean allowNonIdrKeyframes;
        private final ParsableNalUnitBitArray bitArray;
        private byte[] buffer;
        private int bufferLength;
        private final boolean detectAccessUnits;
        private boolean isFilling;
        private long nalUnitStartPosition;
        private long nalUnitTimeUs;
        private int nalUnitType;
        private final TrackOutput output;
        private SliceHeaderData previousSliceHeader;
        private boolean readingSample;
        private boolean sampleIsKeyframe;
        private long samplePosition;
        private long sampleTimeUs;
        private SliceHeaderData sliceHeader;
        private final SparseArray<NalUnitUtil.SpsData> sps = new SparseArray<>();
        private final SparseArray<NalUnitUtil.PpsData> pps = new SparseArray<>();

        public static final class SliceHeaderData {
            private static final int SLICE_TYPE_ALL_I = 7;
            private static final int SLICE_TYPE_I = 2;
            private boolean bottomFieldFlag;
            private boolean bottomFieldFlagPresent;
            private int deltaPicOrderCnt0;
            private int deltaPicOrderCnt1;
            private int deltaPicOrderCntBottom;
            private boolean fieldPicFlag;
            private int frameNum;
            private boolean hasSliceType;
            private boolean idrPicFlag;
            private int idrPicId;
            private boolean isComplete;
            private int nalRefIdc;
            private int picOrderCntLsb;
            private int picParameterSetId;
            private int sliceType;
            private NalUnitUtil.SpsData spsData;

            private SliceHeaderData() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public boolean isFirstVclNalUnitOfPicture(SliceHeaderData sliceHeaderData) {
                boolean z8;
                boolean z9;
                if (this.isComplete) {
                    if (!sliceHeaderData.isComplete || this.frameNum != sliceHeaderData.frameNum || this.picParameterSetId != sliceHeaderData.picParameterSetId || this.fieldPicFlag != sliceHeaderData.fieldPicFlag) {
                        return true;
                    }
                    if (this.bottomFieldFlagPresent && sliceHeaderData.bottomFieldFlagPresent && this.bottomFieldFlag != sliceHeaderData.bottomFieldFlag) {
                        return true;
                    }
                    int i9 = this.nalRefIdc;
                    int i10 = sliceHeaderData.nalRefIdc;
                    if (i9 != i10 && (i9 == 0 || i10 == 0)) {
                        return true;
                    }
                    int i11 = this.spsData.picOrderCountType;
                    if (i11 == 0 && sliceHeaderData.spsData.picOrderCountType == 0 && (this.picOrderCntLsb != sliceHeaderData.picOrderCntLsb || this.deltaPicOrderCntBottom != sliceHeaderData.deltaPicOrderCntBottom)) {
                        return true;
                    }
                    if ((i11 == 1 && sliceHeaderData.spsData.picOrderCountType == 1 && (this.deltaPicOrderCnt0 != sliceHeaderData.deltaPicOrderCnt0 || this.deltaPicOrderCnt1 != sliceHeaderData.deltaPicOrderCnt1)) || (z8 = this.idrPicFlag) != (z9 = sliceHeaderData.idrPicFlag)) {
                        return true;
                    }
                    if (z8 && z9 && this.idrPicId != sliceHeaderData.idrPicId) {
                        return true;
                    }
                }
                return false;
            }

            public void clear() {
                this.hasSliceType = false;
                this.isComplete = false;
            }

            public boolean isISlice() {
                int i9;
                return this.hasSliceType && ((i9 = this.sliceType) == 7 || i9 == 2);
            }

            public void setAll(NalUnitUtil.SpsData spsData, int i9, int i10, int i11, int i12, boolean z8, boolean z9, boolean z10, boolean z11, int i13, int i14, int i15, int i16, int i17) {
                this.spsData = spsData;
                this.nalRefIdc = i9;
                this.sliceType = i10;
                this.frameNum = i11;
                this.picParameterSetId = i12;
                this.fieldPicFlag = z8;
                this.bottomFieldFlagPresent = z9;
                this.bottomFieldFlag = z10;
                this.idrPicFlag = z11;
                this.idrPicId = i13;
                this.picOrderCntLsb = i14;
                this.deltaPicOrderCntBottom = i15;
                this.deltaPicOrderCnt0 = i16;
                this.deltaPicOrderCnt1 = i17;
                this.isComplete = true;
                this.hasSliceType = true;
            }

            public void setSliceType(int i9) {
                this.sliceType = i9;
                this.hasSliceType = true;
            }
        }

        public SampleReader(TrackOutput trackOutput, boolean z8, boolean z9) {
            this.output = trackOutput;
            this.allowNonIdrKeyframes = z8;
            this.detectAccessUnits = z9;
            this.previousSliceHeader = new SliceHeaderData();
            this.sliceHeader = new SliceHeaderData();
            byte[] bArr = new byte[DEFAULT_BUFFER_SIZE];
            this.buffer = bArr;
            this.bitArray = new ParsableNalUnitBitArray(bArr, 0, 0);
            reset();
        }

        private void outputSample(int i9) {
            boolean z8 = this.sampleIsKeyframe;
            this.output.sampleMetadata(this.sampleTimeUs, z8 ? 1 : 0, (int) (this.nalUnitStartPosition - this.samplePosition), i9, null);
        }

        /* JADX WARN: Removed duplicated region for block: B:53:0x00ff  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x0102  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x0106  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x0118  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x011e  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x0152  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void appendToNalUnit(byte[] bArr, int i9, int i10) {
            boolean z8;
            boolean z9;
            boolean bit;
            boolean z10;
            int unsignedExpGolombCodedInt;
            int i11;
            int i12;
            int signedExpGolombCodedInt;
            int i13;
            int signedExpGolombCodedInt2;
            if (this.isFilling) {
                int i14 = i10 - i9;
                byte[] bArr2 = this.buffer;
                int length = bArr2.length;
                int i15 = this.bufferLength;
                if (length < i15 + i14) {
                    this.buffer = Arrays.copyOf(bArr2, (i15 + i14) * 2);
                }
                System.arraycopy(bArr, i9, this.buffer, this.bufferLength, i14);
                int i16 = this.bufferLength + i14;
                this.bufferLength = i16;
                this.bitArray.reset(this.buffer, 0, i16);
                if (this.bitArray.canReadBits(8)) {
                    this.bitArray.skipBit();
                    int bits = this.bitArray.readBits(2);
                    this.bitArray.skipBits(5);
                    if (this.bitArray.canReadExpGolombCodedNum()) {
                        this.bitArray.readUnsignedExpGolombCodedInt();
                        if (this.bitArray.canReadExpGolombCodedNum()) {
                            int unsignedExpGolombCodedInt2 = this.bitArray.readUnsignedExpGolombCodedInt();
                            if (!this.detectAccessUnits) {
                                this.isFilling = false;
                                this.sliceHeader.setSliceType(unsignedExpGolombCodedInt2);
                                return;
                            }
                            if (this.bitArray.canReadExpGolombCodedNum()) {
                                int unsignedExpGolombCodedInt3 = this.bitArray.readUnsignedExpGolombCodedInt();
                                if (this.pps.indexOfKey(unsignedExpGolombCodedInt3) < 0) {
                                    this.isFilling = false;
                                    return;
                                }
                                NalUnitUtil.PpsData ppsData = this.pps.get(unsignedExpGolombCodedInt3);
                                NalUnitUtil.SpsData spsData = this.sps.get(ppsData.seqParameterSetId);
                                if (spsData.separateColorPlaneFlag) {
                                    if (!this.bitArray.canReadBits(2)) {
                                        return;
                                    } else {
                                        this.bitArray.skipBits(2);
                                    }
                                }
                                if (this.bitArray.canReadBits(spsData.frameNumLength)) {
                                    int bits2 = this.bitArray.readBits(spsData.frameNumLength);
                                    if (spsData.frameMbsOnlyFlag) {
                                        z8 = false;
                                        z9 = false;
                                    } else {
                                        if (!this.bitArray.canReadBits(1)) {
                                            return;
                                        }
                                        boolean bit2 = this.bitArray.readBit();
                                        if (bit2) {
                                            if (this.bitArray.canReadBits(1)) {
                                                z8 = bit2;
                                                z9 = true;
                                                bit = this.bitArray.readBit();
                                                z10 = this.nalUnitType != 5;
                                                if (z10) {
                                                    unsignedExpGolombCodedInt = 0;
                                                } else if (!this.bitArray.canReadExpGolombCodedNum()) {
                                                    return;
                                                } else {
                                                    unsignedExpGolombCodedInt = this.bitArray.readUnsignedExpGolombCodedInt();
                                                }
                                                i11 = spsData.picOrderCountType;
                                                if (i11 != 0) {
                                                    if (!this.bitArray.canReadBits(spsData.picOrderCntLsbLength)) {
                                                        return;
                                                    }
                                                    int bits3 = this.bitArray.readBits(spsData.picOrderCntLsbLength);
                                                    if (ppsData.bottomFieldPicOrderInFramePresentFlag && !z8) {
                                                        if (this.bitArray.canReadExpGolombCodedNum()) {
                                                            signedExpGolombCodedInt = this.bitArray.readSignedExpGolombCodedInt();
                                                            i12 = bits3;
                                                            i13 = 0;
                                                            signedExpGolombCodedInt2 = i13;
                                                            this.sliceHeader.setAll(spsData, bits, unsignedExpGolombCodedInt2, bits2, unsignedExpGolombCodedInt3, z8, z9, bit, z10, unsignedExpGolombCodedInt, i12, signedExpGolombCodedInt, i13, signedExpGolombCodedInt2);
                                                            this.isFilling = false;
                                                        }
                                                        return;
                                                    }
                                                    i12 = bits3;
                                                    signedExpGolombCodedInt = 0;
                                                } else {
                                                    if (i11 == 1 && !spsData.deltaPicOrderAlwaysZeroFlag) {
                                                        if (this.bitArray.canReadExpGolombCodedNum()) {
                                                            int signedExpGolombCodedInt3 = this.bitArray.readSignedExpGolombCodedInt();
                                                            if (!ppsData.bottomFieldPicOrderInFramePresentFlag || z8) {
                                                                i13 = signedExpGolombCodedInt3;
                                                                i12 = 0;
                                                                signedExpGolombCodedInt = 0;
                                                                signedExpGolombCodedInt2 = 0;
                                                            } else {
                                                                if (!this.bitArray.canReadExpGolombCodedNum()) {
                                                                    return;
                                                                }
                                                                signedExpGolombCodedInt2 = this.bitArray.readSignedExpGolombCodedInt();
                                                                i13 = signedExpGolombCodedInt3;
                                                                i12 = 0;
                                                                signedExpGolombCodedInt = 0;
                                                            }
                                                            this.sliceHeader.setAll(spsData, bits, unsignedExpGolombCodedInt2, bits2, unsignedExpGolombCodedInt3, z8, z9, bit, z10, unsignedExpGolombCodedInt, i12, signedExpGolombCodedInt, i13, signedExpGolombCodedInt2);
                                                            this.isFilling = false;
                                                        }
                                                        return;
                                                    }
                                                    i12 = 0;
                                                    signedExpGolombCodedInt = 0;
                                                }
                                                i13 = signedExpGolombCodedInt;
                                                signedExpGolombCodedInt2 = i13;
                                                this.sliceHeader.setAll(spsData, bits, unsignedExpGolombCodedInt2, bits2, unsignedExpGolombCodedInt3, z8, z9, bit, z10, unsignedExpGolombCodedInt, i12, signedExpGolombCodedInt, i13, signedExpGolombCodedInt2);
                                                this.isFilling = false;
                                            }
                                            return;
                                        }
                                        z8 = bit2;
                                        z9 = false;
                                    }
                                    bit = z9;
                                    if (this.nalUnitType != 5) {
                                    }
                                    if (z10) {
                                    }
                                    i11 = spsData.picOrderCountType;
                                    if (i11 != 0) {
                                    }
                                    i13 = signedExpGolombCodedInt;
                                    signedExpGolombCodedInt2 = i13;
                                    this.sliceHeader.setAll(spsData, bits, unsignedExpGolombCodedInt2, bits2, unsignedExpGolombCodedInt3, z8, z9, bit, z10, unsignedExpGolombCodedInt, i12, signedExpGolombCodedInt, i13, signedExpGolombCodedInt2);
                                    this.isFilling = false;
                                }
                            }
                        }
                    }
                }
            }
        }

        public void endNalUnit(long j9, int i9) {
            boolean z8 = false;
            if (this.nalUnitType == 9 || (this.detectAccessUnits && this.sliceHeader.isFirstVclNalUnitOfPicture(this.previousSliceHeader))) {
                if (this.readingSample) {
                    outputSample(i9 + ((int) (j9 - this.nalUnitStartPosition)));
                }
                this.samplePosition = this.nalUnitStartPosition;
                this.sampleTimeUs = this.nalUnitTimeUs;
                this.sampleIsKeyframe = false;
                this.readingSample = true;
            }
            boolean z9 = this.sampleIsKeyframe;
            int i10 = this.nalUnitType;
            if (i10 == 5 || (this.allowNonIdrKeyframes && i10 == 1 && this.sliceHeader.isISlice())) {
                z8 = true;
            }
            this.sampleIsKeyframe = z9 | z8;
        }

        public boolean needsSpsPps() {
            return this.detectAccessUnits;
        }

        public void putPps(NalUnitUtil.PpsData ppsData) {
            this.pps.append(ppsData.picParameterSetId, ppsData);
        }

        public void putSps(NalUnitUtil.SpsData spsData) {
            this.sps.append(spsData.seqParameterSetId, spsData);
        }

        public void reset() {
            this.isFilling = false;
            this.readingSample = false;
            this.sliceHeader.clear();
        }

        public void startNalUnit(long j9, int i9, long j10) {
            this.nalUnitType = i9;
            this.nalUnitTimeUs = j10;
            this.nalUnitStartPosition = j9;
            if (!this.allowNonIdrKeyframes || i9 != 1) {
                if (!this.detectAccessUnits) {
                    return;
                }
                if (i9 != 5 && i9 != 1 && i9 != 2) {
                    return;
                }
            }
            SliceHeaderData sliceHeaderData = this.previousSliceHeader;
            this.previousSliceHeader = this.sliceHeader;
            this.sliceHeader = sliceHeaderData;
            sliceHeaderData.clear();
            this.bufferLength = 0;
            this.isFilling = true;
        }
    }

    public H264Reader(SeiReader seiReader, boolean z8, boolean z9) {
        this.seiReader = seiReader;
        this.allowNonIdrKeyframes = z8;
        this.detectAccessUnits = z9;
    }

    private void endNalUnit(long j9, int i9, int i10, long j10) {
        if (!this.hasOutputFormat || this.sampleReader.needsSpsPps()) {
            this.sps.endNalUnit(i10);
            this.pps.endNalUnit(i10);
            if (this.hasOutputFormat) {
                if (this.sps.isCompleted()) {
                    NalUnitTargetBuffer nalUnitTargetBuffer = this.sps;
                    this.sampleReader.putSps(NalUnitUtil.parseSpsNalUnit(nalUnitTargetBuffer.nalData, 3, nalUnitTargetBuffer.nalLength));
                    this.sps.reset();
                } else if (this.pps.isCompleted()) {
                    NalUnitTargetBuffer nalUnitTargetBuffer2 = this.pps;
                    this.sampleReader.putPps(NalUnitUtil.parsePpsNalUnit(nalUnitTargetBuffer2.nalData, 3, nalUnitTargetBuffer2.nalLength));
                    this.pps.reset();
                }
            } else if (this.sps.isCompleted() && this.pps.isCompleted()) {
                ArrayList arrayList = new ArrayList();
                NalUnitTargetBuffer nalUnitTargetBuffer3 = this.sps;
                arrayList.add(Arrays.copyOf(nalUnitTargetBuffer3.nalData, nalUnitTargetBuffer3.nalLength));
                NalUnitTargetBuffer nalUnitTargetBuffer4 = this.pps;
                arrayList.add(Arrays.copyOf(nalUnitTargetBuffer4.nalData, nalUnitTargetBuffer4.nalLength));
                NalUnitTargetBuffer nalUnitTargetBuffer5 = this.sps;
                NalUnitUtil.SpsData spsNalUnit = NalUnitUtil.parseSpsNalUnit(nalUnitTargetBuffer5.nalData, 3, nalUnitTargetBuffer5.nalLength);
                NalUnitTargetBuffer nalUnitTargetBuffer6 = this.pps;
                NalUnitUtil.PpsData ppsNalUnit = NalUnitUtil.parsePpsNalUnit(nalUnitTargetBuffer6.nalData, 3, nalUnitTargetBuffer6.nalLength);
                this.output.format(Format.createVideoSampleFormat(this.formatId, MimeTypes.VIDEO_H264, null, -1, -1, spsNalUnit.width, spsNalUnit.height, -1.0f, arrayList, -1, spsNalUnit.pixelWidthAspectRatio, null));
                this.hasOutputFormat = true;
                this.sampleReader.putSps(spsNalUnit);
                this.sampleReader.putPps(ppsNalUnit);
                this.sps.reset();
                this.pps.reset();
            }
        }
        if (this.sei.endNalUnit(i10)) {
            NalUnitTargetBuffer nalUnitTargetBuffer7 = this.sei;
            this.seiWrapper.reset(this.sei.nalData, NalUnitUtil.unescapeStream(nalUnitTargetBuffer7.nalData, nalUnitTargetBuffer7.nalLength));
            this.seiWrapper.setPosition(4);
            this.seiReader.consume(j10, this.seiWrapper);
        }
        this.sampleReader.endNalUnit(j9, i9);
    }

    private void nalUnitData(byte[] bArr, int i9, int i10) {
        if (!this.hasOutputFormat || this.sampleReader.needsSpsPps()) {
            this.sps.appendToNalUnit(bArr, i9, i10);
            this.pps.appendToNalUnit(bArr, i9, i10);
        }
        this.sei.appendToNalUnit(bArr, i9, i10);
        this.sampleReader.appendToNalUnit(bArr, i9, i10);
    }

    private void startNalUnit(long j9, int i9, long j10) {
        if (!this.hasOutputFormat || this.sampleReader.needsSpsPps()) {
            this.sps.startNalUnit(i9);
            this.pps.startNalUnit(i9);
        }
        this.sei.startNalUnit(i9);
        this.sampleReader.startNalUnit(j9, i9, j10);
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition();
        int iLimit = parsableByteArray.limit();
        byte[] bArr = parsableByteArray.data;
        this.totalBytesWritten += parsableByteArray.bytesLeft();
        this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
        while (true) {
            int iFindNalUnit = NalUnitUtil.findNalUnit(bArr, position, iLimit, this.prefixFlags);
            if (iFindNalUnit == iLimit) {
                nalUnitData(bArr, position, iLimit);
                return;
            }
            int nalUnitType = NalUnitUtil.getNalUnitType(bArr, iFindNalUnit);
            int i9 = iFindNalUnit - position;
            if (i9 > 0) {
                nalUnitData(bArr, position, iFindNalUnit);
            }
            int i10 = iLimit - iFindNalUnit;
            long j9 = this.totalBytesWritten - i10;
            endNalUnit(j9, i10, i9 < 0 ? -i9 : 0, this.pesTimeUs);
            startNalUnit(j9, nalUnitType, this.pesTimeUs);
            position = iFindNalUnit + 3;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        TrackOutput trackOutputTrack = extractorOutput.track(trackIdGenerator.getTrackId(), 2);
        this.output = trackOutputTrack;
        this.sampleReader = new SampleReader(trackOutputTrack, this.allowNonIdrKeyframes, this.detectAccessUnits);
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
        this.sps.reset();
        this.pps.reset();
        this.sei.reset();
        this.sampleReader.reset();
        this.totalBytesWritten = 0L;
    }
}
