package com.google.android.gms.internal.gtm;

import com.google.firebase.messaging.Constants;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes2.dex */
final class zzsw {
    private static final zzsw zzbdr = new zzsw();
    private final ConcurrentMap<Class<?>, zzsz<?>> zzbdt = new ConcurrentHashMap();
    private final zzta zzbds = new zzrz();

    private zzsw() {
    }

    public static zzsw zzqs() {
        return zzbdr;
    }

    public final <T> zzsz<T> zzaf(T t8) {
        return zzi(t8.getClass());
    }

    public final <T> zzsz<T> zzi(Class<T> cls) {
        zzre.zza(cls, Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
        zzsz<T> zzszVar = (zzsz) this.zzbdt.get(cls);
        if (zzszVar != null) {
            return zzszVar;
        }
        zzsz<T> zzszVarZzh = this.zzbds.zzh(cls);
        zzre.zza(cls, Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
        zzre.zza(zzszVarZzh, "schema");
        zzsz<T> zzszVar2 = (zzsz) this.zzbdt.putIfAbsent(cls, zzszVarZzh);
        return zzszVar2 != null ? zzszVar2 : zzszVarZzh;
    }
}
