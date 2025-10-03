package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzhv;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzhh implements zzlk {
    private final zzhf zza;

    private zzhh(zzhf zzhfVar) {
        zzhf zzhfVar2 = (zzhf) zzhx.zza(zzhfVar, "output");
        this.zza = zzhfVar2;
        zzhfVar2.zza = this;
    }

    public static zzhh zza(zzhf zzhfVar) {
        zzhh zzhhVar = zzhfVar.zza;
        return zzhhVar != null ? zzhhVar : new zzhh(zzhfVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzb(int i9, long j9) {
        this.zza.zzc(i9, j9);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzc(int i9, long j9) {
        this.zza.zza(i9, j9);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzd(int i9, long j9) {
        this.zza.zzc(i9, j9);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zze(int i9, int i10) {
        this.zza.zzc(i9, i10);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzf(int i9, int i10) {
        this.zza.zzd(i9, i10);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzg(int i9, List<Double> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zza(i9, list.get(i10).doubleValue());
                i10++;
            }
            return;
        }
        this.zza.zza(i9, 2);
        int iZzb = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzb += zzhf.zzb(list.get(i11).doubleValue());
        }
        this.zza.zzb(iZzb);
        while (i10 < list.size()) {
            this.zza.zza(list.get(i10).doubleValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzh(int i9, List<Integer> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zzb(i9, list.get(i10).intValue());
                i10++;
            }
            return;
        }
        this.zza.zza(i9, 2);
        int iZzk = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzk += zzhf.zzk(list.get(i11).intValue());
        }
        this.zza.zzb(iZzk);
        while (i10 < list.size()) {
            this.zza.zza(list.get(i10).intValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzi(int i9, List<Boolean> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zza(i9, list.get(i10).booleanValue());
                i10++;
            }
            return;
        }
        this.zza.zza(i9, 2);
        int iZzb = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzb += zzhf.zzb(list.get(i11).booleanValue());
        }
        this.zza.zzb(iZzb);
        while (i10 < list.size()) {
            this.zza.zza(list.get(i10).booleanValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzj(int i9, List<Integer> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zzc(i9, list.get(i10).intValue());
                i10++;
            }
            return;
        }
        this.zza.zza(i9, 2);
        int iZzg = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzg += zzhf.zzg(list.get(i11).intValue());
        }
        this.zza.zzb(iZzg);
        while (i10 < list.size()) {
            this.zza.zzb(list.get(i10).intValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzk(int i9, List<Integer> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zze(i9, list.get(i10).intValue());
                i10++;
            }
            return;
        }
        this.zza.zza(i9, 2);
        int iZzj = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzj += zzhf.zzj(list.get(i11).intValue());
        }
        this.zza.zzb(iZzj);
        while (i10 < list.size()) {
            this.zza.zzd(list.get(i10).intValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzl(int i9, List<Long> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zzc(i9, list.get(i10).longValue());
                i10++;
            }
            return;
        }
        this.zza.zza(i9, 2);
        int iZzh = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzh += zzhf.zzh(list.get(i11).longValue());
        }
        this.zza.zzb(iZzh);
        while (i10 < list.size()) {
            this.zza.zzc(list.get(i10).longValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzm(int i9, List<Integer> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zzd(i9, list.get(i10).intValue());
                i10++;
            }
            return;
        }
        this.zza.zza(i9, 2);
        int iZzh = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzh += zzhf.zzh(list.get(i11).intValue());
        }
        this.zza.zzb(iZzh);
        while (i10 < list.size()) {
            this.zza.zzc(list.get(i10).intValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzn(int i9, List<Long> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zzb(i9, list.get(i10).longValue());
                i10++;
            }
            return;
        }
        this.zza.zza(i9, 2);
        int iZzf = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzf += zzhf.zzf(list.get(i11).longValue());
        }
        this.zza.zzb(iZzf);
        while (i10 < list.size()) {
            this.zza.zzb(list.get(i10).longValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzc(int i9, int i10) {
        this.zza.zzb(i9, i10);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzd(int i9, int i10) {
        this.zza.zze(i9, i10);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zze(int i9, long j9) {
        this.zza.zzb(i9, j9);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzf(int i9, List<Float> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zza(i9, list.get(i10).floatValue());
                i10++;
            }
            return;
        }
        this.zza.zza(i9, 2);
        int iZzb = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzb += zzhf.zzb(list.get(i11).floatValue());
        }
        this.zza.zzb(iZzb);
        while (i10 < list.size()) {
            this.zza.zza(list.get(i10).floatValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final int zza() {
        return zzhv.zze.zzj;
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzb(int i9, int i10) {
        this.zza.zzb(i9, i10);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzc(int i9, List<Long> list, boolean z8) {
        int i10 = 0;
        if (z8) {
            this.zza.zza(i9, 2);
            int iZzd = 0;
            for (int i11 = 0; i11 < list.size(); i11++) {
                iZzd += zzhf.zzd(list.get(i11).longValue());
            }
            this.zza.zzb(iZzd);
            while (i10 < list.size()) {
                this.zza.zza(list.get(i10).longValue());
                i10++;
            }
            return;
        }
        while (i10 < list.size()) {
            this.zza.zza(i9, list.get(i10).longValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzd(int i9, List<Long> list, boolean z8) {
        int i10 = 0;
        if (z8) {
            this.zza.zza(i9, 2);
            int iZze = 0;
            for (int i11 = 0; i11 < list.size(); i11++) {
                iZze += zzhf.zze(list.get(i11).longValue());
            }
            this.zza.zzb(iZze);
            while (i10 < list.size()) {
                this.zza.zza(list.get(i10).longValue());
                i10++;
            }
            return;
        }
        while (i10 < list.size()) {
            this.zza.zza(i9, list.get(i10).longValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zze(int i9, List<Long> list, boolean z8) {
        int i10 = 0;
        if (z8) {
            this.zza.zza(i9, 2);
            int iZzg = 0;
            for (int i11 = 0; i11 < list.size(); i11++) {
                iZzg += zzhf.zzg(list.get(i11).longValue());
            }
            this.zza.zzb(iZzg);
            while (i10 < list.size()) {
                this.zza.zzc(list.get(i10).longValue());
                i10++;
            }
            return;
        }
        while (i10 < list.size()) {
            this.zza.zzc(i9, list.get(i10).longValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zza(int i9, int i10) {
        this.zza.zze(i9, i10);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzb(int i9, Object obj, zzjv zzjvVar) {
        zzhf zzhfVar = this.zza;
        zzhfVar.zza(i9, 3);
        zzjvVar.zza((zzjv) obj, (zzlk) zzhfVar.zza);
        zzhfVar.zza(i9, 4);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zza(int i9, long j9) {
        this.zza.zza(i9, j9);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zza(int i9, float f9) {
        this.zza.zza(i9, f9);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zza(int i9, double d9) {
        this.zza.zza(i9, d9);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzb(int i9) {
        this.zza.zza(i9, 4);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zza(int i9, boolean z8) {
        this.zza.zza(i9, z8);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzb(int i9, List<Integer> list, boolean z8) {
        int i10 = 0;
        if (z8) {
            this.zza.zza(i9, 2);
            int iZzi = 0;
            for (int i11 = 0; i11 < list.size(); i11++) {
                iZzi += zzhf.zzi(list.get(i11).intValue());
            }
            this.zza.zzb(iZzi);
            while (i10 < list.size()) {
                this.zza.zzd(list.get(i10).intValue());
                i10++;
            }
            return;
        }
        while (i10 < list.size()) {
            this.zza.zze(i9, list.get(i10).intValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zza(int i9, String str) {
        this.zza.zza(i9, str);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zza(int i9, zzgm zzgmVar) {
        this.zza.zza(i9, zzgmVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zza(int i9, Object obj, zzjv zzjvVar) {
        this.zza.zza(i9, (zzjg) obj, zzjvVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zza(int i9) {
        this.zza.zza(i9, 3);
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zza(int i9, Object obj) {
        if (obj instanceof zzgm) {
            this.zza.zzb(i9, (zzgm) obj);
        } else {
            this.zza.zza(i9, (zzjg) obj);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zza(int i9, List<Integer> list, boolean z8) {
        int i10 = 0;
        if (z8) {
            this.zza.zza(i9, 2);
            int iZzf = 0;
            for (int i11 = 0; i11 < list.size(); i11++) {
                iZzf += zzhf.zzf(list.get(i11).intValue());
            }
            this.zza.zzb(iZzf);
            while (i10 < list.size()) {
                this.zza.zza(list.get(i10).intValue());
                i10++;
            }
            return;
        }
        while (i10 < list.size()) {
            this.zza.zzb(i9, list.get(i10).intValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzb(int i9, List<zzgm> list) {
        for (int i10 = 0; i10 < list.size(); i10++) {
            this.zza.zza(i9, list.get(i10));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zzb(int i9, List<?> list, zzjv zzjvVar) {
        for (int i10 = 0; i10 < list.size(); i10++) {
            zzb(i9, list.get(i10), zzjvVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zza(int i9, List<String> list) {
        int i10 = 0;
        if (list instanceof zzin) {
            zzin zzinVar = (zzin) list;
            while (i10 < list.size()) {
                Object objZzb = zzinVar.zzb(i10);
                if (objZzb instanceof String) {
                    this.zza.zza(i9, (String) objZzb);
                } else {
                    this.zza.zza(i9, (zzgm) objZzb);
                }
                i10++;
            }
            return;
        }
        while (i10 < list.size()) {
            this.zza.zza(i9, list.get(i10));
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final void zza(int i9, List<?> list, zzjv zzjvVar) {
        for (int i10 = 0; i10 < list.size(); i10++) {
            zza(i9, list.get(i10), zzjvVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final <K, V> void zza(int i9, zzix<K, V> zzixVar, Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.zza.zza(i9, 2);
            this.zza.zzb(zziy.zza(zzixVar, entry.getKey(), entry.getValue()));
            zziy.zza(this.zza, zzixVar, entry.getKey(), entry.getValue());
        }
    }
}
