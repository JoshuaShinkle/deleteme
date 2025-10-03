package com.google.common.hash;

import com.google.common.annotations.Beta;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@Beta
/* loaded from: classes2.dex */
public interface HashFunction {
    int bits();

    HashCode hashBytes(ByteBuffer byteBuffer);

    HashCode hashBytes(byte[] bArr);

    HashCode hashBytes(byte[] bArr, int i9, int i10);

    HashCode hashInt(int i9);

    HashCode hashLong(long j9);

    <T> HashCode hashObject(T t8, Funnel<? super T> funnel);

    HashCode hashString(CharSequence charSequence, Charset charset);

    HashCode hashUnencodedChars(CharSequence charSequence);

    Hasher newHasher();

    Hasher newHasher(int i9);
}
