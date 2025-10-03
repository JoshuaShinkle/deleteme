package com.google.android.gms.internal.measurement;

import java.io.Serializable;

/* loaded from: classes2.dex */
public abstract class zzdx<T> implements Serializable {
    public static <T> zzdx<T> zza(T t8) {
        return new zzdz(zzdw.zza(t8));
    }

    public static <T> zzdx<T> zzc() {
        return zzdt.zza;
    }

    public abstract boolean zza();

    public abstract T zzb();
}
