package com.google.android.exoplayer2.util;

import android.util.Log;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class NalUnitUtil {
    public static final int EXTENDED_SAR = 255;
    private static final int H264_NAL_UNIT_TYPE_SEI = 6;
    private static final int H264_NAL_UNIT_TYPE_SPS = 7;
    private static final int H265_NAL_UNIT_TYPE_PREFIX_SEI = 39;
    private static final String TAG = "NalUnitUtil";
    public static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    public static final float[] ASPECT_RATIO_IDC_VALUES = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    private static final Object scratchEscapePositionsLock = new Object();
    private static int[] scratchEscapePositions = new int[10];

    public static final class PpsData {
        public final boolean bottomFieldPicOrderInFramePresentFlag;
        public final int picParameterSetId;
        public final int seqParameterSetId;

        public PpsData(int i9, int i10, boolean z8) {
            this.picParameterSetId = i9;
            this.seqParameterSetId = i10;
            this.bottomFieldPicOrderInFramePresentFlag = z8;
        }
    }

    public static final class SpsData {
        public final boolean deltaPicOrderAlwaysZeroFlag;
        public final boolean frameMbsOnlyFlag;
        public final int frameNumLength;
        public final int height;
        public final int picOrderCntLsbLength;
        public final int picOrderCountType;
        public final float pixelWidthAspectRatio;
        public final boolean separateColorPlaneFlag;
        public final int seqParameterSetId;
        public final int width;

        public SpsData(int i9, int i10, int i11, float f9, boolean z8, boolean z9, int i12, int i13, int i14, boolean z10) {
            this.seqParameterSetId = i9;
            this.width = i10;
            this.height = i11;
            this.pixelWidthAspectRatio = f9;
            this.separateColorPlaneFlag = z8;
            this.frameMbsOnlyFlag = z9;
            this.frameNumLength = i12;
            this.picOrderCountType = i13;
            this.picOrderCntLsbLength = i14;
            this.deltaPicOrderAlwaysZeroFlag = z10;
        }
    }

    private NalUnitUtil() {
    }

    public static void clearPrefixFlags(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }

    public static void discardToSps(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        int i9 = 0;
        int i10 = 0;
        while (true) {
            int i11 = i9 + 1;
            if (i11 >= iPosition) {
                byteBuffer.clear();
                return;
            }
            int i12 = byteBuffer.get(i9) & UnsignedBytes.MAX_VALUE;
            if (i10 == 3) {
                if (i12 == 1 && (byteBuffer.get(i11) & Ascii.f15392US) == 7) {
                    ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
                    byteBufferDuplicate.position(i9 - 3);
                    byteBufferDuplicate.limit(iPosition);
                    byteBuffer.position(0);
                    byteBuffer.put(byteBufferDuplicate);
                    return;
                }
            } else if (i12 == 0) {
                i10++;
            }
            if (i12 != 0) {
                i10 = 0;
            }
            i9 = i11;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x0095, code lost:
    
        r9 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int findNalUnit(byte[] bArr, int i9, int i10, boolean[] zArr) {
        int i11 = i10 - i9;
        Assertions.checkState(i11 >= 0);
        if (i11 == 0) {
            return i10;
        }
        if (zArr != null) {
            if (zArr[0]) {
                clearPrefixFlags(zArr);
                return i9 - 3;
            }
            if (i11 > 1 && zArr[1] && bArr[i9] == 1) {
                clearPrefixFlags(zArr);
                return i9 - 2;
            }
            if (i11 > 2 && zArr[2] && bArr[i9] == 0 && bArr[i9 + 1] == 1) {
                clearPrefixFlags(zArr);
                return i9 - 1;
            }
        }
        int i12 = i10 - 1;
        int i13 = i9 + 2;
        while (i13 < i12) {
            byte b9 = bArr[i13];
            if ((b9 & 254) == 0) {
                int i14 = i13 - 2;
                if (bArr[i14] == 0 && bArr[i13 - 1] == 0 && b9 == 1) {
                    if (zArr != null) {
                        clearPrefixFlags(zArr);
                    }
                    return i14;
                }
                i13 -= 2;
            }
            i13 += 3;
        }
        if (zArr != null) {
            boolean z8 = i11 > 2 ? false : false;
            zArr[0] = z8;
            zArr[1] = i11 <= 1 ? zArr[2] && bArr[i12] == 0 : bArr[i10 + (-2)] == 0 && bArr[i12] == 0;
            zArr[2] = bArr[i12] == 0;
        }
        return i10;
    }

    private static int findNextUnescapeIndex(byte[] bArr, int i9, int i10) {
        while (i9 < i10 - 2) {
            if (bArr[i9] == 0 && bArr[i9 + 1] == 0 && bArr[i9 + 2] == 3) {
                return i9;
            }
            i9++;
        }
        return i10;
    }

    public static int getH265NalUnitType(byte[] bArr, int i9) {
        return (bArr[i9 + 3] & 126) >> 1;
    }

    public static int getNalUnitType(byte[] bArr, int i9) {
        return bArr[i9 + 3] & Ascii.f15392US;
    }

    public static boolean isNalUnitSei(String str, byte b9) {
        if (MimeTypes.VIDEO_H264.equals(str) && (b9 & Ascii.f15392US) == 6) {
            return true;
        }
        return MimeTypes.VIDEO_H265.equals(str) && ((b9 & 126) >> 1) == 39;
    }

    public static PpsData parsePpsNalUnit(byte[] bArr, int i9, int i10) {
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i9, i10);
        parsableNalUnitBitArray.skipBits(8);
        int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int unsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.skipBit();
        return new PpsData(unsignedExpGolombCodedInt, unsignedExpGolombCodedInt2, parsableNalUnitBitArray.readBit());
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x015c A[PHI: r4
      0x015c: PHI (r4v11 float) = (r4v10 float), (r4v10 float), (r4v10 float), (r4v10 float), (r4v10 float), (r4v13 float) binds: [B:72:0x011c, B:74:0x0122, B:84:0x0146, B:78:0x0134, B:79:0x0136, B:80:0x0138] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static SpsData parseSpsNalUnit(byte[] bArr, int i9, int i10) {
        int unsignedExpGolombCodedInt;
        boolean z8;
        boolean z9;
        int unsignedExpGolombCodedInt2;
        boolean z10;
        boolean bit;
        float f9;
        float f10;
        int i11;
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i9, i10);
        parsableNalUnitBitArray.skipBits(8);
        int bits = parsableNalUnitBitArray.readBits(8);
        parsableNalUnitBitArray.skipBits(16);
        int unsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (bits == 100 || bits == 110 || bits == 122 || bits == 244 || bits == 44 || bits == 83 || bits == 86 || bits == 118 || bits == 128 || bits == 138) {
            unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            boolean bit2 = unsignedExpGolombCodedInt == 3 ? parsableNalUnitBitArray.readBit() : false;
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.skipBit();
            if (parsableNalUnitBitArray.readBit()) {
                int i12 = unsignedExpGolombCodedInt != 3 ? 8 : 12;
                int i13 = 0;
                while (i13 < i12) {
                    if (parsableNalUnitBitArray.readBit()) {
                        skipScalingList(parsableNalUnitBitArray, i13 < 6 ? 16 : 64);
                    }
                    i13++;
                }
            }
            z8 = bit2;
        } else {
            unsignedExpGolombCodedInt = 1;
            z8 = false;
        }
        int unsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 4;
        int unsignedExpGolombCodedInt5 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (unsignedExpGolombCodedInt5 == 0) {
            unsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 4;
            z9 = z8;
        } else {
            if (unsignedExpGolombCodedInt5 == 1) {
                boolean bit3 = parsableNalUnitBitArray.readBit();
                parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                long unsignedExpGolombCodedInt6 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                z9 = z8;
                for (int i14 = 0; i14 < unsignedExpGolombCodedInt6; i14++) {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                }
                z10 = bit3;
                unsignedExpGolombCodedInt2 = 0;
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                parsableNalUnitBitArray.skipBit();
                int unsignedExpGolombCodedInt7 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
                int unsignedExpGolombCodedInt8 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
                bit = parsableNalUnitBitArray.readBit();
                int i15 = (2 - (bit ? 1 : 0)) * unsignedExpGolombCodedInt8;
                if (!bit) {
                    parsableNalUnitBitArray.skipBit();
                }
                parsableNalUnitBitArray.skipBit();
                int i16 = unsignedExpGolombCodedInt7 * 16;
                int i17 = i15 * 16;
                if (parsableNalUnitBitArray.readBit()) {
                    int unsignedExpGolombCodedInt9 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    int unsignedExpGolombCodedInt10 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    int unsignedExpGolombCodedInt11 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    int unsignedExpGolombCodedInt12 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    if (unsignedExpGolombCodedInt == 0) {
                        i11 = 2 - (bit ? 1 : 0);
                    } else {
                        int i18 = unsignedExpGolombCodedInt == 3 ? 1 : 2;
                        i11 = (2 - (bit ? 1 : 0)) * (unsignedExpGolombCodedInt == 1 ? 2 : 1);
                        i = i18;
                    }
                    i16 -= (unsignedExpGolombCodedInt9 + unsignedExpGolombCodedInt10) * i;
                    i17 -= (unsignedExpGolombCodedInt11 + unsignedExpGolombCodedInt12) * i11;
                }
                int i19 = i16;
                int i20 = i17;
                f9 = 1.0f;
                if (parsableNalUnitBitArray.readBit() || !parsableNalUnitBitArray.readBit()) {
                    f10 = f9;
                } else {
                    int bits2 = parsableNalUnitBitArray.readBits(8);
                    if (bits2 == 255) {
                        int bits3 = parsableNalUnitBitArray.readBits(16);
                        int bits4 = parsableNalUnitBitArray.readBits(16);
                        if (bits3 != 0 && bits4 != 0) {
                            f9 = bits3 / bits4;
                        }
                    } else {
                        float[] fArr = ASPECT_RATIO_IDC_VALUES;
                        if (bits2 < fArr.length) {
                            f10 = fArr[bits2];
                        } else {
                            Log.w(TAG, "Unexpected aspect_ratio_idc value: " + bits2);
                        }
                    }
                    f10 = f9;
                }
                return new SpsData(unsignedExpGolombCodedInt3, i19, i20, f10, z9, bit, unsignedExpGolombCodedInt4, unsignedExpGolombCodedInt5, unsignedExpGolombCodedInt2, z10);
            }
            z9 = z8;
            unsignedExpGolombCodedInt2 = 0;
        }
        z10 = false;
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.skipBit();
        int unsignedExpGolombCodedInt72 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        int unsignedExpGolombCodedInt82 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        bit = parsableNalUnitBitArray.readBit();
        int i152 = (2 - (bit ? 1 : 0)) * unsignedExpGolombCodedInt82;
        if (!bit) {
        }
        parsableNalUnitBitArray.skipBit();
        int i162 = unsignedExpGolombCodedInt72 * 16;
        int i172 = i152 * 16;
        if (parsableNalUnitBitArray.readBit()) {
        }
        int i192 = i162;
        int i202 = i172;
        f9 = 1.0f;
        if (parsableNalUnitBitArray.readBit()) {
            f10 = f9;
        }
        return new SpsData(unsignedExpGolombCodedInt3, i192, i202, f10, z9, bit, unsignedExpGolombCodedInt4, unsignedExpGolombCodedInt5, unsignedExpGolombCodedInt2, z10);
    }

    private static void skipScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray, int i9) {
        int signedExpGolombCodedInt = 8;
        int i10 = 8;
        for (int i11 = 0; i11 < i9; i11++) {
            if (signedExpGolombCodedInt != 0) {
                signedExpGolombCodedInt = ((parsableNalUnitBitArray.readSignedExpGolombCodedInt() + i10) + 256) % 256;
            }
            if (signedExpGolombCodedInt != 0) {
                i10 = signedExpGolombCodedInt;
            }
        }
    }

    public static int unescapeStream(byte[] bArr, int i9) {
        int i10;
        synchronized (scratchEscapePositionsLock) {
            int iFindNextUnescapeIndex = 0;
            int i11 = 0;
            while (iFindNextUnescapeIndex < i9) {
                try {
                    iFindNextUnescapeIndex = findNextUnescapeIndex(bArr, iFindNextUnescapeIndex, i9);
                    if (iFindNextUnescapeIndex < i9) {
                        int[] iArr = scratchEscapePositions;
                        if (iArr.length <= i11) {
                            scratchEscapePositions = Arrays.copyOf(iArr, iArr.length * 2);
                        }
                        scratchEscapePositions[i11] = iFindNextUnescapeIndex;
                        iFindNextUnescapeIndex += 3;
                        i11++;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            i10 = i9 - i11;
            int i12 = 0;
            int i13 = 0;
            for (int i14 = 0; i14 < i11; i14++) {
                int i15 = scratchEscapePositions[i14] - i13;
                System.arraycopy(bArr, i13, bArr, i12, i15);
                int i16 = i12 + i15;
                int i17 = i16 + 1;
                bArr[i16] = 0;
                i12 = i17 + 1;
                bArr[i17] = 0;
                i13 += i15 + 3;
            }
            System.arraycopy(bArr, i13, bArr, i12, i10 - i12);
        }
        return i10;
    }
}
