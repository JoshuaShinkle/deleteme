package com.google.android.exoplayer2.extractor.p037ts;

import android.util.Log;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.p037ts.TsPayloadReader;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;

/* loaded from: classes.dex */
public final class Id3Reader implements ElementaryStreamReader {
    private static final int ID3_HEADER_SIZE = 10;
    private static final String TAG = "Id3Reader";
    private final ParsableByteArray id3Header = new ParsableByteArray(10);
    private TrackOutput output;
    private int sampleBytesRead;
    private int sampleSize;
    private long sampleTimeUs;
    private boolean writingSample;

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        if (this.writingSample) {
            int iBytesLeft = parsableByteArray.bytesLeft();
            int i9 = this.sampleBytesRead;
            if (i9 < 10) {
                int iMin = Math.min(iBytesLeft, 10 - i9);
                System.arraycopy(parsableByteArray.data, parsableByteArray.getPosition(), this.id3Header.data, this.sampleBytesRead, iMin);
                if (this.sampleBytesRead + iMin == 10) {
                    this.id3Header.setPosition(0);
                    if (73 != this.id3Header.readUnsignedByte() || 68 != this.id3Header.readUnsignedByte() || 51 != this.id3Header.readUnsignedByte()) {
                        Log.w(TAG, "Discarding invalid ID3 tag");
                        this.writingSample = false;
                        return;
                    } else {
                        this.id3Header.skipBytes(3);
                        this.sampleSize = this.id3Header.readSynchSafeInt() + 10;
                    }
                }
            }
            int iMin2 = Math.min(iBytesLeft, this.sampleSize - this.sampleBytesRead);
            this.output.sampleData(parsableByteArray, iMin2);
            this.sampleBytesRead += iMin2;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        TrackOutput trackOutputTrack = extractorOutput.track(trackIdGenerator.getTrackId(), 4);
        this.output = trackOutputTrack;
        trackOutputTrack.format(Format.createSampleFormat(trackIdGenerator.getFormatId(), MimeTypes.APPLICATION_ID3, null, -1, null));
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void packetFinished() {
        int i9;
        if (this.writingSample && (i9 = this.sampleSize) != 0 && this.sampleBytesRead == i9) {
            this.output.sampleMetadata(this.sampleTimeUs, 1, i9, 0, null);
            this.writingSample = false;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void packetStarted(long j9, boolean z8) {
        if (z8) {
            this.writingSample = true;
            this.sampleTimeUs = j9;
            this.sampleSize = 0;
            this.sampleBytesRead = 0;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void seek() {
        this.writingSample = false;
    }
}
