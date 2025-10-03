package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
final class zzed<T> implements zzeb<T>, Serializable {
    private final zzeb<T> zza;
    private volatile transient boolean zzb;

    @NullableDecl
    private transient T zzc;

    public zzed(zzeb<T> zzebVar) {
        this.zza = (zzeb) zzdw.zza(zzebVar);
    }

    public final String toString() {
        Object string;
        if (this.zzb) {
            String strValueOf = String.valueOf(this.zzc);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 25);
            sb.append("<supplier that returned ");
            sb.append(strValueOf);
            sb.append(">");
            string = sb.toString();
        } else {
            string = this.zza;
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
                    return tZza;
                }
            }
        }
        return this.zzc;
    }
}
