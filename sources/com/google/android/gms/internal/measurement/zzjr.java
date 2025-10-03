package com.google.android.gms.internal.measurement;

import com.google.firebase.messaging.Constants;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes2.dex */
final class zzjr {
    private static final zzjr zza = new zzjr();
    private final ConcurrentMap<Class<?>, zzjv<?>> zzc = new ConcurrentHashMap();
    private final zzjy zzb = new zzit();

    private zzjr() {
    }

    public static zzjr zza() {
        return zza;
    }

    public final <T> zzjv<T> zza(Class<T> cls) {
        zzhx.zza(cls, Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
        zzjv<T> zzjvVar = (zzjv) this.zzc.get(cls);
        if (zzjvVar != null) {
            return zzjvVar;
        }
        zzjv<T> zzjvVarZza = this.zzb.zza(cls);
        zzhx.zza(cls, Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
        zzhx.zza(zzjvVarZza, "schema");
        zzjv<T> zzjvVar2 = (zzjv) this.zzc.putIfAbsent(cls, zzjvVarZza);
        return zzjvVar2 != null ? zzjvVar2 : zzjvVarZza;
    }

    public final <T> zzjv<T> zza(T t8) {
        return zza((Class) t8.getClass());
    }
}
