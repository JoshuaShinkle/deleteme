package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzma;
import com.google.android.gms.internal.measurement.zznq;
import com.google.android.gms.internal.measurement.zzod;
import com.google.firebase.iid.ServiceStarter;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public final class zzy extends zzgu {
    private Boolean zza;
    private zzaa zzb;
    private Boolean zzc;

    public zzy(zzgb zzgbVar) {
        super(zzgbVar);
        this.zzb = zzab.zza;
    }

    public static long zzi() {
        return zzat.zzac.zza(null).longValue();
    }

    public static long zzj() {
        return zzat.zzc.zza(null).longValue();
    }

    @VisibleForTesting
    private final Bundle zzx() {
        try {
            if (zzm().getPackageManager() == null) {
                zzq().zze().zza("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = Wrappers.packageManager(zzm()).getApplicationInfo(zzm().getPackageName(), 128);
            if (applicationInfo != null) {
                return applicationInfo.metaData;
            }
            zzq().zze().zza("Failed to load metadata: ApplicationInfo is null");
            return null;
        } catch (PackageManager.NameNotFoundException e9) {
            zzq().zze().zza("Failed to load metadata: Package name not found", e9);
            return null;
        }
    }

    public final void zza(zzaa zzaaVar) {
        this.zzb = zzaaVar;
    }

    public final int zzb(String str) {
        return (zzma.zzb() && zzd(null, zzat.zzcd)) ? zza(str, zzat.zzag, ServiceStarter.ERROR_UNKNOWN, 2000) : ServiceStarter.ERROR_UNKNOWN;
    }

    public final int zzc(String str) {
        return zzb(str, zzat.zzn);
    }

    public final int zzd() {
        if (zzma.zzb() && zzs().zzd(null, zzat.zzce)) {
            zzkx zzkxVarZzo = zzo();
            Boolean boolZzaf = zzkxVarZzo.zzy.zzv().zzaf();
            if (zzkxVarZzo.zzi() >= 201500 || !(boolZzaf == null || boolZzaf.booleanValue())) {
                return 100;
            }
        }
        return 25;
    }

    public final long zze(String str) {
        return zza(str, zzat.zza);
    }

    @VisibleForTesting
    public final Boolean zzf(String str) {
        Preconditions.checkNotEmpty(str);
        Bundle bundleZzx = zzx();
        if (bundleZzx == null) {
            zzq().zze().zza("Failed to load metadata: Metadata bundle is null");
            return null;
        }
        if (bundleZzx.containsKey(str)) {
            return Boolean.valueOf(bundleZzx.getBoolean(str));
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x002b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List<String> zzg(String str) throws Resources.NotFoundException {
        Integer numValueOf;
        Preconditions.checkNotEmpty(str);
        Bundle bundleZzx = zzx();
        if (bundleZzx != null) {
            if (bundleZzx.containsKey(str)) {
                numValueOf = Integer.valueOf(bundleZzx.getInt(str));
            }
            if (numValueOf != null) {
                return null;
            }
            try {
                String[] stringArray = zzm().getResources().getStringArray(numValueOf.intValue());
                if (stringArray == null) {
                    return null;
                }
                return Arrays.asList(stringArray);
            } catch (Resources.NotFoundException e9) {
                zzq().zze().zza("Failed to load string array from metadata: resource not found", e9);
                return null;
            }
        }
        zzq().zze().zza("Failed to load metadata: Metadata bundle is null");
        numValueOf = null;
        if (numValueOf != null) {
        }
    }

    public final Boolean zzh() {
        if (!zzod.zzb() || !zza(zzat.zzbv)) {
            return Boolean.TRUE;
        }
        Boolean boolZzf = zzf("google_analytics_automatic_screen_reporting_enabled");
        return Boolean.valueOf(boolZzf == null || boolZzf.booleanValue());
    }

    public final String zzk(String str) {
        zzem<String> zzemVar = zzat.zzak;
        return str == null ? zzemVar.zza(null) : zzemVar.zza(this.zzb.zza(str, zzemVar.zza()));
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

    public final String zzu() {
        return zza("debug.firebase.analytics.app", "");
    }

    public final String zzv() {
        return zza("debug.deferred.deeplink", "");
    }

    public final boolean zzw() {
        if (this.zza == null) {
            Boolean boolZzf = zzf("app_measurement_lite");
            this.zza = boolZzf;
            if (boolZzf == null) {
                this.zza = Boolean.FALSE;
            }
        }
        return this.zza.booleanValue() || !this.zzy.zzs();
    }

    public final int zza(String str) {
        return zza(str, zzat.zzah, 25, 100);
    }

    public final double zzc(String str, zzem<Double> zzemVar) {
        if (str == null) {
            return zzemVar.zza(null).doubleValue();
        }
        String strZza = this.zzb.zza(str, zzemVar.zza());
        if (TextUtils.isEmpty(strZza)) {
            return zzemVar.zza(null).doubleValue();
        }
        try {
            return zzemVar.zza(Double.valueOf(Double.parseDouble(strZza))).doubleValue();
        } catch (NumberFormatException unused) {
            return zzemVar.zza(null).doubleValue();
        }
    }

    public final boolean zze() {
        if (this.zzc == null) {
            synchronized (this) {
                if (this.zzc == null) {
                    ApplicationInfo applicationInfo = zzm().getApplicationInfo();
                    String myProcessName = ProcessUtils.getMyProcessName();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        this.zzc = Boolean.valueOf(str != null && str.equals(myProcessName));
                    }
                    if (this.zzc == null) {
                        this.zzc = Boolean.TRUE;
                        zzq().zze().zza("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzc.booleanValue();
    }

    public final long zza(String str, zzem<Long> zzemVar) {
        if (str == null) {
            return zzemVar.zza(null).longValue();
        }
        String strZza = this.zzb.zza(str, zzemVar.zza());
        if (TextUtils.isEmpty(strZza)) {
            return zzemVar.zza(null).longValue();
        }
        try {
            return zzemVar.zza(Long.valueOf(Long.parseLong(strZza))).longValue();
        } catch (NumberFormatException unused) {
            return zzemVar.zza(null).longValue();
        }
    }

    public final int zzb(String str, zzem<Integer> zzemVar) {
        if (str == null) {
            return zzemVar.zza(null).intValue();
        }
        String strZza = this.zzb.zza(str, zzemVar.zza());
        if (TextUtils.isEmpty(strZza)) {
            return zzemVar.zza(null).intValue();
        }
        try {
            return zzemVar.zza(Integer.valueOf(Integer.parseInt(strZza))).intValue();
        } catch (NumberFormatException unused) {
            return zzemVar.zza(null).intValue();
        }
    }

    public final boolean zzi(String str) {
        return "1".equals(this.zzb.zza(str, "measurement.event_sampling_enabled"));
    }

    public final boolean zzj(String str) {
        return zzd(str, zzat.zzaj);
    }

    public final boolean zzh(String str) {
        return "1".equals(this.zzb.zza(str, "gaia_collection_enabled"));
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzal zzk() {
        return super.zzk();
    }

    public final boolean zzf() {
        Boolean boolZzf = zzf("firebase_analytics_collection_deactivated");
        return boolZzf != null && boolZzf.booleanValue();
    }

    public final int zzd(String str) {
        if (zzma.zzb() && zzd(null, zzat.zzcd)) {
            return zza(str, zzat.zzaf, 25, 100);
        }
        return 25;
    }

    public final Boolean zzg() {
        Boolean boolZzf = zzf("google_analytics_adid_collection_enabled");
        return Boolean.valueOf(boolZzf == null || boolZzf.booleanValue());
    }

    public final boolean zzd(String str, zzem<Boolean> zzemVar) {
        if (str == null) {
            return zzemVar.zza(null).booleanValue();
        }
        String strZza = this.zzb.zza(str, zzemVar.zza());
        if (TextUtils.isEmpty(strZza)) {
            return zzemVar.zza(null).booleanValue();
        }
        return zzemVar.zza(Boolean.valueOf(Boolean.parseBoolean(strZza))).booleanValue();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    private final int zza(String str, zzem<Integer> zzemVar, int i9, int i10) {
        return Math.max(Math.min(zzb(str, zzemVar), i10), i9);
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    public final boolean zze(String str, zzem<Boolean> zzemVar) {
        return zzd(str, zzemVar);
    }

    public final boolean zza(zzem<Boolean> zzemVar) {
        return zzd(null, zzemVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String zza(zzf zzfVar) {
        Uri.Builder builder = new Uri.Builder();
        String strZze = zzfVar.zze();
        if (TextUtils.isEmpty(strZze)) {
            if (zznq.zzb() && zzs().zzd(zzfVar.zzc(), zzat.zzbj)) {
                strZze = zzfVar.zzg();
                if (TextUtils.isEmpty(strZze)) {
                }
            } else {
                strZze = zzfVar.zzf();
            }
        }
        Uri.Builder builderEncodedAuthority = builder.scheme(zzat.zzd.zza(null)).encodedAuthority(zzat.zze.zza(null));
        String strValueOf = String.valueOf(strZze);
        builderEncodedAuthority.path(strValueOf.length() != 0 ? "config/app/".concat(strValueOf) : new String("config/app/")).appendQueryParameter("app_instance_id", zzfVar.zzd()).appendQueryParameter("platform", "android").appendQueryParameter("gmp_version", "31049");
        return builder.build().toString();
    }

    private final String zza(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (ClassNotFoundException e9) {
            zzq().zze().zza("Could not find SystemProperties class", e9);
            return str2;
        } catch (IllegalAccessException e10) {
            zzq().zze().zza("Could not access SystemProperties.get()", e10);
            return str2;
        } catch (NoSuchMethodException e11) {
            zzq().zze().zza("Could not find SystemProperties.get() method", e11);
            return str2;
        } catch (InvocationTargetException e12) {
            zzq().zze().zza("SystemProperties.get() threw an exception", e12);
            return str2;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }
}
