package com.google.android.exoplayer2.extractor.p037ts;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.MpegAudioHeader;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.p037ts.TsPayloadReader;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.common.primitives.UnsignedBytes;

/* loaded from: classes.dex */
public final class MpegAudioReader implements ElementaryStreamReader {
    private static final int HEADER_SIZE = 4;
    private static final int STATE_FINDING_HEADER = 0;
    private static final int STATE_READING_FRAME = 2;
    private static final int STATE_READING_HEADER = 1;
    private String formatId;
    private int frameBytesRead;
    private long frameDurationUs;
    private int frameSize;
    private boolean hasOutputFormat;
    private final MpegAudioHeader header;
    private final ParsableByteArray headerScratch;
    private final String language;
    private boolean lastByteWasFF;
    private TrackOutput output;
    private int state;
    private long timeUs;

    public MpegAudioReader() {
        this(null);
    }

    private void findHeader(ParsableByteArray parsableByteArray) {
        byte[] bArr = parsableByteArray.data;
        int iLimit = parsableByteArray.limit();
        for (int position = parsableByteArray.getPosition(); position < iLimit; position++) {
            byte b9 = bArr[position];
            boolean z8 = (b9 & UnsignedBytes.MAX_VALUE) == 255;
            boolean z9 = this.lastByteWasFF && (b9 & 224) == 224;
            this.lastByteWasFF = z8;
            if (z9) {
                parsableByteArray.setPosition(position + 1);
                this.lastByteWasFF = false;
                this.headerScratch.data[1] = bArr[position];
                this.frameBytesRead = 2;
                this.state = 1;
                return;
            }
        }
        parsableByteArray.setPosition(iLimit);
    }

    private void readFrameRemainder(ParsableByteArray parsableByteArray) {
        int iMin = Math.min(parsableByteArray.bytesLeft(), this.frameSize - this.frameBytesRead);
        this.output.sampleData(parsableByteArray, iMin);
        int i9 = this.frameBytesRead + iMin;
        this.frameBytesRead = i9;
        int i10 = this.frameSize;
        if (i9 < i10) {
            return;
        }
        this.output.sampleMetadata(this.timeUs, 1, i10, 0, null);
        this.timeUs += this.frameDurationUs;
        this.frameBytesRead = 0;
        this.state = 0;
    }

    private void readHeaderRemainder(ParsableByteArray parsableByteArray) {
        int iMin = Math.min(parsableByteArray.bytesLeft(), 4 - this.frameBytesRead);
        parsableByteArray.readBytes(this.headerScratch.data, this.frameBytesRead, iMin);
        int i9 = this.frameBytesRead + iMin;
        this.frameBytesRead = i9;
        if (i9 < 4) {
            return;
        }
        this.headerScratch.setPosition(0);
        if (!MpegAudioHeader.populateHeader(this.headerScratch.readInt(), this.header)) {
            this.frameBytesRead = 0;
            this.state = 1;
            return;
        }
        MpegAudioHeader mpegAudioHeader = this.header;
        this.frameSize = mpegAudioHeader.frameSize;
        if (!this.hasOutputFormat) {
            long j9 = mpegAudioHeader.samplesPerFrame * C3322C.MICROS_PER_SECOND;
            int i10 = mpegAudioHeader.sampleRate;
            this.frameDurationUs = j9 / i10;
            this.output.format(Format.createAudioSampleFormat(this.formatId, mpegAudioHeader.mimeType, null, -1, 4096, mpegAudioHeader.channels, i10, null, null, 0, this.language));
            this.hasOutputFormat = true;
        }
        this.headerScratch.setPosition(0);
        this.output.sampleData(this.headerScratch, 4);
        this.state = 2;
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            int i9 = this.state;
            if (i9 == 0) {
                findHeader(parsableByteArray);
            } else if (i9 == 1) {
                readHeaderRemainder(parsableByteArray);
            } else if (i9 == 2) {
                readFrameRemainder(parsableByteArray);
            }
        }
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 1);
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void packetFinished() {
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void packetStarted(long j9, boolean z8) {
        this.timeUs = j9;
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void seek() {
        this.state = 0;
        this.frameBytesRead = 0;
        this.lastByteWasFF = false;
    }

    public MpegAudioReader(String str) {
        this.state = 0;
        ParsableByteArray parsableByteArray = new ParsableByteArray(4);
        this.headerScratch = parsableByteArray;
        parsableByteArray.data[0] = -1;
        this.header = new MpegAudioHeader();
        this.language = str;
    }
}
