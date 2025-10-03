package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzca;
import com.google.android.gms.internal.measurement.zzlj;
import com.google.android.gms.internal.measurement.zzmh;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Map;
import p132m.C5302a;

/* loaded from: classes2.dex */
public final class zzfv extends zzkm implements zzaa {

    @VisibleForTesting
    private static int zzb = 65535;

    @VisibleForTesting
    private static int zzc = 2;
    private final Map<String, Map<String, String>> zzd;
    private final Map<String, Map<String, Boolean>> zze;
    private final Map<String, Map<String, Boolean>> zzf;
    private final Map<String, zzca.zzb> zzg;
    private final Map<String, Map<String, Integer>> zzh;
    private final Map<String, String> zzi;

    public zzfv(zzkp zzkpVar) {
        super(zzkpVar);
        this.zzd = new C5302a();
        this.zze = new C5302a();
        this.zzf = new C5302a();
        this.zzg = new C5302a();
        this.zzi = new C5302a();
        this.zzh = new C5302a();
    }

    private final void zzi(String str) throws Throwable {
        zzaj();
        zzc();
        Preconditions.checkNotEmpty(str);
        if (this.zzg.get(str) == null) {
            byte[] bArrZzd = zzi().zzd(str);
            if (bArrZzd != null) {
                zzca.zzb.zza zzaVarZzbo = zza(str, bArrZzd).zzbo();
                zza(str, zzaVarZzbo);
                this.zzd.put(str, zza((zzca.zzb) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo.zzy())));
                this.zzg.put(str, (zzca.zzb) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo.zzy()));
                this.zzi.put(str, null);
                return;
            }
            this.zzd.put(str, null);
            this.zze.put(str, null);
            this.zzf.put(str, null);
            this.zzg.put(str, null);
            this.zzi.put(str, null);
            this.zzh.put(str, null);
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    /* renamed from: f_ */
    public final /* bridge */ /* synthetic */ zzkt mo17540f_() {
        return super.mo17540f_();
    }

    public final zzca.zzb zza(String str) {
        zzaj();
        zzc();
        Preconditions.checkNotEmpty(str);
        zzi(str);
        return this.zzg.get(str);
    }

    public final String zzb(String str) {
        zzc();
        return this.zzi.get(str);
    }

    public final void zzc(String str) {
        zzc();
        this.zzi.put(str, null);
    }

    public final void zzd(String str) {
        zzc();
        this.zzg.remove(str);
    }

    @Override // com.google.android.gms.measurement.internal.zzkm
    public final boolean zzd() {
        return false;
    }

    public final boolean zze(String str) {
        zzc();
        zzca.zzb zzbVarZza = zza(str);
        if (zzbVarZza == null) {
            return false;
        }
        return zzbVarZza.zzh();
    }

    public final long zzf(String str) throws Throwable {
        String strZza = zza(str, "measurement.account.time_zone_offset_minutes");
        if (TextUtils.isEmpty(strZza)) {
            return 0L;
        }
        try {
            return Long.parseLong(strZza);
        } catch (NumberFormatException e9) {
            zzq().zzh().zza("Unable to parse timezone offset. appId", zzex.zza(str), e9);
            return 0L;
        }
    }

    public final boolean zzg(String str) {
        return "1".equals(zza(str, "measurement.upload.blacklist_internal"));
    }

    public final boolean zzh(String str) {
        return "1".equals(zza(str, "measurement.upload.blacklist_public"));
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    public final /* bridge */ /* synthetic */ zzfv zzj() {
        return super.zzj();
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

    @Override // com.google.android.gms.measurement.internal.zzkn
    public final /* bridge */ /* synthetic */ zzo zzh() {
        return super.zzh();
    }

    public final boolean zzb(String str, String str2) throws Throwable {
        Boolean bool;
        zzc();
        zzi(str);
        if (zzg(str) && zzkx.zzd(str2)) {
            return true;
        }
        if (zzh(str) && zzkx.zza(str2)) {
            return true;
        }
        Map<String, Boolean> map = this.zze.get(str);
        if (map == null || (bool = map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public final boolean zzc(String str, String str2) throws Throwable {
        Boolean bool;
        zzc();
        zzi(str);
        if (FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(str2)) {
            return true;
        }
        if (zzmh.zzb() && zzs().zza(zzat.zzcc) && ("purchase".equals(str2) || "refund".equals(str2))) {
            return true;
        }
        Map<String, Boolean> map = this.zzf.get(str);
        if (map == null || (bool = map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public final int zzd(String str, String str2) throws Throwable {
        Integer num;
        zzc();
        zzi(str);
        Map<String, Integer> map = this.zzh.get(str);
        if (map == null || (num = map.get(str2)) == null) {
            return 1;
        }
        return num.intValue();
    }

    @Override // com.google.android.gms.measurement.internal.zzaa
    public final String zza(String str, String str2) throws Throwable {
        zzc();
        zzi(str);
        Map<String, String> map = this.zzd.get(str);
        if (map != null) {
            return map.get(str2);
        }
        return null;
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    public final /* bridge */ /* synthetic */ zzjv zzf() {
        return super.zzf();
    }

    private static Map<String, String> zza(zzca.zzb zzbVar) {
        C5302a c5302a = new C5302a();
        if (zzbVar != null) {
            for (zzca.zzc zzcVar : zzbVar.zze()) {
                c5302a.put(zzcVar.zza(), zzcVar.zzb());
            }
        }
        return c5302a;
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    private final void zza(String str, zzca.zzb.zza zzaVar) {
        C5302a c5302a = new C5302a();
        C5302a c5302a2 = new C5302a();
        C5302a c5302a3 = new C5302a();
        if (zzaVar != null) {
            for (int i9 = 0; i9 < zzaVar.zza(); i9++) {
                zzca.zza.C6857zza c6857zzaZzbo = zzaVar.zza(i9).zzbo();
                if (TextUtils.isEmpty(c6857zzaZzbo.zza())) {
                    zzq().zzh().zza("EventConfig contained null event name");
                } else {
                    String strZza = c6857zzaZzbo.zza();
                    String strZzb = zzgy.zzb(c6857zzaZzbo.zza());
                    if (!TextUtils.isEmpty(strZzb)) {
                        c6857zzaZzbo = c6857zzaZzbo.zza(strZzb);
                        zzaVar.zza(i9, c6857zzaZzbo);
                    }
                    if (zzlj.zzb() && zzs().zza(zzat.zzct)) {
                        c5302a.put(strZza, Boolean.valueOf(c6857zzaZzbo.zzb()));
                    } else {
                        c5302a.put(c6857zzaZzbo.zza(), Boolean.valueOf(c6857zzaZzbo.zzb()));
                    }
                    c5302a2.put(c6857zzaZzbo.zza(), Boolean.valueOf(c6857zzaZzbo.zzc()));
                    if (c6857zzaZzbo.zzd()) {
                        if (c6857zzaZzbo.zze() >= zzc && c6857zzaZzbo.zze() <= zzb) {
                            c5302a3.put(c6857zzaZzbo.zza(), Integer.valueOf(c6857zzaZzbo.zze()));
                        } else {
                            zzq().zzh().zza("Invalid sampling rate. Event name, sample rate", c6857zzaZzbo.zza(), Integer.valueOf(c6857zzaZzbo.zze()));
                        }
                    }
                }
            }
        }
        this.zze.put(str, c5302a);
        this.zzf.put(str, c5302a2);
        this.zzh.put(str, c5302a3);
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    public final /* bridge */ /* synthetic */ zzac zzi() {
        return super.zzi();
    }

    public final boolean zza(String str, byte[] bArr, String str2) {
        zzaj();
        zzc();
        Preconditions.checkNotEmpty(str);
        zzca.zzb.zza zzaVarZzbo = zza(str, bArr).zzbo();
        if (zzaVarZzbo == null) {
            return false;
        }
        zza(str, zzaVarZzbo);
        this.zzg.put(str, (zzca.zzb) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo.zzy()));
        this.zzi.put(str, str2);
        this.zzd.put(str, zza((zzca.zzb) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo.zzy())));
        zzi().zza(str, new ArrayList(zzaVarZzbo.zzb()));
        try {
            zzaVarZzbo.zzc();
            bArr = ((zzca.zzb) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo.zzy())).zzbk();
        } catch (RuntimeException e9) {
            zzq().zzh().zza("Unable to serialize reduced-size config. Storing full config instead. appId", zzex.zza(str), e9);
        }
        zzac zzacVarZzi = zzi();
        Preconditions.checkNotEmpty(str);
        zzacVarZzi.zzc();
        zzacVarZzi.zzaj();
        new ContentValues().put("remote_config", bArr);
        try {
            if (zzacVarZzi.m17537c_().update("apps", r2, "app_id = ?", new String[]{str}) == 0) {
                zzacVarZzi.zzq().zze().zza("Failed to update remote config (got 0). appId", zzex.zza(str));
            }
        } catch (SQLiteException e10) {
            zzacVarZzi.zzq().zze().zza("Error storing remote config. appId", zzex.zza(str), e10);
        }
        this.zzg.put(str, (zzca.zzb) ((com.google.android.gms.internal.measurement.zzhv) zzaVarZzbo.zzy()));
        return true;
    }

    private final zzca.zzb zza(String str, byte[] bArr) {
        if (bArr == null) {
            return zzca.zzb.zzj();
        }
        try {
            zzca.zzb zzbVar = (zzca.zzb) ((com.google.android.gms.internal.measurement.zzhv) ((zzca.zzb.zza) zzkt.zza(zzca.zzb.zzi(), bArr)).zzy());
            zzq().zzw().zza("Parsed config. version, gmp_app_id", zzbVar.zza() ? Long.valueOf(zzbVar.zzb()) : null, zzbVar.zzc() ? zzbVar.zzd() : null);
            return zzbVar;
        } catch (com.google.android.gms.internal.measurement.zzig e9) {
            zzq().zzh().zza("Unable to merge remote config. appId", zzex.zza(str), e9);
            return zzca.zzb.zzj();
        } catch (RuntimeException e10) {
            zzq().zzh().zza("Unable to merge remote config. appId", zzex.zza(str), e10);
            return zzca.zzb.zzj();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }
}
