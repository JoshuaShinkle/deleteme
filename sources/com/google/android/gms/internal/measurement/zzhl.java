package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
final class zzhl {
    private static final zzhk<?> zza = new zzhm();
    private static final zzhk<?> zzb = zzc();

    public static zzhk<?> zza() {
        return zza;
    }

    public static zzhk<?> zzb() {
        zzhk<?> zzhkVar = zzb;
        if (zzhkVar != null) {
            return zzhkVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    private static zzhk<?> zzc() {
        try {
            return (zzhk) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
