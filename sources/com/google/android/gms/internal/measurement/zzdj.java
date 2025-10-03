package com.google.android.gms.internal.measurement;

import android.util.Log;

/* loaded from: classes2.dex */
final class zzdj extends zzdc<Double> {
    public zzdj(zzdl zzdlVar, String str, Double d9, boolean z8) {
        super(zzdlVar, str, d9, true, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.measurement.zzdc
    /* renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final Double zza(Object obj) {
        if (obj instanceof Double) {
            return (Double) obj;
        }
        if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        }
        if (obj instanceof String) {
            try {
                return Double.valueOf(Double.parseDouble((String) obj));
            } catch (NumberFormatException unused) {
            }
        }
        String strZzb = super.zzb();
        String strValueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(strZzb).length() + 27 + strValueOf.length());
        sb.append("Invalid double value for ");
        sb.append(strZzb);
        sb.append(": ");
        sb.append(strValueOf);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
