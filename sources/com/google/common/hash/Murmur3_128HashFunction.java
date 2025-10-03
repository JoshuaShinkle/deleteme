package com.google.common.hash;

import com.google.common.primitives.UnsignedBytes;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes2.dex */
final class Murmur3_128HashFunction extends AbstractHashFunction implements Serializable {
    private static final long serialVersionUID = 0;
    private final int seed;
    static final HashFunction MURMUR3_128 = new Murmur3_128HashFunction(0);
    static final HashFunction GOOD_FAST_HASH_128 = new Murmur3_128HashFunction(Hashing.GOOD_FAST_HASH_SEED);

    public static final class Murmur3_128Hasher extends AbstractStreamingHasher {

        /* renamed from: C1 */
        private static final long f15426C1 = -8663945395140668459L;

        /* renamed from: C2 */
        private static final long f15427C2 = 5545529020109919103L;
        private static final int CHUNK_SIZE = 16;

        /* renamed from: h1 */
        private long f15428h1;

        /* renamed from: h2 */
        private long f15429h2;
        private int length;

        public Murmur3_128Hasher(int i9) {
            super(16);
            long j9 = i9;
            this.f15428h1 = j9;
            this.f15429h2 = j9;
            this.length = 0;
        }

        private void bmix64(long j9, long j10) {
            long jMixK1 = mixK1(j9) ^ this.f15428h1;
            this.f15428h1 = jMixK1;
            long jRotateLeft = Long.rotateLeft(jMixK1, 27);
            long j11 = this.f15429h2;
            this.f15428h1 = ((jRotateLeft + j11) * 5) + 1390208809;
            long jMixK2 = mixK2(j10) ^ j11;
            this.f15429h2 = jMixK2;
            this.f15429h2 = ((Long.rotateLeft(jMixK2, 31) + this.f15428h1) * 5) + 944331445;
        }

        private static long fmix64(long j9) {
            long j10 = (j9 ^ (j9 >>> 33)) * (-49064778989728563L);
            long j11 = (j10 ^ (j10 >>> 33)) * (-4265267296055464877L);
            return j11 ^ (j11 >>> 33);
        }

        private static long mixK1(long j9) {
            return Long.rotateLeft(j9 * f15426C1, 31) * f15427C2;
        }

        private static long mixK2(long j9) {
            return Long.rotateLeft(j9 * f15427C2, 33) * f15426C1;
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public HashCode makeHash() {
            long j9 = this.f15428h1;
            int i9 = this.length;
            long j10 = j9 ^ i9;
            long j11 = this.f15429h2 ^ i9;
            long j12 = j10 + j11;
            this.f15428h1 = j12;
            this.f15429h2 = j11 + j12;
            this.f15428h1 = fmix64(j12);
            long jFmix64 = fmix64(this.f15429h2);
            long j13 = this.f15428h1 + jFmix64;
            this.f15428h1 = j13;
            this.f15429h2 = jFmix64 + j13;
            return HashCode.fromBytesNoCopy(ByteBuffer.wrap(new byte[16]).order(ByteOrder.LITTLE_ENDIAN).putLong(this.f15428h1).putLong(this.f15429h2).array());
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public void process(ByteBuffer byteBuffer) {
            bmix64(byteBuffer.getLong(), byteBuffer.getLong());
            this.length += 16;
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public void processRemaining(ByteBuffer byteBuffer) {
            long j9;
            long j10;
            long j11;
            long j12;
            long j13;
            long j14;
            long j15;
            long j16;
            long j17;
            long j18;
            long j19;
            long j20;
            long j21;
            long j22;
            this.length += byteBuffer.remaining();
            long j23 = 0;
            switch (byteBuffer.remaining()) {
                case 1:
                    j9 = 0;
                    j15 = UnsignedBytes.toInt(byteBuffer.get(0)) ^ j9;
                    this.f15428h1 ^= mixK1(j15);
                    this.f15429h2 ^= mixK2(j23);
                    return;
                case 2:
                    j10 = 0;
                    j9 = j10 ^ (UnsignedBytes.toInt(byteBuffer.get(1)) << 8);
                    j15 = UnsignedBytes.toInt(byteBuffer.get(0)) ^ j9;
                    this.f15428h1 ^= mixK1(j15);
                    this.f15429h2 ^= mixK2(j23);
                    return;
                case 3:
                    j11 = 0;
                    j10 = j11 ^ (UnsignedBytes.toInt(byteBuffer.get(2)) << 16);
                    j9 = j10 ^ (UnsignedBytes.toInt(byteBuffer.get(1)) << 8);
                    j15 = UnsignedBytes.toInt(byteBuffer.get(0)) ^ j9;
                    this.f15428h1 ^= mixK1(j15);
                    this.f15429h2 ^= mixK2(j23);
                    return;
                case 4:
                    j12 = 0;
                    j11 = j12 ^ (UnsignedBytes.toInt(byteBuffer.get(3)) << 24);
                    j10 = j11 ^ (UnsignedBytes.toInt(byteBuffer.get(2)) << 16);
                    j9 = j10 ^ (UnsignedBytes.toInt(byteBuffer.get(1)) << 8);
                    j15 = UnsignedBytes.toInt(byteBuffer.get(0)) ^ j9;
                    this.f15428h1 ^= mixK1(j15);
                    this.f15429h2 ^= mixK2(j23);
                    return;
                case 5:
                    j13 = 0;
                    j12 = j13 ^ (UnsignedBytes.toInt(byteBuffer.get(4)) << 32);
                    j11 = j12 ^ (UnsignedBytes.toInt(byteBuffer.get(3)) << 24);
                    j10 = j11 ^ (UnsignedBytes.toInt(byteBuffer.get(2)) << 16);
                    j9 = j10 ^ (UnsignedBytes.toInt(byteBuffer.get(1)) << 8);
                    j15 = UnsignedBytes.toInt(byteBuffer.get(0)) ^ j9;
                    this.f15428h1 ^= mixK1(j15);
                    this.f15429h2 ^= mixK2(j23);
                    return;
                case 6:
                    j14 = 0;
                    j13 = j14 ^ (UnsignedBytes.toInt(byteBuffer.get(5)) << 40);
                    j12 = j13 ^ (UnsignedBytes.toInt(byteBuffer.get(4)) << 32);
                    j11 = j12 ^ (UnsignedBytes.toInt(byteBuffer.get(3)) << 24);
                    j10 = j11 ^ (UnsignedBytes.toInt(byteBuffer.get(2)) << 16);
                    j9 = j10 ^ (UnsignedBytes.toInt(byteBuffer.get(1)) << 8);
                    j15 = UnsignedBytes.toInt(byteBuffer.get(0)) ^ j9;
                    this.f15428h1 ^= mixK1(j15);
                    this.f15429h2 ^= mixK2(j23);
                    return;
                case 7:
                    j14 = (UnsignedBytes.toInt(byteBuffer.get(6)) << 48) ^ 0;
                    j13 = j14 ^ (UnsignedBytes.toInt(byteBuffer.get(5)) << 40);
                    j12 = j13 ^ (UnsignedBytes.toInt(byteBuffer.get(4)) << 32);
                    j11 = j12 ^ (UnsignedBytes.toInt(byteBuffer.get(3)) << 24);
                    j10 = j11 ^ (UnsignedBytes.toInt(byteBuffer.get(2)) << 16);
                    j9 = j10 ^ (UnsignedBytes.toInt(byteBuffer.get(1)) << 8);
                    j15 = UnsignedBytes.toInt(byteBuffer.get(0)) ^ j9;
                    this.f15428h1 ^= mixK1(j15);
                    this.f15429h2 ^= mixK2(j23);
                    return;
                case 8:
                    j16 = 0;
                    j15 = byteBuffer.getLong() ^ 0;
                    j23 = j16;
                    this.f15428h1 ^= mixK1(j15);
                    this.f15429h2 ^= mixK2(j23);
                    return;
                case 9:
                    j17 = 0;
                    j16 = j17 ^ UnsignedBytes.toInt(byteBuffer.get(8));
                    j15 = byteBuffer.getLong() ^ 0;
                    j23 = j16;
                    this.f15428h1 ^= mixK1(j15);
                    this.f15429h2 ^= mixK2(j23);
                    return;
                case 10:
                    j18 = 0;
                    j17 = j18 ^ (UnsignedBytes.toInt(byteBuffer.get(9)) << 8);
                    j16 = j17 ^ UnsignedBytes.toInt(byteBuffer.get(8));
                    j15 = byteBuffer.getLong() ^ 0;
                    j23 = j16;
                    this.f15428h1 ^= mixK1(j15);
                    this.f15429h2 ^= mixK2(j23);
                    return;
                case 11:
                    j19 = 0;
                    j18 = j19 ^ (UnsignedBytes.toInt(byteBuffer.get(10)) << 16);
                    j17 = j18 ^ (UnsignedBytes.toInt(byteBuffer.get(9)) << 8);
                    j16 = j17 ^ UnsignedBytes.toInt(byteBuffer.get(8));
                    j15 = byteBuffer.getLong() ^ 0;
                    j23 = j16;
                    this.f15428h1 ^= mixK1(j15);
                    this.f15429h2 ^= mixK2(j23);
                    return;
                case 12:
                    j20 = 0;
                    j19 = j20 ^ (UnsignedBytes.toInt(byteBuffer.get(11)) << 24);
                    j18 = j19 ^ (UnsignedBytes.toInt(byteBuffer.get(10)) << 16);
                    j17 = j18 ^ (UnsignedBytes.toInt(byteBuffer.get(9)) << 8);
                    j16 = j17 ^ UnsignedBytes.toInt(byteBuffer.get(8));
                    j15 = byteBuffer.getLong() ^ 0;
                    j23 = j16;
                    this.f15428h1 ^= mixK1(j15);
                    this.f15429h2 ^= mixK2(j23);
                    return;
                case 13:
                    j21 = 0;
                    j20 = j21 ^ (UnsignedBytes.toInt(byteBuffer.get(12)) << 32);
                    j19 = j20 ^ (UnsignedBytes.toInt(byteBuffer.get(11)) << 24);
                    j18 = j19 ^ (UnsignedBytes.toInt(byteBuffer.get(10)) << 16);
                    j17 = j18 ^ (UnsignedBytes.toInt(byteBuffer.get(9)) << 8);
                    j16 = j17 ^ UnsignedBytes.toInt(byteBuffer.get(8));
                    j15 = byteBuffer.getLong() ^ 0;
                    j23 = j16;
                    this.f15428h1 ^= mixK1(j15);
                    this.f15429h2 ^= mixK2(j23);
                    return;
                case 14:
                    j22 = 0;
                    j21 = j22 ^ (UnsignedBytes.toInt(byteBuffer.get(13)) << 40);
                    j20 = j21 ^ (UnsignedBytes.toInt(byteBuffer.get(12)) << 32);
                    j19 = j20 ^ (UnsignedBytes.toInt(byteBuffer.get(11)) << 24);
                    j18 = j19 ^ (UnsignedBytes.toInt(byteBuffer.get(10)) << 16);
                    j17 = j18 ^ (UnsignedBytes.toInt(byteBuffer.get(9)) << 8);
                    j16 = j17 ^ UnsignedBytes.toInt(byteBuffer.get(8));
                    j15 = byteBuffer.getLong() ^ 0;
                    j23 = j16;
                    this.f15428h1 ^= mixK1(j15);
                    this.f15429h2 ^= mixK2(j23);
                    return;
                case 15:
                    j22 = (UnsignedBytes.toInt(byteBuffer.get(14)) << 48) ^ 0;
                    j21 = j22 ^ (UnsignedBytes.toInt(byteBuffer.get(13)) << 40);
                    j20 = j21 ^ (UnsignedBytes.toInt(byteBuffer.get(12)) << 32);
                    j19 = j20 ^ (UnsignedBytes.toInt(byteBuffer.get(11)) << 24);
                    j18 = j19 ^ (UnsignedBytes.toInt(byteBuffer.get(10)) << 16);
                    j17 = j18 ^ (UnsignedBytes.toInt(byteBuffer.get(9)) << 8);
                    j16 = j17 ^ UnsignedBytes.toInt(byteBuffer.get(8));
                    j15 = byteBuffer.getLong() ^ 0;
                    j23 = j16;
                    this.f15428h1 ^= mixK1(j15);
                    this.f15429h2 ^= mixK2(j23);
                    return;
                default:
                    throw new AssertionError("Should never get here.");
            }
        }
    }

    public Murmur3_128HashFunction(int i9) {
        this.seed = i9;
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 128;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Murmur3_128HashFunction) && this.seed == ((Murmur3_128HashFunction) obj).seed;
    }

    public int hashCode() {
        return Murmur3_128HashFunction.class.hashCode() ^ this.seed;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new Murmur3_128Hasher(this.seed);
    }

    public String toString() {
        return "Hashing.murmur3_128(" + this.seed + ")";
    }
}
