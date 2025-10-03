package com.google.common.hash;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedBytes;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
final class Murmur3_32HashFunction extends AbstractHashFunction implements Serializable {

    /* renamed from: C1 */
    private static final int f15430C1 = -862048943;

    /* renamed from: C2 */
    private static final int f15431C2 = 461845907;
    private static final int CHUNK_SIZE = 4;
    private static final long serialVersionUID = 0;
    private final int seed;
    static final HashFunction MURMUR3_32 = new Murmur3_32HashFunction(0);
    static final HashFunction GOOD_FAST_HASH_32 = new Murmur3_32HashFunction(Hashing.GOOD_FAST_HASH_SEED);

    @CanIgnoreReturnValue
    public static final class Murmur3_32Hasher extends AbstractHasher {
        private long buffer;

        /* renamed from: h1 */
        private int f15432h1;
        private int shift;
        private int length = 0;
        private boolean isDone = false;

        public Murmur3_32Hasher(int i9) {
            this.f15432h1 = i9;
        }

        private void update(int i9, long j9) {
            long j10 = this.buffer;
            int i10 = this.shift;
            long j11 = ((j9 & 4294967295L) << i10) | j10;
            this.buffer = j11;
            int i11 = i10 + (i9 * 8);
            this.shift = i11;
            this.length += i9;
            if (i11 >= 32) {
                this.f15432h1 = Murmur3_32HashFunction.mixH1(this.f15432h1, Murmur3_32HashFunction.mixK1((int) j11));
                this.buffer >>>= 32;
                this.shift -= 32;
            }
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            Preconditions.checkState(!this.isDone);
            this.isDone = true;
            int iMixK1 = this.f15432h1 ^ Murmur3_32HashFunction.mixK1((int) this.buffer);
            this.f15432h1 = iMixK1;
            return Murmur3_32HashFunction.fmix(iMixK1, this.length);
        }

        @Override // com.google.common.hash.PrimitiveSink
        public Hasher putByte(byte b9) {
            update(1, b9 & UnsignedBytes.MAX_VALUE);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putChar(char c9) {
            update(2, c9);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putInt(int i9) {
            update(4, i9);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putLong(long j9) {
            update(4, (int) j9);
            update(4, j9 >>> 32);
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putString(CharSequence charSequence, Charset charset) {
            if (!Charsets.UTF_8.equals(charset)) {
                return super.putString(charSequence, charset);
            }
            int length = charSequence.length();
            int i9 = 0;
            while (true) {
                int i10 = i9 + 4;
                if (i10 > length) {
                    break;
                }
                char cCharAt = charSequence.charAt(i9);
                char cCharAt2 = charSequence.charAt(i9 + 1);
                char cCharAt3 = charSequence.charAt(i9 + 2);
                char cCharAt4 = charSequence.charAt(i9 + 3);
                if (cCharAt >= 128 || cCharAt2 >= 128 || cCharAt3 >= 128 || cCharAt4 >= 128) {
                    break;
                }
                update(4, (cCharAt2 << '\b') | cCharAt | (cCharAt3 << 16) | (cCharAt4 << 24));
                i9 = i10;
            }
            while (i9 < length) {
                char cCharAt5 = charSequence.charAt(i9);
                if (cCharAt5 < 128) {
                    update(1, cCharAt5);
                } else if (cCharAt5 < 2048) {
                    update(2, Murmur3_32HashFunction.charToTwoUtf8Bytes(cCharAt5));
                } else if (cCharAt5 < 55296 || cCharAt5 > 57343) {
                    update(3, Murmur3_32HashFunction.charToThreeUtf8Bytes(cCharAt5));
                } else {
                    int iCodePointAt = Character.codePointAt(charSequence, i9);
                    if (iCodePointAt == cCharAt5) {
                        putBytes(charSequence.subSequence(i9, length).toString().getBytes(charset));
                        return this;
                    }
                    i9++;
                    update(4, Murmur3_32HashFunction.codePointToFourUtf8Bytes(iCodePointAt));
                }
                i9++;
            }
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putBytes(byte[] bArr, int i9, int i10) {
            Preconditions.checkPositionIndexes(i9, i9 + i10, bArr.length);
            int i11 = 0;
            while (true) {
                int i12 = i11 + 4;
                if (i12 > i10) {
                    break;
                }
                update(4, Murmur3_32HashFunction.getIntLittleEndian(bArr, i11 + i9));
                i11 = i12;
            }
            while (i11 < i10) {
                putByte(bArr[i9 + i11]);
                i11++;
            }
            return this;
        }

        @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
        public Hasher putBytes(ByteBuffer byteBuffer) {
            ByteOrder byteOrderOrder = byteBuffer.order();
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            while (byteBuffer.remaining() >= 4) {
                putInt(byteBuffer.getInt());
            }
            while (byteBuffer.hasRemaining()) {
                putByte(byteBuffer.get());
            }
            byteBuffer.order(byteOrderOrder);
            return this;
        }
    }

    public Murmur3_32HashFunction(int i9) {
        this.seed = i9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long charToThreeUtf8Bytes(char c9) {
        return (((c9 & '?') | 128) << 16) | (((c9 >>> '\f') | 480) & 255) | ((((c9 >>> 6) & 63) | 128) << 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long charToTwoUtf8Bytes(char c9) {
        return (((c9 & '?') | 128) << 8) | (((c9 >>> 6) | 960) & 255);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long codePointToFourUtf8Bytes(int i9) {
        return (((i9 >>> 18) | 240) & 255) | ((((i9 >>> 12) & 63) | 128) << 8) | ((((i9 >>> 6) & 63) | 128) << 16) | (((i9 & 63) | 128) << 24);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HashCode fmix(int i9, int i10) {
        int i11 = i9 ^ i10;
        int i12 = (i11 ^ (i11 >>> 16)) * (-2048144789);
        int i13 = (i12 ^ (i12 >>> 13)) * (-1028477387);
        return HashCode.fromInt(i13 ^ (i13 >>> 16));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getIntLittleEndian(byte[] bArr, int i9) {
        return Ints.fromBytes(bArr[i9 + 3], bArr[i9 + 2], bArr[i9 + 1], bArr[i9]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int mixH1(int i9, int i10) {
        return (Integer.rotateLeft(i9 ^ i10, 13) * 5) - 430675100;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int mixK1(int i9) {
        return Integer.rotateLeft(i9 * f15430C1, 15) * f15431C2;
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 32;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Murmur3_32HashFunction) && this.seed == ((Murmur3_32HashFunction) obj).seed;
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashBytes(byte[] bArr, int i9, int i10) {
        Preconditions.checkPositionIndexes(i9, i9 + i10, bArr.length);
        int iMixH1 = this.seed;
        int i11 = 0;
        int i12 = 0;
        while (true) {
            int i13 = i12 + 4;
            if (i13 > i10) {
                break;
            }
            iMixH1 = mixH1(iMixH1, mixK1(getIntLittleEndian(bArr, i12 + i9)));
            i12 = i13;
        }
        int i14 = i12;
        int i15 = 0;
        while (i14 < i10) {
            i11 ^= UnsignedBytes.toInt(bArr[i9 + i14]) << i15;
            i14++;
            i15 += 8;
        }
        return fmix(mixK1(i11) ^ iMixH1, i10);
    }

    public int hashCode() {
        return Murmur3_32HashFunction.class.hashCode() ^ this.seed;
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashInt(int i9) {
        return fmix(mixH1(this.seed, mixK1(i9)), 4);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashLong(long j9) {
        return fmix(mixH1(mixH1(this.seed, mixK1((int) j9)), mixK1((int) (j9 >>> 32))), 8);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashString(CharSequence charSequence, Charset charset) {
        if (!Charsets.UTF_8.equals(charset)) {
            return hashBytes(charSequence.toString().getBytes(charset));
        }
        int length = charSequence.length();
        int iMixH1 = this.seed;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        while (true) {
            int i12 = i10 + 4;
            if (i12 > length) {
                break;
            }
            char cCharAt = charSequence.charAt(i10);
            char cCharAt2 = charSequence.charAt(i10 + 1);
            char cCharAt3 = charSequence.charAt(i10 + 2);
            char cCharAt4 = charSequence.charAt(i10 + 3);
            if (cCharAt >= 128 || cCharAt2 >= 128 || cCharAt3 >= 128 || cCharAt4 >= 128) {
                break;
            }
            iMixH1 = mixH1(iMixH1, mixK1((cCharAt2 << '\b') | cCharAt | (cCharAt3 << 16) | (cCharAt4 << 24)));
            i11 += 4;
            i10 = i12;
        }
        long jCharToThreeUtf8Bytes = 0;
        while (i10 < length) {
            char cCharAt5 = charSequence.charAt(i10);
            if (cCharAt5 < 128) {
                jCharToThreeUtf8Bytes |= cCharAt5 << i9;
                i9 += 8;
                i11++;
            } else if (cCharAt5 < 2048) {
                jCharToThreeUtf8Bytes |= charToTwoUtf8Bytes(cCharAt5) << i9;
                i9 += 16;
                i11 += 2;
            } else if (cCharAt5 < 55296 || cCharAt5 > 57343) {
                jCharToThreeUtf8Bytes |= charToThreeUtf8Bytes(cCharAt5) << i9;
                i9 += 24;
                i11 += 3;
            } else {
                int iCodePointAt = Character.codePointAt(charSequence, i10);
                if (iCodePointAt == cCharAt5) {
                    return hashBytes(charSequence.toString().getBytes(charset));
                }
                i10++;
                jCharToThreeUtf8Bytes |= codePointToFourUtf8Bytes(iCodePointAt) << i9;
                i11 += 4;
            }
            if (i9 >= 32) {
                iMixH1 = mixH1(iMixH1, mixK1((int) jCharToThreeUtf8Bytes));
                jCharToThreeUtf8Bytes >>>= 32;
                i9 -= 32;
            }
            i10++;
        }
        return fmix(mixK1((int) jCharToThreeUtf8Bytes) ^ iMixH1, i11);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashUnencodedChars(CharSequence charSequence) {
        int iMixK1 = this.seed;
        for (int i9 = 1; i9 < charSequence.length(); i9 += 2) {
            iMixK1 = mixH1(iMixK1, mixK1(charSequence.charAt(i9 - 1) | (charSequence.charAt(i9) << 16)));
        }
        if ((charSequence.length() & 1) == 1) {
            iMixK1 ^= mixK1(charSequence.charAt(charSequence.length() - 1));
        }
        return fmix(iMixK1, charSequence.length() * 2);
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new Murmur3_32Hasher(this.seed);
    }

    public String toString() {
        return "Hashing.murmur3_32(" + this.seed + ")";
    }
}
