package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedBytes;
import com.google.common.primitives.UnsignedInts;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;

@Beta
/* loaded from: classes2.dex */
public abstract class HashCode {
    private static final char[] hexDigits = "0123456789abcdef".toCharArray();

    public static final class BytesHashCode extends HashCode implements Serializable {
        private static final long serialVersionUID = 0;
        final byte[] bytes;

        public BytesHashCode(byte[] bArr) {
            this.bytes = (byte[]) Preconditions.checkNotNull(bArr);
        }

        @Override // com.google.common.hash.HashCode
        public byte[] asBytes() {
            return (byte[]) this.bytes.clone();
        }

        @Override // com.google.common.hash.HashCode
        public int asInt() {
            byte[] bArr = this.bytes;
            Preconditions.checkState(bArr.length >= 4, "HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", bArr.length);
            byte[] bArr2 = this.bytes;
            return ((bArr2[3] & UnsignedBytes.MAX_VALUE) << 24) | (bArr2[0] & UnsignedBytes.MAX_VALUE) | ((bArr2[1] & UnsignedBytes.MAX_VALUE) << 8) | ((bArr2[2] & UnsignedBytes.MAX_VALUE) << 16);
        }

        @Override // com.google.common.hash.HashCode
        public long asLong() {
            byte[] bArr = this.bytes;
            Preconditions.checkState(bArr.length >= 8, "HashCode#asLong() requires >= 8 bytes (it only has %s bytes).", bArr.length);
            return padToLong();
        }

        @Override // com.google.common.hash.HashCode
        public int bits() {
            return this.bytes.length * 8;
        }

        @Override // com.google.common.hash.HashCode
        public boolean equalsSameBits(HashCode hashCode) {
            if (this.bytes.length != hashCode.getBytesInternal().length) {
                return false;
            }
            boolean z8 = true;
            int i9 = 0;
            while (true) {
                byte[] bArr = this.bytes;
                if (i9 >= bArr.length) {
                    return z8;
                }
                z8 &= bArr[i9] == hashCode.getBytesInternal()[i9];
                i9++;
            }
        }

        @Override // com.google.common.hash.HashCode
        public byte[] getBytesInternal() {
            return this.bytes;
        }

        @Override // com.google.common.hash.HashCode
        public long padToLong() {
            long j9 = this.bytes[0] & UnsignedBytes.MAX_VALUE;
            for (int i9 = 1; i9 < Math.min(this.bytes.length, 8); i9++) {
                j9 |= (this.bytes[i9] & 255) << (i9 * 8);
            }
            return j9;
        }

        @Override // com.google.common.hash.HashCode
        public void writeBytesToImpl(byte[] bArr, int i9, int i10) {
            System.arraycopy(this.bytes, 0, bArr, i9, i10);
        }
    }

    public static final class IntHashCode extends HashCode implements Serializable {
        private static final long serialVersionUID = 0;
        final int hash;

        public IntHashCode(int i9) {
            this.hash = i9;
        }

        @Override // com.google.common.hash.HashCode
        public byte[] asBytes() {
            int i9 = this.hash;
            return new byte[]{(byte) i9, (byte) (i9 >> 8), (byte) (i9 >> 16), (byte) (i9 >> 24)};
        }

        @Override // com.google.common.hash.HashCode
        public int asInt() {
            return this.hash;
        }

        @Override // com.google.common.hash.HashCode
        public long asLong() {
            throw new IllegalStateException("this HashCode only has 32 bits; cannot create a long");
        }

        @Override // com.google.common.hash.HashCode
        public int bits() {
            return 32;
        }

        @Override // com.google.common.hash.HashCode
        public boolean equalsSameBits(HashCode hashCode) {
            return this.hash == hashCode.asInt();
        }

        @Override // com.google.common.hash.HashCode
        public long padToLong() {
            return UnsignedInts.toLong(this.hash);
        }

        @Override // com.google.common.hash.HashCode
        public void writeBytesToImpl(byte[] bArr, int i9, int i10) {
            for (int i11 = 0; i11 < i10; i11++) {
                bArr[i9 + i11] = (byte) (this.hash >> (i11 * 8));
            }
        }
    }

    public static final class LongHashCode extends HashCode implements Serializable {
        private static final long serialVersionUID = 0;
        final long hash;

        public LongHashCode(long j9) {
            this.hash = j9;
        }

        @Override // com.google.common.hash.HashCode
        public byte[] asBytes() {
            return new byte[]{(byte) this.hash, (byte) (r2 >> 8), (byte) (r2 >> 16), (byte) (r2 >> 24), (byte) (r2 >> 32), (byte) (r2 >> 40), (byte) (r2 >> 48), (byte) (r2 >> 56)};
        }

        @Override // com.google.common.hash.HashCode
        public int asInt() {
            return (int) this.hash;
        }

        @Override // com.google.common.hash.HashCode
        public long asLong() {
            return this.hash;
        }

        @Override // com.google.common.hash.HashCode
        public int bits() {
            return 64;
        }

        @Override // com.google.common.hash.HashCode
        public boolean equalsSameBits(HashCode hashCode) {
            return this.hash == hashCode.asLong();
        }

        @Override // com.google.common.hash.HashCode
        public long padToLong() {
            return this.hash;
        }

        @Override // com.google.common.hash.HashCode
        public void writeBytesToImpl(byte[] bArr, int i9, int i10) {
            for (int i11 = 0; i11 < i10; i11++) {
                bArr[i9 + i11] = (byte) (this.hash >> (i11 * 8));
            }
        }
    }

    private static int decode(char c9) {
        if (c9 >= '0' && c9 <= '9') {
            return c9 - '0';
        }
        if (c9 >= 'a' && c9 <= 'f') {
            return (c9 - 'a') + 10;
        }
        throw new IllegalArgumentException("Illegal hexadecimal character: " + c9);
    }

    public static HashCode fromBytes(byte[] bArr) {
        Preconditions.checkArgument(bArr.length >= 1, "A HashCode must contain at least 1 byte.");
        return fromBytesNoCopy((byte[]) bArr.clone());
    }

    public static HashCode fromBytesNoCopy(byte[] bArr) {
        return new BytesHashCode(bArr);
    }

    public static HashCode fromInt(int i9) {
        return new IntHashCode(i9);
    }

    public static HashCode fromLong(long j9) {
        return new LongHashCode(j9);
    }

    public static HashCode fromString(String str) {
        Preconditions.checkArgument(str.length() >= 2, "input string (%s) must have at least 2 characters", str);
        Preconditions.checkArgument(str.length() % 2 == 0, "input string (%s) must have an even number of characters", str);
        byte[] bArr = new byte[str.length() / 2];
        for (int i9 = 0; i9 < str.length(); i9 += 2) {
            bArr[i9 / 2] = (byte) ((decode(str.charAt(i9)) << 4) + decode(str.charAt(i9 + 1)));
        }
        return fromBytesNoCopy(bArr);
    }

    public abstract byte[] asBytes();

    public abstract int asInt();

    public abstract long asLong();

    public abstract int bits();

    public final boolean equals(Object obj) {
        if (!(obj instanceof HashCode)) {
            return false;
        }
        HashCode hashCode = (HashCode) obj;
        return bits() == hashCode.bits() && equalsSameBits(hashCode);
    }

    public abstract boolean equalsSameBits(HashCode hashCode);

    public byte[] getBytesInternal() {
        return asBytes();
    }

    public final int hashCode() {
        if (bits() >= 32) {
            return asInt();
        }
        byte[] bytesInternal = getBytesInternal();
        int i9 = bytesInternal[0] & UnsignedBytes.MAX_VALUE;
        for (int i10 = 1; i10 < bytesInternal.length; i10++) {
            i9 |= (bytesInternal[i10] & UnsignedBytes.MAX_VALUE) << (i10 * 8);
        }
        return i9;
    }

    public abstract long padToLong();

    public final String toString() {
        byte[] bytesInternal = getBytesInternal();
        StringBuilder sb = new StringBuilder(bytesInternal.length * 2);
        for (byte b9 : bytesInternal) {
            char[] cArr = hexDigits;
            sb.append(cArr[(b9 >> 4) & 15]);
            sb.append(cArr[b9 & Ascii.f15389SI]);
        }
        return sb.toString();
    }

    @CanIgnoreReturnValue
    public int writeBytesTo(byte[] bArr, int i9, int i10) {
        int iMin = Ints.min(i10, bits() / 8);
        Preconditions.checkPositionIndexes(i9, i9 + iMin, bArr.length);
        writeBytesToImpl(bArr, i9, iMin);
        return iMin;
    }

    public abstract void writeBytesToImpl(byte[] bArr, int i9, int i10);
}
