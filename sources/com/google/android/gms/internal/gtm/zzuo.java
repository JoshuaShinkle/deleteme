package com.google.android.gms.internal.gtm;

import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

/* loaded from: classes2.dex */
public final class zzuo {
    private final ByteBuffer zzawy;
    private zzqj zzbgz;
    private int zzbha;

    private zzuo(byte[] bArr, int i9, int i10) {
        this(ByteBuffer.wrap(bArr, i9, i10));
    }

    private final void zzab(long j9) throws zzup {
        while (((-128) & j9) != 0) {
            zzca((((int) j9) & 127) | 128);
            j9 >>>= 7;
        }
        zzca((int) j9);
    }

    public static int zzbb(int i9) {
        return zzbj(i9 << 3);
    }

    public static int zzbc(int i9) {
        if (i9 >= 0) {
            return zzbj(i9);
        }
        return 10;
    }

    public static int zzbj(int i9) {
        if ((i9 & (-128)) == 0) {
            return 1;
        }
        if ((i9 & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i9) == 0) {
            return 3;
        }
        return (i9 & (-268435456)) == 0 ? 4 : 5;
    }

    private final void zzca(int i9) throws zzup {
        byte b9 = (byte) i9;
        if (!this.zzawy.hasRemaining()) {
            throw new zzup(this.zzawy.position(), this.zzawy.limit());
        }
        this.zzawy.put(b9);
    }

    private static void zzd(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i9;
        int i10;
        char cCharAt;
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        int i11 = 0;
        if (!byteBuffer.hasArray()) {
            int length = charSequence.length();
            while (i11 < length) {
                char cCharAt2 = charSequence.charAt(i11);
                if (cCharAt2 < 128) {
                    byteBuffer.put((byte) cCharAt2);
                } else if (cCharAt2 < 2048) {
                    byteBuffer.put((byte) ((cCharAt2 >>> 6) | 960));
                    byteBuffer.put((byte) ((cCharAt2 & '?') | 128));
                } else {
                    if (cCharAt2 >= 55296 && 57343 >= cCharAt2) {
                        int i12 = i11 + 1;
                        if (i12 != charSequence.length()) {
                            char cCharAt3 = charSequence.charAt(i12);
                            if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                byteBuffer.put((byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                byteBuffer.put((byte) (((codePoint >>> 12) & 63) | 128));
                                byteBuffer.put((byte) (((codePoint >>> 6) & 63) | 128));
                                byteBuffer.put((byte) ((codePoint & 63) | 128));
                                i11 = i12;
                            } else {
                                i11 = i12;
                            }
                        }
                        StringBuilder sb = new StringBuilder(39);
                        sb.append("Unpaired surrogate at index ");
                        sb.append(i11 - 1);
                        throw new IllegalArgumentException(sb.toString());
                    }
                    byteBuffer.put((byte) ((cCharAt2 >>> '\f') | 480));
                    byteBuffer.put((byte) (((cCharAt2 >>> 6) & 63) | 128));
                    byteBuffer.put((byte) ((cCharAt2 & '?') | 128));
                }
                i11++;
            }
            return;
        }
        try {
            byte[] bArrArray = byteBuffer.array();
            int iArrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
            int iRemaining = byteBuffer.remaining();
            int length2 = charSequence.length();
            int i13 = iRemaining + iArrayOffset;
            while (i11 < length2) {
                int i14 = i11 + iArrayOffset;
                if (i14 >= i13 || (cCharAt = charSequence.charAt(i11)) >= 128) {
                    break;
                }
                bArrArray[i14] = (byte) cCharAt;
                i11++;
            }
            if (i11 == length2) {
                i9 = iArrayOffset + length2;
            } else {
                i9 = iArrayOffset + i11;
                while (i11 < length2) {
                    char cCharAt4 = charSequence.charAt(i11);
                    if (cCharAt4 >= 128 || i9 >= i13) {
                        if (cCharAt4 < 2048 && i9 <= i13 - 2) {
                            int i15 = i9 + 1;
                            bArrArray[i9] = (byte) ((cCharAt4 >>> 6) | 960);
                            i9 = i15 + 1;
                            bArrArray[i15] = (byte) ((cCharAt4 & '?') | 128);
                        } else {
                            if ((cCharAt4 >= 55296 && 57343 >= cCharAt4) || i9 > i13 - 3) {
                                if (i9 > i13 - 4) {
                                    StringBuilder sb2 = new StringBuilder(37);
                                    sb2.append("Failed writing ");
                                    sb2.append(cCharAt4);
                                    sb2.append(" at index ");
                                    sb2.append(i9);
                                    throw new ArrayIndexOutOfBoundsException(sb2.toString());
                                }
                                int i16 = i11 + 1;
                                if (i16 != charSequence.length()) {
                                    char cCharAt5 = charSequence.charAt(i16);
                                    if (Character.isSurrogatePair(cCharAt4, cCharAt5)) {
                                        int codePoint2 = Character.toCodePoint(cCharAt4, cCharAt5);
                                        int i17 = i9 + 1;
                                        bArrArray[i9] = (byte) ((codePoint2 >>> 18) | PsExtractor.VIDEO_STREAM_MASK);
                                        int i18 = i17 + 1;
                                        bArrArray[i17] = (byte) (((codePoint2 >>> 12) & 63) | 128);
                                        int i19 = i18 + 1;
                                        bArrArray[i18] = (byte) (((codePoint2 >>> 6) & 63) | 128);
                                        i9 = i19 + 1;
                                        bArrArray[i19] = (byte) ((codePoint2 & 63) | 128);
                                        i11 = i16;
                                    } else {
                                        i11 = i16;
                                    }
                                }
                                StringBuilder sb3 = new StringBuilder(39);
                                sb3.append("Unpaired surrogate at index ");
                                sb3.append(i11 - 1);
                                throw new IllegalArgumentException(sb3.toString());
                            }
                            int i20 = i9 + 1;
                            bArrArray[i9] = (byte) ((cCharAt4 >>> '\f') | 480);
                            int i21 = i20 + 1;
                            bArrArray[i20] = (byte) (((cCharAt4 >>> 6) & 63) | 128);
                            i10 = i21 + 1;
                            bArrArray[i21] = (byte) ((cCharAt4 & '?') | 128);
                        }
                        i11++;
                    } else {
                        i10 = i9 + 1;
                        bArrArray[i9] = (byte) cCharAt4;
                    }
                    i9 = i10;
                    i11++;
                }
            }
            byteBuffer.position(i9 - byteBuffer.arrayOffset());
        } catch (ArrayIndexOutOfBoundsException e9) {
            BufferOverflowException bufferOverflowException = new BufferOverflowException();
            bufferOverflowException.initCause(e9);
            throw bufferOverflowException;
        }
    }

    public static int zzda(String str) {
        int iZza = zza(str);
        return zzbj(iZza) + iZza;
    }

    public static zzuo zzk(byte[] bArr, int i9, int i10) {
        return new zzuo(bArr, 0, i10);
    }

    public static zzuo zzl(byte[] bArr) {
        return zzk(bArr, 0, bArr.length);
    }

    private final zzqj zzrw() {
        if (this.zzbgz == null) {
            this.zzbgz = zzqj.zza(this.zzawy);
            this.zzbha = this.zzawy.position();
        } else if (this.zzbha != this.zzawy.position()) {
            this.zzbgz.write(this.zzawy.array(), this.zzbha, this.zzawy.position() - this.zzbha);
            this.zzbha = this.zzawy.position();
        }
        return this.zzbgz;
    }

    public final void zza(int i9, String str) throws zzup {
        zzd(i9, 2);
        try {
            int iZzbj = zzbj(str.length());
            if (iZzbj != zzbj(str.length() * 3)) {
                zzcb(zza(str));
                zzd(str, this.zzawy);
                return;
            }
            int iPosition = this.zzawy.position();
            if (this.zzawy.remaining() < iZzbj) {
                throw new zzup(iPosition + iZzbj, this.zzawy.limit());
            }
            this.zzawy.position(iPosition + iZzbj);
            zzd(str, this.zzawy);
            int iPosition2 = this.zzawy.position();
            this.zzawy.position(iPosition);
            zzcb((iPosition2 - iPosition) - iZzbj);
            this.zzawy.position(iPosition2);
        } catch (BufferOverflowException e9) {
            zzup zzupVar = new zzup(this.zzawy.position(), this.zzawy.limit());
            zzupVar.initCause(e9);
            throw zzupVar;
        }
    }

    public final void zzb(int i9, boolean z8) throws zzup {
        zzd(i9, 0);
        byte b9 = z8 ? (byte) 1 : (byte) 0;
        if (!this.zzawy.hasRemaining()) {
            throw new zzup(this.zzawy.position(), this.zzawy.limit());
        }
        this.zzawy.put(b9);
    }

    public final void zzcb(int i9) throws zzup {
        while ((i9 & (-128)) != 0) {
            zzca((i9 & 127) | 128);
            i9 >>>= 7;
        }
        zzca(i9);
    }

    public final void zzcc(int i9) throws zzup {
        if (this.zzawy.remaining() < 4) {
            throw new zzup(this.zzawy.position(), this.zzawy.limit());
        }
        this.zzawy.putInt(i9);
    }

    public final void zze(int i9, int i10) throws zzup {
        zzd(i9, 0);
        if (i10 >= 0) {
            zzcb(i10);
        } else {
            zzab(i10);
        }
    }

    public final void zzi(int i9, long j9) throws zzup {
        zzd(i9, 0);
        zzab(j9);
    }

    public final void zzm(byte[] bArr) throws zzup {
        int length = bArr.length;
        if (this.zzawy.remaining() < length) {
            throw new zzup(this.zzawy.position(), this.zzawy.limit());
        }
        this.zzawy.put(bArr, 0, length);
    }

    public final void zzrx() {
        if (this.zzawy.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", Integer.valueOf(this.zzawy.remaining())));
        }
    }

    private zzuo(ByteBuffer byteBuffer) {
        this.zzawy = byteBuffer;
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    public static int zzi(int i9, int i10) {
        return zzbb(i9) + zzbc(i10);
    }

    public final void zze(int i9, zzsk zzskVar) {
        zzqj zzqjVarZzrw = zzrw();
        zzqjVarZzrw.zza(i9, zzskVar);
        zzqjVarZzrw.flush();
        this.zzbha = this.zzawy.position();
    }

    public final void zzb(zzuw zzuwVar) throws zzup {
        zzcb(zzuwVar.zzse());
        zzuwVar.zza(this);
    }

    public static int zzb(int i9, String str) {
        return zzbb(i9) + zzda(str);
    }

    public static int zzb(int i9, zzuw zzuwVar) {
        int iZzbb = zzbb(i9);
        int iZzpe = zzuwVar.zzpe();
        return iZzbb + zzbj(iZzpe) + iZzpe;
    }

    public final void zza(int i9, zzuw zzuwVar) throws zzup {
        zzd(i9, 2);
        zzb(zzuwVar);
    }

    private static int zza(CharSequence charSequence) {
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
                                StringBuilder sb = new StringBuilder(39);
                                sb.append("Unpaired surrogate at index ");
                                sb.append(i10);
                                throw new IllegalArgumentException(sb.toString());
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
        StringBuilder sb2 = new StringBuilder(54);
        sb2.append("UTF-8 length does not fit in int: ");
        sb2.append(i11 + 4294967296L);
        throw new IllegalArgumentException(sb2.toString());
    }

    public static int zzd(int i9, long j9) {
        return zzbb(i9) + (((-128) & j9) == 0 ? 1 : ((-16384) & j9) == 0 ? 2 : ((-2097152) & j9) == 0 ? 3 : ((-268435456) & j9) == 0 ? 4 : ((-34359738368L) & j9) == 0 ? 5 : ((-4398046511104L) & j9) == 0 ? 6 : ((-562949953421312L) & j9) == 0 ? 7 : ((-72057594037927936L) & j9) == 0 ? 8 : (j9 & Long.MIN_VALUE) == 0 ? 9 : 10);
    }

    public final void zzd(int i9, int i10) throws zzup {
        zzcb((i9 << 3) | i10);
    }
}
