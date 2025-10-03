package com.google.android.gms.internal.measurement;

import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.common.base.Ascii;

/* loaded from: classes2.dex */
final class zzlc extends zzkx {
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0065, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x008f, code lost:
    
        return -1;
     */
    @Override // com.google.android.gms.internal.measurement.zzkx
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int zza(int i9, byte[] bArr, int i10, int i11) {
        int i12;
        if ((i10 | i11 | (bArr.length - i11)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", Integer.valueOf(bArr.length), Integer.valueOf(i10), Integer.valueOf(i11)));
        }
        long j9 = i10;
        int i13 = (int) (i11 - j9);
        if (i13 >= 16) {
            i12 = 0;
            long j10 = j9;
            while (true) {
                if (i12 >= i13) {
                    i12 = i13;
                    break;
                }
                long j11 = j10 + 1;
                if (zzkt.zza(bArr, j10) < 0) {
                    break;
                }
                i12++;
                j10 = j11;
            }
        } else {
            i12 = 0;
        }
        int i14 = i13 - i12;
        long j12 = j9 + i12;
        while (true) {
            byte b9 = 0;
            while (true) {
                if (i14 <= 0) {
                    break;
                }
                long j13 = j12 + 1;
                byte bZza = zzkt.zza(bArr, j12);
                if (bZza < 0) {
                    b9 = bZza;
                    j12 = j13;
                    break;
                }
                i14--;
                b9 = bZza;
                j12 = j13;
            }
            if (i14 != 0) {
                int i15 = i14 - 1;
                if (b9 >= -32) {
                    if (b9 >= -16) {
                        if (i15 >= 3) {
                            i14 = i15 - 3;
                            long j14 = j12 + 1;
                            byte bZza2 = zzkt.zza(bArr, j12);
                            if (bZza2 <= -65 && (((b9 << Ascii.f15383FS) + (bZza2 + 112)) >> 30) == 0) {
                                long j15 = j14 + 1;
                                if (zzkt.zza(bArr, j14) > -65) {
                                    break;
                                }
                                j12 = j15 + 1;
                                if (zzkt.zza(bArr, j15) > -65) {
                                    break;
                                }
                            } else {
                                break;
                            }
                        } else {
                            return zza(bArr, b9, j12, i15);
                        }
                    } else {
                        if (i15 < 2) {
                            return zza(bArr, b9, j12, i15);
                        }
                        i14 = i15 - 2;
                        long j16 = j12 + 1;
                        byte bZza3 = zzkt.zza(bArr, j12);
                        if (bZza3 > -65 || ((b9 == -32 && bZza3 < -96) || (b9 == -19 && bZza3 >= -96))) {
                            break;
                        }
                        j12 = j16 + 1;
                        if (zzkt.zza(bArr, j16) > -65) {
                            break;
                        }
                    }
                } else if (i15 != 0) {
                    i14 = i15 - 1;
                    if (b9 < -62) {
                        break;
                    }
                    long j17 = j12 + 1;
                    if (zzkt.zza(bArr, j12) > -65) {
                        break;
                    }
                    j12 = j17;
                } else {
                    return b9;
                }
            } else {
                return 0;
            }
        }
        return -1;
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
            byte bZza = zzkt.zza(bArr, i9);
            if (!zzky.zzd(bZza)) {
                break;
            }
            i9++;
            zzky.zzb(bZza, cArr, i12);
            i12++;
        }
        int i13 = i12;
        while (i9 < i11) {
            int i14 = i9 + 1;
            byte bZza2 = zzkt.zza(bArr, i9);
            if (zzky.zzd(bZza2)) {
                int i15 = i13 + 1;
                zzky.zzb(bZza2, cArr, i13);
                while (i14 < i11) {
                    byte bZza3 = zzkt.zza(bArr, i14);
                    if (!zzky.zzd(bZza3)) {
                        break;
                    }
                    i14++;
                    zzky.zzb(bZza3, cArr, i15);
                    i15++;
                }
                i9 = i14;
                i13 = i15;
            } else if (zzky.zze(bZza2)) {
                if (i14 >= i11) {
                    throw zzig.zzh();
                }
                zzky.zzb(bZza2, zzkt.zza(bArr, i14), cArr, i13);
                i9 = i14 + 1;
                i13++;
            } else if (zzky.zzf(bZza2)) {
                if (i14 >= i11 - 1) {
                    throw zzig.zzh();
                }
                int i16 = i14 + 1;
                zzky.zzb(bZza2, zzkt.zza(bArr, i14), zzkt.zza(bArr, i16), cArr, i13);
                i9 = i16 + 1;
                i13++;
            } else {
                if (i14 >= i11 - 2) {
                    throw zzig.zzh();
                }
                int i17 = i14 + 1;
                byte bZza4 = zzkt.zza(bArr, i14);
                int i18 = i17 + 1;
                zzky.zzb(bZza2, bZza4, zzkt.zza(bArr, i17), zzkt.zza(bArr, i18), cArr, i13);
                i9 = i18 + 1;
                i13 = i13 + 1 + 1;
            }
        }
        return new String(cArr, 0, i13);
    }

    @Override // com.google.android.gms.internal.measurement.zzkx
    public final int zza(CharSequence charSequence, byte[] bArr, int i9, int i10) {
        char c9;
        long j9;
        long j10;
        long j11;
        char c10;
        int i11;
        char cCharAt;
        long j12 = i9;
        long j13 = i10 + j12;
        int length = charSequence.length();
        if (length > i10 || bArr.length - i10 < i9) {
            char cCharAt2 = charSequence.charAt(length - 1);
            StringBuilder sb = new StringBuilder(37);
            sb.append("Failed writing ");
            sb.append(cCharAt2);
            sb.append(" at index ");
            sb.append(i9 + i10);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int i12 = 0;
        while (true) {
            c9 = 128;
            j9 = 1;
            if (i12 >= length || (cCharAt = charSequence.charAt(i12)) >= 128) {
                break;
            }
            zzkt.zza(bArr, j12, (byte) cCharAt);
            i12++;
            j12 = 1 + j12;
        }
        if (i12 == length) {
            return (int) j12;
        }
        while (i12 < length) {
            char cCharAt3 = charSequence.charAt(i12);
            if (cCharAt3 < c9 && j12 < j13) {
                long j14 = j12 + j9;
                zzkt.zza(bArr, j12, (byte) cCharAt3);
                j11 = j9;
                j10 = j14;
                c10 = c9;
            } else if (cCharAt3 < 2048 && j12 <= j13 - 2) {
                long j15 = j12 + j9;
                zzkt.zza(bArr, j12, (byte) ((cCharAt3 >>> 6) | 960));
                long j16 = j15 + j9;
                zzkt.zza(bArr, j15, (byte) ((cCharAt3 & '?') | 128));
                long j17 = j9;
                c10 = 128;
                j10 = j16;
                j11 = j17;
            } else {
                if ((cCharAt3 >= 55296 && 57343 >= cCharAt3) || j12 > j13 - 3) {
                    if (j12 <= j13 - 4) {
                        int i13 = i12 + 1;
                        if (i13 != length) {
                            char cCharAt4 = charSequence.charAt(i13);
                            if (Character.isSurrogatePair(cCharAt3, cCharAt4)) {
                                int codePoint = Character.toCodePoint(cCharAt3, cCharAt4);
                                long j18 = j12 + 1;
                                zzkt.zza(bArr, j12, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                long j19 = j18 + 1;
                                c10 = 128;
                                zzkt.zza(bArr, j18, (byte) (((codePoint >>> 12) & 63) | 128));
                                long j20 = j19 + 1;
                                zzkt.zza(bArr, j19, (byte) (((codePoint >>> 6) & 63) | 128));
                                j11 = 1;
                                j10 = j20 + 1;
                                zzkt.zza(bArr, j20, (byte) ((codePoint & 63) | 128));
                                i12 = i13;
                            } else {
                                i12 = i13;
                            }
                        }
                        throw new zzkz(i12 - 1, length);
                    }
                    if (55296 <= cCharAt3 && cCharAt3 <= 57343 && ((i11 = i12 + 1) == length || !Character.isSurrogatePair(cCharAt3, charSequence.charAt(i11)))) {
                        throw new zzkz(i12, length);
                    }
                    StringBuilder sb2 = new StringBuilder(46);
                    sb2.append("Failed writing ");
                    sb2.append(cCharAt3);
                    sb2.append(" at index ");
                    sb2.append(j12);
                    throw new ArrayIndexOutOfBoundsException(sb2.toString());
                }
                long j21 = j12 + j9;
                zzkt.zza(bArr, j12, (byte) ((cCharAt3 >>> '\f') | 480));
                long j22 = j21 + j9;
                zzkt.zza(bArr, j21, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                zzkt.zza(bArr, j22, (byte) ((cCharAt3 & '?') | 128));
                j10 = j22 + 1;
                j11 = 1;
                c10 = 128;
            }
            i12++;
            c9 = c10;
            long j23 = j11;
            j12 = j10;
            j9 = j23;
        }
        return (int) j12;
    }

    private static int zza(byte[] bArr, int i9, long j9, int i10) {
        if (i10 == 0) {
            return zzkw.zzb(i9);
        }
        if (i10 == 1) {
            return zzkw.zzb(i9, zzkt.zza(bArr, j9));
        }
        if (i10 == 2) {
            return zzkw.zzb(i9, zzkt.zza(bArr, j9), zzkt.zza(bArr, j9 + 1));
        }
        throw new AssertionError();
    }
}
