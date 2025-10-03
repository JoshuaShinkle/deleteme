package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
final class SipHashFunction extends AbstractHashFunction implements Serializable {
    static final HashFunction SIP_HASH_24 = new SipHashFunction(2, 4, 506097522914230528L, 1084818905618843912L);
    private static final long serialVersionUID = 0;

    /* renamed from: c */
    private final int f15433c;

    /* renamed from: d */
    private final int f15434d;

    /* renamed from: k0 */
    private final long f15435k0;

    /* renamed from: k1 */
    private final long f15436k1;

    public static final class SipHasher extends AbstractStreamingHasher {
        private static final int CHUNK_SIZE = 8;

        /* renamed from: b */
        private long f15437b;

        /* renamed from: c */
        private final int f15438c;

        /* renamed from: d */
        private final int f15439d;
        private long finalM;

        /* renamed from: v0 */
        private long f15440v0;

        /* renamed from: v1 */
        private long f15441v1;

        /* renamed from: v2 */
        private long f15442v2;

        /* renamed from: v3 */
        private long f15443v3;

        public SipHasher(int i9, int i10, long j9, long j10) {
            super(8);
            this.f15437b = 0L;
            this.finalM = 0L;
            this.f15438c = i9;
            this.f15439d = i10;
            this.f15440v0 = 8317987319222330741L ^ j9;
            this.f15441v1 = 7237128888997146477L ^ j10;
            this.f15442v2 = 7816392313619706465L ^ j9;
            this.f15443v3 = 8387220255154660723L ^ j10;
        }

        private void processM(long j9) {
            this.f15443v3 ^= j9;
            sipRound(this.f15438c);
            this.f15440v0 = j9 ^ this.f15440v0;
        }

        private void sipRound(int i9) {
            for (int i10 = 0; i10 < i9; i10++) {
                long j9 = this.f15440v0;
                long j10 = this.f15441v1;
                this.f15440v0 = j9 + j10;
                this.f15442v2 += this.f15443v3;
                this.f15441v1 = Long.rotateLeft(j10, 13);
                long jRotateLeft = Long.rotateLeft(this.f15443v3, 16);
                long j11 = this.f15441v1;
                long j12 = this.f15440v0;
                this.f15441v1 = j11 ^ j12;
                this.f15443v3 = jRotateLeft ^ this.f15442v2;
                long jRotateLeft2 = Long.rotateLeft(j12, 32);
                long j13 = this.f15442v2;
                long j14 = this.f15441v1;
                this.f15442v2 = j13 + j14;
                this.f15440v0 = jRotateLeft2 + this.f15443v3;
                this.f15441v1 = Long.rotateLeft(j14, 17);
                long jRotateLeft3 = Long.rotateLeft(this.f15443v3, 21);
                long j15 = this.f15441v1;
                long j16 = this.f15442v2;
                this.f15441v1 = j15 ^ j16;
                this.f15443v3 = jRotateLeft3 ^ this.f15440v0;
                this.f15442v2 = Long.rotateLeft(j16, 32);
            }
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public HashCode makeHash() {
            long j9 = this.finalM ^ (this.f15437b << 56);
            this.finalM = j9;
            processM(j9);
            this.f15442v2 ^= 255;
            sipRound(this.f15439d);
            return HashCode.fromLong(((this.f15440v0 ^ this.f15441v1) ^ this.f15442v2) ^ this.f15443v3);
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public void process(ByteBuffer byteBuffer) {
            this.f15437b += 8;
            processM(byteBuffer.getLong());
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public void processRemaining(ByteBuffer byteBuffer) {
            this.f15437b += byteBuffer.remaining();
            int i9 = 0;
            while (byteBuffer.hasRemaining()) {
                this.finalM ^= (byteBuffer.get() & 255) << i9;
                i9 += 8;
            }
        }
    }

    public SipHashFunction(int i9, int i10, long j9, long j10) {
        Preconditions.checkArgument(i9 > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", i9);
        Preconditions.checkArgument(i10 > 0, "The number of SipRound iterations (d=%s) during Finalization must be positive.", i10);
        this.f15433c = i9;
        this.f15434d = i10;
        this.f15435k0 = j9;
        this.f15436k1 = j10;
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 64;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SipHashFunction)) {
            return false;
        }
        SipHashFunction sipHashFunction = (SipHashFunction) obj;
        return this.f15433c == sipHashFunction.f15433c && this.f15434d == sipHashFunction.f15434d && this.f15435k0 == sipHashFunction.f15435k0 && this.f15436k1 == sipHashFunction.f15436k1;
    }

    public int hashCode() {
        return (int) ((((SipHashFunction.class.hashCode() ^ this.f15433c) ^ this.f15434d) ^ this.f15435k0) ^ this.f15436k1);
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new SipHasher(this.f15433c, this.f15434d, this.f15435k0, this.f15436k1);
    }

    public String toString() {
        return "Hashing.sipHash" + this.f15433c + "" + this.f15434d + "(" + this.f15435k0 + ", " + this.f15436k1 + ")";
    }
}
