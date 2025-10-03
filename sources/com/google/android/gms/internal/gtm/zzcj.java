package com.google.android.gms.internal.gtm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

/* loaded from: classes2.dex */
class zzcj extends BroadcastReceiver {

    @VisibleForTesting
    private static final String zzabm = "com.google.android.gms.internal.gtm.zzcj";
    private boolean zzabn;
    private boolean zzabo;
    private final zzap zzwc;

    public zzcj(zzap zzapVar) {
        Preconditions.checkNotNull(zzapVar);
        this.zzwc = zzapVar;
    }

    private final void zzfp() {
        this.zzwc.zzco();
        this.zzwc.zzcs();
    }

    @VisibleForTesting
    private final boolean zzfr() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.zzwc.getContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isConnected()) {
                    return true;
                }
            }
        } catch (SecurityException unused) {
        }
        return false;
    }

    public final boolean isConnected() {
        if (!this.zzabn) {
            this.zzwc.zzco().zzt("Connectivity unknown. Receiver not registered");
        }
        return this.zzabo;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        zzfp();
        String action = intent.getAction();
        this.zzwc.zzco().zza("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            boolean zZzfr = zzfr();
            if (this.zzabo != zZzfr) {
                this.zzabo = zZzfr;
                zzae zzaeVarZzcs = this.zzwc.zzcs();
                zzaeVarZzcs.zza("Network connectivity status changed", Boolean.valueOf(zZzfr));
                zzaeVarZzcs.zzcq().zza(new zzag(zzaeVarZzcs, zZzfr));
                return;
            }
            return;
        }
        if (!"com.google.analytics.RADIO_POWERED".equals(action)) {
            this.zzwc.zzco().zzd("NetworkBroadcastReceiver received unknown action", action);
        } else {
            if (intent.hasExtra(zzabm)) {
                return;
            }
            zzae zzaeVarZzcs2 = this.zzwc.zzcs();
            zzaeVarZzcs2.zzq("Radio powered up");
            zzaeVarZzcs2.zzci();
        }
    }

    public final void unregister() {
        if (this.zzabn) {
            this.zzwc.zzco().zzq("Unregistering connectivity change receiver");
            this.zzabn = false;
            this.zzabo = false;
            try {
                this.zzwc.getContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e9) {
                this.zzwc.zzco().zze("Failed to unregister the network broadcast receiver", e9);
            }
        }
    }

    public final void zzfo() {
        zzfp();
        if (this.zzabn) {
            return;
        }
        Context context = this.zzwc.getContext();
        context.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        IntentFilter intentFilter = new IntentFilter("com.google.analytics.RADIO_POWERED");
        intentFilter.addCategory(context.getPackageName());
        context.registerReceiver(this, intentFilter);
        this.zzabo = zzfr();
        this.zzwc.zzco().zza("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzabo));
        this.zzabn = true;
    }

    @VisibleForTesting
    public final void zzfq() {
        Context context = this.zzwc.getContext();
        Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(zzabm, true);
        context.sendOrderedBroadcast(intent, null);
    }
}
