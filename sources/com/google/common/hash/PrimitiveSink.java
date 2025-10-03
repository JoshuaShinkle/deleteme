package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@CanIgnoreReturnValue
@Beta
/* loaded from: classes2.dex */
public interface PrimitiveSink {
    PrimitiveSink putBoolean(boolean z8);

    PrimitiveSink putByte(byte b9);

    PrimitiveSink putBytes(ByteBuffer byteBuffer);

    PrimitiveSink putBytes(byte[] bArr);

    PrimitiveSink putBytes(byte[] bArr, int i9, int i10);

    PrimitiveSink putChar(char c9);

    PrimitiveSink putDouble(double d9);

    PrimitiveSink putFloat(float f9);

    PrimitiveSink putInt(int i9);

    PrimitiveSink putLong(long j9);

    PrimitiveSink putShort(short s8);

    PrimitiveSink putString(CharSequence charSequence, Charset charset);

    PrimitiveSink putUnencodedChars(CharSequence charSequence);
}
