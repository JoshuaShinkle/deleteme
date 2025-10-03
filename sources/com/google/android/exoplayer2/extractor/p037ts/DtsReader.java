package com.google.android.exoplayer2.extractor.p037ts;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.DtsUtil;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.p037ts.TsPayloadReader;
import com.google.android.exoplayer2.util.ParsableByteArray;

/* loaded from: classes.dex */
public final class DtsReader implements ElementaryStreamReader {
    private static final int HEADER_SIZE = 18;
    private static final int STATE_FINDING_SYNC = 0;
    private static final int STATE_READING_HEADER = 1;
    private static final int STATE_READING_SAMPLE = 2;
    private int bytesRead;
    private Format format;
    private String formatId;
    private final String language;
    private TrackOutput output;
    private long sampleDurationUs;
    private int sampleSize;
    private int syncBytes;
    private long timeUs;
    private final ParsableByteArray headerScratchBytes = new ParsableByteArray(new byte[18]);
    private int state = 0;

    public DtsReader(String str) {
        this.language = str;
    }

    private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int i9) {
        int iMin = Math.min(parsableByteArray.bytesLeft(), i9 - this.bytesRead);
        parsableByteArray.readBytes(bArr, this.bytesRead, iMin);
        int i10 = this.bytesRead + iMin;
        this.bytesRead = i10;
        return i10 == i9;
    }

    private void parseHeader() {
        byte[] bArr = this.headerScratchBytes.data;
        if (this.format == null) {
            Format dtsFormat = DtsUtil.parseDtsFormat(bArr, this.formatId, this.language, null);
            this.format = dtsFormat;
            this.output.format(dtsFormat);
        }
        this.sampleSize = DtsUtil.getDtsFrameSize(bArr);
        this.sampleDurationUs = (int) ((DtsUtil.parseDtsAudioSampleCount(bArr) * C3322C.MICROS_PER_SECOND) / this.format.sampleRate);
    }

    private boolean skipToNextSync(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            int i9 = this.syncBytes << 8;
            this.syncBytes = i9;
            int unsignedByte = i9 | parsableByteArray.readUnsignedByte();
            this.syncBytes = unsignedByte;
            if (DtsUtil.isSyncWord(unsignedByte)) {
                byte[] bArr = this.headerScratchBytes.data;
                int i10 = this.syncBytes;
                bArr[0] = (byte) ((i10 >> 24) & 255);
                bArr[1] = (byte) ((i10 >> 16) & 255);
                bArr[2] = (byte) ((i10 >> 8) & 255);
                bArr[3] = (byte) (i10 & 255);
                this.bytesRead = 4;
                this.syncBytes = 0;
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            int i9 = this.state;
            if (i9 != 0) {
                if (i9 != 1) {
                    if (i9 == 2) {
                        int iMin = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
                        this.output.sampleData(parsableByteArray, iMin);
                        int i10 = this.bytesRead + iMin;
                        this.bytesRead = i10;
                        int i11 = this.sampleSize;
                        if (i10 == i11) {
                            this.output.sampleMetadata(this.timeUs, 1, i11, 0, null);
                            this.timeUs += this.sampleDurationUs;
                            this.state = 0;
                        }
                    }
                } else if (continueRead(parsableByteArray, this.headerScratchBytes.data, 18)) {
                    parseHeader();
                    this.headerScratchBytes.setPosition(0);
                    this.output.sampleData(this.headerScratchBytes, 18);
                    this.state = 2;
                }
            } else if (skipToNextSync(parsableByteArray)) {
                this.state = 1;
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
        this.bytesRead = 0;
        this.syncBytes = 0;
    }
}
