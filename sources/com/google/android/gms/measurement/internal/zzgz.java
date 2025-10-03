package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* loaded from: classes2.dex */
public final class zzgz {
    public static void zza(Bundle bundle, Object obj) {
        if (obj instanceof Double) {
            bundle.putDouble("value", ((Double) obj).doubleValue());
        } else if (obj instanceof Long) {
            bundle.putLong("value", ((Long) obj).longValue());
        } else {
            bundle.putString("value", obj.toString());
        }
    }

    public static <T> T zza(Bundle bundle, String str, Class<T> cls, T t8) {
        T t9 = (T) bundle.get(str);
        if (t9 == null) {
            return t8;
        }
        if (cls.isAssignableFrom(t9.getClass())) {
            return t9;
        }
        throw new IllegalStateException(String.format("Invalid conditional user property field type. '%s' expected [%s] but was [%s]", str, cls.getCanonicalName(), t9.getClass().getCanonicalName()));
    }
}
