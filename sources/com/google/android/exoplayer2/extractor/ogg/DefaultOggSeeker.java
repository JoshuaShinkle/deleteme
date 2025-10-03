package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.util.Assertions;
import java.io.EOFException;
import java.io.IOException;

/* loaded from: classes.dex */
final class DefaultOggSeeker implements OggSeeker {
    private static final int DEFAULT_OFFSET = 30000;
    public static final int MATCH_BYTE_RANGE = 100000;
    public static final int MATCH_RANGE = 72000;
    private static final int STATE_IDLE = 3;
    private static final int STATE_READ_LAST_PAGE = 1;
    private static final int STATE_SEEK = 2;
    private static final int STATE_SEEK_TO_END = 0;
    private long end;
    private long endGranule;
    private final long endPosition;
    private final OggPageHeader pageHeader = new OggPageHeader();
    private long positionBeforeSeekToEnd;
    private long start;
    private long startGranule;
    private final long startPosition;
    private int state;
    private final StreamReader streamReader;
    private long targetGranule;
    private long totalGranules;

    public class OggSeekMap implements SeekMap {
        private OggSeekMap() {
        }

        @Override // com.google.android.exoplayer2.extractor.SeekMap
        public long getDurationUs() {
            return DefaultOggSeeker.this.streamReader.convertGranuleToTime(DefaultOggSeeker.this.totalGranules);
        }

        @Override // com.google.android.exoplayer2.extractor.SeekMap
        public SeekMap.SeekPoints getSeekPoints(long j9) {
            if (j9 == 0) {
                return new SeekMap.SeekPoints(new SeekPoint(0L, DefaultOggSeeker.this.startPosition));
            }
            long jConvertTimeToGranule = DefaultOggSeeker.this.streamReader.convertTimeToGranule(j9);
            DefaultOggSeeker defaultOggSeeker = DefaultOggSeeker.this;
            return new SeekMap.SeekPoints(new SeekPoint(j9, defaultOggSeeker.getEstimatedPosition(defaultOggSeeker.startPosition, jConvertTimeToGranule, SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS)));
        }

        @Override // com.google.android.exoplayer2.extractor.SeekMap
        public boolean isSeekable() {
            return true;
        }
    }

    public DefaultOggSeeker(long j9, long j10, StreamReader streamReader, int i9, long j11) {
        Assertions.checkArgument(j9 >= 0 && j10 > j9);
        this.streamReader = streamReader;
        this.startPosition = j9;
        this.endPosition = j10;
        if (i9 != j10 - j9) {
            this.state = 0;
        } else {
            this.totalGranules = j11;
            this.state = 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getEstimatedPosition(long j9, long j10, long j11) {
        long j12 = this.endPosition;
        long j13 = this.startPosition;
        long j14 = j9 + (((j10 * (j12 - j13)) / this.totalGranules) - j11);
        if (j14 >= j13) {
            j13 = j14;
        }
        return j13 >= j12 ? j12 - 1 : j13;
    }

    public long getNextSeekPosition(long j9, ExtractorInput extractorInput) throws IOException {
        if (this.start == this.end) {
            return -(this.startGranule + 2);
        }
        long position = extractorInput.getPosition();
        if (!skipToNextPage(extractorInput, this.end)) {
            long j10 = this.start;
            if (j10 != position) {
                return j10;
            }
            throw new IOException("No ogg page can be found.");
        }
        this.pageHeader.populate(extractorInput, false);
        extractorInput.resetPeekPosition();
        OggPageHeader oggPageHeader = this.pageHeader;
        long j11 = oggPageHeader.granulePosition;
        long j12 = j9 - j11;
        int i9 = oggPageHeader.headerSize + oggPageHeader.bodySize;
        if (j12 >= 0 && j12 <= 72000) {
            extractorInput.skipFully(i9);
            return -(this.pageHeader.granulePosition + 2);
        }
        if (j12 < 0) {
            this.end = position;
            this.endGranule = j11;
        } else {
            long j13 = i9;
            long position2 = extractorInput.getPosition() + j13;
            this.start = position2;
            this.startGranule = this.pageHeader.granulePosition;
            if ((this.end - position2) + j13 < 100000) {
                extractorInput.skipFully(i9);
                return -(this.startGranule + 2);
            }
        }
        long j14 = this.end;
        long j15 = this.start;
        if (j14 - j15 < 100000) {
            this.end = j15;
            return j15;
        }
        long j16 = i9;
        long j17 = j12 > 0 ? 1L : 2L;
        long position3 = extractorInput.getPosition();
        long j18 = this.end;
        long j19 = this.start;
        return Math.min(Math.max((position3 - (j16 * j17)) + ((j12 * (j18 - j19)) / (this.endGranule - this.startGranule)), j19), this.end - 1);
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.OggSeeker
    public long read(ExtractorInput extractorInput) throws IOException {
        int i9 = this.state;
        if (i9 == 0) {
            long position = extractorInput.getPosition();
            this.positionBeforeSeekToEnd = position;
            this.state = 1;
            long j9 = this.endPosition - 65307;
            if (j9 > position) {
                return j9;
            }
        } else if (i9 != 1) {
            if (i9 != 2) {
                if (i9 == 3) {
                    return -1L;
                }
                throw new IllegalStateException();
            }
            long j10 = this.targetGranule;
            long jSkipToPageOfGranule = 0;
            if (j10 != 0) {
                long nextSeekPosition = getNextSeekPosition(j10, extractorInput);
                if (nextSeekPosition >= 0) {
                    return nextSeekPosition;
                }
                jSkipToPageOfGranule = skipToPageOfGranule(extractorInput, this.targetGranule, -(nextSeekPosition + 2));
            }
            this.state = 3;
            return -(jSkipToPageOfGranule + 2);
        }
        this.totalGranules = readGranuleOfLastPage(extractorInput);
        this.state = 3;
        return this.positionBeforeSeekToEnd;
    }

    public long readGranuleOfLastPage(ExtractorInput extractorInput) throws ParserException, EOFException {
        skipToNextPage(extractorInput);
        this.pageHeader.reset();
        while ((this.pageHeader.type & 4) != 4 && extractorInput.getPosition() < this.endPosition) {
            this.pageHeader.populate(extractorInput, false);
            OggPageHeader oggPageHeader = this.pageHeader;
            extractorInput.skipFully(oggPageHeader.headerSize + oggPageHeader.bodySize);
        }
        return this.pageHeader.granulePosition;
    }

    public void resetSeeking() {
        this.start = this.startPosition;
        this.end = this.endPosition;
        this.startGranule = 0L;
        this.endGranule = this.totalGranules;
    }

    public void skipToNextPage(ExtractorInput extractorInput) throws EOFException {
        if (!skipToNextPage(extractorInput, this.endPosition)) {
            throw new EOFException();
        }
    }

    public long skipToPageOfGranule(ExtractorInput extractorInput, long j9, long j10) throws ParserException, EOFException {
        this.pageHeader.populate(extractorInput, false);
        while (true) {
            OggPageHeader oggPageHeader = this.pageHeader;
            if (oggPageHeader.granulePosition >= j9) {
                extractorInput.resetPeekPosition();
                return j10;
            }
            extractorInput.skipFully(oggPageHeader.headerSize + oggPageHeader.bodySize);
            OggPageHeader oggPageHeader2 = this.pageHeader;
            long j11 = oggPageHeader2.granulePosition;
            oggPageHeader2.populate(extractorInput, false);
            j10 = j11;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.OggSeeker
    public long startSeek(long j9) {
        int i9 = this.state;
        Assertions.checkArgument(i9 == 3 || i9 == 2);
        this.targetGranule = j9 != 0 ? this.streamReader.convertTimeToGranule(j9) : 0L;
        this.state = 2;
        resetSeeking();
        return this.targetGranule;
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.OggSeeker
    public OggSeekMap createSeekMap() {
        if (this.totalGranules != 0) {
            return new OggSeekMap();
        }
        return null;
    }

    public boolean skipToNextPage(ExtractorInput extractorInput, long j9) {
        int i9;
        long jMin = Math.min(j9 + 3, this.endPosition);
        int position = 2048;
        byte[] bArr = new byte[2048];
        while (true) {
            int i10 = 0;
            if (extractorInput.getPosition() + position > jMin && (position = (int) (jMin - extractorInput.getPosition())) < 4) {
                return false;
            }
            extractorInput.peekFully(bArr, 0, position, false);
            while (true) {
                i9 = position - 3;
                if (i10 < i9) {
                    if (bArr[i10] == 79 && bArr[i10 + 1] == 103 && bArr[i10 + 2] == 103 && bArr[i10 + 3] == 83) {
                        extractorInput.skipFully(i10);
                        return true;
                    }
                    i10++;
                }
            }
            extractorInput.skipFully(i9);
        }
    }
}
