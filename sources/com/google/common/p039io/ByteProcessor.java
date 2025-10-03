package com.google.common.p039io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public interface ByteProcessor<T> {
    T getResult();

    @CanIgnoreReturnValue
    boolean processBytes(byte[] bArr, int i9, int i10);
}
