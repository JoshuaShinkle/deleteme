package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@CanIgnoreReturnValue
/* loaded from: classes2.dex */
abstract class AbstractHasher implements Hasher {
    @Override // com.google.common.hash.Hasher
    public <T> Hasher putObject(T t8, Funnel<? super T> funnel) {
        funnel.funnel(t8, this);
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public final Hasher putBoolean(boolean z8) {
        return putByte(z8 ? (byte) 1 : (byte) 0);
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putChar(char c9) {
        putByte((byte) c9);
        putByte((byte) (c9 >>> '\b'));
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public final Hasher putDouble(double d9) {
        return putLong(Double.doubleToRawLongBits(d9));
    }

    @Override // com.google.common.hash.PrimitiveSink
    public final Hasher putFloat(float f9) {
        return putInt(Float.floatToRawIntBits(f9));
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putInt(int i9) {
        putByte((byte) i9);
        putByte((byte) (i9 >>> 8));
        putByte((byte) (i9 >>> 16));
        putByte((byte) (i9 >>> 24));
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putLong(long j9) {
        for (int i9 = 0; i9 < 64; i9 += 8) {
            putByte((byte) (j9 >>> i9));
        }
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putShort(short s8) {
        putByte((byte) s8);
        putByte((byte) (s8 >>> 8));
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putString(CharSequence charSequence, Charset charset) {
        return putBytes(charSequence.toString().getBytes(charset));
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putUnencodedChars(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i9 = 0; i9 < length; i9++) {
            putChar(charSequence.charAt(i9));
        }
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putBytes(byte[] bArr) {
        return putBytes(bArr, 0, bArr.length);
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putBytes(byte[] bArr, int i9, int i10) {
        Preconditions.checkPositionIndexes(i9, i9 + i10, bArr.length);
        for (int i11 = 0; i11 < i10; i11++) {
            putByte(bArr[i9 + i11]);
        }
        return this;
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putBytes(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            putBytes(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            byteBuffer.position(byteBuffer.limit());
        } else {
            for (int iRemaining = byteBuffer.remaining(); iRemaining > 0; iRemaining--) {
                putByte(byteBuffer.get());
            }
        }
        return this;
    }
}
