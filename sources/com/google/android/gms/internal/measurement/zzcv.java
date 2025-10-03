package com.google.android.gms.internal.measurement;

import android.os.Binder;

/* loaded from: classes2.dex */
public final /* synthetic */ class zzcv {
    public static <V> V zza(zzcu<V> zzcuVar) {
        try {
            return zzcuVar.zza();
        } catch (SecurityException unused) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                return zzcuVar.zza();
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }
}
