package com.google.android.gms.internal.measurement;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
final class zzec<T> implements zzeb<T> {
    private volatile zzeb<T> zza;
    private volatile boolean zzb;

    @NullableDecl
    private T zzc;

    public zzec(zzeb<T> zzebVar) {
        this.zza = (zzeb) zzdw.zza(zzebVar);
    }

    public final String toString() {
        Object string = this.zza;
        if (string == null) {
            String strValueOf = String.valueOf(this.zzc);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 25);
            sb.append("<supplier that returned ");
            sb.append(strValueOf);
            sb.append(">");
            string = sb.toString();
        }
        String strValueOf2 = String.valueOf(string);
        StringBuilder sb2 = new StringBuilder(strValueOf2.length() + 19);
        sb2.append("Suppliers.memoize(");
        sb2.append(strValueOf2);
        sb2.append(")");
        return sb2.toString();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final T zza() {
        if (!this.zzb) {
            synchronized (this) {
                if (!this.zzb) {
                    T tZza = this.zza.zza();
                    this.zzc = tZza;
                    this.zzb = true;
                    this.zza = null;
                    return tZza;
                }
            }
        }
        return this.zzc;
    }
}
