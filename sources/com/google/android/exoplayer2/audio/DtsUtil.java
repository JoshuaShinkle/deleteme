package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class DtsUtil {
    private static final byte FIRST_BYTE_14B_BE = 31;
    private static final byte FIRST_BYTE_14B_LE = -1;
    private static final byte FIRST_BYTE_BE = 127;
    private static final byte FIRST_BYTE_LE = -2;
    private static final int SYNC_VALUE_14B_BE = 536864768;
    private static final int SYNC_VALUE_14B_LE = -14745368;
    private static final int SYNC_VALUE_BE = 2147385345;
    private static final int SYNC_VALUE_LE = -25230976;
    private static final int[] CHANNELS_BY_AMODE = {1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};
    private static final int[] SAMPLE_RATE_BY_SFREQ = {-1, 8000, 16000, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, 48000, -1, -1};
    private static final int[] TWICE_BITRATE_KBPS_BY_RATE = {64, 112, 128, PsExtractor.AUDIO_STREAM, 224, 256, 384, 448, 512, 640, 768, 896, UserMetadata.MAX_ATTRIBUTE_SIZE, 1152, 1280, 1536, 1920, 2048, 2304, 2560, 2688, 2816, 2823, 2944, 3072, 3840, 4096, 6144, 7680};

    private DtsUtil() {
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int getDtsFrameSize(byte[] bArr) {
        int i9;
        byte b9;
        int i10;
        int i11;
        byte b10;
        boolean z8 = false;
        byte b11 = bArr[0];
        if (b11 != -2) {
            if (b11 == -1) {
                i11 = ((bArr[7] & 3) << 12) | ((bArr[6] & 255) << 4);
                b10 = bArr[9];
            } else if (b11 != 31) {
                i9 = ((bArr[5] & 3) << 12) | ((bArr[6] & 255) << 4);
                b9 = bArr[7];
            } else {
                i11 = ((bArr[6] & 3) << 12) | ((bArr[7] & 255) << 4);
                b10 = bArr[8];
            }
            i10 = (((b10 & 60) >> 2) | i11) + 1;
            z8 = true;
            return !z8 ? (i10 * 16) / 14 : i10;
        }
        i9 = ((bArr[4] & 3) << 12) | ((bArr[7] & 255) << 4);
        b9 = bArr[6];
        i10 = (((b9 & 240) >> 4) | i9) + 1;
        if (!z8) {
        }
    }

    private static ParsableBitArray getNormalizedFrameHeader(byte[] bArr) {
        if (bArr[0] == 127) {
            return new ParsableBitArray(bArr);
        }
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        if (isLittleEndianFrameHeader(bArrCopyOf)) {
            for (int i9 = 0; i9 < bArrCopyOf.length - 1; i9 += 2) {
                byte b9 = bArrCopyOf[i9];
                int i10 = i9 + 1;
                bArrCopyOf[i9] = bArrCopyOf[i10];
                bArrCopyOf[i10] = b9;
            }
        }
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArrCopyOf);
        if (bArrCopyOf[0] == 31) {
            ParsableBitArray parsableBitArray2 = new ParsableBitArray(bArrCopyOf);
            while (parsableBitArray2.bitsLeft() >= 16) {
                parsableBitArray2.skipBits(2);
                parsableBitArray.putInt(parsableBitArray2.readBits(14), 14);
            }
        }
        parsableBitArray.reset(bArrCopyOf);
        return parsableBitArray;
    }

    private static boolean isLittleEndianFrameHeader(byte[] bArr) {
        byte b9 = bArr[0];
        return b9 == -2 || b9 == -1;
    }

    public static boolean isSyncWord(int i9) {
        return i9 == SYNC_VALUE_BE || i9 == SYNC_VALUE_LE || i9 == SYNC_VALUE_14B_BE || i9 == SYNC_VALUE_14B_LE;
    }

    public static int parseDtsAudioSampleCount(byte[] bArr) {
        int i9;
        byte b9;
        int i10;
        byte b10;
        byte b11 = bArr[0];
        if (b11 != -2) {
            if (b11 == -1) {
                i9 = (bArr[4] & 7) << 4;
                b10 = bArr[7];
            } else if (b11 != 31) {
                i9 = (bArr[4] & 1) << 6;
                b9 = bArr[5];
            } else {
                i9 = (bArr[5] & 7) << 4;
                b10 = bArr[6];
            }
            i10 = b10 & 60;
            return (((i10 >> 2) | i9) + 1) * 32;
        }
        i9 = (bArr[5] & 1) << 6;
        b9 = bArr[4];
        i10 = b9 & 252;
        return (((i10 >> 2) | i9) + 1) * 32;
    }

    public static Format parseDtsFormat(byte[] bArr, String str, String str2, DrmInitData drmInitData) {
        ParsableBitArray normalizedFrameHeader = getNormalizedFrameHeader(bArr);
        normalizedFrameHeader.skipBits(60);
        int i9 = CHANNELS_BY_AMODE[normalizedFrameHeader.readBits(6)];
        int i10 = SAMPLE_RATE_BY_SFREQ[normalizedFrameHeader.readBits(4)];
        int bits = normalizedFrameHeader.readBits(5);
        int[] iArr = TWICE_BITRATE_KBPS_BY_RATE;
        int i11 = bits >= iArr.length ? -1 : (iArr[bits] * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT) / 2;
        normalizedFrameHeader.skipBits(10);
        return Format.createAudioSampleFormat(str, MimeTypes.AUDIO_DTS, null, i11, -1, i9 + (normalizedFrameHeader.readBits(2) > 0 ? 1 : 0), i10, null, drmInitData, 0, str2);
    }

    public static int parseDtsAudioSampleCount(ByteBuffer byteBuffer) {
        int i9;
        byte b9;
        int i10;
        byte b10;
        int iPosition = byteBuffer.position();
        byte b11 = byteBuffer.get(iPosition);
        if (b11 != -2) {
            if (b11 == -1) {
                i9 = (byteBuffer.get(iPosition + 4) & 7) << 4;
                b10 = byteBuffer.get(iPosition + 7);
            } else if (b11 != 31) {
                i9 = (byteBuffer.get(iPosition + 4) & 1) << 6;
                b9 = byteBuffer.get(iPosition + 5);
            } else {
                i9 = (byteBuffer.get(iPosition + 5) & 7) << 4;
                b10 = byteBuffer.get(iPosition + 6);
            }
            i10 = b10 & 60;
            return (((i10 >> 2) | i9) + 1) * 32;
        }
        i9 = (byteBuffer.get(iPosition + 5) & 1) << 6;
        b9 = byteBuffer.get(iPosition + 4);
        i10 = b9 & 252;
        return (((i10 >> 2) | i9) + 1) * 32;
    }
}
