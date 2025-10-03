package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;

/* loaded from: classes2.dex */
public class zzag {
    private static volatile zzag zzb = null;
    private static Boolean zzh = null;

    @VisibleForTesting
    private static String zzi = "allow_remote_dynamite";
    private static boolean zzj = false;
    protected final Clock zza;
    private final String zzc;
    private final ExecutorService zzd;
    private final AppMeasurementSdk zze;
    private List<Pair<com.google.android.gms.measurement.internal.zzhc, zzd>> zzf;
    private int zzg;
    private boolean zzk;
    private String zzl;
    private zzv zzm;

    public static class zza extends zzaa {
        private final com.google.android.gms.measurement.internal.zzhd zza;

        public zza(com.google.android.gms.measurement.internal.zzhd zzhdVar) {
            this.zza = zzhdVar;
        }

        @Override // com.google.android.gms.internal.measurement.zzab
        public final void zza(String str, String str2, Bundle bundle, long j9) {
            this.zza.interceptEvent(str, str2, bundle, j9);
        }

        @Override // com.google.android.gms.internal.measurement.zzab
        public final int zza() {
            return System.identityHashCode(this.zza);
        }
    }

    public abstract class zzb implements Runnable {
        final long zza;
        final long zzb;
        private final boolean zzc;

        public zzb(zzag zzagVar) {
            this(true);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (zzag.this.zzk) {
                zzb();
                return;
            }
            try {
                zza();
            } catch (Exception e9) {
                zzag.this.zza(e9, false, this.zzc);
                zzb();
            }
        }

        public abstract void zza();

        public void zzb() {
        }

        public zzb(boolean z8) {
            this.zza = zzag.this.zza.currentTimeMillis();
            this.zzb = zzag.this.zza.elapsedRealtime();
            this.zzc = z8;
        }
    }

    public class zzc implements Application.ActivityLifecycleCallbacks {
        public zzc() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityCreated(Activity activity, Bundle bundle) {
            zzag.this.zza(new zzbp(this, bundle, activity));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityDestroyed(Activity activity) {
            zzag.this.zza(new zzbu(this, activity));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityPaused(Activity activity) {
            zzag.this.zza(new zzbq(this, activity));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityResumed(Activity activity) {
            zzag.this.zza(new zzbr(this, activity));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            zzt zztVar = new zzt();
            zzag.this.zza(new zzbs(this, activity, zztVar));
            Bundle bundleZzb = zztVar.zzb(50L);
            if (bundleZzb != null) {
                bundle.putAll(bundleZzb);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStarted(Activity activity) {
            zzag.this.zza(new zzbo(this, activity));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStopped(Activity activity) {
            zzag.this.zza(new zzbt(this, activity));
        }
    }

    public static class zzd extends zzaa {
        private final com.google.android.gms.measurement.internal.zzhc zza;

        public zzd(com.google.android.gms.measurement.internal.zzhc zzhcVar) {
            this.zza = zzhcVar;
        }

        @Override // com.google.android.gms.internal.measurement.zzab
        public final void zza(String str, String str2, Bundle bundle, long j9) {
            this.zza.onEvent(str, str2, bundle, j9);
        }

        @Override // com.google.android.gms.internal.measurement.zzab
        public final int zza() {
            return System.identityHashCode(this.zza);
        }
    }

    private zzag(Context context, String str, String str2, String str3, Bundle bundle) {
        if (str == null || !zzc(str2, str3)) {
            this.zzc = "FA";
        } else {
            this.zzc = str;
        }
        this.zza = DefaultClock.getInstance();
        this.zzd = zzi.zza().zza(new zzas(this), zzr.zza);
        this.zze = new AppMeasurementSdk(this);
        if (!(!zze(context) || zzk())) {
            this.zzl = null;
            this.zzk = true;
            Log.w(this.zzc, "Disabling data collection. Found google_app_id in strings.xml but Google Analytics for Firebase is missing. Remove this value or add Google Analytics for Firebase to resume data collection.");
            return;
        }
        if (zzc(str2, str3)) {
            this.zzl = str2;
        } else {
            this.zzl = "fa";
            if (str2 == null || str3 == null) {
                if ((str2 == null) ^ (str3 == null)) {
                    Log.w(this.zzc, "Specified origin or custom app id is null. Both parameters will be ignored.");
                }
            } else {
                Log.v(this.zzc, "Deferring to Google Analytics for Firebase for event data collection. https://goo.gl/J1sWQy");
            }
        }
        zza(new zzaj(this, str2, str3, context, bundle));
        Application application = (Application) context.getApplicationContext();
        if (application == null) {
            Log.w(this.zzc, "Unable to register lifecycle notifications. Application null.");
        } else {
            application.registerActivityLifecycleCallbacks(new zzc());
        }
    }

    public static zzag zza(Context context) {
        return zza(context, (String) null, (String) null, (String) null, (Bundle) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzc(String str, String str2) {
        return (str2 == null || str == null || zzk()) ? false : true;
    }

    private static boolean zze(Context context) {
        return com.google.android.gms.measurement.internal.zzik.zza(context, "google_app_id") != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzf(Context context) {
        return DynamiteModule.getRemoteVersion(context, ModuleDescriptor.MODULE_ID);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzg(Context context) {
        return DynamiteModule.getLocalVersion(context, ModuleDescriptor.MODULE_ID);
    }

    private static boolean zzk() {
        return true;
    }

    public final void zzb(com.google.android.gms.measurement.internal.zzhc zzhcVar) {
        Preconditions.checkNotNull(zzhcVar);
        zza(new zzbk(this, zzhcVar));
    }

    public final String zzd() {
        zzt zztVar = new zzt();
        zza(new zzaw(this, zztVar));
        return zztVar.zza(50L);
    }

    public final String zzh() {
        zzt zztVar = new zzt();
        zza(new zzbh(this, zztVar));
        return zztVar.zza(120000L);
    }

    public final String zzi() {
        return this.zzl;
    }

    public static zzag zza(Context context, String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotNull(context);
        if (zzb == null) {
            synchronized (zzag.class) {
                if (zzb == null) {
                    zzb = new zzag(context, str, str2, str3, bundle);
                }
            }
        }
        return zzb;
    }

    public final void zzc(String str) {
        zza(new zzau(this, str));
    }

    public final long zze() {
        zzt zztVar = new zzt();
        zza(new zzaz(this, zztVar));
        Long l9 = (Long) zzt.zza(zztVar.zzb(500L), Long.class);
        if (l9 != null) {
            return l9.longValue();
        }
        long jNextLong = new Random(System.nanoTime() ^ this.zza.currentTimeMillis()).nextLong();
        int i9 = this.zzg + 1;
        this.zzg = i9;
        return jNextLong + i9;
    }

    public final String zzf() {
        zzt zztVar = new zzt();
        zza(new zzay(this, zztVar));
        return zztVar.zza(500L);
    }

    public final String zzg() {
        zzt zztVar = new zzt();
        zza(new zzbb(this, zztVar));
        return zztVar.zza(500L);
    }

    public final void zzb(String str, String str2, Bundle bundle) {
        zza(new zzal(this, str, str2, bundle));
    }

    public final String zzc() {
        zzt zztVar = new zzt();
        zza(new zzax(this, zztVar));
        return zztVar.zza(500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzh(Context context) {
        synchronized (zzag.class) {
            try {
            } catch (Exception e9) {
                Log.e("FA", "Exception reading flag from SharedPreferences.", e9);
                zzh = Boolean.FALSE;
            }
            if (zzh != null) {
                return;
            }
            if (zza(context, "app_measurement_internal_disable_startup_flags")) {
                zzh = Boolean.FALSE;
                return;
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
            zzh = Boolean.valueOf(sharedPreferences.getBoolean(zzi, false));
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.remove(zzi);
            editorEdit.apply();
        }
    }

    public final List<Bundle> zzb(String str, String str2) {
        zzt zztVar = new zzt();
        zza(new zzak(this, str, str2, zztVar));
        List<Bundle> list = (List) zzt.zza(zztVar.zzb(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS), List.class);
        return list == null ? Collections.emptyList() : list;
    }

    public final int zzd(String str) {
        zzt zztVar = new zzt();
        zza(new zzbe(this, str, zztVar));
        Integer num = (Integer) zzt.zza(zztVar.zzb(10000L), Integer.class);
        if (num == null) {
            return 25;
        }
        return num.intValue();
    }

    public final void zzc(Bundle bundle) {
        zza(new zzbi(this, bundle));
    }

    public final void zzb(Bundle bundle) {
        zza(new zzao(this, bundle));
    }

    public final AppMeasurementSdk zza() {
        return this.zze;
    }

    public final void zzb() {
        zza(new zzar(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzb zzbVar) {
        this.zzd.execute(zzbVar);
    }

    public final void zzb(long j9) {
        zza(new zzat(this, j9));
    }

    public final zzv zza(Context context, boolean z8) {
        DynamiteModule.VersionPolicy versionPolicy;
        try {
            if (z8) {
                versionPolicy = DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION;
            } else {
                versionPolicy = DynamiteModule.PREFER_LOCAL;
            }
            return zzu.asInterface(DynamiteModule.load(context, versionPolicy, ModuleDescriptor.MODULE_ID).instantiate("com.google.android.gms.measurement.internal.AppMeasurementDynamiteService"));
        } catch (DynamiteModule.LoadingException e9) {
            zza((Exception) e9, true, false);
            return null;
        }
    }

    public final void zzb(String str) {
        zza(new zzav(this, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(Exception exc, boolean z8, boolean z9) {
        this.zzk |= z8;
        if (z8) {
            Log.w(this.zzc, "Data collection startup failed. No data will be collected.", exc);
            return;
        }
        if (z9) {
            zza(5, "Error with data collection. Data lost.", exc, (Object) null, (Object) null);
        }
        Log.w(this.zzc, "Error with data collection. Data lost.", exc);
    }

    public final void zza(com.google.android.gms.measurement.internal.zzhd zzhdVar) {
        zza(new zzbf(this, zzhdVar));
    }

    public final void zza(com.google.android.gms.measurement.internal.zzhc zzhcVar) {
        Preconditions.checkNotNull(zzhcVar);
        zza(new zzbl(this, zzhcVar));
    }

    public final void zza(String str, Bundle bundle) {
        zza(null, str, bundle, false, true, null);
    }

    public final void zza(String str, String str2, Bundle bundle) {
        zza(str, str2, bundle, true, true, null);
    }

    public final void zza(String str, String str2, Bundle bundle, long j9) {
        zza(str, str2, bundle, true, false, Long.valueOf(j9));
    }

    private final void zza(String str, String str2, Bundle bundle, boolean z8, boolean z9, Long l9) {
        zza(new zzbn(this, l9, str, str2, bundle, z8, z9));
    }

    public final void zza(String str, String str2) {
        zza((String) null, str, (Object) str2, false);
    }

    public final void zza(String str, String str2, Object obj) {
        zza(str, str2, obj, true);
    }

    private final void zza(String str, String str2, Object obj, boolean z8) {
        zza(new zzbm(this, str, str2, obj, z8));
    }

    public final void zza(Bundle bundle) {
        zza(new zzai(this, bundle));
    }

    public final void zza(String str) {
        zza(new zzan(this, str));
    }

    public final void zza(Activity activity, String str, String str2) {
        zza(new zzam(this, activity, str, str2));
    }

    public final void zza(Boolean bool) {
        zza(new zzap(this, bool));
    }

    public final void zza(long j9) {
        zza(new zzaq(this, j9));
    }

    public final Map<String, Object> zza(String str, String str2, boolean z8) {
        zzt zztVar = new zzt();
        zza(new zzba(this, str, str2, z8, zztVar));
        Bundle bundleZzb = zztVar.zzb(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
        if (bundleZzb != null && bundleZzb.size() != 0) {
            HashMap map = new HashMap(bundleZzb.size());
            for (String str3 : bundleZzb.keySet()) {
                Object obj = bundleZzb.get(str3);
                if ((obj instanceof Double) || (obj instanceof Long) || (obj instanceof String)) {
                    map.put(str3, obj);
                }
            }
            return map;
        }
        return Collections.emptyMap();
    }

    public final void zza(int i9, String str, Object obj, Object obj2, Object obj3) {
        zza(new zzbd(this, false, 5, str, obj, null, null));
    }

    public final Bundle zza(Bundle bundle, boolean z8) {
        zzt zztVar = new zzt();
        zza(new zzbc(this, bundle, zztVar));
        if (z8) {
            return zztVar.zzb(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
        }
        return null;
    }

    public final Object zza(int i9) {
        zzt zztVar = new zzt();
        zza(new zzbg(this, zztVar, i9));
        return zzt.zza(zztVar.zzb(15000L), Object.class);
    }

    public final void zza(boolean z8) {
        zza(new zzbj(this, z8));
    }

    private static boolean zza(Context context, String str) {
        Bundle bundle;
        Preconditions.checkNotEmpty(str);
        try {
            ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && (bundle = applicationInfo.metaData) != null) {
                return bundle.getBoolean(str);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }
}
