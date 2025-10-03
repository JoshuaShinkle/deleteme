package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
abstract class AbstractHashFunction implements HashFunction {
    @Override // com.google.common.hash.HashFunction
    public HashCode hashBytes(byte[] bArr) {
        return hashBytes(bArr, 0, bArr.length);
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashInt(int i9) {
        return newHasher(4).putInt(i9).hash();
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashLong(long j9) {
        return newHasher(8).putLong(j9).hash();
    }

    @Override // com.google.common.hash.HashFunction
    public <T> HashCode hashObject(T t8, Funnel<? super T> funnel) {
        return newHasher().putObject(t8, funnel).hash();
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashString(CharSequence charSequence, Charset charset) {
        return newHasher().putString(charSequence, charset).hash();
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashUnencodedChars(CharSequence charSequence) {
        return newHasher(charSequence.length() * 2).putUnencodedChars(charSequence).hash();
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher(int i9) {
        Preconditions.checkArgument(i9 >= 0, "expectedInputSize must be >= 0 but was %s", i9);
        return newHasher();
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashBytes(byte[] bArr, int i9, int i10) {
        Preconditions.checkPositionIndexes(i9, i9 + i10, bArr.length);
        return newHasher(i10).putBytes(bArr, i9, i10).hash();
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashBytes(ByteBuffer byteBuffer) {
        return newHasher(byteBuffer.remaining()).putBytes(byteBuffer).hash();
    }
}
