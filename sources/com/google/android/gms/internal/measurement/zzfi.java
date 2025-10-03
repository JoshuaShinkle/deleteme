package com.google.android.gms.internal.measurement;

/* loaded from: classes2.dex */
public final class zzfi {
    public static Object zza(Object obj, int i9) {
        if (obj != null) {
            return obj;
        }
        StringBuilder sb = new StringBuilder(20);
        sb.append("at index ");
        sb.append(i9);
        throw new NullPointerException(sb.toString());
    }
}
