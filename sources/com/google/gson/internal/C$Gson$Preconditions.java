package com.google.gson.internal;

/* renamed from: com.google.gson.internal.$Gson$Preconditions, reason: invalid class name */
/* loaded from: classes2.dex */
public final class C$Gson$Preconditions {
    private C$Gson$Preconditions() {
        throw new UnsupportedOperationException();
    }

    public static void checkArgument(boolean z8) {
        if (!z8) {
            throw new IllegalArgumentException();
        }
    }

    public static <T> T checkNotNull(T t8) {
        t8.getClass();
        return t8;
    }
}
