package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
final class zzef<T> implements zzeb<T>, Serializable {

    @NullableDecl
    private final T zza;

    public zzef(@NullableDecl T t8) {
        this.zza = t8;
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj instanceof zzef) {
            return zzdu.zza(this.zza, ((zzef) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza});
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.zza);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 22);
        sb.append("Suppliers.ofInstance(");
        sb.append(strValueOf);
        sb.append(")");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final T zza() {
        return this.zza;
    }
}
