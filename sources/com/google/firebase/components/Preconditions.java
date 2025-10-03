package com.google.firebase.components;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* loaded from: classes2.dex */
public final class Preconditions {
    public static void checkArgument(boolean z8, String str) {
        if (!z8) {
            throw new IllegalArgumentException(str);
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8) {
        t8.getClass();
        return t8;
    }

    public static void checkState(boolean z8, String str) {
        if (!z8) {
            throw new IllegalStateException(str);
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t8, String str) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(str);
    }
}
