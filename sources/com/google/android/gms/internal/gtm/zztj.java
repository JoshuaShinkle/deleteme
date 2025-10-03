package com.google.android.gms.internal.gtm;

import java.util.Map;

/* loaded from: classes2.dex */
final class zztj implements Comparable, Map.Entry {
    private Object value;
    private final /* synthetic */ zztc zzbef;
    private final Comparable zzbei;

    public zztj(zztc zztcVar, Map.Entry entry) {
        this(zztcVar, (Comparable) entry.getKey(), entry.getValue());
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        return ((Comparable) getKey()).compareTo((Comparable) ((zztj) obj).getKey());
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return equals(this.zzbei, entry.getKey()) && equals(this.value, entry.getValue());
    }

    @Override // java.util.Map.Entry
    public final /* synthetic */ Object getKey() {
        return this.zzbei;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Comparable comparable = this.zzbei;
        int iHashCode = comparable == null ? 0 : comparable.hashCode();
        Object obj = this.value;
        return iHashCode ^ (obj != null ? obj.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        this.zzbef.zzrd();
        Object obj2 = this.value;
        this.value = obj;
        return obj2;
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.zzbei);
        String strValueOf2 = String.valueOf(this.value);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 1 + strValueOf2.length());
        sb.append(strValueOf);
        sb.append("=");
        sb.append(strValueOf2);
        return sb.toString();
    }

    public zztj(zztc zztcVar, Comparable comparable, Object obj) {
        this.zzbef = zztcVar;
        this.zzbei = comparable;
        this.value = obj;
    }

    private static boolean equals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }
}
