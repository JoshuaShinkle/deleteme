package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;

/* loaded from: classes.dex */
final class TrackFragment {
    public long atomPosition;
    public long auxiliaryDataPosition;
    public long dataPosition;
    public boolean definesEncryptionData;
    public DefaultSampleValues header;
    public long nextFragmentDecodeTime;
    public int[] sampleCompositionTimeOffsetTable;
    public int sampleCount;
    public long[] sampleDecodingTimeTable;
    public ParsableByteArray sampleEncryptionData;
    public int sampleEncryptionDataLength;
    public boolean sampleEncryptionDataNeedsFill;
    public boolean[] sampleHasSubsampleEncryptionTable;
    public boolean[] sampleIsSyncFrameTable;
    public int[] sampleSizeTable;
    public TrackEncryptionBox trackEncryptionBox;
    public int trunCount;
    public long[] trunDataPosition;
    public int[] trunLength;

    public void fillEncryptionData(ExtractorInput extractorInput) {
        extractorInput.readFully(this.sampleEncryptionData.data, 0, this.sampleEncryptionDataLength);
        this.sampleEncryptionData.setPosition(0);
        this.sampleEncryptionDataNeedsFill = false;
    }

    public long getSamplePresentationTime(int i9) {
        return this.sampleDecodingTimeTable[i9] + this.sampleCompositionTimeOffsetTable[i9];
    }

    public void initEncryptionData(int i9) {
        ParsableByteArray parsableByteArray = this.sampleEncryptionData;
        if (parsableByteArray == null || parsableByteArray.limit() < i9) {
            this.sampleEncryptionData = new ParsableByteArray(i9);
        }
        this.sampleEncryptionDataLength = i9;
        this.definesEncryptionData = true;
        this.sampleEncryptionDataNeedsFill = true;
    }

    public void initTables(int i9, int i10) {
        this.trunCount = i9;
        this.sampleCount = i10;
        int[] iArr = this.trunLength;
        if (iArr == null || iArr.length < i9) {
            this.trunDataPosition = new long[i9];
            this.trunLength = new int[i9];
        }
        int[] iArr2 = this.sampleSizeTable;
        if (iArr2 == null || iArr2.length < i10) {
            int i11 = (i10 * 125) / 100;
            this.sampleSizeTable = new int[i11];
            this.sampleCompositionTimeOffsetTable = new int[i11];
            this.sampleDecodingTimeTable = new long[i11];
            this.sampleIsSyncFrameTable = new boolean[i11];
            this.sampleHasSubsampleEncryptionTable = new boolean[i11];
        }
    }

    public void reset() {
        this.trunCount = 0;
        this.nextFragmentDecodeTime = 0L;
        this.definesEncryptionData = false;
        this.sampleEncryptionDataNeedsFill = false;
        this.trackEncryptionBox = null;
    }

    public void fillEncryptionData(ParsableByteArray parsableByteArray) {
        parsableByteArray.readBytes(this.sampleEncryptionData.data, 0, this.sampleEncryptionDataLength);
        this.sampleEncryptionData.setPosition(0);
        this.sampleEncryptionDataNeedsFill = false;
    }
}
