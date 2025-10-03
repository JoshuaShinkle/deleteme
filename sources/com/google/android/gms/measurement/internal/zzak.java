package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;

/* loaded from: classes2.dex */
public final class zzak {
    final String zza;
    final String zzb;
    final long zzc;
    final long zzd;
    final zzam zze;
    private final String zzf;

    private zzak(zzgb zzgbVar, String str, String str2, String str3, long j9, long j10, zzam zzamVar) {
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzamVar);
        this.zza = str2;
        this.zzb = str3;
        this.zzf = TextUtils.isEmpty(str) ? null : str;
        this.zzc = j9;
        this.zzd = j10;
        if (j10 != 0 && j10 > j9) {
            zzgbVar.zzq().zzh().zza("Event created with reverse previous/current timestamps. appId, name", zzex.zza(str2), zzex.zza(str3));
        }
        this.zze = zzamVar;
    }

    public final String toString() {
        String str = this.zza;
        String str2 = this.zzb;
        String strValueOf = String.valueOf(this.zze);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 33 + String.valueOf(str2).length() + strValueOf.length());
        sb.append("Event{appId='");
        sb.append(str);
        sb.append("', name='");
        sb.append(str2);
        sb.append("', params=");
        sb.append(strValueOf);
        sb.append('}');
        return sb.toString();
    }

    public final zzak zza(zzgb zzgbVar, long j9) {
        return new zzak(zzgbVar, this.zzf, this.zza, this.zzb, this.zzc, j9, this.zze);
    }

    public zzak(zzgb zzgbVar, String str, String str2, String str3, long j9, long j10, Bundle bundle) {
        zzam zzamVar;
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        this.zza = str2;
        this.zzb = str3;
        this.zzf = TextUtils.isEmpty(str) ? null : str;
        this.zzc = j9;
        this.zzd = j10;
        if (j10 != 0 && j10 > j9) {
            zzgbVar.zzq().zzh().zza("Event created with reverse previous/current timestamps. appId", zzex.zza(str2));
        }
        if (bundle != null && !bundle.isEmpty()) {
            Bundle bundle2 = new Bundle(bundle);
            Iterator<String> it = bundle2.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next == null) {
                    zzgbVar.zzq().zze().zza("Param name can't be null");
                    it.remove();
                } else {
                    Object objZza = zzgbVar.zzh().zza(next, bundle2.get(next));
                    if (objZza == null) {
                        zzgbVar.zzq().zzh().zza("Param value can't be null", zzgbVar.zzi().zzb(next));
                        it.remove();
                    } else {
                        zzgbVar.zzh().zza(bundle2, next, objZza);
                    }
                }
            }
            zzamVar = new zzam(bundle2);
        } else {
            zzamVar = new zzam(new Bundle());
        }
        this.zze = zzamVar;
    }
}
