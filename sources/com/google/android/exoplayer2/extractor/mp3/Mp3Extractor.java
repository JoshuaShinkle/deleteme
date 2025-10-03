package com.google.android.exoplayer2.extractor.mp3;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.extractor.MpegAudioHeader;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class Mp3Extractor implements Extractor {
    public static final int FLAG_DISABLE_ID3_METADATA = 2;
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING = 1;
    private static final int MAX_SNIFF_BYTES = 16384;
    private static final int MAX_SYNC_BYTES = 131072;
    private static final int MPEG_AUDIO_HEADER_MASK = -128000;
    private static final int SCRATCH_LENGTH = 10;
    private static final int SEEK_HEADER_UNSET = 0;
    private long basisTimeUs;
    private ExtractorOutput extractorOutput;
    private final int flags;
    private final long forcedFirstSampleTimestampUs;
    private final GaplessInfoHolder gaplessInfoHolder;
    private Metadata metadata;
    private int sampleBytesRemaining;
    private long samplesRead;
    private final ParsableByteArray scratch;
    private Seeker seeker;
    private final MpegAudioHeader synchronizedHeader;
    private int synchronizedHeaderData;
    private TrackOutput trackOutput;
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() { // from class: com.google.android.exoplayer2.extractor.mp3.Mp3Extractor.1
        @Override // com.google.android.exoplayer2.extractor.ExtractorsFactory
        public Extractor[] createExtractors() {
            return new Extractor[]{new Mp3Extractor()};
        }
    };
    private static final int SEEK_HEADER_XING = Util.getIntegerCodeForString("Xing");
    private static final int SEEK_HEADER_INFO = Util.getIntegerCodeForString("Info");
    private static final int SEEK_HEADER_VBRI = Util.getIntegerCodeForString("VBRI");

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public interface Seeker extends SeekMap {
        long getTimeUs(long j9);
    }

    public Mp3Extractor() {
        this(0);
    }

    private Seeker getConstantBitrateSeeker(ExtractorInput extractorInput) {
        extractorInput.peekFully(this.scratch.data, 0, 4);
        this.scratch.setPosition(0);
        MpegAudioHeader.populateHeader(this.scratch.readInt(), this.synchronizedHeader);
        return new ConstantBitrateSeeker(extractorInput.getLength(), extractorInput.getPosition(), this.synchronizedHeader);
    }

    private static int getSeekFrameHeader(ParsableByteArray parsableByteArray, int i9) {
        if (parsableByteArray.limit() >= i9 + 4) {
            parsableByteArray.setPosition(i9);
            int i10 = parsableByteArray.readInt();
            if (i10 == SEEK_HEADER_XING || i10 == SEEK_HEADER_INFO) {
                return i10;
            }
        }
        if (parsableByteArray.limit() < 40) {
            return 0;
        }
        parsableByteArray.setPosition(36);
        int i11 = parsableByteArray.readInt();
        int i12 = SEEK_HEADER_VBRI;
        if (i11 == i12) {
            return i12;
        }
        return 0;
    }

    private static boolean headersMatch(int i9, long j9) {
        return ((long) (i9 & MPEG_AUDIO_HEADER_MASK)) == (j9 & (-128000));
    }

    private Seeker maybeReadSeekFrame(ExtractorInput extractorInput) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(this.synchronizedHeader.frameSize);
        extractorInput.peekFully(parsableByteArray.data, 0, this.synchronizedHeader.frameSize);
        MpegAudioHeader mpegAudioHeader = this.synchronizedHeader;
        int i9 = 21;
        if ((mpegAudioHeader.version & 1) != 0) {
            if (mpegAudioHeader.channels != 1) {
                i9 = 36;
            }
        } else if (mpegAudioHeader.channels == 1) {
            i9 = 13;
        }
        int i10 = i9;
        int seekFrameHeader = getSeekFrameHeader(parsableByteArray, i10);
        if (seekFrameHeader != SEEK_HEADER_XING && seekFrameHeader != SEEK_HEADER_INFO) {
            if (seekFrameHeader != SEEK_HEADER_VBRI) {
                extractorInput.resetPeekPosition();
                return null;
            }
            VbriSeeker vbriSeekerCreate = VbriSeeker.create(extractorInput.getLength(), extractorInput.getPosition(), this.synchronizedHeader, parsableByteArray);
            extractorInput.skipFully(this.synchronizedHeader.frameSize);
            return vbriSeekerCreate;
        }
        XingSeeker xingSeekerCreate = XingSeeker.create(extractorInput.getLength(), extractorInput.getPosition(), this.synchronizedHeader, parsableByteArray);
        if (xingSeekerCreate != null && !this.gaplessInfoHolder.hasGaplessInfo()) {
            extractorInput.resetPeekPosition();
            extractorInput.advancePeekPosition(i10 + 141);
            extractorInput.peekFully(this.scratch.data, 0, 3);
            this.scratch.setPosition(0);
            this.gaplessInfoHolder.setFromXingHeaderValue(this.scratch.readUnsignedInt24());
        }
        extractorInput.skipFully(this.synchronizedHeader.frameSize);
        return (xingSeekerCreate == null || xingSeekerCreate.isSeekable() || seekFrameHeader != SEEK_HEADER_INFO) ? xingSeekerCreate : getConstantBitrateSeeker(extractorInput);
    }

    private void peekId3Data(ExtractorInput extractorInput) {
        int i9 = 0;
        while (true) {
            extractorInput.peekFully(this.scratch.data, 0, 10);
            this.scratch.setPosition(0);
            if (this.scratch.readUnsignedInt24() != Id3Decoder.ID3_TAG) {
                extractorInput.resetPeekPosition();
                extractorInput.advancePeekPosition(i9);
                return;
            }
            this.scratch.skipBytes(3);
            int synchSafeInt = this.scratch.readSynchSafeInt();
            int i10 = synchSafeInt + 10;
            if (this.metadata == null) {
                byte[] bArr = new byte[i10];
                System.arraycopy(this.scratch.data, 0, bArr, 0, 10);
                extractorInput.peekFully(bArr, 10, synchSafeInt);
                Metadata metadataDecode = new Id3Decoder((this.flags & 2) != 0 ? GaplessInfoHolder.GAPLESS_INFO_ID3_FRAME_PREDICATE : null).decode(bArr, i10);
                this.metadata = metadataDecode;
                if (metadataDecode != null) {
                    this.gaplessInfoHolder.setFromMetadata(metadataDecode);
                }
            } else {
                extractorInput.advancePeekPosition(synchSafeInt);
            }
            i9 += i10;
        }
    }

    private int readSample(ExtractorInput extractorInput) {
        if (this.sampleBytesRemaining == 0) {
            extractorInput.resetPeekPosition();
            if (!extractorInput.peekFully(this.scratch.data, 0, 4, true)) {
                return -1;
            }
            this.scratch.setPosition(0);
            int i9 = this.scratch.readInt();
            if (!headersMatch(i9, this.synchronizedHeaderData) || MpegAudioHeader.getFrameSize(i9) == -1) {
                extractorInput.skipFully(1);
                this.synchronizedHeaderData = 0;
                return 0;
            }
            MpegAudioHeader.populateHeader(i9, this.synchronizedHeader);
            if (this.basisTimeUs == C3322C.TIME_UNSET) {
                this.basisTimeUs = this.seeker.getTimeUs(extractorInput.getPosition());
                if (this.forcedFirstSampleTimestampUs != C3322C.TIME_UNSET) {
                    this.basisTimeUs += this.forcedFirstSampleTimestampUs - this.seeker.getTimeUs(0L);
                }
            }
            this.sampleBytesRemaining = this.synchronizedHeader.frameSize;
        }
        int iSampleData = this.trackOutput.sampleData(extractorInput, this.sampleBytesRemaining, true);
        if (iSampleData == -1) {
            return -1;
        }
        int i10 = this.sampleBytesRemaining - iSampleData;
        this.sampleBytesRemaining = i10;
        if (i10 > 0) {
            return 0;
        }
        this.trackOutput.sampleMetadata(this.basisTimeUs + ((this.samplesRead * C3322C.MICROS_PER_SECOND) / r14.sampleRate), 1, this.synchronizedHeader.frameSize, 0, null);
        this.samplesRead += this.synchronizedHeader.samplesPerFrame;
        this.sampleBytesRemaining = 0;
        return 0;
    }

    private boolean synchronize(ExtractorInput extractorInput, boolean z8) throws ParserException {
        int peekPosition;
        int i9;
        int frameSize;
        int i10 = z8 ? MAX_SNIFF_BYTES : 131072;
        extractorInput.resetPeekPosition();
        if (extractorInput.getPosition() == 0) {
            peekId3Data(extractorInput);
            peekPosition = (int) extractorInput.getPeekPosition();
            if (!z8) {
                extractorInput.skipFully(peekPosition);
            }
            i9 = 0;
        } else {
            peekPosition = 0;
            i9 = 0;
        }
        int i11 = i9;
        int i12 = i11;
        while (true) {
            if (!extractorInput.peekFully(this.scratch.data, 0, 4, i9 > 0)) {
                break;
            }
            this.scratch.setPosition(0);
            int i13 = this.scratch.readInt();
            if ((i11 == 0 || headersMatch(i13, i11)) && (frameSize = MpegAudioHeader.getFrameSize(i13)) != -1) {
                i9++;
                if (i9 != 1) {
                    if (i9 == 4) {
                        break;
                    }
                } else {
                    MpegAudioHeader.populateHeader(i13, this.synchronizedHeader);
                    i11 = i13;
                }
                extractorInput.advancePeekPosition(frameSize - 4);
            } else {
                int i14 = i12 + 1;
                if (i12 == i10) {
                    if (z8) {
                        return false;
                    }
                    throw new ParserException("Searched too many bytes.");
                }
                if (z8) {
                    extractorInput.resetPeekPosition();
                    extractorInput.advancePeekPosition(peekPosition + i14);
                } else {
                    extractorInput.skipFully(1);
                }
                i11 = 0;
                i12 = i14;
                i9 = 0;
            }
        }
        if (z8) {
            extractorInput.skipFully(peekPosition + i12);
        } else {
            extractorInput.resetPeekPosition();
        }
        this.synchronizedHeaderData = i11;
        return true;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        this.trackOutput = extractorOutput.track(0, 1);
        this.extractorOutput.endTracks();
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws ParserException {
        if (this.synchronizedHeaderData == 0) {
            try {
                synchronize(extractorInput, false);
            } catch (EOFException unused) {
                return -1;
            }
        }
        if (this.seeker == null) {
            Seeker seekerMaybeReadSeekFrame = maybeReadSeekFrame(extractorInput);
            this.seeker = seekerMaybeReadSeekFrame;
            if (seekerMaybeReadSeekFrame == null || (!seekerMaybeReadSeekFrame.isSeekable() && (this.flags & 1) != 0)) {
                this.seeker = getConstantBitrateSeeker(extractorInput);
            }
            this.extractorOutput.seekMap(this.seeker);
            TrackOutput trackOutput = this.trackOutput;
            MpegAudioHeader mpegAudioHeader = this.synchronizedHeader;
            String str = mpegAudioHeader.mimeType;
            int i9 = mpegAudioHeader.channels;
            int i10 = mpegAudioHeader.sampleRate;
            GaplessInfoHolder gaplessInfoHolder = this.gaplessInfoHolder;
            trackOutput.format(Format.createAudioSampleFormat(null, str, null, -1, 4096, i9, i10, -1, gaplessInfoHolder.encoderDelay, gaplessInfoHolder.encoderPadding, null, null, 0, null, (this.flags & 2) != 0 ? null : this.metadata));
        }
        return readSample(extractorInput);
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void release() {
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void seek(long j9, long j10) {
        this.synchronizedHeaderData = 0;
        this.basisTimeUs = C3322C.TIME_UNSET;
        this.samplesRead = 0L;
        this.sampleBytesRemaining = 0;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) {
        return synchronize(extractorInput, true);
    }

    public Mp3Extractor(int i9) {
        this(i9, C3322C.TIME_UNSET);
    }

    public Mp3Extractor(int i9, long j9) {
        this.flags = i9;
        this.forcedFirstSampleTimestampUs = j9;
        this.scratch = new ParsableByteArray(10);
        this.synchronizedHeader = new MpegAudioHeader();
        this.gaplessInfoHolder = new GaplessInfoHolder();
        this.basisTimeUs = C3322C.TIME_UNSET;
    }
}
