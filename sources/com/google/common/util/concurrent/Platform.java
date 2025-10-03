package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
final class Platform {
    private Platform() {
    }

    public static boolean isInstanceOfThrowableClass(Throwable th, Class<? extends Throwable> cls) {
        return cls.isInstance(th);
    }
}
