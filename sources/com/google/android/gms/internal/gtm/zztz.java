package com.google.android.gms.internal.gtm;

import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
final class zztz {
    private static final zzub zzbfo;

    static {
        zzbfo = (!(zztx.zzrm() && zztx.zzrn()) || zzpp.zzna()) ? new zzuc() : new zzue();
    }

    public static int zza(CharSequence charSequence) {
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
                        if (55296 <= cCharAt2 && cCharAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i10) < 65536) {
                                throw new zzud(i10, length2);
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
        StringBuilder sb = new StringBuilder(54);
        sb.append("UTF-8 length does not fit in int: ");
        sb.append(i11 + 4294967296L);
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzbx(int i9) {
        if (i9 > -12) {
            return -1;
        }
        return i9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzc(int i9, int i10, int i11) {
        if (i9 > -12 || i10 > -65 || i11 > -65) {
            return -1;
        }
        return (i9 ^ (i10 << 8)) ^ (i11 << 16);
    }

    public static boolean zzf(byte[] bArr, int i9, int i10) {
        return zzbfo.zzf(bArr, i9, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzg(byte[] bArr, int i9, int i10) {
        byte b9 = bArr[i9 - 1];
        int i11 = i10 - i9;
        if (i11 == 0) {
            return zzbx(b9);
        }
        if (i11 == 1) {
            return zzr(b9, bArr[i9]);
        }
        if (i11 == 2) {
            return zzc(b9, bArr[i9], bArr[i9 + 1]);
        }
        throw new AssertionError();
    }

    public static String zzh(byte[] bArr, int i9, int i10) {
        return zzbfo.zzh(bArr, i9, i10);
    }

    public static boolean zzi(byte[] bArr) {
        return zzbfo.zzf(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzr(int i9, int i10) {
        if (i9 > -12 || i10 > -65) {
            return -1;
        }
        return i9 ^ (i10 << 8);
    }

    public static int zza(CharSequence charSequence, byte[] bArr, int i9, int i10) {
        return zzbfo.zzb(charSequence, bArr, i9, i10);
    }

    public static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        zzub zzubVar = zzbfo;
        if (byteBuffer.hasArray()) {
            int iArrayOffset = byteBuffer.arrayOffset();
            byteBuffer.position(zza(charSequence, byteBuffer.array(), byteBuffer.position() + iArrayOffset, byteBuffer.remaining()) - iArrayOffset);
        } else if (byteBuffer.isDirect()) {
            zzubVar.zzb(charSequence, byteBuffer);
        } else {
            zzub.zzc(charSequence, byteBuffer);
        }
    }
}
