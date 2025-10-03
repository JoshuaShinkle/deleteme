package com.google.android.exoplayer2.extractor.wav;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.MimeTypes;

/* loaded from: classes.dex */
public final class WavExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() { // from class: com.google.android.exoplayer2.extractor.wav.WavExtractor.1
        @Override // com.google.android.exoplayer2.extractor.ExtractorsFactory
        public Extractor[] createExtractors() {
            return new Extractor[]{new WavExtractor()};
        }
    };
    private static final int MAX_INPUT_SIZE = 32768;
    private int bytesPerFrame;
    private ExtractorOutput extractorOutput;
    private int pendingBytes;
    private TrackOutput trackOutput;
    private WavHeader wavHeader;

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        this.trackOutput = extractorOutput.track(0, 1);
        this.wavHeader = null;
        extractorOutput.endTracks();
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws ParserException {
        if (this.wavHeader == null) {
            WavHeader wavHeaderPeek = WavHeaderReader.peek(extractorInput);
            this.wavHeader = wavHeaderPeek;
            if (wavHeaderPeek == null) {
                throw new ParserException("Unsupported or unrecognized wav header.");
            }
            this.trackOutput.format(Format.createAudioSampleFormat(null, MimeTypes.AUDIO_RAW, null, wavHeaderPeek.getBitrate(), MAX_INPUT_SIZE, this.wavHeader.getNumChannels(), this.wavHeader.getSampleRateHz(), this.wavHeader.getEncoding(), null, null, 0, null));
            this.bytesPerFrame = this.wavHeader.getBytesPerFrame();
        }
        if (!this.wavHeader.hasDataBounds()) {
            WavHeaderReader.skipToData(extractorInput, this.wavHeader);
            this.extractorOutput.seekMap(this.wavHeader);
        }
        int iSampleData = this.trackOutput.sampleData(extractorInput, MAX_INPUT_SIZE - this.pendingBytes, true);
        if (iSampleData != -1) {
            this.pendingBytes += iSampleData;
        }
        int i9 = this.pendingBytes / this.bytesPerFrame;
        if (i9 > 0) {
            long timeUs = this.wavHeader.getTimeUs(extractorInput.getPosition() - this.pendingBytes);
            int i10 = i9 * this.bytesPerFrame;
            int i11 = this.pendingBytes - i10;
            this.pendingBytes = i11;
            this.trackOutput.sampleMetadata(timeUs, 1, i10, i11, null);
        }
        return iSampleData == -1 ? -1 : 0;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void release() {
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void seek(long j9, long j10) {
        this.pendingBytes = 0;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) {
        return WavHeaderReader.peek(extractorInput) != null;
    }
}
