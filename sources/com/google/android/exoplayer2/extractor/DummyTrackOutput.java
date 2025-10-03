package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.EOFException;

/* loaded from: classes.dex */
public final class DummyTrackOutput implements TrackOutput {
    @Override // com.google.android.exoplayer2.extractor.TrackOutput
    public void format(Format format) {
    }

    @Override // com.google.android.exoplayer2.extractor.TrackOutput
    public int sampleData(ExtractorInput extractorInput, int i9, boolean z8) throws EOFException {
        int iSkip = extractorInput.skip(i9);
        if (iSkip != -1) {
            return iSkip;
        }
        if (z8) {
            return -1;
        }
        throw new EOFException();
    }

    @Override // com.google.android.exoplayer2.extractor.TrackOutput
    public void sampleMetadata(long j9, int i9, int i10, int i11, TrackOutput.CryptoData cryptoData) {
    }

    @Override // com.google.android.exoplayer2.extractor.TrackOutput
    public void sampleData(ParsableByteArray parsableByteArray, int i9) {
        parsableByteArray.skipBytes(i9);
    }
}
