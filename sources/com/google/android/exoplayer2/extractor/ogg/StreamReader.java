package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;

/* loaded from: classes.dex */
abstract class StreamReader {
    private static final int STATE_END_OF_INPUT = 3;
    private static final int STATE_READ_HEADERS = 0;
    private static final int STATE_READ_PAYLOAD = 2;
    private static final int STATE_SKIP_HEADERS = 1;
    private long currentGranule;
    private ExtractorOutput extractorOutput;
    private boolean formatSet;
    private long lengthOfReadPacket;
    private final OggPacket oggPacket = new OggPacket();
    private OggSeeker oggSeeker;
    private long payloadStartPosition;
    private int sampleRate;
    private boolean seekMapSet;
    private SetupData setupData;
    private int state;
    private long targetGranule;
    private TrackOutput trackOutput;

    public static class SetupData {
        Format format;
        OggSeeker oggSeeker;
    }

    public static final class UnseekableOggSeeker implements OggSeeker {
        private UnseekableOggSeeker() {
        }

        @Override // com.google.android.exoplayer2.extractor.ogg.OggSeeker
        public SeekMap createSeekMap() {
            return new SeekMap.Unseekable(C3322C.TIME_UNSET);
        }

        @Override // com.google.android.exoplayer2.extractor.ogg.OggSeeker
        public long read(ExtractorInput extractorInput) {
            return -1L;
        }

        @Override // com.google.android.exoplayer2.extractor.ogg.OggSeeker
        public long startSeek(long j9) {
            return 0L;
        }
    }

    private int readHeaders(ExtractorInput extractorInput) {
        boolean headers = true;
        while (headers) {
            if (!this.oggPacket.populate(extractorInput)) {
                this.state = 3;
                return -1;
            }
            this.lengthOfReadPacket = extractorInput.getPosition() - this.payloadStartPosition;
            headers = readHeaders(this.oggPacket.getPayload(), this.payloadStartPosition, this.setupData);
            if (headers) {
                this.payloadStartPosition = extractorInput.getPosition();
            }
        }
        Format format = this.setupData.format;
        this.sampleRate = format.sampleRate;
        if (!this.formatSet) {
            this.trackOutput.format(format);
            this.formatSet = true;
        }
        OggSeeker oggSeeker = this.setupData.oggSeeker;
        if (oggSeeker != null) {
            this.oggSeeker = oggSeeker;
        } else if (extractorInput.getLength() == -1) {
            this.oggSeeker = new UnseekableOggSeeker();
        } else {
            OggPageHeader pageHeader = this.oggPacket.getPageHeader();
            this.oggSeeker = new DefaultOggSeeker(this.payloadStartPosition, extractorInput.getLength(), this, pageHeader.headerSize + pageHeader.bodySize, pageHeader.granulePosition);
        }
        this.setupData = null;
        this.state = 2;
        this.oggPacket.trimPayload();
        return 0;
    }

    private int readPayload(ExtractorInput extractorInput, PositionHolder positionHolder) {
        long j9 = this.oggSeeker.read(extractorInput);
        if (j9 >= 0) {
            positionHolder.position = j9;
            return 1;
        }
        if (j9 < -1) {
            onSeekEnd(-(j9 + 2));
        }
        if (!this.seekMapSet) {
            this.extractorOutput.seekMap(this.oggSeeker.createSeekMap());
            this.seekMapSet = true;
        }
        if (this.lengthOfReadPacket <= 0 && !this.oggPacket.populate(extractorInput)) {
            this.state = 3;
            return -1;
        }
        this.lengthOfReadPacket = 0L;
        ParsableByteArray payload = this.oggPacket.getPayload();
        long jPreparePayload = preparePayload(payload);
        if (jPreparePayload >= 0) {
            long j10 = this.currentGranule;
            if (j10 + jPreparePayload >= this.targetGranule) {
                long jConvertGranuleToTime = convertGranuleToTime(j10);
                this.trackOutput.sampleData(payload, payload.limit());
                this.trackOutput.sampleMetadata(jConvertGranuleToTime, 1, payload.limit(), 0, null);
                this.targetGranule = -1L;
            }
        }
        this.currentGranule += jPreparePayload;
        return 0;
    }

    public long convertGranuleToTime(long j9) {
        return (j9 * C3322C.MICROS_PER_SECOND) / this.sampleRate;
    }

    public long convertTimeToGranule(long j9) {
        return (this.sampleRate * j9) / C3322C.MICROS_PER_SECOND;
    }

    public void init(ExtractorOutput extractorOutput, TrackOutput trackOutput) {
        this.extractorOutput = extractorOutput;
        this.trackOutput = trackOutput;
        reset(true);
    }

    public void onSeekEnd(long j9) {
        this.currentGranule = j9;
    }

    public abstract long preparePayload(ParsableByteArray parsableByteArray);

    public final int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
        int i9 = this.state;
        if (i9 == 0) {
            return readHeaders(extractorInput);
        }
        if (i9 != 1) {
            if (i9 == 2) {
                return readPayload(extractorInput, positionHolder);
            }
            throw new IllegalStateException();
        }
        extractorInput.skipFully((int) this.payloadStartPosition);
        this.state = 2;
        return 0;
    }

    public abstract boolean readHeaders(ParsableByteArray parsableByteArray, long j9, SetupData setupData);

    public void reset(boolean z8) {
        if (z8) {
            this.setupData = new SetupData();
            this.payloadStartPosition = 0L;
            this.state = 0;
        } else {
            this.state = 1;
        }
        this.targetGranule = -1L;
        this.currentGranule = 0L;
    }

    public final void seek(long j9, long j10) {
        this.oggPacket.reset();
        if (j9 == 0) {
            reset(!this.seekMapSet);
        } else if (this.state != 0) {
            this.targetGranule = this.oggSeeker.startSeek(j10);
            this.state = 2;
        }
    }
}
