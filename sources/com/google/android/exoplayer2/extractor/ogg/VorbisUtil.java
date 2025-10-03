package com.google.android.exoplayer2.extractor.ogg;

import android.util.Log;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Arrays;

/* loaded from: classes.dex */
final class VorbisUtil {
    private static final String TAG = "VorbisUtil";

    public static final class CodeBook {
        public final int dimensions;
        public final int entries;
        public final boolean isOrdered;
        public final long[] lengthMap;
        public final int lookupType;

        public CodeBook(int i9, int i10, long[] jArr, int i11, boolean z8) {
            this.dimensions = i9;
            this.entries = i10;
            this.lengthMap = jArr;
            this.lookupType = i11;
            this.isOrdered = z8;
        }
    }

    public static final class CommentHeader {
        public final String[] comments;
        public final int length;
        public final String vendor;

        public CommentHeader(String str, String[] strArr, int i9) {
            this.vendor = str;
            this.comments = strArr;
            this.length = i9;
        }
    }

    public static final class Mode {
        public final boolean blockFlag;
        public final int mapping;
        public final int transformType;
        public final int windowType;

        public Mode(boolean z8, int i9, int i10, int i11) {
            this.blockFlag = z8;
            this.windowType = i9;
            this.transformType = i10;
            this.mapping = i11;
        }
    }

    public static final class VorbisIdHeader {
        public final int bitrateMax;
        public final int bitrateMin;
        public final int bitrateNominal;
        public final int blockSize0;
        public final int blockSize1;
        public final int channels;
        public final byte[] data;
        public final boolean framingFlag;
        public final long sampleRate;
        public final long version;

        public VorbisIdHeader(long j9, int i9, long j10, int i10, int i11, int i12, int i13, int i14, boolean z8, byte[] bArr) {
            this.version = j9;
            this.channels = i9;
            this.sampleRate = j10;
            this.bitrateMax = i10;
            this.bitrateNominal = i11;
            this.bitrateMin = i12;
            this.blockSize0 = i13;
            this.blockSize1 = i14;
            this.framingFlag = z8;
            this.data = bArr;
        }

        public int getApproximateBitrate() {
            int i9 = this.bitrateNominal;
            return i9 == 0 ? (this.bitrateMin + this.bitrateMax) / 2 : i9;
        }
    }

    public static int iLog(int i9) {
        int i10 = 0;
        while (i9 > 0) {
            i10++;
            i9 >>>= 1;
        }
        return i10;
    }

    private static long mapType1QuantValues(long j9, long j10) {
        return (long) Math.floor(Math.pow(j9, 1.0d / j10));
    }

    private static CodeBook readBook(VorbisBitArray vorbisBitArray) throws ParserException {
        if (vorbisBitArray.readBits(24) != 5653314) {
            throw new ParserException("expected code book to start with [0x56, 0x43, 0x42] at " + vorbisBitArray.getPosition());
        }
        int bits = vorbisBitArray.readBits(16);
        int bits2 = vorbisBitArray.readBits(24);
        long[] jArr = new long[bits2];
        boolean bit = vorbisBitArray.readBit();
        long jMapType1QuantValues = 0;
        if (bit) {
            int bits3 = vorbisBitArray.readBits(5) + 1;
            int i9 = 0;
            while (i9 < bits2) {
                int bits4 = vorbisBitArray.readBits(iLog(bits2 - i9));
                for (int i10 = 0; i10 < bits4 && i9 < bits2; i10++) {
                    jArr[i9] = bits3;
                    i9++;
                }
                bits3++;
            }
        } else {
            boolean bit2 = vorbisBitArray.readBit();
            for (int i11 = 0; i11 < bits2; i11++) {
                if (!bit2) {
                    jArr[i11] = vorbisBitArray.readBits(5) + 1;
                } else if (vorbisBitArray.readBit()) {
                    jArr[i11] = vorbisBitArray.readBits(5) + 1;
                } else {
                    jArr[i11] = 0;
                }
            }
        }
        int bits5 = vorbisBitArray.readBits(4);
        if (bits5 > 2) {
            throw new ParserException("lookup type greater than 2 not decodable: " + bits5);
        }
        if (bits5 == 1 || bits5 == 2) {
            vorbisBitArray.skipBits(32);
            vorbisBitArray.skipBits(32);
            int bits6 = vorbisBitArray.readBits(4) + 1;
            vorbisBitArray.skipBits(1);
            if (bits5 != 1) {
                jMapType1QuantValues = bits2 * bits;
            } else if (bits != 0) {
                jMapType1QuantValues = mapType1QuantValues(bits2, bits);
            }
            vorbisBitArray.skipBits((int) (jMapType1QuantValues * bits6));
        }
        return new CodeBook(bits, bits2, jArr, bits5, bit);
    }

    private static void readFloors(VorbisBitArray vorbisBitArray) throws ParserException {
        int bits = vorbisBitArray.readBits(6) + 1;
        for (int i9 = 0; i9 < bits; i9++) {
            int bits2 = vorbisBitArray.readBits(16);
            if (bits2 == 0) {
                vorbisBitArray.skipBits(8);
                vorbisBitArray.skipBits(16);
                vorbisBitArray.skipBits(16);
                vorbisBitArray.skipBits(6);
                vorbisBitArray.skipBits(8);
                int bits3 = vorbisBitArray.readBits(4) + 1;
                for (int i10 = 0; i10 < bits3; i10++) {
                    vorbisBitArray.skipBits(8);
                }
            } else {
                if (bits2 != 1) {
                    throw new ParserException("floor type greater than 1 not decodable: " + bits2);
                }
                int bits4 = vorbisBitArray.readBits(5);
                int[] iArr = new int[bits4];
                int i11 = -1;
                for (int i12 = 0; i12 < bits4; i12++) {
                    int bits5 = vorbisBitArray.readBits(4);
                    iArr[i12] = bits5;
                    if (bits5 > i11) {
                        i11 = bits5;
                    }
                }
                int i13 = i11 + 1;
                int[] iArr2 = new int[i13];
                for (int i14 = 0; i14 < i13; i14++) {
                    iArr2[i14] = vorbisBitArray.readBits(3) + 1;
                    int bits6 = vorbisBitArray.readBits(2);
                    if (bits6 > 0) {
                        vorbisBitArray.skipBits(8);
                    }
                    for (int i15 = 0; i15 < (1 << bits6); i15++) {
                        vorbisBitArray.skipBits(8);
                    }
                }
                vorbisBitArray.skipBits(2);
                int bits7 = vorbisBitArray.readBits(4);
                int i16 = 0;
                int i17 = 0;
                for (int i18 = 0; i18 < bits4; i18++) {
                    i16 += iArr2[iArr[i18]];
                    while (i17 < i16) {
                        vorbisBitArray.skipBits(bits7);
                        i17++;
                    }
                }
            }
        }
    }

    private static void readMappings(int i9, VorbisBitArray vorbisBitArray) throws ParserException {
        int bits = vorbisBitArray.readBits(6) + 1;
        for (int i10 = 0; i10 < bits; i10++) {
            int bits2 = vorbisBitArray.readBits(16);
            if (bits2 != 0) {
                Log.e(TAG, "mapping type other than 0 not supported: " + bits2);
            } else {
                int bits3 = vorbisBitArray.readBit() ? vorbisBitArray.readBits(4) + 1 : 1;
                if (vorbisBitArray.readBit()) {
                    int bits4 = vorbisBitArray.readBits(8) + 1;
                    for (int i11 = 0; i11 < bits4; i11++) {
                        int i12 = i9 - 1;
                        vorbisBitArray.skipBits(iLog(i12));
                        vorbisBitArray.skipBits(iLog(i12));
                    }
                }
                if (vorbisBitArray.readBits(2) != 0) {
                    throw new ParserException("to reserved bits must be zero after mapping coupling steps");
                }
                if (bits3 > 1) {
                    for (int i13 = 0; i13 < i9; i13++) {
                        vorbisBitArray.skipBits(4);
                    }
                }
                for (int i14 = 0; i14 < bits3; i14++) {
                    vorbisBitArray.skipBits(8);
                    vorbisBitArray.skipBits(8);
                    vorbisBitArray.skipBits(8);
                }
            }
        }
    }

    private static Mode[] readModes(VorbisBitArray vorbisBitArray) {
        int bits = vorbisBitArray.readBits(6) + 1;
        Mode[] modeArr = new Mode[bits];
        for (int i9 = 0; i9 < bits; i9++) {
            modeArr[i9] = new Mode(vorbisBitArray.readBit(), vorbisBitArray.readBits(16), vorbisBitArray.readBits(16), vorbisBitArray.readBits(8));
        }
        return modeArr;
    }

    private static void readResidues(VorbisBitArray vorbisBitArray) throws ParserException {
        int bits = vorbisBitArray.readBits(6) + 1;
        for (int i9 = 0; i9 < bits; i9++) {
            if (vorbisBitArray.readBits(16) > 2) {
                throw new ParserException("residueType greater than 2 is not decodable");
            }
            vorbisBitArray.skipBits(24);
            vorbisBitArray.skipBits(24);
            vorbisBitArray.skipBits(24);
            int bits2 = vorbisBitArray.readBits(6) + 1;
            vorbisBitArray.skipBits(8);
            int[] iArr = new int[bits2];
            for (int i10 = 0; i10 < bits2; i10++) {
                iArr[i10] = ((vorbisBitArray.readBit() ? vorbisBitArray.readBits(5) : 0) * 8) + vorbisBitArray.readBits(3);
            }
            for (int i11 = 0; i11 < bits2; i11++) {
                for (int i12 = 0; i12 < 8; i12++) {
                    if ((iArr[i11] & (1 << i12)) != 0) {
                        vorbisBitArray.skipBits(8);
                    }
                }
            }
        }
    }

    public static CommentHeader readVorbisCommentHeader(ParsableByteArray parsableByteArray) throws ParserException {
        verifyVorbisHeaderCapturePattern(3, parsableByteArray, false);
        String string = parsableByteArray.readString((int) parsableByteArray.readLittleEndianUnsignedInt());
        int length = 11 + string.length();
        long littleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
        String[] strArr = new String[(int) littleEndianUnsignedInt];
        int length2 = length + 4;
        for (int i9 = 0; i9 < littleEndianUnsignedInt; i9++) {
            String string2 = parsableByteArray.readString((int) parsableByteArray.readLittleEndianUnsignedInt());
            strArr[i9] = string2;
            length2 = length2 + 4 + string2.length();
        }
        if ((parsableByteArray.readUnsignedByte() & 1) != 0) {
            return new CommentHeader(string, strArr, length2 + 1);
        }
        throw new ParserException("framing bit expected to be set");
    }

    public static VorbisIdHeader readVorbisIdentificationHeader(ParsableByteArray parsableByteArray) throws ParserException {
        verifyVorbisHeaderCapturePattern(1, parsableByteArray, false);
        long littleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
        int unsignedByte = parsableByteArray.readUnsignedByte();
        long littleEndianUnsignedInt2 = parsableByteArray.readLittleEndianUnsignedInt();
        int littleEndianInt = parsableByteArray.readLittleEndianInt();
        int littleEndianInt2 = parsableByteArray.readLittleEndianInt();
        int littleEndianInt3 = parsableByteArray.readLittleEndianInt();
        int unsignedByte2 = parsableByteArray.readUnsignedByte();
        return new VorbisIdHeader(littleEndianUnsignedInt, unsignedByte, littleEndianUnsignedInt2, littleEndianInt, littleEndianInt2, littleEndianInt3, (int) Math.pow(2.0d, unsignedByte2 & 15), (int) Math.pow(2.0d, (unsignedByte2 & PsExtractor.VIDEO_STREAM_MASK) >> 4), (parsableByteArray.readUnsignedByte() & 1) > 0, Arrays.copyOf(parsableByteArray.data, parsableByteArray.limit()));
    }

    public static Mode[] readVorbisModes(ParsableByteArray parsableByteArray, int i9) throws ParserException {
        verifyVorbisHeaderCapturePattern(5, parsableByteArray, false);
        int unsignedByte = parsableByteArray.readUnsignedByte() + 1;
        VorbisBitArray vorbisBitArray = new VorbisBitArray(parsableByteArray.data);
        vorbisBitArray.skipBits(parsableByteArray.getPosition() * 8);
        for (int i10 = 0; i10 < unsignedByte; i10++) {
            readBook(vorbisBitArray);
        }
        int bits = vorbisBitArray.readBits(6) + 1;
        for (int i11 = 0; i11 < bits; i11++) {
            if (vorbisBitArray.readBits(16) != 0) {
                throw new ParserException("placeholder of time domain transforms not zeroed out");
            }
        }
        readFloors(vorbisBitArray);
        readResidues(vorbisBitArray);
        readMappings(i9, vorbisBitArray);
        Mode[] modes = readModes(vorbisBitArray);
        if (vorbisBitArray.readBit()) {
            return modes;
        }
        throw new ParserException("framing bit after modes not set as expected");
    }

    public static boolean verifyVorbisHeaderCapturePattern(int i9, ParsableByteArray parsableByteArray, boolean z8) throws ParserException {
        if (parsableByteArray.bytesLeft() < 7) {
            if (z8) {
                return false;
            }
            throw new ParserException("too short header: " + parsableByteArray.bytesLeft());
        }
        if (parsableByteArray.readUnsignedByte() != i9) {
            if (z8) {
                return false;
            }
            throw new ParserException("expected header type " + Integer.toHexString(i9));
        }
        if (parsableByteArray.readUnsignedByte() == 118 && parsableByteArray.readUnsignedByte() == 111 && parsableByteArray.readUnsignedByte() == 114 && parsableByteArray.readUnsignedByte() == 98 && parsableByteArray.readUnsignedByte() == 105 && parsableByteArray.readUnsignedByte() == 115) {
            return true;
        }
        if (z8) {
            return false;
        }
        throw new ParserException("expected characters 'vorbis'");
    }
}
