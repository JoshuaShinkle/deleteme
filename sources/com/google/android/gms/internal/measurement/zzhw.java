package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzhv;

/* loaded from: classes2.dex */
final class zzhw implements zzjd {
    private static final zzhw zza = new zzhw();

    private zzhw() {
    }

    public static zzhw zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzjd
    public final zzje zzb(Class<?> cls) {
        if (!zzhv.class.isAssignableFrom(cls)) {
            String name = cls.getName();
            throw new IllegalArgumentException(name.length() != 0 ? "Unsupported message type: ".concat(name) : new String("Unsupported message type: "));
        }
        try {
            return (zzje) zzhv.zza(cls.asSubclass(zzhv.class)).zza(zzhv.zze.zzc, (Object) null, (Object) null);
        } catch (Exception e9) {
            String name2 = cls.getName();
            throw new RuntimeException(name2.length() != 0 ? "Unable to get message info for ".concat(name2) : new String("Unable to get message info for "), e9);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzjd
    public final boolean zza(Class<?> cls) {
        return zzhv.class.isAssignableFrom(cls);
    }
}
