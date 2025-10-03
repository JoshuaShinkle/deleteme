package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzrc;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzql implements zzum {
    private final zzqj zzawd;

    private zzql(zzqj zzqjVar) {
        zzqj zzqjVar2 = (zzqj) zzre.zza(zzqjVar, "output");
        this.zzawd = zzqjVar2;
        zzqjVar2.zzawu = this;
    }

    public static zzql zza(zzqj zzqjVar) {
        zzql zzqlVar = zzqjVar.zzawu;
        return zzqlVar != null ? zzqlVar : new zzql(zzqjVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzb(int i9, boolean z8) {
        this.zzawd.zzb(i9, z8);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzbk(int i9) {
        this.zzawd.zzd(i9, 3);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzbl(int i9) {
        this.zzawd.zzd(i9, 4);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzc(int i9, long j9) {
        this.zzawd.zzc(i9, j9);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzd(int i9, List<Long> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zzawd.zza(i9, list.get(i10).longValue());
                i10++;
            }
            return;
        }
        this.zzawd.zzd(i9, 2);
        int iZzt = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzt += zzqj.zzt(list.get(i11).longValue());
        }
        this.zzawd.zzay(iZzt);
        while (i10 < list.size()) {
            this.zzawd.zzp(list.get(i10).longValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zze(int i9, int i10) {
        this.zzawd.zze(i9, i10);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzf(int i9, int i10) {
        this.zzawd.zzf(i9, i10);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzg(int i9, int i10) {
        this.zzawd.zzg(i9, i10);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzh(int i9, int i10) {
        this.zzawd.zzh(i9, i10);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzi(int i9, long j9) {
        this.zzawd.zza(i9, j9);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzj(int i9, long j9) {
        this.zzawd.zzc(i9, j9);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzk(int i9, List<Integer> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zzawd.zzh(i9, list.get(i10).intValue());
                i10++;
            }
            return;
        }
        this.zzawd.zzd(i9, 2);
        int iZzbg = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzbg += zzqj.zzbg(list.get(i11).intValue());
        }
        this.zzawd.zzay(iZzbg);
        while (i10 < list.size()) {
            this.zzawd.zzba(list.get(i10).intValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzl(int i9, List<Long> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zzawd.zzc(i9, list.get(i10).longValue());
                i10++;
            }
            return;
        }
        this.zzawd.zzd(i9, 2);
        int iZzw = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzw += zzqj.zzw(list.get(i11).longValue());
        }
        this.zzawd.zzay(iZzw);
        while (i10 < list.size()) {
            this.zzawd.zzr(list.get(i10).longValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzm(int i9, List<Integer> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zzawd.zzg(i9, list.get(i10).intValue());
                i10++;
            }
            return;
        }
        this.zzawd.zzd(i9, 2);
        int iZzbe = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzbe += zzqj.zzbe(list.get(i11).intValue());
        }
        this.zzawd.zzay(iZzbe);
        while (i10 < list.size()) {
            this.zzawd.zzaz(list.get(i10).intValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzn(int i9, List<Long> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zzawd.zzb(i9, list.get(i10).longValue());
                i10++;
            }
            return;
        }
        this.zzawd.zzd(i9, 2);
        int iZzu = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzu += zzqj.zzu(list.get(i11).longValue());
        }
        this.zzawd.zzay(iZzu);
        while (i10 < list.size()) {
            this.zzawd.zzq(list.get(i10).longValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzo(int i9, int i10) {
        this.zzawd.zzh(i9, i10);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final int zzol() {
        return zzrc.zze.zzbbc;
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzp(int i9, int i10) {
        this.zzawd.zze(i9, i10);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzb(int i9, long j9) {
        this.zzawd.zzb(i9, j9);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzc(int i9, List<Long> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zzawd.zza(i9, list.get(i10).longValue());
                i10++;
            }
            return;
        }
        this.zzawd.zzd(i9, 2);
        int iZzs = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzs += zzqj.zzs(list.get(i11).longValue());
        }
        this.zzawd.zzay(iZzs);
        while (i10 < list.size()) {
            this.zzawd.zzp(list.get(i10).longValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zze(int i9, List<Long> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zzawd.zzc(i9, list.get(i10).longValue());
                i10++;
            }
            return;
        }
        this.zzawd.zzd(i9, 2);
        int iZzv = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzv += zzqj.zzv(list.get(i11).longValue());
        }
        this.zzawd.zzay(iZzv);
        while (i10 < list.size()) {
            this.zzawd.zzr(list.get(i10).longValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzf(int i9, List<Float> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zzawd.zza(i9, list.get(i10).floatValue());
                i10++;
            }
            return;
        }
        this.zzawd.zzd(i9, 2);
        int iZzb = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzb += zzqj.zzb(list.get(i11).floatValue());
        }
        this.zzawd.zzay(iZzb);
        while (i10 < list.size()) {
            this.zzawd.zza(list.get(i10).floatValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzg(int i9, List<Double> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zzawd.zza(i9, list.get(i10).doubleValue());
                i10++;
            }
            return;
        }
        this.zzawd.zzd(i9, 2);
        int iZzc = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzc += zzqj.zzc(list.get(i11).doubleValue());
        }
        this.zzawd.zzay(iZzc);
        while (i10 < list.size()) {
            this.zzawd.zzb(list.get(i10).doubleValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzh(int i9, List<Integer> list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zzawd.zze(i9, list.get(i10).intValue());
                i10++;
            }
            return;
        }
        this.zzawd.zzd(i9, 2);
        int iZzbh = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzbh += zzqj.zzbh(list.get(i11).intValue());
        }
        this.zzawd.zzay(iZzbh);
        while (i10 < list.size()) {
            this.zzawd.zzax(list.get(i10).intValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zza(int i9, float f9) {
        this.zzawd.zza(i9, f9);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzb(int i9, Object obj, zzsz zzszVar) {
        zzqj zzqjVar = this.zzawd;
        zzqjVar.zzd(i9, 3);
        zzszVar.zza((zzsk) obj, zzqjVar.zzawu);
        zzqjVar.zzd(i9, 4);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzi(int i9, List<Boolean> list, boolean z8) {
        int i10 = 0;
        if (z8) {
            this.zzawd.zzd(i9, 2);
            int iZzj = 0;
            for (int i11 = 0; i11 < list.size(); i11++) {
                iZzj += zzqj.zzj(list.get(i11).booleanValue());
            }
            this.zzawd.zzay(iZzj);
            while (i10 < list.size()) {
                this.zzawd.zzi(list.get(i10).booleanValue());
                i10++;
            }
            return;
        }
        while (i10 < list.size()) {
            this.zzawd.zzb(i9, list.get(i10).booleanValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzj(int i9, List<Integer> list, boolean z8) {
        int i10 = 0;
        if (z8) {
            this.zzawd.zzd(i9, 2);
            int iZzbd = 0;
            for (int i11 = 0; i11 < list.size(); i11++) {
                iZzbd += zzqj.zzbd(list.get(i11).intValue());
            }
            this.zzawd.zzay(iZzbd);
            while (i10 < list.size()) {
                this.zzawd.zzay(list.get(i10).intValue());
                i10++;
            }
            return;
        }
        while (i10 < list.size()) {
            this.zzawd.zzf(i9, list.get(i10).intValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zza(int i9, double d9) {
        this.zzawd.zza(i9, d9);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zza(int i9, long j9) {
        this.zzawd.zza(i9, j9);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zza(int i9, String str) {
        this.zzawd.zza(i9, str);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zza(int i9, zzps zzpsVar) {
        this.zzawd.zza(i9, zzpsVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzb(int i9, List<Integer> list, boolean z8) {
        int i10 = 0;
        if (z8) {
            this.zzawd.zzd(i9, 2);
            int iZzbf = 0;
            for (int i11 = 0; i11 < list.size(); i11++) {
                iZzbf += zzqj.zzbf(list.get(i11).intValue());
            }
            this.zzawd.zzay(iZzbf);
            while (i10 < list.size()) {
                this.zzawd.zzba(list.get(i10).intValue());
                i10++;
            }
            return;
        }
        while (i10 < list.size()) {
            this.zzawd.zzh(i9, list.get(i10).intValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zza(int i9, Object obj, zzsz zzszVar) {
        this.zzawd.zza(i9, (zzsk) obj, zzszVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zza(int i9, Object obj) {
        if (obj instanceof zzps) {
            this.zzawd.zzb(i9, (zzps) obj);
        } else {
            this.zzawd.zzb(i9, (zzsk) obj);
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zza(int i9, List<Integer> list, boolean z8) {
        int i10 = 0;
        if (z8) {
            this.zzawd.zzd(i9, 2);
            int iZzbc = 0;
            for (int i11 = 0; i11 < list.size(); i11++) {
                iZzbc += zzqj.zzbc(list.get(i11).intValue());
            }
            this.zzawd.zzay(iZzbc);
            while (i10 < list.size()) {
                this.zzawd.zzax(list.get(i10).intValue());
                i10++;
            }
            return;
        }
        while (i10 < list.size()) {
            this.zzawd.zze(i9, list.get(i10).intValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzb(int i9, List<zzps> list) {
        for (int i10 = 0; i10 < list.size(); i10++) {
            this.zzawd.zza(i9, list.get(i10));
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zzb(int i9, List<?> list, zzsz zzszVar) {
        for (int i10 = 0; i10 < list.size(); i10++) {
            zzb(i9, list.get(i10), zzszVar);
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zza(int i9, List<String> list) {
        int i10 = 0;
        if (list instanceof zzrt) {
            zzrt zzrtVar = (zzrt) list;
            while (i10 < list.size()) {
                Object objZzbn = zzrtVar.zzbn(i10);
                if (objZzbn instanceof String) {
                    this.zzawd.zza(i9, (String) objZzbn);
                } else {
                    this.zzawd.zza(i9, (zzps) objZzbn);
                }
                i10++;
            }
            return;
        }
        while (i10 < list.size()) {
            this.zzawd.zza(i9, list.get(i10));
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final void zza(int i9, List<?> list, zzsz zzszVar) {
        for (int i10 = 0; i10 < list.size(); i10++) {
            zza(i9, list.get(i10), zzszVar);
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzum
    public final <K, V> void zza(int i9, zzsd<K, V> zzsdVar, Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.zzawd.zzd(i9, 2);
            this.zzawd.zzay(zzsc.zza(zzsdVar, entry.getKey(), entry.getValue()));
            zzsc.zza(this.zzawd, zzsdVar, entry.getKey(), entry.getValue());
        }
    }
}
