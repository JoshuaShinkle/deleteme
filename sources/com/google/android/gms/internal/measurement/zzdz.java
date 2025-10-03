package com.google.android.gms.internal.measurement;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
final class zzdz<T> extends zzdx<T> {
    private final T zza;

    public zzdz(T t8) {
        this.zza = t8;
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj instanceof zzdz) {
            return this.zza.equals(((zzdz) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode() + 1502476572;
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.zza);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 13);
        sb.append("Optional.of(");
        sb.append(strValueOf);
        sb.append(")");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.measurement.zzdx
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzdx
    public final T zzb() {
        return this.zza;
    }
}
