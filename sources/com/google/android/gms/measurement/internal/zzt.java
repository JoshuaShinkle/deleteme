package com.google.android.gms.measurement.internal;

import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.internal.measurement.zzcd;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
final class zzt {
    private zzcd.zzc zza;
    private Long zzb;
    private long zzc;
    private final /* synthetic */ zzo zzd;

    private zzt(zzo zzoVar) {
        this.zzd = zzoVar;
    }

    public final zzcd.zzc zza(String str, zzcd.zzc zzcVar) {
        Object obj;
        String strZzc = zzcVar.zzc();
        List<zzcd.zze> listZza = zzcVar.zza();
        Long l9 = (Long) this.zzd.mo17540f_().zzb(zzcVar, "_eid");
        boolean z8 = l9 != null;
        if (z8 && strZzc.equals("_ep")) {
            strZzc = (String) this.zzd.mo17540f_().zzb(zzcVar, "_en");
            if (TextUtils.isEmpty(strZzc)) {
                this.zzd.zzq().zzf().zza("Extra parameter without an event name. eventId", l9);
                return null;
            }
            if (this.zza == null || this.zzb == null || l9.longValue() != this.zzb.longValue()) {
                Pair<zzcd.zzc, Long> pairZza = this.zzd.zzi().zza(str, l9);
                if (pairZza == null || (obj = pairZza.first) == null) {
                    this.zzd.zzq().zzf().zza("Extra parameter without existing main event. eventName, eventId", strZzc, l9);
                    return null;
                }
                this.zza = (zzcd.zzc) obj;
                this.zzc = ((Long) pairZza.second).longValue();
                this.zzb = (Long) this.zzd.mo17540f_().zzb(this.zza, "_eid");
            }
            long j9 = this.zzc - 1;
            this.zzc = j9;
            if (j9 <= 0) {
                zzac zzacVarZzi = this.zzd.zzi();
                zzacVarZzi.zzc();
                zzacVarZzi.zzq().zzw().zza("Clearing complex main event info. appId", str);
                try {
                    zzacVarZzi.m17537c_().execSQL("delete from main_event_params where app_id=?", new String[]{str});
                } catch (SQLiteException e9) {
                    zzacVarZzi.zzq().zze().zza("Error clearing complex main event", e9);
                }
            } else {
                this.zzd.zzi().zza(str, l9, this.zzc, this.zza);
            }
            ArrayList arrayList = new ArrayList();
            for (zzcd.zze zzeVar : this.zza.zza()) {
                this.zzd.mo17540f_();
                if (zzkt.zza(zzcVar, zzeVar.zzb()) == null) {
                    arrayList.add(zzeVar);
                }
            }
            if (arrayList.isEmpty()) {
                this.zzd.zzq().zzf().zza("No unique parameters in main event. eventName", strZzc);
            } else {
                arrayList.addAll(listZza);
                listZza = arrayList;
            }
        } else if (z8) {
            this.zzb = l9;
            this.zza = zzcVar;
            Object objZzb = this.zzd.mo17540f_().zzb(zzcVar, "_epc");
            long jLongValue = ((Long) (objZzb != null ? objZzb : 0L)).longValue();
            this.zzc = jLongValue;
            if (jLongValue <= 0) {
                this.zzd.zzq().zzf().zza("Complex event with zero extra param count. eventName", strZzc);
            } else {
                this.zzd.zzi().zza(str, l9, this.zzc, zzcVar);
            }
        }
        return (zzcd.zzc) ((com.google.android.gms.internal.measurement.zzhv) zzcVar.zzbo().zza(strZzc).zzc().zza(listZza).zzy());
    }

    public /* synthetic */ zzt(zzo zzoVar, zzr zzrVar) {
        this(zzoVar);
    }
}
