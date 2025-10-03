package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzrc;

/* loaded from: classes2.dex */
final class zzrb implements zzsj {
    private static final zzrb zzbaj = new zzrb();

    private zzrb() {
    }

    public static zzrb zzpc() {
        return zzbaj;
    }

    @Override // com.google.android.gms.internal.gtm.zzsj
    public final boolean zze(Class<?> cls) {
        return zzrc.class.isAssignableFrom(cls);
    }

    @Override // com.google.android.gms.internal.gtm.zzsj
    public final zzsi zzf(Class<?> cls) {
        if (!zzrc.class.isAssignableFrom(cls)) {
            String name = cls.getName();
            throw new IllegalArgumentException(name.length() != 0 ? "Unsupported message type: ".concat(name) : new String("Unsupported message type: "));
        }
        try {
            return (zzsi) zzrc.zzg(cls.asSubclass(zzrc.class)).zza(zzrc.zze.zzbat, (Object) null, (Object) null);
        } catch (Exception e9) {
            String name2 = cls.getName();
            throw new RuntimeException(name2.length() != 0 ? "Unable to get message info for ".concat(name2) : new String("Unable to get message info for "), e9);
        }
    }
}
