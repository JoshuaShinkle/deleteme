package com.google.android.gms.common.internal;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

@KeepForSdk
/* loaded from: classes2.dex */
public final class Preconditions {
    private Preconditions() {
        throw new AssertionError("Uninstantiable");
    }

    @KeepForSdk
    public static void checkArgument(boolean z8) {
        if (!z8) {
            throw new IllegalArgumentException();
        }
    }

    @KeepForSdk
    public static void checkHandlerThread(Handler handler) {
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper != handler.getLooper()) {
            String name = looperMyLooper != null ? looperMyLooper.getThread().getName() : "null current looper";
            throw new IllegalStateException("Must be called on " + handler.getLooper().getThread().getName() + " thread, but got " + name + ".");
        }
    }

    @KeepForSdk
    public static void checkMainThread() {
        checkMainThread("Must be called on the main application thread");
    }

    @EnsuresNonNull({"#1"})
    @KeepForSdk
    public static String checkNotEmpty(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Given String is empty or null");
        }
        return str;
    }

    @KeepForSdk
    public static void checkNotMainThread() {
        checkNotMainThread("Must not be called on the main application thread");
    }

    @EnsuresNonNull({"#1"})
    @KeepForSdk
    public static <T> T checkNotNull(T t8) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException("null reference");
    }

    @KeepForSdk
    public static int checkNotZero(int i9) {
        if (i9 != 0) {
            return i9;
        }
        throw new IllegalArgumentException("Given Integer is zero");
    }

    @KeepForSdk
    public static void checkState(boolean z8) {
        if (!z8) {
            throw new IllegalStateException();
        }
    }

    @KeepForSdk
    public static void checkArgument(boolean z8, Object obj) {
        if (!z8) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    @KeepForSdk
    public static void checkMainThread(String str) {
        if (!com.google.android.gms.common.util.zzb.zza()) {
            throw new IllegalStateException(str);
        }
    }

    @KeepForSdk
    public static void checkNotMainThread(String str) {
        if (com.google.android.gms.common.util.zzb.zza()) {
            throw new IllegalStateException(str);
        }
    }

    @EnsuresNonNull({"#1"})
    @KeepForSdk
    public static <T> T checkNotNull(T t8, Object obj) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    @KeepForSdk
    public static int checkNotZero(int i9, Object obj) {
        if (i9 != 0) {
            return i9;
        }
        throw new IllegalArgumentException(String.valueOf(obj));
    }

    @KeepForSdk
    public static void checkState(boolean z8, Object obj) {
        if (!z8) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    @KeepForSdk
    public static void checkArgument(boolean z8, String str, Object... objArr) {
        if (!z8) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    @EnsuresNonNull({"#1"})
    @KeepForSdk
    public static String checkNotEmpty(String str, Object obj) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
        return str;
    }

    @KeepForSdk
    public static long checkNotZero(long j9) {
        if (j9 != 0) {
            return j9;
        }
        throw new IllegalArgumentException("Given Long is zero");
    }

    @KeepForSdk
    public static void checkState(boolean z8, String str, Object... objArr) {
        if (!z8) {
            throw new IllegalStateException(String.format(str, objArr));
        }
    }

    @KeepForSdk
    public static long checkNotZero(long j9, Object obj) {
        if (j9 != 0) {
            return j9;
        }
        throw new IllegalArgumentException(String.valueOf(obj));
    }

    @KeepForSdk
    public static void checkHandlerThread(Handler handler, String str) {
        if (Looper.myLooper() != handler.getLooper()) {
            throw new IllegalStateException(str);
        }
    }
}
