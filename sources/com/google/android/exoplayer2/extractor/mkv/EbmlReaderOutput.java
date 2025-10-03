package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.extractor.ExtractorInput;

/* loaded from: classes.dex */
interface EbmlReaderOutput {
    void binaryElement(int i9, int i10, ExtractorInput extractorInput);

    void endMasterElement(int i9);

    void floatElement(int i9, double d9);

    int getElementType(int i9);

    void integerElement(int i9, long j9);

    boolean isLevel1Element(int i9);

    void startMasterElement(int i9, long j9, long j10);

    void stringElement(int i9, String str);
}
