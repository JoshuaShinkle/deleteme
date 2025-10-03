package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.mp4.Atom;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Stack;

/* loaded from: classes.dex */
public final class Mp4Extractor implements Extractor, SeekMap {
    public static final int FLAG_WORKAROUND_IGNORE_EDIT_LISTS = 1;
    private static final long MAXIMUM_READ_AHEAD_BYTES_STREAM = 10485760;
    private static final long RELOAD_MINIMUM_SEEK_DISTANCE = 262144;
    private static final int STATE_READING_ATOM_HEADER = 0;
    private static final int STATE_READING_ATOM_PAYLOAD = 1;
    private static final int STATE_READING_SAMPLE = 2;
    private long[][] accumulatedSampleSizes;
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private final Stack<Atom.ContainerAtom> containerAtoms;
    private long durationUs;
    private ExtractorOutput extractorOutput;
    private int firstVideoTrackIndex;
    private final int flags;
    private boolean isQuickTime;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private int parserState;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private int sampleTrackIndex;
    private Mp4Track[] tracks;
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() { // from class: com.google.android.exoplayer2.extractor.mp4.Mp4Extractor.1
        @Override // com.google.android.exoplayer2.extractor.ExtractorsFactory
        public Extractor[] createExtractors() {
            return new Extractor[]{new Mp4Extractor()};
        }
    };
    private static final int BRAND_QUICKTIME = Util.getIntegerCodeForString("qt  ");

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public static final class Mp4Track {
        public int sampleIndex;
        public final TrackSampleTable sampleTable;
        public final Track track;
        public final TrackOutput trackOutput;

        public Mp4Track(Track track, TrackSampleTable trackSampleTable, TrackOutput trackOutput) {
            this.track = track;
            this.sampleTable = trackSampleTable;
            this.trackOutput = trackOutput;
        }
    }

    public Mp4Extractor() {
        this(0);
    }

    private static long[][] calculateAccumulatedSampleSizes(Mp4Track[] mp4TrackArr) {
        long[][] jArr = new long[mp4TrackArr.length][];
        int[] iArr = new int[mp4TrackArr.length];
        long[] jArr2 = new long[mp4TrackArr.length];
        boolean[] zArr = new boolean[mp4TrackArr.length];
        for (int i9 = 0; i9 < mp4TrackArr.length; i9++) {
            jArr[i9] = new long[mp4TrackArr[i9].sampleTable.sampleCount];
            jArr2[i9] = mp4TrackArr[i9].sampleTable.timestampsUs[0];
        }
        long j9 = 0;
        int i10 = 0;
        while (i10 < mp4TrackArr.length) {
            long j10 = Long.MAX_VALUE;
            int i11 = -1;
            for (int i12 = 0; i12 < mp4TrackArr.length; i12++) {
                if (!zArr[i12]) {
                    long j11 = jArr2[i12];
                    if (j11 <= j10) {
                        i11 = i12;
                        j10 = j11;
                    }
                }
            }
            int i13 = iArr[i11];
            long[] jArr3 = jArr[i11];
            jArr3[i13] = j9;
            TrackSampleTable trackSampleTable = mp4TrackArr[i11].sampleTable;
            j9 += trackSampleTable.sizes[i13];
            int i14 = i13 + 1;
            iArr[i11] = i14;
            if (i14 < jArr3.length) {
                jArr2[i11] = trackSampleTable.timestampsUs[i14];
            } else {
                zArr[i11] = true;
                i10++;
            }
        }
        return jArr;
    }

    private void enterReadingAtomHeaderState() {
        this.parserState = 0;
        this.atomHeaderBytesRead = 0;
    }

    private static int getSynchronizationSampleIndex(TrackSampleTable trackSampleTable, long j9) {
        int indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfEarlierOrEqualSynchronizationSample(j9);
        return indexOfEarlierOrEqualSynchronizationSample == -1 ? trackSampleTable.getIndexOfLaterOrEqualSynchronizationSample(j9) : indexOfEarlierOrEqualSynchronizationSample;
    }

    private int getTrackIndexOfNextReadSample(long j9) {
        int i9 = -1;
        int i10 = -1;
        int i11 = 0;
        long j10 = Long.MAX_VALUE;
        boolean z8 = true;
        long j11 = Long.MAX_VALUE;
        boolean z9 = true;
        long j12 = Long.MAX_VALUE;
        while (true) {
            Mp4Track[] mp4TrackArr = this.tracks;
            if (i11 >= mp4TrackArr.length) {
                break;
            }
            Mp4Track mp4Track = mp4TrackArr[i11];
            int i12 = mp4Track.sampleIndex;
            TrackSampleTable trackSampleTable = mp4Track.sampleTable;
            if (i12 != trackSampleTable.sampleCount) {
                long j13 = trackSampleTable.offsets[i12];
                long j14 = this.accumulatedSampleSizes[i11][i12];
                long j15 = j13 - j9;
                boolean z10 = j15 < 0 || j15 >= 262144;
                if ((!z10 && z9) || (z10 == z9 && j15 < j12)) {
                    z9 = z10;
                    j12 = j15;
                    i10 = i11;
                    j11 = j14;
                }
                if (j14 < j10) {
                    z8 = z10;
                    i9 = i11;
                    j10 = j14;
                }
            }
            i11++;
        }
        return (j10 == Long.MAX_VALUE || !z8 || j11 < j10 + MAXIMUM_READ_AHEAD_BYTES_STREAM) ? i10 : i9;
    }

    private static long maybeAdjustSeekOffset(TrackSampleTable trackSampleTable, long j9, long j10) {
        int synchronizationSampleIndex = getSynchronizationSampleIndex(trackSampleTable, j9);
        return synchronizationSampleIndex == -1 ? j10 : Math.min(trackSampleTable.offsets[synchronizationSampleIndex], j10);
    }

    private void processAtomEnded(long j9) throws ParserException {
        while (!this.containerAtoms.isEmpty() && this.containerAtoms.peek().endPosition == j9) {
            Atom.ContainerAtom containerAtomPop = this.containerAtoms.pop();
            if (containerAtomPop.type == Atom.TYPE_moov) {
                processMoovAtom(containerAtomPop);
                this.containerAtoms.clear();
                this.parserState = 2;
            } else if (!this.containerAtoms.isEmpty()) {
                this.containerAtoms.peek().add(containerAtomPop);
            }
        }
        if (this.parserState != 2) {
            enterReadingAtomHeaderState();
        }
    }

    private static boolean processFtypAtom(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        if (parsableByteArray.readInt() == BRAND_QUICKTIME) {
            return true;
        }
        parsableByteArray.skipBytes(4);
        while (parsableByteArray.bytesLeft() > 0) {
            if (parsableByteArray.readInt() == BRAND_QUICKTIME) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x007f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void processMoovAtom(Atom.ContainerAtom containerAtom) throws ParserException {
        Metadata udta;
        ArrayList arrayList = new ArrayList();
        GaplessInfoHolder gaplessInfoHolder = new GaplessInfoHolder();
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_udta);
        if (leafAtomOfType != null) {
            udta = AtomParsers.parseUdta(leafAtomOfType, this.isQuickTime);
            if (udta != null) {
                gaplessInfoHolder.setFromMetadata(udta);
            }
        } else {
            udta = null;
        }
        int size = -1;
        long jMax = C3322C.TIME_UNSET;
        for (int i9 = 0; i9 < containerAtom.containerChildren.size(); i9++) {
            Atom.ContainerAtom containerAtom2 = containerAtom.containerChildren.get(i9);
            if (containerAtom2.type == Atom.TYPE_trak) {
                Track trak = AtomParsers.parseTrak(containerAtom2, containerAtom.getLeafAtomOfType(Atom.TYPE_mvhd), C3322C.TIME_UNSET, null, (this.flags & 1) != 0, this.isQuickTime);
                if (trak != null) {
                    TrackSampleTable stbl = AtomParsers.parseStbl(trak, containerAtom2.getContainerAtomOfType(Atom.TYPE_mdia).getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl), gaplessInfoHolder);
                    if (stbl.sampleCount != 0) {
                        Mp4Track mp4Track = new Mp4Track(trak, stbl, this.extractorOutput.track(i9, trak.type));
                        Format formatCopyWithMaxInputSize = trak.format.copyWithMaxInputSize(stbl.maximumSize + 30);
                        if (trak.type == 1) {
                            if (gaplessInfoHolder.hasGaplessInfo()) {
                                formatCopyWithMaxInputSize = formatCopyWithMaxInputSize.copyWithGaplessInfo(gaplessInfoHolder.encoderDelay, gaplessInfoHolder.encoderPadding);
                            }
                            if (udta != null) {
                                formatCopyWithMaxInputSize = formatCopyWithMaxInputSize.copyWithMetadata(udta);
                            }
                        }
                        mp4Track.trackOutput.format(formatCopyWithMaxInputSize);
                        long j9 = trak.durationUs;
                        if (j9 == C3322C.TIME_UNSET) {
                            j9 = stbl.durationUs;
                        }
                        jMax = Math.max(jMax, j9);
                        if (trak.type == 2 && size == -1) {
                            size = arrayList.size();
                        }
                        arrayList.add(mp4Track);
                    }
                }
            }
        }
        this.firstVideoTrackIndex = size;
        this.durationUs = jMax;
        Mp4Track[] mp4TrackArr = (Mp4Track[]) arrayList.toArray(new Mp4Track[arrayList.size()]);
        this.tracks = mp4TrackArr;
        this.accumulatedSampleSizes = calculateAccumulatedSampleSizes(mp4TrackArr);
        this.extractorOutput.endTracks();
        this.extractorOutput.seekMap(this);
    }

    private boolean readAtomHeader(ExtractorInput extractorInput) throws ParserException {
        if (this.atomHeaderBytesRead == 0) {
            if (!extractorInput.readFully(this.atomHeader.data, 0, 8, true)) {
                return false;
            }
            this.atomHeaderBytesRead = 8;
            this.atomHeader.setPosition(0);
            this.atomSize = this.atomHeader.readUnsignedInt();
            this.atomType = this.atomHeader.readInt();
        }
        long j9 = this.atomSize;
        if (j9 == 1) {
            extractorInput.readFully(this.atomHeader.data, 8, 8);
            this.atomHeaderBytesRead += 8;
            this.atomSize = this.atomHeader.readUnsignedLongToLong();
        } else if (j9 == 0) {
            long length = extractorInput.getLength();
            if (length == -1 && !this.containerAtoms.isEmpty()) {
                length = this.containerAtoms.peek().endPosition;
            }
            if (length != -1) {
                this.atomSize = (length - extractorInput.getPosition()) + this.atomHeaderBytesRead;
            }
        }
        if (this.atomSize < this.atomHeaderBytesRead) {
            throw new ParserException("Atom size less than header length (unsupported).");
        }
        if (shouldParseContainerAtom(this.atomType)) {
            long position = (extractorInput.getPosition() + this.atomSize) - this.atomHeaderBytesRead;
            this.containerAtoms.add(new Atom.ContainerAtom(this.atomType, position));
            if (this.atomSize == this.atomHeaderBytesRead) {
                processAtomEnded(position);
            } else {
                enterReadingAtomHeaderState();
            }
        } else if (shouldParseLeafAtom(this.atomType)) {
            Assertions.checkState(this.atomHeaderBytesRead == 8);
            Assertions.checkState(this.atomSize <= 2147483647L);
            ParsableByteArray parsableByteArray = new ParsableByteArray((int) this.atomSize);
            this.atomData = parsableByteArray;
            System.arraycopy(this.atomHeader.data, 0, parsableByteArray.data, 0, 8);
            this.parserState = 1;
        } else {
            this.atomData = null;
            this.parserState = 1;
        }
        return true;
    }

    private boolean readAtomPayload(ExtractorInput extractorInput, PositionHolder positionHolder) throws ParserException {
        boolean z8;
        long j9 = this.atomSize - this.atomHeaderBytesRead;
        long position = extractorInput.getPosition() + j9;
        ParsableByteArray parsableByteArray = this.atomData;
        if (parsableByteArray != null) {
            extractorInput.readFully(parsableByteArray.data, this.atomHeaderBytesRead, (int) j9);
            if (this.atomType == Atom.TYPE_ftyp) {
                this.isQuickTime = processFtypAtom(this.atomData);
            } else if (!this.containerAtoms.isEmpty()) {
                this.containerAtoms.peek().add(new Atom.LeafAtom(this.atomType, this.atomData));
            }
        } else {
            if (j9 >= 262144) {
                positionHolder.position = extractorInput.getPosition() + j9;
                z8 = true;
                processAtomEnded(position);
                return (z8 || this.parserState == 2) ? false : true;
            }
            extractorInput.skipFully((int) j9);
        }
        z8 = false;
        processAtomEnded(position);
        if (z8) {
        }
    }

    private int readSample(ExtractorInput extractorInput, PositionHolder positionHolder) {
        long position = extractorInput.getPosition();
        if (this.sampleTrackIndex == -1) {
            int trackIndexOfNextReadSample = getTrackIndexOfNextReadSample(position);
            this.sampleTrackIndex = trackIndexOfNextReadSample;
            if (trackIndexOfNextReadSample == -1) {
                return -1;
            }
        }
        Mp4Track mp4Track = this.tracks[this.sampleTrackIndex];
        TrackOutput trackOutput = mp4Track.trackOutput;
        int i9 = mp4Track.sampleIndex;
        TrackSampleTable trackSampleTable = mp4Track.sampleTable;
        long j9 = trackSampleTable.offsets[i9];
        int i10 = trackSampleTable.sizes[i9];
        long j10 = (j9 - position) + this.sampleBytesWritten;
        if (j10 < 0 || j10 >= 262144) {
            positionHolder.position = j9;
            return 1;
        }
        if (mp4Track.track.sampleTransformation == 1) {
            j10 += 8;
            i10 -= 8;
        }
        extractorInput.skipFully((int) j10);
        int i11 = mp4Track.track.nalUnitLengthFieldLength;
        if (i11 == 0) {
            while (true) {
                int i12 = this.sampleBytesWritten;
                if (i12 >= i10) {
                    break;
                }
                int iSampleData = trackOutput.sampleData(extractorInput, i10 - i12, false);
                this.sampleBytesWritten += iSampleData;
                this.sampleCurrentNalBytesRemaining -= iSampleData;
            }
        } else {
            byte[] bArr = this.nalLength.data;
            bArr[0] = 0;
            bArr[1] = 0;
            bArr[2] = 0;
            int i13 = 4 - i11;
            while (this.sampleBytesWritten < i10) {
                int i14 = this.sampleCurrentNalBytesRemaining;
                if (i14 == 0) {
                    extractorInput.readFully(this.nalLength.data, i13, i11);
                    this.nalLength.setPosition(0);
                    this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                    this.nalStartCode.setPosition(0);
                    trackOutput.sampleData(this.nalStartCode, 4);
                    this.sampleBytesWritten += 4;
                    i10 += i13;
                } else {
                    int iSampleData2 = trackOutput.sampleData(extractorInput, i14, false);
                    this.sampleBytesWritten += iSampleData2;
                    this.sampleCurrentNalBytesRemaining -= iSampleData2;
                }
            }
        }
        TrackSampleTable trackSampleTable2 = mp4Track.sampleTable;
        trackOutput.sampleMetadata(trackSampleTable2.timestampsUs[i9], trackSampleTable2.flags[i9], i10, 0, null);
        mp4Track.sampleIndex++;
        this.sampleTrackIndex = -1;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        return 0;
    }

    private static boolean shouldParseContainerAtom(int i9) {
        return i9 == Atom.TYPE_moov || i9 == Atom.TYPE_trak || i9 == Atom.TYPE_mdia || i9 == Atom.TYPE_minf || i9 == Atom.TYPE_stbl || i9 == Atom.TYPE_edts;
    }

    private static boolean shouldParseLeafAtom(int i9) {
        return i9 == Atom.TYPE_mdhd || i9 == Atom.TYPE_mvhd || i9 == Atom.TYPE_hdlr || i9 == Atom.TYPE_stsd || i9 == Atom.TYPE_stts || i9 == Atom.TYPE_stss || i9 == Atom.TYPE_ctts || i9 == Atom.TYPE_elst || i9 == Atom.TYPE_stsc || i9 == Atom.TYPE_stsz || i9 == Atom.TYPE_stz2 || i9 == Atom.TYPE_stco || i9 == Atom.TYPE_co64 || i9 == Atom.TYPE_tkhd || i9 == Atom.TYPE_ftyp || i9 == Atom.TYPE_udta;
    }

    private void updateSampleIndices(long j9) {
        for (Mp4Track mp4Track : this.tracks) {
            TrackSampleTable trackSampleTable = mp4Track.sampleTable;
            int indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfEarlierOrEqualSynchronizationSample(j9);
            if (indexOfEarlierOrEqualSynchronizationSample == -1) {
                indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfLaterOrEqualSynchronizationSample(j9);
            }
            mp4Track.sampleIndex = indexOfEarlierOrEqualSynchronizationSample;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j9) {
        long j10;
        long j11;
        int indexOfLaterOrEqualSynchronizationSample;
        Mp4Track[] mp4TrackArr = this.tracks;
        if (mp4TrackArr.length == 0) {
            return new SeekMap.SeekPoints(SeekPoint.START);
        }
        int i9 = this.firstVideoTrackIndex;
        long jMaybeAdjustSeekOffset = -1;
        if (i9 != -1) {
            TrackSampleTable trackSampleTable = mp4TrackArr[i9].sampleTable;
            int synchronizationSampleIndex = getSynchronizationSampleIndex(trackSampleTable, j9);
            if (synchronizationSampleIndex == -1) {
                return new SeekMap.SeekPoints(SeekPoint.START);
            }
            long j12 = trackSampleTable.timestampsUs[synchronizationSampleIndex];
            j10 = trackSampleTable.offsets[synchronizationSampleIndex];
            if (j12 >= j9 || synchronizationSampleIndex >= trackSampleTable.sampleCount - 1 || (indexOfLaterOrEqualSynchronizationSample = trackSampleTable.getIndexOfLaterOrEqualSynchronizationSample(j9)) == -1 || indexOfLaterOrEqualSynchronizationSample == synchronizationSampleIndex) {
                j11 = -9223372036854775807L;
            } else {
                j11 = trackSampleTable.timestampsUs[indexOfLaterOrEqualSynchronizationSample];
                jMaybeAdjustSeekOffset = trackSampleTable.offsets[indexOfLaterOrEqualSynchronizationSample];
            }
            j9 = j12;
        } else {
            j10 = Long.MAX_VALUE;
            j11 = -9223372036854775807L;
        }
        int i10 = 0;
        while (true) {
            Mp4Track[] mp4TrackArr2 = this.tracks;
            if (i10 >= mp4TrackArr2.length) {
                break;
            }
            if (i10 != this.firstVideoTrackIndex) {
                TrackSampleTable trackSampleTable2 = mp4TrackArr2[i10].sampleTable;
                long jMaybeAdjustSeekOffset2 = maybeAdjustSeekOffset(trackSampleTable2, j9, j10);
                if (j11 != C3322C.TIME_UNSET) {
                    jMaybeAdjustSeekOffset = maybeAdjustSeekOffset(trackSampleTable2, j11, jMaybeAdjustSeekOffset);
                }
                j10 = jMaybeAdjustSeekOffset2;
            }
            i10++;
        }
        SeekPoint seekPoint = new SeekPoint(j9, j10);
        return j11 == C3322C.TIME_UNSET ? new SeekMap.SeekPoints(seekPoint) : new SeekMap.SeekPoints(seekPoint, new SeekPoint(j11, jMaybeAdjustSeekOffset));
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) {
        while (true) {
            int i9 = this.parserState;
            if (i9 != 0) {
                if (i9 != 1) {
                    if (i9 == 2) {
                        return readSample(extractorInput, positionHolder);
                    }
                    throw new IllegalStateException();
                }
                if (readAtomPayload(extractorInput, positionHolder)) {
                    return 1;
                }
            } else if (!readAtomHeader(extractorInput)) {
                return -1;
            }
        }
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void release() {
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void seek(long j9, long j10) {
        this.containerAtoms.clear();
        this.atomHeaderBytesRead = 0;
        this.sampleTrackIndex = -1;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        if (j9 == 0) {
            enterReadingAtomHeaderState();
        } else if (this.tracks != null) {
            updateSampleIndices(j10);
        }
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) {
        return Sniffer.sniffUnfragmented(extractorInput);
    }

    public Mp4Extractor(int i9) {
        this.flags = i9;
        this.atomHeader = new ParsableByteArray(16);
        this.containerAtoms = new Stack<>();
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleTrackIndex = -1;
    }
}
