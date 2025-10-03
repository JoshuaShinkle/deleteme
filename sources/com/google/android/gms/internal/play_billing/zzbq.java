package com.google.android.gms.internal.play_billing;

/* loaded from: classes2.dex */
final class zzbq {
    private static final zzbo zza = new zzbp();
    private static final zzbo zzb;

    static {
        zzbo zzboVar;
        try {
            zzboVar = (zzbo) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzboVar = null;
        }
        zzb = zzboVar;
    }

    public static zzbo zza() {
        zzbo zzboVar = zzb;
        if (zzboVar != null) {
            return zzboVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    public static zzbo zzb() {
        return zza;
    }
}
