package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzlj;
import com.google.android.gms.internal.measurement.zzmb;
import com.google.android.gms.internal.measurement.zzmh;
import com.google.android.gms.internal.measurement.zzmn;
import com.google.android.gms.internal.measurement.zzmt;
import com.google.android.gms.internal.measurement.zzne;
import com.google.android.gms.internal.measurement.zznr;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p132m.C5302a;

/* loaded from: classes2.dex */
public final class zzhe extends zzg {

    @VisibleForTesting
    protected zzic zza;
    final zzp zzb;
    private zzhd zzc;
    private final Set<zzhc> zzd;
    private boolean zze;
    private final AtomicReference<String> zzf;
    private final Object zzg;
    private zzad zzh;
    private int zzi;
    private final AtomicLong zzj;
    private long zzk;
    private int zzl;

    @VisibleForTesting
    private boolean zzm;

    public zzhe(zzgb zzgbVar) {
        super(zzgbVar);
        this.zzd = new CopyOnWriteArraySet();
        this.zzg = new Object();
        this.zzm = true;
        this.zzf = new AtomicReference<>();
        this.zzh = new zzad(null, null);
        this.zzi = 100;
        this.zzk = -1L;
        this.zzl = 100;
        this.zzj = new AtomicLong(0L);
        this.zzb = new zzp(zzgbVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzal() {
        zzc();
        String strZza = zzr().zzn.zza();
        if (strZza != null) {
            if ("unset".equals(strZza)) {
                zza("app", "_npa", (Object) null, zzl().currentTimeMillis());
            } else {
                zza("app", "_npa", Long.valueOf("true".equals(strZza) ? 1L : 0L), zzl().currentTimeMillis());
            }
        }
        if (!this.zzy.zzaa() || !this.zzm) {
            zzq().zzv().zza("Updating Scion state (FE)");
            zzg().zzab();
            return;
        }
        zzq().zzv().zza("Recording app launch after enabling measurement for the first time (FE)");
        zzah();
        if (zzne.zzb() && zzs().zza(zzat.zzbr)) {
            zzj().zza.zza();
        }
        if (zzmt.zzb() && zzs().zza(zzat.zzbu)) {
            if (!(this.zzy.zze().zza.zzb().zzi.zza() > 0)) {
                zzfo zzfoVarZze = this.zzy.zze();
                zzfoVarZze.zza(zzfoVarZze.zza.zzm().getPackageName());
            }
        }
        if (zzs().zza(zzat.zzck)) {
            zzp().zza(new zzhi(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzd(Bundle bundle) {
        zzc();
        zzv();
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotEmpty(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        if (!this.zzy.zzaa()) {
            zzq().zzw().zza("Conditional property not cleared since app measurement is disabled");
        } else {
            try {
                zzg().zza(new zzw(bundle.getString("app_id"), bundle.getString("origin"), new zzkw(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME), 0L, null, null), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), bundle.getBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), null, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), null, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zzo().zza(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), bundle.getString("origin"), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), true, false, zzlj.zzb() && zzs().zza(zzat.zzcr))));
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    public final void zza(Boolean bool) {
        zzv();
        zzp().zza(new zzhy(this, bool));
    }

    public final void zzaa() {
        if (zzm().getApplicationContext() instanceof Application) {
            ((Application) zzm().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.zza);
        }
    }

    public final Boolean zzab() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) zzp().zza(atomicReference, 15000L, "boolean test flag value", new zzhj(this, atomicReference));
    }

    public final String zzac() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) zzp().zza(atomicReference, 15000L, "String test flag value", new zzht(this, atomicReference));
    }

    public final Long zzad() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) zzp().zza(atomicReference, 15000L, "long test flag value", new zzhx(this, atomicReference));
    }

    public final Integer zzae() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) zzp().zza(atomicReference, 15000L, "int test flag value", new zzhw(this, atomicReference));
    }

    public final Double zzaf() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) zzp().zza(atomicReference, 15000L, "double test flag value", new zzhz(this, atomicReference));
    }

    public final String zzag() {
        return this.zzf.get();
    }

    public final void zzah() {
        zzc();
        zzv();
        if (this.zzy.zzaf()) {
            if (zzs().zza(zzat.zzbd)) {
                Boolean boolZzf = zzs().zzf("google_analytics_deferred_deep_link_enabled");
                if (boolZzf != null && boolZzf.booleanValue()) {
                    zzq().zzv().zza("Deferred Deep Link feature enabled.");
                    zzp().zza(new Runnable(this) { // from class: com.google.android.gms.measurement.internal.zzhg
                        private final zzhe zza;

                        {
                            this.zza = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            zzhe zzheVar = this.zza;
                            zzheVar.zzc();
                            if (zzheVar.zzr().zzs.zza()) {
                                zzheVar.zzq().zzv().zza("Deferred Deep Link already retrieved. Not fetching again.");
                                return;
                            }
                            long jZza = zzheVar.zzr().zzt.zza();
                            zzheVar.zzr().zzt.zza(1 + jZza);
                            if (jZza < 5) {
                                zzheVar.zzy.zzag();
                            } else {
                                zzheVar.zzq().zzh().zza("Permanently failed to retrieve Deferred Deep Link. Reached maximum retries.");
                                zzheVar.zzr().zzs.zza(true);
                            }
                        }
                    });
                }
            }
            zzg().zzad();
            this.zzm = false;
            String strZzx = zzr().zzx();
            if (TextUtils.isEmpty(strZzx)) {
                return;
            }
            zzk().zzaa();
            if (strZzx.equals(Build.VERSION.RELEASE)) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("_po", strZzx);
            zza("auto", "_ou", bundle);
        }
    }

    public final String zzai() {
        zzin zzinVarZzaa = this.zzy.zzu().zzaa();
        if (zzinVarZzaa != null) {
            return zzinVarZzaa.zza;
        }
        return null;
    }

    public final String zzaj() {
        zzin zzinVarZzaa = this.zzy.zzu().zzaa();
        if (zzinVarZzaa != null) {
            return zzinVarZzaa.zzb;
        }
        return null;
    }

    public final String zzak() {
        if (this.zzy.zzn() != null) {
            return this.zzy.zzn();
        }
        try {
            return zzik.zza(zzm(), "google_app_id");
        } catch (IllegalStateException e9) {
            this.zzy.zzq().zze().zza("getGoogleAppId failed with exception", e9);
            return null;
        }
    }

    public final void zzb(String str, String str2, Bundle bundle) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        zzc();
        zza(str, str2, zzl().currentTimeMillis(), bundle);
    }

    public final void zzc(String str, String str2, Bundle bundle) {
        zzb((String) null, str, str2, bundle);
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzc(Bundle bundle) {
        zzc();
        zzv();
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotEmpty(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        Preconditions.checkNotEmpty(bundle.getString("origin"));
        Preconditions.checkNotNull(bundle.get("value"));
        if (!this.zzy.zzaa()) {
            zzq().zzw().zza("Conditional property not set since app measurement is disabled");
            return;
        }
        try {
            zzg().zza(new zzw(bundle.getString("app_id"), bundle.getString("origin"), new zzkw(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP), bundle.get("value"), bundle.getString("origin")), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), false, bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), zzo().zza(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS), bundle.getString("origin"), 0L, true, false, zzlj.zzb() && zzs().zza(zzat.zzcr)), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), zzo().zza(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS), bundle.getString("origin"), 0L, true, false, zzlj.zzb() && zzs().zza(zzat.zzcr)), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zzo().zza(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), bundle.getString("origin"), 0L, true, false, zzlj.zzb() && zzs().zza(zzat.zzcr))));
        } catch (IllegalArgumentException unused) {
        }
    }

    public final void zzb(zzhc zzhcVar) {
        zzv();
        Preconditions.checkNotNull(zzhcVar);
        if (this.zzd.remove(zzhcVar)) {
            return;
        }
        zzq().zzh().zza("OnEventListener had not been registered");
    }

    public final void zza(zzad zzadVar, int i9, long j9) {
        boolean z8;
        zzad zzadVar2;
        boolean z9;
        boolean zZza;
        if (zzmb.zzb() && zzs().zza(zzat.zzco)) {
            zzv();
            if (zzadVar.zzb() == null && zzadVar.zzd() == null) {
                zzq().zzj().zza("Discarding empty consent settings");
                return;
            }
            synchronized (this.zzg) {
                z8 = false;
                if (zzad.zza(i9, this.zzi)) {
                    zZza = zzadVar.zza(this.zzh);
                    if (zzadVar.zze() && !this.zzh.zze()) {
                        z8 = true;
                    }
                    zzad zzadVarZzc = zzadVar.zzc(this.zzh);
                    this.zzh = zzadVarZzc;
                    zzadVar2 = zzadVarZzc;
                    z9 = z8;
                    z8 = true;
                } else {
                    zzadVar2 = zzadVar;
                    z9 = false;
                    zZza = false;
                }
            }
            if (!z8) {
                zzq().zzu().zza("Ignoring lower-priority consent settings, proposed settings", zzadVar2);
                return;
            }
            long andIncrement = this.zzj.getAndIncrement();
            if (zZza) {
                zza((String) null);
                zzp().zzb(new zzib(this, zzadVar2, j9, i9, andIncrement, z9));
            } else {
                zzp().zza(new zzia(this, zzadVar2, i9, andIncrement, z9));
            }
        }
    }

    public final void zzb(Bundle bundle) {
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotEmpty(bundle.getString("app_id"));
        zza();
        zzb(new Bundle(bundle), zzl().currentTimeMillis());
    }

    private final void zzb(Bundle bundle, long j9) {
        Preconditions.checkNotNull(bundle);
        zzgz.zza(bundle, "app_id", String.class, null);
        zzgz.zza(bundle, "origin", String.class, null);
        zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.NAME, String.class, null);
        zzgz.zza(bundle, "value", Object.class, null);
        zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, String.class, null);
        zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.class, 0L);
        zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, String.class, null);
        zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, Bundle.class, null);
        zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, String.class, null);
        zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, Bundle.class, null);
        zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.class, 0L);
        zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, String.class, null);
        zzgz.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, Bundle.class, null);
        Preconditions.checkNotEmpty(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        Preconditions.checkNotEmpty(bundle.getString("origin"));
        Preconditions.checkNotNull(bundle.get("value"));
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, j9);
        String string = bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
        Object obj = bundle.get("value");
        if (zzo().zzb(string) != 0) {
            zzq().zze().zza("Invalid conditional user property name", zzn().zzc(string));
            return;
        }
        if (zzo().zzb(string, obj) != 0) {
            zzq().zze().zza("Invalid conditional user property value", zzn().zzc(string), obj);
            return;
        }
        Object objZzc = zzo().zzc(string, obj);
        if (objZzc == null) {
            zzq().zze().zza("Unable to normalize conditional user property value", zzn().zzc(string), obj);
            return;
        }
        zzgz.zza(bundle, objZzc);
        long j10 = bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT);
        if (!TextUtils.isEmpty(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME)) && (j10 > 15552000000L || j10 < 1)) {
            zzq().zze().zza("Invalid conditional user property timeout", zzn().zzc(string), Long.valueOf(j10));
            return;
        }
        long j11 = bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE);
        if (j11 <= 15552000000L && j11 >= 1) {
            zzp().zza(new zzhq(this, bundle));
        } else {
            zzq().zze().zza("Invalid conditional user property time to live", zzn().zzc(string), Long.valueOf(j11));
        }
    }

    public final void zza(zzad zzadVar) {
        zzc();
        boolean z8 = (zzadVar.zze() && zzadVar.zzc()) || zzg().zzai();
        if (z8 != this.zzy.zzac()) {
            this.zzy.zzb(z8);
            Boolean boolZzv = zzr().zzv();
            if (!z8 || boolZzv == null || boolZzv.booleanValue()) {
                zza(Boolean.valueOf(z8), false);
            }
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zza zzd() {
        return super.zzd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(Boolean bool, boolean z8) {
        zzc();
        zzv();
        zzq().zzv().zza("Setting app measurement enabled (FE)", bool);
        zzr().zza(bool);
        if (zzmb.zzb() && zzs().zza(zzat.zzco) && z8) {
            zzr().zzb(bool);
        }
        if (zzmb.zzb() && zzs().zza(zzat.zzco) && !this.zzy.zzac() && bool.booleanValue()) {
            return;
        }
        zzal();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzad zzadVar, int i9, long j9, boolean z8, boolean z9) {
        zzc();
        zzv();
        if (j9 <= this.zzk && zzad.zza(this.zzl, i9)) {
            zzq().zzu().zza("Dropped out-of-date consent setting, proposed settings", zzadVar);
            return;
        }
        if (zzr().zza(zzadVar, i9)) {
            this.zzk = j9;
            this.zzl = i9;
            zzg().zza(z8);
            if (z9) {
                zzg().zza(new AtomicReference<>());
                return;
            }
            return;
        }
        zzq().zzu().zza("Lower precedence consent source ignored, proposed source", Integer.valueOf(i9));
    }

    public final void zza(String str, String str2, Bundle bundle) {
        zza(str, str2, bundle, true, true, zzl().currentTimeMillis());
    }

    public final void zza(String str, String str2, long j9, Bundle bundle) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        zzc();
        zza(str, str2, j9, bundle, true, this.zzc == null || zzkx.zzd(str2), false, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:61:0x0146, code lost:
    
        r5 = 13;
     */
    /* JADX WARN: Removed duplicated region for block: B:161:0x03ea  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0404  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x045e  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x050c  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x051b  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x014f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zza(String str, String str2, long j9, Bundle bundle, boolean z8, boolean z9, boolean z10, String str3) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        String str4;
        long j10;
        long j11;
        boolean z11;
        String[] strArr;
        int length;
        int i9;
        int length2;
        boolean z12;
        ArrayList arrayList;
        String str5;
        String str6;
        ArrayList arrayList2;
        String str7;
        String str8;
        zzin zzinVar;
        Bundle bundle2;
        long j12;
        boolean z13;
        int i10;
        boolean z14;
        Class<?> cls;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(bundle);
        zzc();
        zzv();
        if (!this.zzy.zzaa()) {
            zzq().zzv().zza("Event not sent since app measurement is disabled");
            return;
        }
        List<String> listZzag = zzf().zzag();
        if (listZzag != null && !listZzag.contains(str2)) {
            zzq().zzv().zza("Dropping non-safelisted event. event name, origin", str2, str);
            return;
        }
        if (!this.zze) {
            this.zze = true;
            try {
                if (!this.zzy.zzs()) {
                    cls = Class.forName("com.google.android.gms.tagmanager.TagManagerService", true, zzm().getClassLoader());
                } else {
                    cls = Class.forName("com.google.android.gms.tagmanager.TagManagerService");
                }
                try {
                    cls.getDeclaredMethod("initialize", Context.class).invoke(null, zzm());
                } catch (Exception e9) {
                    zzq().zzh().zza("Failed to invoke Tag Manager's initialize() method", e9);
                }
            } catch (ClassNotFoundException unused) {
                zzq().zzu().zza("Tag Manager is not found and thus will not be used");
            }
        }
        if (zzs().zza(zzat.zzbe) && Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN.equals(str2) && bundle.containsKey("gclid")) {
            zza("auto", "_lgclid", bundle.getString("gclid"), zzl().currentTimeMillis());
        }
        if (zznr.zzb() && zzs().zza(zzat.zzcg) && z8 && zzkx.zzf(str2)) {
            zzo().zza(bundle, zzr().zzx.zza());
        }
        if (z10 && !"_iap".equals(str2)) {
            zzkx zzkxVarZzh = this.zzy.zzh();
            boolean z15 = zzlj.zzb() && zzs().zza(zzat.zzcr);
            int i11 = 2;
            if (zzkxVarZzh.zza("event", str2)) {
                if (z15) {
                    if (zzkxVarZzh.zza("event", 40, str2)) {
                        i11 = 0;
                    }
                } else if (zzkxVarZzh.zza("event", 40, str2)) {
                }
            }
            if (i11 != 0) {
                zzq().zzg().zza("Invalid public event name. Event will not be logged (FE)", zzn().zza(str2));
                this.zzy.zzh();
                this.zzy.zzh().zza(i11, "_ev", zzkx.zza(str2, 40, true), str2 != null ? str2.length() : 0);
                return;
            }
        }
        zzin zzinVarZza = zzh().zza(false);
        if (zzinVarZza != null && !bundle.containsKey("_sc")) {
            zzinVarZza.zzd = true;
        }
        zzim.zza(zzinVarZza, bundle, z8 && z10);
        boolean zEquals = "am".equals(str);
        boolean zZzd = zzkx.zzd(str2);
        if (z8 && this.zzc != null && !zZzd && !zEquals) {
            zzq().zzv().zza("Passing event to registered event handler (FE)", zzn().zza(str2), zzn().zza(bundle));
            this.zzc.interceptEvent(str, str2, bundle, j9);
            return;
        }
        if (this.zzy.zzaf()) {
            int iZza = zzo().zza(str2, zzlj.zzb() && zzs().zza(zzat.zzcr));
            if (iZza != 0) {
                zzq().zzg().zza("Invalid event name. Event will not be logged (FE)", zzn().zza(str2));
                zzo();
                this.zzy.zzh().zza(str3, iZza, "_ev", zzkx.zza(str2, 40, true), str2 != null ? str2.length() : 0);
                return;
            }
            List<String> listListOf = CollectionUtils.listOf((Object[]) new String[]{"_o", "_sn", "_sc", "_si"});
            Bundle bundleZza = zzo().zza(str3, str2, bundle, listListOf, z10, true);
            zzin zzinVar2 = (bundleZza != null && bundleZza.containsKey("_sc") && bundleZza.containsKey("_si")) ? new zzin(bundleZza.getString("_sn"), bundleZza.getString("_sc"), Long.valueOf(bundleZza.getLong("_si")).longValue()) : null;
            zzin zzinVar3 = zzinVar2 == null ? zzinVarZza : zzinVar2;
            String str9 = "_ae";
            if (!zzs().zza(zzat.zzat) || zzh().zza(false) == null) {
                str4 = str2;
            } else {
                str4 = str2;
                if ("_ae".equals(str4)) {
                    long jZzb = zzj().zzb.zzb();
                    if (jZzb > 0) {
                        zzo().zza(bundleZza, jZzb);
                    }
                }
            }
            if (zzmn.zzb() && zzs().zza(zzat.zzbq)) {
                if (!"auto".equals(str) && "_ssr".equals(str4)) {
                    zzkx zzkxVarZzo = zzo();
                    String string = bundleZza.getString("_ffr");
                    String strTrim = Strings.isEmptyOrWhitespace(string) ? null : string.trim();
                    if (zzkx.zzc(strTrim, zzkxVarZzo.zzr().zzu.zza())) {
                        zzkxVarZzo.zzq().zzv().zza("Not logging duplicate session_start_with_rollout event");
                        z14 = false;
                    } else {
                        zzkxVarZzo.zzr().zzu.zza(strTrim);
                        z14 = true;
                    }
                    if (!z14) {
                        return;
                    }
                } else if ("_ae".equals(str4)) {
                    String strZza = zzo().zzr().zzu.zza();
                    if (!TextUtils.isEmpty(strZza)) {
                        bundleZza.putString("_ffr", strZza);
                    }
                }
            }
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(bundleZza);
            long jNextLong = zzo().zzg().nextLong();
            if (zzr().zzp.zza() > 0) {
                j10 = j9;
                if (zzr().zza(j10) && zzr().zzr.zza()) {
                    zzq().zzw().zza("Current session is expired, remove the session number, ID, and engagement time");
                    j11 = jNextLong;
                    zza("auto", "_sid", (Object) null, zzl().currentTimeMillis());
                    zza("auto", "_sno", (Object) null, zzl().currentTimeMillis());
                    zza("auto", "_se", (Object) null, zzl().currentTimeMillis());
                }
                if (bundleZza.getLong(FirebaseAnalytics.Param.EXTEND_SESSION, 0L) != 1) {
                    zzq().zzw().zza("EXTEND_SESSION param attached: initiate a new session or extend the current active session");
                    z11 = true;
                    this.zzy.zzd().zza.zza(j10, true);
                } else {
                    z11 = true;
                }
                strArr = (String[]) bundleZza.keySet().toArray(new String[bundleZza.size()]);
                Arrays.sort(strArr);
                if (!zzmh.zzb() && zzs().zza(zzat.zzca) && zzs().zza(zzat.zzbz)) {
                    for (String str10 : strArr) {
                        zzo();
                        Bundle[] bundleArrZzb = zzkx.zzb(bundleZza.get(str10));
                        if (bundleArrZzb != null) {
                            bundleZza.putParcelableArray(str10, bundleArrZzb);
                        }
                    }
                    z12 = z11;
                    arrayList = arrayList3;
                    str5 = str4;
                    str6 = "_ae";
                } else {
                    length = strArr.length;
                    i9 = 0;
                    length2 = 0;
                    while (i9 < length) {
                        String str11 = strArr[i9];
                        Object obj = bundleZza.get(str11);
                        zzo();
                        String[] strArr2 = strArr;
                        Bundle[] bundleArrZzb2 = zzkx.zzb(obj);
                        int i12 = length;
                        if (bundleArrZzb2 != null) {
                            bundleZza.putInt(str11, bundleArrZzb2.length);
                            int i13 = 0;
                            while (i13 < bundleArrZzb2.length) {
                                Bundle bundle3 = bundleArrZzb2[i13];
                                zzim.zza(zzinVar3, bundle3, true);
                                ArrayList arrayList4 = arrayList3;
                                Bundle bundleZza2 = zzo().zza(str3, "_ep", bundle3, listListOf, z10, false);
                                bundleZza2.putString("_en", str2);
                                bundleZza2.putLong("_eid", j11);
                                bundleZza2.putString("_gn", str11);
                                bundleZza2.putInt("_ll", bundleArrZzb2.length);
                                bundleZza2.putInt("_i", i13);
                                arrayList4.add(bundleZza2);
                                i13++;
                                bundleZza = bundleZza;
                                zzinVar3 = zzinVar3;
                                str9 = str9;
                                str4 = str2;
                                arrayList3 = arrayList4;
                            }
                            arrayList2 = arrayList3;
                            str7 = str4;
                            str8 = str9;
                            zzinVar = zzinVar3;
                            bundle2 = bundleZza;
                            j12 = j11;
                            z13 = true;
                            length2 += bundleArrZzb2.length;
                        } else {
                            arrayList2 = arrayList3;
                            str7 = str4;
                            str8 = str9;
                            zzinVar = zzinVar3;
                            bundle2 = bundleZza;
                            j12 = j11;
                            z13 = true;
                        }
                        i9++;
                        strArr = strArr2;
                        bundleZza = bundle2;
                        j11 = j12;
                        zzinVar3 = zzinVar;
                        length = i12;
                        z11 = z13;
                        str9 = str8;
                        str4 = str7;
                        arrayList3 = arrayList2;
                    }
                    z12 = z11;
                    arrayList = arrayList3;
                    str5 = str4;
                    str6 = str9;
                    Bundle bundle4 = bundleZza;
                    long j13 = j11;
                    if (length2 != 0) {
                        bundle4.putLong("_eid", j13);
                        bundle4.putInt("_epc", length2);
                    }
                }
                i10 = 0;
                while (i10 < arrayList.size()) {
                    Bundle bundleZza3 = (Bundle) arrayList.get(i10);
                    String str12 = i10 != 0 ? z12 : false ? "_ep" : str5;
                    bundleZza3.putString("_o", str);
                    if (z9) {
                        bundleZza3 = zzo().zza(bundleZza3);
                    }
                    Bundle bundle5 = bundleZza3;
                    boolean z16 = z12;
                    zzg().zza(new zzar(str12, new zzam(bundle5), str, j9), str3);
                    if (!zEquals) {
                        Iterator<zzhc> it = this.zzd.iterator();
                        while (it.hasNext()) {
                            it.next().onEvent(str, str2, new Bundle(bundle5), j9);
                        }
                    }
                    i10++;
                    z12 = z16;
                }
                boolean z17 = z12;
                if (zzh().zza(false) == null && str6.equals(str5)) {
                    zzj().zza(z17, z17, zzl().elapsedRealtime());
                    return;
                }
            }
            j10 = j9;
            j11 = jNextLong;
            if (bundleZza.getLong(FirebaseAnalytics.Param.EXTEND_SESSION, 0L) != 1) {
            }
            strArr = (String[]) bundleZza.keySet().toArray(new String[bundleZza.size()]);
            Arrays.sort(strArr);
            if (zzmh.zzb()) {
                length = strArr.length;
                i9 = 0;
                length2 = 0;
                while (i9 < length) {
                }
                z12 = z11;
                arrayList = arrayList3;
                str5 = str4;
                str6 = str9;
                Bundle bundle42 = bundleZza;
                long j132 = j11;
                if (length2 != 0) {
                }
            }
            i10 = 0;
            while (i10 < arrayList.size()) {
            }
            boolean z172 = z12;
            if (zzh().zza(false) == null) {
            }
        }
    }

    private final void zzb(String str, String str2, String str3, Bundle bundle) {
        long jCurrentTimeMillis = zzl().currentTimeMillis();
        Preconditions.checkNotEmpty(str2);
        Bundle bundle2 = new Bundle();
        if (str != null) {
            bundle2.putString("app_id", str);
        }
        bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, str2);
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, jCurrentTimeMillis);
        if (str3 != null) {
            bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str3);
            bundle2.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle);
        }
        zzp().zza(new zzhs(this, bundle2));
    }

    @VisibleForTesting
    private final ArrayList<Bundle> zzb(String str, String str2, String str3) {
        if (zzp().zzf()) {
            zzq().zze().zza("Cannot get conditional user properties from analytics worker thread");
            return new ArrayList<>(0);
        }
        if (zzx.zza()) {
            zzq().zze().zza("Cannot get conditional user properties from main thread");
            return new ArrayList<>(0);
        }
        AtomicReference atomicReference = new AtomicReference();
        this.zzy.zzp().zza(atomicReference, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, "get conditional user properties", new zzhv(this, atomicReference, str, str2, str3));
        List list = (List) atomicReference.get();
        if (list == null) {
            zzq().zze().zza("Timed out waiting for get conditional user properties", str);
            return new ArrayList<>();
        }
        return zzkx.zzb((List<zzw>) list);
    }

    @VisibleForTesting
    private final Map<String, Object> zzb(String str, String str2, String str3, boolean z8) {
        if (zzp().zzf()) {
            zzq().zze().zza("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        }
        if (zzx.zza()) {
            zzq().zze().zza("Cannot get user properties from main thread");
            return Collections.emptyMap();
        }
        AtomicReference atomicReference = new AtomicReference();
        this.zzy.zzp().zza(atomicReference, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, "get user properties", new zzhu(this, atomicReference, str, str2, str3, z8));
        List<zzkw> list = (List) atomicReference.get();
        if (list == null) {
            zzq().zze().zza("Timed out waiting for handle get user properties, includeInternal", Boolean.valueOf(z8));
            return Collections.emptyMap();
        }
        C5302a c5302a = new C5302a(list.size());
        for (zzkw zzkwVar : list) {
            c5302a.put(zzkwVar.zza, zzkwVar.zza());
        }
        return c5302a;
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z8, boolean z9, long j9) {
        String str3 = str == null ? "app" : str;
        Bundle bundle2 = bundle == null ? new Bundle() : bundle;
        if (zzs().zza(zzat.zzbw) && zzkx.zzc(str2, FirebaseAnalytics.Event.SCREEN_VIEW)) {
            zzh().zza(bundle2, j9);
            return;
        }
        zzp().zza(new zzhn(this, str3, str2, j9, zzkx.zzb(bundle2), z9, !z9 || this.zzc == null || zzkx.zzd(str2), !z8, null));
    }

    public final void zza(String str, String str2, Object obj, boolean z8) {
        zza(str, str2, obj, true, zzl().currentTimeMillis());
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zza(String str, String str2, Object obj, boolean z8, long j9) {
        int iZzb;
        if (str == null) {
            str = "app";
        }
        String str3 = str;
        if (z8) {
            iZzb = zzo().zzb(str2);
        } else {
            zzkx zzkxVarZzo = zzo();
            if (zzkxVarZzo.zza("user property", str2)) {
                if (zzkxVarZzo.zza("user property", zzha.zza, str2)) {
                    iZzb = !zzkxVarZzo.zza("user property", 24, str2) ? 6 : 0;
                } else {
                    iZzb = 15;
                }
            }
        }
        if (iZzb != 0) {
            zzo();
            this.zzy.zzh().zza(iZzb, "_ev", zzkx.zza(str2, 24, true), str2 != null ? str2.length() : 0);
            return;
        }
        if (obj != null) {
            int iZzb2 = zzo().zzb(str2, obj);
            if (iZzb2 != 0) {
                zzo();
                this.zzy.zzh().zza(iZzb2, "_ev", zzkx.zza(str2, 24, true), ((obj instanceof String) || (obj instanceof CharSequence)) ? String.valueOf(obj).length() : 0);
                return;
            } else {
                Object objZzc = zzo().zzc(str2, obj);
                if (objZzc != null) {
                    zza(str3, str2, j9, objZzc);
                    return;
                }
                return;
            }
        }
        zza(str3, str2, j9, (Object) null);
    }

    private final void zza(String str, String str2, long j9, Object obj) {
        zzp().zza(new zzhm(this, str, str2, obj, j9));
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zza(String str, String str2, Object obj, long j9) {
        String str3;
        Object obj2;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzc();
        zzv();
        if (!FirebaseAnalytics.UserProperty.ALLOW_AD_PERSONALIZATION_SIGNALS.equals(str2)) {
            str3 = str2;
            obj2 = obj;
        } else if (obj instanceof String) {
            String str4 = (String) obj;
            if (TextUtils.isEmpty(str4)) {
                if (obj == null) {
                    zzr().zzn.zza("unset");
                    obj2 = obj;
                }
                str3 = str2;
                obj2 = obj;
            } else {
                Long lValueOf = Long.valueOf("false".equals(str4.toLowerCase(Locale.ENGLISH)) ? 1L : 0L);
                zzr().zzn.zza(lValueOf.longValue() == 1 ? "true" : "false");
                obj2 = lValueOf;
            }
            str3 = "_npa";
        }
        if (!this.zzy.zzaa()) {
            zzq().zzw().zza("User property not set since app measurement is disabled");
        } else if (this.zzy.zzaf()) {
            zzg().zza(new zzkw(str3, j9, obj2, str));
        }
    }

    public final List<zzkw> zza(boolean z8) {
        zzv();
        zzq().zzw().zza("Getting user properties (FE)");
        if (zzp().zzf()) {
            zzq().zze().zza("Cannot get all user properties from analytics worker thread");
            return Collections.emptyList();
        }
        if (zzx.zza()) {
            zzq().zze().zza("Cannot get all user properties from main thread");
            return Collections.emptyList();
        }
        AtomicReference atomicReference = new AtomicReference();
        this.zzy.zzp().zza(atomicReference, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, "get user properties", new zzhp(this, atomicReference, z8));
        List<zzkw> list = (List) atomicReference.get();
        if (list != null) {
            return list;
        }
        zzq().zze().zza("Timed out waiting for get user properties, includeInternal", Boolean.valueOf(z8));
        return Collections.emptyList();
    }

    public final void zza(String str) {
        this.zzf.set(str);
    }

    public final void zza(long j9, boolean z8) {
        zzc();
        zzv();
        zzq().zzv().zza("Resetting analytics data (FE)");
        zzkb zzkbVarZzj = zzj();
        zzkbVarZzj.zzc();
        zzkbVarZzj.zzb.zza();
        boolean zZzaa = this.zzy.zzaa();
        zzfj zzfjVarZzr = zzr();
        zzfjVarZzr.zzh.zza(j9);
        if (!TextUtils.isEmpty(zzfjVarZzr.zzr().zzu.zza())) {
            zzfjVarZzr.zzu.zza(null);
        }
        if (zzne.zzb() && zzfjVarZzr.zzs().zza(zzat.zzbr)) {
            zzfjVarZzr.zzp.zza(0L);
        }
        if (!zzfjVarZzr.zzs().zzf()) {
            zzfjVarZzr.zzb(!zZzaa);
        }
        zzfjVarZzr.zzv.zza(null);
        zzfjVarZzr.zzw.zza(0L);
        zzfjVarZzr.zzx.zza(null);
        if (z8) {
            zzg().zzac();
        }
        if (zzne.zzb() && zzs().zza(zzat.zzbr)) {
            zzj().zza.zza();
        }
        this.zzm = !zZzaa;
    }

    public final void zza(zzhd zzhdVar) {
        zzhd zzhdVar2;
        zzc();
        zzv();
        if (zzhdVar != null && zzhdVar != (zzhdVar2 = this.zzc)) {
            Preconditions.checkState(zzhdVar2 == null, "EventInterceptor already set.");
        }
        this.zzc = zzhdVar;
    }

    public final void zza(zzhc zzhcVar) {
        zzv();
        Preconditions.checkNotNull(zzhcVar);
        if (this.zzd.add(zzhcVar)) {
            return;
        }
        zzq().zzh().zza("OnEventListener already registered");
    }

    public final void zza(Bundle bundle) {
        zza(bundle, zzl().currentTimeMillis());
    }

    public final void zza(Bundle bundle, long j9) {
        Preconditions.checkNotNull(bundle);
        Bundle bundle2 = new Bundle(bundle);
        if (!TextUtils.isEmpty(bundle2.getString("app_id"))) {
            zzq().zzh().zza("Package name should be null when calling setConditionalUserProperty");
        }
        bundle2.remove("app_id");
        zzb(bundle2, j9);
    }

    public final void zza(String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotEmpty(str);
        zza();
        zzb(str, str2, str3, bundle);
    }

    public final ArrayList<Bundle> zza(String str, String str2) {
        return zzb((String) null, str, str2);
    }

    public final ArrayList<Bundle> zza(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zza();
        return zzb(str, str2, str3);
    }

    public final Map<String, Object> zza(String str, String str2, boolean z8) {
        return zzb((String) null, str, str2, z8);
    }

    public final Map<String, Object> zza(String str, String str2, String str3, boolean z8) {
        Preconditions.checkNotEmpty(str);
        zza();
        return zzb(str, str2, str3, z8);
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }
}
