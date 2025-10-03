package com.google.android.exoplayer2.extractor.flv;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;

/* loaded from: classes.dex */
abstract class TagPayloadReader {
    protected final TrackOutput output;

    public static final class UnsupportedFormatException extends ParserException {
        public UnsupportedFormatException(String str) {
            super(str);
        }
    }

    public TagPayloadReader(TrackOutput trackOutput) {
        this.output = trackOutput;
    }

    public final void consume(ParsableByteArray parsableByteArray, long j9) {
        if (parseHeader(parsableByteArray)) {
            parsePayload(parsableByteArray, j9);
        }
    }

    public abstract boolean parseHeader(ParsableByteArray parsableByteArray);

    public abstract void parsePayload(ParsableByteArray parsableByteArray, long j9);

    public abstract void seek();
}
