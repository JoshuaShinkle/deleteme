package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
public final class zzea {
    public static <T> zzeb<T> zza(zzeb<T> zzebVar) {
        return ((zzebVar instanceof zzec) || (zzebVar instanceof zzed)) ? zzebVar : zzebVar instanceof Serializable ? new zzed(zzebVar) : new zzec(zzebVar);
    }

    public static <T> zzeb<T> zza(@NullableDecl T t8) {
        return new zzef(t8);
    }
}
