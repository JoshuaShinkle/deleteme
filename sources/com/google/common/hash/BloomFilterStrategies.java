package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.common.hash.BloomFilter;
import com.google.common.math.LongMath;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLongArray;

/* loaded from: classes2.dex */
enum BloomFilterStrategies implements BloomFilter.Strategy {
    MURMUR128_MITZ_32 { // from class: com.google.common.hash.BloomFilterStrategies.1
        @Override // com.google.common.hash.BloomFilter.Strategy
        public <T> boolean mightContain(T t8, Funnel<? super T> funnel, int i9, LockFreeBitArray lockFreeBitArray) {
            long jBitSize = lockFreeBitArray.bitSize();
            long jAsLong = Hashing.murmur3_128().hashObject(t8, funnel).asLong();
            int i10 = (int) jAsLong;
            int i11 = (int) (jAsLong >>> 32);
            for (int i12 = 1; i12 <= i9; i12++) {
                int i13 = (i12 * i11) + i10;
                if (i13 < 0) {
                    i13 = ~i13;
                }
                if (!lockFreeBitArray.get(i13 % jBitSize)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.hash.BloomFilter.Strategy
        public <T> boolean put(T t8, Funnel<? super T> funnel, int i9, LockFreeBitArray lockFreeBitArray) {
            long jBitSize = lockFreeBitArray.bitSize();
            long jAsLong = Hashing.murmur3_128().hashObject(t8, funnel).asLong();
            int i10 = (int) jAsLong;
            int i11 = (int) (jAsLong >>> 32);
            boolean z8 = false;
            for (int i12 = 1; i12 <= i9; i12++) {
                int i13 = (i12 * i11) + i10;
                if (i13 < 0) {
                    i13 = ~i13;
                }
                z8 |= lockFreeBitArray.set(i13 % jBitSize);
            }
            return z8;
        }
    },
    MURMUR128_MITZ_64 { // from class: com.google.common.hash.BloomFilterStrategies.2
        private long lowerEight(byte[] bArr) {
            return Longs.fromBytes(bArr[7], bArr[6], bArr[5], bArr[4], bArr[3], bArr[2], bArr[1], bArr[0]);
        }

        private long upperEight(byte[] bArr) {
            return Longs.fromBytes(bArr[15], bArr[14], bArr[13], bArr[12], bArr[11], bArr[10], bArr[9], bArr[8]);
        }

        @Override // com.google.common.hash.BloomFilter.Strategy
        public <T> boolean mightContain(T t8, Funnel<? super T> funnel, int i9, LockFreeBitArray lockFreeBitArray) {
            long jBitSize = lockFreeBitArray.bitSize();
            byte[] bytesInternal = Hashing.murmur3_128().hashObject(t8, funnel).getBytesInternal();
            long jLowerEight = lowerEight(bytesInternal);
            long jUpperEight = upperEight(bytesInternal);
            for (int i10 = 0; i10 < i9; i10++) {
                if (!lockFreeBitArray.get((Long.MAX_VALUE & jLowerEight) % jBitSize)) {
                    return false;
                }
                jLowerEight += jUpperEight;
            }
            return true;
        }

        @Override // com.google.common.hash.BloomFilter.Strategy
        public <T> boolean put(T t8, Funnel<? super T> funnel, int i9, LockFreeBitArray lockFreeBitArray) {
            long jBitSize = lockFreeBitArray.bitSize();
            byte[] bytesInternal = Hashing.murmur3_128().hashObject(t8, funnel).getBytesInternal();
            long jLowerEight = lowerEight(bytesInternal);
            long jUpperEight = upperEight(bytesInternal);
            boolean z8 = false;
            for (int i10 = 0; i10 < i9; i10++) {
                z8 |= lockFreeBitArray.set((Long.MAX_VALUE & jLowerEight) % jBitSize);
                jLowerEight += jUpperEight;
            }
            return z8;
        }
    };

    public static final class LockFreeBitArray {
        private static final int LONG_ADDRESSABLE_BITS = 6;
        private final LongAddable bitCount;
        final AtomicLongArray data;

        public LockFreeBitArray(long j9) {
            this(new long[Ints.checkedCast(LongMath.divide(j9, 64L, RoundingMode.CEILING))]);
        }

        public static long[] toPlainArray(AtomicLongArray atomicLongArray) {
            int length = atomicLongArray.length();
            long[] jArr = new long[length];
            for (int i9 = 0; i9 < length; i9++) {
                jArr[i9] = atomicLongArray.get(i9);
            }
            return jArr;
        }

        public long bitCount() {
            return this.bitCount.sum();
        }

        public long bitSize() {
            return this.data.length() * 64;
        }

        public LockFreeBitArray copy() {
            return new LockFreeBitArray(toPlainArray(this.data));
        }

        public boolean equals(Object obj) {
            if (obj instanceof LockFreeBitArray) {
                return Arrays.equals(toPlainArray(this.data), toPlainArray(((LockFreeBitArray) obj).data));
            }
            return false;
        }

        public boolean get(long j9) {
            return ((1 << ((int) j9)) & this.data.get((int) (j9 >>> 6))) != 0;
        }

        public int hashCode() {
            return Arrays.hashCode(toPlainArray(this.data));
        }

        public void putAll(LockFreeBitArray lockFreeBitArray) {
            long j9;
            long j10;
            boolean z8;
            Preconditions.checkArgument(this.data.length() == lockFreeBitArray.data.length(), "BitArrays must be of equal length (%s != %s)", this.data.length(), lockFreeBitArray.data.length());
            for (int i9 = 0; i9 < this.data.length(); i9++) {
                long j11 = lockFreeBitArray.data.get(i9);
                while (true) {
                    j9 = this.data.get(i9);
                    j10 = j9 | j11;
                    if (j9 != j10) {
                        if (this.data.compareAndSet(i9, j9, j10)) {
                            z8 = true;
                            break;
                        }
                    } else {
                        z8 = false;
                        break;
                    }
                }
                if (z8) {
                    this.bitCount.add(Long.bitCount(j10) - Long.bitCount(j9));
                }
            }
        }

        public boolean set(long j9) {
            long j10;
            long j11;
            if (get(j9)) {
                return false;
            }
            int i9 = (int) (j9 >>> 6);
            long j12 = 1 << ((int) j9);
            do {
                j10 = this.data.get(i9);
                j11 = j10 | j12;
                if (j10 == j11) {
                    return false;
                }
            } while (!this.data.compareAndSet(i9, j10, j11));
            this.bitCount.increment();
            return true;
        }

        public LockFreeBitArray(long[] jArr) {
            Preconditions.checkArgument(jArr.length > 0, "data length is zero!");
            this.data = new AtomicLongArray(jArr);
            this.bitCount = LongAddables.create();
            long jBitCount = 0;
            for (long j9 : jArr) {
                jBitCount += Long.bitCount(j9);
            }
            this.bitCount.add(jBitCount);
        }
    }
}
