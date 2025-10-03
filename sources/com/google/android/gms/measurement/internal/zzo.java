package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzbv;
import com.google.android.gms.internal.measurement.zzcd;
import com.google.android.gms.internal.measurement.zzms;
import com.google.android.gms.internal.measurement.zzmy;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import p132m.C5302a;

/* loaded from: classes2.dex */
final class zzo extends zzkm {
    private String zzb;
    private Set<Integer> zzc;
    private Map<Integer, zzq> zzd;
    private Long zze;
    private Long zzf;

    public zzo(zzkp zzkpVar) {
        super(zzkpVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:215:0x0618, code lost:
    
        r7 = zzq().zzh();
        r9 = com.google.android.gms.measurement.internal.zzex.zza(r46.zzb);
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x062a, code lost:
    
        if (r8.zza() == false) goto L218;
     */
    /* JADX WARN: Code restructure failed: missing block: B:217:0x062c, code lost:
    
        r8 = java.lang.Integer.valueOf(r8.zzb());
     */
    /* JADX WARN: Code restructure failed: missing block: B:218:0x0635, code lost:
    
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:219:0x0636, code lost:
    
        r7.zza("Invalid property filter ID. appId, id", r9, java.lang.String.valueOf(r8));
        r8 = false;
     */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02c8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List<zzcd.zza> zza(String str, List<zzcd.zzc> list, List<zzcd.zzk> list2, Long l9, Long l10) throws Throwable {
        int i9;
        boolean z8;
        zzan zzanVar;
        zzt zztVar;
        int i10;
        Map<Integer, zzcd.zzi> map;
        List<zzbv.zzb> list3;
        boolean z9;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(list2);
        this.zzb = str;
        this.zzc = new HashSet();
        this.zzd = new C5302a();
        this.zze = l9;
        this.zzf = l10;
        Iterator<zzcd.zzc> it = list.iterator();
        while (true) {
            i9 = 1;
            if (!it.hasNext()) {
                z8 = false;
                break;
            }
            if ("_s".equals(it.next().zzc())) {
                z8 = true;
                break;
            }
        }
        boolean z10 = zzms.zzb() && zzs().zzd(this.zzb, zzat.zzbc);
        boolean z11 = zzms.zzb() && zzs().zzd(this.zzb, zzat.zzbb);
        if (z8) {
            zzac zzacVarZzi = zzi();
            String str2 = this.zzb;
            zzacVarZzi.zzaj();
            zzacVarZzi.zzc();
            Preconditions.checkNotEmpty(str2);
            ContentValues contentValues = new ContentValues();
            contentValues.put("current_session_count", (Integer) 0);
            try {
                zzacVarZzi.m17537c_().update("events", contentValues, "app_id = ?", new String[]{str2});
            } catch (SQLiteException e9) {
                zzacVarZzi.zzq().zze().zza("Error resetting session-scoped event counts. appId", zzex.zza(str2), e9);
            }
        }
        Map<Integer, List<zzbv.zzb>> mapEmptyMap = Collections.emptyMap();
        if (z11 && z10) {
            mapEmptyMap = zzi().zze(this.zzb);
        }
        Map<Integer, zzcd.zzi> mapZzg = zzi().zzg(this.zzb);
        if (((zzmy.zzb() && zzs().zzd(this.zzb, zzat.zzcm)) || mapZzg != null) && !mapZzg.isEmpty()) {
            HashSet hashSet = new HashSet(mapZzg.keySet());
            if (z8) {
                String str3 = this.zzb;
                Preconditions.checkNotEmpty(str3);
                Preconditions.checkNotNull(mapZzg);
                C5302a c5302a = new C5302a();
                if (!mapZzg.isEmpty()) {
                    Map<Integer, List<Integer>> mapZzf = zzi().zzf(str3);
                    Iterator<Integer> it2 = mapZzg.keySet().iterator();
                    while (it2.hasNext()) {
                        int iIntValue = it2.next().intValue();
                        zzcd.zzi zziVar = mapZzg.get(Integer.valueOf(iIntValue));
                        List<Integer> list4 = mapZzf.get(Integer.valueOf(iIntValue));
                        if (list4 == null || list4.isEmpty()) {
                            c5302a.put(Integer.valueOf(iIntValue), zziVar);
                        } else {
                            List<Long> listZza = mo17540f_().zza(zziVar.zzc(), list4);
                            if (!listZza.isEmpty()) {
                                zzcd.zzi.zza zzaVarZzb = zziVar.zzbo().zzb().zzb(listZza);
                                zzaVarZzb.zza().zza(mo17540f_().zza(zziVar.zza(), list4));
                                for (int i11 = 0; i11 < zziVar.zzf(); i11++) {
                                    if (list4.contains(Integer.valueOf(zziVar.zza(i11).zzb()))) {
                                        zzaVarZzb.zza(i11);
                                    }
                                }
                                for (int i12 = 0; i12 < zziVar.zzh(); i12++) {
                                    if (list4.contains(Integer.valueOf(zziVar.zzb(i12).zzb()))) {
                                        zzaVarZzb.zzb(i12);
                                    }
                                }
                                c5302a.put(Integer.valueOf(iIntValue), (zzcd.zzi) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzb.zzy()));
                            }
                        }
                    }
                }
                map = c5302a;
            } else {
                map = mapZzg;
            }
            Iterator it3 = hashSet.iterator();
            while (it3.hasNext()) {
                int iIntValue2 = ((Integer) it3.next()).intValue();
                zzcd.zzi zziVar2 = map.get(Integer.valueOf(iIntValue2));
                BitSet bitSet = new BitSet();
                BitSet bitSet2 = new BitSet();
                C5302a c5302a2 = new C5302a();
                if (zziVar2 != null && zziVar2.zzf() != 0) {
                    for (zzcd.zzb zzbVar : zziVar2.zze()) {
                        if (zzbVar.zza()) {
                            c5302a2.put(Integer.valueOf(zzbVar.zzb()), zzbVar.zzc() ? Long.valueOf(zzbVar.zzd()) : null);
                        }
                    }
                }
                C5302a c5302a3 = new C5302a();
                if (zziVar2 != null && zziVar2.zzh() != 0) {
                    for (zzcd.zzj zzjVar : zziVar2.zzg()) {
                        if (zzjVar.zza() && zzjVar.zzd() > 0) {
                            c5302a3.put(Integer.valueOf(zzjVar.zzb()), Long.valueOf(zzjVar.zza(zzjVar.zzd() - i9)));
                        }
                    }
                }
                if (zziVar2 != null) {
                    for (int i13 = 0; i13 < (zziVar2.zzb() << 6); i13++) {
                        if (zzkt.zza(zziVar2.zza(), i13)) {
                            zzq().zzw().zza("Filter already evaluated. audience ID, filter ID", Integer.valueOf(iIntValue2), Integer.valueOf(i13));
                            bitSet2.set(i13);
                            if (zzkt.zza(zziVar2.zzc(), i13)) {
                                bitSet.set(i13);
                                z9 = true;
                            } else {
                                z9 = false;
                            }
                        }
                        if (!z9) {
                            c5302a2.remove(Integer.valueOf(i13));
                        }
                    }
                }
                zzcd.zzi zziVar3 = mapZzg.get(Integer.valueOf(iIntValue2));
                if (z11 && z10 && (list3 = mapEmptyMap.get(Integer.valueOf(iIntValue2))) != null && this.zzf != null && this.zze != null) {
                    for (zzbv.zzb zzbVar2 : list3) {
                        int iZzb = zzbVar2.zzb();
                        long jLongValue = this.zzf.longValue() / 1000;
                        if (zzbVar2.zzi()) {
                            jLongValue = this.zze.longValue() / 1000;
                        }
                        if (c5302a2.containsKey(Integer.valueOf(iZzb))) {
                            c5302a2.put(Integer.valueOf(iZzb), Long.valueOf(jLongValue));
                        }
                        if (c5302a3.containsKey(Integer.valueOf(iZzb))) {
                            c5302a3.put(Integer.valueOf(iZzb), Long.valueOf(jLongValue));
                        }
                    }
                }
                this.zzd.put(Integer.valueOf(iIntValue2), new zzq(this, this.zzb, zziVar3, bitSet, bitSet2, c5302a2, c5302a3, null));
                map = map;
                i9 = 1;
            }
        }
        zzr zzrVar = null;
        if (!list.isEmpty()) {
            zzt zztVar2 = new zzt(this, zzrVar);
            C5302a c5302a4 = new C5302a();
            for (zzcd.zzc zzcVar : list) {
                zzcd.zzc zzcVarZza = zztVar2.zza(this.zzb, zzcVar);
                if (zzcVarZza != null) {
                    zzac zzacVarZzi2 = zzi();
                    String str4 = this.zzb;
                    String strZzc = zzcVarZza.zzc();
                    zzan zzanVarZza = zzacVarZzi2.zza(str4, zzcVar.zzc());
                    if (zzanVarZza == null) {
                        zzacVarZzi2.zzq().zzh().zza("Event aggregate wasn't created during raw event logging. appId, event", zzex.zza(str4), zzacVarZzi2.zzn().zza(strZzc));
                        zzanVar = new zzan(str4, zzcVar.zzc(), 1L, 1L, 1L, zzcVar.zze(), 0L, null, null, null, null);
                    } else {
                        zzanVar = new zzan(zzanVarZza.zza, zzanVarZza.zzb, zzanVarZza.zzc + 1, zzanVarZza.zzd + 1, zzanVarZza.zze + 1, zzanVarZza.zzf, zzanVarZza.zzg, zzanVarZza.zzh, zzanVarZza.zzi, zzanVarZza.zzj, zzanVarZza.zzk);
                    }
                    zzi().zza(zzanVar);
                    long j9 = zzanVar.zzc;
                    String strZzc2 = zzcVarZza.zzc();
                    Map<Integer, List<zzbv.zzb>> mapZzf2 = (Map) c5302a4.get(strZzc2);
                    if (mapZzf2 == null) {
                        mapZzf2 = zzi().zzf(this.zzb, strZzc2);
                        if ((!zzmy.zzb() || !zzs().zzd(this.zzb, zzat.zzcm)) && mapZzf2 == null) {
                            mapZzf2 = new C5302a<>();
                        }
                        c5302a4.put(strZzc2, mapZzf2);
                    }
                    Iterator<Integer> it4 = mapZzf2.keySet().iterator();
                    while (it4.hasNext()) {
                        int iIntValue3 = it4.next().intValue();
                        if (this.zzc.contains(Integer.valueOf(iIntValue3))) {
                            zzq().zzw().zza("Skipping failed audience ID", Integer.valueOf(iIntValue3));
                        } else {
                            Iterator<zzbv.zzb> it5 = mapZzf2.get(Integer.valueOf(iIntValue3)).iterator();
                            boolean zZza = true;
                            while (true) {
                                if (!it5.hasNext()) {
                                    zztVar = zztVar2;
                                    i10 = iIntValue3;
                                    break;
                                }
                                zzbv.zzb next = it5.next();
                                zzs zzsVar = new zzs(this, this.zzb, iIntValue3, next);
                                zztVar = zztVar2;
                                i10 = iIntValue3;
                                zZza = zzsVar.zza(this.zze, this.zzf, zzcVarZza, j9, zzanVar, zza(iIntValue3, next.zzb()));
                                if (!zZza) {
                                    this.zzc.add(Integer.valueOf(i10));
                                    break;
                                }
                                zza(i10).zza(zzsVar);
                                iIntValue3 = i10;
                                zztVar2 = zztVar;
                            }
                            if (!zZza) {
                                this.zzc.add(Integer.valueOf(i10));
                            }
                            zztVar2 = zztVar;
                        }
                    }
                }
            }
        }
        if (!list2.isEmpty()) {
            C5302a c5302a5 = new C5302a();
            for (zzcd.zzk zzkVar : list2) {
                String strZzc3 = zzkVar.zzc();
                Map<Integer, List<zzbv.zze>> mapZzg2 = (Map) c5302a5.get(strZzc3);
                if (mapZzg2 == null) {
                    mapZzg2 = zzi().zzg(this.zzb, strZzc3);
                    if ((!zzmy.zzb() || !zzs().zzd(this.zzb, zzat.zzcm)) && mapZzg2 == null) {
                        mapZzg2 = new C5302a<>();
                    }
                    c5302a5.put(strZzc3, mapZzg2);
                }
                Iterator<Integer> it6 = mapZzg2.keySet().iterator();
                while (true) {
                    if (it6.hasNext()) {
                        int iIntValue4 = it6.next().intValue();
                        if (this.zzc.contains(Integer.valueOf(iIntValue4))) {
                            zzq().zzw().zza("Skipping failed audience ID", Integer.valueOf(iIntValue4));
                            break;
                        }
                        Iterator<zzbv.zze> it7 = mapZzg2.get(Integer.valueOf(iIntValue4)).iterator();
                        boolean zZza2 = true;
                        while (true) {
                            if (!it7.hasNext()) {
                                break;
                            }
                            zzbv.zze next2 = it7.next();
                            if (zzq().zza(2)) {
                                zzq().zzw().zza("Evaluating filter. audience, filter, property", Integer.valueOf(iIntValue4), next2.zza() ? Integer.valueOf(next2.zzb()) : null, zzn().zzc(next2.zzc()));
                                zzq().zzw().zza("Filter definition", mo17540f_().zza(next2));
                            }
                            if (!next2.zza() || next2.zzb() > 256) {
                                break;
                            }
                            zzu zzuVar = new zzu(this, this.zzb, iIntValue4, next2);
                            zZza2 = zzuVar.zza(this.zze, this.zzf, zzkVar, zza(iIntValue4, next2.zzb()));
                            if (!zZza2) {
                                this.zzc.add(Integer.valueOf(iIntValue4));
                                break;
                            }
                            zza(iIntValue4).zza(zzuVar);
                        }
                        if (!zZza2) {
                            this.zzc.add(Integer.valueOf(iIntValue4));
                        }
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        Set<Integer> setKeySet = this.zzd.keySet();
        setKeySet.removeAll(this.zzc);
        Iterator<Integer> it8 = setKeySet.iterator();
        while (it8.hasNext()) {
            int iIntValue5 = it8.next().intValue();
            zzcd.zza zzaVarZza = this.zzd.get(Integer.valueOf(iIntValue5)).zza(iIntValue5);
            arrayList.add(zzaVarZza);
            zzac zzacVarZzi3 = zzi();
            String str5 = this.zzb;
            zzcd.zzi zziVarZzc = zzaVarZza.zzc();
            zzacVarZzi3.zzaj();
            zzacVarZzi3.zzc();
            Preconditions.checkNotEmpty(str5);
            Preconditions.checkNotNull(zziVarZzc);
            byte[] bArrZzbk = zziVarZzc.zzbk();
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("app_id", str5);
            contentValues2.put("audience_id", Integer.valueOf(iIntValue5));
            contentValues2.put("current_results", bArrZzbk);
            try {
                try {
                    if (zzacVarZzi3.m17537c_().insertWithOnConflict("audience_filter_values", null, contentValues2, 5) == -1) {
                        zzacVarZzi3.zzq().zze().zza("Failed to insert filter results (got -1). appId", zzex.zza(str5));
                    }
                } catch (SQLiteException e10) {
                    e = e10;
                    zzacVarZzi3.zzq().zze().zza("Error storing filter results. appId", zzex.zza(str5), e);
                }
            } catch (SQLiteException e11) {
                e = e11;
            }
        }
        return arrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzkm
    public final boolean zzd() {
        return false;
    }

    private final zzq zza(int i9) {
        if (this.zzd.containsKey(Integer.valueOf(i9))) {
            return this.zzd.get(Integer.valueOf(i9));
        }
        zzq zzqVar = new zzq(this, this.zzb, null);
        this.zzd.put(Integer.valueOf(i9), zzqVar);
        return zzqVar;
    }

    private final boolean zza(int i9, int i10) {
        if (this.zzd.get(Integer.valueOf(i9)) == null) {
            return false;
        }
        return this.zzd.get(Integer.valueOf(i9)).zzd.get(i10);
    }
}
