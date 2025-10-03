package com.google.android.gms.internal.play_billing;

import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.common.base.Ascii;

/* loaded from: classes2.dex */
final class zzev {
    public static final /* synthetic */ int zza = 0;
    private static final zzes zzb;

    static {
        if (zzeq.zzx() && zzeq.zzy()) {
            int i9 = zzam.zza;
        }
        zzb = new zzet();
    }

    public static /* bridge */ /* synthetic */ int zza(byte[] bArr, int i9, int i10) {
        int i11 = i10 - i9;
        byte b9 = bArr[i9 - 1];
        if (i11 != 0) {
            if (i11 == 1) {
                byte b10 = bArr[i9];
                if (b9 <= -12 && b10 <= -65) {
                    return b9 ^ (b10 << 8);
                }
            } else {
                if (i11 != 2) {
                    throw new AssertionError();
                }
                byte b11 = bArr[i9];
                byte b12 = bArr[i9 + 1];
                if (b9 <= -12 && b11 <= -65 && b12 <= -65) {
                    return ((b11 << 8) ^ b9) ^ (b12 << Ascii.DLE);
                }
            }
        } else if (b9 <= -12) {
            return b9;
        }
        return -1;
    }

    public static int zzb(CharSequence charSequence, byte[] bArr, int i9, int i10) {
        int i11;
        int i12;
        int i13;
        int i14;
        char cCharAt;
        int length = charSequence.length();
        int i15 = 0;
        while (true) {
            i11 = i9 + i10;
            if (i15 >= length || (i14 = i15 + i9) >= i11 || (cCharAt = charSequence.charAt(i15)) >= 128) {
                break;
            }
            bArr[i14] = (byte) cCharAt;
            i15++;
        }
        if (i15 == length) {
            return i9 + length;
        }
        int i16 = i9 + i15;
        while (i15 < length) {
            char cCharAt2 = charSequence.charAt(i15);
            if (cCharAt2 >= 128 || i16 >= i11) {
                if (cCharAt2 < 2048 && i16 <= i11 - 2) {
                    int i17 = i16 + 1;
                    bArr[i16] = (byte) ((cCharAt2 >>> 6) | 960);
                    i16 = i17 + 1;
                    bArr[i17] = (byte) ((cCharAt2 & '?') | 128);
                } else {
                    if ((cCharAt2 >= 55296 && cCharAt2 <= 57343) || i16 > i11 - 3) {
                        if (i16 > i11 - 4) {
                            if (cCharAt2 >= 55296 && cCharAt2 <= 57343 && ((i13 = i15 + 1) == charSequence.length() || !Character.isSurrogatePair(cCharAt2, charSequence.charAt(i13)))) {
                                throw new zzeu(i15, length);
                            }
                            throw new ArrayIndexOutOfBoundsException("Failed writing " + cCharAt2 + " at index " + i16);
                        }
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
                        throw new zzeu(i15 - 1, length);
                    }
                    int i22 = i16 + 1;
                    bArr[i16] = (byte) ((cCharAt2 >>> '\f') | 480);
                    int i23 = i22 + 1;
                    bArr[i22] = (byte) (((cCharAt2 >>> 6) & 63) | 128);
                    i12 = i23 + 1;
                    bArr[i23] = (byte) ((cCharAt2 & '?') | 128);
                }
                i15++;
            } else {
                i12 = i16 + 1;
                bArr[i16] = (byte) cCharAt2;
            }
            i16 = i12;
            i15++;
        }
        return i16;
    }

    public static int zzc(CharSequence charSequence) {
        int length = charSequence.length();
        int i9 = 0;
        int i10 = 0;
        while (i10 < length && charSequence.charAt(i10) < 128) {
            i10++;
        }
        int i11 = length;
        while (true) {
            if (i10 >= length) {
                break;
            }
            char cCharAt = charSequence.charAt(i10);
            if (cCharAt < 2048) {
                i11 += (127 - cCharAt) >>> 31;
                i10++;
            } else {
                int length2 = charSequence.length();
                while (i10 < length2) {
                    char cCharAt2 = charSequence.charAt(i10);
                    if (cCharAt2 < 2048) {
                        i9 += (127 - cCharAt2) >>> 31;
                    } else {
                        i9 += 2;
                        if (cCharAt2 >= 55296 && cCharAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i10) < 65536) {
                                throw new zzeu(i10, length2);
                            }
                            i10++;
                        }
                    }
                    i10++;
                }
                i11 += i9;
            }
        }
        if (i11 >= length) {
            return i11;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (i11 + 4294967296L));
    }

    public static boolean zzd(byte[] bArr) {
        return zzb.zzb(bArr, 0, bArr.length);
    }

    public static boolean zze(byte[] bArr, int i9, int i10) {
        return zzb.zzb(bArr, i9, i10);
    }
}
