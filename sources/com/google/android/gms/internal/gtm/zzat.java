package com.google.android.gms.internal.gtm;

import android.content.ComponentName;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;

@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzat extends zzan {
    private final zzav zzxa;
    private zzce zzxb;
    private final zzbs zzxc;
    private final zzcv zzxd;

    public zzat(zzap zzapVar) {
        super(zzapVar);
        this.zzxd = new zzcv(zzapVar.zzcn());
        this.zzxa = new zzav(this);
        this.zzxc = new zzau(this, zzapVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onServiceDisconnected(ComponentName componentName) {
        com.google.android.gms.analytics.zzk.zzav();
        if (this.zzxb != null) {
            this.zzxb = null;
            zza("Disconnected from device AnalyticsService", componentName);
            zzcs().zzck();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzce zzceVar) {
        com.google.android.gms.analytics.zzk.zzav();
        this.zzxb = zzceVar;
        zzdo();
        zzcs().onServiceConnected();
    }

    private final void zzdo() {
        this.zzxd.start();
        this.zzxc.zzh(zzby.zzaaj.get().longValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzdp() {
        com.google.android.gms.analytics.zzk.zzav();
        if (isConnected()) {
            zzq("Inactivity, disconnecting from device AnalyticsService");
            disconnect();
        }
    }

    public final boolean connect() {
        com.google.android.gms.analytics.zzk.zzav();
        zzdb();
        if (this.zzxb != null) {
            return true;
        }
        zzce zzceVarZzdq = this.zzxa.zzdq();
        if (zzceVarZzdq == null) {
            return false;
        }
        this.zzxb = zzceVarZzdq;
        zzdo();
        return true;
    }

    public final void disconnect() {
        com.google.android.gms.analytics.zzk.zzav();
        zzdb();
        try {
            ConnectionTracker.getInstance().unbindService(getContext(), this.zzxa);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        if (this.zzxb != null) {
            this.zzxb = null;
            zzcs().zzck();
        }
    }

    public final boolean isConnected() {
        com.google.android.gms.analytics.zzk.zzav();
        zzdb();
        return this.zzxb != null;
    }

    @Override // com.google.android.gms.internal.gtm.zzan
    public final void zzaw() {
    }

    public final boolean zzb(zzcd zzcdVar) {
        Preconditions.checkNotNull(zzcdVar);
        com.google.android.gms.analytics.zzk.zzav();
        zzdb();
        zzce zzceVar = this.zzxb;
        if (zzceVar == null) {
            return false;
        }
        try {
            zzceVar.zza(zzcdVar.zzdm(), zzcdVar.zzfh(), zzcdVar.zzfj() ? zzbq.zzet() : zzbq.zzeu(), Collections.emptyList());
            zzdo();
            return true;
        } catch (RemoteException unused) {
            zzq("Failed to send hits to AnalyticsService");
            return false;
        }
    }

    public final boolean zzdn() {
        com.google.android.gms.analytics.zzk.zzav();
        zzdb();
        zzce zzceVar = this.zzxb;
        if (zzceVar == null) {
            return false;
        }
        try {
            zzceVar.zzch();
            zzdo();
            return true;
        } catch (RemoteException unused) {
            zzq("Failed to clear hits from AnalyticsService");
            return false;
        }
    }
}
