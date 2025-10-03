package com.google.common.hash;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedBytes;

/* loaded from: classes2.dex */
final class FarmHashFingerprint64 extends AbstractNonStreamingHashFunction {
    static final HashFunction FARMHASH_FINGERPRINT_64 = new FarmHashFingerprint64();

    /* renamed from: K0 */
    private static final long f15423K0 = -4348849565147123417L;

    /* renamed from: K1 */
    private static final long f15424K1 = -5435081209227447693L;

    /* renamed from: K2 */
    private static final long f15425K2 = -7286425919675154353L;

    @VisibleForTesting
    public static long fingerprint(byte[] bArr, int i9, int i10) {
        return i10 <= 32 ? i10 <= 16 ? hashLength0to16(bArr, i9, i10) : hashLength17to32(bArr, i9, i10) : i10 <= 64 ? hashLength33To64(bArr, i9, i10) : hashLength65Plus(bArr, i9, i10);
    }

    private static long hashLength0to16(byte[] bArr, int i9, int i10) {
        if (i10 >= 8) {
            long j9 = (i10 * 2) + f15425K2;
            long jLoad64 = LittleEndianByteArray.load64(bArr, i9) + f15425K2;
            long jLoad642 = LittleEndianByteArray.load64(bArr, (i9 + i10) - 8);
            return hashLength16((Long.rotateRight(jLoad642, 37) * j9) + jLoad64, (Long.rotateRight(jLoad64, 25) + jLoad642) * j9, j9);
        }
        if (i10 >= 4) {
            return hashLength16(i10 + ((LittleEndianByteArray.load32(bArr, i9) & 4294967295L) << 3), LittleEndianByteArray.load32(bArr, (i9 + i10) - 4) & 4294967295L, (i10 * 2) + f15425K2);
        }
        if (i10 <= 0) {
            return f15425K2;
        }
        return shiftMix((((bArr[i9] & UnsignedBytes.MAX_VALUE) + ((bArr[(i10 >> 1) + i9] & UnsignedBytes.MAX_VALUE) << 8)) * f15425K2) ^ ((i10 + ((bArr[i9 + (i10 - 1)] & 255) << 2)) * f15423K0)) * f15425K2;
    }

    private static long hashLength16(long j9, long j10, long j11) {
        long j12 = (j9 ^ j10) * j11;
        long j13 = ((j12 ^ (j12 >>> 47)) ^ j10) * j11;
        return (j13 ^ (j13 >>> 47)) * j11;
    }

    private static long hashLength17to32(byte[] bArr, int i9, int i10) {
        long j9 = (i10 * 2) + f15425K2;
        long jLoad64 = LittleEndianByteArray.load64(bArr, i9) * f15424K1;
        long jLoad642 = LittleEndianByteArray.load64(bArr, i9 + 8);
        int i11 = i9 + i10;
        long jLoad643 = LittleEndianByteArray.load64(bArr, i11 - 8) * j9;
        return hashLength16((LittleEndianByteArray.load64(bArr, i11 - 16) * f15425K2) + Long.rotateRight(jLoad64 + jLoad642, 43) + Long.rotateRight(jLoad643, 30), jLoad64 + Long.rotateRight(jLoad642 + f15425K2, 18) + jLoad643, j9);
    }

    private static long hashLength33To64(byte[] bArr, int i9, int i10) {
        long j9 = (i10 * 2) + f15425K2;
        long jLoad64 = LittleEndianByteArray.load64(bArr, i9) * f15425K2;
        long jLoad642 = LittleEndianByteArray.load64(bArr, i9 + 8);
        int i11 = i9 + i10;
        long jLoad643 = LittleEndianByteArray.load64(bArr, i11 - 8) * j9;
        long jRotateRight = Long.rotateRight(jLoad64 + jLoad642, 43) + Long.rotateRight(jLoad643, 30) + (LittleEndianByteArray.load64(bArr, i11 - 16) * f15425K2);
        long jHashLength16 = hashLength16(jRotateRight, jLoad643 + Long.rotateRight(jLoad642 + f15425K2, 18) + jLoad64, j9);
        long jLoad644 = LittleEndianByteArray.load64(bArr, i9 + 16) * j9;
        long jLoad645 = LittleEndianByteArray.load64(bArr, i9 + 24);
        long jLoad646 = (jRotateRight + LittleEndianByteArray.load64(bArr, i11 - 32)) * j9;
        return hashLength16(((jHashLength16 + LittleEndianByteArray.load64(bArr, i11 - 24)) * j9) + Long.rotateRight(jLoad644 + jLoad645, 43) + Long.rotateRight(jLoad646, 30), jLoad644 + Long.rotateRight(jLoad645 + jLoad64, 18) + jLoad646, j9);
    }

    private static long hashLength65Plus(byte[] bArr, int i9, int i10) {
        long jShiftMix = shiftMix(-7956866745689871395L) * f15425K2;
        long[] jArr = new long[2];
        long[] jArr2 = new long[2];
        long jLoad64 = 95310865018149119L + LittleEndianByteArray.load64(bArr, i9);
        int i11 = i10 - 1;
        int i12 = i9 + ((i11 / 64) * 64);
        int i13 = i11 & 63;
        int i14 = (i12 + i13) - 63;
        long j9 = 2480279821605975764L;
        int i15 = i9;
        while (true) {
            long jRotateRight = Long.rotateRight(jLoad64 + j9 + jArr[0] + LittleEndianByteArray.load64(bArr, i15 + 8), 37) * f15424K1;
            long jRotateRight2 = Long.rotateRight(j9 + jArr[1] + LittleEndianByteArray.load64(bArr, i15 + 48), 42) * f15424K1;
            long j10 = jRotateRight ^ jArr2[1];
            long jLoad642 = jRotateRight2 + jArr[0] + LittleEndianByteArray.load64(bArr, i15 + 40);
            long jRotateRight3 = Long.rotateRight(jShiftMix + jArr2[0], 33) * f15424K1;
            weakHashLength32WithSeeds(bArr, i15, jArr[1] * f15424K1, j10 + jArr2[0], jArr);
            weakHashLength32WithSeeds(bArr, i15 + 32, jRotateRight3 + jArr2[1], jLoad642 + LittleEndianByteArray.load64(bArr, i15 + 16), jArr2);
            i15 += 64;
            if (i15 == i12) {
                long j11 = ((j10 & 255) << 1) + f15424K1;
                long j12 = jArr2[0] + i13;
                jArr2[0] = j12;
                long j13 = jArr[0] + j12;
                jArr[0] = j13;
                jArr2[0] = jArr2[0] + j13;
                long jRotateRight4 = Long.rotateRight(jRotateRight3 + jLoad642 + jArr[0] + LittleEndianByteArray.load64(bArr, i14 + 8), 37) * j11;
                long jRotateRight5 = Long.rotateRight(jLoad642 + jArr[1] + LittleEndianByteArray.load64(bArr, i14 + 48), 42) * j11;
                long j14 = jRotateRight4 ^ (jArr2[1] * 9);
                long jLoad643 = jRotateRight5 + (jArr[0] * 9) + LittleEndianByteArray.load64(bArr, i14 + 40);
                long jRotateRight6 = Long.rotateRight(j10 + jArr2[0], 33) * j11;
                weakHashLength32WithSeeds(bArr, i14, jArr[1] * j11, j14 + jArr2[0], jArr);
                weakHashLength32WithSeeds(bArr, i14 + 32, jRotateRight6 + jArr2[1], LittleEndianByteArray.load64(bArr, i14 + 16) + jLoad643, jArr2);
                return hashLength16(hashLength16(jArr[0], jArr2[0], j11) + (shiftMix(jLoad643) * f15423K0) + j14, hashLength16(jArr[1], jArr2[1], j11) + jRotateRight6, j11);
            }
            jShiftMix = j10;
            j9 = jLoad642;
            jLoad64 = jRotateRight3;
        }
    }

    private static long shiftMix(long j9) {
        return j9 ^ (j9 >>> 47);
    }

    private static void weakHashLength32WithSeeds(byte[] bArr, int i9, long j9, long j10, long[] jArr) {
        long jLoad64 = LittleEndianByteArray.load64(bArr, i9);
        long jLoad642 = LittleEndianByteArray.load64(bArr, i9 + 8);
        long jLoad643 = LittleEndianByteArray.load64(bArr, i9 + 16);
        long jLoad644 = LittleEndianByteArray.load64(bArr, i9 + 24);
        long j11 = j9 + jLoad64;
        long j12 = jLoad642 + j11 + jLoad643;
        long jRotateRight = Long.rotateRight(j10 + j11 + jLoad644, 21) + Long.rotateRight(j12, 44);
        jArr[0] = j12 + jLoad644;
        jArr[1] = jRotateRight + j11;
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 64;
    }

    @Override // com.google.common.hash.AbstractNonStreamingHashFunction, com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashBytes(byte[] bArr, int i9, int i10) {
        Preconditions.checkPositionIndexes(i9, i9 + i10, bArr.length);
        return HashCode.fromLong(fingerprint(bArr, i9, i10));
    }

    public String toString() {
        return "Hashing.farmHashFingerprint64()";
    }
}
