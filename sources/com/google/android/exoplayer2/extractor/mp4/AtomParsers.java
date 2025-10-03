package com.google.android.exoplayer2.extractor.mp4;

import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.extractor.mp4.Atom;
import com.google.android.exoplayer2.extractor.mp4.FixedSampleSizeRechunker;
import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.AvcConfig;
import com.google.android.exoplayer2.video.HevcConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
final class AtomParsers {
    private static final String TAG = "AtomParsers";
    private static final int TYPE_vide = Util.getIntegerCodeForString("vide");
    private static final int TYPE_soun = Util.getIntegerCodeForString("soun");
    private static final int TYPE_text = Util.getIntegerCodeForString(MimeTypes.BASE_TYPE_TEXT);
    private static final int TYPE_sbtl = Util.getIntegerCodeForString("sbtl");
    private static final int TYPE_subt = Util.getIntegerCodeForString("subt");
    private static final int TYPE_clcp = Util.getIntegerCodeForString("clcp");
    private static final int TYPE_meta = Util.getIntegerCodeForString("meta");

    public static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z8) {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z8;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            Assertions.checkState(parsableByteArray.readInt() == 1, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            int i9 = this.index + 1;
            this.index = i9;
            if (i9 == this.length) {
                return false;
            }
            this.offset = this.chunkOffsetsAreLongs ? this.chunkOffsets.readUnsignedLongToLong() : this.chunkOffsets.readUnsignedInt();
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i10 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i10;
                this.nextSamplesPerChunkChangeIndex = i10 > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
            }
            return true;
        }
    }

    public interface SampleSizeBox {
        int getSampleCount();

        boolean isFixedSampleSize();

        int readNextSampleSize();
    }

    public static final class StsdData {
        public static final int STSD_HEADER_SIZE = 8;
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation = 0;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i9) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i9];
        }
    }

    public static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize;
        private final int sampleCount;

        public StszSampleSizeBox(Atom.LeafAtom leafAtom) {
            ParsableByteArray parsableByteArray = leafAtom.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            this.fixedSampleSize = parsableByteArray.readUnsignedIntToInt();
            this.sampleCount = parsableByteArray.readUnsignedIntToInt();
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public boolean isFixedSampleSize() {
            return this.fixedSampleSize != 0;
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int readNextSampleSize() {
            int i9 = this.fixedSampleSize;
            return i9 == 0 ? this.data.readUnsignedIntToInt() : i9;
        }
    }

    public static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize;
        private final int sampleCount;
        private int sampleIndex;

        public Stz2SampleSizeBox(Atom.LeafAtom leafAtom) {
            ParsableByteArray parsableByteArray = leafAtom.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            this.fieldSize = parsableByteArray.readUnsignedIntToInt() & 255;
            this.sampleCount = parsableByteArray.readUnsignedIntToInt();
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public boolean isFixedSampleSize() {
            return false;
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int readNextSampleSize() {
            int i9 = this.fieldSize;
            if (i9 == 8) {
                return this.data.readUnsignedByte();
            }
            if (i9 == 16) {
                return this.data.readUnsignedShort();
            }
            int i10 = this.sampleIndex;
            this.sampleIndex = i10 + 1;
            if (i10 % 2 != 0) {
                return this.currentByte & 15;
            }
            int unsignedByte = this.data.readUnsignedByte();
            this.currentByte = unsignedByte;
            return (unsignedByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
        }
    }

    public static final class TkhdData {
        private final long duration;

        /* renamed from: id */
        private final int f15303id;
        private final int rotationDegrees;

        public TkhdData(int i9, long j9, int i10) {
            this.f15303id = i9;
            this.duration = j9;
            this.rotationDegrees = i10;
        }
    }

    private AtomParsers() {
    }

    private static int findEsdsPosition(ParsableByteArray parsableByteArray, int i9, int i10) {
        int position = parsableByteArray.getPosition();
        while (position - i9 < i10) {
            parsableByteArray.setPosition(position);
            int i11 = parsableByteArray.readInt();
            Assertions.checkArgument(i11 > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_esds) {
                return position;
            }
            position += i11;
        }
        return -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void parseAudioSampleEntry(ParsableByteArray parsableByteArray, int i9, int i10, int i11, int i12, String str, boolean z8, DrmInitData drmInitData, StsdData stsdData, int i13) {
        int unsignedShort;
        int unsignedFixedPoint1616;
        int unsignedIntToInt;
        String str2;
        String str3;
        DrmInitData drmInitData2;
        boolean z9;
        int i14;
        int i15;
        int i16;
        int i17 = i10;
        DrmInitData drmInitDataCopyWithSchemeType = drmInitData;
        parsableByteArray.setPosition(i17 + 8 + 8);
        int i18 = 0;
        if (z8) {
            unsignedShort = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
        } else {
            parsableByteArray.skipBytes(8);
            unsignedShort = 0;
        }
        int i19 = 2;
        boolean z10 = true;
        if (unsignedShort == 0 || unsignedShort == 1) {
            int unsignedShort2 = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
            unsignedFixedPoint1616 = parsableByteArray.readUnsignedFixedPoint1616();
            if (unsignedShort == 1) {
                parsableByteArray.skipBytes(16);
            }
            unsignedIntToInt = unsignedShort2;
        } else {
            if (unsignedShort != 2) {
                return;
            }
            parsableByteArray.skipBytes(16);
            unsignedFixedPoint1616 = (int) Math.round(parsableByteArray.readDouble());
            unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            parsableByteArray.skipBytes(20);
        }
        int position = parsableByteArray.getPosition();
        int iIntValue = i9;
        if (iIntValue == Atom.TYPE_enca) {
            Pair<Integer, TrackEncryptionBox> sampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, i17, i11);
            if (sampleEntryEncryptionData != null) {
                iIntValue = ((Integer) sampleEntryEncryptionData.first).intValue();
                drmInitDataCopyWithSchemeType = drmInitDataCopyWithSchemeType == null ? null : drmInitDataCopyWithSchemeType.copyWithSchemeType(((TrackEncryptionBox) sampleEntryEncryptionData.second).schemeType);
                stsdData.trackEncryptionBoxes[i13] = (TrackEncryptionBox) sampleEntryEncryptionData.second;
            }
            parsableByteArray.setPosition(position);
        }
        DrmInitData drmInitData3 = drmInitDataCopyWithSchemeType;
        int i20 = Atom.TYPE_ac_3;
        String str4 = MimeTypes.AUDIO_RAW;
        String str5 = iIntValue == i20 ? MimeTypes.AUDIO_AC3 : iIntValue == Atom.TYPE_ec_3 ? MimeTypes.AUDIO_E_AC3 : iIntValue == Atom.TYPE_dtsc ? MimeTypes.AUDIO_DTS : (iIntValue == Atom.TYPE_dtsh || iIntValue == Atom.TYPE_dtsl) ? MimeTypes.AUDIO_DTS_HD : iIntValue == Atom.TYPE_dtse ? MimeTypes.AUDIO_DTS_EXPRESS : iIntValue == Atom.TYPE_samr ? MimeTypes.AUDIO_AMR_NB : iIntValue == Atom.TYPE_sawb ? MimeTypes.AUDIO_AMR_WB : (iIntValue == Atom.TYPE_lpcm || iIntValue == Atom.TYPE_sowt) ? MimeTypes.AUDIO_RAW : iIntValue == Atom.TYPE__mp3 ? MimeTypes.AUDIO_MPEG : iIntValue == Atom.TYPE_alac ? MimeTypes.AUDIO_ALAC : null;
        int iIntValue2 = unsignedFixedPoint1616;
        int iIntValue3 = unsignedIntToInt;
        int i21 = position;
        byte[] bArr = null;
        while (i21 - i17 < i11) {
            parsableByteArray.setPosition(i21);
            int i22 = parsableByteArray.readInt();
            Assertions.checkArgument(i22 > 0 ? z10 : i18, "childAtomSize should be positive");
            int i23 = parsableByteArray.readInt();
            int i24 = Atom.TYPE_esds;
            if (i23 == i24) {
                str2 = str5;
                str3 = str4;
                drmInitData2 = drmInitData3;
                z9 = z10;
                i14 = i19;
                i15 = i18;
            } else if (z8 && i23 == Atom.TYPE_wave) {
                str2 = str5;
                str3 = str4;
                drmInitData2 = drmInitData3;
                i14 = i19;
                i15 = i18;
                z9 = true;
            } else {
                if (i23 == Atom.TYPE_dac3) {
                    parsableByteArray.setPosition(i21 + 8);
                    stsdData.format = Ac3Util.parseAc3AnnexFFormat(parsableByteArray, Integer.toString(i12), str, drmInitData3);
                } else if (i23 == Atom.TYPE_dec3) {
                    parsableByteArray.setPosition(i21 + 8);
                    stsdData.format = Ac3Util.parseEAc3AnnexFFormat(parsableByteArray, Integer.toString(i12), str, drmInitData3);
                } else {
                    if (i23 == Atom.TYPE_ddts) {
                        str2 = str5;
                        str3 = str4;
                        drmInitData2 = drmInitData3;
                        i16 = i21;
                        z9 = true;
                        i14 = i19;
                        i15 = i18;
                        stsdData.format = Format.createAudioSampleFormat(Integer.toString(i12), str5, null, -1, -1, iIntValue3, iIntValue2, null, drmInitData2, 0, str);
                        i22 = i22;
                    } else {
                        i16 = i21;
                        str2 = str5;
                        str3 = str4;
                        drmInitData2 = drmInitData3;
                        i14 = i19;
                        i15 = i18;
                        z9 = true;
                        if (i23 == Atom.TYPE_alac) {
                            byte[] bArr2 = new byte[i22];
                            i21 = i16;
                            parsableByteArray.setPosition(i21);
                            parsableByteArray.readBytes(bArr2, i15, i22);
                            bArr = bArr2;
                        }
                        str5 = str2;
                        i21 += i22;
                        i18 = i15;
                        z10 = z9;
                        drmInitData3 = drmInitData2;
                        i19 = i14;
                        str4 = str3;
                        i17 = i10;
                    }
                    i21 = i16;
                    str5 = str2;
                    i21 += i22;
                    i18 = i15;
                    z10 = z9;
                    drmInitData3 = drmInitData2;
                    i19 = i14;
                    str4 = str3;
                    i17 = i10;
                }
                str2 = str5;
                str3 = str4;
                drmInitData2 = drmInitData3;
                i14 = i19;
                i15 = i18;
                z9 = true;
                str5 = str2;
                i21 += i22;
                i18 = i15;
                z10 = z9;
                drmInitData3 = drmInitData2;
                i19 = i14;
                str4 = str3;
                i17 = i10;
            }
            int iFindEsdsPosition = i23 == i24 ? i21 : findEsdsPosition(parsableByteArray, i21, i22);
            if (iFindEsdsPosition != -1) {
                Pair<String, byte[]> esdsFromParent = parseEsdsFromParent(parsableByteArray, iFindEsdsPosition);
                str5 = (String) esdsFromParent.first;
                bArr = (byte[]) esdsFromParent.second;
                if (MimeTypes.AUDIO_AAC.equals(str5)) {
                    Pair<Integer, Integer> aacAudioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(bArr);
                    iIntValue2 = ((Integer) aacAudioSpecificConfig.first).intValue();
                    iIntValue3 = ((Integer) aacAudioSpecificConfig.second).intValue();
                }
            } else {
                str5 = str2;
            }
            i21 += i22;
            i18 = i15;
            z10 = z9;
            drmInitData3 = drmInitData2;
            i19 = i14;
            str4 = str3;
            i17 = i10;
        }
        String str6 = str5;
        String str7 = str4;
        DrmInitData drmInitData4 = drmInitData3;
        int i25 = i19;
        if (stsdData.format != null || str6 == null) {
            return;
        }
        stsdData.format = Format.createAudioSampleFormat(Integer.toString(i12), str6, null, -1, -1, iIntValue3, iIntValue2, str7.equals(str6) ? i25 : -1, bArr != null ? Collections.singletonList(bArr) : null, drmInitData4, 0, str);
    }

    public static Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent(ParsableByteArray parsableByteArray, int i9, int i10) {
        int i11 = i9 + 8;
        int i12 = -1;
        int i13 = 0;
        String string = null;
        Integer numValueOf = null;
        while (i11 - i9 < i10) {
            parsableByteArray.setPosition(i11);
            int i14 = parsableByteArray.readInt();
            int i15 = parsableByteArray.readInt();
            if (i15 == Atom.TYPE_frma) {
                numValueOf = Integer.valueOf(parsableByteArray.readInt());
            } else if (i15 == Atom.TYPE_schm) {
                parsableByteArray.skipBytes(4);
                string = parsableByteArray.readString(4);
            } else if (i15 == Atom.TYPE_schi) {
                i12 = i11;
                i13 = i14;
            }
            i11 += i14;
        }
        if (!C3322C.CENC_TYPE_cenc.equals(string) && !C3322C.CENC_TYPE_cbc1.equals(string) && !C3322C.CENC_TYPE_cens.equals(string) && !C3322C.CENC_TYPE_cbcs.equals(string)) {
            return null;
        }
        Assertions.checkArgument(numValueOf != null, "frma atom is mandatory");
        Assertions.checkArgument(i12 != -1, "schi atom is mandatory");
        TrackEncryptionBox schiFromParent = parseSchiFromParent(parsableByteArray, i12, i13, string);
        Assertions.checkArgument(schiFromParent != null, "tenc atom is mandatory");
        return Pair.create(numValueOf, schiFromParent);
    }

    private static Pair<long[], long[]> parseEdts(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom leafAtomOfType;
        if (containerAtom == null || (leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_elst)) == null) {
            return Pair.create(null, null);
        }
        ParsableByteArray parsableByteArray = leafAtomOfType.data;
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[unsignedIntToInt];
        long[] jArr2 = new long[unsignedIntToInt];
        for (int i9 = 0; i9 < unsignedIntToInt; i9++) {
            jArr[i9] = fullAtomVersion == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
            jArr2[i9] = fullAtomVersion == 1 ? parsableByteArray.readLong() : parsableByteArray.readInt();
            if (parsableByteArray.readShort() != 1) {
                throw new IllegalArgumentException("Unsupported media rate.");
            }
            parsableByteArray.skipBytes(2);
        }
        return Pair.create(jArr, jArr2);
    }

    private static Pair<String, byte[]> parseEsdsFromParent(ParsableByteArray parsableByteArray, int i9) {
        parsableByteArray.setPosition(i9 + 8 + 4);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        int unsignedByte = parsableByteArray.readUnsignedByte();
        if ((unsignedByte & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((unsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort());
        }
        if ((unsignedByte & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        String mimeTypeFromMp4ObjectType = MimeTypes.getMimeTypeFromMp4ObjectType(parsableByteArray.readUnsignedByte());
        if (MimeTypes.AUDIO_MPEG.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS_HD.equals(mimeTypeFromMp4ObjectType)) {
            return Pair.create(mimeTypeFromMp4ObjectType, null);
        }
        parsableByteArray.skipBytes(12);
        parsableByteArray.skipBytes(1);
        int expandableClassSize = parseExpandableClassSize(parsableByteArray);
        byte[] bArr = new byte[expandableClassSize];
        parsableByteArray.readBytes(bArr, 0, expandableClassSize);
        return Pair.create(mimeTypeFromMp4ObjectType, bArr);
    }

    private static int parseExpandableClassSize(ParsableByteArray parsableByteArray) {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i9 = unsignedByte & 127;
        while ((unsignedByte & 128) == 128) {
            unsignedByte = parsableByteArray.readUnsignedByte();
            i9 = (i9 << 7) | (unsignedByte & 127);
        }
        return i9;
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        int i9 = parsableByteArray.readInt();
        if (i9 == TYPE_soun) {
            return 1;
        }
        if (i9 == TYPE_vide) {
            return 2;
        }
        if (i9 == TYPE_text || i9 == TYPE_sbtl || i9 == TYPE_subt || i9 == TYPE_clcp) {
            return 3;
        }
        return i9 == TYPE_meta ? 4 : -1;
    }

    private static Metadata parseIlst(ParsableByteArray parsableByteArray, int i9) {
        parsableByteArray.skipBytes(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray.getPosition() < i9) {
            Metadata.Entry ilstElement = MetadataUtil.parseIlstElement(parsableByteArray);
            if (ilstElement != null) {
                arrayList.add(ilstElement);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata(arrayList);
    }

    private static Pair<Long, String> parseMdhd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(fullAtomVersion == 0 ? 8 : 16);
        long unsignedInt = parsableByteArray.readUnsignedInt();
        parsableByteArray.skipBytes(fullAtomVersion == 0 ? 4 : 8);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        return Pair.create(Long.valueOf(unsignedInt), "" + ((char) (((unsignedShort >> 10) & 31) + 96)) + ((char) (((unsignedShort >> 5) & 31) + 96)) + ((char) ((unsignedShort & 31) + 96)));
    }

    private static Metadata parseMetaAtom(ParsableByteArray parsableByteArray, int i9) {
        parsableByteArray.skipBytes(12);
        while (parsableByteArray.getPosition() < i9) {
            int position = parsableByteArray.getPosition();
            int i10 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_ilst) {
                parsableByteArray.setPosition(position);
                return parseIlst(parsableByteArray, position + i10);
            }
            parsableByteArray.skipBytes(i10 - 8);
        }
        return null;
    }

    private static long parseMvhd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        parsableByteArray.skipBytes(Atom.parseFullAtomVersion(parsableByteArray.readInt()) != 0 ? 16 : 8);
        return parsableByteArray.readUnsignedInt();
    }

    private static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i9) {
        parsableByteArray.setPosition(i9 + 8);
        return parsableByteArray.readUnsignedIntToInt() / parsableByteArray.readUnsignedIntToInt();
    }

    private static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i9, int i10) {
        int i11 = i9 + 8;
        while (i11 - i9 < i10) {
            parsableByteArray.setPosition(i11);
            int i12 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_proj) {
                return Arrays.copyOfRange(parsableByteArray.data, i11, i12 + i11);
            }
            i11 += i12;
        }
        return null;
    }

    private static Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i9, int i10) {
        Pair<Integer, TrackEncryptionBox> commonEncryptionSinfFromParent;
        int position = parsableByteArray.getPosition();
        while (position - i9 < i10) {
            parsableByteArray.setPosition(position);
            int i11 = parsableByteArray.readInt();
            Assertions.checkArgument(i11 > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_sinf && (commonEncryptionSinfFromParent = parseCommonEncryptionSinfFromParent(parsableByteArray, position, i11)) != null) {
                return commonEncryptionSinfFromParent;
            }
            position += i11;
        }
        return null;
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i9, int i10, String str) {
        int i11;
        int i12;
        int i13 = i9 + 8;
        while (true) {
            byte[] bArr = null;
            if (i13 - i9 >= i10) {
                return null;
            }
            parsableByteArray.setPosition(i13);
            int i14 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_tenc) {
                int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
                parsableByteArray.skipBytes(1);
                if (fullAtomVersion == 0) {
                    parsableByteArray.skipBytes(1);
                    i12 = 0;
                    i11 = 0;
                } else {
                    int unsignedByte = parsableByteArray.readUnsignedByte();
                    i11 = unsignedByte & 15;
                    i12 = (unsignedByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
                }
                boolean z8 = parsableByteArray.readUnsignedByte() == 1;
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                byte[] bArr2 = new byte[16];
                parsableByteArray.readBytes(bArr2, 0, 16);
                if (z8 && unsignedByte2 == 0) {
                    int unsignedByte3 = parsableByteArray.readUnsignedByte();
                    bArr = new byte[unsignedByte3];
                    parsableByteArray.readBytes(bArr, 0, unsignedByte3);
                }
                return new TrackEncryptionBox(z8, str, unsignedByte2, bArr2, i12, i11, bArr);
            }
            i13 += i14;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0207  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static TrackSampleTable parseStbl(Track track, Atom.ContainerAtom containerAtom, GaplessInfoHolder gaplessInfoHolder) throws ParserException {
        SampleSizeBox stz2SampleSizeBox;
        boolean z8;
        int unsignedIntToInt;
        int unsignedIntToInt2;
        Track track2;
        int i9;
        String str;
        long[] jArr;
        int[] iArr;
        int i10;
        long[] jArr2;
        int[] iArr2;
        long j9;
        long[] jArr3;
        int[] iArr3;
        int[] iArr4;
        int i11;
        long jScaleLargeTimestamp;
        long[] jArr4;
        long[] jArr5;
        int[] iArr5;
        int[] iArr6;
        long[] jArr6;
        boolean z9;
        int[] iArr7;
        int[] iArr8;
        int[] iArr9;
        String str2;
        int[] iArr10;
        int i12;
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_stsz);
        if (leafAtomOfType != null) {
            stz2SampleSizeBox = new StszSampleSizeBox(leafAtomOfType);
        } else {
            Atom.LeafAtom leafAtomOfType2 = containerAtom.getLeafAtomOfType(Atom.TYPE_stz2);
            if (leafAtomOfType2 == null) {
                throw new ParserException("Track has no sample table size information");
            }
            stz2SampleSizeBox = new Stz2SampleSizeBox(leafAtomOfType2);
        }
        int sampleCount = stz2SampleSizeBox.getSampleCount();
        if (sampleCount == 0) {
            return new TrackSampleTable(new long[0], new int[0], 0, new long[0], new int[0], C3322C.TIME_UNSET);
        }
        Atom.LeafAtom leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_stco);
        if (leafAtomOfType3 == null) {
            leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_co64);
            z8 = true;
        } else {
            z8 = false;
        }
        ParsableByteArray parsableByteArray = leafAtomOfType3.data;
        ParsableByteArray parsableByteArray2 = containerAtom.getLeafAtomOfType(Atom.TYPE_stsc).data;
        ParsableByteArray parsableByteArray3 = containerAtom.getLeafAtomOfType(Atom.TYPE_stts).data;
        Atom.LeafAtom leafAtomOfType4 = containerAtom.getLeafAtomOfType(Atom.TYPE_stss);
        ParsableByteArray parsableByteArray4 = null;
        ParsableByteArray parsableByteArray5 = leafAtomOfType4 != null ? leafAtomOfType4.data : null;
        Atom.LeafAtom leafAtomOfType5 = containerAtom.getLeafAtomOfType(Atom.TYPE_ctts);
        ParsableByteArray parsableByteArray6 = leafAtomOfType5 != null ? leafAtomOfType5.data : null;
        ChunkIterator chunkIterator = new ChunkIterator(parsableByteArray2, parsableByteArray, z8);
        parsableByteArray3.setPosition(12);
        int unsignedIntToInt3 = parsableByteArray3.readUnsignedIntToInt() - 1;
        int unsignedIntToInt4 = parsableByteArray3.readUnsignedIntToInt();
        int unsignedIntToInt5 = parsableByteArray3.readUnsignedIntToInt();
        if (parsableByteArray6 != null) {
            parsableByteArray6.setPosition(12);
            unsignedIntToInt = parsableByteArray6.readUnsignedIntToInt();
        } else {
            unsignedIntToInt = 0;
        }
        if (parsableByteArray5 != null) {
            parsableByteArray5.setPosition(12);
            unsignedIntToInt2 = parsableByteArray5.readUnsignedIntToInt();
            unsignedIntToInt = unsignedIntToInt2 > 0 ? parsableByteArray5.readUnsignedIntToInt() - 1 : -1;
            long j10 = 0;
            if (!stz2SampleSizeBox.isFixedSampleSize() && MimeTypes.AUDIO_RAW.equals(track.format.sampleMimeType) && unsignedIntToInt3 == 0 && unsignedIntToInt == 0 && unsignedIntToInt2 == 0) {
                jArr = new long[sampleCount];
                iArr = new int[sampleCount];
                int i13 = unsignedIntToInt2;
                jArr2 = new long[sampleCount];
                iArr2 = new int[sampleCount];
                int i14 = unsignedIntToInt5;
                long j11 = 0;
                long j12 = 0;
                int i15 = unsignedIntToInt3;
                int i16 = 0;
                i10 = 0;
                int unsignedIntToInt6 = 0;
                int i17 = 0;
                int i18 = 0;
                int i19 = i13;
                int unsignedIntToInt7 = unsignedIntToInt;
                int i20 = unsignedIntToInt;
                int i21 = unsignedIntToInt4;
                while (i16 < sampleCount) {
                    while (i18 == 0) {
                        Assertions.checkState(chunkIterator.moveNext());
                        j12 = chunkIterator.offset;
                        i18 = chunkIterator.numSamples;
                        i15 = i15;
                        i21 = i21;
                    }
                    int i22 = i15;
                    int i23 = i21;
                    if (parsableByteArray6 != null) {
                        while (unsignedIntToInt6 == 0 && i20 > 0) {
                            unsignedIntToInt6 = parsableByteArray6.readUnsignedIntToInt();
                            i17 = parsableByteArray6.readInt();
                            i20--;
                        }
                        unsignedIntToInt6--;
                    }
                    int i24 = i17;
                    jArr[i16] = j12;
                    int nextSampleSize = stz2SampleSizeBox.readNextSampleSize();
                    iArr[i16] = nextSampleSize;
                    int i25 = sampleCount;
                    if (nextSampleSize > i10) {
                        i10 = nextSampleSize;
                    }
                    SampleSizeBox sampleSizeBox = stz2SampleSizeBox;
                    jArr2[i16] = j11 + i24;
                    iArr2[i16] = parsableByteArray4 == null ? 1 : 0;
                    if (i16 == unsignedIntToInt7) {
                        iArr2[i16] = 1;
                        i19--;
                        if (i19 > 0) {
                            unsignedIntToInt7 = parsableByteArray4.readUnsignedIntToInt() - 1;
                        }
                    }
                    j11 += i14;
                    int unsignedIntToInt8 = i23 - 1;
                    if (unsignedIntToInt8 == 0 && i22 > 0) {
                        i22--;
                        unsignedIntToInt8 = parsableByteArray3.readUnsignedIntToInt();
                        i14 = parsableByteArray3.readInt();
                    }
                    int i26 = unsignedIntToInt8;
                    j12 += iArr[i16];
                    i18--;
                    i16++;
                    stz2SampleSizeBox = sampleSizeBox;
                    sampleCount = i25;
                    i14 = i14;
                    i21 = i26;
                    i17 = i24;
                    i15 = i22;
                }
                int i27 = i15;
                int i28 = i21;
                int i29 = i17;
                i9 = sampleCount;
                j9 = j11 + i29;
                Assertions.checkArgument(unsignedIntToInt6 == 0);
                while (i20 > 0) {
                    Assertions.checkArgument(parsableByteArray6.readUnsignedIntToInt() == 0);
                    parsableByteArray6.readInt();
                    i20--;
                }
                if (i19 == 0 && i28 == 0) {
                    i12 = i18;
                    if (i12 == 0 && i27 == 0) {
                        track2 = track;
                        str = TAG;
                    }
                } else {
                    i12 = i18;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Inconsistent stbl box for track ");
                track2 = track;
                sb.append(track2.f15304id);
                sb.append(": remainingSynchronizationSamples ");
                sb.append(i19);
                sb.append(", remainingSamplesAtTimestampDelta ");
                sb.append(i28);
                sb.append(", remainingSamplesInChunk ");
                sb.append(i12);
                sb.append(", remainingTimestampDeltaChanges ");
                sb.append(i27);
                String string = sb.toString();
                str = TAG;
                Log.w(str, string);
            } else {
                track2 = track;
                i9 = sampleCount;
                SampleSizeBox sampleSizeBox2 = stz2SampleSizeBox;
                str = TAG;
                int i30 = chunkIterator.length;
                long[] jArr7 = new long[i30];
                int[] iArr11 = new int[i30];
                while (chunkIterator.moveNext()) {
                    int i31 = chunkIterator.index;
                    jArr7[i31] = chunkIterator.offset;
                    iArr11[i31] = chunkIterator.numSamples;
                }
                FixedSampleSizeRechunker.Results resultsRechunk = FixedSampleSizeRechunker.rechunk(sampleSizeBox2.readNextSampleSize(), jArr7, iArr11, unsignedIntToInt5);
                jArr = resultsRechunk.offsets;
                iArr = resultsRechunk.sizes;
                i10 = resultsRechunk.maximumSize;
                jArr2 = resultsRechunk.timestamps;
                iArr2 = resultsRechunk.flags;
                j9 = resultsRechunk.duration;
            }
            jArr3 = jArr2;
            iArr3 = iArr;
            iArr4 = iArr2;
            i11 = i10;
            jScaleLargeTimestamp = Util.scaleLargeTimestamp(j9, C3322C.MICROS_PER_SECOND, track2.timescale);
            if (track2.editListDurations != null || gaplessInfoHolder.hasGaplessInfo()) {
                Util.scaleLargeTimestampsInPlace(jArr3, C3322C.MICROS_PER_SECOND, track2.timescale);
                return new TrackSampleTable(jArr, iArr3, i11, jArr3, iArr4, jScaleLargeTimestamp);
            }
            long[] jArr8 = track2.editListDurations;
            if (jArr8.length == 1 && track2.type == 1 && jArr3.length >= 2) {
                long j13 = track2.editListMediaTimes[0];
                long jScaleLargeTimestamp2 = Util.scaleLargeTimestamp(jArr8[0], track2.timescale, track2.movieTimescale) + j13;
                long j14 = jArr3[0];
                if (j14 <= j13 && j13 < jArr3[1] && jArr3[jArr3.length - 1] < jScaleLargeTimestamp2 && jScaleLargeTimestamp2 <= j9) {
                    long j15 = j9 - jScaleLargeTimestamp2;
                    long jScaleLargeTimestamp3 = Util.scaleLargeTimestamp(j13 - j14, track2.format.sampleRate, track2.timescale);
                    long jScaleLargeTimestamp4 = Util.scaleLargeTimestamp(j15, track2.format.sampleRate, track2.timescale);
                    if ((jScaleLargeTimestamp3 != 0 || jScaleLargeTimestamp4 != 0) && jScaleLargeTimestamp3 <= 2147483647L && jScaleLargeTimestamp4 <= 2147483647L) {
                        gaplessInfoHolder.encoderDelay = (int) jScaleLargeTimestamp3;
                        gaplessInfoHolder.encoderPadding = (int) jScaleLargeTimestamp4;
                        Util.scaleLargeTimestampsInPlace(jArr3, C3322C.MICROS_PER_SECOND, track2.timescale);
                        return new TrackSampleTable(jArr, iArr3, i11, jArr3, iArr4, jScaleLargeTimestamp);
                    }
                }
            }
            long[] jArr9 = track2.editListDurations;
            if (jArr9.length == 1 && jArr9[0] == 0) {
                long j16 = track2.editListMediaTimes[0];
                for (int i32 = 0; i32 < jArr3.length; i32++) {
                    jArr3[i32] = Util.scaleLargeTimestamp(jArr3[i32] - j16, C3322C.MICROS_PER_SECOND, track2.timescale);
                }
                return new TrackSampleTable(jArr, iArr3, i11, jArr3, iArr4, Util.scaleLargeTimestamp(j9 - j16, C3322C.MICROS_PER_SECOND, track2.timescale));
            }
            boolean z10 = track2.type == 1;
            boolean z11 = false;
            int i33 = 0;
            int i34 = 0;
            int i35 = 0;
            while (true) {
                long[] jArr10 = track2.editListDurations;
                if (i35 >= jArr10.length) {
                    break;
                }
                long j17 = track2.editListMediaTimes[i35];
                if (j17 != -1) {
                    iArr10 = iArr4;
                    iArr9 = iArr3;
                    str2 = str;
                    long jScaleLargeTimestamp5 = Util.scaleLargeTimestamp(jArr10[i35], track2.timescale, track2.movieTimescale);
                    int iBinarySearchCeil = Util.binarySearchCeil(jArr3, j17, true, true);
                    int iBinarySearchCeil2 = Util.binarySearchCeil(jArr3, j17 + jScaleLargeTimestamp5, z10, false);
                    i33 += iBinarySearchCeil2 - iBinarySearchCeil;
                    z11 = (i34 != iBinarySearchCeil) | z11;
                    i34 = iBinarySearchCeil2;
                } else {
                    iArr9 = iArr3;
                    str2 = str;
                    iArr10 = iArr4;
                }
                i35++;
                iArr4 = iArr10;
                str = str2;
                iArr3 = iArr9;
            }
            int[] iArr12 = iArr3;
            String str3 = str;
            int[] iArr13 = iArr4;
            boolean z12 = (i33 != i9) | z11;
            long[] jArr11 = z12 ? new long[i33] : jArr;
            int[] iArr14 = z12 ? new int[i33] : iArr12;
            int i36 = z12 ? 0 : i11;
            int[] iArr15 = z12 ? new int[i33] : iArr13;
            long[] jArr12 = new long[i33];
            int i37 = i36;
            int i38 = 0;
            int i39 = 0;
            while (true) {
                long[] jArr13 = track2.editListDurations;
                if (i38 >= jArr13.length) {
                    break;
                }
                int i40 = i37;
                int[] iArr16 = iArr13;
                long j18 = track2.editListMediaTimes[i38];
                long j19 = jArr13[i38];
                if (j18 != -1) {
                    jArr5 = jArr12;
                    int[] iArr17 = iArr15;
                    long jScaleLargeTimestamp6 = Util.scaleLargeTimestamp(j19, track2.timescale, track2.movieTimescale) + j18;
                    int iBinarySearchCeil3 = Util.binarySearchCeil(jArr3, j18, true, true);
                    int iBinarySearchCeil4 = Util.binarySearchCeil(jArr3, jScaleLargeTimestamp6, z10, false);
                    if (z12) {
                        int i41 = iBinarySearchCeil4 - iBinarySearchCeil3;
                        System.arraycopy(jArr, iBinarySearchCeil3, jArr11, i39, i41);
                        iArr5 = iArr12;
                        System.arraycopy(iArr5, iBinarySearchCeil3, iArr14, i39, i41);
                        z9 = z10;
                        iArr8 = iArr16;
                        jArr6 = jArr11;
                        iArr7 = iArr17;
                        System.arraycopy(iArr8, iBinarySearchCeil3, iArr7, i39, i41);
                    } else {
                        iArr5 = iArr12;
                        z9 = z10;
                        iArr8 = iArr16;
                        jArr6 = jArr11;
                        iArr7 = iArr17;
                    }
                    int i42 = i40;
                    while (iBinarySearchCeil3 < iBinarySearchCeil4) {
                        long[] jArr14 = jArr;
                        int[] iArr18 = iArr8;
                        long j20 = j18;
                        jArr5[i39] = Util.scaleLargeTimestamp(j10, C3322C.MICROS_PER_SECOND, track2.movieTimescale) + Util.scaleLargeTimestamp(jArr3[iBinarySearchCeil3] - j18, C3322C.MICROS_PER_SECOND, track2.timescale);
                        if (z12 && iArr14[i39] > i42) {
                            i42 = iArr5[iBinarySearchCeil3];
                        }
                        i39++;
                        iBinarySearchCeil3++;
                        jArr = jArr14;
                        j18 = j20;
                        iArr8 = iArr18;
                    }
                    jArr4 = jArr;
                    iArr6 = iArr8;
                    i37 = i42;
                } else {
                    jArr4 = jArr;
                    jArr5 = jArr12;
                    iArr5 = iArr12;
                    iArr6 = iArr16;
                    jArr6 = jArr11;
                    z9 = z10;
                    iArr7 = iArr15;
                    i37 = i40;
                }
                j10 += j19;
                i38++;
                iArr15 = iArr7;
                z10 = z9;
                jArr11 = jArr6;
                jArr = jArr4;
                jArr12 = jArr5;
                iArr13 = iArr6;
                iArr12 = iArr5;
            }
            long[] jArr15 = jArr11;
            long[] jArr16 = jArr;
            long[] jArr17 = jArr12;
            int[] iArr19 = iArr15;
            int i43 = i37;
            int[] iArr20 = iArr13;
            int[] iArr21 = iArr12;
            long jScaleLargeTimestamp7 = Util.scaleLargeTimestamp(j10, C3322C.MICROS_PER_SECOND, track2.timescale);
            boolean z13 = false;
            for (int i44 = 0; i44 < iArr19.length && !z13; i44++) {
                z13 |= (iArr19[i44] & 1) != 0;
            }
            if (z13) {
                return new TrackSampleTable(jArr15, iArr14, i43, jArr17, iArr19, jScaleLargeTimestamp7);
            }
            Log.w(str3, "Ignoring edit list: Edited sample sequence does not contain a sync sample.");
            Util.scaleLargeTimestampsInPlace(jArr3, C3322C.MICROS_PER_SECOND, track2.timescale);
            return new TrackSampleTable(jArr16, iArr21, i11, jArr3, iArr20, jScaleLargeTimestamp);
        }
        unsignedIntToInt2 = 0;
        parsableByteArray4 = parsableByteArray5;
        long j102 = 0;
        if (!stz2SampleSizeBox.isFixedSampleSize() && MimeTypes.AUDIO_RAW.equals(track.format.sampleMimeType) && unsignedIntToInt3 == 0 && unsignedIntToInt == 0 && unsignedIntToInt2 == 0) {
        }
        jArr3 = jArr2;
        iArr3 = iArr;
        iArr4 = iArr2;
        i11 = i10;
        jScaleLargeTimestamp = Util.scaleLargeTimestamp(j9, C3322C.MICROS_PER_SECOND, track2.timescale);
        if (track2.editListDurations != null) {
        }
        Util.scaleLargeTimestampsInPlace(jArr3, C3322C.MICROS_PER_SECOND, track2.timescale);
        return new TrackSampleTable(jArr, iArr3, i11, jArr3, iArr4, jScaleLargeTimestamp);
    }

    private static StsdData parseStsd(ParsableByteArray parsableByteArray, int i9, int i10, String str, DrmInitData drmInitData, boolean z8) throws ParserException {
        parsableByteArray.setPosition(12);
        int i11 = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(i11);
        for (int i12 = 0; i12 < i11; i12++) {
            int position = parsableByteArray.getPosition();
            int i13 = parsableByteArray.readInt();
            Assertions.checkArgument(i13 > 0, "childAtomSize should be positive");
            int i14 = parsableByteArray.readInt();
            if (i14 == Atom.TYPE_avc1 || i14 == Atom.TYPE_avc3 || i14 == Atom.TYPE_encv || i14 == Atom.TYPE_mp4v || i14 == Atom.TYPE_hvc1 || i14 == Atom.TYPE_hev1 || i14 == Atom.TYPE_s263 || i14 == Atom.TYPE_vp08 || i14 == Atom.TYPE_vp09) {
                parseVideoSampleEntry(parsableByteArray, i14, position, i13, i9, i10, drmInitData, stsdData, i12);
            } else if (i14 == Atom.TYPE_mp4a || i14 == Atom.TYPE_enca || i14 == Atom.TYPE_ac_3 || i14 == Atom.TYPE_ec_3 || i14 == Atom.TYPE_dtsc || i14 == Atom.TYPE_dtse || i14 == Atom.TYPE_dtsh || i14 == Atom.TYPE_dtsl || i14 == Atom.TYPE_samr || i14 == Atom.TYPE_sawb || i14 == Atom.TYPE_lpcm || i14 == Atom.TYPE_sowt || i14 == Atom.TYPE__mp3 || i14 == Atom.TYPE_alac) {
                parseAudioSampleEntry(parsableByteArray, i14, position, i13, i9, str, z8, drmInitData, stsdData, i12);
            } else if (i14 == Atom.TYPE_TTML || i14 == Atom.TYPE_tx3g || i14 == Atom.TYPE_wvtt || i14 == Atom.TYPE_stpp || i14 == Atom.TYPE_c608) {
                parseTextSampleEntry(parsableByteArray, i14, position, i13, i9, str, stsdData);
            } else if (i14 == Atom.TYPE_camm) {
                stsdData.format = Format.createSampleFormat(Integer.toString(i9), MimeTypes.APPLICATION_CAMERA_MOTION, null, -1, null);
            }
            parsableByteArray.setPosition(position + i13);
        }
        return stsdData;
    }

    private static void parseTextSampleEntry(ParsableByteArray parsableByteArray, int i9, int i10, int i11, int i12, String str, StsdData stsdData) {
        parsableByteArray.setPosition(i10 + 8 + 8);
        int i13 = Atom.TYPE_TTML;
        String str2 = MimeTypes.APPLICATION_TTML;
        List listSingletonList = null;
        long j9 = Long.MAX_VALUE;
        if (i9 != i13) {
            if (i9 == Atom.TYPE_tx3g) {
                int i14 = (i11 - 8) - 8;
                byte[] bArr = new byte[i14];
                parsableByteArray.readBytes(bArr, 0, i14);
                listSingletonList = Collections.singletonList(bArr);
                str2 = MimeTypes.APPLICATION_TX3G;
            } else if (i9 == Atom.TYPE_wvtt) {
                str2 = MimeTypes.APPLICATION_MP4VTT;
            } else if (i9 == Atom.TYPE_stpp) {
                j9 = 0;
            } else {
                if (i9 != Atom.TYPE_c608) {
                    throw new IllegalStateException();
                }
                stsdData.requiredSampleTransformation = 1;
                str2 = MimeTypes.APPLICATION_MP4CEA608;
            }
        }
        stsdData.format = Format.createTextSampleFormat(Integer.toString(i12), str2, null, -1, 0, str, -1, null, j9, listSingletonList);
    }

    private static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        boolean z8;
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(fullAtomVersion == 0 ? 8 : 16);
        int i9 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        int i10 = fullAtomVersion == 0 ? 4 : 8;
        int i11 = 0;
        int i12 = 0;
        while (true) {
            if (i12 >= i10) {
                z8 = true;
                break;
            }
            if (parsableByteArray.data[position + i12] != -1) {
                z8 = false;
                break;
            }
            i12++;
        }
        long j9 = C3322C.TIME_UNSET;
        if (z8) {
            parsableByteArray.skipBytes(i10);
        } else {
            long unsignedInt = fullAtomVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
            if (unsignedInt != 0) {
                j9 = unsignedInt;
            }
        }
        parsableByteArray.skipBytes(16);
        int i13 = parsableByteArray.readInt();
        int i14 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int i15 = parsableByteArray.readInt();
        int i16 = parsableByteArray.readInt();
        if (i13 == 0 && i14 == 65536 && i15 == -65536 && i16 == 0) {
            i11 = 90;
        } else if (i13 == 0 && i14 == -65536 && i15 == 65536 && i16 == 0) {
            i11 = 270;
        } else if (i13 == -65536 && i14 == 0 && i15 == 0 && i16 == -65536) {
            i11 = 180;
        }
        return new TkhdData(i9, j9, i11);
    }

    public static Track parseTrak(Atom.ContainerAtom containerAtom, Atom.LeafAtom leafAtom, long j9, DrmInitData drmInitData, boolean z8, boolean z9) {
        Atom.LeafAtom leafAtom2;
        long j10;
        long[] jArr;
        long[] jArr2;
        Atom.ContainerAtom containerAtomOfType = containerAtom.getContainerAtomOfType(Atom.TYPE_mdia);
        int hdlr = parseHdlr(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_hdlr).data);
        if (hdlr == -1) {
            return null;
        }
        TkhdData tkhd = parseTkhd(containerAtom.getLeafAtomOfType(Atom.TYPE_tkhd).data);
        long jScaleLargeTimestamp = C3322C.TIME_UNSET;
        if (j9 == C3322C.TIME_UNSET) {
            leafAtom2 = leafAtom;
            j10 = tkhd.duration;
        } else {
            leafAtom2 = leafAtom;
            j10 = j9;
        }
        long mvhd = parseMvhd(leafAtom2.data);
        if (j10 != C3322C.TIME_UNSET) {
            jScaleLargeTimestamp = Util.scaleLargeTimestamp(j10, C3322C.MICROS_PER_SECOND, mvhd);
        }
        long j11 = jScaleLargeTimestamp;
        Atom.ContainerAtom containerAtomOfType2 = containerAtomOfType.getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl);
        Pair<Long, String> mdhd = parseMdhd(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_mdhd).data);
        StsdData stsd = parseStsd(containerAtomOfType2.getLeafAtomOfType(Atom.TYPE_stsd).data, tkhd.f15303id, tkhd.rotationDegrees, (String) mdhd.second, drmInitData, z9);
        if (z8) {
            jArr = null;
            jArr2 = null;
        } else {
            Pair<long[], long[]> edts = parseEdts(containerAtom.getContainerAtomOfType(Atom.TYPE_edts));
            long[] jArr3 = (long[]) edts.first;
            jArr2 = (long[]) edts.second;
            jArr = jArr3;
        }
        if (stsd.format == null) {
            return null;
        }
        return new Track(tkhd.f15303id, hdlr, ((Long) mdhd.first).longValue(), mvhd, j11, stsd.format, stsd.requiredSampleTransformation, stsd.trackEncryptionBoxes, stsd.nalUnitLengthFieldLength, jArr, jArr2);
    }

    public static Metadata parseUdta(Atom.LeafAtom leafAtom, boolean z8) {
        if (z8) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtom.data;
        parsableByteArray.setPosition(8);
        while (parsableByteArray.bytesLeft() >= 8) {
            int position = parsableByteArray.getPosition();
            int i9 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_meta) {
                parsableByteArray.setPosition(position);
                return parseMetaAtom(parsableByteArray, position + i9);
            }
            parsableByteArray.skipBytes(i9 - 8);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:79:0x013e A[PHI: r13
      0x013e: PHI (r13v2 int) = (r13v1 int), (r13v3 int) binds: [B:73:0x0133, B:75:0x0136] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void parseVideoSampleEntry(ParsableByteArray parsableByteArray, int i9, int i10, int i11, int i12, int i13, DrmInitData drmInitData, StsdData stsdData, int i14) throws ParserException {
        DrmInitData drmInitDataCopyWithSchemeType = drmInitData;
        parsableByteArray.setPosition(i10 + 8 + 8);
        parsableByteArray.skipBytes(16);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int unsignedShort2 = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(50);
        int position = parsableByteArray.getPosition();
        String str = null;
        int iIntValue = i9;
        if (iIntValue == Atom.TYPE_encv) {
            Pair<Integer, TrackEncryptionBox> sampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, i10, i11);
            if (sampleEntryEncryptionData != null) {
                iIntValue = ((Integer) sampleEntryEncryptionData.first).intValue();
                drmInitDataCopyWithSchemeType = drmInitDataCopyWithSchemeType == null ? null : drmInitDataCopyWithSchemeType.copyWithSchemeType(((TrackEncryptionBox) sampleEntryEncryptionData.second).schemeType);
                stsdData.trackEncryptionBoxes[i14] = (TrackEncryptionBox) sampleEntryEncryptionData.second;
            }
            parsableByteArray.setPosition(position);
        }
        DrmInitData drmInitData2 = drmInitDataCopyWithSchemeType;
        float paspFromParent = 1.0f;
        List<byte[]> listSingletonList = null;
        byte[] projFromParent = null;
        int i15 = -1;
        boolean z8 = false;
        while (position - i10 < i11) {
            parsableByteArray.setPosition(position);
            int position2 = parsableByteArray.getPosition();
            int i16 = parsableByteArray.readInt();
            if (i16 == 0 && parsableByteArray.getPosition() - i10 == i11) {
                break;
            }
            int i17 = 1;
            Assertions.checkArgument(i16 > 0, "childAtomSize should be positive");
            int i18 = parsableByteArray.readInt();
            if (i18 == Atom.TYPE_avcC) {
                Assertions.checkState(str == null);
                parsableByteArray.setPosition(position2 + 8);
                AvcConfig avcConfig = AvcConfig.parse(parsableByteArray);
                listSingletonList = avcConfig.initializationData;
                stsdData.nalUnitLengthFieldLength = avcConfig.nalUnitLengthFieldLength;
                if (!z8) {
                    paspFromParent = avcConfig.pixelWidthAspectRatio;
                }
                str = MimeTypes.VIDEO_H264;
            } else if (i18 == Atom.TYPE_hvcC) {
                Assertions.checkState(str == null);
                parsableByteArray.setPosition(position2 + 8);
                HevcConfig hevcConfig = HevcConfig.parse(parsableByteArray);
                listSingletonList = hevcConfig.initializationData;
                stsdData.nalUnitLengthFieldLength = hevcConfig.nalUnitLengthFieldLength;
                str = MimeTypes.VIDEO_H265;
            } else if (i18 == Atom.TYPE_vpcC) {
                Assertions.checkState(str == null);
                str = iIntValue == Atom.TYPE_vp08 ? MimeTypes.VIDEO_VP8 : MimeTypes.VIDEO_VP9;
            } else if (i18 == Atom.TYPE_d263) {
                Assertions.checkState(str == null);
                str = MimeTypes.VIDEO_H263;
            } else if (i18 == Atom.TYPE_esds) {
                Assertions.checkState(str == null);
                Pair<String, byte[]> esdsFromParent = parseEsdsFromParent(parsableByteArray, position2);
                str = (String) esdsFromParent.first;
                listSingletonList = Collections.singletonList(esdsFromParent.second);
            } else if (i18 == Atom.TYPE_pasp) {
                paspFromParent = parsePaspFromParent(parsableByteArray, position2);
                z8 = true;
            } else if (i18 == Atom.TYPE_sv3d) {
                projFromParent = parseProjFromParent(parsableByteArray, position2, i16);
            } else if (i18 == Atom.TYPE_st3d) {
                int unsignedByte = parsableByteArray.readUnsignedByte();
                parsableByteArray.skipBytes(3);
                if (unsignedByte == 0) {
                    int unsignedByte2 = parsableByteArray.readUnsignedByte();
                    if (unsignedByte2 == 0) {
                        i15 = 0;
                    } else if (unsignedByte2 != 1) {
                        i17 = 2;
                        if (unsignedByte2 == 2) {
                            i15 = i17;
                        } else if (unsignedByte2 == 3) {
                            i15 = 3;
                        }
                    }
                }
            }
            position += i16;
        }
        if (str == null) {
            return;
        }
        stsdData.format = Format.createVideoSampleFormat(Integer.toString(i12), str, null, -1, -1, unsignedShort, unsignedShort2, -1.0f, listSingletonList, i13, paspFromParent, projFromParent, i15, null, drmInitData2);
    }
}
