package com.google.android.gms.internal.gtm;

import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
abstract class zzub {
    public static void zzc(CharSequence charSequence, ByteBuffer byteBuffer) {
        int length = charSequence.length();
        int iPosition = byteBuffer.position();
        int i9 = 0;
        while (i9 < length) {
            try {
                char cCharAt = charSequence.charAt(i9);
                if (cCharAt >= 128) {
                    break;
                }
                byteBuffer.put(iPosition + i9, (byte) cCharAt);
                i9++;
            } catch (IndexOutOfBoundsException unused) {
                int iPosition2 = byteBuffer.position() + Math.max(i9, (iPosition - byteBuffer.position()) + 1);
                char cCharAt2 = charSequence.charAt(i9);
                StringBuilder sb = new StringBuilder(37);
                sb.append("Failed writing ");
                sb.append(cCharAt2);
                sb.append(" at index ");
                sb.append(iPosition2);
                throw new ArrayIndexOutOfBoundsException(sb.toString());
            }
        }
        if (i9 == length) {
            byteBuffer.position(iPosition + i9);
            return;
        }
        iPosition += i9;
        while (i9 < length) {
            char cCharAt3 = charSequence.charAt(i9);
            if (cCharAt3 < 128) {
                byteBuffer.put(iPosition, (byte) cCharAt3);
            } else if (cCharAt3 < 2048) {
                int i10 = iPosition + 1;
                try {
                    byteBuffer.put(iPosition, (byte) ((cCharAt3 >>> 6) | PsExtractor.AUDIO_STREAM));
                    byteBuffer.put(i10, (byte) ((cCharAt3 & '?') | 128));
                    iPosition = i10;
                } catch (IndexOutOfBoundsException unused2) {
                    iPosition = i10;
                    int iPosition22 = byteBuffer.position() + Math.max(i9, (iPosition - byteBuffer.position()) + 1);
                    char cCharAt22 = charSequence.charAt(i9);
                    StringBuilder sb2 = new StringBuilder(37);
                    sb2.append("Failed writing ");
                    sb2.append(cCharAt22);
                    sb2.append(" at index ");
                    sb2.append(iPosition22);
                    throw new ArrayIndexOutOfBoundsException(sb2.toString());
                }
            } else {
                if (cCharAt3 >= 55296 && 57343 >= cCharAt3) {
                    int i11 = i9 + 1;
                    if (i11 != length) {
                        try {
                            char cCharAt4 = charSequence.charAt(i11);
                            if (Character.isSurrogatePair(cCharAt3, cCharAt4)) {
                                int codePoint = Character.toCodePoint(cCharAt3, cCharAt4);
                                int i12 = iPosition + 1;
                                try {
                                    byteBuffer.put(iPosition, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                    int i13 = i12 + 1;
                                    byteBuffer.put(i12, (byte) (((codePoint >>> 12) & 63) | 128));
                                    int i14 = i13 + 1;
                                    byteBuffer.put(i13, (byte) (((codePoint >>> 6) & 63) | 128));
                                    byteBuffer.put(i14, (byte) ((codePoint & 63) | 128));
                                    iPosition = i14;
                                    i9 = i11;
                                } catch (IndexOutOfBoundsException unused3) {
                                    iPosition = i12;
                                    i9 = i11;
                                    int iPosition222 = byteBuffer.position() + Math.max(i9, (iPosition - byteBuffer.position()) + 1);
                                    char cCharAt222 = charSequence.charAt(i9);
                                    StringBuilder sb22 = new StringBuilder(37);
                                    sb22.append("Failed writing ");
                                    sb22.append(cCharAt222);
                                    sb22.append(" at index ");
                                    sb22.append(iPosition222);
                                    throw new ArrayIndexOutOfBoundsException(sb22.toString());
                                }
                            } else {
                                i9 = i11;
                            }
                        } catch (IndexOutOfBoundsException unused4) {
                        }
                    }
                    throw new zzud(i9, length);
                }
                int i15 = iPosition + 1;
                byteBuffer.put(iPosition, (byte) ((cCharAt3 >>> '\f') | 224));
                iPosition = i15 + 1;
                byteBuffer.put(i15, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                byteBuffer.put(iPosition, (byte) ((cCharAt3 & '?') | 128));
            }
            i9++;
            iPosition++;
        }
        byteBuffer.position(iPosition);
    }

    public abstract int zzb(int i9, byte[] bArr, int i10, int i11);

    public abstract int zzb(CharSequence charSequence, byte[] bArr, int i9, int i10);

    public abstract void zzb(CharSequence charSequence, ByteBuffer byteBuffer);

    public final boolean zzf(byte[] bArr, int i9, int i10) {
        return zzb(0, bArr, i9, i10) == 0;
    }

    public abstract String zzh(byte[] bArr, int i9, int i10);
}
