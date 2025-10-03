package com.google.android.gms.internal.gtm;

import com.google.android.gms.common.config.GservicesValue;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;

@ShowFirstParty
@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzbz<V> {
    private final V zzaar;
    private final GservicesValue<V> zzaas;

    private zzbz(GservicesValue<V> gservicesValue, V v8) {
        Preconditions.checkNotNull(gservicesValue);
        this.zzaas = gservicesValue;
        this.zzaar = v8;
    }

    public static zzbz<Boolean> zza(String str, boolean z8, boolean z9) {
        return new zzbz<>(GservicesValue.value(str, z9), Boolean.valueOf(z8));
    }

    public final V get() {
        return this.zzaar;
    }

    public static zzbz<String> zza(String str, String str2, String str3) {
        return new zzbz<>(GservicesValue.value(str, str3), str2);
    }

    public static zzbz<Long> zza(String str, long j9, long j10) {
        return new zzbz<>(GservicesValue.value(str, Long.valueOf(j10)), Long.valueOf(j9));
    }

    public static zzbz<Integer> zza(String str, int i9, int i10) {
        return new zzbz<>(GservicesValue.value(str, Integer.valueOf(i10)), Integer.valueOf(i9));
    }

    public static zzbz<Float> zza(String str, float f9, float f10) {
        Float fValueOf = Float.valueOf(0.5f);
        return new zzbz<>(GservicesValue.value(str, fValueOf), fValueOf);
    }
}
