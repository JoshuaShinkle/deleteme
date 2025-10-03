package com.google.android.gms.internal.measurement;

import android.util.Log;

/* loaded from: classes2.dex */
final class zzdg extends zzdc<Boolean> {
    public zzdg(zzdl zzdlVar, String str, Boolean bool, boolean z8) {
        super(zzdlVar, str, bool, true, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzdc
    public final /* synthetic */ Boolean zza(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (zzck.zzb.matcher(str).matches()) {
                return Boolean.TRUE;
            }
            if (zzck.zzc.matcher(str).matches()) {
                return Boolean.FALSE;
            }
        }
        String strZzb = super.zzb();
        String strValueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(strZzb).length() + 28 + strValueOf.length());
        sb.append("Invalid boolean value for ");
        sb.append(strZzb);
        sb.append(": ");
        sb.append(strValueOf);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
