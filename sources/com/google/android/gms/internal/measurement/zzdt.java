package com.google.android.gms.internal.measurement;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
final class zzdt<T> extends zzdx<T> {
    static final zzdt<Object> zza = new zzdt<>();

    private zzdt() {
    }

    public final boolean equals(@NullableDecl Object obj) {
        return obj == this;
    }

    public final int hashCode() {
        return 2040732332;
    }

    public final String toString() {
        return "Optional.absent()";
    }

    @Override // com.google.android.gms.internal.measurement.zzdx
    public final boolean zza() {
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzdx
    public final T zzb() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }
}
