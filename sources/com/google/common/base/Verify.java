package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@Beta
@GwtCompatible
/* loaded from: classes2.dex */
public final class Verify {
    private Verify() {
    }

    public static void verify(boolean z8) {
        if (!z8) {
            throw new VerifyException();
        }
    }

    @CanIgnoreReturnValue
    public static <T> T verifyNotNull(T t8) {
        return (T) verifyNotNull(t8, "expected a non-null reference", new Object[0]);
    }

    public static void verify(boolean z8, String str, Object... objArr) {
        if (!z8) {
            throw new VerifyException(Preconditions.format(str, objArr));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T verifyNotNull(T t8, String str, Object... objArr) {
        verify(t8 != null, str, objArr);
        return t8;
    }
}
