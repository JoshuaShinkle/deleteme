package com.google.android.gms.internal.measurement;

import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.common.base.Ascii;

/* loaded from: classes2.dex */
final class zzla extends zzkx {
    @Override // com.google.android.gms.internal.measurement.zzkx
    public final int zza(int i9, byte[] bArr, int i10, int i11) {
        while (i10 < i11 && bArr[i10] >= 0) {
            i10++;
        }
        if (i10 >= i11) {
            return 0;
        }
        while (i10 < i11) {
            int i12 = i10 + 1;
            byte b9 = bArr[i10];
            if (b9 < 0) {
                if (b9 < -32) {
                    if (i12 >= i11) {
                        return b9;
                    }
                    if (b9 >= -62) {
                        i10 = i12 + 1;
                        if (bArr[i12] > -65) {
                        }
                    }
                    return -1;
                }
                if (b9 >= -16) {
                    if (i12 >= i11 - 2) {
                        return zzkw.zzd(bArr, i12, i11);
                    }
                    int i13 = i12 + 1;
                    byte b10 = bArr[i12];
                    if (b10 <= -65 && (((b9 << Ascii.f15383FS) + (b10 + 112)) >> 30) == 0) {
                        int i14 = i13 + 1;
                        if (bArr[i13] <= -65) {
                            i12 = i14 + 1;
                            if (bArr[i14] > -65) {
                            }
                        }
                    }
                    return -1;
                }
                if (i12 >= i11 - 1) {
                    return zzkw.zzd(bArr, i12, i11);
                }
                int i15 = i12 + 1;
                byte b11 = bArr[i12];
                if (b11 <= -65 && ((b9 != -32 || b11 >= -96) && (b9 != -19 || b11 < -96))) {
                    i10 = i15 + 1;
                    if (bArr[i15] > -65) {
                    }
                }
                return -1;
            }
            i10 = i12;
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzkx
    public final String zzb(byte[] bArr, int i9, int i10) throws zzig {
        if ((i9 | i10 | ((bArr.length - i9) - i10)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i9), Integer.valueOf(i10)));
        }
        int i11 = i9 + i10;
        char[] cArr = new char[i10];
        int i12 = 0;
        while (i9 < i11) {
            byte b9 = bArr[i9];
            if (!zzky.zzd(b9)) {
                break;
            }
            i9++;
            zzky.zzb(b9, cArr, i12);
            i12++;
        }
        int i13 = i12;
        while (i9 < i11) {
            int i14 = i9 + 1;
            byte b10 = bArr[i9];
            if (zzky.zzd(b10)) {
                int i15 = i13 + 1;
                zzky.zzb(b10, cArr, i13);
                while (i14 < i11) {
                    byte b11 = bArr[i14];
                    if (!zzky.zzd(b11)) {
                        break;
                    }
                    i14++;
                    zzky.zzb(b11, cArr, i15);
                    i15++;
                }
                i9 = i14;
                i13 = i15;
            } else if (zzky.zze(b10)) {
                if (i14 >= i11) {
                    throw zzig.zzh();
                }
                zzky.zzb(b10, bArr[i14], cArr, i13);
                i9 = i14 + 1;
                i13++;
            } else if (zzky.zzf(b10)) {
                if (i14 >= i11 - 1) {
                    throw zzig.zzh();
                }
                int i16 = i14 + 1;
                zzky.zzb(b10, bArr[i14], bArr[i16], cArr, i13);
                i9 = i16 + 1;
                i13++;
            } else {
                if (i14 >= i11 - 2) {
                    throw zzig.zzh();
                }
                int i17 = i14 + 1;
                byte b12 = bArr[i14];
                int i18 = i17 + 1;
                zzky.zzb(b10, b12, bArr[i17], bArr[i18], cArr, i13);
                i9 = i18 + 1;
                i13 = i13 + 1 + 1;
            }
        }
        return new String(cArr, 0, i13);
    }

    @Override // com.google.android.gms.internal.measurement.zzkx
    public final int zza(CharSequence charSequence, byte[] bArr, int i9, int i10) {
        int i11;
        int i12;
        int i13;
        char cCharAt;
        int length = charSequence.length();
        int i14 = i10 + i9;
        int i15 = 0;
        while (i15 < length && (i13 = i15 + i9) < i14 && (cCharAt = charSequence.charAt(i15)) < 128) {
            bArr[i13] = (byte) cCharAt;
            i15++;
        }
        if (i15 == length) {
            return i9 + length;
        }
        int i16 = i9 + i15;
        while (i15 < length) {
            char cCharAt2 = charSequence.charAt(i15);
            if (cCharAt2 >= 128 || i16 >= i14) {
                if (cCharAt2 < 2048 && i16 <= i14 - 2) {
                    int i17 = i16 + 1;
                    bArr[i16] = (byte) ((cCharAt2 >>> 6) | 960);
                    i16 = i17 + 1;
                    bArr[i17] = (byte) ((cCharAt2 & '?') | 128);
                } else {
                    if ((cCharAt2 >= 55296 && 57343 >= cCharAt2) || i16 > i14 - 3) {
                        if (i16 <= i14 - 4) {
                            int i18 = i15 + 1;
                            if (i18 != charSequence.length()) {
                                char cCharAt3 = charSequence.charAt(i18);
                                if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                    int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                    int i19 = i16 + 1;
                                    bArr[i16] = (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK);
                                    int i20 = i19 + 1;
                                    bArr[i19] = (byte) (((codePoint >>> 12) & 63) | 128);
                                    int i21 = i20 + 1;
                                    bArr[i20] = (byte) (((codePoint >>> 6) & 63) | 128);
                                    i16 = i21 + 1;
                                    bArr[i21] = (byte) ((codePoint & 63) | 128);
                                    i15 = i18;
                                } else {
                                    i15 = i18;
                                }
                            }
                            throw new zzkz(i15 - 1, length);
                        }
                        if (55296 <= cCharAt2 && cCharAt2 <= 57343 && ((i12 = i15 + 1) == charSequence.length() || !Character.isSurrogatePair(cCharAt2, charSequence.charAt(i12)))) {
                            throw new zzkz(i15, length);
                        }
                        StringBuilder sb = new StringBuilder(37);
                        sb.append("Failed writing ");
                        sb.append(cCharAt2);
                        sb.append(" at index ");
                        sb.append(i16);
                        throw new ArrayIndexOutOfBoundsException(sb.toString());
                    }
                    int i22 = i16 + 1;
                    bArr[i16] = (byte) ((cCharAt2 >>> '\f') | 480);
                    int i23 = i22 + 1;
                    bArr[i22] = (byte) (((cCharAt2 >>> 6) & 63) | 128);
                    i11 = i23 + 1;
                    bArr[i23] = (byte) ((cCharAt2 & '?') | 128);
                }
                i15++;
            } else {
                i11 = i16 + 1;
                bArr[i16] = (byte) cCharAt2;
            }
            i16 = i11;
            i15++;
        }
        return i16;
    }
}
