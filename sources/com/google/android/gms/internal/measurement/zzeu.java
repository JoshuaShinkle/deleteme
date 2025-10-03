package com.google.android.gms.internal.measurement;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
final class zzeu {
    public static int zza(int i9) {
        return (int) (Integer.rotateLeft((int) (i9 * (-862048943)), 15) * 461845907);
    }

    public static int zza(@NullableDecl Object obj) {
        return zza(obj == null ? 0 : obj.hashCode());
    }
}
