package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzap;
import com.google.android.gms.internal.gtm.zzby;
import com.google.android.gms.internal.gtm.zzch;
import com.google.android.gms.internal.gtm.zzcw;
import com.google.android.gms.internal.gtm.zzcy;
import com.google.android.gms.internal.gtm.zzda;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@VisibleForTesting
/* loaded from: classes2.dex */
public final class GoogleAnalytics extends com.google.android.gms.analytics.zza {
    private static List<Runnable> zzrp = new ArrayList();
    private boolean zzrq;
    private Set<zza> zzrr;
    private boolean zzrs;
    private boolean zzrt;
    private volatile boolean zzru;
    private boolean zzrv;

    public interface zza {
        void zzc(Activity activity);

        void zzd(Activity activity);
    }

    @TargetApi(14)
    public class zzb implements Application.ActivityLifecycleCallbacks {
        public zzb() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStarted(Activity activity) {
            GoogleAnalytics.this.zza(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStopped(Activity activity) {
            GoogleAnalytics.this.zzb(activity);
        }
    }

    @VisibleForTesting
    public GoogleAnalytics(zzap zzapVar) {
        super(zzapVar);
        this.zzrr = new HashSet();
    }

    public static GoogleAnalytics getInstance(Context context) {
        return zzap.zzc(context).zzde();
    }

    public static void zzah() {
        synchronized (GoogleAnalytics.class) {
            List<Runnable> list = zzrp;
            if (list != null) {
                Iterator<Runnable> it = list.iterator();
                while (it.hasNext()) {
                    it.next().run();
                }
                zzrp = null;
            }
        }
    }

    public final void dispatchLocalHits() {
        zzab().zzcs().zzci();
    }

    @TargetApi(14)
    public final void enableAutoActivityReports(Application application) {
        if (this.zzrs) {
            return;
        }
        application.registerActivityLifecycleCallbacks(new zzb());
        this.zzrs = true;
    }

    public final boolean getAppOptOut() {
        return this.zzru;
    }

    @Deprecated
    public final Logger getLogger() {
        return zzch.getLogger();
    }

    public final boolean isDryRunEnabled() {
        return this.zzrt;
    }

    public final boolean isInitialized() {
        return this.zzrq;
    }

    public final Tracker newTracker(String str) {
        Tracker tracker;
        synchronized (this) {
            tracker = new Tracker(zzab(), str, null);
            tracker.zzag();
        }
        return tracker;
    }

    public final void reportActivityStart(Activity activity) {
        if (this.zzrs) {
            return;
        }
        zza(activity);
    }

    public final void reportActivityStop(Activity activity) {
        if (this.zzrs) {
            return;
        }
        zzb(activity);
    }

    public final void setAppOptOut(boolean z8) {
        this.zzru = z8;
        if (this.zzru) {
            zzab().zzcs().zzch();
        }
    }

    public final void setDryRun(boolean z8) {
        this.zzrt = z8;
    }

    public final void setLocalDispatchPeriod(int i9) {
        zzab().zzcs().setLocalDispatchPeriod(i9);
    }

    @Deprecated
    public final void setLogger(Logger logger) {
        zzch.setLogger(logger);
        if (this.zzrv) {
            return;
        }
        String str = zzby.zzzb.get();
        String str2 = zzby.zzzb.get();
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 112);
        sb.append("GoogleAnalytics.setLogger() is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag.");
        sb.append(str2);
        sb.append(" DEBUG");
        Log.i(str, sb.toString());
        this.zzrv = true;
    }

    @VisibleForTesting
    public final void zza(Activity activity) {
        Iterator<zza> it = this.zzrr.iterator();
        while (it.hasNext()) {
            it.next().zzc(activity);
        }
    }

    public final void zzag() {
        zzda zzdaVarZzcu = zzab().zzcu();
        zzdaVarZzcu.zzgh();
        if (zzdaVarZzcu.zzgi()) {
            setDryRun(zzdaVarZzcu.zzgj());
        }
        zzdaVarZzcu.zzgh();
        this.zzrq = true;
    }

    @VisibleForTesting
    public final void zzb(Activity activity) {
        Iterator<zza> it = this.zzrr.iterator();
        while (it.hasNext()) {
            it.next().zzd(activity);
        }
    }

    public final void zza(zza zzaVar) {
        this.zzrr.add(zzaVar);
        Context context = zzab().getContext();
        if (context instanceof Application) {
            enableAutoActivityReports((Application) context);
        }
    }

    public final void zzb(zza zzaVar) {
        this.zzrr.remove(zzaVar);
    }

    public final Tracker newTracker(int i9) {
        Tracker tracker;
        zzcy zzcyVar;
        synchronized (this) {
            tracker = new Tracker(zzab(), null, null);
            if (i9 > 0 && (zzcyVar = (zzcy) new zzcw(zzab()).zzq(i9)) != null) {
                tracker.zza(zzcyVar);
            }
            tracker.zzag();
        }
        return tracker;
    }
}
