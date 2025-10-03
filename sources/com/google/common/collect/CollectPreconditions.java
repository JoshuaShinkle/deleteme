package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@GwtCompatible
/* loaded from: classes2.dex */
final class CollectPreconditions {
    public static void checkEntryNotNull(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("null key in entry: null=" + obj2);
        }
        if (obj2 != null) {
            return;
        }
        throw new NullPointerException("null value in entry: " + obj + "=null");
    }

    @CanIgnoreReturnValue
    public static int checkNonnegative(int i9, String str) {
        if (i9 >= 0) {
            return i9;
        }
        throw new IllegalArgumentException(str + " cannot be negative but was: " + i9);
    }

    public static void checkPositive(int i9, String str) {
        if (i9 > 0) {
            return;
        }
        throw new IllegalArgumentException(str + " must be positive but was: " + i9);
    }

    public static void checkRemove(boolean z8) {
        Preconditions.checkState(z8, "no calls to next() since the last call to remove()");
    }

    @CanIgnoreReturnValue
    public static long checkNonnegative(long j9, String str) {
        if (j9 >= 0) {
            return j9;
        }
        throw new IllegalArgumentException(str + " cannot be negative but was: " + j9);
    }
}
