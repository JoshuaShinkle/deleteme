package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Looper;

/* loaded from: classes2.dex */
public final class zzx {
    private final boolean zza = false;

    public zzx(Context context) {
    }

    public static boolean zza() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
