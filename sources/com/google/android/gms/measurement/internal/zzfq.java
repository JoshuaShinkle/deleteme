package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.measurement.zzmt;
import com.google.firebase.messaging.Constants;

/* loaded from: classes2.dex */
final class zzfq implements Runnable {
    private final /* synthetic */ com.google.android.gms.internal.measurement.zzd zza;
    private final /* synthetic */ ServiceConnection zzb;
    private final /* synthetic */ zzfr zzc;

    public zzfq(zzfr zzfrVar, com.google.android.gms.internal.measurement.zzd zzdVar, ServiceConnection serviceConnection) {
        this.zzc = zzfrVar;
        this.zza = zzdVar;
        this.zzb = serviceConnection;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00eb  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void run() {
        zzfr zzfrVar = this.zzc;
        zzfo zzfoVar = zzfrVar.zza;
        String str = zzfrVar.zzb;
        com.google.android.gms.internal.measurement.zzd zzdVar = this.zza;
        ServiceConnection serviceConnection = this.zzb;
        Bundle bundleZza = zzfoVar.zza(str, zzdVar);
        zzfoVar.zza.zzp().zzc();
        if (bundleZza != null) {
            long j9 = bundleZza.getLong("install_begin_timestamp_seconds", 0L) * 1000;
            if (j9 == 0) {
                zzfoVar.zza.zzq().zzh().zza("Service response is missing Install Referrer install timestamp");
            } else {
                String string = bundleZza.getString("install_referrer");
                if (string == null || string.isEmpty()) {
                    zzfoVar.zza.zzq().zze().zza("No referrer defined in Install Referrer response");
                } else {
                    zzfoVar.zza.zzq().zzw().zza("InstallReferrer API result", string);
                    Bundle bundleZza2 = zzfoVar.zza.zzh().zza(Uri.parse(string.length() != 0 ? "?".concat(string) : new String("?")));
                    if (bundleZza2 == null) {
                        zzfoVar.zza.zzq().zze().zza("No campaign params defined in Install Referrer result");
                    } else {
                        String string2 = bundleZza2.getString("medium");
                        if ((string2 == null || "(not set)".equalsIgnoreCase(string2) || "organic".equalsIgnoreCase(string2)) ? false : true) {
                            long j10 = bundleZza.getLong("referrer_click_timestamp_seconds", 0L) * 1000;
                            if (j10 == 0) {
                                zzfoVar.zza.zzq().zze().zza("Install Referrer is missing click timestamp for ad campaign");
                            } else {
                                bundleZza2.putLong("click_timestamp", j10);
                                if (j9 != zzfoVar.zza.zzb().zzi.zza()) {
                                    zzfoVar.zza.zzq().zzw().zza("Install Referrer campaign has already been logged");
                                } else if (!zzmt.zzb() || !zzfoVar.zza.zza().zza(zzat.zzbu) || zzfoVar.zza.zzaa()) {
                                    zzfoVar.zza.zzb().zzi.zza(j9);
                                    zzfoVar.zza.zzq().zzw().zza("Logging Install Referrer campaign from sdk with ", "referrer API");
                                    bundleZza2.putString("_cis", "referrer API");
                                    zzfoVar.zza.zzg().zza("auto", Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, bundleZza2);
                                }
                            }
                        } else if (j9 != zzfoVar.zza.zzb().zzi.zza()) {
                        }
                    }
                }
            }
        }
        if (serviceConnection != null) {
            ConnectionTracker.getInstance().unbindService(zzfoVar.zza.zzm(), serviceConnection);
        }
    }
}
