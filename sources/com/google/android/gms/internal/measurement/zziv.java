package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
final class zziv implements zzjd {
    private zzjd[] zza;

    public zziv(zzjd... zzjdVarArr) {
        this.zza = zzjdVarArr;
    }

    @Override // com.google.android.gms.internal.measurement.zzjd
    public final boolean zza(Class<?> cls) {
        for (zzjd zzjdVar : this.zza) {
            if (zzjdVar.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzjd
    public final zzje zzb(Class<?> cls) {
        for (zzjd zzjdVar : this.zza) {
            if (zzjdVar.zza(cls)) {
                return zzjdVar.zzb(cls);
            }
        }
        String name = cls.getName();
        throw new UnsupportedOperationException(name.length() != 0 ? "No factory is available for message type: ".concat(name) : new String("No factory is available for message type: "));
    }
}
