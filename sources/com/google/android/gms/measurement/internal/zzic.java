package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.firebase.messaging.Constants;

@TargetApi(14)
/* loaded from: classes2.dex */
final class zzic implements Application.ActivityLifecycleCallbacks {
    private final /* synthetic */ zzhe zza;

    private zzic(zzhe zzheVar) {
        this.zza = zzheVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(boolean z8, Uri uri, String str, String str2) {
        Bundle bundleZza;
        Bundle bundleZza2;
        this.zza.zzc();
        try {
            if (this.zza.zzs().zza(zzat.zzbe) || this.zza.zzs().zza(zzat.zzbg) || this.zza.zzs().zza(zzat.zzbf)) {
                zzkx zzkxVarZzo = this.zza.zzo();
                if (!TextUtils.isEmpty(str2)) {
                    if (str2.contains("gclid") || str2.contains("utm_campaign") || str2.contains("utm_source") || str2.contains("utm_medium")) {
                        bundleZza = zzkxVarZzo.zza(Uri.parse(str2.length() != 0 ? "https://google.com/search?".concat(str2) : new String("https://google.com/search?")));
                        if (bundleZza != null) {
                            bundleZza.putString("_cis", "referrer");
                        }
                    } else {
                        zzkxVarZzo.zzq().zzv().zza("Activity created with data 'referrer' without required params");
                    }
                }
                bundleZza = null;
            } else {
                bundleZza = null;
            }
            boolean z9 = false;
            if (z8) {
                bundleZza2 = this.zza.zzo().zza(uri);
                if (bundleZza2 != null) {
                    bundleZza2.putString("_cis", "intent");
                    if (this.zza.zzs().zza(zzat.zzbe) && !bundleZza2.containsKey("gclid") && bundleZza != null && bundleZza.containsKey("gclid")) {
                        bundleZza2.putString("_cer", String.format("gclid=%s", bundleZza.getString("gclid")));
                    }
                    this.zza.zza(str, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, bundleZza2);
                    if (this.zza.zzs().zza(zzat.zzck)) {
                        this.zza.zzb.zza(str, bundleZza2);
                    }
                }
            } else {
                bundleZza2 = null;
            }
            if (this.zza.zzs().zza(zzat.zzbg) && !this.zza.zzs().zza(zzat.zzbf) && bundleZza != null && bundleZza.containsKey("gclid") && (bundleZza2 == null || !bundleZza2.containsKey("gclid"))) {
                this.zza.zza("auto", "_lgclid", (Object) bundleZza.getString("gclid"), true);
            }
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            this.zza.zzq().zzv().zza("Activity created with referrer", str2);
            if (this.zza.zzs().zza(zzat.zzbf)) {
                if (bundleZza != null) {
                    this.zza.zza(str, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, bundleZza);
                    if (this.zza.zzs().zza(zzat.zzck)) {
                        this.zza.zzb.zza(str, bundleZza);
                    }
                } else {
                    this.zza.zzq().zzv().zza("Referrer does not contain valid parameters", str2);
                }
                this.zza.zza("auto", "_ldl", (Object) null, true);
                return;
            }
            if (str2.contains("gclid") && (str2.contains("utm_campaign") || str2.contains("utm_source") || str2.contains("utm_medium") || str2.contains("utm_term") || str2.contains("utm_content"))) {
                z9 = true;
            }
            if (!z9) {
                this.zza.zzq().zzv().zza("Activity created with data 'referrer' without required params");
            } else {
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                this.zza.zza("auto", "_ldl", (Object) str2, true);
            }
        } catch (Exception e9) {
            this.zza.zzq().zze().zza("Throwable caught in handleReferrerForOnActivityCreated", e9);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        try {
            this.zza.zzq().zzw().zza("onActivityCreated");
            Intent intent = activity.getIntent();
            if (intent == null) {
                return;
            }
            Uri data = intent.getData();
            if (data != null && data.isHierarchical()) {
                this.zza.zzo();
                this.zza.zzp().zza(new zzif(this, bundle == null, data, zzkx.zza(intent) ? "gs" : "auto", data.getQueryParameter("referrer")));
            }
        } catch (Exception e9) {
            this.zza.zzq().zze().zza("Throwable caught in onActivityCreated", e9);
        } finally {
            this.zza.zzh().zza(activity, bundle);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        this.zza.zzh().zzc(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        this.zza.zzh().zzb(activity);
        zzkb zzkbVarZzj = this.zza.zzj();
        zzkbVarZzj.zzp().zza(new zzkd(zzkbVarZzj, zzkbVarZzj.zzl().elapsedRealtime()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        zzkb zzkbVarZzj = this.zza.zzj();
        zzkbVarZzj.zzp().zza(new zzka(zzkbVarZzj, zzkbVarZzj.zzl().elapsedRealtime()));
        this.zza.zzh().zza(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.zza.zzh().zzb(activity, bundle);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    public /* synthetic */ zzic(zzhe zzheVar, zzhj zzhjVar) {
        this(zzheVar);
    }
}
