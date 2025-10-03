package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzmb;

/* loaded from: classes2.dex */
final class zzfj extends zzgx {

    @VisibleForTesting
    static final Pair<String, Long> zza = new Pair<>("", 0L);
    private String zzaa;
    private boolean zzab;
    private long zzac;
    public zzfm zzb;
    public final zzfn zzc;
    public final zzfn zzd;
    public final zzfn zze;
    public final zzfn zzf;
    public final zzfn zzg;
    public final zzfn zzh;
    public final zzfn zzi;
    public final zzfp zzj;
    public final zzfn zzk;
    public final zzfn zzl;
    public final zzfl zzm;
    public final zzfp zzn;
    public final zzfl zzo;
    public final zzfn zzp;
    public boolean zzq;
    public zzfl zzr;
    public zzfl zzs;
    public zzfn zzt;
    public final zzfp zzu;
    public final zzfp zzv;
    public final zzfn zzw;
    public final zzfk zzx;
    private SharedPreferences zzz;

    public zzfj(zzgb zzgbVar) {
        super(zzgbVar);
        this.zzc = new zzfn(this, "last_upload", 0L);
        this.zzd = new zzfn(this, "last_upload_attempt", 0L);
        this.zze = new zzfn(this, "backoff", 0L);
        this.zzf = new zzfn(this, "last_delete_stale", 0L);
        this.zzk = new zzfn(this, "time_before_start", 10000L);
        this.zzl = new zzfn(this, "session_timeout", 1800000L);
        this.zzm = new zzfl(this, "start_new_session", true);
        this.zzp = new zzfn(this, "last_pause_time", 0L);
        this.zzn = new zzfp(this, "non_personalized_ads", null);
        this.zzo = new zzfl(this, "allow_remote_dynamite", false);
        this.zzg = new zzfn(this, "midnight_offset", 0L);
        this.zzh = new zzfn(this, "first_open_time", 0L);
        this.zzi = new zzfn(this, "app_install_time", 0L);
        this.zzj = new zzfp(this, "app_instance_id", null);
        this.zzr = new zzfl(this, "app_backgrounded", false);
        this.zzs = new zzfl(this, "deep_link_retrieval_complete", false);
        this.zzt = new zzfn(this, "deep_link_retrieval_attempts", 0L);
        this.zzu = new zzfp(this, "firebase_feature_rollouts", null);
        this.zzv = new zzfp(this, "deferred_attribution_cache", null);
        this.zzw = new zzfn(this, "deferred_attribution_cache_timestamp", 0L);
        this.zzx = new zzfk(this, "default_event_parameters", null);
    }

    @Override // com.google.android.gms.measurement.internal.zzgx
    /* renamed from: g_ */
    public final void mo17541g_() {
        SharedPreferences sharedPreferences = zzm().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzz = sharedPreferences;
        boolean z8 = sharedPreferences.getBoolean("has_been_opened", false);
        this.zzq = z8;
        if (!z8) {
            SharedPreferences.Editor editorEdit = this.zzz.edit();
            editorEdit.putBoolean("has_been_opened", true);
            editorEdit.apply();
        }
        this.zzb = new zzfm(this, "health_monitor", Math.max(0L, zzat.zzb.zza(null).longValue()));
    }

    public final Pair<String, Boolean> zza(String str) {
        zzc();
        long jElapsedRealtime = zzl().elapsedRealtime();
        if (this.zzaa != null && jElapsedRealtime < this.zzac) {
            return new Pair<>(this.zzaa, Boolean.valueOf(this.zzab));
        }
        this.zzac = jElapsedRealtime + zzs().zze(str);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(zzm());
            if (advertisingIdInfo != null) {
                this.zzaa = advertisingIdInfo.getId();
                this.zzab = advertisingIdInfo.isLimitAdTrackingEnabled();
            }
            if (this.zzaa == null) {
                this.zzaa = "";
            }
        } catch (Exception e9) {
            zzq().zzv().zza("Unable to get advertising id", e9);
            this.zzaa = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.zzaa, Boolean.valueOf(this.zzab));
    }

    public final void zzb(String str) {
        zzc();
        SharedPreferences.Editor editorEdit = zzf().edit();
        editorEdit.putString("gmp_app_id", str);
        editorEdit.apply();
    }

    public final void zzc(String str) {
        zzc();
        SharedPreferences.Editor editorEdit = zzf().edit();
        editorEdit.putString("admob_app_id", str);
        editorEdit.apply();
    }

    @Override // com.google.android.gms.measurement.internal.zzgx
    public final boolean zzd() {
        return true;
    }

    @VisibleForTesting
    public final SharedPreferences zzf() {
        zzc();
        zzaa();
        return this.zzz;
    }

    public final String zzg() {
        zzc();
        return zzf().getString("gmp_app_id", null);
    }

    public final String zzh() {
        zzc();
        return zzf().getString("admob_app_id", null);
    }

    public final Boolean zzi() {
        zzc();
        if (zzf().contains("use_service")) {
            return Boolean.valueOf(zzf().getBoolean("use_service", false));
        }
        return null;
    }

    public final void zzj() {
        zzc();
        Boolean boolZzu = zzu();
        SharedPreferences.Editor editorEdit = zzf().edit();
        editorEdit.clear();
        editorEdit.apply();
        if (boolZzu != null) {
            zza(boolZzu);
        }
    }

    public final Boolean zzu() {
        zzc();
        if (zzf().contains("measurement_enabled")) {
            return Boolean.valueOf(zzf().getBoolean("measurement_enabled", true));
        }
        return null;
    }

    public final Boolean zzv() {
        if (!zzmb.zzb() || !zzs().zza(zzat.zzco)) {
            return null;
        }
        zzc();
        if (zzf().contains("measurement_enabled_from_api")) {
            return Boolean.valueOf(zzf().getBoolean("measurement_enabled_from_api", true));
        }
        return null;
    }

    public final zzad zzw() {
        zzc();
        return zzad.zza(zzf().getString("consent_settings", "G1"));
    }

    public final String zzx() {
        zzc();
        String string = zzf().getString("previous_os_version", null);
        zzk().zzaa();
        String str = Build.VERSION.RELEASE;
        if (!TextUtils.isEmpty(str) && !str.equals(string)) {
            SharedPreferences.Editor editorEdit = zzf().edit();
            editorEdit.putString("previous_os_version", str);
            editorEdit.apply();
        }
        return string;
    }

    public final boolean zzy() {
        return this.zzz.contains("deferred_analytics_collection");
    }

    public final void zzb(Boolean bool) {
        if (zzmb.zzb() && zzs().zza(zzat.zzco)) {
            zzc();
            SharedPreferences.Editor editorEdit = zzf().edit();
            if (bool != null) {
                editorEdit.putBoolean("measurement_enabled_from_api", bool.booleanValue());
            } else {
                editorEdit.remove("measurement_enabled_from_api");
            }
            editorEdit.apply();
        }
    }

    public final void zzb(boolean z8) {
        zzc();
        zzq().zzw().zza("App measurement setting deferred collection", Boolean.valueOf(z8));
        SharedPreferences.Editor editorEdit = zzf().edit();
        editorEdit.putBoolean("deferred_analytics_collection", z8);
        editorEdit.apply();
    }

    public final void zza(boolean z8) {
        zzc();
        SharedPreferences.Editor editorEdit = zzf().edit();
        editorEdit.putBoolean("use_service", z8);
        editorEdit.apply();
    }

    public final void zza(Boolean bool) {
        zzc();
        SharedPreferences.Editor editorEdit = zzf().edit();
        if (bool != null) {
            editorEdit.putBoolean("measurement_enabled", bool.booleanValue());
        } else {
            editorEdit.remove("measurement_enabled");
        }
        editorEdit.apply();
    }

    public final boolean zza(zzad zzadVar, int i9) {
        if (!zzmb.zzb() || !zzs().zza(zzat.zzco)) {
            return false;
        }
        zzc();
        if (!zza(i9)) {
            return false;
        }
        SharedPreferences.Editor editorEdit = zzf().edit();
        editorEdit.putString("consent_settings", zzadVar.zza());
        editorEdit.putInt("consent_source", i9);
        editorEdit.apply();
        return true;
    }

    public final boolean zza(int i9) {
        return zzad.zza(i9, zzf().getInt("consent_source", 100));
    }

    public final boolean zza(long j9) {
        return j9 - this.zzl.zza() > this.zzp.zza();
    }
}
