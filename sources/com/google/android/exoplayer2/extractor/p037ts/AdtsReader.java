package com.google.android.exoplayer2.extractor.p037ts;

import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.p037ts.TsPayloadReader;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.common.primitives.UnsignedBytes;
import java.util.Arrays;
import java.util.Collections;

/* loaded from: classes.dex */
public final class AdtsReader implements ElementaryStreamReader {
    private static final int CRC_SIZE = 2;
    private static final int HEADER_SIZE = 5;
    private static final int ID3_HEADER_SIZE = 10;
    private static final byte[] ID3_IDENTIFIER = {73, 68, 51};
    private static final int ID3_SIZE_OFFSET = 6;
    private static final int MATCH_STATE_FF = 512;
    private static final int MATCH_STATE_I = 768;
    private static final int MATCH_STATE_ID = 1024;
    private static final int MATCH_STATE_START = 256;
    private static final int MATCH_STATE_VALUE_SHIFT = 8;
    private static final int STATE_FINDING_SAMPLE = 0;
    private static final int STATE_READING_ADTS_HEADER = 2;
    private static final int STATE_READING_ID3_HEADER = 1;
    private static final int STATE_READING_SAMPLE = 3;
    private static final String TAG = "AdtsReader";
    private final ParsableBitArray adtsScratch;
    private int bytesRead;
    private TrackOutput currentOutput;
    private long currentSampleDuration;
    private final boolean exposeId3;
    private String formatId;
    private boolean hasCrc;
    private boolean hasOutputFormat;
    private final ParsableByteArray id3HeaderBuffer;
    private TrackOutput id3Output;
    private final String language;
    private int matchState;
    private TrackOutput output;
    private long sampleDurationUs;
    private int sampleSize;
    private int state;
    private long timeUs;

    public AdtsReader(boolean z8) {
        this(z8, null);
    }

    private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int i9) {
        int iMin = Math.min(parsableByteArray.bytesLeft(), i9 - this.bytesRead);
        parsableByteArray.readBytes(bArr, this.bytesRead, iMin);
        int i10 = this.bytesRead + iMin;
        this.bytesRead = i10;
        return i10 == i9;
    }

    private void findNextSample(ParsableByteArray parsableByteArray) {
        byte[] bArr = parsableByteArray.data;
        int position = parsableByteArray.getPosition();
        int iLimit = parsableByteArray.limit();
        while (position < iLimit) {
            int i9 = position + 1;
            int i10 = bArr[position] & UnsignedBytes.MAX_VALUE;
            int i11 = this.matchState;
            if (i11 == MATCH_STATE_FF && i10 >= 240 && i10 != 255) {
                this.hasCrc = (i10 & 1) == 0;
                setReadingAdtsHeaderState();
                parsableByteArray.setPosition(i9);
                return;
            }
            int i12 = i10 | i11;
            if (i12 == 329) {
                this.matchState = MATCH_STATE_I;
            } else if (i12 == 511) {
                this.matchState = MATCH_STATE_FF;
            } else if (i12 == 836) {
                this.matchState = 1024;
            } else if (i12 == 1075) {
                setReadingId3HeaderState();
                parsableByteArray.setPosition(i9);
                return;
            } else if (i11 != MATCH_STATE_START) {
                this.matchState = MATCH_STATE_START;
                i9--;
            }
            position = i9;
        }
        parsableByteArray.setPosition(position);
    }

    private void parseAdtsHeader() {
        this.adtsScratch.setPosition(0);
        if (this.hasOutputFormat) {
            this.adtsScratch.skipBits(10);
        } else {
            int bits = this.adtsScratch.readBits(2) + 1;
            if (bits != 2) {
                Log.w(TAG, "Detected audio object type: " + bits + ", but assuming AAC LC.");
                bits = 2;
            }
            int bits2 = this.adtsScratch.readBits(4);
            this.adtsScratch.skipBits(1);
            byte[] bArrBuildAacAudioSpecificConfig = CodecSpecificDataUtil.buildAacAudioSpecificConfig(bits, bits2, this.adtsScratch.readBits(3));
            Pair<Integer, Integer> aacAudioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(bArrBuildAacAudioSpecificConfig);
            Format formatCreateAudioSampleFormat = Format.createAudioSampleFormat(this.formatId, MimeTypes.AUDIO_AAC, null, -1, -1, ((Integer) aacAudioSpecificConfig.second).intValue(), ((Integer) aacAudioSpecificConfig.first).intValue(), Collections.singletonList(bArrBuildAacAudioSpecificConfig), null, 0, this.language);
            this.sampleDurationUs = 1024000000 / formatCreateAudioSampleFormat.sampleRate;
            this.output.format(formatCreateAudioSampleFormat);
            this.hasOutputFormat = true;
        }
        this.adtsScratch.skipBits(4);
        int bits3 = (this.adtsScratch.readBits(13) - 2) - 5;
        if (this.hasCrc) {
            bits3 -= 2;
        }
        setReadingSampleState(this.output, this.sampleDurationUs, 0, bits3);
    }

    private void parseId3Header() {
        this.id3Output.sampleData(this.id3HeaderBuffer, 10);
        this.id3HeaderBuffer.setPosition(6);
        setReadingSampleState(this.id3Output, 0L, 10, this.id3HeaderBuffer.readSynchSafeInt() + 10);
    }

    private void readSample(ParsableByteArray parsableByteArray) {
        int iMin = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
        this.currentOutput.sampleData(parsableByteArray, iMin);
        int i9 = this.bytesRead + iMin;
        this.bytesRead = i9;
        int i10 = this.sampleSize;
        if (i9 == i10) {
            this.currentOutput.sampleMetadata(this.timeUs, 1, i10, 0, null);
            this.timeUs += this.currentSampleDuration;
            setFindingSampleState();
        }
    }

    private void setFindingSampleState() {
        this.state = 0;
        this.bytesRead = 0;
        this.matchState = MATCH_STATE_START;
    }

    private void setReadingAdtsHeaderState() {
        this.state = 2;
        this.bytesRead = 0;
    }

    private void setReadingId3HeaderState() {
        this.state = 1;
        this.bytesRead = ID3_IDENTIFIER.length;
        this.sampleSize = 0;
        this.id3HeaderBuffer.setPosition(0);
    }

    private void setReadingSampleState(TrackOutput trackOutput, long j9, int i9, int i10) {
        this.state = 3;
        this.bytesRead = i9;
        this.currentOutput = trackOutput;
        this.currentSampleDuration = j9;
        this.sampleSize = i10;
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            int i9 = this.state;
            if (i9 == 0) {
                findNextSample(parsableByteArray);
            } else if (i9 != 1) {
                if (i9 == 2) {
                    if (continueRead(parsableByteArray, this.adtsScratch.data, this.hasCrc ? 7 : 5)) {
                        parseAdtsHeader();
                    }
                } else if (i9 == 3) {
                    readSample(parsableByteArray);
                }
            } else if (continueRead(parsableByteArray, this.id3HeaderBuffer.data, 10)) {
                parseId3Header();
            }
        }
    }

    @Override // com.google.android.exoplayer2.extractor.p037ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 1);
        if (!this.exposeId3) {
            this.id3Output = new DummyTrackOutput();
            return;
        }
        trackIdGenerator.generateNewId();
        TrackOutput trackOutputTrack = extractorOutput.track(trackIdGenerator.getTrackId(), 4);
        this.id3Output = trackOutputTrack;
        trackOutputTrack.format(Format.createSampleFormat(trackIdGenerator.getFormatId(), MimeTypes.APPLICATION_ID3, null, -1, null));
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
        setFindingSampleState();
    }

    public AdtsReader(boolean z8, String str) {
        this.adtsScratch = new ParsableBitArray(new byte[7]);
        this.id3HeaderBuffer = new ParsableByteArray(Arrays.copyOf(ID3_IDENTIFIER, 10));
        setFindingSampleState();
        this.exposeId3 = z8;
        this.language = str;
    }
}
