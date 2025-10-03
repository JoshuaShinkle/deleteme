package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzmh;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
public final class zzev extends zzgx {
    private static final AtomicReference<String[]> zza = new AtomicReference<>();
    private static final AtomicReference<String[]> zzb = new AtomicReference<>();
    private static final AtomicReference<String[]> zzc = new AtomicReference<>();

    public zzev(zzgb zzgbVar) {
        super(zzgbVar);
    }

    private final boolean zzf() {
        return this.zzy.zzk() && this.zzy.zzq().zza(3);
    }

    public final String zza(String str) {
        if (str == null) {
            return null;
        }
        return !zzf() ? str : zza(str, zzgy.zzc, zzgy.zza, zza);
    }

    public final String zzb(String str) {
        if (str == null) {
            return null;
        }
        return !zzf() ? str : zza(str, zzhb.zzb, zzhb.zza, zzb);
    }

    public final String zzc(String str) {
        if (str == null) {
            return null;
        }
        if (!zzf()) {
            return str;
        }
        if (!str.startsWith("_exp_")) {
            return zza(str, zzha.zzb, zzha.zza, zzc);
        }
        return "experiment_id(" + str + ")";
    }

    @Override // com.google.android.gms.measurement.internal.zzgx
    public final boolean zzd() {
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzal zzk() {
        return super.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ Clock zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ Context zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzev zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzkx zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzfu zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzex zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzfj zzr() {
        return super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzy zzs() {
        return super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzx zzt() {
        return super.zzt();
    }

    private static String zza(String str, String[] strArr, String[] strArr2, AtomicReference<String[]> atomicReference) {
        String str2;
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        Preconditions.checkNotNull(atomicReference);
        Preconditions.checkArgument(strArr.length == strArr2.length);
        for (int i9 = 0; i9 < strArr.length; i9++) {
            if (zzkx.zzc(str, strArr[i9])) {
                synchronized (atomicReference) {
                    String[] strArr3 = atomicReference.get();
                    if (strArr3 == null) {
                        strArr3 = new String[strArr2.length];
                        atomicReference.set(strArr3);
                    }
                    if (strArr3[i9] == null) {
                        strArr3[i9] = strArr2[i9] + "(" + strArr[i9] + ")";
                    }
                    str2 = strArr3[i9];
                }
                return str2;
            }
        }
        return str;
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    public final String zza(zzar zzarVar) {
        String strZza = null;
        if (zzarVar == null) {
            return null;
        }
        if (!zzf()) {
            return zzarVar.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("origin=");
        sb.append(zzarVar.zzc);
        sb.append(",name=");
        sb.append(zza(zzarVar.zza));
        sb.append(",params=");
        zzam zzamVar = zzarVar.zzb;
        if (zzamVar != null) {
            if (!zzf()) {
                strZza = zzamVar.toString();
            } else {
                strZza = zza(zzamVar.zzb());
            }
        }
        sb.append(strZza);
        return sb.toString();
    }

    public final String zza(Bundle bundle) {
        String strValueOf;
        if (bundle == null) {
            return null;
        }
        if (!zzf()) {
            return bundle.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Bundle[{");
        for (String str : bundle.keySet()) {
            if (sb.length() != 8) {
                sb.append(", ");
            }
            sb.append(zzb(str));
            sb.append("=");
            if (zzmh.zzb() && zzs().zza(zzat.zzbx)) {
                Object obj = bundle.get(str);
                if (obj instanceof Bundle) {
                    strValueOf = zza(new Object[]{obj});
                } else if (obj instanceof Object[]) {
                    strValueOf = zza((Object[]) obj);
                } else if (obj instanceof ArrayList) {
                    strValueOf = zza(((ArrayList) obj).toArray());
                } else {
                    strValueOf = String.valueOf(obj);
                }
                sb.append(strValueOf);
            } else {
                sb.append(bundle.get(str));
            }
        }
        sb.append("}]");
        return sb.toString();
    }

    private final String zza(Object[] objArr) {
        String strValueOf;
        if (objArr == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object obj : objArr) {
            if (obj instanceof Bundle) {
                strValueOf = zza((Bundle) obj);
            } else {
                strValueOf = String.valueOf(obj);
            }
            if (strValueOf != null) {
                if (sb.length() != 1) {
                    sb.append(", ");
                }
                sb.append(strValueOf);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }
}
