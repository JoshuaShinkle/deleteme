package com.google.android.exoplayer2.extractor.mp4;

import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.mp4.Atom;
import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.android.exoplayer2.text.cea.CeaUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.Ascii;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

/* loaded from: classes.dex */
public final class FragmentedMp4Extractor implements Extractor {
    public static final int FLAG_ENABLE_EMSG_TRACK = 4;
    private static final int FLAG_SIDELOADED = 8;
    public static final int FLAG_WORKAROUND_EVERY_VIDEO_FRAME_IS_SYNC_FRAME = 1;
    public static final int FLAG_WORKAROUND_IGNORE_EDIT_LISTS = 16;
    public static final int FLAG_WORKAROUND_IGNORE_TFDT_BOX = 2;
    private static final int STATE_READING_ATOM_HEADER = 0;
    private static final int STATE_READING_ATOM_PAYLOAD = 1;
    private static final int STATE_READING_ENCRYPTION_DATA = 2;
    private static final int STATE_READING_SAMPLE_CONTINUE = 4;
    private static final int STATE_READING_SAMPLE_START = 3;
    private static final String TAG = "FragmentedMp4Extractor";
    private final TrackOutput additionalEmsgTrackOutput;
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private TrackOutput[] cea608TrackOutputs;
    private final List<Format> closedCaptionFormats;
    private final Stack<Atom.ContainerAtom> containerAtoms;
    private TrackBundle currentTrackBundle;
    private final ParsableByteArray defaultInitializationVector;
    private long durationUs;
    private TrackOutput[] emsgTrackOutputs;
    private final ParsableByteArray encryptionSignalByte;
    private long endOfMdatPosition;
    private final byte[] extendedTypeScratch;
    private ExtractorOutput extractorOutput;
    private final int flags;
    private boolean haveOutputSeekMap;
    private final ParsableByteArray nalBuffer;
    private final ParsableByteArray nalPrefix;
    private final ParsableByteArray nalStartCode;
    private int parserState;
    private int pendingMetadataSampleBytes;
    private final ArrayDeque<MetadataSampleInfo> pendingMetadataSampleInfos;
    private boolean processSeiNalUnitPayload;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private int sampleSize;
    private long segmentIndexEarliestPresentationTimeUs;
    private final DrmInitData sideloadedDrmInitData;
    private final Track sideloadedTrack;
    private final TimestampAdjuster timestampAdjuster;
    private final SparseArray<TrackBundle> trackBundles;
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() { // from class: com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor.1
        @Override // com.google.android.exoplayer2.extractor.ExtractorsFactory
        public Extractor[] createExtractors() {
            return new Extractor[]{new FragmentedMp4Extractor()};
        }
    };
    private static final int SAMPLE_GROUP_TYPE_seig = Util.getIntegerCodeForString("seig");
    private static final byte[] PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE = {-94, 57, 79, 82, 90, -101, 79, Ascii.DC4, -94, 68, 108, 66, 124, 100, -115, -12};
    private static final Format EMSG_FORMAT = Format.createSampleFormat(null, MimeTypes.APPLICATION_EMSG, Long.MAX_VALUE);

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public static final class MetadataSampleInfo {
        public final long presentationTimeDeltaUs;
        public final int size;

        public MetadataSampleInfo(long j9, int i9) {
            this.presentationTimeDeltaUs = j9;
            this.size = i9;
        }
    }

    public static final class TrackBundle {
        public int currentSampleInTrackRun;
        public int currentSampleIndex;
        public int currentTrackRunIndex;
        public DefaultSampleValues defaultSampleValues;
        public final TrackFragment fragment = new TrackFragment();
        public final TrackOutput output;
        public Track track;

        public TrackBundle(TrackOutput trackOutput) {
            this.output = trackOutput;
        }

        public void init(Track track, DefaultSampleValues defaultSampleValues) {
            this.track = (Track) Assertions.checkNotNull(track);
            this.defaultSampleValues = (DefaultSampleValues) Assertions.checkNotNull(defaultSampleValues);
            this.output.format(track.format);
            reset();
        }

        public void reset() {
            this.fragment.reset();
            this.currentSampleIndex = 0;
            this.currentTrackRunIndex = 0;
            this.currentSampleInTrackRun = 0;
        }

        public void updateDrmInitData(DrmInitData drmInitData) {
            TrackEncryptionBox sampleDescriptionEncryptionBox = this.track.getSampleDescriptionEncryptionBox(this.fragment.header.sampleDescriptionIndex);
            this.output.format(this.track.format.copyWithDrmInitData(drmInitData.copyWithSchemeType(sampleDescriptionEncryptionBox != null ? sampleDescriptionEncryptionBox.schemeType : null)));
        }
    }

    public FragmentedMp4Extractor() {
        this(0);
    }

    private int appendSampleEncryptionData(TrackBundle trackBundle) {
        ParsableByteArray parsableByteArray;
        TrackFragment trackFragment = trackBundle.fragment;
        int i9 = trackFragment.header.sampleDescriptionIndex;
        TrackEncryptionBox sampleDescriptionEncryptionBox = trackFragment.trackEncryptionBox;
        if (sampleDescriptionEncryptionBox == null) {
            sampleDescriptionEncryptionBox = trackBundle.track.getSampleDescriptionEncryptionBox(i9);
        }
        int length = sampleDescriptionEncryptionBox.initializationVectorSize;
        if (length != 0) {
            parsableByteArray = trackFragment.sampleEncryptionData;
        } else {
            byte[] bArr = sampleDescriptionEncryptionBox.defaultInitializationVector;
            this.defaultInitializationVector.reset(bArr, bArr.length);
            parsableByteArray = this.defaultInitializationVector;
            length = bArr.length;
        }
        boolean z8 = trackFragment.sampleHasSubsampleEncryptionTable[trackBundle.currentSampleIndex];
        ParsableByteArray parsableByteArray2 = this.encryptionSignalByte;
        parsableByteArray2.data[0] = (byte) ((z8 ? 128 : 0) | length);
        parsableByteArray2.setPosition(0);
        TrackOutput trackOutput = trackBundle.output;
        trackOutput.sampleData(this.encryptionSignalByte, 1);
        trackOutput.sampleData(parsableByteArray, length);
        if (!z8) {
            return length + 1;
        }
        ParsableByteArray parsableByteArray3 = trackFragment.sampleEncryptionData;
        int unsignedShort = parsableByteArray3.readUnsignedShort();
        parsableByteArray3.skipBytes(-2);
        int i10 = (unsignedShort * 6) + 2;
        trackOutput.sampleData(parsableByteArray3, i10);
        return length + 1 + i10;
    }

    private void enterReadingAtomHeaderState() {
        this.parserState = 0;
        this.atomHeaderBytesRead = 0;
    }

    private static DrmInitData getDrmInitDataFromAtoms(List<Atom.LeafAtom> list) {
        int size = list.size();
        ArrayList arrayList = null;
        for (int i9 = 0; i9 < size; i9++) {
            Atom.LeafAtom leafAtom = list.get(i9);
            if (leafAtom.type == Atom.TYPE_pssh) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                byte[] bArr = leafAtom.data.data;
                UUID uuid = PsshAtomUtil.parseUuid(bArr);
                if (uuid == null) {
                    Log.w(TAG, "Skipped pssh atom (failed to extract uuid)");
                } else {
                    arrayList.add(new DrmInitData.SchemeData(uuid, MimeTypes.VIDEO_MP4, bArr));
                }
            }
        }
        if (arrayList == null) {
            return null;
        }
        return new DrmInitData(arrayList);
    }

    private static TrackBundle getNextFragmentRun(SparseArray<TrackBundle> sparseArray) {
        int size = sparseArray.size();
        TrackBundle trackBundle = null;
        long j9 = Long.MAX_VALUE;
        for (int i9 = 0; i9 < size; i9++) {
            TrackBundle trackBundleValueAt = sparseArray.valueAt(i9);
            int i10 = trackBundleValueAt.currentTrackRunIndex;
            TrackFragment trackFragment = trackBundleValueAt.fragment;
            if (i10 != trackFragment.trunCount) {
                long j10 = trackFragment.trunDataPosition[i10];
                if (j10 < j9) {
                    trackBundle = trackBundleValueAt;
                    j9 = j10;
                }
            }
        }
        return trackBundle;
    }

    private void maybeInitExtraTracks() {
        int i9;
        if (this.emsgTrackOutputs == null) {
            TrackOutput[] trackOutputArr = new TrackOutput[2];
            this.emsgTrackOutputs = trackOutputArr;
            TrackOutput trackOutput = this.additionalEmsgTrackOutput;
            if (trackOutput != null) {
                trackOutputArr[0] = trackOutput;
                i9 = 1;
            } else {
                i9 = 0;
            }
            if ((this.flags & 4) != 0) {
                trackOutputArr[i9] = this.extractorOutput.track(this.trackBundles.size(), 4);
                i9++;
            }
            TrackOutput[] trackOutputArr2 = (TrackOutput[]) Arrays.copyOf(this.emsgTrackOutputs, i9);
            this.emsgTrackOutputs = trackOutputArr2;
            for (TrackOutput trackOutput2 : trackOutputArr2) {
                trackOutput2.format(EMSG_FORMAT);
            }
        }
        if (this.cea608TrackOutputs == null) {
            this.cea608TrackOutputs = new TrackOutput[this.closedCaptionFormats.size()];
            for (int i10 = 0; i10 < this.cea608TrackOutputs.length; i10++) {
                TrackOutput trackOutputTrack = this.extractorOutput.track(this.trackBundles.size() + 1 + i10, 3);
                trackOutputTrack.format(this.closedCaptionFormats.get(i10));
                this.cea608TrackOutputs[i10] = trackOutputTrack;
            }
        }
    }

    private void onContainerAtomRead(Atom.ContainerAtom containerAtom) throws ParserException {
        int i9 = containerAtom.type;
        if (i9 == Atom.TYPE_moov) {
            onMoovContainerAtomRead(containerAtom);
        } else if (i9 == Atom.TYPE_moof) {
            onMoofContainerAtomRead(containerAtom);
        } else {
            if (this.containerAtoms.isEmpty()) {
                return;
            }
            this.containerAtoms.peek().add(containerAtom);
        }
    }

    private void onEmsgLeafAtomRead(ParsableByteArray parsableByteArray) {
        TrackOutput[] trackOutputArr = this.emsgTrackOutputs;
        if (trackOutputArr == null || trackOutputArr.length == 0) {
            return;
        }
        parsableByteArray.setPosition(12);
        int iBytesLeft = parsableByteArray.bytesLeft();
        parsableByteArray.readNullTerminatedString();
        parsableByteArray.readNullTerminatedString();
        long jScaleLargeTimestamp = Util.scaleLargeTimestamp(parsableByteArray.readUnsignedInt(), C3322C.MICROS_PER_SECOND, parsableByteArray.readUnsignedInt());
        for (TrackOutput trackOutput : this.emsgTrackOutputs) {
            parsableByteArray.setPosition(12);
            trackOutput.sampleData(parsableByteArray, iBytesLeft);
        }
        if (this.segmentIndexEarliestPresentationTimeUs == C3322C.TIME_UNSET) {
            this.pendingMetadataSampleInfos.addLast(new MetadataSampleInfo(jScaleLargeTimestamp, iBytesLeft));
            this.pendingMetadataSampleBytes += iBytesLeft;
            return;
        }
        for (TrackOutput trackOutput2 : this.emsgTrackOutputs) {
            trackOutput2.sampleMetadata(this.segmentIndexEarliestPresentationTimeUs + jScaleLargeTimestamp, 1, iBytesLeft, 0, null);
        }
    }

    private void onLeafAtomRead(Atom.LeafAtom leafAtom, long j9) throws ParserException {
        if (!this.containerAtoms.isEmpty()) {
            this.containerAtoms.peek().add(leafAtom);
            return;
        }
        int i9 = leafAtom.type;
        if (i9 != Atom.TYPE_sidx) {
            if (i9 == Atom.TYPE_emsg) {
                onEmsgLeafAtomRead(leafAtom.data);
            }
        } else {
            Pair<Long, ChunkIndex> sidx = parseSidx(leafAtom.data, j9);
            this.segmentIndexEarliestPresentationTimeUs = ((Long) sidx.first).longValue();
            this.extractorOutput.seekMap((SeekMap) sidx.second);
            this.haveOutputSeekMap = true;
        }
    }

    private void onMoofContainerAtomRead(Atom.ContainerAtom containerAtom) throws ParserException {
        parseMoof(containerAtom, this.trackBundles, this.flags, this.extendedTypeScratch);
        DrmInitData drmInitDataFromAtoms = this.sideloadedDrmInitData != null ? null : getDrmInitDataFromAtoms(containerAtom.leafChildren);
        if (drmInitDataFromAtoms != null) {
            int size = this.trackBundles.size();
            for (int i9 = 0; i9 < size; i9++) {
                this.trackBundles.valueAt(i9).updateDrmInitData(drmInitDataFromAtoms);
            }
        }
    }

    private void onMoovContainerAtomRead(Atom.ContainerAtom containerAtom) {
        int i9;
        int i10;
        int i11 = 0;
        Assertions.checkState(this.sideloadedTrack == null, "Unexpected moov box.");
        DrmInitData drmInitDataFromAtoms = this.sideloadedDrmInitData;
        if (drmInitDataFromAtoms == null) {
            drmInitDataFromAtoms = getDrmInitDataFromAtoms(containerAtom.leafChildren);
        }
        Atom.ContainerAtom containerAtomOfType = containerAtom.getContainerAtomOfType(Atom.TYPE_mvex);
        SparseArray sparseArray = new SparseArray();
        int size = containerAtomOfType.leafChildren.size();
        long mehd = -9223372036854775807L;
        for (int i12 = 0; i12 < size; i12++) {
            Atom.LeafAtom leafAtom = containerAtomOfType.leafChildren.get(i12);
            int i13 = leafAtom.type;
            if (i13 == Atom.TYPE_trex) {
                Pair<Integer, DefaultSampleValues> trex = parseTrex(leafAtom.data);
                sparseArray.put(((Integer) trex.first).intValue(), trex.second);
            } else if (i13 == Atom.TYPE_mehd) {
                mehd = parseMehd(leafAtom.data);
            }
        }
        SparseArray sparseArray2 = new SparseArray();
        int size2 = containerAtom.containerChildren.size();
        int i14 = 0;
        while (i14 < size2) {
            Atom.ContainerAtom containerAtom2 = containerAtom.containerChildren.get(i14);
            if (containerAtom2.type == Atom.TYPE_trak) {
                i9 = i14;
                i10 = size2;
                Track trak = AtomParsers.parseTrak(containerAtom2, containerAtom.getLeafAtomOfType(Atom.TYPE_mvhd), mehd, drmInitDataFromAtoms, (this.flags & 16) != 0, false);
                if (trak != null) {
                    sparseArray2.put(trak.f15304id, trak);
                }
            } else {
                i9 = i14;
                i10 = size2;
            }
            i14 = i9 + 1;
            size2 = i10;
        }
        int size3 = sparseArray2.size();
        if (this.trackBundles.size() != 0) {
            Assertions.checkState(this.trackBundles.size() == size3);
            while (i11 < size3) {
                Track track = (Track) sparseArray2.valueAt(i11);
                this.trackBundles.get(track.f15304id).init(track, (DefaultSampleValues) sparseArray.get(track.f15304id));
                i11++;
            }
            return;
        }
        while (i11 < size3) {
            Track track2 = (Track) sparseArray2.valueAt(i11);
            TrackBundle trackBundle = new TrackBundle(this.extractorOutput.track(i11, track2.type));
            trackBundle.init(track2, (DefaultSampleValues) sparseArray.get(track2.f15304id));
            this.trackBundles.put(track2.f15304id, trackBundle);
            this.durationUs = Math.max(this.durationUs, track2.durationUs);
            i11++;
        }
        maybeInitExtraTracks();
        this.extractorOutput.endTracks();
    }

    private void outputPendingMetadataSamples(long j9) {
        while (!this.pendingMetadataSampleInfos.isEmpty()) {
            MetadataSampleInfo metadataSampleInfoRemoveFirst = this.pendingMetadataSampleInfos.removeFirst();
            this.pendingMetadataSampleBytes -= metadataSampleInfoRemoveFirst.size;
            for (TrackOutput trackOutput : this.emsgTrackOutputs) {
                trackOutput.sampleMetadata(metadataSampleInfoRemoveFirst.presentationTimeDeltaUs + j9, 1, metadataSampleInfoRemoveFirst.size, this.pendingMetadataSampleBytes, null);
            }
        }
    }

    private static long parseMehd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        return Atom.parseFullAtomVersion(parsableByteArray.readInt()) == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
    }

    private static void parseMoof(Atom.ContainerAtom containerAtom, SparseArray<TrackBundle> sparseArray, int i9, byte[] bArr) throws ParserException {
        int size = containerAtom.containerChildren.size();
        for (int i10 = 0; i10 < size; i10++) {
            Atom.ContainerAtom containerAtom2 = containerAtom.containerChildren.get(i10);
            if (containerAtom2.type == Atom.TYPE_traf) {
                parseTraf(containerAtom2, sparseArray, i9, bArr);
            }
        }
    }

    private static void parseSaio(ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        parsableByteArray.setPosition(8);
        int i9 = parsableByteArray.readInt();
        if ((Atom.parseFullAtomFlags(i9) & 1) == 1) {
            parsableByteArray.skipBytes(8);
        }
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (unsignedIntToInt == 1) {
            trackFragment.auxiliaryDataPosition += Atom.parseFullAtomVersion(i9) == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
        } else {
            throw new ParserException("Unexpected saio entry count: " + unsignedIntToInt);
        }
    }

    private static void parseSaiz(TrackEncryptionBox trackEncryptionBox, ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        int i9;
        int i10 = trackEncryptionBox.initializationVectorSize;
        parsableByteArray.setPosition(8);
        if ((Atom.parseFullAtomFlags(parsableByteArray.readInt()) & 1) == 1) {
            parsableByteArray.skipBytes(8);
        }
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (unsignedIntToInt != trackFragment.sampleCount) {
            throw new ParserException("Length mismatch: " + unsignedIntToInt + ", " + trackFragment.sampleCount);
        }
        if (unsignedByte == 0) {
            boolean[] zArr = trackFragment.sampleHasSubsampleEncryptionTable;
            i9 = 0;
            for (int i11 = 0; i11 < unsignedIntToInt; i11++) {
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                i9 += unsignedByte2;
                zArr[i11] = unsignedByte2 > i10;
            }
        } else {
            i9 = (unsignedByte * unsignedIntToInt) + 0;
            Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, unsignedIntToInt, unsignedByte > i10);
        }
        trackFragment.initEncryptionData(i9);
    }

    private static void parseSenc(ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        parseSenc(parsableByteArray, 0, trackFragment);
    }

    private static void parseSgpd(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, String str, TrackFragment trackFragment) throws ParserException {
        byte[] bArr;
        parsableByteArray.setPosition(8);
        int i9 = parsableByteArray.readInt();
        int i10 = parsableByteArray.readInt();
        int i11 = SAMPLE_GROUP_TYPE_seig;
        if (i10 != i11) {
            return;
        }
        if (Atom.parseFullAtomVersion(i9) == 1) {
            parsableByteArray.skipBytes(4);
        }
        if (parsableByteArray.readInt() != 1) {
            throw new ParserException("Entry count in sbgp != 1 (unsupported).");
        }
        parsableByteArray2.setPosition(8);
        int i12 = parsableByteArray2.readInt();
        if (parsableByteArray2.readInt() != i11) {
            return;
        }
        int fullAtomVersion = Atom.parseFullAtomVersion(i12);
        if (fullAtomVersion == 1) {
            if (parsableByteArray2.readUnsignedInt() == 0) {
                throw new ParserException("Variable length description in sgpd found (unsupported)");
            }
        } else if (fullAtomVersion >= 2) {
            parsableByteArray2.skipBytes(4);
        }
        if (parsableByteArray2.readUnsignedInt() != 1) {
            throw new ParserException("Entry count in sgpd != 1 (unsupported).");
        }
        parsableByteArray2.skipBytes(1);
        int unsignedByte = parsableByteArray2.readUnsignedByte();
        int i13 = (unsignedByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
        int i14 = unsignedByte & 15;
        boolean z8 = parsableByteArray2.readUnsignedByte() == 1;
        if (z8) {
            int unsignedByte2 = parsableByteArray2.readUnsignedByte();
            byte[] bArr2 = new byte[16];
            parsableByteArray2.readBytes(bArr2, 0, 16);
            if (z8 && unsignedByte2 == 0) {
                int unsignedByte3 = parsableByteArray2.readUnsignedByte();
                byte[] bArr3 = new byte[unsignedByte3];
                parsableByteArray2.readBytes(bArr3, 0, unsignedByte3);
                bArr = bArr3;
            } else {
                bArr = null;
            }
            trackFragment.definesEncryptionData = true;
            trackFragment.trackEncryptionBox = new TrackEncryptionBox(z8, str, unsignedByte2, bArr2, i13, i14, bArr);
        }
    }

    private static Pair<Long, ChunkIndex> parseSidx(ParsableByteArray parsableByteArray, long j9) throws ParserException {
        long unsignedLongToLong;
        long unsignedLongToLong2;
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(4);
        long unsignedInt = parsableByteArray.readUnsignedInt();
        if (fullAtomVersion == 0) {
            unsignedLongToLong = parsableByteArray.readUnsignedInt();
            unsignedLongToLong2 = parsableByteArray.readUnsignedInt();
        } else {
            unsignedLongToLong = parsableByteArray.readUnsignedLongToLong();
            unsignedLongToLong2 = parsableByteArray.readUnsignedLongToLong();
        }
        long j10 = unsignedLongToLong;
        long j11 = j9 + unsignedLongToLong2;
        long jScaleLargeTimestamp = Util.scaleLargeTimestamp(j10, C3322C.MICROS_PER_SECOND, unsignedInt);
        parsableByteArray.skipBytes(2);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int[] iArr = new int[unsignedShort];
        long[] jArr = new long[unsignedShort];
        long[] jArr2 = new long[unsignedShort];
        long[] jArr3 = new long[unsignedShort];
        long j12 = jScaleLargeTimestamp;
        int i9 = 0;
        long j13 = j10;
        while (i9 < unsignedShort) {
            int i10 = parsableByteArray.readInt();
            if ((i10 & Integer.MIN_VALUE) != 0) {
                throw new ParserException("Unhandled indirect reference");
            }
            long unsignedInt2 = parsableByteArray.readUnsignedInt();
            iArr[i9] = i10 & Integer.MAX_VALUE;
            jArr[i9] = j11;
            jArr3[i9] = j12;
            long j14 = j13 + unsignedInt2;
            long[] jArr4 = jArr2;
            long[] jArr5 = jArr3;
            int i11 = unsignedShort;
            long jScaleLargeTimestamp2 = Util.scaleLargeTimestamp(j14, C3322C.MICROS_PER_SECOND, unsignedInt);
            jArr4[i9] = jScaleLargeTimestamp2 - jArr5[i9];
            parsableByteArray.skipBytes(4);
            j11 += r1[i9];
            i9++;
            iArr = iArr;
            jArr3 = jArr5;
            jArr2 = jArr4;
            jArr = jArr;
            unsignedShort = i11;
            j13 = j14;
            j12 = jScaleLargeTimestamp2;
        }
        return Pair.create(Long.valueOf(jScaleLargeTimestamp), new ChunkIndex(iArr, jArr, jArr2, jArr3));
    }

    private static long parseTfdt(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        return Atom.parseFullAtomVersion(parsableByteArray.readInt()) == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
    }

    private static TrackBundle parseTfhd(ParsableByteArray parsableByteArray, SparseArray<TrackBundle> sparseArray, int i9) {
        parsableByteArray.setPosition(8);
        int fullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
        int i10 = parsableByteArray.readInt();
        if ((i9 & 8) != 0) {
            i10 = 0;
        }
        TrackBundle trackBundle = sparseArray.get(i10);
        if (trackBundle == null) {
            return null;
        }
        if ((fullAtomFlags & 1) != 0) {
            long unsignedLongToLong = parsableByteArray.readUnsignedLongToLong();
            TrackFragment trackFragment = trackBundle.fragment;
            trackFragment.dataPosition = unsignedLongToLong;
            trackFragment.auxiliaryDataPosition = unsignedLongToLong;
        }
        DefaultSampleValues defaultSampleValues = trackBundle.defaultSampleValues;
        trackBundle.fragment.header = new DefaultSampleValues((fullAtomFlags & 2) != 0 ? parsableByteArray.readUnsignedIntToInt() - 1 : defaultSampleValues.sampleDescriptionIndex, (fullAtomFlags & 8) != 0 ? parsableByteArray.readUnsignedIntToInt() : defaultSampleValues.duration, (fullAtomFlags & 16) != 0 ? parsableByteArray.readUnsignedIntToInt() : defaultSampleValues.size, (fullAtomFlags & 32) != 0 ? parsableByteArray.readUnsignedIntToInt() : defaultSampleValues.flags);
        return trackBundle;
    }

    private static void parseTraf(Atom.ContainerAtom containerAtom, SparseArray<TrackBundle> sparseArray, int i9, byte[] bArr) throws ParserException {
        TrackBundle tfhd = parseTfhd(containerAtom.getLeafAtomOfType(Atom.TYPE_tfhd).data, sparseArray, i9);
        if (tfhd == null) {
            return;
        }
        TrackFragment trackFragment = tfhd.fragment;
        long tfdt = trackFragment.nextFragmentDecodeTime;
        tfhd.reset();
        int i10 = Atom.TYPE_tfdt;
        if (containerAtom.getLeafAtomOfType(i10) != null && (i9 & 2) == 0) {
            tfdt = parseTfdt(containerAtom.getLeafAtomOfType(i10).data);
        }
        parseTruns(containerAtom, tfhd, tfdt, i9);
        TrackEncryptionBox sampleDescriptionEncryptionBox = tfhd.track.getSampleDescriptionEncryptionBox(trackFragment.header.sampleDescriptionIndex);
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_saiz);
        if (leafAtomOfType != null) {
            parseSaiz(sampleDescriptionEncryptionBox, leafAtomOfType.data, trackFragment);
        }
        Atom.LeafAtom leafAtomOfType2 = containerAtom.getLeafAtomOfType(Atom.TYPE_saio);
        if (leafAtomOfType2 != null) {
            parseSaio(leafAtomOfType2.data, trackFragment);
        }
        Atom.LeafAtom leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_senc);
        if (leafAtomOfType3 != null) {
            parseSenc(leafAtomOfType3.data, trackFragment);
        }
        Atom.LeafAtom leafAtomOfType4 = containerAtom.getLeafAtomOfType(Atom.TYPE_sbgp);
        Atom.LeafAtom leafAtomOfType5 = containerAtom.getLeafAtomOfType(Atom.TYPE_sgpd);
        if (leafAtomOfType4 != null && leafAtomOfType5 != null) {
            parseSgpd(leafAtomOfType4.data, leafAtomOfType5.data, sampleDescriptionEncryptionBox != null ? sampleDescriptionEncryptionBox.schemeType : null, trackFragment);
        }
        int size = containerAtom.leafChildren.size();
        for (int i11 = 0; i11 < size; i11++) {
            Atom.LeafAtom leafAtom = containerAtom.leafChildren.get(i11);
            if (leafAtom.type == Atom.TYPE_uuid) {
                parseUuid(leafAtom.data, trackFragment, bArr);
            }
        }
    }

    private static Pair<Integer, DefaultSampleValues> parseTrex(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(12);
        return Pair.create(Integer.valueOf(parsableByteArray.readInt()), new DefaultSampleValues(parsableByteArray.readUnsignedIntToInt() - 1, parsableByteArray.readUnsignedIntToInt(), parsableByteArray.readUnsignedIntToInt(), parsableByteArray.readInt()));
    }

    private static int parseTrun(TrackBundle trackBundle, int i9, long j9, int i10, ParsableByteArray parsableByteArray, int i11) {
        boolean z8;
        int unsignedIntToInt;
        boolean z9;
        int i12;
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        parsableByteArray.setPosition(8);
        int fullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
        Track track = trackBundle.track;
        TrackFragment trackFragment = trackBundle.fragment;
        DefaultSampleValues defaultSampleValues = trackFragment.header;
        trackFragment.trunLength[i9] = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = trackFragment.trunDataPosition;
        long j10 = trackFragment.dataPosition;
        jArr[i9] = j10;
        if ((fullAtomFlags & 1) != 0) {
            jArr[i9] = j10 + parsableByteArray.readInt();
        }
        boolean z14 = (fullAtomFlags & 4) != 0;
        int unsignedIntToInt2 = defaultSampleValues.flags;
        if (z14) {
            unsignedIntToInt2 = parsableByteArray.readUnsignedIntToInt();
        }
        boolean z15 = (fullAtomFlags & 256) != 0;
        boolean z16 = (fullAtomFlags & 512) != 0;
        boolean z17 = (fullAtomFlags & UserMetadata.MAX_ATTRIBUTE_SIZE) != 0;
        boolean z18 = (fullAtomFlags & 2048) != 0;
        long[] jArr2 = track.editListDurations;
        long jScaleLargeTimestamp = 0;
        if (jArr2 != null && jArr2.length == 1 && jArr2[0] == 0) {
            jScaleLargeTimestamp = Util.scaleLargeTimestamp(track.editListMediaTimes[0], 1000L, track.timescale);
        }
        int[] iArr = trackFragment.sampleSizeTable;
        int[] iArr2 = trackFragment.sampleCompositionTimeOffsetTable;
        long[] jArr3 = trackFragment.sampleDecodingTimeTable;
        boolean[] zArr = trackFragment.sampleIsSyncFrameTable;
        int i13 = unsignedIntToInt2;
        boolean z19 = track.type == 2 && (i10 & 1) != 0;
        int i14 = i11 + trackFragment.trunLength[i9];
        long j11 = track.timescale;
        long j12 = jScaleLargeTimestamp;
        long j13 = i9 > 0 ? trackFragment.nextFragmentDecodeTime : j9;
        int i15 = i11;
        while (i15 < i14) {
            int unsignedIntToInt3 = z15 ? parsableByteArray.readUnsignedIntToInt() : defaultSampleValues.duration;
            if (z16) {
                z8 = z15;
                unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            } else {
                z8 = z15;
                unsignedIntToInt = defaultSampleValues.size;
            }
            if (i15 == 0 && z14) {
                z9 = z14;
                i12 = i13;
            } else if (z17) {
                z9 = z14;
                i12 = parsableByteArray.readInt();
            } else {
                z9 = z14;
                i12 = defaultSampleValues.flags;
            }
            if (z18) {
                z10 = z18;
                z11 = z16;
                z12 = z17;
                iArr2[i15] = (int) ((parsableByteArray.readInt() * 1000) / j11);
                z13 = false;
            } else {
                z10 = z18;
                z11 = z16;
                z12 = z17;
                z13 = false;
                iArr2[i15] = 0;
            }
            jArr3[i15] = Util.scaleLargeTimestamp(j13, 1000L, j11) - j12;
            iArr[i15] = unsignedIntToInt;
            zArr[i15] = (((i12 >> 16) & 1) != 0 || (z19 && i15 != 0)) ? z13 : true;
            i15++;
            j13 += unsignedIntToInt3;
            j11 = j11;
            z15 = z8;
            z14 = z9;
            z18 = z10;
            z16 = z11;
            z17 = z12;
        }
        trackFragment.nextFragmentDecodeTime = j13;
        return i14;
    }

    private static void parseTruns(Atom.ContainerAtom containerAtom, TrackBundle trackBundle, long j9, int i9) {
        List<Atom.LeafAtom> list = containerAtom.leafChildren;
        int size = list.size();
        int i10 = 0;
        int i11 = 0;
        for (int i12 = 0; i12 < size; i12++) {
            Atom.LeafAtom leafAtom = list.get(i12);
            if (leafAtom.type == Atom.TYPE_trun) {
                ParsableByteArray parsableByteArray = leafAtom.data;
                parsableByteArray.setPosition(12);
                int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
                if (unsignedIntToInt > 0) {
                    i11 += unsignedIntToInt;
                    i10++;
                }
            }
        }
        trackBundle.currentTrackRunIndex = 0;
        trackBundle.currentSampleInTrackRun = 0;
        trackBundle.currentSampleIndex = 0;
        trackBundle.fragment.initTables(i10, i11);
        int i13 = 0;
        int trun = 0;
        for (int i14 = 0; i14 < size; i14++) {
            Atom.LeafAtom leafAtom2 = list.get(i14);
            if (leafAtom2.type == Atom.TYPE_trun) {
                trun = parseTrun(trackBundle, i13, j9, i9, leafAtom2.data, trun);
                i13++;
            }
        }
    }

    private static void parseUuid(ParsableByteArray parsableByteArray, TrackFragment trackFragment, byte[] bArr) throws ParserException {
        parsableByteArray.setPosition(8);
        parsableByteArray.readBytes(bArr, 0, 16);
        if (Arrays.equals(bArr, PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE)) {
            parseSenc(parsableByteArray, 16, trackFragment);
        }
    }

    private void processAtomEnded(long j9) throws ParserException {
        while (!this.containerAtoms.isEmpty() && this.containerAtoms.peek().endPosition == j9) {
            onContainerAtomRead(this.containerAtoms.pop());
        }
        enterReadingAtomHeaderState();
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
        long position = extractorInput.getPosition() - this.atomHeaderBytesRead;
        if (this.atomType == Atom.TYPE_moof) {
            int size = this.trackBundles.size();
            for (int i9 = 0; i9 < size; i9++) {
                TrackFragment trackFragment = this.trackBundles.valueAt(i9).fragment;
                trackFragment.atomPosition = position;
                trackFragment.auxiliaryDataPosition = position;
                trackFragment.dataPosition = position;
            }
        }
        int i10 = this.atomType;
        if (i10 == Atom.TYPE_mdat) {
            this.currentTrackBundle = null;
            this.endOfMdatPosition = this.atomSize + position;
            if (!this.haveOutputSeekMap) {
                this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs, position));
                this.haveOutputSeekMap = true;
            }
            this.parserState = 2;
            return true;
        }
        if (shouldParseContainerAtom(i10)) {
            long position2 = (extractorInput.getPosition() + this.atomSize) - 8;
            this.containerAtoms.add(new Atom.ContainerAtom(this.atomType, position2));
            if (this.atomSize == this.atomHeaderBytesRead) {
                processAtomEnded(position2);
            } else {
                enterReadingAtomHeaderState();
            }
        } else if (shouldParseLeafAtom(this.atomType)) {
            if (this.atomHeaderBytesRead != 8) {
                throw new ParserException("Leaf atom defines extended atom size (unsupported).");
            }
            long j10 = this.atomSize;
            if (j10 > 2147483647L) {
                throw new ParserException("Leaf atom with length > 2147483647 (unsupported).");
            }
            ParsableByteArray parsableByteArray = new ParsableByteArray((int) j10);
            this.atomData = parsableByteArray;
            System.arraycopy(this.atomHeader.data, 0, parsableByteArray.data, 0, 8);
            this.parserState = 1;
        } else {
            if (this.atomSize > 2147483647L) {
                throw new ParserException("Skipping atom with length > 2147483647 (unsupported).");
            }
            this.atomData = null;
            this.parserState = 1;
        }
        return true;
    }

    private void readAtomPayload(ExtractorInput extractorInput) throws ParserException {
        int i9 = ((int) this.atomSize) - this.atomHeaderBytesRead;
        ParsableByteArray parsableByteArray = this.atomData;
        if (parsableByteArray != null) {
            extractorInput.readFully(parsableByteArray.data, 8, i9);
            onLeafAtomRead(new Atom.LeafAtom(this.atomType, this.atomData), extractorInput.getPosition());
        } else {
            extractorInput.skipFully(i9);
        }
        processAtomEnded(extractorInput.getPosition());
    }

    private void readEncryptionData(ExtractorInput extractorInput) throws ParserException {
        int size = this.trackBundles.size();
        TrackBundle trackBundleValueAt = null;
        long j9 = Long.MAX_VALUE;
        for (int i9 = 0; i9 < size; i9++) {
            TrackFragment trackFragment = this.trackBundles.valueAt(i9).fragment;
            if (trackFragment.sampleEncryptionDataNeedsFill) {
                long j10 = trackFragment.auxiliaryDataPosition;
                if (j10 < j9) {
                    trackBundleValueAt = this.trackBundles.valueAt(i9);
                    j9 = j10;
                }
            }
        }
        if (trackBundleValueAt == null) {
            this.parserState = 3;
            return;
        }
        int position = (int) (j9 - extractorInput.getPosition());
        if (position < 0) {
            throw new ParserException("Offset to encryption data was negative.");
        }
        extractorInput.skipFully(position);
        trackBundleValueAt.fragment.fillEncryptionData(extractorInput);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean readSample(ExtractorInput extractorInput) throws ParserException {
        int i9;
        TrackOutput.CryptoData cryptoData;
        int iSampleData;
        int i10 = 4;
        int i11 = 1;
        int i12 = 0;
        if (this.parserState == 3) {
            if (this.currentTrackBundle == null) {
                TrackBundle nextFragmentRun = getNextFragmentRun(this.trackBundles);
                if (nextFragmentRun == null) {
                    int position = (int) (this.endOfMdatPosition - extractorInput.getPosition());
                    if (position < 0) {
                        throw new ParserException("Offset to end of mdat was negative.");
                    }
                    extractorInput.skipFully(position);
                    enterReadingAtomHeaderState();
                    return false;
                }
                int position2 = (int) (nextFragmentRun.fragment.trunDataPosition[nextFragmentRun.currentTrackRunIndex] - extractorInput.getPosition());
                if (position2 < 0) {
                    Log.w(TAG, "Ignoring negative offset to sample data.");
                    position2 = 0;
                }
                extractorInput.skipFully(position2);
                this.currentTrackBundle = nextFragmentRun;
            }
            TrackBundle trackBundle = this.currentTrackBundle;
            TrackFragment trackFragment = trackBundle.fragment;
            this.sampleSize = trackFragment.sampleSizeTable[trackBundle.currentSampleIndex];
            if (trackFragment.definesEncryptionData) {
                int iAppendSampleEncryptionData = appendSampleEncryptionData(trackBundle);
                this.sampleBytesWritten = iAppendSampleEncryptionData;
                this.sampleSize += iAppendSampleEncryptionData;
            } else {
                this.sampleBytesWritten = 0;
            }
            if (this.currentTrackBundle.track.sampleTransformation == 1) {
                this.sampleSize -= 8;
                extractorInput.skipFully(8);
            }
            this.parserState = 4;
            this.sampleCurrentNalBytesRemaining = 0;
        }
        TrackBundle trackBundle2 = this.currentTrackBundle;
        TrackFragment trackFragment2 = trackBundle2.fragment;
        Track track = trackBundle2.track;
        TrackOutput trackOutput = trackBundle2.output;
        int i13 = trackBundle2.currentSampleIndex;
        int i14 = track.nalUnitLengthFieldLength;
        if (i14 == 0) {
            while (true) {
                int i15 = this.sampleBytesWritten;
                int i16 = this.sampleSize;
                if (i15 >= i16) {
                    break;
                }
                this.sampleBytesWritten += trackOutput.sampleData(extractorInput, i16 - i15, false);
            }
        } else {
            byte[] bArr = this.nalPrefix.data;
            bArr[0] = 0;
            bArr[1] = 0;
            bArr[2] = 0;
            int i17 = i14 + 1;
            int i18 = 4 - i14;
            while (this.sampleBytesWritten < this.sampleSize) {
                int i19 = this.sampleCurrentNalBytesRemaining;
                if (i19 == 0) {
                    extractorInput.readFully(bArr, i18, i17);
                    this.nalPrefix.setPosition(i12);
                    this.sampleCurrentNalBytesRemaining = this.nalPrefix.readUnsignedIntToInt() - i11;
                    this.nalStartCode.setPosition(i12);
                    trackOutput.sampleData(this.nalStartCode, i10);
                    trackOutput.sampleData(this.nalPrefix, i11);
                    this.processSeiNalUnitPayload = (this.cea608TrackOutputs.length <= 0 || !NalUnitUtil.isNalUnitSei(track.format.sampleMimeType, bArr[i10])) ? i12 : i11;
                    this.sampleBytesWritten += 5;
                    this.sampleSize += i18;
                } else {
                    if (this.processSeiNalUnitPayload) {
                        this.nalBuffer.reset(i19);
                        extractorInput.readFully(this.nalBuffer.data, i12, this.sampleCurrentNalBytesRemaining);
                        trackOutput.sampleData(this.nalBuffer, this.sampleCurrentNalBytesRemaining);
                        iSampleData = this.sampleCurrentNalBytesRemaining;
                        ParsableByteArray parsableByteArray = this.nalBuffer;
                        int iUnescapeStream = NalUnitUtil.unescapeStream(parsableByteArray.data, parsableByteArray.limit());
                        this.nalBuffer.setPosition(MimeTypes.VIDEO_H265.equals(track.format.sampleMimeType) ? 1 : 0);
                        this.nalBuffer.setLimit(iUnescapeStream);
                        CeaUtil.consume(trackFragment2.getSamplePresentationTime(i13) * 1000, this.nalBuffer, this.cea608TrackOutputs);
                    } else {
                        iSampleData = trackOutput.sampleData(extractorInput, i19, i12);
                    }
                    this.sampleBytesWritten += iSampleData;
                    this.sampleCurrentNalBytesRemaining -= iSampleData;
                    i10 = 4;
                    i11 = 1;
                    i12 = 0;
                }
            }
        }
        long samplePresentationTime = trackFragment2.getSamplePresentationTime(i13) * 1000;
        TimestampAdjuster timestampAdjuster = this.timestampAdjuster;
        if (timestampAdjuster != null) {
            samplePresentationTime = timestampAdjuster.adjustSampleTimestamp(samplePresentationTime);
        }
        boolean z8 = trackFragment2.sampleIsSyncFrameTable[i13];
        if (trackFragment2.definesEncryptionData) {
            int i20 = (z8 ? 1 : 0) | 1073741824;
            TrackEncryptionBox sampleDescriptionEncryptionBox = trackFragment2.trackEncryptionBox;
            if (sampleDescriptionEncryptionBox == null) {
                sampleDescriptionEncryptionBox = track.getSampleDescriptionEncryptionBox(trackFragment2.header.sampleDescriptionIndex);
            }
            i9 = i20;
            cryptoData = sampleDescriptionEncryptionBox.cryptoData;
        } else {
            i9 = z8 ? 1 : 0;
            cryptoData = null;
        }
        trackOutput.sampleMetadata(samplePresentationTime, i9, this.sampleSize, 0, cryptoData);
        outputPendingMetadataSamples(samplePresentationTime);
        TrackBundle trackBundle3 = this.currentTrackBundle;
        trackBundle3.currentSampleIndex++;
        int i21 = trackBundle3.currentSampleInTrackRun + 1;
        trackBundle3.currentSampleInTrackRun = i21;
        int[] iArr = trackFragment2.trunLength;
        int i22 = trackBundle3.currentTrackRunIndex;
        if (i21 == iArr[i22]) {
            trackBundle3.currentTrackRunIndex = i22 + 1;
            trackBundle3.currentSampleInTrackRun = 0;
            this.currentTrackBundle = null;
        }
        this.parserState = 3;
        return true;
    }

    private static boolean shouldParseContainerAtom(int i9) {
        return i9 == Atom.TYPE_moov || i9 == Atom.TYPE_trak || i9 == Atom.TYPE_mdia || i9 == Atom.TYPE_minf || i9 == Atom.TYPE_stbl || i9 == Atom.TYPE_moof || i9 == Atom.TYPE_traf || i9 == Atom.TYPE_mvex || i9 == Atom.TYPE_edts;
    }

    private static boolean shouldParseLeafAtom(int i9) {
        return i9 == Atom.TYPE_hdlr || i9 == Atom.TYPE_mdhd || i9 == Atom.TYPE_mvhd || i9 == Atom.TYPE_sidx || i9 == Atom.TYPE_stsd || i9 == Atom.TYPE_tfdt || i9 == Atom.TYPE_tfhd || i9 == Atom.TYPE_tkhd || i9 == Atom.TYPE_trex || i9 == Atom.TYPE_trun || i9 == Atom.TYPE_pssh || i9 == Atom.TYPE_saiz || i9 == Atom.TYPE_saio || i9 == Atom.TYPE_senc || i9 == Atom.TYPE_uuid || i9 == Atom.TYPE_sbgp || i9 == Atom.TYPE_sgpd || i9 == Atom.TYPE_elst || i9 == Atom.TYPE_mehd || i9 == Atom.TYPE_emsg;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        Track track = this.sideloadedTrack;
        if (track != null) {
            TrackBundle trackBundle = new TrackBundle(extractorOutput.track(0, track.type));
            trackBundle.init(this.sideloadedTrack, new DefaultSampleValues(0, 0, 0, 0));
            this.trackBundles.put(0, trackBundle);
            maybeInitExtraTracks();
            this.extractorOutput.endTracks();
        }
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws ParserException {
        while (true) {
            int i9 = this.parserState;
            if (i9 != 0) {
                if (i9 == 1) {
                    readAtomPayload(extractorInput);
                } else if (i9 == 2) {
                    readEncryptionData(extractorInput);
                } else if (readSample(extractorInput)) {
                    return 0;
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
        int size = this.trackBundles.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.trackBundles.valueAt(i9).reset();
        }
        this.pendingMetadataSampleInfos.clear();
        this.pendingMetadataSampleBytes = 0;
        this.containerAtoms.clear();
        enterReadingAtomHeaderState();
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) {
        return Sniffer.sniffFragmented(extractorInput);
    }

    public FragmentedMp4Extractor(int i9) {
        this(i9, null);
    }

    private static void parseSenc(ParsableByteArray parsableByteArray, int i9, TrackFragment trackFragment) throws ParserException {
        parsableByteArray.setPosition(i9 + 8);
        int fullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
        if ((fullAtomFlags & 1) != 0) {
            throw new ParserException("Overriding TrackEncryptionBox parameters is unsupported.");
        }
        boolean z8 = (fullAtomFlags & 2) != 0;
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (unsignedIntToInt == trackFragment.sampleCount) {
            Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, unsignedIntToInt, z8);
            trackFragment.initEncryptionData(parsableByteArray.bytesLeft());
            trackFragment.fillEncryptionData(parsableByteArray);
        } else {
            throw new ParserException("Length mismatch: " + unsignedIntToInt + ", " + trackFragment.sampleCount);
        }
    }

    public FragmentedMp4Extractor(int i9, TimestampAdjuster timestampAdjuster) {
        this(i9, timestampAdjuster, null, null);
    }

    public FragmentedMp4Extractor(int i9, TimestampAdjuster timestampAdjuster, Track track, DrmInitData drmInitData) {
        this(i9, timestampAdjuster, track, drmInitData, Collections.emptyList());
    }

    public FragmentedMp4Extractor(int i9, TimestampAdjuster timestampAdjuster, Track track, DrmInitData drmInitData, List<Format> list) {
        this(i9, timestampAdjuster, track, drmInitData, list, null);
    }

    public FragmentedMp4Extractor(int i9, TimestampAdjuster timestampAdjuster, Track track, DrmInitData drmInitData, List<Format> list, TrackOutput trackOutput) {
        this.flags = i9 | (track != null ? 8 : 0);
        this.timestampAdjuster = timestampAdjuster;
        this.sideloadedTrack = track;
        this.sideloadedDrmInitData = drmInitData;
        this.closedCaptionFormats = Collections.unmodifiableList(list);
        this.additionalEmsgTrackOutput = trackOutput;
        this.atomHeader = new ParsableByteArray(16);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalPrefix = new ParsableByteArray(5);
        this.nalBuffer = new ParsableByteArray();
        this.encryptionSignalByte = new ParsableByteArray(1);
        this.defaultInitializationVector = new ParsableByteArray();
        this.extendedTypeScratch = new byte[16];
        this.containerAtoms = new Stack<>();
        this.pendingMetadataSampleInfos = new ArrayDeque<>();
        this.trackBundles = new SparseArray<>();
        this.durationUs = C3322C.TIME_UNSET;
        this.segmentIndexEarliestPresentationTimeUs = C3322C.TIME_UNSET;
        enterReadingAtomHeaderState();
    }
}
