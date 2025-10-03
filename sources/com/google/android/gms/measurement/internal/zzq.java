package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzcd;
import com.google.android.gms.internal.measurement.zzms;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p132m.C5302a;

/* loaded from: classes2.dex */
final class zzq {
    private String zza;
    private boolean zzb;
    private zzcd.zzi zzc;
    private BitSet zzd;
    private BitSet zze;
    private Map<Integer, Long> zzf;
    private Map<Integer, List<Long>> zzg;
    private final /* synthetic */ zzo zzh;

    private zzq(zzo zzoVar, String str) {
        this.zzh = zzoVar;
        this.zza = str;
        this.zzb = true;
        this.zzd = new BitSet();
        this.zze = new BitSet();
        this.zzf = new C5302a();
        this.zzg = new C5302a();
    }

    public final void zza(zzv zzvVar) {
        int iZza = zzvVar.zza();
        Boolean bool = zzvVar.zzc;
        if (bool != null) {
            this.zze.set(iZza, bool.booleanValue());
        }
        Boolean bool2 = zzvVar.zzd;
        if (bool2 != null) {
            this.zzd.set(iZza, bool2.booleanValue());
        }
        if (zzvVar.zze != null) {
            Long l9 = this.zzf.get(Integer.valueOf(iZza));
            long jLongValue = zzvVar.zze.longValue() / 1000;
            if (l9 == null || jLongValue > l9.longValue()) {
                this.zzf.put(Integer.valueOf(iZza), Long.valueOf(jLongValue));
            }
        }
        if (zzvVar.zzf != null) {
            List<Long> arrayList = this.zzg.get(Integer.valueOf(iZza));
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.zzg.put(Integer.valueOf(iZza), arrayList);
            }
            if (zzvVar.zzb()) {
                arrayList.clear();
            }
            if (zzms.zzb() && this.zzh.zzs().zzd(this.zza, zzat.zzbc) && zzvVar.zzc()) {
                arrayList.clear();
            }
            if (!zzms.zzb() || !this.zzh.zzs().zzd(this.zza, zzat.zzbc)) {
                arrayList.add(Long.valueOf(zzvVar.zzf.longValue() / 1000));
                return;
            }
            long jLongValue2 = zzvVar.zzf.longValue() / 1000;
            if (arrayList.contains(Long.valueOf(jLongValue2))) {
                return;
            }
            arrayList.add(Long.valueOf(jLongValue2));
        }
    }

    private zzq(zzo zzoVar, String str, zzcd.zzi zziVar, BitSet bitSet, BitSet bitSet2, Map<Integer, Long> map, Map<Integer, Long> map2) {
        this.zzh = zzoVar;
        this.zza = str;
        this.zzd = bitSet;
        this.zze = bitSet2;
        this.zzf = map;
        this.zzg = new C5302a();
        if (map2 != null) {
            for (Integer num : map2.keySet()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(map2.get(num));
                this.zzg.put(num, arrayList);
            }
        }
        this.zzb = false;
        this.zzc = zziVar;
    }

    public /* synthetic */ zzq(zzo zzoVar, String str, zzcd.zzi zziVar, BitSet bitSet, BitSet bitSet2, Map map, Map map2, zzr zzrVar) {
        this(zzoVar, str, zziVar, bitSet, bitSet2, map, map2);
    }

    public /* synthetic */ zzq(zzo zzoVar, String str, zzr zzrVar) {
        this(zzoVar, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.internal.measurement.zzcd$zza$zza, com.google.android.gms.internal.measurement.zzhv$zzb] */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r8v5, types: [com.google.android.gms.internal.measurement.zzcd$zzi$zza] */
    public final zzcd.zza zza(int i9) {
        ArrayList arrayList;
        ?? arrayList2;
        ?? Zzh = zzcd.zza.zzh();
        Zzh.zza(i9);
        Zzh.zza(this.zzb);
        zzcd.zzi zziVar = this.zzc;
        if (zziVar != null) {
            Zzh.zza(zziVar);
        }
        ?? Zza = zzcd.zzi.zzi().zzb(zzkt.zza(this.zzd)).zza(zzkt.zza(this.zze));
        if (this.zzf == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(this.zzf.size());
            Iterator<Integer> it = this.zzf.keySet().iterator();
            while (it.hasNext()) {
                int iIntValue = it.next().intValue();
                arrayList.add((zzcd.zzb) ((com.google.android.gms.internal.measurement.zzhv) zzcd.zzb.zze().zza(iIntValue).zza(this.zzf.get(Integer.valueOf(iIntValue)).longValue()).zzy()));
            }
        }
        Zza.zzc(arrayList);
        if (this.zzg == null) {
            arrayList2 = Collections.emptyList();
        } else {
            arrayList2 = new ArrayList(this.zzg.size());
            for (Integer num : this.zzg.keySet()) {
                zzcd.zzj.zza zzaVarZza = zzcd.zzj.zze().zza(num.intValue());
                List<Long> list = this.zzg.get(num);
                if (list != null) {
                    Collections.sort(list);
                    zzaVarZza.zza(list);
                }
                arrayList2.add((zzcd.zzj) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZza.zzy()));
            }
        }
        Zza.zzd(arrayList2);
        Zzh.zza(Zza);
        return (zzcd.zza) ((com.google.android.gms.internal.measurement.zzhv) Zzh.zzy());
    }
}
