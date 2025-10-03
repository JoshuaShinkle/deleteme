package com.google.android.exoplayer2.extractor.p037ts;

import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.p037ts.TsPayloadReader;
import com.google.android.exoplayer2.util.ParsableByteArray;

/* loaded from: classes.dex */
public interface ElementaryStreamReader {
    void consume(ParsableByteArray parsableByteArray);

    void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator);

    void packetFinished();

    void packetStarted(long j9, boolean z8);

    void seek();
}
