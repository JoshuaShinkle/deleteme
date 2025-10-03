package com.google.android.gms.internal.measurement;

import android.util.Log;

/* loaded from: classes2.dex */
final class zzdh extends zzdc<Long> {
    public zzdh(zzdl zzdlVar, String str, Long l9, boolean z8) {
        super(zzdlVar, str, l9, true, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.measurement.zzdc
    /* renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final Long zza(Object obj) {
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof String) {
            try {
                return Long.valueOf(Long.parseLong((String) obj));
            } catch (NumberFormatException unused) {
            }
        }
        String strZzb = super.zzb();
        String strValueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(strZzb).length() + 25 + strValueOf.length());
        sb.append("Invalid long value for ");
        sb.append(strZzb);
        sb.append(": ");
        sb.append(strValueOf);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
