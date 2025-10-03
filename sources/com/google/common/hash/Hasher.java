package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@CanIgnoreReturnValue
@Beta
/* loaded from: classes2.dex */
public interface Hasher extends PrimitiveSink {
    HashCode hash();

    @Deprecated
    int hashCode();

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putBoolean(boolean z8);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putByte(byte b9);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putBytes(ByteBuffer byteBuffer);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putBytes(byte[] bArr);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putBytes(byte[] bArr, int i9, int i10);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putChar(char c9);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putDouble(double d9);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putFloat(float f9);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putInt(int i9);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putLong(long j9);

    <T> Hasher putObject(T t8, Funnel<? super T> funnel);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putShort(short s8);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putString(CharSequence charSequence, Charset charset);

    @Override // com.google.common.hash.PrimitiveSink
    Hasher putUnencodedChars(CharSequence charSequence);
}
