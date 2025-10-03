package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@CanIgnoreReturnValue
/* loaded from: classes2.dex */
abstract class AbstractByteHasher extends AbstractHasher {
    private final ByteBuffer scratch = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);

    public abstract void update(byte b9);

    public void update(byte[] bArr) {
        update(bArr, 0, bArr.length);
    }

    @Override // com.google.common.hash.PrimitiveSink
    public Hasher putByte(byte b9) {
        update(b9);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
    public Hasher putChar(char c9) {
        this.scratch.putChar(c9);
        return update(2);
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
    public Hasher putInt(int i9) {
        this.scratch.putInt(i9);
        return update(4);
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
    public Hasher putLong(long j9) {
        this.scratch.putLong(j9);
        return update(8);
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
    public Hasher putShort(short s8) {
        this.scratch.putShort(s8);
        return update(2);
    }

    public void update(byte[] bArr, int i9, int i10) {
        for (int i11 = i9; i11 < i9 + i10; i11++) {
            update(bArr[i11]);
        }
    }

    public void update(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            update(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            byteBuffer.position(byteBuffer.limit());
        } else {
            for (int iRemaining = byteBuffer.remaining(); iRemaining > 0; iRemaining--) {
                update(byteBuffer.get());
            }
        }
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
    public Hasher putBytes(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        update(bArr);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
    public Hasher putBytes(byte[] bArr, int i9, int i10) {
        Preconditions.checkPositionIndexes(i9, i9 + i10, bArr.length);
        update(bArr, i9, i10);
        return this;
    }

    private Hasher update(int i9) {
        try {
            update(this.scratch.array(), 0, i9);
            return this;
        } finally {
            this.scratch.clear();
        }
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.PrimitiveSink
    public Hasher putBytes(ByteBuffer byteBuffer) {
        update(byteBuffer);
        return this;
    }
}
