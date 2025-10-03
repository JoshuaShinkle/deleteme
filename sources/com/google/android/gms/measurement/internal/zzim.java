package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zznf;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public final class zzim extends zzg {

    @VisibleForTesting
    protected zzin zza;
    private volatile zzin zzb;
    private zzin zzc;
    private final Map<Activity, zzin> zzd;
    private Activity zze;
    private volatile boolean zzf;
    private volatile zzin zzg;
    private zzin zzh;
    private boolean zzi;
    private final Object zzj;
    private zzin zzk;
    private String zzl;

    public zzim(zzgb zzgbVar) {
        super(zzgbVar);
        this.zzj = new Object();
        this.zzd = new ConcurrentHashMap();
    }

    private final zzin zzd(Activity activity) {
        Preconditions.checkNotNull(activity);
        zzin zzinVar = this.zzd.get(activity);
        if (zzinVar == null) {
            zzin zzinVar2 = new zzin(null, zza(activity.getClass().getCanonicalName()), zzo().zzf());
            this.zzd.put(activity, zzinVar2);
            zzinVar = zzinVar2;
        }
        return (zzs().zza(zzat.zzbw) && this.zzg != null) ? this.zzg : zzinVar;
    }

    public final zzin zza(boolean z8) {
        zzv();
        zzc();
        if (!zzs().zza(zzat.zzbw) || !z8) {
            return this.zza;
        }
        zzin zzinVar = this.zza;
        return zzinVar != null ? zzinVar : this.zzh;
    }

    public final zzin zzaa() {
        return this.zzb;
    }

    public final void zzb(Activity activity) {
        if (zzs().zza(zzat.zzbw)) {
            synchronized (this.zzj) {
                this.zzi = false;
                this.zzf = true;
            }
        }
        long jElapsedRealtime = zzl().elapsedRealtime();
        if (zzs().zza(zzat.zzbv) && !zzs().zzh().booleanValue()) {
            this.zzb = null;
            zzp().zza(new zziq(this, jElapsedRealtime));
        } else {
            zzin zzinVarZzd = zzd(activity);
            this.zzc = this.zzb;
            this.zzb = null;
            zzp().zza(new zzit(this, zzinVarZzd, jElapsedRealtime));
        }
    }

    public final void zzc(Activity activity) {
        synchronized (this.zzj) {
            if (activity == this.zze) {
                this.zze = null;
            }
        }
        if (zzs().zzh().booleanValue()) {
            this.zzd.remove(activity);
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzhe zze() {
        return super.zze();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzeq zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zziv zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzim zzh() {
        return super.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzet zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzkb zzj() {
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

    @Override // com.google.android.gms.measurement.internal.zzg
    public final boolean zzy() {
        return false;
    }

    public final void zza(Bundle bundle, long j9) {
        String string;
        String str;
        String strZza;
        if (!zzs().zza(zzat.zzbw)) {
            zzq().zzj().zza("Manual screen reporting is disabled.");
            return;
        }
        synchronized (this.zzj) {
            if (!this.zzi) {
                zzq().zzj().zza("Cannot log screen view event when the app is in the background.");
                return;
            }
            if (bundle != null) {
                String string2 = bundle.getString(FirebaseAnalytics.Param.SCREEN_NAME);
                if (string2 != null && (string2.length() <= 0 || string2.length() > 100)) {
                    zzq().zzj().zza("Invalid screen name length for screen view. Length", Integer.valueOf(string2.length()));
                    return;
                }
                string = bundle.getString(FirebaseAnalytics.Param.SCREEN_CLASS);
                if (string != null && (string.length() <= 0 || string.length() > 100)) {
                    zzq().zzj().zza("Invalid screen class length for screen view. Length", Integer.valueOf(string.length()));
                    return;
                }
                str = string2;
            } else {
                string = null;
                str = null;
            }
            if (string == null) {
                Activity activity = this.zze;
                strZza = activity != null ? zza(activity.getClass().getCanonicalName()) : "Activity";
            } else {
                strZza = string;
            }
            if (this.zzf && this.zzb != null) {
                this.zzf = false;
                boolean zZzc = zzkx.zzc(this.zzb.zzb, strZza);
                boolean zZzc2 = zzkx.zzc(this.zzb.zza, str);
                if (zZzc && zZzc2) {
                    zzq().zzj().zza("Ignoring call to log screen view event with duplicate parameters.");
                    return;
                }
            }
            zzq().zzw().zza("Logging screen view with name, class", str == null ? "null" : str, strZza == null ? "null" : strZza);
            zzin zzinVar = this.zzb == null ? this.zzc : this.zzb;
            zzin zzinVar2 = new zzin(str, strZza, zzo().zzf(), true, j9);
            this.zzb = zzinVar2;
            this.zzc = zzinVar;
            this.zzg = zzinVar2;
            zzp().zza(new zzip(this, bundle, zzinVar2, zzinVar, zzl().elapsedRealtime()));
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zza zzd() {
        return super.zzd();
    }

    public final void zzb(Activity activity, Bundle bundle) {
        zzin zzinVar;
        if (!zzs().zzh().booleanValue() || bundle == null || (zzinVar = this.zzd.get(activity)) == null) {
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putLong(TtmlNode.ATTR_ID, zzinVar.zzc);
        bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, zzinVar.zza);
        bundle2.putString("referrer_name", zzinVar.zzb);
        bundle.putBundle("com.google.app_measurement.screen_service", bundle2);
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(Bundle bundle, zzin zzinVar, zzin zzinVar2, long j9) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (bundle != null) {
            bundle.remove(FirebaseAnalytics.Param.SCREEN_NAME);
            bundle.remove(FirebaseAnalytics.Param.SCREEN_CLASS);
        }
        zza(zzinVar, zzinVar2, j9, true, zzo().zza((String) null, FirebaseAnalytics.Event.SCREEN_VIEW, bundle, (List<String>) null, true, true));
    }

    @Deprecated
    public final void zza(Activity activity, String str, String str2) {
        if (!zzs().zzh().booleanValue()) {
            zzq().zzj().zza("setCurrentScreen cannot be called while screen reporting is disabled.");
            return;
        }
        if (this.zzb == null) {
            zzq().zzj().zza("setCurrentScreen cannot be called while no activity active");
            return;
        }
        if (this.zzd.get(activity) == null) {
            zzq().zzj().zza("setCurrentScreen must be called with an activity in the activity lifecycle");
            return;
        }
        if (str2 == null) {
            str2 = zza(activity.getClass().getCanonicalName());
        }
        boolean zZzc = zzkx.zzc(this.zzb.zzb, str2);
        boolean zZzc2 = zzkx.zzc(this.zzb.zza, str);
        if (zZzc && zZzc2) {
            zzq().zzj().zza("setCurrentScreen cannot be called with the same class and name");
            return;
        }
        if (str != null && (str.length() <= 0 || str.length() > 100)) {
            zzq().zzj().zza("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            return;
        }
        if (str2 != null && (str2.length() <= 0 || str2.length() > 100)) {
            zzq().zzj().zza("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            return;
        }
        zzq().zzw().zza("Setting current screen to name, class", str == null ? "null" : str, str2);
        zzin zzinVar = new zzin(str, str2, zzo().zzf());
        this.zzd.put(activity, zzinVar);
        zza(activity, zzinVar, true);
    }

    private final void zza(Activity activity, zzin zzinVar, boolean z8) {
        zzin zzinVar2;
        zzin zzinVar3 = this.zzb == null ? this.zzc : this.zzb;
        if (zzinVar.zzb == null) {
            zzinVar2 = new zzin(zzinVar.zza, activity != null ? zza(activity.getClass().getCanonicalName()) : null, zzinVar.zzc, zzinVar.zze, zzinVar.zzf);
        } else {
            zzinVar2 = zzinVar;
        }
        this.zzc = this.zzb;
        this.zzb = zzinVar2;
        zzp().zza(new zzio(this, zzinVar2, zzinVar3, zzl().elapsedRealtime(), z8));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0115  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zza(zzin zzinVar, zzin zzinVar2, long j9, boolean z8, Bundle bundle) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        boolean z9;
        zzin zzinVar3;
        long jZzb;
        zzc();
        if (zzs().zza(zzat.zzat)) {
            z9 = z8 && this.zza != null;
            if (z9) {
                zza(this.zza, true, j9);
            }
        } else {
            if (z8 && (zzinVar3 = this.zza) != null) {
                zza(zzinVar3, true, j9);
            }
            z9 = false;
        }
        if ((zzinVar2 != null && zzinVar2.zzc == zzinVar.zzc && zzkx.zzc(zzinVar2.zzb, zzinVar.zzb) && zzkx.zzc(zzinVar2.zza, zzinVar.zza)) ? false : true) {
            Bundle bundle2 = new Bundle();
            if (zzs().zza(zzat.zzbw)) {
                bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            }
            Bundle bundle3 = bundle2;
            zza(zzinVar, bundle3, true);
            if (zzinVar2 != null) {
                String str = zzinVar2.zza;
                if (str != null) {
                    bundle3.putString("_pn", str);
                }
                String str2 = zzinVar2.zzb;
                if (str2 != null) {
                    bundle3.putString("_pc", str2);
                }
                bundle3.putLong("_pi", zzinVar2.zzc);
            }
            if (zzs().zza(zzat.zzat) && z9) {
                if (zznf.zzb() && zzs().zza(zzat.zzav)) {
                    jZzb = zzj().zza(j9);
                } else {
                    jZzb = zzj().zzb.zzb();
                }
                if (jZzb > 0) {
                    zzo().zza(bundle3, jZzb);
                }
            }
            String str3 = "auto";
            if (zzs().zza(zzat.zzbw)) {
                if (!zzs().zzh().booleanValue()) {
                    bundle3.putLong("_mst", 1L);
                }
                if (zzinVar.zze) {
                    str3 = "app";
                }
            }
            String str4 = str3;
            if (zzs().zza(zzat.zzbw)) {
                long jCurrentTimeMillis = zzl().currentTimeMillis();
                if (zzinVar.zze) {
                    long j10 = zzinVar.zzf;
                    long j11 = j10 != 0 ? j10 : jCurrentTimeMillis;
                    zze().zza(str4, "_vs", j11, bundle3);
                }
            } else {
                zze().zzb(str4, "_vs", bundle3);
            }
        }
        this.zza = zzinVar;
        if (zzs().zza(zzat.zzbw) && zzinVar.zze) {
            this.zzh = zzinVar;
        }
        zzg().zza(zzinVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzin zzinVar, boolean z8, long j9) {
        zzd().zza(zzl().elapsedRealtime());
        if (!zzj().zza(zzinVar != null && zzinVar.zzd, z8, j9) || zzinVar == null) {
            return;
        }
        zzinVar.zzd = false;
    }

    public static void zza(zzin zzinVar, Bundle bundle, boolean z8) {
        if (bundle == null || zzinVar == null || (bundle.containsKey("_sc") && !z8)) {
            if (bundle != null && zzinVar == null && z8) {
                bundle.remove("_sn");
                bundle.remove("_sc");
                bundle.remove("_si");
                return;
            }
            return;
        }
        String str = zzinVar.zza;
        if (str != null) {
            bundle.putString("_sn", str);
        } else {
            bundle.remove("_sn");
        }
        String str2 = zzinVar.zzb;
        if (str2 != null) {
            bundle.putString("_sc", str2);
        } else {
            bundle.remove("_sc");
        }
        bundle.putLong("_si", zzinVar.zzc);
    }

    public final void zza(String str, zzin zzinVar) {
        zzc();
        synchronized (this) {
            String str2 = this.zzl;
            if (str2 == null || str2.equals(str) || zzinVar != null) {
                this.zzl = str;
                this.zzk = zzinVar;
            }
        }
    }

    @VisibleForTesting
    private static String zza(String str) {
        String[] strArrSplit = str.split("\\.");
        String str2 = strArrSplit.length > 0 ? strArrSplit[strArrSplit.length - 1] : "";
        return str2.length() > 100 ? str2.substring(0, 100) : str2;
    }

    public final void zza(Activity activity, Bundle bundle) {
        Bundle bundle2;
        if (!zzs().zzh().booleanValue() || bundle == null || (bundle2 = bundle.getBundle("com.google.app_measurement.screen_service")) == null) {
            return;
        }
        this.zzd.put(activity, new zzin(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.NAME), bundle2.getString("referrer_name"), bundle2.getLong(TtmlNode.ATTR_ID)));
    }

    public final void zza(Activity activity) {
        if (zzs().zza(zzat.zzbw)) {
            synchronized (this.zzj) {
                this.zzi = true;
                if (activity != this.zze) {
                    synchronized (this.zzj) {
                        this.zze = activity;
                        this.zzf = false;
                    }
                    if (zzs().zza(zzat.zzbv) && zzs().zzh().booleanValue()) {
                        this.zzg = null;
                        zzp().zza(new zzis(this));
                    }
                }
            }
        }
        if (zzs().zza(zzat.zzbv) && !zzs().zzh().booleanValue()) {
            this.zzb = this.zzg;
            zzp().zza(new zzir(this));
        } else {
            zza(activity, zzd(activity), false);
            zza zzaVarZzd = zzd();
            zzaVarZzd.zzp().zza(new zze(zzaVarZzd, zzaVarZzd.zzl().elapsedRealtime()));
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    public static /* synthetic */ zzin zza(zzim zzimVar, zzin zzinVar) {
        zzimVar.zzh = null;
        return null;
    }
}
