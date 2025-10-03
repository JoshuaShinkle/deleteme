package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
final class zzjq {
    private static final zzjo zza = zzc();
    private static final zzjo zzb = new zzjn();

    public static zzjo zza() {
        return zza;
    }

    public static zzjo zzb() {
        return zzb;
    }

    private static zzjo zzc() {
        try {
            return (zzjo) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
