package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.IntentFilter;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;

@ShowFirstParty
@VisibleForTesting
/* loaded from: classes2.dex */
final class zzfn extends zzfm {
    private static final Object zzakn = new Object();
    private static zzfn zzaky;
    private Context zzako;
    private zzcb zzakp;
    private volatile zzby zzakq;
    private zzfq zzakv;
    private zzdn zzakw;
    private int zzakr = 1800000;
    private boolean zzaks = true;
    private boolean zzakt = false;
    private boolean connected = true;
    private boolean zzaku = true;
    private zzcc zzaig = new zzfo(this);
    private boolean zzakx = false;

    private zzfn() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isPowerSaveMode() {
        return this.zzakx || !this.connected || this.zzakr <= 0;
    }

    public static zzfn zzjq() {
        if (zzaky == null) {
            zzaky = new zzfn();
        }
        return zzaky;
    }

    @Override // com.google.android.gms.tagmanager.zzfm
    public final synchronized void dispatch() {
        if (this.zzakt) {
            this.zzakq.zzc(new zzfp(this));
        } else {
            zzdi.zzab("Dispatch call queued. Dispatch will run once initialization is complete.");
            this.zzaks = true;
        }
    }

    public final synchronized void zza(Context context, zzby zzbyVar) {
        if (this.zzako != null) {
            return;
        }
        this.zzako = context.getApplicationContext();
        if (this.zzakq == null) {
            this.zzakq = zzbyVar;
        }
    }

    @Override // com.google.android.gms.tagmanager.zzfm
    public final synchronized void zzf(boolean z8) {
        zza(this.zzakx, z8);
    }

    @Override // com.google.android.gms.tagmanager.zzfm
    public final synchronized void zzjp() {
        if (!isPowerSaveMode()) {
            this.zzakv.zzjt();
        }
    }

    public final synchronized zzcb zzjr() {
        if (this.zzakp == null) {
            if (this.zzako == null) {
                throw new IllegalStateException("Cant get a store unless we have a context");
            }
            this.zzakp = new zzeb(this.zzaig, this.zzako);
        }
        if (this.zzakv == null) {
            zzfr zzfrVar = new zzfr(this, null);
            this.zzakv = zzfrVar;
            int i9 = this.zzakr;
            if (i9 > 0) {
                zzfrVar.zzh(i9);
            }
        }
        this.zzakt = true;
        if (this.zzaks) {
            dispatch();
            this.zzaks = false;
        }
        if (this.zzakw == null && this.zzaku) {
            zzdn zzdnVar = new zzdn(this);
            this.zzakw = zzdnVar;
            Context context = this.zzako;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            context.registerReceiver(zzdnVar, intentFilter);
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("com.google.analytics.RADIO_POWERED");
            intentFilter2.addCategory(context.getPackageName());
            context.registerReceiver(zzdnVar, intentFilter2);
        }
        return this.zzakp;
    }

    @VisibleForTesting
    public final synchronized void zza(boolean z8, boolean z9) {
        boolean zIsPowerSaveMode = isPowerSaveMode();
        this.zzakx = z8;
        this.connected = z9;
        if (isPowerSaveMode() == zIsPowerSaveMode) {
            return;
        }
        if (isPowerSaveMode()) {
            this.zzakv.cancel();
            zzdi.zzab("PowerSaveMode initiated.");
        } else {
            this.zzakv.zzh(this.zzakr);
            zzdi.zzab("PowerSaveMode terminated.");
        }
    }
}
